/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial11;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author becky
 */
public class ThreadRace extends Application implements EventHandler<ActionEvent>
{
    
    StackPane mainPane;
    VBox vPane;
    Scene newScene;
    ArrayList <Button> buttonArray;
    ArrayList <TextField> tempArray;
    ArrayList<Thread> threadArray;
    
    @Override
    public void init()
    {
        buttonArray = new ArrayList<>();
        threadArray = new ArrayList<>();
        tempArray = new ArrayList<>();
        mainPane = new StackPane();
        newScene = new Scene(mainPane, 500, 500);
        
        SetVMenu();
        vPane.setAlignment(Pos.CENTER);
        //vPane.setStyle("-fx-background-color: blue;");
        mainPane.getChildren().add(vPane);
        mainPane.setAlignment(vPane, Pos.CENTER);
        
        vPane.prefWidthProperty().bind(mainPane.widthProperty());
        mainPane.prefWidthProperty().bind(newScene.widthProperty());
        
        
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        primaryStage.setScene(newScene);
        primaryStage.show();
        
    }
    
    public void SetVMenu()
    {
        vPane = new VBox();
        
        Button startButton = new Button("Start");
        startButton.setOnAction(click->handle(click));
        vPane.getChildren().add(startButton);
        startButton.setId("Start");
        
        
        for(int ii = 0; ii < 10; ii += 1)
        {   
            FlowPane subPane = new FlowPane();
            
            Button vButton = new Button("Pause");
            vButton.setId("Pause"+ii);
            vButton.setOnAction(click->handle(click));
            buttonArray.add(vButton);
            subPane.getChildren().add(vButton);
            
            TextField textCounter = new TextField();
            tempArray.add(textCounter);
            subPane.getChildren().add(textCounter);
            subPane.setAlignment(Pos.CENTER);
            vPane.getChildren().add(subPane);
        }
        
    }

    @Override
    public void handle(ActionEvent event) {
        Button handleButton = (Button) event.getSource();
        
        System.out.println("button id:" + handleButton.getId());
        
        if(handleButton.getId().equals("Start"))
        {
            for(int ii = 0; ii < tempArray.size(); ii += 1)
            {
                try
                {
//                    CountingCrows object = new CountingCrows(tempArray.get(ii));
//                    threadArray.add(object);
//                    threadArray.get(ii).call();
//                    threadArray.get(ii).run();
                    //Thread newThread = new Thread(threadArray.get(ii));
                    //newThread.run();
                    //tempArray.get(ii).setText(threadArray.get(ii).getMessage());
                    
                    StartTask(tempArray.get(ii));
                    Thread.sleep(100);
                }
                catch(Exception e)
                {
                    
                    System.out.println(e);
                    
                }
                
            }
        }
        else
        {
            for(int ii = 0; ii < tempArray.size(); ii += 1)
            {
                if(handleButton.getId().equals(buttonArray.get(ii).getId()))
                {
                    try
                    {
                        System.out.println("Running pause@@@");
                        synchronized(threadArray.get(ii))
                        {
                            threadArray.get(ii).wait();
                        }
                        
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }
    }
    
    
    public void StartTask(TextField inputField)
    {
        CountingCrows task = new CountingCrows(inputField);
        inputField.textProperty().bind(task.messageProperty());
        Thread newThread = new Thread(task);
        threadArray.add(newThread);
        
        newThread.start();
    }
}
