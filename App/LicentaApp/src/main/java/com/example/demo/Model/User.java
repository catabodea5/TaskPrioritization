package com.example.demo.Model;

import com.example.demo.Model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private int id;

    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "expertise")
    private int expertise;

    @Column(name = "speed")
    private int speed;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "role")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assigned_user")
    //ignore serialization to JSON
    @JsonIgnore
    private List<Task> assigned_tasks = new ArrayList<>();

    public User() {
        super();
    }

    public User(String username, String password, String email, int expertise, int speed, Role role) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.expertise = expertise;
        this.speed = speed;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpertise(int expertise) {
        this.expertise = expertise;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getExpertise() {
        return expertise;
    }

    public int getSpeed() {
        return speed;
    }

    public Role getRole() {
        return role;
    }

    public List<Task> getAssigned_tasks() {
        return assigned_tasks;
    }

    public void setAssigned_tasks(List<Task> assigned_tasks) {
        this.assigned_tasks = assigned_tasks;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && expertise == user.expertise && speed == user.speed && username.equals(user.username) && password.equals(user.password) && email.equals(user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, expertise, speed, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", expertise=" + expertise +
                ", speed=" + speed +
                ", role=" + role +
                '}';
    }
}
