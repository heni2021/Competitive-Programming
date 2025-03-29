package com.example.practice.codes.Utilities;

import java.util.Scanner;

public class ArrayUtilities_Java {
    public static int[] scanIntArray(){
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n = ");
        n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        sc.close();
        return arr;
    }
}
