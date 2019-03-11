package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import javax.swing.*;

public class Controller {
    Plane gamePlane;
    int row=0;
    int column = 0;
    Label selectState;
    Button goProc;
    int selectMode=0;
    @FXML
    Button loginBtn;
    @FXML
    TextField rows;
    @FXML
    TextField columns;

    @FXML
    protected void loginAction(ActionEvent event){
        row = Integer.parseInt(rows.getText().toString());
        column = Integer.parseInt(columns.getText().toString());
        gamePlane =new Plane(row,column);
        selectMode=0;
        System.out.println("row :"+row+","+"column:"+column);
        HBox hBoxes[] = new HBox[row];
        VBox vBox = new VBox();
        for(int j=0;j<row;j++) {
            hBoxes[j] = new HBox();
            for(int i = 0; i < column; i++) {
                Rectangle rect = new Rectangle();
                rect.setWidth(65);
                rect.setHeight(65);
                rect.setFill(Color.BLANCHEDALMOND);
                rect.setStroke(Color.BLACK);
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        int x=0;
                        int y=0;
                        if(selectMode==0){
                            selectState.setText("خانه مقصد را انتخاب کنید");
                            rect.setFill(Color.GREEN);
                            x= Character.getNumericValue(rect.getId().toString().charAt(0));
                            y= Character.getNumericValue(rect.getId().toString().charAt(1));
                            System.out.println(x+","+y);
                            gamePlane.homes[x][y].state = Home.START;
                            gamePlane.start = gamePlane.homes[x][y];
                        }
                        if(selectMode==1){
                            selectState.setText("دیوار ها را انتخاب کنید");
                            rect.setFill(Color.YELLOW);
                            x= Character.getNumericValue(rect.getId().toString().charAt(0));
                            y= Character.getNumericValue(rect.getId().toString().charAt(1));
                            System.out.println(x+","+y);
                            gamePlane.homes[x][y].state = Home.END;
                        }
                        if(selectMode>1){
                            x= Character.getNumericValue(rect.getId().toString().charAt(0));
                            y= Character.getNumericValue(rect.getId().toString().charAt(1));
                            System.out.println(x+","+y);
                            if(gamePlane.homes[x][y].state == Home.EMPT) {
                                rect.setFill(Color.RED);
                                gamePlane.homes[x][y].state = Home.WALL;
                            }
                            gamePlane.printHomes();
                        }
                        selectMode++;
                    }
                });
                rect.setId(Integer.toString(j)+Integer.toString(i));
                System.out.println(rect.getId());
                hBoxes[j].getChildren().add(rect);
            }
            vBox.getChildren().add(hBoxes[j]);
        }
        selectState = new Label("خانه شروع را انتخاب کنید");
        selectState.setTextAlignment(TextAlignment.CENTER);
        vBox.getChildren().add(selectState);
        goProc = new Button("پردازش");
        vBox.getChildren().add(goProc);
        goProc.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stack stack;
                stack = gamePlane.processMaze();
                if(stack == null){
                    selectState.setText("مسیر پیدا نشد");
                }else {
                    selectState.setText("مسیری به طول "+stack.getSize()+"یافت شد");
                    System.out.println(stack.getSize());
                    for (Home home : stack.getHomes()) {
                        HBox box = (HBox) vBox.getChildren().get(home.y);
                        Rectangle rect = (Rectangle) box.getChildren().get(home.x);
                        rect.setFill(Color.BLUEVIOLET);
                    }
                }
            }
        });
        Stage selectStage = Main.selectStage;
        selectStage.setTitle("انتخاب خانه ها");
        selectStage.setScene(new Scene(vBox));
        selectStage.show();
    }
}
