package behaviouraldesignpattern.strategy.src;

public class Sorter {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortArray(int[] arr) {
        if (strategy == null) {
            throw new IllegalStateException("No strategy set!");
        }
        strategy.sort(arr);
    }
}
