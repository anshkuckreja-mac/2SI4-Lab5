
public class MaxHeap {
	
	// define the heap variable, stored as an Integer[] array
	private Integer[] heap;
	
	private int arr_size; // memory of array
	private int heap_size; // size of heap (number of items stored)
	
	// sets an empty heap
	public MaxHeap(int heap_size) {
		this.arr_size = 0;
		this.heap_size = 0;
		
		this.heap = new Integer[0];
		
	}
	
	
	public MaxHeap(Integer[] someArray) {
		
		// 1 higher because index starts at 1 for heaps
		this.heap = new Integer[someArray.length + 1];
		
		// use the heap function made below to sort the someArray array
		for (int i = heap.length; i > 0; i--) {
			maxheap_sort(someArray, i);
		}
		
		// once sorted, just append each value to the heap variable
		for (int j = 0; j < heap.length; j++) {
			this.heap[j] = someArray[j];
		}
		
		
	}
	
	// this function will be recursively called to swap out parents and children based on whichever ones' bigger
	// the integer 'node' passed is the index of the current node acting as the root of a subtree to be heaped
	public void maxheap_sort(Integer arr[], int node) {
		
		// define the parent and children to be used as indices, as well as a temp variable for swapping nodes
		int largest, lc, rc, temp;
		
		// these are the indices for the supposedly "largest" node and its children
		largest = node;
		lc = (2*node) + 1; 
		rc = (2*node) + 2; 
		
		// first check if the right child is bigger than its parent
		if (arr[largest] < arr[rc]) {
			
			// if it's bigger, set the "largest" title to the rc node
			largest = rc;
		}
		// now, compare the modified largest to the other child and assign
		// this essentially identifies the largest node index of the parent and its children
		if (arr[largest] < arr[lc]) {

			largest = lc;
		}
		
		// now swap the parent with the biggest node, or don't swap if children are smaller
		if (largest != node) {
			
			temp = arr[node];
			arr[node] = arr[largest];
			arr[largest] = temp;
			
			// so now we have swapped out a section, now call the function recursively to keep ironing out any errors
			// this will stop once the largest == node, meaning the parent is bigger than all children
			
			maxheap_sort(arr, largest);
			
		}	
	}
	// inserts a node of value n into the tree
	public void insert(int n) {
		
		
		
	}
	
	// just prints out all the values of the heap into a string in typical array order
	public String toString() {
		
		String str = "";
		
		for (int i = 0; i < heap.length - 1; i++) { str += heap[i] + " "; }
		
		str += heap[-1];
		
		return str;
		
	}
	
	public static void main(String[] args) {
		
		Integer[] a = {1,2,3,4,5,6};
		
		
		
	}

}
