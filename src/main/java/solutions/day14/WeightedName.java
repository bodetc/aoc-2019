package solutions.day14;

import utils.Reject;

class WeightedName {
    final int amount;
    final String name;

    WeightedName(String input) {
        String[] split = input.split(" ");
        Reject.ifFalse(split.length == 2);
        this.amount = Integer.parseInt(split[0]);
        this.name = split[1];
    }
}
