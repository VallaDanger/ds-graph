package mx.chux.cs.ds.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class Node<E extends Comparable<E>> implements Supplier<E> {

    private final E value;
    private boolean visited = false;
    
    private Set<Node<E>> adjacents;
    
    public Node(E value) {
        this.value = value;
        this.visited = false;
        this.adjacents = new HashSet<>();
    }
    
    public Node<E> addAdjacentNode(final Node<E> node) {
        this.adjacents.add(node);
        return this;
    }
    
    public Node<E> addAdjacentNodes(final Collection<Node<E>> nodes) {
        this.adjacents.addAll(nodes);
        return this;
    }
    
    public void setVisited(final boolean visited) {
        this.visited = visited;
    }
    
    public boolean hasBeenVisited() {
        return this.visited;
    }
    
    public int size() {
        return this.adjacents.size();
    }
    
    boolean isAdjacentTo(Node<E> node) {
        return this.adjacents.contains(node);
    }
    
    boolean isAdjacentTo(E value) {
        for(Node<E> adjacent : adjacents) {
            if( adjacent.value.equals(value) ) {
                return true;
            }
        }
        return false;
    }
    
    Collection<Node<E>> getAdjacents() {
        return Collections.unmodifiableCollection(this.adjacents);
    }
    
    @Override
    public E get() {
        return this.value;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        
        builder.append(value).append(" -> ");
        
        for( Node<E> adjacent : adjacents ) {
            builder.append(adjacent.get()).append("(v: ")
            .append(this.hasBeenVisited()).append("), ");
        }
        
        final String stringRepresentation = builder.toString();
        
        return stringRepresentation;
    }
    
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
    
    @Override 
    public boolean equals(Object other) {
        if( other instanceof Node<?> ) {
            return ((Node<?>) other).value.equals(this.value);
        }
        return false;
    }
    
}
