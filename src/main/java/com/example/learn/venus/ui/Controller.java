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
            opts = view.displayMenuAndSelect();
            System.out.println(opts.getTitle());
            switch(opts){
                case EXIT:
                    break;
                case DISPLAY_ORBITERS:
                    break;
                case CREATE_ORBITER:
                    break;
                case UPDATE_ORBITER:
                    break;
                case DELETE_ORBITER:
                    break;
            }

        }while(opts != MenuOptions.EXIT);
    }
}
