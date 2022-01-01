/*
 * A1ByaruhangaAsiimwe 
 * 
 * COMP 2140 SECTION A01
 * INSTRUCTOR	AKCORA
 * ASSIGNMENT 	ASSIGNMENT #1
 * @author		Byaruhanga Asiimwe Sami,
 * @version		FEBRURAY 8TH, 2020
 * 
 * PURPOSE		Comparison of 3 sorting algorithms
 * SOURCES		Youtube video to clarify radix sort
 * 				https://youtu.be/XiuSW_mEn7g
 */
import java.util.*;

public class A1ByaruhangaAsiimwe {
	public static void main(String[] args) {
		
		int[] arraySize = {10_000, 20_000, 30_000, 40_000, 50_000};
		
		//TESTING THE ALGORITHMS FOR SPECIFIED ARRAYSIZES 
		System.out.println("Merge sort2 is used for mergeSortInefficient below");
		for(int i = 0;i<arraySize.length;i++) {
			System.out.println("____________________________________");
			System.out.println("Algorithm Name \t|"+ "ArraySize|" + "time (ms)|");
			testing(arraySize[i]);
			System.out.println("____________________________________|");
			System.out.println("\n ");
		}
		System.out.println("End of Processing");
	}
	
	
	


	/******************************************************************************
	 * print 
	 * PURPOSE: To print any given array and used in testing small sizes to see
	 * 			if the array is sorted
	 *****************************************************************************/
	public static void print(int[] a) {
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}

	/******************************************************************************
	 * mergeSort call
	 * PURPOSE: 	Call Recursive Merge sort on array with help of temporary array
	 * PARAMETER:	An array to be sorted 
	 *****************************************************************************/
	public static void mergeSort(int[] array) {
		int[] temp = new int[array.length];
		mergeSort(array,0,array.length,temp);
	}
	
	/******************************************************************************
	 * mergeSort helper
	 * PURPOSE:		To do a recursive merge sort on an array 
	 * PARAMETERS:	An array, indices start, end and temporary array 
	 *****************************************************************************/
	private static void mergeSort(int[] array, int start, int end, int[] temp) {
		int mid;
		if(1<end-start) {
			mid = start + (end-start)/2;
			mergeSort(array,start,mid,temp);
			mergeSort(array,mid,end,temp);
			merge(array,start,mid,end,temp);
		}
	}

	/******************************************************************************
	 * merge
	 * PURPOSE:		To merge two sorted sublists into one sorted list
	 * PARAMETERS:	An array, three indices start, mid, end, temp array	
	 *****************************************************************************/
	private static void merge(int[] array, int start, int mid, int end, int[] temp) {
		int currL =	start;
		int currR = mid;
		int currT;
		
		for(currT=start; currT<end; currT++) {
			if(currL < mid && (currR>=end || array[currL] < array[currR])) {
				temp[currT] = array[currL];
				currL++;
			} else {
				temp[currT] = array[currR];
				currR++;
			}
		}
		
		for(currT=start; currT<end; currT++) {
			array[currT] = temp[currT];
		}
	}
	//END OF MERGE SORT ALGORITHM
	
	
	
	/******************************************************************************
	 * mergeSortInefficient call
	 * PURPOSE: 	Call Recursive Merge sort on array without help of temp array
	 * PARAMETER:	An array to be sorted 
	 *****************************************************************************/
	public static void mergeSortInefficient(int[] array) {
		mergeSortInefficient(array, 0, array.length-1);
		
	}

	/******************************************************************************
	 * mergeSortInefficient helper
	 * PURPOSE:		To do a recursive merge sort on an array 
	 * PARAMETERS:	An array, indices start, end 
	 *****************************************************************************/
	private static void mergeSortInefficient(int[] array, int start, int end) {
		int mid;
//		if(1<end-start) { //when we still have values
		if(end<=start)return;
//			mid = start + (end-start)/2;
			mid = (end+start)/2;
			mergeSortInefficient(array,start,mid);
			mergeSortInefficient(array,mid+1,end);
			merge(array,start,mid,end);
//		}
		
	}
	
