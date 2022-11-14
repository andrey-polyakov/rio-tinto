package com.avinty.hr.exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "Employee with email '%s' already exists.";

    public EmployeeAlreadyExistsException(final String email) {
        super(String.format(MESSAGE, email));
    }
}
