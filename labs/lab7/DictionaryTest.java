public class DictionaryTest{

public static void main(String[] args){
      String v;
      Dictionary A = new Dictionary();
      A.insert("1","a");
      A.insert("2","b");
      System.out.println(A.isEmpty());
      System.out.println(A.size());
      System.out.println(A);

      v = A.lookup("1");
      System.out.println("key=1 "+(v==null?"not found":("value="+v)));
      v = A.lookup("3");
      System.out.println("key=3 "+(v==null?"not found":("value="+v)));
      v = A.lookup("7");
      System.out.println("key=7 "+(v==null?"not found":("value="+v)));
      v = A.lookup("8");
      System.out.println("key=8 "+(v==null?"not found":("value="+v)));
      System.out.println();

      A.delete("1");
      System.out.println(A);

      System.out.println(A.isEmpty());
      System.out.println(A.size());
      A.makeEmpty();
      System.out.println(A.isEmpty());
      System.out.println(A);
  }
}