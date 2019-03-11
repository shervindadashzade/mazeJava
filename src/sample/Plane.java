package sample;

public class Plane {
    Home[][] homes;
    Home start;
    int row;
    int column;
    int x;
    int y;
    public Plane(int row,int column){
        homes = new Home[row][column];
        this.row = row;
        this.column = column;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                homes[i][j] = new Home();
                homes[i][j].x = j;
                homes[i][j].y = i;
                homes[i][j].state = Home.EMPT;
            }
        }
    }
    public void printHomes()
    {
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                System.out.print(homes[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    public Stack processMaze(){
        int c=0;
        Stack stack = new Stack();
        stack.push(start);
        Home current = start;
        while(current.state != Home.END){
            System.out.println(current);
            //check can go right
            if(current.x != column-1 && homes[current.y][current.x+1].state != Home.WALL && homes[current.y][current.x+1].state != Home.VISITED){
                if( homes[current.y][current.x+1].state == Home.END){
                    System.out.println("WE ARIVED END HOME :))))");
                    break;
                }
                homes[current.y][current.x+1].state = Home.VISITED;
                        stack.push(homes[current.y][current.x+1]);
                        current = homes[current.y][current.x+1];
                        System.out.println("RIGHT");
            }else if(current.y != row-1 && homes[current.y+1][current.x].state != Home.WALL && homes[current.y+1][current.x].state != Home.VISITED){
                if( homes[current.y+1][current.x].state == Home.END){
                    System.out.println("WE ARIVED END HOME :))))");
                    break;
                }
                homes[current.y+1][current.x].state = Home.VISITED;
                        stack.push(homes[current.y+1][current.x]);
                        current = homes[current.y+1][current.x];
                        System.out.println("DOWN");
            }else if(current.x != 0 && homes[current.y][current.x-1].state != Home.WALL && homes[current.y][current.x-1].state != Home.VISITED){
                if( homes[current.y][current.x-1].state == Home.END){
                    System.out.println("WE ARIVED END HOME :))))");
                    break;
                }
                        homes[current.y][current.x-1].state = Home.VISITED;
                        stack.push(homes[current.y][current.x-1]);
                        current = homes[current.y][current.x-1];
                        System.out.println("LEFT");
            }else if(current.y != 0 && homes[current.y-1][current.x].state != Home.WALL && homes[current.y-1][current.x].state != Home.VISITED){
                if( homes[current.y-1][current.x].state == Home.END){
                    System.out.println("WE ARIVED END HOME :))))");
                    break;
                }
                        homes[current.y-1][current.x].state = Home.VISITED;
                        stack.push(homes[current.y-1][current.x]);
                        current = homes[current.y-1][current.x];
                        System.out.println("UP");
            }else{
                // cant go anywhere
                current = stack.pop();
                System.out.println("GO BEFORE HOUSE");
                if(current == null){
                    System.out.println("No WAY FOUNDED ... :(");
                    return null;
                }
            }
            if(c==0){
                c++;
            }else{
                if(current.state == Home.START){
                    System.out.println("BACKED TO START HOME");
                    break;
                }
                if(current.state == Home.END){
                    System.out.println("WE ARRIVED TO END HOME");
                    break;
                }
            }
        }
        System.out.println("END OF PROCEES");
        return stack;
    }

}
