
package ExtraCreditMario;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class TextEditor extends Application 
{
    
    //Text Area
        TextArea textDocument;
        
    @Override
    public void start(Stage primaryStage) 
    {
        textDocument = new TextArea();

        
        //For the BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(StandardMenu.StandardMenuBar(textDocument, primaryStage));
        //Menu Bar
        borderPane.setCenter(textDocument);
        
        Scene scene = new Scene(borderPane, 400, 400);
        
        primaryStage.setTitle("Text Editor - Untitled");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
