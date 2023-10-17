package com.example.learn.venus.data;
import com.example.learn.venus.models.Orbiter;

import com.example.learn.venus.models.OrbiterType;

import java.io.*;
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
                String[] fields = line.split(",", -1);
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
    public Orbiter findById(int orbiterId){
        for(Orbiter orbiter: findAll()){
            if(orbiter.getOrbiterId() == orbiterId){
                return orbiter;
            }
        }
        return null;
    }
    public List<Orbiter> findByType(OrbiterType type){
        ArrayList<Orbiter> result = new ArrayList<>();
        for(Orbiter orbiter: findAll()){
            if(orbiter.getType() == type){
                result.add(orbiter);
            }
        }
        return result;
    }

    public Orbiter add(Orbiter orbiter) throws DataAccessException {
        List<Orbiter> all = findAll();
        int nextId = 0;
        for(Orbiter o: all){
            nextId = Math.max(nextId, o.getOrbiterId());
        }
        nextId++;
        orbiter.setOrbiterId(nextId);
        all.add(orbiter);
        writeAll(all);
        return orbiter;
    }

    public boolean update(Orbiter orbiter) throws DataAccessException {
        List<Orbiter> all = findAll();
        for(int i = 0; i < all.size(); i++){
            if(all.get(i).getOrbiterId() == orbiter.getOrbiterId()){
                all.set(i, orbiter);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBId(int orbiterId){
        return false;
    }

    private void writeAll(List<Orbiter> orbiters) throws DataAccessException{
        try(PrintWriter writer = new PrintWriter(filepath)){
            writer.println("orbiterId,name,type,sponsor");
            for(Orbiter o : orbiters){
                writer.println(serialize(o));
            }
        }catch (IOException e){
            throw new DataAccessException(e.getMessage(),e);
        }
    }

    private String serialize(Orbiter orbiter){
        return String.format("%s,%s,%s,%s",
                orbiter.getOrbiterId(),
                orbiter.getName(),
                orbiter.getType(),
                orbiter.getSponsor());
    }

}
