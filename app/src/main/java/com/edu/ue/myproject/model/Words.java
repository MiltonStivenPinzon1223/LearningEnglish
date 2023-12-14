package com.edu.ue.myproject.model;

import java.util.List;

public class Words {
    public String wor_id;
    public String wor_english;
    public String wor_spanish;
    public String cat_id;
    public String word1;
    public String word2;

    public Words(List<Words> wordsList) {
        this.wor_id = wordsList.get(0).getWor_id();
        this.wor_english = wordsList.get(0).getWor_english();
        this.wor_spanish = wordsList.get(0).getWor_spanish();
        this.cat_id = wordsList.get(0).getCat_id();
        this.word1 = wordsList.get(0).getWord1();
        this.word2 = wordsList.get(0).getWord2();
    }

    public String getWor_id() {
        return wor_id;
    }

    public void setWor_id(String wor_id) {
        this.wor_id = wor_id;
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

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }
}
