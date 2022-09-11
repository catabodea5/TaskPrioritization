package com.example.demo;

import com.example.demo.Model.Task;
import com.example.demo.Model.User;
import com.example.demo.Model.enums.Severity;
import com.example.demo.Model.enums.Status;
import com.example.demo.Model.enums.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTests {

    Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
    }

    @Test
    void getSetTitle() {
        task.setName("title");
        assertEquals(task.getName(),"title");
        task.setName("");
        assertEquals(task.getName(),"");
    }


    @Test
    void getSetContent() {
        task.setContent("desc");
        assertEquals(task.getContent(),"desc");
        task.setContent("");
        assertEquals(task.getContent(),"");
    }

    @Test
    void getSetAssignedTo() {
        User user = new User();
        task.setAssigned_user(user);
        assertEquals(task.getAssigned_user(), user);
    }


    @Test
    void getSetCreated() {
        LocalDate localDate = LocalDate.now();
        task.setCreationDate(localDate);
        assertEquals(task.getCreationDate(), localDate);
    }

    @Test
    void getSetStatus() {
        Status status = Status.TO_DO;
        task.setStatus(status);
        assertEquals(task.getStatus(), status);
    }

    @Test
    void getSetType() {
        Type type = Type.BLOCKING;
        task.setType(type);
        assertEquals(task.getType(), type);
    }
    @Test
    void getSetSeverity() {
        Severity severity = Severity.MAJOR;
        task.setSeverity(severity);
        assertEquals(task.getSeverity(),severity);
    }
    @Test
    void getSetFinishDate() {
        LocalDate localDate = LocalDate.now();
        task.setFinishDate(localDate);
        assertEquals(task.getFinishDate(), localDate);
    }
}