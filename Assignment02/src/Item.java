public class Item {
    private final String name;
    private final int worth;
    private final int weight;

    public Item(String name, int worth, int weight) {
        this.name = name;
        this.worth = worth;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWorth() {
        return worth;
    }

    public int getWeight() {
        return weight;
    }

    public double getValueWeightRatio() {
        return (double) worth / weight;
    }

    @Override
    public String toString() {
        return name + " (worth=" + worth + ", weight=" + weight + "g, ratio="
                + String.format("%.4f", getValueWeightRatio()) + ")";
    }
}
