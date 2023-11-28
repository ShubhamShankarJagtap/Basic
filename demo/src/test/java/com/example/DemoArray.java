package com.example;

public class DemoArray {

    void learn(int[] arr) {

        int sum = 0, average = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            average = sum / arr.length;

        }

        System.out.println("The sum of the given array is : " + sum + " Average of the given array is :" + average);
    }

    public static void main(String[] args) {

        int[] arr = { 34, 53, 90, 78, 43, 55, 47 };
        new DemoArray().learn(arr);

    }
}
