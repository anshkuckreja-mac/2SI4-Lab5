package testpkg;

public class Demo {

	
	public static void main(String[] args) {
		
		Integer[] a = {4, 11, 10, 3, 5, 1};
		
		MaxHeap heap1 = new MaxHeap(a);
		
		heap1.insert(21);
		
		//int yeet = heap1.deleteMax();
		
	
		System.out.println(heap1.toString());
		//System.out.println(heap1.deleteMax());
	}
}
