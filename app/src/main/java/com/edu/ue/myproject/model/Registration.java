package com.edu.ue.myproject.model;

public class Registration {
    public String use_id;
    public String wor_id;

    public String getUse_id() {
        return use_id;
    }

    public void setUse_id(String use_id) {
        this.use_id = use_id;
    }

    public String getWor_id() {
        return wor_id;
    }



    public void setWor_id(String wor_id) {
        this.wor_id = wor_id;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "use_id='" + use_id + '\'' +
                ", wor_id='" + wor_id + '\'' +
                '}';
    }
}
