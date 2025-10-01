package behaviouraldesignpattern.strategy.src;

public class BehaviouralStrategy {
    public static void main(String[] args) {
        int[] nums = {3, 1, 2};
        Sorter s = new Sorter();

        s.setStrategy(new QuickSort());
        s.sort(nums);

        s.setStrategy(new MergeSort());
        s.sort(nums);
    }
}
