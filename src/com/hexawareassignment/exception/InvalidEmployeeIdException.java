//Task7
package com.hexawareassignment.exception;

public class InvalidEmployeeIdException extends Exception {

    public InvalidEmployeeIdException() {
        super("Invalid employee ID.");
    }

    public InvalidEmployeeIdException(String message) {
        super(message);
    }
}
