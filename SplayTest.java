public class SplayTest
{
    public static void main(String[] args)
    {
        AisyaSplayTree tree = new AisyaSplayTree();

        // Insert emergency case priorities
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);

        // Search for an emergency case
        boolean found = tree.search(10);

        System.out.println(
            "Splay Tree Search (Emergency Case 10 found): "
            + found
        );
    }
}