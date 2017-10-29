package com.developer.r47.carnotmachinetest.models;

/**
 * Created by r47 on 29/10/17.
 * This class will encapsulate the error that is thrown by the server.
 * errorCode: displays the http error code for the server error
 * errorMessage: displays the error message for the server error
 */

public class CarnotMachineTestError {
    public int errorCode;
    public String errorMessage;

    public CarnotMachineTestError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
