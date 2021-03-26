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
		
		// System.out.println("now sortiing subtree with root index: " + node + " , value: " + arr[node]);
		
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
		
		
		// System.out.println("largest after child search: " + largest);
		
		// now swap the parent with the biggest node, or don't swap if children are smaller
		if (largest != node) {
			
			// System.out.println("swapping...");
			
			temp = arr[node];
			arr[node] = arr[largest];
			arr[largest] = temp;
			
			// so now we have swapped out a section, now call the function recursively to keep ironing out any errors
			// this will stop once the largest == node, meaning the parent is bigger than all children
			
			// so in this case, 'largest' is now the child you switched with, so you'll be investigating that subtree
			
			// again tho, don't want to call the function unless it has parents
			
			if (largest <= arr.length/2 - 1) {
				
				maxheapsort(arr, largest);
			}
				
			
			
			
		}else {
			// System.out.println("subtree sorted, moving on...");
			
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
	
	
	// so this is literally just reversing an array
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
	public int deleteMax() {
		
		// essentially using a 'temp', but inserting the last leaf into the root
		int root = heap[0];
		int lastleaf = heap[heap_size - 1];
		heap[0] = lastleaf;
		
		//reduce the size of the heap variable
		heap_size -= 1;
		
		
		// use the heap function made below to sort the someArray array
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
