package com.sketch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
 
public class Main extends Application {
	
	Button btn = new Button();
    Label lbl = new Label();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        
        lbl.setLayoutX(70);
        lbl.setLayoutY(150);
        
        btn.setLayoutX(100);
        btn.setLayoutY(100);
        btn.setText("Hello, World!");
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                lbl.setText("Click'd on button Hello, World.");
            }
        });
        
        Group root = new Group();
        
        root.getChildren().add(btn);
        root.getChildren().add(lbl);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}