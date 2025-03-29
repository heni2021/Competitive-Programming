// Link - https://www.geeksforgeeks.org/batch/gfg-160-problems/track/arrays-gfg-160/problem/second-largest3735
#include <iostream>
#include <algorithm>
using namespace std;

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
int findSecondMaximumElementBruteForce (int arr[], int n){
    sort(arr, arr+n);
    int maximumNumber = arr[n-1];
    for(int i=n-2; i>=0; i--){
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

int findSecondMaximumElementBetterApproach(int arr[], int x, int currentMax, int secondMax, int currentIndx, int difference, int n){
    if(x>currentMax){
        difference = x-currentMax;
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

    if(currentIndx == n-1){
        return secondMax;
    }
    else{
        return findSecondMaximumElementBetterApproach(arr, arr[currentIndx+1], currentMax, secondMax, currentIndx+1, difference, n);
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

int findSecondMaximumElementOptimisedApproach (int arr[], int n){
    int maximumNumber = 0;
    for (int i = 0; i < n; i++){
        maximumNumber = max(arr[i], maximumNumber);
    }

    int secondMaximumNumber = -1;
    for (int i = 0; i < n; i++){
        if (arr[i] > secondMaximumNumber && arr[i] < maximumNumber){
            secondMaximumNumber = arr[i];
        }
    }
    return secondMaximumNumber;
}

int main()
{
    int arr[] = {12, 35, 1, 10, 34, 1};
    int length = sizeof(arr) / sizeof(arr[0]);
    // int arr[] = {10,10, 10};
    // int arr[] = {12};
    // int arr[] = {2192, 13849, 3443, 20919, 10086, 31384, 4405};

    int secondMaxValueUsingBruteForce = findSecondMaximumElementBruteForce(arr, length);
    cout << "Second Max Using Brute Force : " << secondMaxValueUsingBruteForce << "\n";

    int secondMaxValueUsingBetterApproach = findSecondMaximumElementBetterApproach(arr, arr[0], arr[0], -1, 0, 100000, length);
    cout << "Second Max Using Better Approach : " << secondMaxValueUsingBetterApproach  << "\n";

    int secondMaxValueUsingOptimisedApproach = findSecondMaximumElementOptimisedApproach(arr, length);
    cout << "Second Max Using Optimised Approach : " << secondMaxValueUsingOptimisedApproach << "\n";
    return 0;
}