//Steven Cruz
//sicruz
//pa1
//CMPS012B

// Extrema.java
//finds the highest and lowest value in the given array
class Extrema {

    // maxArray()
    // returns the largest value in int array A
    static int maxArray(int[] A, int p, int r) {
        int q; //mid value
        int max1;
        int max2;
        if (p == r) {
            return A[p];
        } else {
            q = (p + r) / 2;
            max1 = maxArray(A, p, q);
            max2 = maxArray(A, q + 1, r);
        }
        if (max1 > max2) {
            return max1; //displays max value in array
        } else {
            return max2;
        }
    }
    // minArray()
    // returns the smallest value in int array A
    static int minArray(int[] A, int p, int r) {
        int q; //mid value
        int min1;
        int min2;
        if (p == r) {
            return A[p];
        } else {
            q = (p + r) / 2;
            min1 = minArray(A, q + 1, r);
            min2 = minArray(A, p, q);
        }
        if (min1 > min2) {
            return min2; //this will display the lowest value in the array
        } else {
            return min1;
        }
    }
    // main()
    public static void main(String[] args) {
        int[] B = { -1, 2, 6, 3, 9, 2, -3, -2, 11, 1, 7};
        System.out.println( "max = " + maxArray(B, 0, B.length - 1) ); // output: max = 11
        System.out.println( "min = " + minArray(B, 0, B.length - 1) ); // output: min = -3
    }
}