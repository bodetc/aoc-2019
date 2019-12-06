package utils.tree;

import java.util.ArrayList;
import java.util.List;

class Node<T> {
    private final T value;
    private final List<Node<T>> children;
    private Node<T> parent;

    Node(T value) {
        this.value = value;
        children = new ArrayList<>();
        parent = null;
    }

    void addChild(Node<T> child) {
        children.add(child);
    }

    void setParent(Node<T> parent) {
        if(this.parent != null && this.parent.value != parent.value) {
            throw new IllegalStateException("A node cannot have multiple parents!");
        }
        this.parent = parent;
    }

    Node<T> getParent() {
        return parent;
    }
}
