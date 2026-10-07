public class AisyaSplayTree
{
    class Node
    {
        int key;
        Node left, right;

        Node(int key)
        {
            this.key = key;
        }
    }

    private Node root;

    // Right rotation
    private Node rotateRight(Node x)
    {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    // Left rotation
    private Node rotateLeft(Node x)
    {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    // Splay operation
    private Node splay(Node root, int key)
    {
        if (root == null || root.key == key)
        {
            return root;
        }

        // Key is in the left subtree
        if (key < root.key)
        {
            if (root.left == null)
            {
                return root;
            }

            // Zig-Zig
            if (key < root.left.key)
            {
                root.left.left = splay(root.left.left, key);
                root = rotateRight(root);
            }

            // Zig-Zag
            else if (key > root.left.key)
            {
                root.left.right = splay(root.left.right, key);

                if (root.left.right != null)
                {
                    root.left = rotateLeft(root.left);
                }
            }

            if (root.left == null)
            {
                return root;
            }

            return rotateRight(root);
        }

        // Key is in the right subtree
        else
        {
            if (root.right == null)
            {
                return root;
            }

            // Zig-Zag
            if (key < root.right.key)
            {
                root.right.left = splay(root.right.left, key);

                if (root.right.left != null)
                {
                    root.right = rotateRight(root.right);
                }
            }

            // Zig-Zig
            else if (key > root.right.key)
            {
                root.right.right = splay(root.right.right, key);
                root = rotateLeft(root);
            }

            if (root.right == null)
            {
                return root;
            }

            return rotateLeft(root);
        }
    }

    // Insert a new value
    public void insert(int key)
    {
        if (root == null)
        {
            root = new Node(key);
            return;
        }

        root = splay(root, key);

        if (root.key == key)
        {
            return;
        }

        Node newNode = new Node(key);

        if (key < root.key)
        {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        }
        else
        {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }

        root = newNode;
    }

    // Search for a value
    public boolean search(int key)
    {
        root = splay(root, key);

        return root != null && root.key == key;
    }
}

