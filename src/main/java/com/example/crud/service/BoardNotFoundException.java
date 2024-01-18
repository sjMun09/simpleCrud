package com.example.crud.service;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(Long id) {
        super("Board not Found with id: " + id);
    }
}
