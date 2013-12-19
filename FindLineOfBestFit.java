import java.util.Scanner;

public class FindLineOfBestFit {

	public static void main(String[] args) {
	/*	System.out.println("Enter your matrix 'A': ");
		Matrix AMatrix = new Matrix();
		System.out.println("Enter your right hand side 'b': ");
		Matrix bVector = new Matrix();
	*/	
		double[][] coordinates = readPoints();

		double[][] solution = Matrix.vectorOfBestFit(AMatrix.getMatrix(), bVector.getMatrix());
		Matrix xVector = new Matrix(solution);
		System.out.println("Solving Ax = b for x: \nA:");
		Matrix.printMatrix(AMatrix.getMatrix());
		System.out.println("x:");
		Matrix.printMatrix(xVector.getMatrix());
		System.out.println("b: ");
		Matrix.printMatrix(bVector.getMatrix());
		System.out.println("The line of best fit is: ");
		System.out.print(solution[0][0]);
		for (int i = 1; i < solution.length; i++) {
			System.out.print(" + " + solution[i][0]);
			System.out.println("*" + (char)(i+ 96) + "^" + i);
		}
	}

	public static double[][] readPoints () {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many points are there?");
		int numPoints = sc.nextInt();
		double[][] myPoints = new double[numPoints][2];
		for (int i = 0; i < numPoints; i++) {
			System.out.printf("Enter coordinate x%d: ", i + 1);
			myPoints[0][i] = sc.nextDouble();
			System.out.printf("Enter coordinate y%d: ", i + 1);
			myPoints[1][i] = sc.nextDouble();
		}

		return myPoints;
	}

	//2 columns because we're only solving LINEAR equations right now
	public static double[][] retrieveX(int numPoints, int[][] coordinates) {
		double[][] matrix = new double[numPoints][2];
		for (int i = 0; i < numPoints; i++) {
			matrix[i][0] = 1;
			matrix[i][1] = xCoordinates[i];
		}

		return matrix;
	} 
	
	public static double[][] retrieveY(int numPoints, int[] yCoordinates) {
		double[][] b = new double[numPoints][1];
		for (int i = 0; i < numPoints; i++) {
			b[i][0] = yCoordinates[i];
		}

		return b;
	}

}
