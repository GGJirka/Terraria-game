/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Graphics;
import java.util.LinkedList;
import ticatactoe.player.IPlayer;

/**
 *
 * @author jirkazbor
 */
public class Handler {
    private LinkedList<IPlayer>gameBoard;
    
    public Handler(){
        gameBoard = new LinkedList<>();
    }
    public void render(Graphics g){
        for(IPlayer p:gameBoard){
            p.render(g);
        }
    }
    public void addArray(IPlayer player){
        gameBoard.add(player);
    }
}
