public class BacktrackingEAROP
{
    // Location names
    static String[] locations = {
        "Hospital",
        "Emergency Location B",
        "Emergency Location C",
        "Emergency Location D"
    };

    // Store the best route and its cost
    static int bestCostBacktracking = Integer.MAX_VALUE;
    static String bestPathBacktracking = "";


    // ==========================================
    // BACKTRACKING EAROP
    // ==========================================

    public static String backtrackingEAROP(int[][] dist)
    {
        int n = dist.length;

        // Track visited locations
        boolean[] visited = new boolean[n];

        // Start the route from Hospital
        visited[0] = true;

        StringBuilder path = new StringBuilder();
        path.append(locations[0]);

        // Reset best result
        bestCostBacktracking = Integer.MAX_VALUE;
        bestPathBacktracking = "";

        // Start Backtracking
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


    // ==========================================
    // BACKTRACKING HELPER METHOD
    // ==========================================

    private static int earopBacktracking(
        int pos,
        int[][] dist,
        boolean[] visited,
        int n,
        int count,
        int cost,
        StringBuilder path)
    {
        // Base case:
        // All locations have been visited
        if (count == n)
        {
            // Return to Hospital
            int total = cost + dist[pos][0];

            String fullPath =
                path.toString() + " -> " + locations[0];

            // Check whether this is the best route
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
                // Choose the location
                visited[city] = true;

                int oldLength = path.length();

                path.append(" -> ")
                    .append(locations[city]);


                // Explore the selected location
                int routeCost = earopBacktracking(
                    city,
                    dist,
                    visited,
                    n,
                    count + 1,
                    cost + dist[pos][city],
                    path
                );


                // Keep the smaller cost
                minCost = Math.min(minCost, routeCost);


                // Backtrack
                path.setLength(oldLength);
                visited[city] = false;
            }
        }

        return minCost;
    }


    // ==========================================
    // TEST THE PROGRAM
    // ==========================================

    public static void main(String[] args)
    {
        int[][] dist = {
            {0, 15, 25, 35},
            {15, 0, 30, 28},
            {25, 30, 0, 20},
            {35, 28, 20, 0}
        };


        String result = backtrackingEAROP(dist);

        System.out.println(result);
    }
}