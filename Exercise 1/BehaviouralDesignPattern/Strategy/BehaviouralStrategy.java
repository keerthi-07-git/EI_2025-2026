package behaviouraldesignpattern.strategy.src;
public class BehaviouralStrategy {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 9, 1, 7};

        Sorter sorter = new Sorter();

        // Use QuickSort strategy
        sorter.setStrategy(new QuickSort());
        sorter.sortArray(numbers.clone());  // clone to keep original array

        // Switch to MergeSort strategy
        sorter.setStrategy(new MergeSort());
        sorter.sortArray(numbers.clone());
    }
}


