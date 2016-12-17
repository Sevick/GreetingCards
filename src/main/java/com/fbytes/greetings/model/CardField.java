package com.fbytes.greetings.model;
/**
 * Created by S on 17.12.2016.
 */

abstract public class CardField {

    public class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }

    private String name;

    public CardField(String name){ this.name = name; };
    abstract public void validate(String value) throws ValidationException;        // check if value is valid, if its not - throw exception

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
