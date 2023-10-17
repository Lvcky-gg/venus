package com.example.learn.venus.data;
import com.example.learn.venus.models.Orbiter;

import com.example.learn.venus.models.OrbiterType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrbiterFileRepository {

    private final String filepath;

    public OrbiterFileRepository(String filepath) {
        this.filepath = filepath;
    }
    public List<Orbiter> findAll(){
        ArrayList<Orbiter> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            reader.readLine();
            for(String line = reader.readLine(); line != null; line = reader.readLine()){
                String[] fields = line.split(",");
                if(fields.length == 4){
                    Orbiter orbiter = new Orbiter();
                    orbiter.setOrbiterId(Integer.parseInt(fields[0]));
                    orbiter.setName(fields[1]);
                    orbiter.setType(OrbiterType.valueOf(fields[2]));
                    orbiter.setSponsor(fields[3]);
                    result.add(orbiter);
                }
            }
        }catch(IOException ex){
            //nothing?

        }
        return result;
    }
}
