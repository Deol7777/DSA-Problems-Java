public class Main {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian()); // -1.0

        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian()); // -1.5

        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian()); // -2.0

        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian()); // -2.5

        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian()); // -3.0
    }
}
