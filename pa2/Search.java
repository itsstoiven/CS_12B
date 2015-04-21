//Steven Cruz
//PA2
//sicruz
//CS12B


import java.io.*;
import java.util.Scanner;

class Search {


    static void mergeSort(String[] word, int[] lineNumber, int p, int r) {
        int q; //mid
        if (p < r) { //p is left and r is right
            q = (p + r) / 2;
            mergeSort(word, lineNumber, p, q);
            mergeSort(word, lineNumber, q + 1, r);
            merge(word, lineNumber, p, q, r);
        }
    }

    static void merge(String[] word, int[] lineNumber, int p, int q, int r) {
        int s1 = q - p + 1;
        int s2 = r - q;
        String[] L = new String[s1];
        String[] R = new String[s2];
        int[] LLin = new int[s1]; // Left lineNumber
        int[] RLin = new int[s2]; // right LineNumber
        int i, j, k;

        for (i = 0; i < s1; i++) L[i] = word[p + i];
        for (j = 0; j < s2; j++) R[j] = word[q + j + 1];
        i = 0; j = 0;
        for (i = 0; i < s1; i++) LLin[i] = lineNumber[p + i];
        for (j = 0; j < s2; j++) RLin[j] = lineNumber[q + j + 1];
        i = 0; j = 0;
        for (k = p; k <= r; k++) {
            if ( i < s1 && j < s2 ) {
                if ( (L[i].compareTo(R[j])) < 0 ) { //compare the values
                    word[k] = L[i];
                    lineNumber[k] = LLin[i];
                    i++;
                } else {
                    word[k] = R[j];
                    lineNumber[k] = RLin[j];
                    j++;
                }
            } else if ( i < s1 ) {
                lineNumber[k] = LLin[i];
                word[k] = L[i];
                i++;
            } else { // j<s2
                word[k] = R[j];
                lineNumber[k] = RLin[j];
                j++;
            }
        }
    }
    static int binarySearch(String[] word, int[] lineNumber, int p, int r, String target) {
        int q; //mid value
        if (p > r) {
            return -1;
        }
        q = (p + r) / 2; //split in two
        if (target.compareTo(word[q]) == 0) {
            return lineNumber[q];
        } else if (target.compareTo(word[q]) < 0) {
            return binarySearch(word, lineNumber, p, q - 1, target);
        } else { //target > word[q]
            return binarySearch(word, lineNumber, q + 1, r, target);
        }
    }

    public static void main(String[] args) throws IOException {

        int i, n = 0;
        int lineCounter = 0;

        // check command line arguments
        if (args.length < 2) {
            System.err.println("Usage: Search file target(s)");  //if user fails to put a target
            System.exit(1);
        }

        Scanner file1 = new Scanner(new File(args[0]));

        while (file1.hasNextLine()) {
            file1.nextLine();
            lineCounter++;  //increment the line count to count how many lines are in file
        }

        file1.close();

        int[] lineNumber = new int[lineCounter];

        for (i = 0; i < lineCounter; i++) {
            lineNumber[i] = ++n;            //holds the line number
        }

        String[] word = new String[lineCounter];

        file1 = new Scanner(new File(args[0]));

        i  = 0;
        while (file1.hasNext()) {
            word[i] = file1.next();
            i++;
        }

        file1.close();

        mergeSort(word, lineNumber, 0, word.length - 1); //merge all the words together
        int breturn; //declare an int to return the values of the arguments

        for (i = 1; i < args.length; i++) {
            breturn = binarySearch(word, lineNumber, 0,  word.length - 1, args[i]);
            if (breturn == -1) {
                System.out.println(args[i] + " not found");
            } else {
                System.out.println(args[i] + " found on line " + breturn);
            }
        }
    }
}