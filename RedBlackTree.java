import java.util.Random;

public class RedBlackTree<K extends Comparable, V> {
    private Boolean red;
    private Boolean black;
    private class Node {
        private Node left;
        private Node right;
        private int N;
        private K key;
        private V val;
        private Boolean color;
        public Node(K key, V val)
        {
            this.key = key;
            this.val = val;
            this.color = red;
            this.right = null;
            this.left = null;
            this.N = 0;
        }
        public K key()
        {
            return key;
        }
        public V value()
        {
            return val;
        }
        public void setLeftChild(Node h)
        {
            left = h;
        }
        public void setRightChild(Node h)
        {
            right = h;
        }
        public Node leftChild()
        {
            return left;
        }
        public Node rightChild()
        {
            return right;
        }
    };
    private Node root;
    private Boolean isRed(Node h)
    {
        if (h == null)
            return false;
        return (h.color == red);
    }
    private Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = red;
        x.N = h.N;
        h.N++;
        return x;
    }
    private Node rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = red;
        x.N = h.N;
        h.N++;
        return x;
    }
    private void colorFlip(Node h)
    {
        h.color = !h.color;
        h.right.color = !h.right.color;
        h.left.color = !h.left.color;
    }
    private Node insertFix(Node h)
    {
        if (isRed(h.rightChild()) && !isRed(h.leftChild()))
        {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left))
        {
            h = rotateRight(h);
        }
        if (isRed(h.right) && isRed(h.left))
        {
            colorFlip(h);
        }
        return h;
    }
    private Node put(Node h, K key, V val)
    {
        if (h == null)
        {
            return new Node(key, val);
        }
        if (key.compareTo(h.key()) < 0)
        {
            h.setLeftChild(put(h.leftChild(), key, val));
        } else {
            h.setRightChild(put(h.rightChild(), key, val));
        }
        h.N++;
        return insertFix(h);
    }
    
    private void walk(Node h)
    {
        if (h != null)
        {
            System.out.print("(" + h.key() + ":" + h.value() + ") ");
            walk(h.leftChild());
            walk(h.rightChild());
        }
    }

    private Node findMin(Node h)
    {
        if (h.left == null)
            return h;
        return findMin(h.left);
    }

    private Node findMax(Node h)
    {
        if (h.right == null)
            return h;
        return findMax(h.right);
    }

    public void insert(K key, V val)
    {
        root = put(root, key, val);
        root.color = black;
    }

    public K min()
    {
        Node h = findMin(root);
        return h.key();
    }

    public K max()
    {
        Node h = findMax(root);
        return h.key();
    }

    public void show()
    {
        walk(root);
        System.out.println();
    }
    RedBlackTree()
    {
        root = null;
        red = true;
        black = false;
    }
    public static void main(String[] args) {
        Random rng = new Random();
        RedBlackTree<Character,Integer> rbt = new RedBlackTree();
        String sed = "ASEARCHINGEXAMPLE";
        for (Integer i = 0; i < 17; i++)
        {
            rbt.insert(sed.charAt(i), rng.nextInt(15));
        }
        rbt.show();
        System.out.println("Min: " + rbt.min());
        System.out.println("Max: " + rbt.max());
    }
}