package screenswitcher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{
    
    private final ObjectProperty<ViewControllerInterface> currentViewController = new SimpleObjectProperty<>();
    
    @FXML
    public void goPaneOne(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaneOne.fxml"));
        Parent view = null;
        try {
            view = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentViewController.set((ViewControllerInterface)loader.getController());
        root.setCenter(view); 
    }
    @FXML
    public void goPaneTwo(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaneTwo.fxml"));
        Parent view = null;
        try {
            view = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentViewController.set((ViewControllerInterface)loader.getController());
        root.setCenter(view); 
    }
    
    @FXML
    private BorderPane root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaneOne.fxml"));
        Parent main = null;  
        try {
            main = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentViewController.set((ViewControllerInterface)loader.getController());
        root.setCenter(main);
        ObservableValue<URL> currentView = Bindings.select(currentViewController, "selectedView");
        currentView.addListener(new ChangeListener<URL>() {  
            public void changed(ObservableValue<? extends URL> obs, URL oldView, URL newView) {  
                System.out.println("CHANGELISTENER TRIGGERED!");
                if (newView == null) {
                    System.out.println("VIEW IS NULL");
                    root.setCenter(null);  
                } else {
                    System.out.println("VIEW IS NOT NULL !");
                    Parent view = null;
                    FXMLLoader loader = new FXMLLoader(newView);
                    try {
                        view = (Parent) loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentViewController.set((ViewControllerInterface)loader.getController());
                    root.setCenter(view); 
                }  
            }  
        });  
    }       
}
