package com.example.learn.venus.domain;

import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;

import java.util.ArrayList;
import java.util.List;

public class OrbiterResult {
    private ArrayList<String> messages = new ArrayList<>();
    private Orbiter payload;

    public void addErrorMessages(String message){
        messages.add(message);
    }
    public boolean isSuccess(){
        return messages.size() == 0;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public Orbiter getPayload() {
        return payload;
    }

    public void setPayload(Orbiter payload) {
        this.payload = payload;
    }
}
