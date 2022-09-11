package com.example.demo.Repositories;

import com.example.demo.Model.User;
import com.example.demo.Model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    @Query("FROM User user WHERE user.email = :email")
    User getUserByEmail(String email);

    @Modifying
    @Query("UPDATE User user SET user.expertise = :expertise, user.speed = :speed WHERE user.id = :id")
    void update(@Param(value = "id") int id,
                @Param(value = "expertise") int expertise,
                @Param(value = "speed") int speed);
}
