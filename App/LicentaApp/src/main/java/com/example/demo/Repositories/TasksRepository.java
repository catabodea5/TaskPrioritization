package com.example.demo.Repositories;

import com.example.demo.Model.Task;
import com.example.demo.Model.User;
import com.example.demo.Model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {

    @Query("FROM Task task WHERE task.assigned_user.id = :user_id")
    List<Task> getTasksByUserId(int user_id);


    @Modifying
    @Query("update Task t set t.status = :status where t.id = :id")
    void update(@Param(value = "id") int id,
                @Param(value = "status") Status status);

    @Modifying
    @Query("update Task t set t.assigned_user = :assigned_user where t.id = :id")
    void removeAssignedTask(@Param(value = "id") int id,
                @Param(value = "assigned_user") User assigned_user);

    @Modifying
    @Query("update Task t set t.finishDate = :date where t.id = :id")
    void updateFinishDate(@Param(value = "id") int id,
                @Param(value = "date") LocalDate date);

}
