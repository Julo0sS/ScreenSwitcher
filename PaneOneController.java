package screenswitcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class PaneOneController implements ViewControllerInterface{

    private final ReadOnlyObjectWrapper<URL> selectedView = new ReadOnlyObjectWrapper<>(this, "selectedView", null); 
    
    @Override
    public ReadOnlyObjectProperty<URL> selectedViewProperty() {
        return selectedView.getReadOnlyProperty();  
    }

    @Override
    public URL getSelectedView() {
        return selectedView.get();
    }
    
    @FXML
    public void goPaneTwoFromOne(){
        selectedView.set(getClass().getResource("PaneTwo.fxml"));
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        selectedView.set(getClass().getResource("PaneOne.fxml"));
    }    
    
}
