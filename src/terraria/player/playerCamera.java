/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.player;

/**
 *
 * @author jirkazbor
 */
public class playerCamera {
    private int x;
    private int y;
    
    public void camera(APlayer player){
        setX(-player.getX()+640);
        setY(-player.getY()+300);
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
    
}
