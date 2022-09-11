package com.example.demo.BLL;

import au.com.bytecode.opencsv.CSVReader;
import com.example.demo.BLL.Validators.ValidatorTask;
import com.example.demo.BLL.algorithm.UserAssignment;
import com.example.demo.BLL.algorithm.UserComparator;
import com.example.demo.BLL.exceptions.ExceptionMessages;
import com.example.demo.BLL.exceptions.InvalidIdException;
import com.example.demo.BLL.exceptions.InvalidTaskException;
import com.example.demo.BLL.exceptions.InvalidUserException;
import com.example.demo.Model.DTOs.TaskDTO;
import com.example.demo.Model.Task;
import com.example.demo.Model.User;
import com.example.demo.Model.enums.Role;
import com.example.demo.Model.enums.Severity;
import com.example.demo.Model.enums.Status;
import com.example.demo.Model.enums.Type;
import com.example.demo.Repositories.TasksRepository;
import com.example.demo.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;
import javax.lang.model.util.Types;
import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Component
public class TaskService {
    private TasksRepository tasksRepository;
    private UsersRepository usersRepository;
    private ValidatorTask taskValidator = new ValidatorTask();
    private final UserAssignment userAssignmentObject = new UserAssignment();

    @Autowired
    public void setTasksRepository(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Task> getTasksByUserId(int id){
        return tasksRepository.getTasksByUserId(id);
    }

    public List<Task> getAllTasks() {
        return tasksRepository.findAll();
    }

    //daata persistence in case of system failure
    @Transactional
    public void addDataToDatabase() throws IOException, InvalidUserException, InvalidTaskException {
        CSVReader reader = new CSVReader(new FileReader("D:\\Facultate\\An3\\Licenta\\App\\LicentaApp\\src\\main\\java\\com\\example\\demo\\Datasets\\cassandra-bug-fix-comment-log-dataset.csv"));
        List<String> mails = new ArrayList<>();
        List<String> taskNames = new ArrayList<>();
        List<String> contents = new ArrayList<>();
        List<LocalDate> dates = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        String[] nextLine = reader.readNext();
        String[] nextlineParsed = nextLine[0].split(";");

        while ((nextLine = reader.readNext()) != null) {
            nextlineParsed = nextLine[0].split(";");
            dates.add(LocalDate.parse(nextlineParsed[5].split("T")[0]));
            taskNames.add(nextlineParsed[3] + "." + taskNames.size());
            contents.add("Details for task "+ nextlineParsed[3] + "." + taskNames.size() + "...");
        }
        Random rand = new Random();
        int rand_1 = rand.nextInt(4);
        int rand_2 = rand.nextInt(4);

        for(int i=0; i<500; i++){
            Task task = new Task();
            task.setName(taskNames.get(i));
            task.setCreationDate(dates.get(i));
            task.setContent(contents.get(i));
            switch (rand_1){
                case 0:
                    task.setType(Type.BLOCKING);
                    break;
                case 1:
                    task.setType(Type.FUNCTIONAL);
                    break;
                case 2:
                    task.setType(Type.ENHANCEMENT);
                    break;
                case 3:
                    task.setType(Type.COSMETIC);
                    break;
            }
            switch (rand_2){
                case 0:
                    task.setSeverity(Severity.CRITICAL);
                    break;
                case 1:
                    task.setSeverity(Severity.MAJOR);
                    break;
                case 2:
                    task.setSeverity(Severity.MINOR);
                    break;
                case 3:
                    task.setSeverity(Severity.TRIVIAL);
                    break;
            }
            if(i<250)
                task.setStatus(Status.TO_DO);
            else
                task.setStatus(Status.IN_PROGRESS);
            task.setAssigned_user(assignUserToTask(task));
            taskValidator.validate(task);
            tasksRepository.save(task);
        }
    }
    @Transactional
    public void addvaluesinDatabase() throws InvalidUserException, IOException, InvalidTaskException {
        addDataToDatabase();
    }
    @Transactional
    public Task addTask(TaskDTO taskDTO) throws InvalidUserException, InvalidTaskException {
        Task toAdd = taskDTOtoTask(taskDTO);
        toAdd.setStatus(Status.TO_DO);
        toAdd.setAssigned_user(assignUserToTask(toAdd));
        taskValidator.validate(toAdd);
        return tasksRepository.save(toAdd);

    }

    private Task taskDTOtoTask(TaskDTO taskDTO) {
        Task taskFromDTO = new Task();

        taskFromDTO.setName(taskDTO.getName());
        taskFromDTO.setContent(taskDTO.getContent());
        taskFromDTO.setCreationDate(taskDTO.getCreationDate());
        taskFromDTO.setSeverity(taskDTO.getSeverity());
        taskFromDTO.setType(taskDTO.getType());

        return taskFromDTO;
    }

    /**
     * Assigns the user to a new task
     * @param taskDTO - Task
     *
     * @return User - assigned User
     */
    private User assignUserToTask(Task taskDTO) throws  InvalidUserException {
        List<User> allUsersFiltered = filterUsers(usersRepository.findAll());
        Collections.sort(allUsersFiltered, new UserComparator());

        for (User user : allUsersFiltered){
            if(userAssignmentObject.checkAvailability(user, taskDTO.getSeverity(), taskDTO.getType()))
                return user;
        }
        throw new InvalidUserException(ExceptionMessages.userDoesNotExist);
    }



    /**
     * Filters the users so that only the developers which have less than 5 tasks assigned can be
     * selected to have assigned a new task
     * @param all<User> - all
     *
     * @return List<User></User> - all
     */
    private List<User> filterUsers(List<User> all) {
        List<User> filtered = new ArrayList<>();
        for (User user : all) {
            if (user.getAssigned_tasks().size() <= 5 && user.getRole()== Role.DEVELOPER){
                filtered.add(user);
            }
            else{
                if (user.getRole() == Role.DEVELOPER && user.getAssigned_tasks().size() > 5)
                {
                    int nrNotCompleted = 0;
                    for (Task i : user.getAssigned_tasks()){
                        if (i.getStatus() != Status.COMPLETED)
                            nrNotCompleted+=1;
                    }
                    if (nrNotCompleted < 5){
                        filtered.add(user);
                    }

                }
            }
        }
        return filtered;
    }

    @Transactional
    public Task updateTaskStatus(int id, Task task) throws InvalidIdException {
        Task taskToBeUpdated = tasksRepository.findById(id).orElseThrow(() -> new InvalidIdException(ExceptionMessages.incorrectIdMessage));
        tasksRepository.update(taskToBeUpdated.getId(),
               task.getStatus());
        return task;
    }

    @Transactional
    public void deleteTask(int taskId) {
        tasksRepository.deleteById(taskId);
    }

    @Transactional
    public Task completetask(int id) throws InvalidIdException {
        Task taskToBeUpdated = tasksRepository.findById(id).orElseThrow(() -> new InvalidIdException(ExceptionMessages.incorrectIdMessage));
        taskToBeUpdated.setStatus(Status.COMPLETED);
        taskToBeUpdated.setFinishDate(LocalDate.now());
        tasksRepository.update(taskToBeUpdated.getId(),
                Status.COMPLETED);
        tasksRepository.updateFinishDate(taskToBeUpdated.getId(),
                LocalDate.now());

        User assignedUser = taskToBeUpdated.getAssigned_user();
        List<Task> new_assigned_tasks = assignedUser.getAssigned_tasks();

        new_assigned_tasks.remove(taskToBeUpdated);
        assignedUser.setAssigned_tasks(new_assigned_tasks);

        Integer newSpeed = userAssignmentObject.updateSpeed(assignedUser, taskToBeUpdated);
        Integer newExpertise = userAssignmentObject.updateExpertise(assignedUser, taskToBeUpdated);

        usersRepository.update(assignedUser.getId(), newExpertise, newSpeed);

        return tasksRepository.findById(id).orElseThrow(() -> new InvalidIdException(ExceptionMessages.incorrectIdMessage));

    }
}
