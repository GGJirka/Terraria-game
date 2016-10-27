/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.frame;

import javax.swing.JFrame;
import tictactoe.TicTacToe;

/**
 *
 * @author jirkazbor
 */
public class Frame extends JFrame{
    private TicTacToe main;
    public Frame(TicTacToe main){
        this.main = main;
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(333,333);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(main);
        main.start();
    }
}
