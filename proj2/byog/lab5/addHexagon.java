package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class addHexagon {
    public static void addhexagon(Position p, int size, TETile tile, TETile[][] world) {
        for (int i = 0; i < size; i++) {
            int move = size - 1 - i;
            for (int j = 0; j < size + 2 * i; j++) {
                world[p.x + move + j][p.y + i] = tile;
            }
        }
        for (int i = size; i < 2 * size; i++) {
            int move = i - size;
            for (int j = 0; j < size+2*(2*size-1-i); j++) {
                world[p.x + move + j][p.y + i] = tile;
            }
        }
    }
}
