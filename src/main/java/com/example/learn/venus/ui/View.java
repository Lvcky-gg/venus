package com.example.learn.venus.ui;

import java.util.Scanner;

public class View {
    private final Scanner console = new Scanner(System.in);
    public MenuOptions displayMenuAndSelect(){
        MenuOptions[] values = MenuOptions.values();
        printHeader("Main Menu");
        for(int i = 0; i < values.length; i++){
            System.out.printf("%s. %s%n",i,values[i].getTitle());
        }
        return MenuOptions.EXIT;
    }

    public void printHeader(String message){
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
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
        }while(res.length() > 0);
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

}
