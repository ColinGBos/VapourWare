package vapourdrive.vapourware.shared.utils;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class WeightedRandom<T> {
    private final NavigableMap<Double, T> weightMap = new TreeMap<>();
    private final Random random = new Random();
    private double totalWeight = 0.0;

    /**
     * Initialize the generator with a map of items and their weights
     * @param weights HashMap containing items as keys and their weights as values
     */
    public WeightedRandom(Map<T, Double> weights) {
        for (Map.Entry<T, Double> entry : weights.entrySet()) {
            if (entry.getValue() < 0) {
                throw new IllegalArgumentException("Weights cannot be negative");
            }
            totalWeight += entry.getValue();
            weightMap.put(totalWeight, entry.getKey());
        }
    }

    /**
     * Generate a random item based on the weights
     * @return randomly selected item
     */
    public T nextRandomItem() {
        double value = random.nextDouble() * totalWeight;
        return weightMap.higherEntry(value).getValue();
    }

}
