package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {

    private static final String NB_NODES_DEFINITION_TAG = "NB_NODES";
    private static final String DIST_MATRIX_START_TAG = "NODES_DIST_SECTION";
    private static final String DIST_MATRIX_END_TAG = "END_NODES_DIST_SECTION";
    
    private FileParser() {

    }

    public static EnvData loadEnv(String path) throws IOException {
        //TODO impl gestion des différentes versions de TSP
        float env[][][];
        Wrapper<Short> nbNodesWrapper = new Wrapper<>((short)-1);
        List<List<String>> lines = new ArrayList<>();
        Wrapper<Boolean> parseAsDistMatrixLineWrapper = new Wrapper<>(false);

        Files.lines(Paths.get(path)).forEach(str->{
            if(!str.trim().startsWith("#")) {
                if (str.contains(NB_NODES_DEFINITION_TAG)) {
                    nbNodesWrapper.set(Short.parseShort(str.trim().substring(NB_NODES_DEFINITION_TAG.length())));
                } else if (str.contains(DIST_MATRIX_START_TAG)) {
                    parseAsDistMatrixLineWrapper.set(true);
                } else if (str.contains(DIST_MATRIX_END_TAG)){
                    parseAsDistMatrixLineWrapper.set(false);
                }else if(parseAsDistMatrixLineWrapper.get()){
                    String data[] = str.split("\\s");
                    List<String> listData = Arrays.asList(data);
                    if(listData.size()!=nbNodesWrapper.get())
                        throw new IllegalArgumentException("expected "+nbNodesWrapper.get()+" nodes, got "+listData.size());
                    else
                        lines.add(listData);
                }
            }
        });

        if(nbNodesWrapper.get()>0) {
            env = new float[nbNodesWrapper.get()][nbNodesWrapper.get()][2];
            for (short i = 0; i < nbNodesWrapper.get(); i++) {
                for (short j = 0; j < nbNodesWrapper.get(); j++) {
                    env[i][j][0] = Float.parseFloat(lines.get(i).get(j));
                    env[i][j][1] = 0;
                }
            }
        }else{
            throw new IllegalStateException("number of nodes invalid : "+nbNodesWrapper.get());
        }
        return new EnvData(env, nbNodesWrapper.get(), 0);
    }

    private static class Wrapper<T>{
        private T value;

        Wrapper(T value) {
            this.value = value;
        }

        public T get(){
            return value;
        }

        public void set(T value){
            this.value = value;
        }
    }


}
