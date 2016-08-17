package Lab2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RandomizedQuickSort 
{	
    public static int[] initialize(int[] arr)
    {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++)
		    arr[i] = rand.nextInt(10000);
		return arr;
    }

    public static void swap(int[] arr, int i, int j)
    {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
    }

    public static int partition(int[] arr, int p, int r)
    {
		int x = arr[r];
		int i = p - 1;
		for (int j = p; j < r; j++)
		{
			if (arr[j] <= x)
			{
				i++;
				swap(arr, i, j);
			}
		}
	
		swap(arr, i + 1, r);
		return i + 1;
    }

    public static int randomizedPartition(int[] arr, int p, int r)
    {
		//Random rand = new Random();
		int i = p + (int)(Math.random() * ((r - p) + 1));
		swap(arr, r, i);
		
		return partition(arr, p, r);
    }
    
    public static void randomizedQuickSort(int [] arr, int p, int r)
    {
		if (p < r)
		{
			int q = randomizedPartition(arr, p, r);
			randomizedQuickSort(arr, p, q - 1);
			randomizedQuickSort(arr, q + 1, r);
		}
    }

    public static void main(String[] args) 
    {
		long start, end;
		double time;
		int size;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the size for the array or 0 to exit: ");
		size = scanner.nextInt();
		while (size != 0)
		{
			int[] A = new int[size];
			initialize(A);
			
			if (size <= 50)
			    System.out.println("Unsorted array is: " + Arrays.toString(A));
			
			start = System.nanoTime();
			randomizedQuickSort(A, 0, size - 1);
			end = System.nanoTime();
			time = (end - start) / 1000000.0;
			
			if (size <= 50)
			    System.out.println("Sorted array is: " + Arrays.toString(A));
			
			System.out.printf("Time for randomized quick sort of size %d: %.3f milliseconds.\n", size, time);
			
			System.out.println("\nEnter the size for the array or 0 to exit: ");
			size = scanner.nextInt();
		}
		scanner.close();
    }
}
