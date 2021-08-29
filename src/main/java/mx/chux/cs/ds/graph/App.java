package mx.chux.cs.ds.graph;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        
        Map<String, Collection<String>> data = 
                new HashMap<String, Collection<String>>(){{
            put("a", Arrays.asList("b", "c"));
            put("b", Arrays.asList("c"));
            put("c", Arrays.asList("d"));
        }};
        
        final Graph<String> graph = new Graph<String>(data);
        
        graph.print();
        
    }
    
}
