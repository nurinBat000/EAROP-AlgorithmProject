public class MinHeap
{
    private int[] heap;
    private int size;

    public MinHeap()
    {
        heap = new int[50];
        size = 0;
    }

    // Insert a new value into the Min-Heap
    public void insert(int value)
    {
        heap[size] = value;
        int current = size;
        size++;

        // Move the new value upward
        while (current > 0)
        {
            int parent = (current - 1) / 2;

            if (heap[current] < heap[parent])
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

    // Remove and return the minimum value
    public int extractMin()
    {
        if (size == 0)
        {
            return -1;
        }

        int min = heap[0];

        heap[0] = heap[size - 1];
        size--;

        // Move the root downward
        int current = 0;

        while (true)
        {
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            int smallest = current;

            if (left < size && heap[left] < heap[smallest])
            {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest])
            {
                smallest = right;
            }

            if (smallest != current)
            {
                int temp = heap[current];
                heap[current] = heap[smallest];
                heap[smallest] = temp;

                current = smallest;
            }
            else
            {
                break;
            }
        }

        return min;
    }
}