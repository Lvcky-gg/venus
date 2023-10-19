package com.example.learn.venus.ui;

import com.example.learn.venus.data.DataAccessException;
import com.example.learn.venus.domain.OrbiterResult;
import com.example.learn.venus.domain.OrbiterService;
import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;

import java.util.List;

public class Controller {
    private final OrbiterService service;
    private final View view;

    public Controller(OrbiterService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run(){
        try{
            runMenu();
        }catch(DataAccessException e){
            view.printHeader("Fatal Err: " + e);
        }

    }

    private void runMenu() throws DataAccessException {
        MenuOptions opts;
        do{
            opts = view.displayMenuAndSelect();
            System.out.println(opts.getTitle());
            switch(opts){
                case EXIT:
                    view.printHeader("Goodbye.");
                    break;
                case DISPLAY_ORBITERS:
                    displayOrbitters();
                    break;
                case CREATE_ORBITER:
                    createOrbiter();
                    break;
                case UPDATE_ORBITER:
                    updateOrbiter();
                    break;
                case DELETE_ORBITER:
                    deleteOrbitter();
                    break;
            }

        }while(opts != MenuOptions.EXIT);
    }

    private void displayOrbitters() throws DataAccessException {
        view.printHeader(MenuOptions.DISPLAY_ORBITERS.getTitle());
        OrbiterType type = view.readOrbiterType();
        List<Orbiter> orbiters = service.findByType(type);
        view.displayOrbiters(orbiters);

    }
    private void createOrbiter() throws DataAccessException {
        view.printHeader(MenuOptions.CREATE_ORBITER.getTitle());
        Orbiter orbiter = view.makeOrbiter();
       OrbiterResult res =service.add(orbiter);
       view.displayResult(res);

    }

    private void updateOrbiter(){
        view.printHeader(MenuOptions.UPDATE_ORBITER.getTitle());

    }
    private void deleteOrbitter(){
        view.printHeader(MenuOptions.DELETE_ORBITER.getTitle());

    }
}
