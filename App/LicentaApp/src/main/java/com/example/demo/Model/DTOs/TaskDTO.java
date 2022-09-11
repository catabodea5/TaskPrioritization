package com.example.demo.Model.DTOs;

import com.example.demo.Model.enums.Priority;
import com.example.demo.Model.enums.Severity;
import com.example.demo.Model.enums.Type;

import java.time.LocalDate;

public class TaskDTO {

    private String name;

    private LocalDate creationDate;

    private String content;

    private Severity severity;

    private Type type;

    public TaskDTO(String name, LocalDate creationDate, String content, Severity severity, Type type) {
        this.name = name;
        this.creationDate = creationDate;
        this.content = content;
        this.severity = severity;
        this.type = type;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public TaskDTO() {
    }

}
