package interfaces;

import java.nio.file.Path;
import java.util.EventListener;

public interface ITSPDisplayerCallback extends EventListener{
    void onFileInput(Path path);
    void onPropertyChanged(String propertyId, String propertyValue);
    void onGuiClose();
}
