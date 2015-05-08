//Steven Cruz
//cs 12b
//lab 7
//sicruz

public class Dictionary implements DictionaryInterface {


    //private inner Node class
    private class Node {
        String key;
        String value;
        Node left;
        Node right;

        //constructor for the node
        Node(String k, String v) {
            key = k;
            value = v;
            left = null;
            right = null;

        }
    }
    //Fields for the Dictionary class
    private Node left;     // reference to left node of the list
    private Node right;    // reference to the right node of the list
    private Node root;
    private int numPairs; //number of keys in the Dictionary

    // findKey()
    // returns the Node containing key k in the subtree rooted at R, or returns
    // NULL if no such Node exists
    private Node findKey(Node R, String key) {
        if (R == null || key.compareTo(R.key) == 0) {
            return R;
        }
        if (key.compareTo(R.key) < 0) {
            return findKey(R.left, key);
        } else {
            return findKey(R.right, key);
        }
    }

    // findParent()
    // returns the parent of N in the subtree rooted at R, or returns NULL
    // if N is equal to R. (pre: R != NULL)
    private Node findParent(Node N, Node R) {
        Node P = null;
        if (N != R) {
            P = R;
            while (P.left != N && P.right != N) {
                if (N.key.compareTo(P.key) < 0)
                    P = P.left;
                else
                    P = P.right;
            }
        }
        return P;
    }

    // findLeftmost()
    // returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
    private Node findLeftmost(Node R) {
        Node L = R;
        if (L != null) for (; L.left != null; L = L.left);
        return L;
    }

    // printInOrder()
    // prints the (key, value) pairs belonging to the subtree rooted at R in order
    // of increasing keys to file pointed to by out.
    private String printInOrder(Node R) {
        String a, b, c;
        a = b = c = ""; 
        if (R != null) {
            a = printInOrder(R.left);
            b = R.key + " " + R.value + "\n"; 
            c = printInOrder(R.right);
        }
        return (a+b+c); 
    }

    // deleteAll()
    // deletes all Nodes in the subtree rooted at N.
    private void deleteAll(Node N) {
        if (N != null) {
            deleteAll(N.left);
            deleteAll(N.right);
        }
    }


    //public functions

    //Dictionary()
    //constructor for the Dictionary Class
    public Dictionary() {
        Node root = null;
        numPairs = 0;
    }

    // isEmpty
    // pre: none
    // post: returns true if Dictionary contains no pairs. Returns false otherwise.
    public boolean isEmpty() {
        return (numPairs == 0);
    }

    //size()
    //pre: none
    //post: returns the number of pairs in the Dictionary
    public int size() {
        if (numPairs == 0) {
            return 0;
        } else {
            return numPairs;
        }
    }

    //lookup
    //pre: none
    //post: returns value associated key, or null reference if no such key exists
    public String lookup(String key) {
        Node N = findKey(root, key);
        if (N != null) {
            return N.value;
        } else {
            return null;
        }
    }

    // insert()
    // inserts new (key,value) pair into this Dictionary
    // pre: lookup(key)==null
    // post: inserts new (key,value)
    public void insert(String key, String value) throws KeyCollisionException {
        Node N, A, B;
        if (findKey(root, key) != null) {
            throw new KeyCollisionException (
                "insert(): cannot insert duplicate keys.");
        }

        N = new Node(key, value);
        B = null;
        A = root;
        while (A != null) {
            B = A;
            if (key.compareTo(A.key) < 0) A = A.left;
            else A = A.right;
        }
        if (B == null) root = N;
        else if (key.compareTo(B.key) < 0) B.left = N;
        else B.right = N;
        numPairs++;
    }

    // delete()
    // deletes pair with the given key
    // pre: lookup(key)!=null
    // post: removes a pair with the given key
    public void delete(String key) throws KeyNotFoundException {
        Node N, P, S;
        N = findKey(root, key);
        if (findKey(root, key) == null) {
            throw new KeyNotFoundException (
                "cannot delete non-existent key.");
        }
        if (N.left == null && N.right == null) {    //case 1: N has no children
            if (N == root) {
                root = null;
            } else {
                P = findParent(N, root);
                if (P.right == N) P.right = null;
                else P.left = null;
            }
        }
        else if (N.right == null) {                      //case 2.1: N has a left child
            if (N == root) {
                root = N.left;
            } else {
                P = findParent(N, root);
                if (P.right == N) P.right = N.left;
                else P.left = N.left;
            }
        }
        else if (N.left == null) {                      //case 2.2 N has a right child
            if (N == root) {
                root = N.right;
            } else {
                P = findParent(N, root);
                if (P.right == N) P.right = N.right;
                else P.left = N.right;
            }
        }



        else { //N.left != null && N.right != null   case 3 where N has both left and right childs
            S = findLeftmost(N.right);
            N.key = S.key;
            N.value = S.value;
            P = findParent(S, N);
            if (P.right == S) P.right = S.right;
            else P.left = S.right;

        }
        numPairs--;
    }

    // makeEmpty()
    // pre: none
    // post: isEmpty()
    public void makeEmpty() {
        deleteAll(root);
        root = null;
        numPairs = 0;
    }


   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
    public String toString() {
        String s = "";
        s = printInOrder(root);
        return s;
    }
}

