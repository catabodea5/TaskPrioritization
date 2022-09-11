package com.example.demo.Model;

import com.example.demo.Model.enums.Severity;
import com.example.demo.Model.enums.Status;
import com.example.demo.Model.enums.Type;
import com.sun.istack.NotNull;
import lombok.ToString;
import net.bytebuddy.build.ToStringPlugin;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type")
    private Type type;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "severity")
    private Severity severity;

    @ManyToOne
    @ToString.Exclude
    private User assigned_user;

    @Column(name = "date")
    private LocalDate creationDate;

    @Column(name = "finishdate")
    private LocalDate finishDate;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    private Status status;


    public Task() {
    }



    public Task(String name, User author, LocalDate creationDate, String content, Status status, Severity severity, Type type) {
        this.name = name;
        this.assigned_user = author;
        this.creationDate = creationDate;
        this.content = content;
        this.status = status;
        this.severity = severity;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getAssigned_user() {
        return assigned_user;
    }

    public void setAssigned_user(User assigned_user) {
        this.assigned_user = assigned_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(assigned_user, task.assigned_user) && Objects.equals(creationDate, task.creationDate) && Objects.equals(content, task.content) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,  assigned_user, creationDate, content, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", severity=" + severity +
                ", assigned_user=" + assigned_user +
                ", creationDate=" + creationDate +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }

}
