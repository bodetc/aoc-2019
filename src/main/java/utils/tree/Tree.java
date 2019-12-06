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
            count += node.getParents().size();
        }
        return count;
    }

    public int getShortestPath(T a, T b) {
        List<Node<T>> parentsA = getOrMakeNode(a).getParents();
        List<Node<T>> parentsB = getOrMakeNode(b).getParents();

        Node<T> commonParent = parentsA.stream()
                .filter(nodeA -> parentsB.stream().anyMatch(nodeB -> nodeB.getValue().equals(nodeA.getValue())))
                .findFirst()
                .orElseThrow();

        return parentsA.indexOf(commonParent)+parentsB.indexOf(commonParent);
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
