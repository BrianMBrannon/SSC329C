/*
 * The current purpose of this class will be to display the matrix in terms of fractions
 * rather than doubles which aren't exactly accurate.
 * For applied purposes I have in mind exact values are not important, but when displaying
 * values to the user it's preferred they're exact.
 * This is implemented here, after an imprecise value is likely given, because doing so in
 * the actual Matrix class would prove to be unnessecary.  
*/


public class MathSupport {

	//how many zeros signify an error?
	public final static int MAX_ZEROS = 3;
	//how many repititions signifiy a 1/3 equivalent?
	public final static int MAX_TIMES_REPEATED = 5;

	public static void main(String[] args) {
		double number = 3.1250000000;
		System.out.println("Convert this to a fraction: " + number);
		System.out.println(convert(number));
		double num2 = 1.24242222222;
		System.out.println(num2 + "" + checkRepeat(num2));
		System.out.println("Convert that to a fraction: " + convert(num2));
	}

	public static String convert(double number) {
		int whereRepeated = checkRepeat(number);
		if (whereRepeated != 0) {
			return repeatToFraction(number, whereRepeated);
		}
		else {
			number = eliminateError(number);
			int denom = (int)Math.pow(10, numDecPoints(number));
			int num = (int)(number * denom);
			int GCD = findGCD(num, denom);
			num /= GCD;
			denom /= GCD;
			return (num + "/" + denom);
		}
	}

	/**
	 * Find way in above method to turn 0.3333 to 1/3.  Multiply by 9, then that divided by nine is the answer.
	 * Find way to deal with numbers such as 8/7.
	 * Find way to recognize irrational numbers.
	**/

	//Is there a repeating amount of characters?  Where do they begin?
	public static int checkRepeat(double number) {
		String myNum = Double.toString(number);
		char repeat;
		int beginAt = 0;
		int repeatCount = -1;

		for (int i = 0; i < myNum.length(); i++) {
		//only consider past the decimal point
			if (myNum.charAt(i) == '.') {
				repeat = myNum.charAt(i + 1);
				beginAt = i + 1;
				for (int j = i + 1; j < myNum.length(); j++) {
					if (myNum.charAt(j) == repeat) {
						repeatCount++;
						if (repeatCount == MAX_TIMES_REPEATED) {
							return beginAt;
						}
					}
					else {
						beginAt = j - 1; //zero based indexing
						repeatCount = 0;
						repeat = myNum.charAt(j);
					}
				}
				break;
			}
		}		

		return -1;
	}

	public static String repeatToFraction(double number,int indexRepeated) {
		int decPointsMult = (int) Math.pow(10, indexRepeated);
		System.out.println("DecPointsMult: " + decPointsMult);
		if (decPointsMult == 0) decPointsMult = 1;
		//double num = number * 9
		double num = ((int)(number * 9 * decPointsMult) / decPointsMult);
		System.out.println("num : " + num);
		int denom = 9;
		if (num % 1 == 0) {
			int GCD = findGCD((int)num, denom);
			num /= (GCD * decPointsMult);
			denom /= (GCD * decPointsMult);
		}
		return (num + "/" + denom);
	}

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
