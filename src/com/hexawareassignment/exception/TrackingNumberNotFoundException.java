//Task 7
package com.hexawareassignment.exception;

public class TrackingNumberNotFoundException extends Exception {

    public TrackingNumberNotFoundException() {
        super("Tracking number not found.");
    }

    public TrackingNumberNotFoundException(String message) {
        super(message);
    }
}
