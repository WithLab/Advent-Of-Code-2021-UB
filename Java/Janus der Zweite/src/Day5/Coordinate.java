package Day5;

public class Coordinate {

    int x;
    int y;
    int visited;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
        visited = 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
