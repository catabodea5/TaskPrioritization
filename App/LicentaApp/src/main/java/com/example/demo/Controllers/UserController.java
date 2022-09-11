package com.example.demo.Controllers;

import com.example.demo.BLL.UserService;
import com.example.demo.BLL.exceptions.InvalidCredentialsException;
import com.example.demo.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(UserController.BASE_URL)
public class UserController {
    protected static final String BASE_URL = "task_prioritization/users";

    private final UserService userService;

    public UserController(UserService userBLL) {
        this.userService = userBLL;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user) throws InvalidCredentialsException {
        User userFound;

        try {
            userFound = userService.login(user);
        } catch (InvalidCredentialsException ex) {
            throw new InvalidCredentialsException(ex.getMessage());
        }
        return new ResponseEntity<>(userFound, HttpStatus.OK);
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllusers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

}
