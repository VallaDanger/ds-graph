package mx.chux.cs.ds.graph;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        
        Map<String, Collection<String>> data = new HashMap<>();
        
        data.put("a", Arrays.asList("b", "c"));
        data.put("b", Arrays.asList("c"));
        data.put("c", Arrays.asList("d"));
        
        final Graph<String> graph = new Graph<>(data);
        
        graph.print();
        
    }
    
}
