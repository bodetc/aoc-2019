package utils.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    @Test
    public void testGetIndirectDownlinks() {
        Tree<String> tree = new Tree<>("COM");
        tree.addLink("COM", "B");
        tree.addLink("B", "G");
        tree.addLink("G", "H");
        tree.addLink("B", "C");
        tree.addLink("C", "D");
        tree.addLink("D", "I");
        tree.addLink("D", "E");
        tree.addLink("E", "J");
        tree.addLink("J", "K");
        tree.addLink("K", "L");
        tree.addLink("E", "F");

        assertEquals(42, tree.getNumberOfIndirectParents());
    }

    @Test
    public void testGetShortestPath() {
        Tree<String> tree = new Tree<>("COM");
        tree.addLink("COM", "B");
        tree.addLink("B", "G");
        tree.addLink("G", "H");
        tree.addLink("B", "C");
        tree.addLink("C", "D");
        tree.addLink("D", "I");
        tree.addLink("D", "E");
        tree.addLink("E", "J");
        tree.addLink("J", "K");
        tree.addLink("K", "L");
        tree.addLink("E", "F");
        tree.addLink("K", "YOU");
        tree.addLink("I", "SAN");

        assertEquals(4, tree.getShortestPath("YOU", "SAN"));
    }
}