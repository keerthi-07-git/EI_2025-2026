package behaviouraldesignpattern.strategy.src;

public class MergeSort implements SortStrategy {
    @Override
    public void sort(int[] arr) {
        System.out.println("MergeSort executed");
    }
}
