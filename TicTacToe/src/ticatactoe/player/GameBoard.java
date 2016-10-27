/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticatactoe.player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ticatactoe.player.GameBoard.STATE;
import tictactoe.Handler;

/**
 *
 * @author jirkazbor
 */
public class GameBoard implements MouseListener{
    IPlayer[][] map;
    IPlayer[] space;
    Handler handler;
    char character;
    boolean clicked = false;
    int mx =0,my =0;
    int spaceFinal = 9;
    public STATE state = STATE.WAIT;
    public enum STATE{
        WAIT,
        INGAMEX,
        INGAMEO,
        INGAME,
        DISCONNECT,
        WIN,
    }
    public void setState(STATE state){
        this.state = state;
    }
    public STATE getState(){
        return state;
    }
    public GameBoard(Handler handler,char character){
        this.handler = handler;
        this.character = character;
        map = new Player[3][4];
        space = new Player[10];
        for(int x=0;x<map.length;x++){
            for(int y=0;y<map[0].length;y++){
                map[x][y] = new Player(x*111,y*111,'n');
                handler.addArray(map[x][y]);
            }
        }
        space[0]=map[0][0];
        space[1]=map[1][0];
        space[2]=map[2][0];
        space[3]=map[0][1];
        space[4]=map[1][1];
        space[5]=map[2][1];
        space[6]=map[0][2];
        space[7]=map[1][2];
        space[8]=map[2][2];
        space[9]=map[0][3];
    }

    @Override
    public void mouseClicked(MouseEvent e){
        mx = e.getX();
        my = e.getY();
        setState(STATE.INGAME);
        if(state!=STATE.WAIT){
            spaceFinal = mx/111 + my/111*3; 
            getMap(spaceFinal).setCharacter(character);         
        }
        System.out.println(getState());
    }
    public int getSpace(){
        return spaceFinal;
    }
    public IPlayer getMap(int x, int y){
        return map[x][y];
    }
    public IPlayer getMap(int z){
        return space[z];
    }
    public void setClick(boolean clicked){
        this.clicked = clicked;
    }
    public boolean isClicked(){
        return clicked;
    }
    public int getMx(){
        return mx;
    }
    public int getMy(){
        return my;
    }
    public char getCharacter(){
        return character;
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
