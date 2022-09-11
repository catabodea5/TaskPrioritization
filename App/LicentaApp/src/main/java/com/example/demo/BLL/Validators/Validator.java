package com.example.demo.BLL.Validators;

import com.example.demo.BLL.exceptions.InvalidTaskException;

public interface Validator<T> {
    void validate(T entity) throws InvalidTaskException;
}