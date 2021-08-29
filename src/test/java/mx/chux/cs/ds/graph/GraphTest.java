package mx.chux.cs.ds.graph;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphTest {

    private Graph<String> graph;
    
    @Before
    public void initializeGraph() {
        Map<String, Collection<String>> data = 
                new HashMap<String, Collection<String>>(){{
            put("a", Arrays.asList("b", "c"));
            put("b", Arrays.asList("c"));
            put("c", Arrays.asList("d"));
        }};
        
        this.graph = new Graph<>(data);
    }
    
    @Test
    public void correctNumberOfNodesTest() {
        assertThat(this.graph.sizeOfNodes()).isEqualTo(4);
    }
    
    @Test
    public void correctNumberOfVertexTest() {
        assertThat(this.graph.sizeOfVertex()).isEqualTo(8);
    }
    
    @Test
    public void correctNumberOfAdjacenciesTest() {
        assertThat(this.graph.getNode("a").size()).isEqualTo(2);
        assertThat(this.graph.getNode("b").size()).isEqualTo(2);
        assertThat(this.graph.getNode("c").size()).isEqualTo(3);
        assertThat(this.graph.getNode("d").size()).isEqualTo(1);
    }
    
    @Test
    public void correctfAdjacenciesTest() {
        final Node<String> node = this.graph.getNode("c");
        
        List<String> adjacencies = Arrays.asList("a", "b", "d");
        Collection<Node<String>> adjacents = node.getAdjacents();
        
        int matches = 0;
        for( Node<String> adjacent : adjacents ) {
            boolean isContained = adjacencies.contains(adjacent.get());
            assertThat(isContained).isTrue();
            if( isContained ) {
                matches += 1;
            }
        }
        
        assertThat(matches).isEqualTo(adjacencies.size());
    }
    
}
