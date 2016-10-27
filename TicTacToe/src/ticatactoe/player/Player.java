/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticatactoe.player;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jirkazbor
 */
public class Player extends IPlayer{

    public Player(int x, int y,char character){
        super(x, y,character);
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(getX(), getY(), 111, 111);
        if(getCharacter() == 'O'){
            g.setColor(Color.GREEN);
            g.drawOval(getX()+5, getY()+5, 100, 100);
        }else if(getCharacter() == 'X'){
            g.setColor(Color.RED);
            g.fillOval(getX()+5, getY()+5, 100, 100);
        }
    }    
}
