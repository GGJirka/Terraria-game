/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.player;

import java.awt.Rectangle;
import terraria.id.ID;
import terraria.input.Input;

/**
 *
 * @author jirkazbor
 */
public abstract class APlayer {
    public int x,y;
    public boolean developerMode;
    private ID id;
    private Input input;
    private String username;
    private int velX;
    public APlayer(int x, int y, ID id, Input input, String username) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.input = input;
        this.username = username;
    }
    
    public abstract void tick();
    
    public abstract void render(java.awt.Graphics g);
    
    public abstract Rectangle getPlayer();
    
    public abstract Rectangle getTop();
    
    public abstract Rectangle getBot();
    
    public abstract Rectangle getLeft();
    
    public abstract Rectangle getRight();
    
    public abstract int getX();
    
    public abstract int getY();
    
    public int getVelX(){
        return velX;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    
}
