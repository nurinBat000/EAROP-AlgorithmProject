import java.util.*;
public class EmergencyAmbulanceOptimization
{
    // =========================================================
    // 3.3 DYNAMIC PROGRAMMING - ELYSA
    // =========================================================

    public static String dynamicProgrammingEAROP(int[][] dist)
    {
        int n = dist.length;
        int VISITED_ALL = (1 << n) - 1;

        int[][] memo = new int[n][1 << n];
        String[][] paths = new String[n][1 << n];

        // Initialize memo and paths
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < (1 << n); j++)
            {
                memo[i][j] = -1;
                paths[i][j] = "";
            }
        }

        // Start from Hospital Headquarters
        int cost = dynamicProgrammingEAROPHelper(
            0,
            1,
            dist,
            memo,
            VISITED_ALL,
            paths
        );

        return "Dynamic Programming Route: "
            + "Hospital Headquarters (H) -> "
            + paths[0][1]
            + " -> Hospital Headquarters (H)"
            + " | Total Cost: "
            + cost;
    }


    // Dynamic Programming Helper
    private static int dynamicProgrammingEAROPHelper(
        int pos,
        int mask,
        int[][] dist,
        int[][] memo,
        int VISITED_ALL,
        String[][] paths)
    {
        // All locations have been visited
        if (mask == VISITED_ALL)
        {
            return dist[pos][0];
        }

        // Return stored result if already calculated
        if (memo[pos][mask] != -1)
        {
            return memo[pos][mask];
        }

        int n = dist.length;
        int minCost = Integer.MAX_VALUE;
        String bestPath = "";

        // Try every unvisited location
        for (int next = 0; next < n; next++)
        {
            if ((mask & (1 << next)) == 0)
            {
                int newMask = mask | (1 << next);

                int cost = dist[pos][next]
                    + dynamicProgrammingEAROPHelper(
                        next,
                        newMask,
                        dist,
                        memo,
                        VISITED_ALL,
                        paths
                    );

                if (cost < minCost)
                {
                    minCost = cost;

                    String nextPath = paths[next][newMask];

                    bestPath = getLocationName(next)
                        + (nextPath.isEmpty()
                            ? ""
                            : " -> " + nextPath);
                }
            }
        }

        // Store the result
        memo[pos][mask] = minCost;
        paths[pos][mask] = bestPath;

        return minCost;
    }


    // Get location name
    private static String getLocationName(int index)
    {
        String[] locations = {
            "Hospital Headquarters (H)",
            "Emergency Location E1",
            "Emergency Location E2",
            "Emergency Location E3"
        };

        return locations[index];

    // =========================================================
    // 3.5 SORTING & SEARCHING - ELYSA
    // =========================================================

    // 3.5.1 Insertion Sort
    public static void insertionSort(int[] arr)
    {
        for (int i = 1; i < arr.length; i++)
        {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key)
            {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }


    // 3.5.2 Binary Search
    public static int binarySearch(
        int[] arr,
        int target)
    {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right)
        {
            int mid =
                left + (right - left) / 2;

            if (arr[mid] == target)
            {
                return mid;
            }

            if (arr[mid] < target)
            {
                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }

        return -1;
    }

    // Demonstration for Elysa's sections
    public static void main(String[] args)
    {
        int[][] costMatrix = {
            { 0, 15, 25, 35 },
            { 15, 0, 30, 28 },
            { 25, 30, 0, 20 },
            { 35, 28, 20, 0 }
        };

        System.out.println(dynamicProgrammingEAROP(costMatrix));

        int[] data = {8, 3, 5, 1, 9, 2};
        System.out.println("Original Data: " + Arrays.toString(data));
        insertionSort(data);
        System.out.println("After Insertion Sort: " + Arrays.toString(data));
        int searchResult = binarySearch(data, 5);
        System.out.println("Binary Search for 5: index " + searchResult);
    }
}
