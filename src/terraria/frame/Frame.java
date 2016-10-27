/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.frame;

import javax.swing.*;
import terraria.Terraria;

/**
 *
 * @author jirkazbor
 */
public class Frame extends JFrame{
    Terraria terraria;
    public Frame(Terraria terraria){
        this.terraria = terraria;
        JFrame frame = new JFrame("Terraria 0.5");
        frame.setVisible(true);
        frame.setSize(1320,880);
        frame.add(terraria);
        terraria.start();
    }
}
