package com.mahesaiqbal.darkskyclient.events;

/**
 * Created by mahesaiqbal on 11/3/2017.
 */

public class ErrorEvent {

    private final String errorMessage;

    public ErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
