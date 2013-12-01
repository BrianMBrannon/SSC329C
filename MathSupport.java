/*
 * The current purpose of this class will be to display the matrix in terms of fractions
 * rather than doubles which aren't exactly accurate.
*/


public class MathSupport {

	//how many zeros signify an error?
	public final static int MAX_ZEROS = 3;
	//how many repititions signifiy a 1/3 equivalent?
//	public final static int MAX_TIMES_REPEATED = 4;

	public static void main(String[] args) {
		double number = 3.1250000000;
		System.out.println("Convert this to a fraction: " + number);
		System.out.println(convert(number));
	}

	public static String convert(double number) {
		number = eliminateError(number);
		int denom = (int)Math.pow(10, numDecPoints(number));
		int num = (int)(number * denom);
		int GCD = findGCD(num, denom);

		num /= GCD;
		denom /= GCD;

		return (num + "/" + denom);
	}

	/**
	 * Find way in above method to turn 0.3333 to 1/3.  Multiply by 9, then that divided by nine is the answer.
	 * Find way to deal with numbers such as 8/7.
	 * Find way to recognize irrational numbers.
	**/

	/**
	 * The below will not work for small numbers (i.e. 1.27*10^-9).  This should not happen
	**/
	public static double eliminateError(double number) {
		int consecutiveZeros = 0;
		double remainder = number - (int)number;
		String myRemainder = Double.toString(remainder);
		char thisDigit = 'a'; //dummy value
	
		for (int i = 0; i < myRemainder.length(); i++) {
			thisDigit = myRemainder.charAt(i);

			if (thisDigit == '0') consecutiveZeros++;
			else consecutiveZeros = 0;

			if (consecutiveZeros == MAX_ZEROS) {
				myRemainder = myRemainder.substring(0, i);
				break;
			}

		}
		
		return (int)number + Double.parseDouble(myRemainder);
	}

	public static int numDecPoints(double number) {
		int numDecPoints = 0;
		while (number % 1 != 0) {
			number *= 10;
			numDecPoints++;
		}
		return numDecPoints;
	}

	private static int findGCD(int number1, int number2) {
        	if(number2 == 0){
           		 return number1;
       		 }
        	return findGCD(number2, number1%number2);
    	}
}
