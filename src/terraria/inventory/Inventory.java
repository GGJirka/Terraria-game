/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import terraria.Terraria;
import terraria.blocks.ABlocks;
import terraria.id.ID;
import terraria.player.APlayer;

/**
 *
 * @author jirkazbor
 */
public class Inventory {

    private LinkedList<AItem> items = new LinkedList<>();
    private LinkedList<ANewItem> newItems = new LinkedList<>();
    private LinkedList<ANewItem> dropBlocks = new LinkedList<>();
    private ArrayList<ID> IDs = new ArrayList<>();
    private int index = 0;
    ANewItem[][] inventory;
    int pX = 0, pY = 0, bounds = 0;
    private Terraria terraria;
    public Inventory(Terraria terraria){
        this.terraria = terraria;
    }

    public void render(Graphics g){
        inventory = new NewItem[6][1];
        try {
            sort(g);
            for(ANewItem dropBlock : dropBlocks){
                dropBlock.render(g);
            }
        } catch (Exception ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        drawRectChoose(bounds,g);
    }

    public void sort(Graphics g)throws Exception{
        Color color = null;
            for(APlayer p : terraria.getHandler().getPlayer()){
                pX = p.x;
                pY = p.y;
                for(int i=0;i<newItems.size();i++){
                    ANewItem newItem = newItems.get(i);
                    g.setColor(Color.BLACK);
                    g.drawString("" + newItem.getCount(), p.x-140+i*90, p.y+280);
                    if(newItem.getCount()==0){
                        newItems.remove(newItems.get(i));
                        IDs.remove(IDs.get(i));
                        if(bounds != newItems.size()){
                            terraria.getInput().setIdTo(terraria.getInventory().getNewItems().get(bounds).getId());
                        }else{
                            terraria.getInput().setIdTo(terraria.getInventory().getNewItems().get(bounds-1).getId());
                            bounds = bounds-1;
                        }
                    }
                    if (newItem.getId() == ID.Blocks) {
                        g.setColor(new Color(156, 93, 50));
                    }
                    if (newItem.getId() == ID.Kmen){
                        g.setColor(new Color(107, 50, 18));
                    }
                    if(newItem.getId() == ID.Lazur){
                        g.setColor(Color.PINK);
                    }
                    if(newItem.getId() == ID.Trees){
                        g.setColor(Color.GREEN);
                    }
                    if(newItem.getId() == ID.Diamond){
                        g.setColor(new Color(0,196,255));
                    }
                    g.fillRect(p.x-200+i*90, p.y+300, 50, 50);
                    g.setColor(Color.BLACK);
                    g.drawRect(p.x-200+i*90, p.y+300, 50, 50);
                    
                }
            }
    }
    public void drawRectChoose(int bounds,Graphics g){
        if(newItems.size() != 0){
            for(int i=0;i<5;i++){
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(pX-200+bounds*90-i/2,pY+300-i/2,50+i,50+i);
            }
        }
    }
    public void setBounds(int bounds){
        this.bounds = bounds;
    }
    public void addId(ID id){
        this.IDs.add(id);
    }
    public void addItem(AItem item){
        this.items.add(item);
    }
    public ANewItem chooseNewItem(int x, int y){
        return inventory[x][y];
    }
    public LinkedList<AItem> getList() {
        return items;
    }

    public ArrayList<ID> getIDs() {
        return this.IDs;
    }
  
    public LinkedList<ANewItem> getNewItems() {
        return this.newItems;
    }
    public LinkedList<ANewItem> getDropBlocks() {
        return this.dropBlocks;
    }
    public void addDropBlock(ANewItem dropBlock){
        this.dropBlocks.add(dropBlock);
    }
}
