package solutions;

import utils.FileUtils;
import utils.tree.Tree;

public class Day06 {
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("COM");

        FileUtils.readLines("day06.txt")
            .forEach(line -> {
                String[] split = line.split("\\)");
                tree.addLink(split[0], split[1]);
            });

        int parents = tree.getNumberOfIndirectParents();
        System.out.println("Output for first star: " + parents);

        int shortestPath = tree.getShortestPath("YOU", "SAN");
        System.out.println("Output for second star: " + shortestPath);
    }
}
