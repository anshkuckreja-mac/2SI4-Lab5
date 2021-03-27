package testpkg;

public class MaxHeap {
	
	// define the heap variable, stored as an Integer[] array
	private Integer[] heap;
	
	private int arr_size; // memory of array
	private int heap_size; // size of heap (number of items stored)
	
	
	
	
	// sets an empty heap
	public MaxHeap(int heap_size) {
		
		
		this.arr_size = heap_size;
		this.heap_size = 0;
		
		this.heap = new Integer[heap_size];
		
	}
	
	
	// constructor that makes a heap given an unsorted array
	// starting at the first non-leaf node, recursively left-order traverse the tree with root that node
	// then work your way up from the bottom to the root of the full list, ensuring the entire tree is made
	
	
	public MaxHeap(Integer[] someArray) {
		
		// 1 higher because index starts at 1 for heaps
		this.heap = new Integer[someArray.length];
		
		
		
		// need to start our recursion with the first non-leaf node, for which the formula is
		int nonleaf_ind = someArray.length/2 - 1 ;
		
		// System.out.println("nonleaf: " + nonleaf_ind);
		
		// use the heap function made below to sort the someArray array
		for (int i = nonleaf_ind; i >= 0; i--) {
			
			maxheapsort(someArray, i);
		}
		
		// once sorted, just append each value to the heap variable
		for (int j = 0; j < someArray.length; j++) {
			//System.out.println(someArray[j]);
			this.heap[j] = someArray[j];
		}
		
		heap_size = heap.length;
		arr_size = someArray.length;
		
		
	}
	
		
	
	
	// this function will be recursively called to swap out parents and children based on whichever ones' bigger
	// the integer 'node' passed is the index of the current node acting as the root of a subtree to be heaped
	public void maxheapsort(Integer arr[], int node) {
		
		// System.out.println("now sorting subtree with root index: " + node + " , value: " + arr[node]);
		
		// define the parent and children to be used as indices, as well as a temp variable for swapping nodes
		int largest, lc, rc, temp;
		
		// these are the indices for the supposedly "largest" node and its children
		largest = node;
		lc = (2*node) + 1; 
		rc = (2*node) + 2; 
		
		/*System.out.println("largest: " + largest);
			System.out.println("lc: " + lc);
			System.out.println("rc: " + rc);
*/
		
		
		// first check if the right child is bigger than its parent
		// first 'if' confirms that there's a right child (there may not be)
		if (rc < arr.length && arr[rc] != null) {
			if (arr[largest] < arr[rc]) {
				
				// System.out.println("yes rc");
				
				// if it's bigger, set the "largest" title to the rc node
				largest = rc;
			}
		}
		
		// now, compare the modified largest to the other child and assign
		// this essentially identifies the largest node index of the parent and its children
		if (lc < arr.length && arr[lc] != null) {
			if (arr[largest] < arr[lc]) {
				
				// System.out.println("yes lc");

				largest = lc;
			}
		}
		
		
	
		
		// so now we have swapped out a section, now call the function recursively to keep ironing out any errors
		// this will stop once the largest == node, meaning the parent is bigger than all children
					
		if (largest != node) {
			
			// System.out.println("swapping...");
			
			temp = arr[node];
			arr[node] = arr[largest];
			arr[largest] = temp;
			
			
			// so in this case, 'largest' is now the child you switched with, so you'll be investigating that subtree
			
			// again tho, don't want to call the function unless it has parents
			
			if (largest <= arr.length/2 - 1) {
				
				maxheapsort(arr, largest);
			}
				
			
			
		// if the largest is the node and nothing was switched, no need to traverse the subtree any further
		}
	}
	
	
	// accessor method gets the size of the heap
	public int getSizeHeap() {
		return this.heap_size;
	}
	
	// accessor method gets the size of the array
	public int getSizeArr() {
		return this.arr_size;
	}
	
	// accessor method gets the heap
	public Integer[] getHeap() {
		return this.heap;
	}
	
	
	
	// inserts a node of value n into the tree
	// 1) makes new array of either same size or double size if needed (tbh don't know if need to make new array if same size but whatever) & 
	// change the size variables to make them more accurate
	// 2) then loops through the new array and fills it in with the values from the og heap, and then adds the 'n' on top
	// 3) just like in constructor 2, start from the first non-leaf node and recursively left-order traverse to sort everything out
	
	
	public void insert(int n) {

		/*System.out.println("heap_size: "+ heap_size);
		System.out.println("arr_size: "+ arr_size);
		*/
		
		int mult = 1;
		
		// if there's no space for an addition, array needs to be doubled
		if (arr_size < heap_size + 1) {
			mult = 2;
		}
		
		// make a new array of new length and copy current values to it
		Integer[] newarr = new Integer[arr_size*mult];
		
		for (int i = 0; i < arr_size; i++) {
			newarr[i] = heap[i];
		}
		
		// this is now the new heap
		heap = newarr; 
		arr_size *= mult;
		
		// append the new value to the heap
		heap_size += 1;
		heap[heap_size-1] = n;
		
		
		/*System.out.println("heap_size: "+ heap_size);
		System.out.println("arr_size: "+ arr_size);
		*/
		
		// again, need to start our recursion with the first non-leaf node, for which the formula is
		int nonleaf_ind = (heap_size)/2 - 1 ;
		
		
		
		// use the heap function made above to sort the someArray array
		for (int i = nonleaf_ind; i >= 0; i--) {
			
			maxheapsort(heap, i);
		}
		
		
	}
	
	
	// so this is literally just sorting an array to descending order
	// start from the first value, find the furthest value in the array that it's smaller than and swap
	// continue looping and swapping and eventually, everything sorts itself ut
	public static void heapsort(Integer[] arrayToSort) {
		
		// copy the input to an array that will be arrayToSort
		// heap = arrayToSort;
		
		int temp;
		
		for (int i = 0; i < arrayToSort.length; i++) {
			
			for (int j = arrayToSort.length-1; j > i; j--) {
				
				// start from the back, if it's smaller, put it at the end
				if (arrayToSort[j] > arrayToSort[i]) {
					temp = arrayToSort[i];
					arrayToSort[i] = arrayToSort[j];
					arrayToSort[j] = temp;
				}
			}
		}
		
	
		
	}
	
	
	// this deletes the max (root) of a heap and then reshuffles everything
	// 1) puts the last leaf as the root and adjust the size variables
	// 2) same thing, last non-leaf node and sort the boi
	
	public int deleteMax() {
		
		// essentially using a 'temp', but inserting the last leaf into the root
		int root = heap[0];
		int lastleaf = heap[heap_size - 1];
		heap[0] = lastleaf;
		
		//reduce the size of the heap variable
		heap_size -= 1;
		
		
		// use the heap function made below to sort the heap
		// only need to start at the leaf bc we want to search the entire tree
		
		maxheapsort(heap, heap.length/2 - 1);
		
		// return the max, which is the root of the tree
		return root;
	}
	
	
	// just prints out all the values of the heap into a string in typical array order
	public String toString() {
		
		String str = "";
		
		for (int i = 0; i <= heap_size - 1; i++) { str += heap[i] + ","; }
		
		
		return str;
		
	}
	
	public static void main(String[] args) {
		
	
	}

}
