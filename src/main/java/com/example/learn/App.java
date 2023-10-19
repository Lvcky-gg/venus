package com.example.learn;

import com.example.learn.venus.data.OrbiterFileRepository;
import com.example.learn.venus.domain.OrbiterService;
import com.example.learn.venus.ui.Controller;
import com.example.learn.venus.ui.View;

public class App {
    public static void main(String[] args){
        OrbiterFileRepository repository = new OrbiterFileRepository("./data/orbiters.csv");
        OrbiterService service = new OrbiterService(repository);
        View view = new View();
        Controller controller = new Controller(service, view);
        controller.run();
    }
}
