/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.enemies;

import java.awt.Graphics;
import terraria.Terraria;

/**
 *
 * @author jirkazbor
 */
public abstract class AZombie {
    private int x, y;
    private Terraria terraria;
    private boolean isFalling = false, isJumping = false;
    
    public AZombie(int x, int y, Terraria terraria) {
        this.x = x;
        this.y = y;
        this.terraria = terraria;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public boolean isFalling(){
        return isFalling;
    }
    public void FallingSet(boolean isFalling){
        this.isFalling = isFalling;
    }
    public boolean isJumping(){
        return isJumping;
    }
    public void JumpingSet(boolean isJumping){
        this.isJumping = isJumping;
    }
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

    public Terraria getTerraria() {
        return terraria;
    }

    public void setTerraria(Terraria terraria) {
        this.terraria = terraria;
    }
    
}
