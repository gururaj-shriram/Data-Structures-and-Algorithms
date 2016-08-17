import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class InsertionSort 
{	
	public static int[] initialize(int[] A)
	{
		Random rand = new Random();
		for (int i = 0; i < A.length; i++)
			A[i] = rand.nextInt(10000);
		return A;
	}
	
	public static int[] insertSort(int[] A)
	{
		int key, j = 0;
		
		for (int i = 1; i < A.length; i++)
		{
			key = A[i];
			j = i - 1;
			while (j >= 0 && A[j] > key)
			{
				A[j + 1] = A[j];
				j -= 1;
			}
			A[j+1] = key;
		}
		return A;	
	}
	
	// The main method performs time complexities on an array of n random integers
	public static void main(String[] args) 
	{
		long start, end;
		double time;
		int size;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the size for the Array or 0 to exit: ");
		size = scanner.nextInt();
		while (size != 0)
		{
			int[] A = new int[size];
			initialize(A);
			
			if (size <= 50)
				System.out.println("Uninitialized Array is: " + Arrays.toString(A));
			
			start = System.nanoTime();
			insertSort(A);
			end = System.nanoTime();
			time = (end - start) / 1000000.0;

			if (size <= 50)
				System.out.println("Initialized Array is: " + Arrays.toString(A));
			
			System.out.printf("Time for insertion sort of size %d: %.3f milliseconds.\n", size, time);
			
			System.out.println("\nEnter the size for the Array or 0 to exit: ");
			size = scanner.nextInt();
		}		
		scanner.close();
	}

}
