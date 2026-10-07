public class HeapTest
{
    public static void main(String[] args)
    {
        MinHeap heap = new MinHeap();

        heap.insert(10);
        heap.insert(3);
        heap.insert(15);

        System.out.println(
            "Min-Heap Extract Minimum Priority Value: "
            + heap.extractMin()
        );
    }
}