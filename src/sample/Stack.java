package sample;

import java.util.ArrayList;

public class Stack {
    private ArrayList<Home> array = new ArrayList<Home>();
    public void push(Home home){
        array.add(home);
    }
    public Home pop(){
        if(array.size()>0) {
            Home temp = array.get(array.size() - 1);
            array.remove(array.size() - 1);
            return temp;
        }else{
            return null;
        }
    }
    public int getSize(){
        return array.size();
    }
}