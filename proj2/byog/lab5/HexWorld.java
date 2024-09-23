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
        ter.initialize(100, 100);
        TETile[][] world = new TETile[100][100];
        Position p = new Position(50, 50);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        addHexagon.addhexagon(p, 3, Tileset.FLOWER, world);
        for (int i = 0; i < 10; i++) {
            p = nextPosition(p, 3);
            if (i % 3 == 0) {
                addHexagon.addhexagon(p, 3, Tileset.FLOOR, world);
            } else if (i % 3 == 1) {
                addHexagon.addhexagon(p, 3, Tileset.FLOWER, world);
            } else {
                addHexagon.addhexagon(p, 3, Tileset.WALL, world);
            }
        }
        ter.renderFrame(world);
    }

    private static Position nextPosition(Position p, int size) {
        double rand = Math.random();
        Position nextP;
        if (rand <= 0.25 && p.x + 2 * size - 2 > 0 && p.y + 2 * size - 2 > 0) {
            nextP = new Position(p.x + 2 * size - 2, p.y + 2 * size - 2);
        } else if (rand <= 0.5 && p.x - 2 * size + 2 > 0 && p.y + 2 * size - 2 > 0) {
            nextP = new Position(p.x - 2 * size + 2, p.y + 2 * size - 2);
        } else if (rand <= 0.75 && p.x - 2 * size + 2 > 0 && p.y - 2 * size + 2 > 0) {
            nextP = new Position(p.x - 2 * size + 2, p.y - 2 * size + 2);
        } else if (p.x + 2 * size - 2 > 0 && p.y - 2 * size + 2 > 0) {
            nextP = new Position(p.x + 2 * size - 2, p.y - 2 * size + 2);
        }else{
            nextP = p;
        }
        return nextP;
    }

}
