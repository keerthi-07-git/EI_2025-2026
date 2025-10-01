package behaviouraldesignpattern.strategy.src;

public class QuickSort implements SortStrategy {
    @Override
    public void sort(int[] arr) {
        System.out.println("QuickSort executed");
    }
}
