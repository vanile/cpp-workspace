/**
 * 
 */
package edu.cpp.cs.cs240.assignment1;

/**
 * @author thefurryman
 *
 */
public class IntSetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//    	case1();
//		case2();
//		case3();
//		case4();
//		case5();
		

	}
	
	public static void case1() {
		IntSet set1 = new IntSet();
		IntSet set2 = new IntSet();
		
		set1.addElement(1);
		set1.addElement(2);
		set1.addElement(3);

		
		set2.addElement(2);
		set2.addElement(1);
		set2.addElement(3);
		
		
		System.out.println(set1);
		System.out.println(set2);
		System.out.println("");
		
		System.out.println("Contain " + set1.contain(3));
		System.out.println("Subset " + set1.subsetOf(set2));
		System.out.println("isEqual " + set1.isEqual(set2));
		System.out.println("Union " + set1.union(set2));
		System.out.println("Intersection " + set1.intersection(set2));
		System.out.println("Complement " + set1.complement(set2));
		
	}
	
	public static void case2() {
		IntSet set1 = new IntSet();
		IntSet set2 = new IntSet();
		
		set1.addElement(1);
		
		set2.addElement(1);
		set2.addElement(2);
		
		System.out.println(set1);
		System.out.println(set2);
		System.out.println("");
		
		System.out.println("Contain " + set1.contain(3));
		System.out.println("Subset " + set1.subsetOf(set2));
		System.out.println("isEqual " + set1.isEqual(set2));
		System.out.println("Union " + set1.union(set2));
		System.out.println("Intersection " + set1.intersection(set2));
		System.out.println("Complement " + set1.complement(set2));
	}
	
	public static void case3() {
		IntSet set1 = new IntSet();
		IntSet set2 = new IntSet();
		
		set1.addElement(1);
		set1.addElement(2);
		set1.addElement(3);
		
		set2.addElement(2);
		set2.addElement(3);
		set2.addElement(4);
		set2.addElement(5);
		
		System.out.println(set1);
		System.out.println(set2);
		System.out.println("");
		
		System.out.println("Contain " + set1.contain(3));
		System.out.println("Subset " + set1.subsetOf(set2));
		System.out.println("isEqual " + set1.isEqual(set2));
		System.out.println("Union " + set1.union(set2));
		System.out.println("Intersection " + set1.intersection(set2));
		System.out.println("Complement " + set1.complement(set2));
	}
	
	public static void case4() {
		IntSet set1 = new IntSet();
		IntSet set2 = new IntSet();
		
		set1.addElement(1);
		
		set2.addElement(2);
		set2.addElement(3);
		
		System.out.println(set1);
		System.out.println(set2);
		System.out.println("");
		
		System.out.println("Contain " + set1.contain(3));
		System.out.println("Subset " + set1.subsetOf(set2));
		System.out.println("isEqual " + set1.isEqual(set2));
		System.out.println("Union " + set1.union(set2));
		System.out.println("Intersection " + set1.intersection(set2));
		System.out.println("Complement " + set1.complement(set2));
	}
	
	public static void case5() {
		IntSet set1 = new IntSet();
		IntSet set2 = new IntSet();
		
		set1.addElement(3);
		
		System.out.println(set1);
		System.out.println(set2);
		System.out.println("");
		
		System.out.println("Contain " + set1.contain(3));
		System.out.println("Subset " + set1.subsetOf(set2));
		System.out.println("isEqual " + set1.isEqual(set2));
		System.out.println("Union " + set1.union(set2));
		System.out.println("Intersection " + set1.intersection(set2));
		System.out.println("Complement " + set1.complement(set2));
		
	}

}
