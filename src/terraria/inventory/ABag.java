/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.inventory;

import java.awt.Graphics;
import java.util.LinkedList;
import terraria.id.ID;

/**
 *
 * @author jirkazbor
 */
public abstract class ABag{
    int x;
    int y;
    ID id;
    public ABag(int x, int y,ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void render(Graphics g);

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

   
    
    public ID getID(){
        return id;
    }
    public void setId(ID id){
        this.id = id;
    }
}
