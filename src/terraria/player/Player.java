/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.player;

import terraria.inventory.ANewItem;
import terraria.inventory.Inventory;
import terraria.inventory.NewItem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import terraria.blocks.ABlocks;
import terraria.id.ID;
import terraria.input.Input;

/**
 *
 * @author jirkazbor
 */
public class Player extends APlayer{
    private int HP = 100;
    private boolean isFalling = true;
    private int velocityUp, velocityDown;
    private final Input input;
    private Image player;
    private Inventory inventory;

    public Player(int x, int y, ID id, Input input, String username) {
        super(x, y, id, input, username);
        this.input = input;
        inventory = input.main().getInventory();
    }

    @Override
    public void tick() {
        if (developerMode == false) {
            if (input.up == true) {
                velocityUp -= 15;
                y += velocityUp;
                if (velocityUp >= -15) {
                    velocityUp = 0;
                    isFalling = true;
                }
            }
            if (input.left == true || input.right == true) {
                x += getVelX();
                input.left = false;
                input.right = false;
            }
//        }if(input.left == true){
//            x+=input.velX;
//            input.left = false;
//        }if(input.right == true){
//            x+=input.velX;
//            input.right = false;
            if (isFalling == true) {
                velocityDown++;
                y += velocityDown;

            }
            if (isFalling == false) {
                velocityDown = 0;
            }
            collision();
        } else {
            if (input.up) {
                y -= 20;
                input.up = false;
            }
            if (input.down) {
                y += 20;
                input.down = false;
            }
            if (input.left == true || input.right == true) {
                x += getVelX();
                input.left = false;
                input.right = false;
            }
        }
    }

    public void collision() {
        for (int i = 0; i < input.main().getHandler().getBlocks().size(); i++) {
            ABlocks blocks = input.main().getHandler().getBlocks().get(i);
            if (getBot().intersects(blocks.getTop())) {
                isFalling = true;
                input.up = false;
                velocityDown = 0;
                y = velocityDown + y;
                y -= 1;
            }
            if (getTop().intersects(blocks.getBot())) {
                input.up = false;
                isFalling = true;
                velocityUp = 0;
                y = velocityUp + y;
                y++;
            }
            if (getLeft().intersects(blocks.getRight())) {
                input.intersectsLeft = true;
                x += 8;
                setVelX(0);
            } else {
                input.intersectsLeft = false;
            }
            if (getRight().intersects(blocks.getLeft())) {
                input.intersectsRight = true;
                x -= 8;
                setVelX(0);
            }
            try {
                for (ANewItem dropBlock : input.main().getInventory().getDropBlocks()) {
                    if (getPlayer().intersects(dropBlock.getDropBlock())) {
                        if (inventory.getIDs().contains(dropBlock.getId()) == false) {
                            inventory.addId(dropBlock.getId());
                            inventory.getNewItems().add(new NewItem(dropBlock.getId(), 0));
                        }
                        for (ANewItem newItem : inventory.getNewItems()) {
                            if (dropBlock.getId() == newItem.getId()) {
                                newItem.setCount(newItem.getCount() + 1);
                            }
                        }
                        inventory.getDropBlocks().remove();
                    }

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public Image getImage(String path) {
        Image image = null;
        URL imageURL = Player.class.getResource(path);
        image = Toolkit.getDefaultToolkit().getImage(imageURL);
        return image;
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        player = getImage("player.gif");
//        g.drawRect(x+8,y+4,22,8);
//        g.drawRect(x+8,y+4,8,50);
//        g.drawRect(x+8,y+46,22,8);
//        g.drawRect(x+22,y+4,8,50);
        g.drawImage(player, x, y, null);
    }

    @Override
    public Rectangle getPlayer() {
        return new Rectangle(x, y+5, 40, 48);
    }

    @Override
    public Rectangle getTop() {
        return new Rectangle(x + 8, y, 22, 8);
    }

    @Override
    public Rectangle getBot() {
        return new Rectangle(x + 8, y + 46, 22, 8);
    }

    @Override
    public Rectangle getLeft() {
        return new Rectangle(x + 8, y, 8, 54);
    }

    @Override
    public Rectangle getRight() {
        return new Rectangle(x + 22, y, 8, 54);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    public int getHp(){
        return HP;
    }
    public void setHp(int HP){
        this.HP = HP;
    }
}
