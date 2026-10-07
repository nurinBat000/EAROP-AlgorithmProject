import java.util.PriorityQueue;

public class NurinHeap
{
    public static void main(String[] args)
    {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(10);
        minHeap.add(3);
        minHeap.add(15);

        System.out.println("=== MIN-HEAP TEST ===");
        System.out.println("Values inserted: 10, 3, 15");
        System.out.println("Min-Heap Extract Min: " + minHeap.poll());
    }
}