public class MaxHeapTest
{
    public static void main(String[] args)
    {
        MaxHeap heap = new MaxHeap();

        heap.insert(10);
        heap.insert(3);
        heap.insert(15);

        System.out.println(
            "Max-Heap Extract Maximum Priority Value: "
            + heap.extractMax()
        );
    }
}