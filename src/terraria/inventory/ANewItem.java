/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.inventory;

import terraria.Terraria;
import terraria.id.ID;

/**
 *
 * @author jirkazbor
 */
public abstract class ANewItem {
    
    private ID id;
    private int count;
    private int x;
    private int y;
    public Terraria terraria;
    
    public ANewItem(ID id, int count) {
        this.id = id;
        this.count = count;
    }
    public ANewItem(int x,int y, ID id,Terraria terraria){
        this.x = x;
        this.y = y;
        this.id = id;
        this.terraria = terraria;
    }
    public abstract void render(java.awt.Graphics g);
    
    public abstract java.awt.Rectangle getDropBlock();
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
