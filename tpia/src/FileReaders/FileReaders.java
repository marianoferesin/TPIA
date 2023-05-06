package FileReaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaders {
    private static String MACOS_NAME = "Mac OS X";
    public static ArrayList<String> leerUbicaciones(){
        ArrayList<String> ret = new ArrayList<String>();
        String path = ".\\tpia\\src\\Files\\Ubicaciones.txt";
        try {
            if(System.getProperty("os.name").equals(MACOS_NAME)){
                path = "tpia/src/Files/Ubicaciones.txt";
            }
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ret.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return ret;
    }
    public static ArrayList<ArrayList<String>> leerAristas(){
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        String path = ".\\tpia\\src\\Files\\Aristas.txt";

        try {
            if(System.getProperty("os.name").equals(MACOS_NAME)){
                path = "tpia/src/Files/Aristas.txt";
            }
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitted = data.split("\\s+");
                ArrayList <String> next = new ArrayList<String>();
                next.add(splitted[0]);
                next.add(splitted[1]);
                ret.add(next);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return ret;
    }

    public static ArrayList<ArrayList<String>> leerInfoUbicaciones(){
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        String path = ".\\tpia\\src\\Files\\InfoUbicaciones.txt";
        try {
            if(System.getProperty("os.name").equals(MACOS_NAME)){
                path = "tpia/src/Files/InfoUbicaciones.txt";
            }
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitted = data.split("\\s+");
                ArrayList <String> next = new ArrayList<String>();
                next.add(splitted[0]);
                next.add(splitted[1]);
                next.add(splitted[2]);
                next.add(splitted[3]);
                ret.add(next);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return ret;
    }

}
