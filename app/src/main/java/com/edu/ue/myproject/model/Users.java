package com.edu.ue.myproject.model;

import java.util.List;

public class Users {
    public String use_id;
    public String use_name;
    public String use_email;
    public String use_password;
    public String use_status;

    public Users(List<Users> user) {
        this.use_id = user.get(0).use_id;
        this.use_name = user.get(0).use_name;
        this.use_email = user.get(0).use_email;
        this.use_password = user.get(0).use_password;
        this.use_status = user.get(0).use_status;
        this.rol_id = user.get(0).rol_id;
    }

    public Users() {

    }

    public String getUse_password() {
        return use_password;
    }

    public void setUse_password(String use_password) {
        this.use_password = use_password;
    }


    public String getUse_id() {
        return use_id;
    }

    public void setUse_id(String use_id) {
        this.use_id = use_id;
    }

    public String getUse_name() {
        return use_name;
    }

    public void setUse_name(String use_name) {
        this.use_name = use_name;
    }

    public String getUse_email() {
        return use_email;
    }

    public void setUse_email(String use_email) {
        this.use_email = use_email;
    }

    public String getUse_status() {
        return use_status;
    }

    public void setUse_status(String use_status) {
        this.use_status = use_status;
    }

    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String rol_id;
}
