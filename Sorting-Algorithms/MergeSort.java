import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MergeSort 
{	
	public static int[] initialize(int[] A)
	{
		Random rand = new Random();
		for (int i = 0; i < A.length; i++)
			A[i] = rand.nextInt(10000);
		return A;
	}
	
	public static void merge(int[] A, int p, int q, int r)
	{
		int i = 0, j = 0;
		
		int n1 = q - p + 1;
		int n2 = r - q;
		
		int[] left = new int[n1 + 1];
		int [] right = new int[n2 + 1];
		
		left[n1] = Integer.MAX_VALUE;
		right[n2] = Integer.MAX_VALUE;
		
		System.arraycopy(A, p, left, 0, n1);
		System.arraycopy(A, q + 1, right, 0, n2);
		
		for (int k = p; k <= r; k++)
		{
			if (left[i] <= right[j]) 
				A[k] = left[i++];
			else
				A[k] = right[j++];
		}		
	}
	
	public static void mergeSort(int[] A, int p, int r)
	{
		if (p < r)
		{
			int q = (p + r) / 2;
			mergeSort(A, p, q);
			mergeSort(A, q + 1, r);
			merge(A, p, q, r);
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
				System.out.println("Uninitialized array is: " + Arrays.toString(A));
			
			start = System.nanoTime();
			mergeSort(A, 0, size - 1);
			end = System.nanoTime();
			time = (end - start) / 1000000.0;

			if (size <= 50)
				System.out.println("Initialized array is: " + Arrays.toString(A));
			
			System.out.printf("Time for merge sort of size %d: %.3f milliseconds.\n", size, time);
			
			System.out.println("\nEnter the size for the array or 0 to exit: ");
			size = scanner.nextInt();
		}
		scanner.close();
	}
}
