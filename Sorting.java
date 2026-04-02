import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sorting {
	final int MAX_SIZE = 10000000;

	// Set this to true if you wish the arrays to be printed.
	final static boolean OUTPUT_DATA = false;
	
	public static String sortAlg= null;
	public static  int size = 0;
	public static int maxDepth = 0;
	public static int idealDepth = 0;
	public static Random rand = new Random();
	
	public static void main(String[] args) {
		readInput();
		int [] data = new int[size];
		GenerateSortedData(data, size);
		Sort(data, size,"Sorted Data");

		GenerateNearlySortedData(data, size);
		Sort(data, size,"Nearly Sorted Data");
		
		GenerateReverselySortedData(data, size);
		Sort(data, size,"Reversely Sorted Data");
		
		GenerateRandomData(data, size);
		Sort(data, size,"Random Data");
			
		System.out.println("\nProgram Completed Successfully.");
		
	}
	
	@SuppressWarnings("resource")
	public static void readInput(){
		System.out.println("  I:\tInsertion Sort");
		System.out.println("  M:\tMergeSort");
		System.out.println("  Q:\tQuickSort");
		System.out.println("  S:\tSTLSort");
	    System.out.println("Enter sorting algorithm:");
	    Scanner reader = new Scanner(System.in);
	    sortAlg = reader.next();
	    System.out.println(sortAlg);
		String sortAlgName = "";
		
		if(sortAlg.equals("I"))
			sortAlgName = "Insertion Sort";
		else if(sortAlg.equals("M"))
			sortAlgName = "MergeSort";
		else if(sortAlg.equals("Q"))
			sortAlgName = "QuickSort";
		else if(sortAlg.equals("S"))
			sortAlgName = "STLSort";
		else {
			System.out.println("Unrecognized sorting algorithm Code:"+sortAlg);
			System.exit(1);
		}
		System.out.println("Enter input size: ");
	    size = reader.nextInt();
		System.out.println("\nSorting Algorithm: " + sortAlgName);
        System.out.println("\nInput Size = "  + size);
	}
	
	/******************************************************************************/

	public static void GenerateSortedData(int data[], int size)
	{
		int i;
		
		for(i=0; i<size; i++)
			data[i] = i * 3 + 5;
	}
	/*****************************************************************************/
	public static void GenerateNearlySortedData(int data[], int size)
	{
		int i;
		
		GenerateSortedData(data, size);
		
		for(i=0; i<size; i++)
			if(i % 10 == 0)
				if(i+1 < size)
					data[i] = data[i+1] + 7;
	}
	/*****************************************************************************/

	public static void GenerateReverselySortedData(int data[], int size)
	{
		int i;
		
		for(i = 0; i < size; i++)
			data[i] = (size-i) * 2 + 3;
	}
	/*****************************************************************************/

	public static void GenerateRandomData(int data[], int size)
	{
		int i;
		for(i = 0; i < size; i++)
			data[i] = new Random().nextInt(10000000);
	}
	/*****************************************************************************/

	
	public static void Sort(int[] data, int size,  String string)
	{

		System.out.print("\n"+string+":");
		if (OUTPUT_DATA)
		{
			printData(data, size, "Data before sorting:");
		}

		// Sorting is about to begin ... start the timer!
		long start_time = System.nanoTime();
			if (sortAlg.equals("I"))
			{
			InsertionSort(data, size);
			}
			else if (sortAlg.equals("M"))
			{
			MergeSort(data, 0, size-1);
			}
			else if (sortAlg.equals("Q"))
			{
			maxDepth = 0;
			idealDepth = (int)(3 * Math.log(size));
			QuickSort(data, 0, size-1, 0 );
			System.out.println("Max recursion depth: " + maxDepth);
			System.out.println("Ideal recursion depth: " + idealDepth);
			}
			else if (sortAlg.equals("S"))
			{
			STLSort(data, size);
			}
		else
		{
			System.out.print("Invalid sorting algorithm!");
			System.out.print("\n");
			System.exit(1);
		}

		// Sorting has finished ... stop the timer!
		
		double elapsed = System.nanoTime()-start_time;
		elapsed=elapsed/1000000;


		if (OUTPUT_DATA)
		{
			printData(data, size, "Data after sorting:");
		}


		if (IsSorted(data, size))
		{
			System.out.print("\nCorrectly sorted ");
			System.out.print(size);
			System.out.print(" elements in ");
			System.out.print(elapsed);
			System.out.print("ms");
		}
		else
		{
			System.out.print("ERROR!: INCORRECT SORTING!");
			System.out.print("\n");
		}
		System.out.print("\n-------------------------------------------------------------\n");
	}
	
	/*****************************************************************************/

	public static boolean IsSorted(int data[], int size)
	{
		int i;
		
		for(i=0; i<(size-1); i++)
		{
			if(data[i] > data[i+1])
				return false;
		}
		return true;
	}
	
	/*****************************************************************************/

	public static void InsertionSort(int data[], int size)
	{
		//System.out.println("InserionSort");

		 for (int i = 1; i < size; i++) {
	            int temp = data[i];
	           
				int j;
				for (j = i - 1; j >=0 && data[j] > temp; j--) {
					data[j + 1] = data[j];
				}
				
				data[j + 1] = temp;
	        }		
		
	}
	/*****************************************************************************/

	public static void Merge(int data[], int lo, int mid, int hi)
	{
		int n1 = mid - lo + 1;
		int n2 = hi - mid;

		// Create temp arrays
		int L[] = new int[n1 + 1];
		int R[] = new int[n2 + 1];

		// Copy data into temp arrays
		for (int i = 0; i < n1; i++) {
			L[i] = data[lo + i];
		}
		for (int j = 0; j < n2; j++) {
			R[j] = data[mid + 1 + j];
		}

		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;

		int i = 0;
		int j = 0;
		for (int k = lo; k <= hi; k++) {
			if (L[i] <= R[j]) {
				data[k] = L[i];
				i++;
			} else {
				data[k] = R[j];
				j++;
			}
		}
	}

	public static void MergeSort(int data[], int lo, int hi)
	{
		//System.out.println("MergeSort");

		if (lo == hi) {
			return;
		}

		int mid  = (lo + hi) / 2;
		MergeSort(data, lo, mid);
		MergeSort(data, mid + 1, hi);
		Merge(data, lo, mid, hi);
	}
	/*****************************************************************************/

	public static int medianOfThree(int index1, int index2, int index3, int[] data) {
		int val1 = data[index1];
		int val2 = data[index2];
		int val3 = data[index3];

		if ((val1 <= val2 && val2 <= val3) || (val3 <= val2 && val2 <= val1)) {
			return index2;
		} else if ((val2 <= val1 && val1 <= val3) || (val3 <= val1 && val1 <= val2)) {
			return index1;
		} else {
			return index3;
		}
	}	

	public static int Partition(int data[], int lo, int hi)
	{
		// this caused a stack overflow. Switched to random pivot selection below.
		//int pivot = data[hi];
		
		// Random pivot selection 

		int randIndex = rand.nextInt(hi - lo + 1) + lo;
		swap(randIndex, hi, data);
		int pivot = data[hi];

		// Median-of-random-three pivot selection 

		// int randIndex1 = rand.nextInt(hi - lo + 1) + lo;
		// int randIndex2 = rand.nextInt(hi - lo + 1) + lo;
		// int randIndex3 = rand.nextInt(hi - lo + 1) + lo;
		// int pivotIndex = medianOfThree(randIndex1, randIndex2, randIndex3, data);
		// swap(pivotIndex, hi, data);
		// int pivot = data[hi];

		int x = lo - 1;

		for (int j = lo; j < hi; j++) {
			if (data[j] <= pivot) {
				x++;
				swap(x, j, data);
			}
		}
		swap(x + 1, hi, data);
		return x + 1;
	}

	public static void QuickSort(int data[], int lo, int hi, int depth)
	{
		//System.out.println("QuickSort");
		//System.out.println("QuickSort depth: " + depth);

		if (depth > maxDepth) {
			maxDepth = depth;
			// System.out.println("New max depth: " + maxDepth);
		}

		if (lo >= hi) {
			return;
		}
		
		// use insertion sort for small subarrays

		if (hi - lo + 1 < 20) {
			for (int i = lo + 1; i <= hi; i++) {
				int temp = data[i];
				int j;
				for (j = i - 1; j >= lo && data[j] > temp; j--) {
					data[j + 1] = data[j];
				}
				data[j + 1] = temp;
			}
			return;
		}

		// switch to merge sort if depth exceeds ideal depth
		
		if (depth > idealDepth) {
			MergeSort(data, lo, hi);
			return;
		}

		int pivot = Partition(data, lo, hi);
		QuickSort(data, lo, pivot - 1, depth + 1);
		QuickSort(data, pivot + 1, hi, depth + 1);

	}
	/*****************************************************************************/

	public static void STLSort(int data[], int size)
	{
		//Your code should simply call the STL sorting function  
		//System.out.println("STLSort");
		Arrays.sort(data);
		
	}
	/*****************************************************************************/
	
	public static void swap(int x , int y ,int data[])
	{
		int temp = data[x];
		data[x] = data[y];
	    data[y] = temp;
	}
	
	/*****************************************************************************/
	
	public static void printData(int[] data, int size, String title)
	{
		int i;

		System.out.print("\n");
		System.out.print(title);
		System.out.print("\n");
		for (i = 0; i < size; i++)
		{
			System.out.print(data[i]);
			System.out.print(" ");
			if (i % 10 == 9 && size > 10)
			{
				System.out.print("\n");
			}
		}
	}

}
