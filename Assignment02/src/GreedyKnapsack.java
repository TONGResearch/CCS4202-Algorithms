import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyKnapsack {
    public static class Result {
        private final List<Item> selectedItems;
        private final int totalWeight;
        private final int totalWorth;
        private final long executionTimeNanos;

        public Result(List<Item> selectedItems, int totalWeight, int totalWorth, long executionTimeNanos) {
            this.selectedItems = selectedItems;
            this.totalWeight = totalWeight;
            this.totalWorth = totalWorth;
            this.executionTimeNanos = executionTimeNanos;
        }

        public List<Item> getSelectedItems() {
            return selectedItems;
        }

        public int getTotalWeight() {
            return totalWeight;
        }

        public int getTotalWorth() {
            return totalWorth;
        }

        public long getExecutionTimeNanos() {
            return executionTimeNanos;
        }
    }

    public Result solve(List<Item> items, int capacity) {
        long startTime = System.nanoTime();

        List<Item> sortedItems = new ArrayList<>(items);
        sortedItems.sort(
                Comparator.comparingDouble(Item::getValueWeightRatio)
                        .reversed()
                        .thenComparing(Comparator.comparingInt(Item::getWorth).reversed())
                        .thenComparing(Item::getName)
        );

        List<Item> selectedItems = new ArrayList<>();
        int totalWeight = 0;
        int totalWorth = 0;

        for (Item item : sortedItems) {
            if (totalWeight + item.getWeight() <= capacity) {
                selectedItems.add(item);
                totalWeight += item.getWeight();
                totalWorth += item.getWorth();
            }
        }

        long executionTime = System.nanoTime() - startTime;
        return new Result(selectedItems, totalWeight, totalWorth, executionTime);
    }
}
