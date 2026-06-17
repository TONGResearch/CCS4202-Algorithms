import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DynamicProgrammingKnapsack {
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

        int itemCount = items.size();
        int[][] dp = new int[itemCount + 1][capacity + 1];

        for (int i = 1; i <= itemCount; i++) {
            Item currentItem = items.get(i - 1);
            for (int weight = 0; weight <= capacity; weight++) {
                if (currentItem.getWeight() <= weight) {
                    int includeWorth = currentItem.getWorth() + dp[i - 1][weight - currentItem.getWeight()];
                    int excludeWorth = dp[i - 1][weight];
                    dp[i][weight] = Math.max(includeWorth, excludeWorth);
                } else {
                    dp[i][weight] = dp[i - 1][weight];
                }
            }
        }

        List<Item> selectedItems = new ArrayList<>();
        int remainingCapacity = capacity;
        int totalWeight = 0;

        for (int i = itemCount; i > 0; i--) {
            if (dp[i][remainingCapacity] != dp[i - 1][remainingCapacity]) {
                Item selectedItem = items.get(i - 1);
                selectedItems.add(selectedItem);
                totalWeight += selectedItem.getWeight();
                remainingCapacity -= selectedItem.getWeight();
            }
        }

        Collections.reverse(selectedItems);
        long executionTime = System.nanoTime() - startTime;

        return new Result(selectedItems, totalWeight, dp[itemCount][capacity], executionTime);
    }
}
