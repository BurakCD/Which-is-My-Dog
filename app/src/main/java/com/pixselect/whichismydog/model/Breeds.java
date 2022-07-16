
package com.pixselect.whichismydog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Breeds {

    @SerializedName("message")
    @Expose
    private List<String> message;

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }


}