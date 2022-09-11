package com.example.demo.BLL.Validators;

import com.example.demo.BLL.exceptions.ExceptionMessages;
import com.example.demo.BLL.exceptions.InvalidCredentialsException;
import com.example.demo.BLL.exceptions.InvalidTaskException;
import com.example.demo.Model.Task;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

//scan app for automatic initialization
@Component
public class ValidatorTask implements Validator<Task> {

    @Override
    public void validate(Task task) throws InvalidTaskException {

        List<String> errors = new ArrayList<>();
        if(task.getType() == null || task.getSeverity() == null)
            errors.add(ExceptionMessages.taskSeverityOrTypeNullMessage);

        if(task.getCreationDate() == null )
            errors.add(ExceptionMessages.invalidDateMessage);

        String errorMessage = errors
                .stream()
                .reduce("", String::concat);

        if(!errorMessage.isEmpty()) {
            throw new InvalidTaskException(errorMessage);
        }

    }
}