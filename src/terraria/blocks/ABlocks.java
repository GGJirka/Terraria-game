/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.blocks;

import java.awt.Color;
import java.awt.Rectangle;
import terraria.Terraria;
import terraria.id.ID;

/**
 *
 * @author jirkazbor
 */
public abstract class ABlocks{
    public int x,y;
    public ID id;
    public Color texture;
    public java.awt.Color color;
    private Terraria terraria;
    public boolean grass = false;
    public ABlocks(int x, int y, ID id, Color texture,Terraria terraria) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.texture = texture;
        this.terraria = terraria;
    }
    public abstract void render(java.awt.Graphics g);
        
    public abstract Rectangle getBlock();
    
    public abstract Rectangle getTop();
    
    public abstract Rectangle getBot();
    
    public abstract Rectangle getLeft();
    
    public abstract Rectangle getRight();
    
    public abstract Rectangle botRect();
    
    public abstract java.awt.Color getColor();
    
    public abstract void setColor(java.awt.Color color);
}
