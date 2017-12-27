package gui.chart;

import javax.swing.event.EventListenerList;
import java.util.*;

public class ChartModel {

    private Integer iterationNb;
    private EventListenerList listenerList;
    private Map<Integer, Long> datas;

    public ChartModel() {
        iterationNb = 0;
        listenerList = new EventListenerList();
        datas = new HashMap<>();
    }

    public void addData(long distance){
        datas.put(++iterationNb, distance);
        Arrays.stream(listenerList.getListeners(IChartModelListener.class))
                .forEach(l->l.onDataAdded(iterationNb, distance));
    }

    public void addListener(IChartModelListener l){
        listenerList.add(IChartModelListener.class, l);
    }

    public Integer getCurrentIterationNumber(){
        return iterationNb;
    }

    public Map<Integer, Long> getDatas() {
        return datas;
    }

    public void clear() {
        //TODO clear chart
    }

    public interface IChartModelListener extends EventListener {
        void onDataAdded(Integer iteration, Long distance);
        void onDataModified();
    }
}
