/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria;

import terraria.inventory.AItem;
import terraria.inventory.ANewItem;
import terraria.inventory.Bag;
import terraria.inventory.Inventory;
import terraria.inventory.Item;
import terraria.inventory.NewItem;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import javax.swing.SwingUtilities;
import terraria.Terraria;
import terraria.blocks.ABlocks;
import terraria.id.ID;
import terraria.player.APlayer;

/**
 *
 * @author jirkazbor
 */
public class MouseInput implements MouseListener, MouseMotionListener {

    int mx, my;
    public Terraria terraria;
    public Inventory inventory;
    public ID actualPutId = ID.None;
    public ID actualId;
    public boolean start, options, exit, resume, settings, menu, game_info;
    public boolean startClick, optionsClick, exitClick, resumeClick, settingsClick, menuClick, game_infoClick;

    public MouseInput(Terraria terraria, Inventory inventory) {
        this.terraria = terraria;
        terraria.addMouseListener(this);
        terraria.addMouseMotionListener(this);
        this.inventory = inventory;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        ABlocks block = null;
        if (start == true) {
            startClick = true;
        }
        if (exit == true) {
            System.exit(0);
        }
        if (!terraria.menu) {
            if(terraria.normal){
                block= terraria.getMap().chooseBlock((mx - terraria.getCamera().getX()) / 32, (my - terraria.getCamera().getY()) / 32);
            }else if(terraria.leftWorld){
                block= terraria.getMap().chooseBlock((mx/ 32), (my - terraria.getCamera().getY()) / 32);
            }
            // on left mouse click 

            if (SwingUtilities.isLeftMouseButton(e)) {
                try {
                    if (block.id != ID.None) {
                        //inventory.addItem(new Item(actualId));
                        terraria.getHandler().removeBlock(block);
                        actualId = block.id;
                        //drop block
                        //if intersects drop block
                        inventory.addDropBlock(new NewItem(block.x+10, block.y-10, block.id, terraria));
                        block.id = ID.None;
                        if (block.y < 650) {
                            terraria.getMap().generateGrass();
                        }

                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                // on right mouse click
            } else if (SwingUtilities.isRightMouseButton(e)) {
                try {
                    for (ANewItem newItem : inventory.getNewItems()) {
                        if (actualPutId == newItem.getId()) {
                            if (newItem.getCount() > 0) {
                                if (block.id == ID.None) {
                                    block.id = newItem.getId();
                                    terraria.getHandler().addBlock(block);
                                    newItem.setCount(newItem.getCount() - 1);
                                }
                            }
                        }
                    }
                }catch (Exception ex) {
                    System.err.println(ex);
                }
            }
        }else{
            if (mx >= 558 && mx <= 558 + 280 && my >= 150 && my <= 200) {
                terraria.menu = false;
            } 
            if (mx >= 558 && mx <= 558 + 280 && my >= 150 + 100 && my <= 200 + 100) {
                settingsClick = true;
            } 
            if (mx >= 558 && mx <= 558 + 280 && my >= 150 + 200 && my <= 200 + 200) {
                startClick = false;
            }
            if (mx >= 558 && mx <= 558 + 280 && my >= 150 + 300 && my <= 200 + 300) {
                game_infoClick = true;
            }
        }
    }

    public void setIdTo(ID id) {
        actualPutId = id;
    }

    @Override
    public void mousePressed(MouseEvent e){
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

    public int getMx() {
        return mx;
    }

    public int getMy() {
        return my;
    }

    public ID getActaulId() {
        return this.actualId;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        if (!startClick) {
            if (recMouse().intersects(terraria.recStart())) {
                terraria.startColor = new Color(0, 0, 0, 50);
                start = true;
            } else {
                terraria.startColor = new Color(0, 0, 0, 110);
                start = false;
            }
            if (recMouse().intersects(terraria.recOptions())) {
                terraria.optionsColor = new Color(0, 0, 0, 50);
                options = true;
            } else {
                terraria.optionsColor = new Color(0, 0, 0, 110);
                options = false;
            }
            if (recMouse().intersects(terraria.recExit())) {
                terraria.exitColor = new Color(0, 0, 0, 50);
                exit = true;
            } else {
                terraria.exitColor = new Color(0, 0, 0, 110);
                exit = false;
            }
        }
        if (mx >= 558 && mx <= 558 + 280 && my >= 150 && my <= 200) {
            terraria.escMenu.resumeColor = new Color(0, 0, 0, 50);
            resume = true;
        } else {
            terraria.escMenu.resumeColor = new Color(0, 0, 0, 110);
            resume = false;
        }
        if (mx >= 558 && mx <= 558 + 280 && my >= 150 + 100 && my <= 200 + 100) {
            terraria.escMenu.settingsColor = new Color(0, 0, 0, 50);
            options = true;
        } else {
            terraria.escMenu.settingsColor = new Color(0, 0, 0, 110);
            options = false;
        }
        if (mx >= 558 && mx <= 558 + 280 && my >= 150 + 200 && my <= 200 + 200) {
            terraria.escMenu.menuColor = new Color(0, 0, 0, 50);
            menu = true;
        } else {
            terraria.escMenu.menuColor = new Color(0, 0, 0, 110);
            menu = false;
        }
        if (mx >= 558 && mx <= 558 + 280 && my >= 150 + 300 && my <= 200 + 300) {
            terraria.escMenu.infoColor = new Color(0, 0, 0, 50);
            game_info = true;
        } else {
            terraria.escMenu.infoColor = new Color(0, 0, 0, 110);
            game_info = false;
        }
    }

    public Rectangle recMouse() {
        return new Rectangle(mx, my, 5, 5);
    }
}
