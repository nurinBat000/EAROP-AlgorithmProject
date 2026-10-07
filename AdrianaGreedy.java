import java.util.PriorityQueue;

public class AdrianaGreedy {

       private static final String[] LOCATIONS = {
        "Hospital Headquarters (H)", 
        "Emergency Location E1", 
        "Emergency Location E2", 
        "Emergency Location E3"
    };

     public static void runGreedy(int[][] dist) {
        int n = dist.length;
        boolean[] visited = new boolean[n];
        
        int curr = 0; // Starts at Hospital Headquarters (index 0)
        visited[curr] = true;
        
        StringBuilder path = new StringBuilder(LOCATIONS[curr]);
         int totalCost = 0;

                for (int i = 1; i < n; i++) {
            int nextLocation = -1;
            int minCost = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && dist[curr][j] < minCost) {
                    minCost = dist[curr][j];
                    nextLocation = j;
                }
            }

            if (nextLocation != -1) {
                visited[nextLocation] = true;
                totalCost += minCost;
                path.append(" -> ").append(LOCATIONS[nextLocation]);
                curr = nextLocation;
            }
        }

               totalCost += dist[curr][0];
        path.append(" -> ").append(LOCATIONS[0]);

        System.out.println("Greedy Route: " + path.toString() + " | Total Cost: " + totalCost);
    }
     static class SplayTree {
        class Node {
            int key;
            Node left, right;
            Node(int key) { this.key = key; }
        }

        private Node root;

        private Node rightRotate(Node x) {
            Node y = x.left;
            x.left = y.right;
            y.right = x;
            return y;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            y.left = x;
            return y;
        }
        private Node splay(Node root, int key) {
            if (root == null || root.key == key) return root;

            if (root.key > key) {
                if (root.left == null) return root;
                if (root.left.key > key) {
                    root.left.left = splay(root.left.left, key);
                    root = rightRotate(root);
                } else if (root.left.key < key) {
                    root.left.right = splay(root.left.right, key);
                    if (root.left.right != null) root.left = leftRotate(root.left);
                }
                return (root.left == null) ? root : rightRotate(root);
            } else {
                if (root.right == null) return root;
                if (root.right.key > key) {
                    root.right.left = splay(root.right.left, key);
                    if (root.right.left != null) root.right = rightRotate(root.right);
                } else if (root.right.key < key) {
                    root.right.right = splay(root.right.right, key);
                    root = leftRotate(root);
                }
                return (root.right == null) ? root : leftRotate(root);
            }
        }

        public void insert(int key) {
            if (root == null) {
                root = new Node(key);
                return;
            }
            root = splay(root, key);
            if (root.key == key) return;
            Node newnode = new Node(key);
            if (root.key > key) {
                newnode.right = root;
                  newnode.left = root.left;
                root.left = null;
            } else {
                newnode.left = root;
                newnode.right = root.right;
                root.right = null;
            }
            root = newnode;
        }

        public boolean search(int key) {
            root = splay(root, key);
            return root != null && root.key == key;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SYSTEM DEMONSTRATION OUTPUT ===\n");

        // 1. Run Greedy Algorithm Demonstration
        int[][] costMatrix = {
            { 0, 15, 25, 35 },
            { 15,  0, 30, 28 },
            { 25, 30,  0, 20 },
            { 35, 28, 20,  0 }
        };
        runGreedy(costMatrix);

        // 2. Run Min-Heap Demonstration
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(10);
        minHeap.add(3);  // Highest priority / lowest distance
        minHeap.add(15);
        System.out.println("Min-Heap Extract Min: " + minHeap.poll());

        // 3. Run Splay Tree Demonstration
        SplayTree splayTree = new SplayTree();
        splayTree.insert(5);
        splayTree.insert(10);
        splayTree.insert(20);
        boolean isFound = splayTree.search(10);
        System.out.println("Splay Tree Search (10 found): " + isFound);
    }
}