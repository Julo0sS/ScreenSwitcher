package screenswitcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable, ViewControllerInterface {
    
    private final ObjectProperty<ViewControllerInterface> currentViewController = new SimpleObjectProperty<>();
    
    private final ReadOnlyObjectWrapper<URL> selectedView = new ReadOnlyObjectWrapper<>(this, "selectedView", null);
    
    public void handleViewSelection(String target){
        try {
            selectedView.set(new URL(null,target));
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void goPaneOne(){
        System.out.println("CALL TO GOPANEONE");
        String target = "PaneOne.fxml";
        selectedView.set(getClass().getResource(target));
    }
    @FXML
    public void goPaneTwo(){
        System.out.println("CALL TO GOPANETWO");
        String target = "PaneTwo.fxml";
        selectedView.set(getClass().getResource(target));
    }
    
    @FXML
    private BorderPane root;
                
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("INITIALIZE MAINCONTROLLER");
        ObservableValue<URL> subMenuSelectedView = Bindings.select(currentViewController, "selectedView");  
        subMenuSelectedView.addListener(new ChangeListener<URL>() {  
            public void changed(ObservableValue<? extends URL> obs, URL oldView, URL newView) {  
                System.out.println("CURRENT VIEW CHANGED!");
                if (newView == null) {  
                    root.setCenter(null);  
                } else {  
                    Parent view = null;
                    FXMLLoader loader = new FXMLLoader(newView);  
                    try {
                        view = (Parent) loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    root.setCenter(view); 
                }  
            }  
        });  
    }    

    @Override
    public ReadOnlyObjectProperty<URL> selectedViewProperty() {
        return selectedView.getReadOnlyProperty();  
    }

    @Override
    public URL getSelectedView() {
        return selectedView.get();
    }
    
}
