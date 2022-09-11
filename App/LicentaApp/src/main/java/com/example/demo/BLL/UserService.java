package com.example.demo.BLL;


import com.example.demo.BLL.exceptions.ExceptionMessages;
import com.example.demo.BLL.exceptions.InvalidCredentialsException;
import com.example.demo.BLL.exceptions.InvalidIdException;
import com.example.demo.Model.Task;
import com.example.demo.Model.User;
import com.example.demo.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private UsersRepository usersRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User login(User user) throws InvalidCredentialsException {
        User userFound = usersRepository.getUserByEmail(user.getEmail());
        if(userFound == null)
            throw new InvalidCredentialsException(ExceptionMessages.nonExistentUserMessage);
        if(!userFound.getPassword().equals(user.getPassword()))
            throw new InvalidCredentialsException(ExceptionMessages.incorrectPasswordMessage);
        return userFound;
    }


    public List<User> getAllUsers(){
        return  usersRepository.findAll();
    }


}
