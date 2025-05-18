package com.example.fitlifeapp;

public class LoginResponse {
    private boolean success;
    private String token;
    private String mensaje;

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public String getMensaje() {
        return mensaje;
    }
}
