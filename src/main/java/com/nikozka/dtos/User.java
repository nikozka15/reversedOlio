package com.nikozka.dtos;

public record User(
    String email,
    String password,
    String confirmPassword,
    String firstName,
    String lastName,
    Boolean isTCSigned) {}
