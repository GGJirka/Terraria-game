/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.manager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author jirkazbor
 */
public class ManagerSaver {
    
    public static void save(Serializable data, String fileName){
        try{
           ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName))); 
           os.write((int) data);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static Object load(String fileName) throws IOException, ClassNotFoundException{
        ObjectInputStream is = null;
        try{
            is = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        }catch(Exception e){
            System.out.println(e);
        }
        return is.readObject();
    }
}
