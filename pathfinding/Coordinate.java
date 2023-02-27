package pathfinding;

public class Coordinate {
    Coordinate parent;
    public int[] position = new int[2];
    int g;
    double h;
    double f;

    public Coordinate(Coordinate parent, int[] position) {
        this.parent = parent;
        this.position = position;
    }
    
    public boolean equals(int[] tuple) {
        if(position[0] == tuple[0] && position[1] == tuple[1]) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "{" + position[0] + "," + position[1] + "}";
    }
}
