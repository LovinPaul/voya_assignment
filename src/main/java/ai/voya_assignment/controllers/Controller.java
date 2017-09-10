package ai.voya_assignment.controllers;

import ai.voya_assignment.persistance.Data;
import ai.voya_assignment.persistance.MySQLDatabase;

public abstract class Controller {

    //AccountAudit
    protected static final String ACCOUNT_UNAVAILABLE = "Account unavailable, please check input and try again later.";
    protected static final String NOT_ENOUGH_MONEY = "Not enough money to withdraw!";

    //AccountOpCRUD
    protected static final String USER_DOES_NOT_EXIST = "The user does not exist in database.";
    protected static final String INVALID_INDEX = "Invalid account index.";

    //UserOpCRUD
    protected static final String EMAIL_ALREADY_EXISTS = " already exists in database.";
    protected static final String DATABASE_ERROR = "Something went wrong. Please contact us.";

    //HTTP status codes
    protected static final String HTTP_OK = " (200 OK) ";
    protected static final String HTTP_CREATED = " (201 Created) ";
    protected static final String HTTP_ACCEPTED = " (202 Accepted) ";
    protected static final String HTTP_BAD_REQUEST = " (400 Bad Request) ";
    protected static final String HTTP_NOT_FOUND = " (404 Not Found) ";
    protected static final String HTTP_CONFLICT = " (409 Conflict) ";
    protected static final String HTTP_INTERNAL_SERVER_ERROR = " (500 Internal Server Error) ";
    
    protected final Data database = MySQLDatabase.getINSTACE();

}