	/******************************************************************************
	 * merge
	 * PURPOSE:		To merge two sorted sublists into one sorted list
	 * PARAMETERS:	An array, three indices start, mid, end	
	 *****************************************************************************/
	private static void merge(int[] array, int start, int mid, int end) {
		int size1 = mid-start +1;
		int size2 = end-mid;

		int[] leftArray = new int[size1];
		int[] rightArray = new int[size2];
		
		for(int i=0; i<size1;i++) { //size1 
			leftArray[i] = array[start+i];
		}
		
		for(int j=0; j<size2;j++) { //size2 wrks
			rightArray[j] = array[mid+j+1];
		}
		
		int currL=0;
		int currR=0;

		for(int curr=start; curr<end+1; curr++) {
			if(currL<size1 && currR<size2) {
				if(leftArray[currL]<rightArray[currR]) {
					array[curr] = leftArray[currL];
					currL++;
				}else {
					array[curr] = rightArray[currR];
					currR++;
				}
			} else if(currL <size1) {
				array[curr] = leftArray[currL];
				currL++;
			}else if(currL <size2) {
				array[curr] = rightArray[currR];
				currR++;
			}
		}
	}
	//END OF MERGE SORT INEFFICIENT ALGORITHM 


	
	//END OF MERGESORT WITHOUT TEMP
	
	
	/******************************************************************************
	 * quickSort call
	 * PURPOSE: 	To call the private recursive quick sort method
	 * PARAMETER:	An array to be sorted 
	 *****************************************************************************/
	public static void quickSort(int[] array) {
		if(array==null) return;
		if(array.length < 2) return;
		quickSort(array,0,array.length);
	}
	
