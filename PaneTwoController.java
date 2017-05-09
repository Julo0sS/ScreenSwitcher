package screenswitcher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;

public class PaneTwoController implements ViewControllerInterface{

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
    public void goPaneOneFromTwo(){
        selectedView.set(getClass().getResource("PaneOne.fxml"));
    }
}
