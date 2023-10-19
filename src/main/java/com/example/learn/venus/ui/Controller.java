package com.example.learn.venus.ui;

import com.example.learn.venus.domain.OrbiterService;

public class Controller {
    private final OrbiterService service;
    private final View view;

    public Controller(OrbiterService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run(){
        MenuOptions opts;
        do{

        }while(opts != MenuOptions.EXIT);
    }
}
