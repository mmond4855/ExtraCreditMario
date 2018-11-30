
package ExtraCreditMario;

import dao.LineSequential;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static ExtraCreditMario.StandardMenu.file;

public class CreateEditMenu 
{
    static Menu EditMenu(TextArea textDocument)
    {
        final Menu editMenu = new Menu("Edit");
        
        
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");
        
        editMenu.getItems().add(copy);
        editMenu.getItems().add(paste);
        
        copy.setOnAction((ActionEvent e) ->
        {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent c1 = new ClipboardContent();
           
            if(textDocument.isFocused())
            {
                String textString = textDocument.getSelectedText();
                
                if(textString != null)
                {
                    c1.putString(textString);
                    clipboard.setContent(c1);
                }
            }
        });
        
        paste.setOnAction((ActionEvent e) ->
        {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            if(clipboard.hasString())
            {
                textDocument.appendText(clipboard.getString());
            
            
            }
        
        
        });
        
        return editMenu;
    }
    
    
}
