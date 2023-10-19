package com.example.learn.venus.ui;

import com.example.learn.venus.domain.OrbiterResult;
import com.example.learn.venus.models.Orbiter;
import com.example.learn.venus.models.OrbiterType;

import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner console = new Scanner(System.in);
    public MenuOptions displayMenuAndSelect(){
        MenuOptions[] values = MenuOptions.values();
        printHeader("Main Menu");
        for(int i = 0; i < values.length; i++){
            System.out.printf("%s. %s%n",i,values[i].getTitle());
        }
        int idx = readInt("Select [0-4]: ",0,4);
        return values[idx];
    }
    public void displayResult(OrbiterResult res){
        if(res.isSuccess()){
            printHeader("Success!");
        }else{
            printHeader("Err:");
            for(String err: res.getMessages()){
                System.out.println(err);
            }
        }

    }
    public Orbiter update(List<Orbiter> orbiters){
        displayOrbiters(orbiters);
        if(orbiters.size() == 0){
            return null;
        }
        int orbiterId = readInt("Orbiter Id: ");
        for(Orbiter o : orbiters){
            if(o.getOrbiterId() == orbiterId){
                return update(o);
            }
        }
        System.out.println("Orbiter Id" + orbiterId + "not found");
        return null;
    }

    private Orbiter update(Orbiter orbiter){
        String name = readString("Name (" + orbiter.getName() + "):");
        if(name.trim().length() > 0){
            orbiter.setName(name);
        }
        String sponsor = readString("Sponsor (" + orbiter.getSponsor() + "):");
        if(sponsor.trim().length() > 0){
            orbiter.setName(sponsor);
        }
        return orbiter;
    }

    public Orbiter makeOrbiter(){
        Orbiter orbiter = new Orbiter();
        orbiter.setName(readRequiredString("Name: "));
        orbiter.setType(readOrbiterType());
        orbiter.setSponsor(readString("Sponsor: "));
        return orbiter;
    }

    public void printHeader(String message){
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public OrbiterType readOrbiterType(){
        System.out.println("Types: ");
        OrbiterType[] values = OrbiterType.values();
        for(int i = 0; i < values.length; i++){
            System.out.printf("%s. %s%n",i,values[i]);
        }
        int idx = readInt("Select [0-4]: ",0,4);
        return values[idx];
    }

    public void displayOrbiters(List<Orbiter> orbiter){
        printHeader("Orbiters:");
        if(orbiter.size() == 0){
            System.out.println("No orbiters found");
        }else{
            for(Orbiter o : orbiter){
                System.out.printf(
                        "%s - %s - %s - %s%n",
                        o.getOrbiterId(),
                        o.getName(),
                        o.getType(),
                        o.getSponsor());
            }
        }
    }

    private String readString(String prompt){
        System.out.print(prompt);
        return console.nextLine();
    }
    private String readRequiredString(String prompt){
        String res = null;
        do{
            res = readString(prompt).trim();
            if(res.length() == 0){
                System.out.println("Value is required");
            }
        }while(res.length() == 0);
        return res;
    }

    private int readInt(String prompt){
        int res = 0;
        boolean isValid = false;
        do{
            String value = readRequiredString(prompt);
            try{
            res = Integer.parseInt(value);
            isValid = true;
            }catch(NumberFormatException ex){
                System.out.println("Value must be a number");
            }
        }while(!isValid);
        return res;
    }

    private int readInt(String prompt, int min, int max){
        int res = 0;
        do{
            res = readInt(prompt);
            if(res < min || res > max){
                System.out.printf("Value must be between %s and %max.%n",min, max );
            }
        }while(res < min || res > max);
        return res;
    }

}
