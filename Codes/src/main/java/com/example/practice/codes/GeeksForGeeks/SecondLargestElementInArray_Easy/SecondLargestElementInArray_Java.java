// Link - https://www.geeksforgeeks.org/batch/gfg-160-problems/track/arrays-gfg-160/problem/second-largest3735
package com.example.practice.codes.GeeksForGeeks.SecondLargestElementInArray_Easy;

import java.util.Arrays;
import java.util.Scanner;

public class SecondLargestElementInArray_Java{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Enter n = ");
        n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        int secondMaxUsingBruteForce = findSecondMaximumElementBruteForce(arr);
        System.out.println("The value of second max using brute force is - " + secondMaxUsingBruteForce);

        int secondMaxUsingBetterApproach = findSecondMaximumElementBetterApproach(arr, arr[0], arr[0], -1, 0, Integer.MAX_VALUE);
        System.out.println("The value of second max using better approach is - " + secondMaxUsingBetterApproach);

        int secondMaxUsingOptimisedApproach = findSecondMaximumElementOptimisedApproach(arr);
        System.out.println("The value of second max using optimised approach is - " + secondMaxUsingOptimisedApproach);

        sc.close();
    }


    /**
     * Approach :
     * If array length is 1 then no second max is possible so directly return -1
     * If length is greater than 1 then sort the array. --- O(nlogn) worst case
     * sorting algo.
     * Parse the array from back and find the first changed element. If you reach
     * the 0th index then return -1 or else return the first changed element.
     * @param arr
     * @timeComplexity : O(nlogn)
     * @spaceComplexity : O(1)
     */

    public static int findSecondMaximumElementBruteForce(int[] arr){
        Arrays.sort(arr);
        int maximumNumber = arr[arr.length-1];
        for(int i=arr.length-2; i>=0;i--){
            if(arr[i] != maximumNumber){
                return arr[i];
            }
        }
        return -1;
    }

    /**
     * Better Approach!
     * 1. If the length of the array is 1 then second maximum is not possible
     * 2. If current value is greater than maximum then it has potential chance to become maximum. and the current maximum has potential chances to become second maximum.
     *      then directly we can say that the value which is there in currentMax can become secondMax.
     * 3. If current value is less than or equals to maximum it means that we either found our maximum or it is equal to the maximum so in that case we only check for difference and find the current value has potential to become second maximum and update it.
     * 4. While recursively doing it if we reach the n-1 index then we just do the computation and upgradtion and then return secondMax value without doing any further recursive call.
     * @param : arr
     * @timeComplexity : O(n)
     * @spaceComplexity : O(n)
     */
    public static int findSecondMaximumElementBetterApproach(int[] arr, int x, int currentMax, int secondMax, int currentIndx, int difference){
        if(x > currentMax){
            difference = x - currentMax;
            secondMax = currentMax;
            currentMax = x;
        }
        else{
            int currentDiff = currentMax - x;
            if(currentDiff < difference && currentDiff != 0){
                difference = currentDiff;
                secondMax = x;
            }
        }
        if(currentIndx == arr.length-1){
            return secondMax;
        }
        else{
            return findSecondMaximumElementBetterApproach(arr, arr[currentIndx+1], currentMax, secondMax, currentIndx+1, difference);
        }
    }

    /**
     *  Optimised Approach
     * Find the maximum number from the array and then find the second maximum
     * number such that it is greater than all the other elements but less than the
     * maximum number.
     * Edge Case : If the length of array is 1 or all the elements in the array are
     * same then return -1.
     *
     * @param arr
     * @timeComplexity : O(n)
     * @spaceComplexity : O(1)
     */
    public static int findSecondMaximumElementOptimisedApproach(int[] arr){
        int maximumNumber = Integer.MIN_VALUE;

        for (int j : arr) {
            maximumNumber = Math.max(j, maximumNumber);
        }

        int secondMaximumNumber = -1;
        for (int j : arr) {
            if (j > secondMaximumNumber && j < maximumNumber) {
                secondMaximumNumber = j;
            }
        }
        return secondMaximumNumber;
    }
}