/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import terraria.player.APlayer;

/**
 *
 * @author jirkazbor
 */
public class EscMenu{
    Terraria terraria;
    public Color resumeColor = new Color(0, 0, 0, 110);
    public Color settingsColor = new Color(0, 0, 0, 110);
    public Color menuColor = new Color(0, 0, 0, 110);
    public Color infoColor = new Color(0, 0, 0, 110);
    public int px,py;
    public EscMenu(Terraria terraria){
        this.terraria = terraria;
    }
    public void render(Graphics g){
        for(APlayer p : terraria.getHandler().getPlayer()){
            px = p.x;
            py = p.y;
            terraria.drawMenu(g, p.x-82, p.y-150, 280, 50, "RESUME", resumeColor);
            terraria.drawMenu(g, p.x-82, p.y+100-150, 280, 50, "SETTING", settingsColor);
            terraria.drawMenu(g, p.x-82, p.y+200-150, 280, 50, "MENU", menuColor);
            terraria.drawMenu(g, p.x-82, p.y+300-150, 280, 50, "GAME INFO", infoColor);
        }        
    }
    public  Rectangle recResume(){       
        System.out.println(px);
        return new Rectangle(px-82, py-150, 280, 50);
    }
    
    public  Rectangle recSettings() {
        return new Rectangle(px-82, py-150+100, 280, 50);
    }
    
    public  Rectangle recMenu(){
        return new Rectangle(px-82,py-150+200,280,50);
    }
    public  Rectangle recInfo() {
        return new Rectangle(px-82, py-150+300, 280, 50);
    }
}
