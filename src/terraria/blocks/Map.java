/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terraria.blocks;

import java.awt.Color;
import java.util.Random;
import terraria.Terraria;
import terraria.id.ID;

/**
 *
 * @author jirkazbor
 */
public class Map {

    public int worldW = 70, worldH = 40;
    ABlocks[][] map = new Blocks[worldW][worldH];
    Terraria terraria;
    Random block;
    Random trees;
    Color color;
    private int scaleY = 1;
    private int topTerrain = 9;

    public Map(Terraria terraria) {
        this.terraria = terraria;
        block = new Random();
        trees = new Random();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (block.nextInt(100) <= 11 && block.nextInt(100) >= 10 && y > 15){
                    map[x][y] = new Blocks(x * 32, y * 32, ID.Lazur, color, terraria);             
                }
                if(block.nextInt(100) == 74 && y > 25){
                    map[x][y] = new Blocks(x * 32, y * 32, ID.Diamond, color, terraria);             
                }
                else {
                    map[x][y] = new Blocks(x * 32, y * 32, ID.Blocks, color, terraria);
                }
                terraria.getHandler().addBlock(map[x][y]);
                if (y <= 22) {
                    terraria.getHandler().removeBlock(map[x][y]);
                    map[x][y].id = ID.None;
                }
            }
        }
        System.out.println("Generating world ...");
        generateCaves();
    }

    public ABlocks chooseBlock(int x, int y) {
        return map[x][y];
    }

    public void generateCaves() {
        int countCaves = 0;
        for (int x = 6; x < map.length - 3; x++) {
            for (int y = 0; y < map[0].length - 3; y++) {
                if (y == 20) {
//                    if (block.nextInt(100) >= 20 && block.nextInt(100) <= 21){
//                        terraria.getHandler().addBlock(map[x][y]);
//                        terraria.getHandler().addBlock(map[x + 1][y]);
//                        terraria.getHandler().addBlock(map[x + 2][y]);
//                        terraria.getHandler().addBlock(map[x + 1][y - 1]);
//                        terraria.getHandler().addBlock(map[x + 2][y - 1]);
//                        terraria.getHandler().addBlock(map[x + 3][y]);
//                        map[x][y].id = ID.Blocks;
//                        map[x + 1][y].id = ID.Blocks;
//                        map[x + 2][y].id = ID.Blocks;
//                        map[x + 1][y - 1].id = ID.Blocks;
//                        map[x + 2][y - 1].id = ID.Blocks;
//                        map[x + 3][y].id = ID.Blocks;
//                    }
                    //generation access to caves
                    if (block.nextInt(100) >= 0 && block.nextInt(100) <= 2 && countCaves < 2 && x < 120) {
                        countCaves++;
                        for (int count = 0; count < 5; count++) {
                            for (int i = 0; i < 5; i++) {
                                for (int j = 0; j < 5; j++) {
                                    terraria.getHandler().removeBlock(map[x + count][8]);
                                    terraria.getHandler().removeBlock(map[x + i + count][y + i]);
                                    terraria.getHandler().removeBlock(map[x + i + 1 + count][y + i + 7]);
                                }
                            }
                        }
                        for (int a = 0; a < 5; a++) {
                            for (int b = 0; b < 3; b++) {
                                terraria.getHandler().removeBlock(map[(x + 3) - b + a][(y + 5) + b]);
                            }
                        }

                    }
                }
                //generation caves
                if (y >= 29) {
                    if (block.nextInt(100) == 32 || block.nextInt(100) == 35) {
                        terraria.getHandler().removeBlock(map[x - 1][y - 2]);
                        terraria.getHandler().removeBlock(map[x][y - 2]);
                        terraria.getHandler().removeBlock(map[x - 1][y - 1]);
                        terraria.getHandler().removeBlock(map[x][y]);
                        terraria.getHandler().removeBlock(map[x + 1][y]);
                        terraria.getHandler().removeBlock(map[x][y - 1]);
                        terraria.getHandler().removeBlock(map[x + 1][y - 1]);
                        terraria.getHandler().removeBlock(map[x + 2][y]);
                        terraria.getHandler().removeBlock(map[x + 2][y + 1]);
                        terraria.getHandler().removeBlock(map[x + 1][y + 1]);
                        terraria.getHandler().removeBlock(map[x - 2][y - 1]);
                        terraria.getHandler().removeBlock(map[x - 2][y - 2]);
                        terraria.getHandler().removeBlock(map[x - 3][y - 1]);
                        terraria.getHandler().removeBlock(map[x - 3][y - 2]);
                        terraria.getHandler().removeBlock(map[x - 4][y - 1]);
                        terraria.getHandler().removeBlock(map[x - 4][y - 2]);
                        terraria.getHandler().removeBlock(map[x - 4][y]);
                        terraria.getHandler().removeBlock(map[x - 5][y]);
                        terraria.getHandler().removeBlock(map[x - 5][y - 1]);
                        terraria.getHandler().removeBlock(map[x - 6][y]);
                        terraria.getHandler().removeBlock(map[x - 5][y + 1]);
                        terraria.getHandler().removeBlock(map[x - 6][y + 1]);
                    }
                }
            }
        }
        System.out.println("Generating Caves ...");
        generateTopTerrain();
    }

    public void generateTopTerrain() {
        int range = block.nextInt(20);
        for (int x = 0; x < map.length - 1; x++) {
            for (int y = 20; y >= 9; y--) {
                int usage = 0;
                int chance = 50;
                //generation terrain on top
                //if(map[x][y+1].id ==  ID.Blocks){
                if (x != 0 && x != map.length) {
                    if (map[x - 1][y].id == ID.Blocks || map[x + 1][y].id == ID.Blocks) {
                        chance += 40;
                    }
                }
                if (x >= range && x <= range + 20) {
                    chance -= 15;
                }
                if (x >= range + 23 && x <= range + 40) {
                    chance += 40;
                }
                if (block.nextInt(100) >= 0 && block.nextInt(100) <= chance) {
                    while (map[x][y + 1 + usage].id != ID.Blocks) {
                        usage++;
                    }
                    terraria.getHandler().addBlock(map[x][y + usage]);
                    map[x][y + usage].id = ID.Blocks;
                }
                //}
            }
        }
        for (int x = map.length - 2; x > 2; x--) {
            for (int y = 10; y <= 18; y++) {
                if (map[x][y].id == ID.Blocks) {
                    if (map[x - 1][y].id != ID.Blocks && map[x + 1][y].id != ID.Blocks && map[x][y - 1].id != ID.Blocks) {
                        map[x][y].id = ID.None;
                        terraria.getHandler().removeBlock(map[x][y]);
                    }
                    if (map[x][y - 1].id == ID.Blocks && map[x - 1][y].id == ID.Blocks && map[x + 1][y].id != ID.Blocks) {
                        map[x + 1][y].id = ID.Blocks;
                        terraria.getHandler().addBlock(map[x + 1][y]);
                        //map[x+1][y].grass = false;
                    }
                    if (map[x][y - 1].id == ID.Blocks && map[x + 1][y].id == ID.Blocks && map[x - 1][y].id != ID.Blocks) {
                        map[x - 1][y].id = ID.Blocks;
                        terraria.getHandler().addBlock(map[x - 1][y]);
                        //map[x-1][y].grass = false;
                    }
                }
                if (map[x - 1][y].id == ID.Blocks && map[x + 1][y].id == ID.Blocks && map[x][y + 1].id == ID.Blocks
                        && map[x][y - 1].id == ID.Blocks && map[x][y].id == ID.None) {
                    map[x][y].id = ID.Blocks;
                    terraria.getHandler().addBlock(map[x][y]);
                    map[x][y].grass = false;
                }
            }
        }
        System.out.println("Generating terrain ...");
        generateGrass();
        generateTrees();
    }

    public void generateGrass(){
        for (int x = 0; x < map.length - 1; x++) {
            for (int y = 20; y >= 9; y--) {
                if (map[x][y].id == ID.Blocks) {
                    if (map[x][y - 1].id != ID.Blocks) {
                        if (map[x][y + 1].id == ID.Blocks) {
                            map[x][y].grass = true;
                        }
                    }
                }
            }
        }
        System.out.println("Generating grass ...");
    }

    public void generateTrees(){
        for (int x = 5; x < map.length - 5; x++) {
            for (int y = 0; y < map[0].length - 5; y++){
                if (block.nextInt(8) == 1) {
                    //generate strain 
                    if (map[x][y+1].grass){
                        int ran = 4 + (int) (Math.random() * 6);
                        for (int i = 0; i <= ran; i++) {
                            if(i>0){
                                //trees hands
//                                if(block.nextInt(10) == 4){
//                                    map[x-1][y-i].id = ID.Kmen;
//                                    terraria.getHandler().addBlock(map[x-1][y-i]);
//                                    
//                                }
//                                if(block.nextInt(10) == 3){
//                                    map[x+1][y-i].id = ID.Kmen;
//                                    terraria.getHandler().addBlock(map[x+1][y-i]);
//                                    
//                                }
                            }
                            map[x][y - i].id = ID.Kmen;
                            terraria.getHandler().addBlock(map[x][y - i]);

                        }
                        //generate the top of the tree (green)
                        map[x][y - ran].id = ID.Trees;
                        terraria.getHandler().addBlock(map[x][y - ran]);
                        map[x + 1][y - ran].id = ID.Trees;
                        terraria.getHandler().addBlock(map[x + 1][y - ran]);
                        map[x - 1][y - ran].id = ID.Trees;
                        terraria.getHandler().addBlock(map[x - 1][y - ran]);
                        map[x][y - ran - 1].id = ID.Trees;
                        terraria.getHandler().addBlock(map[x][y - ran - 1]);
                    }
                }
                //bushes
//                if(block.nextInt(100)>=40 && block.nextInt()<=50){
//                    if(map[x][y+1].grass){
//                        map[x][y].id=ID.Bush;
//                        terraria.getHandler().addBlock(map[x][y]);
//                    }
//                }
            }
        }
        System.out.println("Generating trees ...");
    }

    public void generateWater() {
        for (int x = 5; x < map.length - 5; x++) {
            for (int y = 0; y < map[0].length - 5; y++) {
                if (block.nextInt(200) == 143) {
                    if (y >= 9) {
                        for (int a = 0; a < 5; a++) {
                            if (map[x + a - 1][y].id != ID.None && map[x + a][y - 1].id != ID.None && map[x + a + 1][y].id != ID.None && map[x + a][y + 1].id != ID.None && map[x + a][y - 1].id != ID.Kmen) {
                                map[x + a][y].id = ID.Water;
                            }
                        }
                    }
                }
            }
        }
    }

    public ABlocks getMap(int x, int y) {
        return this.map[x][y];
    }

    public ABlocks[][] getMap() {
        return this.map;
    }
}
