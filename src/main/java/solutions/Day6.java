package solutions;

import utils.FileUtils;
import utils.tree.Tree;

public class Day6 {
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("COM");

        FileUtils.readLines("day6/input.txt")
            .forEach(line -> {
                String[] split = line.split("\\)");
                tree.addLink(split[0], split[1]);
            });

        int parents = tree.getNumberOfIndirectParents();
        System.out.println("Output for first star: " + parents);
    }
}