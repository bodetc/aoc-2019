package utils.tree;

import java.util.*;

public class Tree<T> {
    private final Node<T> root;
    private final Map<T, Node<T>> nodes;

    public Tree(T rootValue) {
        nodes = new HashMap<>();
        root = getOrMakeNode(rootValue);
    }

    public void addLink(T parentData, T childData) {
        Node<T> parent = getOrMakeNode(parentData);
        Node<T> child = getOrMakeNode(childData);

        parent.addChild(child);
        child.setParent(parent);
    }

    public int getNumberOfIndirectParents() {
        int count = 0;
        for (Node<T> node : nodes.values()) {
            while (node.getParent() != null) {
                node = node.getParent();
                count++;
            }
        }
        return count;
    }

    private Node<T> getOrMakeNode(T data) {
        Node<T> node = nodes.get(data);
        if (node == null) {
            node = new Node<>(data);
            nodes.put(data, node);
        }
        return node;
    }
}
