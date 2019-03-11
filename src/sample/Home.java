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
        if(state == 0) {
            return "EMPTY";
        }
        if(state == 1){
            return "START";
        }
        if(state == 2){
            return  "END";
        }
        if(state == 3){
            return "WALL";
        }
        if(state == 4){
            return "VISITED";
        }
        return "WTF?!";
    }
}