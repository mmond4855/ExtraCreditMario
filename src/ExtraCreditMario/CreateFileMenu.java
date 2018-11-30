
package ExtraCreditMario;

import dao.LineSequential;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.print.PrinterJob;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static ExtraCreditMario.StandardMenu.file;

public class CreateFileMenu 
{
    static File file = null;
    
    static Menu FileMenu(TextArea textDocument, Stage aStage)
    {
        final Menu fileMenu = new Menu("File");
        
        //Menu Items
        MenuItem clear = new MenuItem("New");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        MenuItem close = new MenuItem("Close");
        MenuItem pageSet = new MenuItem("Page Setup");
        MenuItem print = new MenuItem("Print");
        
        fileMenu.getItems().add(clear);
        fileMenu.getItems().add(open);
        fileMenu.getItems().add(save);
        fileMenu.getItems().add(saveAs);
        fileMenu.getItems().add(close);
        fileMenu.getItems().add(pageSet);
        fileMenu.getItems().add(print);
        
        clear.setOnAction((ActionEvent e) -> 
        {
            aStage.setTitle("TextEditor - Untitled");
            textDocument.clear();
            file = null;
            
        });
        
        open.setOnAction((ActionEvent e) -> 
        {
            FileChooser fileChooser = new FileChooser(); //Chooses files.
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
            file = fileChooser.showOpenDialog(aStage);
            
            if(file != null)
            {
                textDocument.clear(); //Clears the text editor
                String inputLine; 
                String fileStream = file.getName(); //Obtains file
                
                aStage.setTitle("Text Editor - " + fileStream); //Sets the name of the top as the file name.
                LineSequential.open(file.getAbsolutePath(), fileStream, "input");//You already know about line sequential.
                
                while((inputLine = LineSequential.read(fileStream)) != null)
                {
                    textDocument.appendText(inputLine + "\n");
                }
                
                LineSequential.close(fileStream, "input");
                
            }
        
        });
        
        save.setOnAction((ActionEvent e) ->
        {
            if(file != null)
            {
                saveOperation(textDocument, aStage);
            }
            
            else
            {
                saveAsOperation(textDocument, aStage);
            }
        });
        
        saveAs.setOnAction((ActionEvent e) ->
        {
            saveAsOperation(textDocument, aStage);
        });
        
        close.setOnAction((ActionEvent e) ->
        {
            aStage.setTitle("Text Editor - Untitled");
            textDocument.clear();
            file = null;
        });
        
        //For Page SetUp
        pageSet.setOnAction((ActionEvent e) ->
        {
            pageSetupOperation(textDocument, aStage);
        
        });
        
        //For printing....
        print.setOnAction((ActionEvent e) ->
        {
            printDialog(textDocument, aStage);
        
        });
        
        //For the edit menu.
     
        
        return fileMenu;
        
    }
    
    static void saveAsOperation(TextArea textDocument, Stage primaryStage)
    {
        FileChooser fileChooser = new FileChooser();
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        file = fileChooser.showSaveDialog(primaryStage);
        
        if(file != null)
        {
            saveOperation(textDocument, primaryStage);
        }
    
    }
    
    static void saveOperation(TextArea textDocument, Stage primaryStage)
    {
        String fileStream = file.getName();
        primaryStage.setTitle("Text Editor - " + fileStream);
        
        LineSequential.open(file.getAbsolutePath(), fileStream, "output");
        LineSequential.write(fileStream, textDocument.getText());
        LineSequential.close(fileStream, "output");
        
    }
    
    static void pageSetupOperation(TextArea textDocument, Stage primaryStage)
    {
        //This is for the page setUp.....
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        
        if(printerJob == null)
        {
            return;
        }
        
        boolean startUp = printerJob.showPageSetupDialog(primaryStage);
        
        if(startUp)
        {
            print(printerJob, textDocument);
        
        }
    }
    
    static void printDialog(TextArea textDocument, Stage primaryStage)
    {
        //This is for the print option
        PrinterJob printerJob = PrinterJob.createPrinterJob();
    
         
        if(printerJob == null)
        {
            return;
        }
        
        //for the printing tool
        boolean pRint = printerJob.showPrintDialog(primaryStage);
        
        if(pRint)
        {
            print(printerJob, textDocument);
        
        }
        
    }
    
    static void print(PrinterJob printerJob, TextArea textDocument)
    {
        //This is for the execution of the print method.
        boolean printed = printerJob.printPage(textDocument);
        
        if(printed)
        {
            printerJob.endJob();
        }
    
    
    }
    
}
