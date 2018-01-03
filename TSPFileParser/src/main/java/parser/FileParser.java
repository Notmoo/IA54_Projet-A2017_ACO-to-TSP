package parser;

import javafx.util.Pair;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileParser {

    private static final String NB_NODES_DEFINITION_TAG = "NB_NODES";
    private static final String DIST_MATRIX_START_TAG = "NODES_DIST_SECTION",
            DIST_MATRIX_END_TAG = "END_NODES_DIST_SECTION";
    private static final String VERSION_TAG = "TSP_VERSION";
    private static final String TIME_WINDOW_LIST_START_TAG = "NODE_TIME_SECTION",
            TIME_WINDOW_LIST_END_TAG = "END_NODE_TIME_SECTION",
            TIME_WINDOW_EMPTY_VALUE_TAG = "NONE";
    private static final String INITIAL_TIME_TAG = "TSP_INITIAL_TIME";
    
    private FileParser() {

    }

    public static EnvData loadEnv(String path) throws Exception {
        float env[][][];
        Map<Short, List<Pair<Float,Float>>> timeWindow = new HashMap<>();

        Wrapper<Short> currentTimeWindowIndex = new Wrapper<>((short)0);
        Wrapper<Float> initialTimeWrapper = new Wrapper<>(0f);

        Wrapper<Short> nbNodesWrapper = new Wrapper<>((short)-1);

        List<List<String>> distLines = new ArrayList<>();
        Wrapper<Integer> tspVersionWrapper = new Wrapper<>(0);

        Wrapper<Boolean> parseAsDistMatrixLineWrapper = new Wrapper<>(false);
        Wrapper<Boolean> parseAsTimeWindowMatrixLineWrapper = new Wrapper<>(false);

        Files.lines(Paths.get(path)).forEach(str->{
            if(!str.trim().startsWith("#")) {
                if (str.contains(DIST_MATRIX_START_TAG)) {
                    parseAsDistMatrixLineWrapper.set(true);
                }else if (str.contains(DIST_MATRIX_END_TAG)){
                    parseAsDistMatrixLineWrapper.set(false);
                }else if (str.contains(TIME_WINDOW_LIST_START_TAG)) {
                    parseAsTimeWindowMatrixLineWrapper.set(true);
                }else if (str.contains(TIME_WINDOW_LIST_END_TAG)){
                    parseAsTimeWindowMatrixLineWrapper.set(false);
                }else if(parseAsDistMatrixLineWrapper.get()){
                    String data[] = str.split("\\s");
                    List<String> listData = Arrays.asList(data);
                    if(listData.size()!=nbNodesWrapper.get())
                        throw new IllegalArgumentException("expected "+nbNodesWrapper.get()+" nodes, got "+listData.size());
                    else
                        distLines.add(listData);
                }else if(parseAsTimeWindowMatrixLineWrapper.get()){
                    List<String> datas = Arrays.asList(str.split("\\s"));
                    for(String data : datas){
                        if(!data.equals(TIME_WINDOW_EMPTY_VALUE_TAG)) {
                            String parts[] = data.split("-");
                            Pair<Float, Float> tw = new Pair<>(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]));

                            if (!timeWindow.containsKey(currentTimeWindowIndex.get())) {
                                timeWindow.put(currentTimeWindowIndex.get(), new ArrayList<>());
                            }
                            timeWindow.get(currentTimeWindowIndex.get()).add(tw);
                        }else{
                            timeWindow.get(currentTimeWindowIndex.get()).add(new Pair<>(Float.MIN_VALUE, Float.MAX_VALUE));
                        }
                    }
                    currentTimeWindowIndex.set((short)(currentTimeWindowIndex.get()+1));
                }else if (str.contains(NB_NODES_DEFINITION_TAG)) {
                    nbNodesWrapper.set(Short.parseShort(str.trim().substring(NB_NODES_DEFINITION_TAG.length()+1)));
                }else if(str.contains(VERSION_TAG)){
                    tspVersionWrapper.set(Integer.parseInt(str.trim().substring(VERSION_TAG.length()+1)));
                }else if(str.contains(INITIAL_TIME_TAG)){
                    initialTimeWrapper.set(Float.parseFloat(str.trim().substring(INITIAL_TIME_TAG.length()+1)));
                }
            }
        });

        if(nbNodesWrapper.get()>0) {
            env = new float[nbNodesWrapper.get()][nbNodesWrapper.get()][2];
            for (short i = 0; i < nbNodesWrapper.get(); i++) {
                for (short j = 0; j < nbNodesWrapper.get(); j++) {
                    env[i][j][0] = Float.parseFloat(distLines.get(i).get(j));
                    env[i][j][1] = 0;
                }
            }
        }else{
            throw new IllegalStateException("number of nodes invalid : "+nbNodesWrapper.get());
        }
        return new EnvData(env, nbNodesWrapper.get(), tspVersionWrapper.get(), timeWindow, initialTimeWrapper.get());
    }

    private static class Wrapper<T>{
        private T value;

        Wrapper(T value) {
            this.value = value;
        }

        T get(){
            return value;
        }

        void set(T value){
            this.value = value;
        }
    }


}