	/******************************************************************************
	 * swap
	 * PURPOSE: 	To swap two values in an array
	 * PARAMETER:	An array to be swapped and the two indices
	 *****************************************************************************/
	private static void swap(int[]array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/******************************************************************************
	 * quickSort helper
	 * PURPOSE: 	Recursive quickSort method
	 * PARAMETER:	the array, starting and ending indices
	 *****************************************************************************/
	private static void quickSort(int[]array, int start, int end) {
		if(2==(end-start)) { //if we have only 2 elements
			if(array[start+1] < array[start]) {
				swap(array,start, start+1);
			}
		}
		
		if(start+2 < end) {
			int pivotPosn = partion(array,start,end);
			quickSort(array,start,pivotPosn);
			quickSort(array,pivotPosn+1,end);
		}
	}
	
	/******************************************************************************
	 * choosePivot
	 * PURPOSE: To choose a pivot from items in the array using the median of
	 * 			three method and swaps the pivot into start of the array
	 *****************************************************************************/
	private static int[] choosePivot(int[] array, int start, int end) {
		int median;
		median = (end+start)/2;
		swap(array,start,median);
		return array;
		//if array does not have 3 values to compute the last elemnt is pivot
	}

	
	/******************************************************************************
	 * Partition
	 * PURPOSE: 	Non-recursive partition method 
	 * PARAMETERS:	Array, start and end 
	 *****************************************************************************/	
	private static int partion(int[] array, int start, int end) {
//		int pivot = a[start];	//take pivot to be the first elemnt
//		int pivot = choosePivot(a,start,end);
		choosePivot(array,start,end);
		int pivot =array[start]; //first element 
//		System.out.println(pivot);
		int bigStart = (start +1); //bigstart is the next elments 
		for(int curr =start+1; curr<end; curr++) {
			if(array[curr]<pivot) {
				swap(array,bigStart, curr);
				bigStart++;
			}
			//do nothing if it belongs to bigs
		}
		
		swap(array, start, bigStart-1);
		return bigStart-1;
	}
	//END OF QUICK SORT ALGORITHM
	
	
	
	//END OF QUICK SORT ALGORITHM
	
	
	/******************************************************************************
	 * radixSort
	 * PURPOSE: 	To call count sort which helps in implementing radix sort
	 * PARAMETER:	Array to be sorted
	 *****************************************************************************/
	public static void radixSort(int[] array) {
		int max = max(array);
		int place = 1; //starting radix place
		while(max/place > 0) {
			countSort(array,place);
			place*=10; //step of 10 to next radix place
		}
	}
	
	/******************************************************************************
	 * max
	 * PURPOSE: 	To get the max value in an array will be used in radix sort 
	 * PARAMETER:	Array to be sorted
	 *****************************************************************************/
	private static int max(int[] array) {
		int max = array[0];
		for(int i=0;i<array.length;i++) {
			if(array[i]>max) {
				max = array[i];
			}
		}
		return max;
	}
	
	/******************************************************************************
	 * countSort
	 * PURPOSE: 	To count sort our array with each call
	 * PARAMETER:	Array to be sorted, radix place
	 *****************************************************************************/
	private static void countSort(int[] array, int place) {
		int size = array.length;
		int[] sortedArray = new int[size];
		int[] frequency = new int[size];
		
		for(int i=0; i<size; i++) { 
			frequency[(array[i]/place)%10]++;
		}
		
		for(int i=1;i<size;i++) {
			frequency[i] += frequency[i-1];
		}
		
		for(int i=size-1;i>=0;i--) {
			sortedArray[frequency[(array[i]/place)%10]-1] = array[i];
			frequency[(array[i]/place)%10]--;
		}
		
		for(int i=0; i<size;i++) {
			array[i] = sortedArray[i];
		}
	}
	//END OF RADIX SORT ALGORITHM 
	
	
	
	//END OF RADIX SORT
	
	
	/******************************************************************************
	 * isSorted
	 * PURPOSE:		To verify if the array is sorted, returns true if sorted 
	 * 				and returns false otherwise.
	 * 
	 *****************************************************************************/
	public static boolean isSorted(int[] array) {
		boolean result = true;
		for(int i=0; i<array.length-1;i++) {
			if(array[i] > array[i+1]) {
				result =  false;
			}
		}
		return result;
	}
	
	
	/******************************************************************************
	 * fillArray
	 * PURPOSE: 	To fill an array with values to sort
	 * PARAMETER:	An array
	 *****************************************************************************/
	public static void fillArray(int[] array) {
		int n = array.length;
		if(n==0) {
			System.out.println("Provided a null array please double check");
		}
		
		for(int i=0;i<n;i++) {
			array[i] += i;
		}
	}
	
	
	
	/******************************************************************************
	 * randomizeArray
	 * PURPOSE: 	To perform random swaps on an array
	 * PARAMETER:	An array to be randomized
	 *****************************************************************************/
	public static void randomizeArray(int[] array, int n) { //instruction a little bit unclear so used position i and 2

		for(int i=0;i<n;i++) {
			int position1 = new Random().nextInt(array.length);
			int position2 = new Random().nextInt(array.length);
			swap(array,position1,position2);
		}
	}
	
	
	/********************************************************************************
	 * testing
	 * PURPOSE: 	To test the algorithms so that i can compare there sorting times
	 *******************************************************************************/
	public static void testing(int arraySize) {
		final int TIMES = 100;
		int[] array = new int[arraySize];
		int numSwaps = (int) (0.25*arraySize);
		
		//fill our array 
		fillArray(array);


		
		//MERGESORT ALROGRITHM TESTING
		long[] mergeSortTimes = new long[TIMES];
		for(int i=0; i<TIMES;i++) {
			randomizeArray(array,numSwaps);
			long startTime = System.currentTimeMillis();
			mergeSort(array);
			long endTime = System.currentTimeMillis();
			if(isSorted(array) ==false) {
				System.out.println("Error in mergeSort");
			}
			long time = endTime - startTime;
			mergeSortTimes[i] =time;
		}
		
		double mergeSortMean = arithmeticMean( mergeSortTimes);
		System.out.println("mergeSort \t \t| " + arraySize + "\t "+ " | "+ mergeSortMean +" ms\t|");
		

		
		//MERGESORT INEFFICIENT TESTING
		long[] mergeSortInefficientTimes = new long[TIMES];
		for(int i=0; i<TIMES;i++) {
			randomizeArray(array,numSwaps);
			long startTime = System.currentTimeMillis();
			mergeSortInefficient(array);
			long endTime = System.currentTimeMillis();
			if(isSorted(array) ==false) {
				System.out.println("Error in mergeSort inefficient sort");
			}
			long time = endTime - startTime;
			mergeSortInefficientTimes[i] =time;
		}
		
		double mergeSort2 = arithmeticMean( mergeSortInefficientTimes);
		System.out.println("mergeSort2 \t \t| " + arraySize + "\t " + " | "+ mergeSort2 +" ms\t|");
		
		
		//QUICK SORT TESTING
		long[] quickSortTimes = new long[TIMES];
		for(int i=0; i<TIMES;i++) {
			randomizeArray(array,numSwaps);
			long startTime = System.currentTimeMillis();
			quickSort(array);
			long endTime = System.currentTimeMillis();
			if(isSorted(array) ==false) {
				System.out.println("Error in quickSort");
			}
			long time = endTime - startTime;
			quickSortTimes[i] =time;
		}
		
		double quickSortMean = arithmeticMean( quickSortTimes);
		System.out.println("QuickSort \t \t| " + arraySize + "\t "+ " | "+ quickSortMean +" ms\t|");
		
		
		//RADIX SORT TESTING
		long[] radixSortTimes = new long[TIMES];
		for(int i=0; i<TIMES;i++) {
			randomizeArray(array,numSwaps);
			long startTime = System.currentTimeMillis();
			radixSort(array);
			long endTime = System.currentTimeMillis();
			if(isSorted(array) ==false) {
				System.out.println("Error in radix sort");
			}
			long time = endTime - startTime;
			radixSortTimes[i] =time;
		}
		
		double radixSortMean = arithmeticMean( radixSortTimes);
		System.out.println("RadixSort \t \t| " + arraySize + "\t "+ " | "+ radixSortMean +" ms\t|");
	}

	
	/*******************************************************************
	* arithmeticMean
	*
	* Purpose: Compute the average of long values.
	* To avoid long overflow, use type double in the computation.
	******************************************************************/
	public static double arithmeticMean( long data[] ) {
	double sum = 0;
	for (int i = 0; i < data.length; i++)
		sum += (double)data[i];
	return sum / (double)data.length;
	} // end arithmeticMean

}//END OF THE A1ByaruhangaAsiimwe


/******************************************************************************
 * 						REPORT
 * 
 * 1. The table is created in after running main
 * 
 * 2. The efficient version of mergeSort was faster than quick sort for data 
 * 	  of array size 10,000 because mergeSort does less recursive calls and has 
 * 	  a run time complexity of O(nlogn) while quick sort has O(n^2). 
 * 
 * 3. The inefficient mergeSort has a higher run time compared to efficient
 * 	  mergeSort (example: mergeSort had 0.82 ms and ineffecient mergeSort had
 * 	  1.3 ms in one of the run) and in all cases the inefficient mergesort
 *    had a higher cost.
 *    
 *4. No mergerSort was faster than radix sort. This is because mergeSort 
 *	 complexity is O(nlogn) while radix sort has worstcase O(n*w) where
 *	 n- number of keys and w is key length.
 *
 *5. Radix sort is slowed by having to get keys all the time and for large
 *	values this slows it down drastically (due having to repeated process the 
 *	key i.e. the bottleneck effect).
 * 	 
 *****************************************************************************/



























