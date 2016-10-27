/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;
import terraria.Terraria;
import terraria.id.ID;

/**
 *
 * @author jirkazbor
 */
public class Blocks extends ABlocks {

    private Random r;
    private Image blockImage;
    private boolean chooseColor;
    private Color color;
    private Terraria terraria;
    private int sizeX, sizeY;
    
    public Blocks(int x, int y, ID id, Color color, Terraria terraria) {
        super(x, y, id, color, terraria);
        this.terraria = terraria;
        r = new Random();
    }
    @Override
    public void render(Graphics g){
        if (id == ID.Blocks) {
            color = new Color(156, 93, 50);
            sizeX = 32;
            sizeY = 32;
        }
        if (id == ID.Kmen) {
            color = new Color(107, 50, 18);
            sizeX = 32;
            sizeY = 32;
        }
        if (id == ID.Trees) {
            color = Color.GREEN;
            sizeX = 32;
            sizeY = 32;
        }
        if (id == ID.Lazur) {
            color = Color.MAGENTA;
            sizeX = 32;
            sizeY = 32;
        }     
        if(id == ID.Bush){
           color = new Color(0,255,102);
        }
        if(id == ID.Stone){
            color = Color.GRAY;
        }
        if(id == ID.Diamond){
            color = new Color(0,196,255);
        }
        if (grass) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y - 2, 32, 4);
        }
        g.setColor(color);
        g.fillRect(x, y, 32, 32);
    }


    public Image loadImage(String path) {
        Image image = null;
        try {
            URL imageURL = Blocks.class.getResource(path);
            image = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            System.out.println("Couldn´t load block image");
        }
        return image;
    }

    @Override
    public Rectangle getBlock() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public Rectangle getTop() {
        return new Rectangle(x, y, 32, 16);
    }

    @Override
    public Rectangle getBot() {
        return new Rectangle(x, y + 16, 32, 16);
    }

    @Override
    public Rectangle getLeft() {
        return new Rectangle(x, y, 16, 32);
    }

    @Override
    public Rectangle getRight() {
        return new Rectangle(x + 16, y, 16, 32);
    }

    public Rectangle botRect() {
        return new Rectangle(x, y - 3, 8, 2);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
