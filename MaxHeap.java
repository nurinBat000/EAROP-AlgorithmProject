public class MaxHeap
{
    private int[] heap;
    private int size;

    public MaxHeap()
    {
        heap = new int[50];
        size = 0;
    }

    // Insert a new value into the Max-Heap
    public void insert(int value)
    {
        heap[size] = value;
        int current = size;
        size++;

        // Move the new value upward
        while (current > 0)
        {
            int parent = (current - 1) / 2;

            if (heap[current] > heap[parent])
            {
                int temp = heap[current];
                heap[current] = heap[parent];
                heap[parent] = temp;

                current = parent;
            }
            else
            {
                break;
            }
        }
    }

    // Remove and return the maximum value
    public int extractMax()
    {
        if (size == 0)
        {
            return -1;
        }

        int max = heap[0];

        heap[0] = heap[size - 1];
        size--;

        // Move the root downward
        int current = 0;

        while (true)
        {
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            int largest = current;

            if (left < size && heap[left] > heap[largest])
            {
                largest = left;
            }

            if (right < size && heap[right] > heap[largest])
            {
                largest = right;
            }

            if (largest != current)
            {
                int temp = heap[current];
                heap[current] = heap[largest];
                heap[largest] = temp;

                current = largest;
            }
            else
            {
                break;
            }
        }

        return max;
    }
}