package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(20, 20);
        TETile[][] world = new TETile[20][20];
        Position p = new Position(0, 0);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                world[i][j] = Tileset.WALL;
            }
        }
        addHexagon.addhexagon(p, 4, Tileset.FLOWER, world);
        ter.renderFrame(world);
    }
}
