/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.inventory;
import java.awt.Color;
import java.awt.Graphics;
import terraria.Handler;
import terraria.id.ID;
import terraria.player.APlayer;

/**
 *
 * @author jirkazbor
 */
public class Bag extends ABag{
    Handler handler;
    public Bag(int x, int y,ID id){
        super(x, y,id);
        handler = new Handler();
    }
    @Override
    public void render(Graphics g){    
       for(APlayer p:handler.getPlayer()){
            g.setColor(Color.RED);
            g.fillRect(x*100,200+p.getY(),50,50);
       }
    }
}
