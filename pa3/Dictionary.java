//Steven Cruz
//CS12B
//PA3
//sicruz :)


public class Dictionary implements DictionaryInterface {


    //private inner Node class
    private class Node {
        String key;
        String value;
        Node next;

        Node(String k, String v) {
            key = k;
            value = v;
            next = null;

        }
    }

    //Fields for the Dictionary class

    private Node head;     // reference to first Node in List
    private int numPairs; //number of keys in the Dictionary
    private Node tail; //reference to the last Node in the list

    //findKey()
    //returns a reference to the Node at position key in this Dictionary
    private Node findKey(String key) {
        Node N = head;
        for (N = head; N != null; N = N.next) {
            if (N.key.equals(key)) {
                break;
            }
        }
        return N;
    }

    //Dictionary()
    //constructor for the Dictionary Class
    public Dictionary() {
        head = null;
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
        Node N = findKey(key);
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
        if (lookup(key) != null) {
            throw new KeyCollisionException (
                "insert(): cannot insert duplicate keys.");
        }
        if (numPairs == 0) {
            head = tail = new Node(key, value);

        } else {
            Node P = new Node(key, value);
            tail.next = P;
            tail = P;
        }
        numPairs++;
    }

    // delete()
    // deletes pair with the given key
    // pre: lookup(key)!=null
    // post: removes a pair with the given key
    public void delete(String key) throws KeyNotFoundException {
        if (lookup(key) == null) {
            throw new KeyNotFoundException (
                "cannot delete non-existent key.");

        }
        Node N;
        Node q = findKey(key);

        if (numPairs == 1) {        //deletes everything, which is only one thing
            head = tail = null;

        } else if (q == head) {     //deletes head
            head = head.next;
            q = null;
        } else if (q == tail) {     //deletes end of the list
            N = head;
            while (N.next != q) {
                N = N.next;
            }
            N.next = null;
            tail = N;
        } else {                    //deletes a node in the middle
            N = head;
            while (N.next != q) {
               N = N.next;
            }
            N.next = q.next;
        }
        q = null;
        numPairs--;
    }

    // makeEmpty()
    // pre: none
    // post: isEmpty()
    public void makeEmpty() {
        head = null;
        numPairs = 0;
    }

    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    public String toString() {
        String s = "";
        for (Node N = head; N != null; N = N.next) {
            s += N.key + " " + N.value + "\n";
        }
        return s;
    }
}
