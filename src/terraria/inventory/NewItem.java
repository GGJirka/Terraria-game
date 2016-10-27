/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.inventory;

import java.awt.Color;
import java.awt.Graphics;
import terraria.Terraria;
import terraria.blocks.ABlocks;
import terraria.id.ID;

/**
 *
 * @author jirkazbor
 */
public class NewItem extends ANewItem{
public ID id;
public int count;
public Color color;
private int x,y;

    public NewItem(ID id, int count){
        super(id, count);
        this.id = id;
        this.count = count;
    }
    public NewItem(int x,int y,ID id,Terraria terraria){
        super(x,y,id,terraria);
        this.x = x;
        this.y =y;
        this.id = id;
    }
    @Override
    public void render(Graphics g){
        if (id == ID.Blocks) {
            color = new Color(156, 93, 50);
        }
        if (id == ID.Kmen) {
            color = new Color(107, 50, 18); 
        }
        if (id == ID.Trees) {
            color = Color.GREEN;
        }
        if (id == ID.Lazur) {
            color = Color.MAGENTA;
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
        g.setColor(color);
        g.fillRect(x,y,16,16);
    }
    @Override
    public java.awt.Rectangle getDropBlock(){
        return new java.awt.Rectangle(x,y,16,16);
    }
}