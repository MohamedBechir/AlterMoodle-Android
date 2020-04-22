package com.example.cs425;

public class LoginResponse {
    private String email;
    private String password;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String token;
    private String error;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
