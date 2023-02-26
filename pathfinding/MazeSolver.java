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
        ArrayList<Coordinate> path = Astar.astar(maze, start, end);
        path.add(new Coordinate(null, start));
        System.out.println(printPath(path));

    }

    public static String printPath(ArrayList<Coordinate> coords) {
        if(coords == null)  {
            return "No Path";
        }
        String msg = "";
        for(int i = coords.size()-1; i > -1; i--) {
            msg += "{" + coords.get(i).position[0] + "," + coords.get(i).position[1] +"}" + " ";
        }
        return msg;
    }
}
