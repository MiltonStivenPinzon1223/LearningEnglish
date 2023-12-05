package com.edu.ue.myproject.model;

public class Auth {
    private String use_password;
    private String use_email;
    private String use_token;

    public String getUse_token() {
        return use_token;
    }

    public void setUse_token(String use_token) {
        this.use_token = use_token;
    }

    public String getUse_password() {
        return use_password;
    }

    public void setUse_password(String use_password) {
        this.use_password = use_password;
    }

    public String getUse_email() {
        return use_email;
    }

    public void setUse_email(String use_email) {
        this.use_email = use_email;
    }
}
