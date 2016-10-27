/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.blocks;

import java.awt.Graphics;
import terraria.Terraria;

/**
 *
 * @author jirkazbor
 */
public abstract class ATrees {
    private int x,y;
    private Terraria terraria;
    private String texture;

    public ATrees(int x, int y, Terraria terraria, String texture) {
        this.x = x;
        this.y = y;
        this.terraria = terraria;
        this.texture = texture;
    }
    public abstract void render(Graphics g);
}
