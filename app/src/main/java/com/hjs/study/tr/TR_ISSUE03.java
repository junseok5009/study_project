package com.hjs.study.tr;

import com.google.gson.annotations.SerializedName;
import com.hjs.study.model.Issue;

import java.util.ArrayList;

public class TR_ISSUE03 {

    @SerializedName("list_Issue")
    private ArrayList<Issue> list_Issue;

    public ArrayList<Issue> getList_Issue() {
        if(list_Issue==null) return new ArrayList<>();
        else return list_Issue;
    }

    public void setList_Issue(ArrayList<Issue> list_Issue) {
        this.list_Issue = list_Issue;
    }

}
