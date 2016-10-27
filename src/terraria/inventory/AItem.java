/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.inventory;

import java.awt.Graphics;
import terraria.id.ID;

/**
 *
 * @author jirkazbor
 */
public abstract class AItem{
    public ID id;
    public int count;
    public AItem(ID id){
        this.id = id;
        
    }
    public ID getId(){
        return id;
    }
    public int getCount(){
        return count;
    }
    public abstract void render(Graphics g);
}
