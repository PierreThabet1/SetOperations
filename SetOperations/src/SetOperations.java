import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This class has methods for setting a treeset of Integer and carrys out
 * operations on it as well.
 * @author Pierre
 *
 */
public class SetOperations { // SetOperations class begin.
	
	/**
	 * This is a variable for the first treeset.
	 */
	public static TreeSet<Integer> A = new TreeSet<>();
	
	/**
	 * This is a variable for the second treeset.
	 */
	public static TreeSet<Integer> B = new TreeSet<>();
	
	/**
	 * This variable holds the operation character, like +, * and -.
	 */
	public static char op;

	public static void main(String[] args) { // main Begin.
		
		String s ="";
		int cb = 0;
		
		/**
		 * This loop checks if the input has two ']' to mark the end.
		 */
		while (cb != 2) { // Begin of while loop.
			
			cb = 0;
			
			System.out.println("Type two diffrent sets, each set between []."
					+ " Only positive integers are allowed.");
			System.out.println("Use between both sets +,*,- for union, "
					+ "intersection, and difference.");

			 s = TextIO.getlnString();
			 
			 for (char i : s.toCharArray()) {
				 
				 if (i == ']') {
					 
					 cb++;
					 
				 }
				 
			 }
			 	 
		} // End of while loop.
		

		setTree(s);
		System.out.println(setCalc(A, B, op));

	
		
	} // End of main.

	/**
	 * 
	 * This method applys three methods, depening on the op parameter.
	 * The operation is between two treeSets, whom given as parameters.
	 * Methods are either, addAll, retainAll or removeAll.
	 * 
	 * @param first TreeSet<Integer>.
	 * @param second TreeSet<Integer>.
	 * @param op Char.
	 * @return TreeSet<Integer>.
	 * @throws IllegalArgumentException In case of negative elements, or 
	 * if the operator is none of the +,* or -.
	 */
	public static TreeSet<Integer> setCalc (TreeSet<Integer> first, TreeSet<Integer> second, char op) 
			throws IllegalArgumentException { // Begin of setCalc method.

		/**
		 * This variable will be returned, if none of the given methods
		 * have been triggered. It will return -1 back and an Error message
		 * as well.
		 */
		TreeSet<Integer> bad = new TreeSet<>();
		bad.add(-1);
		
		/**
		 * checking if there are negative elements in either of the 
		 * given treeSets.
		 */
		for (int i : first) {

			if (i < 0) {

				throw new IllegalArgumentException("This "
						+ "set contains negative element.");

			}

		}

		for (int i : second) {

			if (i < 0) {

				throw new IllegalArgumentException("This "
						+ "set contains negative element.");

			}

		}

		/**
		 * assigning the two TreeSets member variables, A and B, the values
		 * of the given parameters respectively.
		 */
		TreeSet<Integer> A = first;
		TreeSet<Integer> B = second;
		
		/**
		 * Choosing the right operator, according to the given op parameter.
		 */
		switch(op) {

		case '+' -> {

			A.addAll(B);
			break;

		}

		case '*' -> {

			A.retainAll(B);
			break;

		}

		case '-' -> {

			A.removeAll(B);
			break;

		}

		default -> {

			System.out.println("Invalid operation.");
			return bad;

		}

		}

		return A;

	} // End of setCalc method.
	
	/**
	 * This method reads and sets the given String input in the main
	 * and divides each part into two parts, where each TreeSet, A and B
	 * takes one part, with assigning the operator as well.
	 * 
	 * @param tree String.
	 */
	public static void setTree(String tree) { // Begin of setTree method.
		
		tree = tree.replace(" ", "");
		tree = tree.replace(",", "");
		
		/**
		 * turning the String into a charArray to iterate it.
		 */
		char[] input = tree.toCharArray();
		
		/**
		 * Flag marker for the second while loop. This concerns the 
		 * TreeSet of B, the second TreeSet.
		 */
		boolean flag = false;

		int i = 0;

		while(input[i] != ']') {

			try {
				
				if (input[i] != '[' && i ==0) {

					throw new IllegalArgumentException("'[' was "
							+ "expected at the start!");

				}
				
			}

			catch(IllegalArgumentException iae) {
				System.out.println(iae.getMessage());

			}

			if (input[i] == '[' && i == 0) {

				i++;
				continue;

			}

			try {

				if(!Character.isDigit(input[i])) {

					throw new IllegalArgumentException("a positive integer "
							+ "was expected!");

				}
				
			}
			
			catch(IllegalArgumentException iae) {

				System.out.println(iae.getMessage());
				i+=2;
				continue;

			}

			if (flag == false) {

				A.add(Character.getNumericValue(input[i]));
				i++;
				continue;

			}

		}

		i++;
		op = input[i];
		i++;
		int j = i;
		flag = true;
		
		while(flag == true && input[j] != ']') {

			try {

				if (input[j] != '[' && j == i) {

					throw new IllegalArgumentException("'[' was expected!");

				}

			}

			catch (IllegalArgumentException iae) {

				System.out.println(iae.getMessage());

			}

			if (input[j] == '[' && j == i) {

				j++;
				continue;

			}

			try {

				if(!Character.isDigit(input[j])) {

					throw new IllegalArgumentException("a positive integer"
							+ " was expected!");

				}

			}

			catch (IllegalArgumentException iae) {

				System.out.println(iae.getMessage());
				j+=2;
				continue;

			}

			if (flag == true) {

				B.add(Character.getNumericValue(input[j]));
				j++;
				continue;

			}

		}

	} // End of setTree method.

} // End of class.
