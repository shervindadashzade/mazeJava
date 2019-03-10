package sample;

public class Home {
  public int x=0;
  public int y=0;
  public static final int EMPT = 0;
    public static final int VISITED = 4;
  public static final int START = 1;
  public static final int END = 2;
  public static final int WALL=3;
//  Direction Up = new Direction();
//  Direction Down = new Direction();
//  Direction Left = new Direction();
//  Direction Right = new Direction();
  public int state= this.EMPT;

    @Override
    public String toString() {
        return Integer.toString(this.state);
    }
}
class Direction{
    //VISITED
    //WALL
    //UNVISTED
    public static final int UNVISITED = 0;
    public static final int WALL =1;
    public static final int VISITED =2;
    public static final int NULL = 3;
    public int state = 0;
}
