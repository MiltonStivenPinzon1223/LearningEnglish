package com.edu.ue.myproject.model;

public class Results {
    public String created_at;
    public String wor_english;
    public String wor_spanish;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getWor_english() {
        return wor_english;
    }

    public void setWor_english(String wor_english) {
        this.wor_english = wor_english;
    }

    public String getWor_spanish() {
        return wor_spanish;
    }

    public void setWor_spanish(String wor_spanish) {
        this.wor_spanish = wor_spanish;
    }

    @Override
    public String toString() {
        return "Results{" +
                "created_at='" + created_at + '\'' +
                ", wor_english='" + wor_english + '\'' +
                ", wor_spanish='" + wor_spanish + '\'' +
                '}';
    }
}
