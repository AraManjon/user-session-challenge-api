package com.kubikdata.controllers.response;

import java.util.Date;

/**
 * This object will encapsulate required fields to know user , token and date.
 */
public class UserResponse {

    private final String username;
    private final String token;
    private final Date date;

    public UserResponse(String username, String token, Date date) {
        this.username = username;
        this.token = token;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public Date getDate() {
        return date;
    }
}
