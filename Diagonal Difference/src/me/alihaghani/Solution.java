package me.alihaghani;

import java.io.*;
import java.util.*;

public class Solution {

    // HackerRank Diagonal Difference solution

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n*n];
        for (int i = 0; i < n*n; i++){
            arr[i] = input.nextInt();
        }

        int diff1 = 0;
        int diff2 = 0;

        for (int i = 0; i < arr.length;){
            diff1 += arr[i];
            i += n+1;
        }

        for (int j = n-1; j <= arr.length-n;){
            diff2 += arr[j];
            j += n-1;
        }

        int result = Math.abs(diff1 - diff2);

        System.out.println(result);

    }
}
