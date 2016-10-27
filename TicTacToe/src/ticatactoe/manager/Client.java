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
import java.net.Socket;
import ticatactoe.player.GameBoard;
import tictactoe.*;

/**
 *
 * @author jirkazbor
 */
public class Client {

    public Socket clientSocket;
    public TicTacToe toe;
    public GameBoard game;

    public Client(TicTacToe toe) {
        this.toe = toe;
        game = new GameBoard(toe.getHandler(), 'X');
        toe.addMouseListener(game);
        this.startClient();
    }

    public void startClient() {
        try {
            clientSocket = new Socket("192.168.213.213", 8080);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread clientThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (bw != null) {
                            try {
                                bw.write(game.getSpace());
                                bw.flush();
                            } catch (Exception e) {
                                //System.out.println(e);
                            }
                        }
                    }
                }
            });
            clientThread.start();
            while (true) {
                if (br.ready()) {
                    int finalSpace = br.read();
                    game.getMap(finalSpace).setCharacter('O');
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
