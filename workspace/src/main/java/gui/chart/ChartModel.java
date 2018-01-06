package gui.chart;

import javax.swing.event.EventListenerList;
import java.util.*;

public class ChartModel {

    private Integer iterationNb;
    private EventListenerList listenerList;
    private Map<Integer, Double> datas;

    public ChartModel() {
        iterationNb = 0;
        listenerList = new EventListenerList();
        datas = new HashMap<>();
    }

    void addData(double distance){
        datas.put(++iterationNb, distance);
        Arrays.stream(listenerList.getListeners(IChartModelListener.class))
                .forEach(l->l.onDataAdded(iterationNb, distance));
    }

    void addListener(IChartModelListener l){
        listenerList.add(IChartModelListener.class, l);
    }

    Map<Integer, Double> getDatas() {
        return datas;
    }

    void clear() {
        iterationNb = 0;
        datas.clear();
        Arrays.stream(listenerList.getListeners(IChartModelListener.class))
                .forEach(IChartModelListener::onDataModified);
    }

    public interface IChartModelListener extends EventListener {
        void onDataAdded(Integer iteration, Double distance);
        void onDataModified();
    }
}
