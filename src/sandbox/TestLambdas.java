package sandbox;

public class TestLambdas {
	public static void main(String[] args) {
		BinaryIntExpression b0;
		BinaryIntExpression b1;
		BinaryIntExpression b2;
		BinaryIntExpression b3;
		BinaryIntExpression b4;
		int result;
		
		b0 = (a1, a2) -> a1 + a2;
		b1 = (a1, a2) -> a1 - a2;
		b2 = (a1, a2) -> a1 * a2;
		b3 = (a1, a2) -> a1 / a2;
		b4 = (a1, a2) -> {
			int n = a1 * a2;
			n = n * a1 + a2;
			return n;
		};
			
		result = binaryOperation(1,2,b0);
		System.out.println(result);
		
		result = binaryOperation(1,2,b1);
		System.out.println(result);
		
		result = binaryOperation(1,2,b2);
		System.out.println(result);
		
		result = binaryOperation(1,2,b3);
		System.out.println(result);
		
		result = binaryOperation(1,2,b4);
		System.out.println(result);
	}
	
	public static int binaryOperation(int a1, int a2, BinaryIntExpression exp) {
		return exp.eval(a1, a2);
	}
	
}
