package behaviouraldesignpattern.strategy.src;

public class Sorter {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy s) {
        strategy = s;
    }

    public void sort(int[] arr) {
        strategy.sort(arr);
    }
}
