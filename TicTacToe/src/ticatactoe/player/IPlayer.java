/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticatactoe.player;

/**
 *
 * @author jirkazbor
 */
public abstract class IPlayer {
   private int x,y;
   private char character;
   
   public IPlayer(int x,int y,char character){
       this.x = x;
       this.y = y;
       this.character = character;
   }
   public abstract void render(java.awt.Graphics g);
   
   public int getX(){
       return x;
   }
   public int getY(){
       return y;
   }
   public char getCharacter(){
       return character;
   }
   public void setCharacter(char character){
        this.character = character;
    }
}
