import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int CAPACITY_GRAMS = 4000;

    public static void main(String[] args) throws IOException {
        List<Item> items = createItems();

        GreedyKnapsack.Result greedyResult = new GreedyKnapsack().solve(items, CAPACITY_GRAMS);
        DynamicProgrammingKnapsack.Result dpResult = new DynamicProgrammingKnapsack().solve(items, CAPACITY_GRAMS);

        printGreedySolution(greedyResult);
        System.out.println();
        printDynamicProgrammingSolution(dpResult);

        writeComparisonTable(greedyResult, dpResult);
    }

    private static List<Item> createItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Lenovo X1 Carbon (5th Gen)", 40, 112));
        items.add(new Item("10 pairs thongs", 39, 80));
        items.add(new Item("5 Underarmour Strappy", 38, 305));
        items.add(new Item("1 pair Uniqlo leggings", 37, 185));
        items.add(new Item("2 Lululemon Cool Racerback", 36, 174));
        items.add(new Item("Chargers and cables in Mini Bomber Travel Kit", 35, 665));
        items.add(new Item("The Roost Stand", 34, 170));
        items.add(new Item("ThinkPad Compact Bluetooth Keyboard with trackpoint", 33, 460));
        items.add(new Item("Seagate Backup PlusSlim", 32, 159));
        items.add(new Item("1 pair black denim shorts", 31, 197));
        items.add(new Item("2 pairs Nike Pro shorts", 30, 112));
        items.add(new Item("2 pairs Lululemon shorts", 29, 184));
        items.add(new Item("Isabella T-Strap Croc sandals", 28, 200));
        items.add(new Item("2 Underarmour HeatGear CoolSwitch tank tops", 27, 138));
        items.add(new Item("5 pairs black socks", 26, 95));
        items.add(new Item("2 pairs Injinji Women's Run Lightweight No-Show Toe Socks", 25, 54));
        items.add(new Item("1 fancy tank top", 24, 71));
        items.add(new Item("1 light and stretchylong-sleeve shirt (Gap Fit)", 23, 147));
        items.add(new Item("Uniqlo Ultralight Down insulating jacket", 22, 235));
        items.add(new Item("Patagonia Torrentshell", 21, 301));
        items.add(new Item("Lightweight Merino Wool Buff", 20, 50));
        items.add(new Item("1 LBD (H&M)", 19, 174));
        items.add(new Item("Field Notes Pitch Black Memo Book Dot-Graph", 18, 68));
        items.add(new Item("Innergie PocketCell USB-C 6000mAh power bank", 17, 14));
        items.add(new Item("Important papers", 16, 228));
        items.add(new Item("Deuter First Aid Kit Active", 15, 144));
        items.add(new Item("Stanley Classic Vacuum Camp Mug 16oz", 14, 454));
        items.add(new Item("JBL Reflect Mini Bluetooth Sport Headphones", 13, 14));
        items.add(new Item("Anker SoundCore nano Bluetooth Speaker", 12, 89));
        items.add(new Item("Oakley Latch Sunglasses", 11, 30));
        items.add(new Item("Ray Ban Wayfarer Classic", 10, 45));
        items.add(new Item("Zip bag of toiletries", 9, 236));
        items.add(new Item("Petzl E+LITE Emergency Headlamp", 8, 27));
        items.add(new Item("Laptop Bag", 7, 20));
        items.add(new Item("Peak Design Cuff Camera Wrist Strap", 6, 26));
        items.add(new Item("Travelon Micro Scale", 5, 125));
        items.add(new Item("BlitzWolf Bluetooth Tripod/Monopod", 4, 150));
        items.add(new Item("Humangear GoBites Duo", 3, 22));
        items.add(new Item("Touchlight", 2, 10));
        items.add(new Item("Vapur Bottle 1L", 1, 41));
        return items;
    }

    private static void printGreedySolution(GreedyKnapsack.Result result) {
        System.out.println("GREEDY SOLUTION");
        printSelectedItems(result.getSelectedItems());
        System.out.println("Total weight: " + result.getTotalWeight() + "g");
        System.out.println("Total worth: " + result.getTotalWorth());
        System.out.println("Execution time: " + formatNanos(result.getExecutionTimeNanos()));
    }

    private static void printDynamicProgrammingSolution(DynamicProgrammingKnapsack.Result result) {
        System.out.println("DYNAMIC PROGRAMMING SOLUTION");
        printSelectedItems(result.getSelectedItems());
        System.out.println("Total weight: " + result.getTotalWeight() + "g");
        System.out.println("Total worth: " + result.getTotalWorth());
        System.out.println("Execution time: " + formatNanos(result.getExecutionTimeNanos()));
    }

    private static void printSelectedItems(List<Item> selectedItems) {
        System.out.println("Selected items:");
        for (int i = 0; i < selectedItems.size(); i++) {
            Item item = selectedItems.get(i);
            System.out.printf("%2d. %s | Worth: %d | Weight: %dg | Ratio: %.4f%n",
                    i + 1,
                    item.getName(),
                    item.getWorth(),
                    item.getWeight(),
                    item.getValueWeightRatio());
        }
    }

    private static void writeComparisonTable(
            GreedyKnapsack.Result greedyResult,
            DynamicProgrammingKnapsack.Result dpResult
    ) throws IOException {
        String table = "Metric\tGreedy\tDynamic Programming%n"
                + "Total Weight\t%dg\t%dg%n"
                + "Total Worth\t%d\t%d%n"
                + "Execution Time\t%s\t%s%n";

        String content = String.format(
                table,
                greedyResult.getTotalWeight(),
                dpResult.getTotalWeight(),
                greedyResult.getTotalWorth(),
                dpResult.getTotalWorth(),
                formatNanos(greedyResult.getExecutionTimeNanos()),
                formatNanos(dpResult.getExecutionTimeNanos())
        );

        Path outputPath = Path.of("comparison_table.txt");
        Files.writeString(outputPath, content);
        System.out.println();
        System.out.println("Comparison table generated: " + outputPath.toAbsolutePath());
    }

    private static String formatNanos(long nanos) {
        return String.format("%d ns (%.3f ms)", nanos, nanos / 1_000_000.0);
    }
}
