package solutions;

import org.junit.jupiter.api.Test;
import utils.FileUtils;
import utils.tree.Tree;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test {

    @Test
    void regressionTestFirstStar() {
        Tree<String> tree = new Tree<>("COM");

        FileUtils.readLines("day06.txt")
                .forEach(line -> {
                    String[] split = line.split("\\)");
                    tree.addLink(split[0], split[1]);
                });

        int parents = tree.getNumberOfIndirectParents();
        assertEquals(245089, parents);
    }

    @Test
    void regressionTestSecondStar() {
        Tree<String> tree = new Tree<>("COM");

        FileUtils.readLines("day06.txt")
                .forEach(line -> {
                    String[] split = line.split("\\)");
                    tree.addLink(split[0], split[1]);
                });

        int shortestPath = tree.getShortestPath("YOU", "SAN");
        assertEquals(511, shortestPath);
    }
}