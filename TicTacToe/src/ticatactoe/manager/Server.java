/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticatactoe.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import ticatactoe.player.GameBoard;
import tictactoe.TicTacToe;

/**
 *
 * @author jirkazbor
 */
public class Server {

    public ServerSocket serverSocket;
    public Socket socket;
    public GameBoard game;
    public TicTacToe toe;
    public BufferedWriter bw;
    public BufferedReader br;

    public Server(TicTacToe toe) {
        this.toe = toe;
        game = new GameBoard(toe.getHandler(), 'O');
        toe.addMouseListener(game);
        this.startServer();
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("waiting for another player...");
            socket = serverSocket.accept();
            System.out.println("someone has connected");
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (bw != null) {
                            try {
                                if(game.getState()==GameBoard.STATE.INGAME){
                                    bw.write(game.getSpace());
                                    bw.flush();
                                }
                            } catch (Exception e) {
                                //System.out.println(e);
                            }
                        }
                    }
                }
            });

            thread.start();
            while (true) {
                try {
                    if (br.ready()) {
                        int finalSpace = br.read();
                        game.getMap(finalSpace).setCharacter('X');
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
