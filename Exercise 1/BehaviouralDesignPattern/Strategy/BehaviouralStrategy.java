package behaviouraldesignpattern.strategy.src;
public class BehaviouralStrategy {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 9, 1, 7};

        Sorter sorter = new Sorter();

        sorter.setStrategy(new QuickSort());
        sorter.sortArray(numbers.clone());  

        sorter.setStrategy(new MergeSort());
        sorter.sortArray(numbers.clone());
    }
}



