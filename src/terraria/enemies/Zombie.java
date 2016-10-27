/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import terraria.Terraria;
import terraria.blocks.ABlocks;
import terraria.player.APlayer;

/**
 *
 * @author jirkazbor
 */
public class Zombie extends AZombie {

    private int x, y;
    private Terraria terraria;
    private int velX, velY;
    private boolean right, left, righttop, lefttop, playerFocus, jump;

    public Zombie(int x, int y, Terraria terraria) {
        super(x, y, terraria);
        this.x = x;
        this.y = y;
        this.terraria = terraria;
    }

    @Override
    public void tick() {
        // jumping, moving, attacking

        if (left == true) {
            if (lefttop == true) {
                velY = 10;
                if (velY < -10) {
                    velY = 0;
                }
            }
            velX = -1;
        } else if (right = true) {
            velX = 1;
            if (righttop == true) {
                velY = 10;
                if (velY < -10) {
                    velY = 0;
                }
            }
        }
        if (isFalling() == true) {
            velY++;
        } else {
            velY = 0;

        }
        y += velY;
        x += velX;
        collision();
    }

    public void collision() {
        for (APlayer p : terraria.getHandler().getPlayer()) {
            if (p.getPlayer().intersects(getZombie())) {
                playerFocus = true;
                if (p.getX() <= x) {
                    left = true;
                    right = false;
                } else {
                    right = true;
                    left = false;
                }
                if (p.getY() < y) {
//                    if(jump == true){
//                        jump();
//                    }
                }
            }
        }
        for (ABlocks blocks : terraria.getHandler().getBlocks()) {
            if (getBot().intersects(blocks.getTop())) {
                FallingSet(false);
                velY = 0;
                y = y + velY;
                y--;
                jump = true;
            } else {
                JumpingSet(false);
                FallingSet(true);
            }
            if(getBot().intersects(blocks.getBot())){
                y++;
                velY = 0;
                y = y + velY;
                jump = false;
            }
            if (playerFocus == false) {
                if (getRight().intersects(blocks.getLeft())) {
                    left = true;
                    right = false;
                }
                if (getLeft().intersects(blocks.getRight())) {
                    right = true;
                    left = false;
                }
            } else {
                if (getRight().intersects(blocks.getLeft())) {
                    velX = 0;
                    x--;
                    if(jump == true){
                        jump();
                    }
                }
                if (getLeft().intersects(blocks.getRight())) {
                    velX = 0;
                    x++;
                    if(jump == true){
                        jump();
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        //rendering object - zombie
        g.setColor(Color.BLACK);
        //g.drawRect(x - 200, y - 200, 400, 400);
        //g.fillRect(x+33,y-10,22,10);
        //g.fillRect(x - 22, y + 30, 22, 10);
        //g.fillRect(x+33,y+30,22,10);
        g.setColor(Color.RED);
        g.drawRect(x + 8, y + 4, 22, 8);
        g.drawRect(x + 8, y + 4, 8, 50);
        g.drawRect(x + 8, y + 46, 22, 8);
        g.drawRect(x + 22, y + 4, 8, 50);

    }

    public void jump() {
        velY -= 10;
        y += velY;
        if (velY < -10) {
            velY = 0;
            FallingSet(true);
        }
        jump = false;
    }

    public Rectangle getZombie() {
        return new Rectangle(x - 200, y - 200, 400, 400);
    }

    public Rectangle getRightTop() {
        return new Rectangle(x + 33, y - 10, 22, 10);
    }

    public Rectangle getLeftTop() {
        return new Rectangle(x - 22, y - 10, 22, 10);
    }

    public Rectangle getRightBot() {
        return new Rectangle(x + 33, y + 30, 22, 10);
    }

    public Rectangle getLeftBot() {
        return new Rectangle(x - 22, y + 30, 22, 10);
    }

    public Rectangle getTop() {
        return new Rectangle(x + 8, y, 22, 8);
    }

    public Rectangle getBot() {
        return new Rectangle(x + 8, y + 46, 22, 8);
    }

    public Rectangle getLeft() {
        return new Rectangle(x + 8, y, 8, 54);
    }

    public Rectangle getRight() {
        return new Rectangle(x + 22, y, 8, 54);
    }
}
