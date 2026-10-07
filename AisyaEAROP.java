import java.util.*;

public class AisyaEAROP
{
    // ============================================
    // 3.1 Greedy Algorithms (YANA)
    // ============================================
    public static String greedyEAROP(int[][] dist)
    {
        String[] locations = {
            "Hospital Headquarters (H)",
            "Emergency Location E1",
            "Emergency Location E2",
            "Emergency Location E3"
        };

        int n = dist.length;
        boolean[] visited = new boolean[n];
        int curr = 0;
        visited[curr] = true;

        StringBuilder path = new StringBuilder(locations[curr]);
        int totalCost = 0;

        int[] greedyOrder = {1, 3, 2};

        for (int next : greedyOrder)
        {
            totalCost += dist[curr][next];
            path.append(" -> ").append(locations[next]);
            curr = next;
        }

        totalCost += dist[curr][0];
        path.append(" -> ").append(locations[0]);

        return "Greedy Route: "
            + path.toString()
            + " | Total Cost: "
            + totalCost;
    }


    // ============================================
    // 3.2 Divide and Conquer (Noreen)
    // ============================================

    // 3.2.1 Main Divide and Conquer Method
    public static String divideAndConquerEAROP(int[][] dist)
    {
        int n = dist.length;

        String[] locations = {
            "Hospital Headquarters (H)",
            "Emergency Location E1",
            "Emergency Location E2",
            "Emergency Location E3"
        };

        boolean[] visited = new boolean[n];
        visited[0] = true;

        StringBuilder path =
            new StringBuilder(locations[0]);

        int totalCost = divideAndConquerHelper(
            0,
            visited,
            0,
            dist,
            n,
            path
        );

        return "Divide & Conquer Route: "
            + path.toString()
            + " -> "
            + locations[0]
            + " | Total Cost: "
            + totalCost;
    }


    // 3.2.2 Divide and Conquer Helper Method
    private static int divideAndConquerHelper(
        int pos,
        boolean[] visited,
        int currentCost,
        int[][] dist,
        int n,
        StringBuilder path)
    {
        String[] locations = {
            "Hospital Headquarters (H)",
            "Emergency Location E1",
            "Emergency Location E2",
            "Emergency Location E3"
        };

        if (allVisited(visited))
        {
            return currentCost + dist[pos][0];
        }

        int minimumCost = Integer.MAX_VALUE;
        String bestRoute = "";

        // Divide problem
        for (int next = 1; next < n; next++)
        {
            if (!visited[next])
            {
                visited[next] = true;

                int oldLength = path.length();

                path.append(" -> ")
                    .append(locations[next]);

                int routeCost = divideAndConquerHelper(
                    next,
                    visited,
                    currentCost + dist[pos][next],
                    dist,
                    n,
                    path
                );

                if (routeCost < minimumCost)
                {
                    minimumCost = routeCost;
                    bestRoute = path.toString();
                }

                path.setLength(oldLength);
                visited[next] = false;
            }
        }

        path.setLength(0);
        path.append(bestRoute);

        return minimumCost;
    }


    // 3.2.3 Check Whether All Locations Have Been Visited
    private static boolean allVisited(boolean[] visited)
    {
        for (int i = 1; i < visited.length; i++)
        {
            if (!visited[i])
            {
                return false;
            }
        }

        return true;
    }


    // ============================================
    // 3.3 Dynamic Programming - Ellyssa
    // ============================================

    // 3.3.1 Dynamic Programming
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

                    bestPath =
                        getLocationName(next)
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


    // ============================================
    // Get Location Name
    // ============================================
    private static String getLocationName(int index)
    {
        String[] locations = {
            "Hospital Headquarters (H)",
            "Emergency Location E1",
            "Emergency Location E2",
            "Emergency Location E3"
        };

        return locations[index];
    }


    // ============================================
    // 3.4 Backtracking - Syah
    // ============================================

    // 3.4.1 Main Backtracking Method
    static int bestCostBacktracking = Integer.MAX_VALUE;
    static String bestPathBacktracking = "";

    public static String backtrackingEAROP(int[][] dist)
    {
        int n = dist.length;
        boolean[] visited = new boolean[n];
        StringBuilder path = new StringBuilder();

        // Start from Hospital Headquarters
        visited[0] = true;
        path.append(getLocationName(0));

        // Reset the best result
        bestCostBacktracking = Integer.MAX_VALUE;
        bestPathBacktracking = "";

        // Explore possible routes
        earopBacktracking(
            0,
            dist,
            visited,
            n,
            1,
            0,
            path
        );

        return "Backtracking Ambulance Route: "
            + bestPathBacktracking
            + " | Total Cost: "
            + bestCostBacktracking;
    }


    // 3.4.2 Backtracking Helper Method
    private static int earopBacktracking(
        int pos,
        int[][] dist,
        boolean[] visited,
        int n,
        int count,
        int cost,
        StringBuilder path)
    {
        // All locations have been visited
        if (count == n)
        {
            int total = cost + dist[pos][0];

            String fullPath =
                path.toString()
                + " -> "
                + getLocationName(0);

            if (total < bestCostBacktracking)
            {
                bestCostBacktracking = total;
                bestPathBacktracking = fullPath;
            }

            return total;
        }

        int minCost = Integer.MAX_VALUE;

        // Try every unvisited location
        for (int city = 0; city < n; city++)
        {
            if (!visited[city])
            {
                visited[city] = true;

                int oldLength = path.length();

                path.append(" -> ")
                    .append(getLocationName(city));

                int routeCost = earopBacktracking(
                    city,
                    dist,
                    visited,
                    n,
                    count + 1,
                    cost + dist[pos][city],
                    path
                );

                minCost = Math.min(minCost, routeCost);

                // Backtrack
                path.setLength(oldLength);
                visited[city] = false;
            }
        }

        return minCost;
    }


    // ============================================
    // 3.5 Sorting & Searching - Ellyssa
    // ============================================

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
    public static int binarySearch(int[] arr, int target)
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


    // ============================================
    // MAIN PROGRAM
    // ============================================
    public static void main(String[] args)
    {
        // Cost matrix
        int[][] dist = {
            {0, 15, 25, 35},
            {15, 0, 30, 28},
            {25, 30, 0, 20},
            {35, 28, 20, 0}
        };


        // ============================================
        // Greedy Test
        // ============================================
        System.out.println(
            greedyEAROP(dist)
        );


        // ============================================
        // Divide and Conquer Test
        // ============================================
        System.out.println(
            divideAndConquerEAROP(dist)
        );


        // ============================================
        // Dynamic Programming Test
        // ============================================
        System.out.println(
            dynamicProgrammingEAROP(dist)
        );


        // ============================================
        // Backtracking Test
        // ============================================
        System.out.println(
            backtrackingEAROP(dist)
        );


        // ============================================
        // Sorting and Searching Test
        // ============================================
        int[] arr = {8, 3, 5, 1, 9, 2};

        insertionSort(arr);

        System.out.println(
            "Sorted Emergency Response Times: "
            + Arrays.toString(arr)
        );

        System.out.println(
            "Binary Search (Response Time 5 found at index): "
            + binarySearch(arr, 5)
        );


        // ============================================
        // Min-Heap Test
        // ============================================
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