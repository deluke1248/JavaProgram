package com.network.javagroup7;

public class SearchQuestion {
    Integer id;
    String paperName, paper;

    public SearchQuestion(Integer id, String paperName, String paper) {
        this.id = id;
        this.paperName = paperName;
        this.paper = paper;
    }

    public Integer getId() {
        return id;
    }

    public String getPaperName() {
        return paperName;
    }

    public String getPaper() {
        return paper;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }
}
