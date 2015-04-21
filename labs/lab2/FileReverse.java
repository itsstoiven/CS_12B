/*Steven Cruz
sicruz
lab2
cmps012m 
pre:  none
post: object returned is a String that is the reverse of s
*/ 

import java.io.*;
import java.util.Scanner;

class FileReverse {
	
   public static String stringReverse(String s, int n) {
	 if (n > 0) { 
         return s.substring(n-1,n) + stringReverse(s, n-1); //keeps returning last char 												//of string
           }
    	else {
    	return "";
    	}
   }
   public static void main(String[] args) throws IOException {
      Scanner in = null;
      PrintWriter out = null;
      String line = null;
      String[] token = null;
      int i, n;
      
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));
      
      in.useDelimiter("\n");
      while(in.hasNext()){
         line = in.next() + " ";  // add space so split works on blank lines
         token = line.split("\\s+");  // split line around white space
         n = token.length;
         for(i=0; i<n; i++){
            out.println(stringReverse(token[i], token[i].length()));
         }
      }

      in.close();
      out.close();
   }
      
}

