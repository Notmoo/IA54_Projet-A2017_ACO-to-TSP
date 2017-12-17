package gui.chart;

import javafx.util.Pair;

import javax.swing.event.EventListenerList;
import java.util.*;

public class ChartModel {

    private EventListenerList listenerList;
    private Map<Integer, Float> datas;

    public ChartModel() {
        listenerList = new EventListenerList();
        datas = new HashMap<>();

        //TODO remove hard-coded data
        datas.put(1, 0.5f);
        datas.put(2, 0.6f);
        datas.put(3, 0.7f);
        datas.put(4, 0.8f);
        datas.put(5, 0.9f);
        datas.put(6, 1f);
    }

    public void addData(Integer iterationNb, Float distance){
        if(datas.containsKey(iterationNb)){
            datas.replace(iterationNb, distance);
            Arrays.stream(listenerList.getListeners(IChartModelListener.class))
                    .forEach(IChartModelListener::onDataModified);
        }else{
            datas.put(iterationNb, distance);
            Arrays.stream(listenerList.getListeners(IChartModelListener.class))
                    .forEach(l->l.onDataAdded(iterationNb, distance));
        }
    }

    public void addListener(IChartModelListener l){
        listenerList.add(IChartModelListener.class, l);
    }

    public Map<Integer, Float> getDatas() {
        return datas;
    }

    public interface IChartModelListener extends EventListener {
        void onDataAdded(Integer iteration, Float distance);
        void onDataModified();
    }
}
