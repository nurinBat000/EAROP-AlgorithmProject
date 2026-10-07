public class EAROPDemo
{
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
            0, visited, 0, dist, n, path
        );

        return "Divide & Conquer Route: "
            + path.toString()
            + " -> " + locations[0]
            + " | Total Cost: "
            + totalCost;
    }

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

                StringBuilder newPath =
                    new StringBuilder(path);
                newPath.append(" -> ")
                       .append(locations[next]);

                // Conquer subproblem recursively
                int routeCost = divideAndConquerHelper(
                    next,
                    visited,
                    currentCost + dist[pos][next],
                    dist,
                    n,
                    newPath
                );

                // Combine solution
                if (routeCost < minimumCost)
                {
                    minimumCost = routeCost;
                    bestRoute = newPath.toString();
                }
                visited[next] = false;
            }
        }
        path.setLength(0);
        path.append(bestRoute);
        return minimumCost;
    }

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

    public static void main(String[] args)
    {
        int[][] costMatrix = {
            { 0, 15, 25, 35 },
            { 15, 0, 30, 28 },
            { 25, 30, 0, 20 },
            { 35, 28, 20, 0 }
        };
        System.out.println("=== DIVIDE AND CONQUER TEST ===");
        System.out.println(divideAndConquerEAROP(costMatrix));
    }
}