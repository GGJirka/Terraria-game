/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria;

import terraria.inventory.ABag;
import java.util.LinkedList;
import terraria.blocks.ABlocks;
import terraria.enemies.AZombie;
import terraria.player.APlayer;

/**
 *
 * @author jirkazbor
 */
public class Handler {
    private LinkedList<APlayer> player = new LinkedList<>();
    private LinkedList<ABlocks>blocks = new LinkedList<>();
    private LinkedList<ABag> bag = new LinkedList<>();
    private LinkedList<AZombie> zombie = new LinkedList<>();
    
    public void actions(java.awt.Graphics g){
        for(int i = 0;i<player.size();i++){
            APlayer p = player.get(i);
            p.render(g);
            p.tick();
        }
        for(int i = 0;i<blocks.size();i++){
            ABlocks block = blocks.get(i);
            block.render(g);
        }
        for(ABag b: bag){
            b.render(g);
        }
        for(AZombie z: zombie){
            z.render(g);
            z.tick();
        }
        
    }
    public void addZombie(AZombie zombie){
        this.zombie.add(zombie);
    }
    public void addBag(ABag bag){
        this.bag.add(bag);
    }
    public LinkedList<ABag> getBag(){
        return bag;
    }
    public void addPlayer(APlayer player){
        this.player.add(player);
    }
    public void addBlock(ABlocks blocks){
        this.blocks.add(blocks);
    }
    public LinkedList<ABlocks> getBlocks(){
        return blocks;
    }
    public LinkedList<APlayer>getPlayer(){
        return player;
    }
    public void removeBlock(ABlocks blocks){
        this.blocks.remove(blocks);
    }
}
