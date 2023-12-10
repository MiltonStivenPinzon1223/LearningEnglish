package com.edu.ue.myproject.model;

public class Categories {
    public String cat_id;
    public String cat_category;
    public String cat_progress;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_category() {
        return cat_category;
    }

    public void setCat_category(String cat_category) {
        this.cat_category = cat_category;
    }

    public int getCat_progress() {
        return Integer.parseInt(cat_progress);
    }

    public void setCat_progress(String cat_progress) {
        this.cat_progress = cat_progress;
    }
}
