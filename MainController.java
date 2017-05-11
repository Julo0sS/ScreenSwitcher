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
    
    //This could be helpful to set just a function, taking the "resource" argument to load a fxml
    public void handleViewSelection(String target){
        try {
            selectedView.set(new URL(null,target));
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void goPaneOne(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaneOne.fxml"));
        currentViewController.set((ViewControllerInterface)loader.getController());
        String target = "PaneOne.fxml";
        selectedView.set(getClass().getResource(target));
    }
    @FXML
    public void goPaneTwo(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaneTwo.fxml"));
        currentViewController.set((ViewControllerInterface)loader.getController());
        String target = "PaneTwo.fxml";
        selectedView.set(getClass().getResource("PaneTwo.fxml"));
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
        ObservableValue<URL> subMenuSelectedView = Bindings.select(currentViewController, "selectedView");
        subMenuSelectedView.addListener(new ChangeListener<URL>() {  
            public void changed(ObservableValue<? extends URL> obs, URL oldView, URL newView) {  
                System.out.println("CHANGELISTENER TRIGGERED!");
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
                    currentViewController.set((ViewControllerInterface)loader.getController());
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
