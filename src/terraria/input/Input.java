/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import terraria.Terraria;
import terraria.player.APlayer;

/**
 *
 * @author jirkazbor
 */
public class Input implements KeyListener {

    private Terraria terraria;
    public boolean up;
    public boolean left;
    public boolean right;
    public boolean down;
    public boolean intersectsLeft;
    public boolean intersectsRight;
    public boolean falling;
    public int velX;
    public int velY;
    public boolean creative = false;
    public int count = 2,count2 = 2;
    public Input(Terraria terraria) {
        this.terraria = terraria;
        terraria.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        for (APlayer p : terraria.getHandler().getPlayer()) {
            switch (keyCode){
                case KeyEvent.VK_W:
                    if (up == false){
                        up = true;
                    }
                    break;
                case KeyEvent.VK_S:
                    if(down == false){
                        down = true;
                    }
                    break;
                case KeyEvent.VK_A:
                    if (left == false) {
                        p.setVelX(-10);
                        left = true;
                    }
                    break;
                case KeyEvent.VK_D:
                    if (right == false){
                        p.setVelX(10);
                        right = true;
                    }
                    break;
                case KeyEvent.VK_O:
                    if(count%2==0){
                        p.developerMode = true;
                    }else{
                        p.developerMode = false;
                    }
                    count++;
                    break;
                case KeyEvent.VK_1:
                    terraria.getInput().setIdTo(terraria.getInventory().getNewItems().get(0).getId());
                    terraria.getInventory().setBounds(0);
                    break;
                case KeyEvent.VK_2:
                    terraria.getInput().setIdTo(terraria.getInventory().getNewItems().get(1).getId());
                    terraria.getInventory().setBounds(1);
                    break;
                case KeyEvent.VK_3:
                    terraria.getInput().setIdTo(terraria.getInventory().getNewItems().get(2).getId());
                    terraria.getInventory().setBounds(2);
                    break;
                case KeyEvent.VK_4:
                    terraria.getInput().setIdTo(terraria.getInventory().getNewItems().get(3).getId());
                    terraria.getInventory().setBounds(3);
                    break;
                case KeyEvent.VK_ESCAPE:
                    if(terraria.getInput().startClick){
                        if(count2%2 == 0){
                            terraria.menu = true;
                        }else{
                            terraria.menu = false;
                        }
                        count2++;
                    }
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Terraria main() {
        return terraria;
    }
}
