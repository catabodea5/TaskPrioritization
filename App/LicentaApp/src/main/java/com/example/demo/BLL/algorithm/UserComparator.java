package com.example.demo.BLL.algorithm;

import com.example.demo.Model.User;

import java.util.Comparator;

public class UserComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        User obj1 = (User) o1;
        User obj2 = (User) o2;
        float criteria1 = 70f/100 * obj1.getExpertise() + 30f/100 * obj1.getSpeed();
        float criteria2 = 70f/100 * obj2.getExpertise() + 30f/100 * obj2.getSpeed();
        return (int) (criteria1 - criteria2);

    }
}