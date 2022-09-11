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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;


@SpringBootTest

class LicentaApplicationTests {

    @Test
    void contextLoads() {
    }



}
