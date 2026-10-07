public class NurinSplayTree
{
    static class SplayTree
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

        private Node rightRotate(Node x)
        {
            Node y = x.left;
            x.left = y.right;
            y.right = x;

            return y;
        }

        private Node leftRotate(Node x)
        {
            Node y = x.right;
            x.right = y.left;
            y.left = x;

            return y;
        }

        private Node splay(Node root, int key)
        {
            if (root == null || root.key == key)
            {
                return root;
            }

            if (root.key > key)
            {
                if (root.left == null)
                {
                    return root;
                }

                if (root.left.key > key)
                {
                    root.left.left =
                        splay(root.left.left, key);

                    root = rightRotate(root);
                }
                else if (root.left.key < key)
                {
                    root.left.right =
                        splay(root.left.right, key);

                    if (root.left.right != null)
                    {
                        root.left =
                            leftRotate(root.left);
                    }
                }

                return (root.left == null)
                    ? root
                    : rightRotate(root);
            }
            else
            {
                if (root.right == null)
                {
                    return root;
                }

                if (root.right.key > key)
                {
                    root.right.left =
                        splay(root.right.left, key);

                    if (root.right.left != null)
                    {
                        root.right =
                            rightRotate(root.right);
                    }
                }
                else if (root.right.key < key)
                {
                    root.right.right =
                        splay(root.right.right, key);

                    root = leftRotate(root);
                }

                return (root.right == null)
                    ? root
                    : leftRotate(root);
            }
        }

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

            if (root.key > key)
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

        public boolean search(int key)
        {
            root = splay(root, key);

            return root != null && root.key == key;
        }
    }


    public static void main(String[] args)
    {
        SplayTree splayTree = new SplayTree();

        splayTree.insert(20);
        splayTree.insert(10);
        splayTree.insert(30);

        System.out.println("=== SPLAY TREE TEST ===");
        System.out.println("Values inserted: 20, 10, 30");

        boolean isFound = splayTree.search(10);

        System.out.println(
            "Splay Tree Search (10 found): " + isFound
        );
    }
}