package com.kubikdata.controllers.request;

/**
 * this class contains username necessary to create a userSession
 */
public class UserSessionRequest {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
