package gui.toolbar;

import javax.swing.event.EventListenerList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

public class PropertyToolbarModel {

    private EventListenerList eventListenerList;
    private Map<String, String> properties;

    private Map<String, String> tempBuffer;

    public PropertyToolbarModel() {
        properties = new HashMap<>();
        tempBuffer = new HashMap<>();
        eventListenerList = new EventListenerList();
    }

    public Map<String, String> getProperties(){
        return properties;
    }

    private void firePropertyChanged(String property, String value) {
        Arrays.stream(eventListenerList.getListeners(IPropertyToolbarModelListener.class))
                .forEach(l->l.onPropertyChangedEvent(property, value));
    }

    public void addListener(IPropertyToolbarModelListener l){
        eventListenerList.add(IPropertyToolbarModelListener.class, l);
    }

    public void commit() {
        for(String key : tempBuffer.keySet()){
            if(properties.containsKey(key) && !tempBuffer.get(key).equals(properties.get(key))){
                properties.replace(key, tempBuffer.get(key));
            }else{
                properties.put(key, tempBuffer.get(key));
            }
            firePropertyChanged(key, tempBuffer.get(key));
        }
        tempBuffer.clear();
    }

    public void addModification(String property, String value) {
        tempBuffer.put(property, value);
    }

    public void setProperties(Map<String,String> properties) {
        this.properties.clear();
        this.properties.putAll(properties);
    }

    public interface IPropertyToolbarModelListener extends EventListener{
        void onPropertyChangedEvent(String property, String newValue);
        void onPropertiesChangedEvent();
    }
}
