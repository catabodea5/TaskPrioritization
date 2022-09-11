package com.example.demo.BLL.algorithm;

import com.example.demo.Model.Task;
import com.example.demo.Model.User;
import com.example.demo.Model.enums.Severity;
import com.example.demo.Model.enums.Type;

import java.time.Period;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class UserAssignment {

    private List<Severity> severities = Arrays.asList(
            Severity.CRITICAL,
            Severity.MAJOR,
            Severity.MINOR,
            Severity.TRIVIAL);
    private List<Type> types = Arrays.asList(Type.BLOCKING,
            Type.FUNCTIONAL,
            Type.ENHANCEMENT,
            Type.COSMETIC);

    private Integer[][] requiredExpertise ={{50,50,50,50}, {40,30,20,20}, {30,20,10,10}, {20,0,0,0}};
    private Integer[][] requiredSpeed = {{50,50,40,30}, {40,30,20,10}, {40,20,10,0}, {30,0,0,0}};

    public UserAssignment(){

    }
    public boolean checkAvailability(User user, Severity severity, Type type) {
        return  user.getExpertise() >= requiredExpertise[severities.indexOf(severity)][types.indexOf(type)] &&
                user.getSpeed() >= requiredSpeed[severities.indexOf(severity)][types.indexOf(type)];
    }

    public Integer updateExpertise(User user, Task task){
        int newExpertise = user.getExpertise()+ 2 + severities.indexOf(task.getSeverity()) + 1;
        return newExpertise;
    }
    public Integer updateSpeed(User user, Task task){
        long noOfDaysBetween = DAYS.between(task.getCreationDate(), task.getFinishDate());
        int newSpeed = user.getSpeed();
        if (7 - noOfDaysBetween > 0)
            newSpeed = (int) (7 - noOfDaysBetween) + user.getSpeed();
        return newSpeed;
    }
}
