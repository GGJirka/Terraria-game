/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.image;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import terraria.blocks.Blocks;

/**
 *
 * @author jirkazbor
 */
public class LoadImage {
    
     public Image loadImage(String path){
        Image image = null;
        try{
            URL imageURL = Blocks.class.getResource(path);
            image = Toolkit.getDefaultToolkit().getImage(imageURL);
        }catch(Exception e){
            System.out.println("Couldn´t load block image");
        }
        return image;
    }
}
