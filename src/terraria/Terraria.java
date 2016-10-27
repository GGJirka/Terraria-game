    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria;

import terraria.inventory.Inventory;
import terraria.inventory.Item;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import terraria.blocks.Map;
import terraria.enemies.Zombie;
import terraria.frame.Frame;
import terraria.id.ID;
import terraria.input.Input;
import terraria.player.APlayer;
import terraria.player.Player;
import terraria.player.playerCamera;

/**
 *
 * @author jirkazbor
 */
public class Terraria extends Canvas implements Runnable {

    private Thread thread;
    private boolean isRunning;
    private Handler handler;
    private Input input;
    private Map map;
    private MouseInput mi;
    private playerCamera camera;
    private Color blockColor;
    private Inventory inventory;
    public Image background;
    public EscMenu escMenu;
    public double alpha;
    public double velocityAlpha = 0.01;
    public double time;
    public boolean menu=false;
    public boolean normal,leftWorld;
    public Color startColor = new Color(0, 0, 0, 110);
    public Color optionsColor = new Color(0, 0, 0, 110);
    public Color exitColor = new Color(0, 0, 0, 110);
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width,
            height = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Frame frame = new Frame(new Terraria());
    }

    public Terraria(){
        escMenu = new EscMenu(this);
        handler = new Handler();
        inventory = new Inventory(this);
        mi = new MouseInput(this, inventory);
        map = new Map(this);
        camera = new playerCamera();
        input = new Input(this);
        handler.addPlayer(new Player(200 + ((int) Math.random()) * 600, 350, ID.Player, input, "Jimmy"));
        try {
            spawnZombie();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.setProperty("sun.java2d.opengl", "True");
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        requestFocus();
    }

    public void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (isRunning) {
            render();
            tick();
        }
    }

    private void tick() {
        for (int i = 0; i < handler.getPlayer().size(); i++) {
            APlayer player = handler.getPlayer().get(i);
            camera.camera(player);
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
            Graphics g = bs.getDrawGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1720, 1020);

        if (!mi.startClick){
            background = getImage("terraria.png");
            g.drawImage(background, 0, 0, null);
            drawMenu(g, width / 4, height / 4, 230, 30, "START", startColor);
            drawMenu(g, width / 4, height / 4 + 60, 230, 30, "OPTIONS", optionsColor);
            drawMenu(g, width / 4, height / 4 + 120, 230, 30, "EXIT", exitColor);
        }else{
            for (int i = 0; i < handler.getPlayer().size(); i++) {
                APlayer p = handler.getPlayer().get(i);
                if(p.x >=640 && p.y<=900){
                    normal = true;
                    leftWorld = false;
                    g.translate(camera.getX(), camera.getY());
                }else if(p.x <=640 && p.y<=860){
                    leftWorld = true;
                    normal = false;
                    g.translate(0, camera.getY());
                }
            }
            paintBackground(g);
            handler.actions(g);
            inventory.render(g);
            if(menu){
                escMenu.render(g);
            }
            night_day(g);
        }
        g.dispose();
        bs.show();
    }
    public void paintBackground(Graphics g){
        g.setColor(new Color(151,231,255));
        g.fillRect(0,-1000,4000,1600);
        g.setColor(new Color(191, 172, 130));
        g.fillRect(0, 574, 3000, 1000);
    }
    public void night_day(Graphics g){
        g.setColor(new Color(0,0,0, (int) alpha));
        g.fillRect(0, -1000, 4000, 2800);
        alpha+=velocityAlpha;
            if(alpha >=225 || alpha<=0){
                velocityAlpha*=-1;
            }
    }

    public void drawMenu(Graphics g, int x, int y, int width, int height, String text, Color color) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.setColor(color);
        g.fillRect(x + 1, y + 1, width - 1, height - 1);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString(text, x + width / 2 - text.length()*4, y + height / 2 + 5);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString(text, x + width / 2 - text.length()*4, y + height / 2 + 5);
    }
    public Image getImage(String path) {
        Image image = null;
        URL imageURL = Terraria.class.getResource(path);
        image = Toolkit.getDefaultToolkit().getImage(imageURL);
        return image;
    }

    public final Rectangle recStart() {
        return new Rectangle(width / 4, height / 4, 230, 30);
    }

    public final Rectangle recOptions() {
        return new Rectangle(width / 4, height / 4 + 60, 230, 30);
    }

    public final Rectangle recExit() {
        return new Rectangle(width / 4, height / 4 + 120, 230, 30);
    }

    public void spawnZombie() {
        //handler.addZombie(new Zombie(200, 100, this));
    }

    public Handler getHandler() {
        return handler;
    }

    public Map getMap() {
        return map;
    }

    public playerCamera getCamera() {
        return camera;
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    public MouseInput getInput() {
        return mi;
    }

    public Color getColor() {
        return blockColor;
    }

    public void setColor(Color blockColor) {
        this.blockColor = blockColor;
    }
}
