/*
Craig Poirier
N01410520
Assignment 3
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.util.Calendar;
import javafx.scene.control.Label;
import java.text.SimpleDateFormat;
import javafx.scene.paint.Color;

public class TimeButtonDemo extends Application {
   
    protected BorderPane getPane() {
    
    BorderPane pane = new BorderPane(); //pane for containing buttons and clock  
  
    HBox paneForButtons = new HBox(50); //pane for containing buttons 
    paneForButtons.setAlignment(Pos.CENTER); //center paneForButtons pane
    paneForButtons.setStyle("-fx-border-color: green"); //add green border to paneForButtons
     
    // write code for buttons 
    Image USA = new Image("usa.jpg"); //import image
    Button USAbtn = new Button("12hr"); //Create button, add 12hr text
    ImageView imageUSA = new ImageView(USA);
    imageUSA.setFitWidth(47);
    imageUSA.setFitHeight(31);
    USAbtn.setGraphic(imageUSA);
    
    Image EU = new Image("eu.jpg"); //import image
    Button EUbtn = new Button("24hr"); //Create button, add 24hr text
    ImageView imageEU = new ImageView(EU);
    imageEU.setFitWidth(47);
    imageEU.setFitHeight(31);
    EUbtn.setGraphic(imageEU);
    
    paneForButtons.getChildren().addAll(USAbtn, EUbtn); //Add buttons to paneForButtons
           
    DigitalClock clock = new DigitalClock();  //clock to be added to pane
        
    //Add clock and paneForButtons to pane and Refreshes the clock pane
    EventHandler<ActionEvent> update = e -> {   
    pane.setCenter(clock.lblCurrentTime);      
    };
       
    Timeline refreshPane = new Timeline(
        new KeyFrame(Duration.millis(1000), update));
        refreshPane.setCycleCount(Timeline.INDEFINITE);
        refreshPane.play();
    
    pane.setBottom(paneForButtons);
    
    // handle button clicks with lambdas
    USAbtn.setOnAction(e -> clock.changeFormat12());
    EUbtn.setOnAction(e -> clock.changeFormat24());
  
    // handle keyboard presses with lambdas    
    pane.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.UP) {
         clock.color = 3; // Color 3 = Cyan
         }
      else if (e.getCode() == KeyCode.DOWN) {
         clock.color = 2; // Color 2 = Red
         } 
      else if (e.getCode() == KeyCode.ENTER) {
         clock.color = 1; // Color 1 = Black
         }          
         });       
        
    return pane;
  }
  
  public void start(Stage primaryStage) {
    // Create a scene and place it in the stage
    Scene scene = new Scene(getPane(),255, 150);
    primaryStage.setTitle("ClockApplication"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  public static void main(String[] args) {
    launch(args);
  }
}

class DigitalClock extends Label {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Timeline animation;
    private Calendar time;
    public Label lblCurrentTime;
    public Font myFont;
    public int color = 1;
     
    public DigitalClock() {
        // get time and set text with lambda
        myFont = new Font("Arial", 30);
        
        EventHandler<ActionEvent> eventHandler = e -> {           
            setTextColor(color);
            lblCurrentTime.setFont(myFont);         
        };
    
        // set animation here
        animation = new Timeline(
        new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
    }
    
    public void setTextColor(int color)
    {
      time = Calendar.getInstance();
      String timeString = dateFormat.format(time.getTime());
      lblCurrentTime = new Label(timeString);
    
      if (color == 1) 
         {
           lblCurrentTime.setTextFill(Color.BLACK);
         }
    
      else if (color == 2) 
         {
           lblCurrentTime.setTextFill(Color.RED);
         }
    
      else if (color == 3) 
         {
           lblCurrentTime.setTextFill(Color.CYAN);
         }
    }
 
    public void changeFormat24(){
        // write code here for changing to 24 hour clock
        dateFormat = new SimpleDateFormat("HH:mm:ss");
     }
    
    public void changeFormat12(){
        // write code here for changing to 12 hour clock
        dateFormat = new SimpleDateFormat("hh:mm:ss aa");
    }         
}