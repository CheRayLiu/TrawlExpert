/*
 * More info on lambdas: http://tutorials.jenkov.com/java/lambda-expressions.html
 */

package sandbox;

public class TestLambdas {
	public static void main(String[] args) {
		//declare the instances of BinaryIntExpression
		GeneralCompare<Integer> b0;
		GeneralCompare<Integer> b1;
		GeneralCompare<Integer> b2;
		BinaryIntExpression b3;
		BinaryIntExpression b4;
		BinaryIntExpression b5;
		BinaryIntExpression b6;
		int result;
		
		//specify the instances of the functions (these implicitly become the "eval()" function)
		//single-line examples:
		b0 = (a1, a2) -> a1 + a2;
		b1 = (a1, a2) -> a1 - a2;
		b2 = (a1, a2) -> a1 * a2;
		b3 = (a1, a2) -> a1 / a2;
		b4 = (a1, a2) -> 12 * 2 + 46;
		//multi-line examples
		b5 = (a1, a2) -> {
			return a1 + a2;
		};
		b6 = (a1, a2) -> {
			int n = a1 * a2;
			n = n * a1 + 2 * a2;
			return n;
		};
			
		//call the main function using the instances as a parameter.
		result = binaryIntegerOperation(1,2,b0);
		System.out.println(result);
		
		result = binaryIntegerOperation(1,2,b1);
		System.out.println(result);
		
		result = binaryIntegerOperation(1,2,b2);
		System.out.println(result);
		
		result = binaryOperation(1,2,b3);
		System.out.println(result);
		
		result = binaryOperation(1,2,b4);
		System.out.println(result);
		
		result = binaryOperation(1,2,b5);
		System.out.println(result);
		
		result = binaryOperation(1,2,b6);
		System.out.println(result);
	}
	
	public static int binaryOperation(int a1, int a2, BinaryIntExpression exp) {
		//call the eval() function in the given BinaryIntExpression.
		return exp.eval(a1, a2);
	}
	
	public static int binaryIntegerOperation(int a1, int a2, GeneralCompare<Integer> exp) {
		//call the eval() function in the given BinaryIntExpression.
		return exp.eval(a1, a2);
	}
	
}
