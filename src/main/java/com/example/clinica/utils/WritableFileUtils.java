package com.example.clinica.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WritableFileUtils {

    public static void writeSectieID(Long ID){
        try {

            FileWriter fileWriter = new FileWriter("C:\\Facultate\\MAP\\Clinica\\src\\main\\java\\com\\example\\clinica\\sectie_selectata.txt");
            fileWriter.write(ID.toString());
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Long readSectieID(){

        Long result = null;

        try {
            File file = new File("C:\\Facultate\\MAP\\Clinica\\src\\main\\java\\com\\example\\clinica\\sectie_selectata.txt");

            Scanner scanner = new Scanner(file);

            String data = scanner.nextLine();
            result = Long.parseLong(data);
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return result;

    }

}
