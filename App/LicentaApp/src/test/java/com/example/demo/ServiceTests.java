package com.example.demo;

import com.example.demo.BLL.TaskService;
import com.example.demo.BLL.UserService;
import com.example.demo.BLL.exceptions.InvalidTaskException;
import com.example.demo.BLL.exceptions.InvalidUserException;
import com.example.demo.Model.DTOs.TaskDTO;
import com.example.demo.Model.Task;
import com.example.demo.Model.enums.Severity;
import com.example.demo.Model.enums.Type;
import com.example.demo.Repositories.TasksRepository;
import com.example.demo.Repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//public class ServiceTests {
//    private final TaskService taskService = new TaskService();
//    private final UserService userService = new UserService();
//    @Autowired private TasksRepository tasksRepository;
//    @Autowired private UsersRepository usersRepository;
//
//    @Test
//    void addTask_valid_added() throws InvalidTaskException, InvalidUserException {
//        LocalDate date = LocalDate.of(2022, Month.MARCH, 8);
//        TaskDTO taskDTO = new TaskDTO("name1", date, "content1", Severity.MINOR, Type.ENHANCEMENT );
//        List<Task> allTasks = taskService.getAllTasks();
//        int size = 0;
//        if(allTasks != null){
//            size = allTasks.size();
//        }
//        this.taskService.addTask(taskDTO);
//        assertEquals(size+1, taskService.getAllTasks().size());
//    }
//}
