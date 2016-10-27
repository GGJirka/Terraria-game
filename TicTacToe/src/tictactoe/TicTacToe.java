    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import ticatactoe.manager.Client;
import ticatactoe.manager.Server;
import ticatactoe.player.GameBoard;
import tictactoe.frame.Frame;

/**
 *
 * @author jirkazbor
 */
public class TicTacToe extends Canvas implements Runnable{

    /**
     * @param args the command line arguments
     */
    private Handler handler;
    private GameBoard board;
    private Thread mainThread;
    private boolean isRunning = false;
    public static void main(String[] args) {
         //Server server = new Server(new TicTacToe());
         Client client = new Client(new TicTacToe());
    }
    public TicTacToe(){
        handler = new Handler();
        Frame frame = new Frame(this);
    }
    public void start(){
        isRunning = true;
        mainThread = new Thread(this,"MAIN");
        mainThread.start();
    }
    @Override
    public void run(){
        while(isRunning == true){
            render();
        }
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        handler.render(g);
        g.dispose();
        bs.show();
    }
    public Handler getHandler(){
        return handler;
    }
}
