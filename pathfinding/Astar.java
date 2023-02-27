package pathfinding;

import java.util.ArrayList;

public class Astar {
    public ArrayList<Coordinate> astar(int[][] maze, int[] start, int[] end) {
        if(start[0] < 0 || start[0] > maze[0].length - 1) {
            //Out of bounds for x distance
            return null;
        }
        if(start[1] < 0 || start[1] > maze.length - 1) {
            //Out of bounds for y distance
            return null;
        }
        if(end[0] < 0 || end[0] > maze[0].length - 1) {
            //Out of bounds for x distance
            return null;
        }
        if(end[1] < 0 || end[1] > maze.length - 1) {
            //Out of bounds for y distance
            return null;
        }
        Coordinate startCoord = new Coordinate(null, start);
        Coordinate endCoord = new Coordinate(null, end);

        ArrayList<Coordinate> openList = new ArrayList<Coordinate>();
        ArrayList<Coordinate> closeList = new ArrayList<Coordinate>();

        openList.add(startCoord);

        while(openList.size() > 0) {
            Coordinate curr = openList.get(0);
            int currIndex = 0;

            for(Coordinate coord : openList) {
                if(curr.f > coord.f) {
                    curr = coord;
                    currIndex = openList.indexOf(curr);
                }
            }

            openList.remove(currIndex);
            closeList.add(curr);

            if(curr.equals(end)) {
                ArrayList<Coordinate> path = new ArrayList<Coordinate>();
                while(curr.parent != null) {
                    path.add(curr);
                    curr = curr.parent;
                }
                return path;
            }

            int[][] adjacent = {{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,1},{1,-1},{-1,1}}; //This is all8 adjacent squeares

            ArrayList<Coordinate> children = new ArrayList<Coordinate>();
            for(int[] adj : adjacent) {
               
                int[] newPos = {curr.position[0] + adj[0], curr.position[1] + adj[1]};

                if((newPos[0] < 0 || newPos[0] > maze.length - 1) || (newPos[1] < 0 || newPos[1] > maze[0].length - 1)) {
                    //Checks if the new position is within range
                    continue;
                }

                if(maze[newPos[0]][newPos[1]] == 1) {
                    //Check if its valid place to go...
                    continue;
                }

                Coordinate newCoord = new Coordinate(curr, newPos);

                children.add(newCoord);
            }
            
            for(Coordinate child : children) {
                if(closeList.contains(child)) {
                    continue;
                }

                child.g = curr.g + 1;
                child.h = Math.sqrt(Math.pow(child.position[0] - endCoord.position[0], 2) + Math.pow(child.position[1] - endCoord.position[1], 2));
                child.f = child.g + child.h;

                if(openList.contains(child)) {
                    Coordinate node = openList.get(openList.indexOf(child));
                    if(curr.g > node.g) {
                        continue;
                    }
                }

                openList.add(child);
            }

        }
        
        return null;
    }

    public String printPath(ArrayList<Coordinate> coords) {
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
                                                                                                                             