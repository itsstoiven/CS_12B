//Steven Cruz
//CS12B
//PA3
//sicruz
import java.util.*;


public class DictionaryTest {

    public static void main(String[] args) {
        String v;
        Dictionary A = new Dictionary();

        System.out.println(A.isEmpty());
        System.out.println(A.size());

        A.insert("1", "a");
        A.insert("2", "b");
        A.insert("3", "c");
        A.insert("4", "d");
        System.out.println(A);

        v = A.lookup("1");
        System.out.println("key=1 " + (v == null ? "not found" : ("value=" + v)));
        v = A.lookup("3");
        System.out.println("key=3 " + (v == null ? "not found" : ("value=" + v)));

        System.out.println(A.isEmpty());
        A.delete("1");

        System.out.println(A);

        A.makeEmpty();
        System.out.println(A.isEmpty());
        System.out.println(A.size());

        v = A.lookup("1");
        System.out.println("key=1 " + (v == null ? "not found" : ("value=" + v)));
        v = A.lookup("3");
        System.out.println("key=3 " + (v == null ? "not found" : ("value=" + v)));


    }
}