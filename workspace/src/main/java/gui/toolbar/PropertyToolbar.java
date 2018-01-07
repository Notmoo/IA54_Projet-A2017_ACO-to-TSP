package gui.toolbar;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.swing.event.EventListenerList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

public class PropertyToolbar {

    private final EventListenerList eventListenerList;
    private final PropertyToolbarModel model;

    private VBox mainPane;
    private Map<Label, TextField> guiProperties;

    public PropertyToolbar() {
        eventListenerList = new EventListenerList();
        model = new PropertyToolbarModel();
        model.addListener(new PropertyToolbarModel.IPropertyToolbarModelListener() {
            @Override
            public void onPropertyChangedEvent(String property, String newValue) {
                updateProperty(property, newValue);
            }

            @Override
            public void onPropertiesChangedEvent() {
                updateGui();
            }
        });
        guiProperties = new HashMap<>();

        updateGui();
    }

    private void applyChanges() {
        for(Label label : guiProperties.keySet()){
            model.addModification(label.getText(), guiProperties.get(label).getText());
        }
        model.commit();
    }

    private void updateProperty(String property, String value) {
        for(Label label : guiProperties.keySet()){
            if(label.getText().equals(property))
                guiProperties.get(label).setText(value);
        }
        firePropertyChangedEvent(property, value);
    }

    private void updateGui() {
            mainPane = new VBox();
            GridPane grid = new GridPane();

            int rowCount = 0;
            for(String key : model.getProperties().keySet()){
                Label label = new Label(key);
                TextField value = new TextField(model.getProperties().get(key));


                grid.add(label,0, rowCount);
                grid.add(value, 1, rowCount);
                guiProperties.put(label, value);

                rowCount++;
            }

            Button validationButton = new Button("Valider");
            validationButton.setOnAction((event -> applyChanges()));

            mainPane.getChildren().addAll(grid, validationButton);
    }

    public Pane getContent(){
        return mainPane;
    }

    public void addListener(IPropertyToolbarListener l){
        eventListenerList.add(IPropertyToolbarListener.class, l);
    }

    private void firePropertyChangedEvent(String property, String value) {
        Arrays.stream(eventListenerList.getListeners(IPropertyToolbarListener.class))
                .forEach(l->l.onPropertyChangedEvent(property, value));
    }

    public void setProperties(Map<String, String> properties) {
        model.setProperties(properties);
        updateGui();
    }

    public interface IPropertyToolbarListener extends EventListener{
        void onPropertyChangedEvent(String property, String value);
    }
}
