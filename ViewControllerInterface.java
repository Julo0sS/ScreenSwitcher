package screenswitcher;

import java.net.URL;
import javafx.beans.property.ReadOnlyObjectProperty;

public interface ViewControllerInterface {
    public ReadOnlyObjectProperty<URL> selectedViewProperty();  
    
    public URL getSelectedView() ;
}
