package pathfinding;

import java.util.ArrayList;

public class MazeSolver {
    public static void main(String[] args) {
        int[][] maze = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        int[] start = {0, 0};
        int[] end = {0, 3};
        // ArrayList<Coordinate> path = Astar.astar(maze, start, end);
        // path.add(new Coordinate(null, start));
        // System.out.println(printPath(path));

    }
}
