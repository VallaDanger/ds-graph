package mx.chux.cs.ds.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class Graph<E extends Comparable<E>> {
    
    private static final Logger LOGGER = Logger.getLogger(Graph.class.getName());

    private List<Node<E>> nodes;
    
    public Graph(Map<E, Collection<E>> graph) {
        
        this.nodes = new ArrayList<Node<E>>(graph.size());
        
        // initialize with keys
        initialize(graph.keySet());
        
        // build connections including the ones not declared
        buildConnections(graph);
        
        // ensure that connections are both-ways
        enforceConnections();
        
    }
    
    private void initialize(Set<E> nodes) {
        for( E node : nodes ) {
            this.nodes.add(new Node<>(node));
        }
    }

    private void buildConnections(Map<E, Collection<E>> graph) {
        final ListIterator<Node<E>> iterator = this.nodes.listIterator();
        while( iterator.hasNext() ) {
            final Node<E> node = iterator.next();
            List<Node<E>> missingNodes = buildAdjacencies(node, graph.get(node.get()));
            for( Node<E> missingNode : missingNodes ) {
                iterator.add(missingNode);
            }
        }
    }
    
    private void enforceConnections( ) {
        for( Node<E> node : this.nodes ) {
            for( Node<E> adjacent : node.getAdjacents() ) {
                if( !isAdjacencyComplete(node, adjacent) ) {
                    adjacent.addAdjacentNode(node);
                }
            }
        }
    }
    
    private List<Node<E>> buildAdjacencies(Node<E> node, final Collection<E> connections) { 
        
        List<Node<E>> missingNodes = new ArrayList<>();
        
        final List<Node<E>> adjacents = buildAdjacencies(connections);
        
        for( Node<E> adjacent : adjacents ) {
            if( !this.nodes.contains(adjacent) ) {
                missingNodes.add(adjacent);
            }
        }

        node.addAdjacentNodes(adjacents);
        
        return missingNodes;
    }
    
    private List<Node<E>> buildAdjacencies(final Collection<E> adjacents) {
        
        final List<Node<E>> n = new ArrayList<>(adjacents.size());
        
        for( E adjacent : adjacents ) {
            Node<E> node = getOrBuildNode(adjacent);
            n.add(node);
        }
        
        return n;
    }
    
    private Node<E> getOrBuildNode(E value) {
        Node<E> node = getNode(value);
        if( node == null ) {
            node = new Node<>(value);
        }
        return node;
    }
    
    private boolean isAdjacencyComplete(Node<E> x, Node<E> y) {
        return x.isAdjacentTo(y) && y.isAdjacentTo(x);
    }
    
    Node<E> getNode(E value) {
        for(Node<E> node : this.nodes) {
            if( node.get().equals(value) ) {
                return node;
            }
        } 
        return null;
    }
    
    public int sizeOfNodes() {
        return this.nodes.size();
    }
    
    public int sizeOfVertex() {
        int size = 0;
        for( Node<E> node : this.nodes ) {
            size += node.size();
        }
        return size;
    }
    
    public void print() {
        final String stringRepresentation = this.toString();
        LOGGER.info(stringRepresentation);
    }
    
    @Override
    public String toString() {
        
        final StringBuilder builder = new StringBuilder("\n");
        
        for( Node<E> node : nodes ) {
            builder.append(node).append("\n");
        }
        
        return builder.toString();
    }
    
}
