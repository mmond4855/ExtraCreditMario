
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



public class StandardMenu 
{
    static File file = null;
    
    static MenuBar StandardMenuBar(TextArea textDocument, Stage aStage)
    {
        MenuBar standardMenuBar = new MenuBar();
        
        standardMenuBar.getMenus().addAll(CreateFileMenu.FileMenu(textDocument, aStage));
        
        
        standardMenuBar.getMenus().addAll(CreateEditMenu.EditMenu(textDocument));
        
       return standardMenuBar;
    }
}
