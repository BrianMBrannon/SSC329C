import java.util.Scanner;
import java.util.Arrays;

public class Matrix {

private double[][] theMatrix;

/**	public static void main(String[] args) {
		//test();
		Scanner sc = new Scanner(System.in);
		double myMatrix[][] = createMatrix(sc);
		assignValues(sc, myMatrix);
		double myRightHandSide[][] = createMatrix(sc);
		assignValues(sc, myRightHandSide);
//		double yourMatrix[][] = createMatrix(sc);
	//	assignValues(sc, yourMatrix);
		System.out.println("My Matrix:");
		printMatrix(myMatrix);
		System.out.println("Solve for:");
		printMatrix(myRightHandSide);
//
		System.out.println("The vector of best fit: ");
		printMatrix(vectorOfBestFit(myMatrix, myRightHandSide));
	//	printMatrix(yourMatrix);
	//	printMatrix(multiply2DMatrices(myMatrix, yourMatrix));
//		System.out.println("Echelon form:");
///		printMatrix(echelonForm(myMatrix));
//		System.out.println("Tranposed:");
//		printMatrix(transposeMatrix(myMatrix));
//		System.out.p/rintln("Inverted:");
//		printMatrix(invertMatrix(myMatrix));
	}
**/

	public Matrix() {
		Scanner sc = new Scanner(System.in);
		theMatrix = createMatrix(sc);
		theMatrix = assignValues(sc, theMatrix);
	}

	public Matrix(double[][] matrix) {
		theMatrix = matrix;
	}

	public double[][] getMatrix() {
		return theMatrix;
	}

	public static void printMatrix(double matrix[][]) {
		for(int i = 0; i <= matrix.length - 1; i++) {
			System.out.print("|");
			for(int j = 0; j <= matrix[i].length - 1; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println();
	}

	public static double[][] createMatrix(Scanner sc) {
		int rows = 0;
		System.out.println("How many rows are in the matrix? ");
		if (!sc.hasNextInt()) {
			while (!sc.hasNextInt()) {
				System.out.println("Please enter a positive integer: ");
				if (sc.hasNextInt()) {
					rows = sc.nextInt();				
					if (rows <= 0) {
						while (!sc.hasNextInt() && rows <= 0) {
							System.out.println("Please enter a positive integer: ");
							if (sc.hasNextInt()) {
								rows = sc.nextInt();
							}
						}
					}
				}
				else sc.next();
			}
		}
		else {
			rows = sc.nextInt();
			if (rows <= 0) {
				while (!sc.hasNextInt() && rows <= 0) {
					System.out.println("Please enter a positive integer: ");
					if (sc.hasNextInt()) {
						rows = sc.nextInt();
					}
				}
			}
		}

		int columns = 0;
		System.out.println("How many columns are in the matrix? ");
		if (!sc.hasNextInt()) {
			while (!sc.hasNextInt()) {
				System.out.println("Please enter a positive integer: ");
				if (sc.hasNextInt()) {
					columns = sc.nextInt();				
					if (rows <= 0) {
						while (!sc.hasNextInt() && columns <= 0) {
							System.out.println("Please enter a positive integer: ");
							if (sc.hasNextInt()) {
								columns = sc.nextInt();
							}
						}
					}
				}
				else sc.next();
			}
		}
		else {
			columns = sc.nextInt();
			if (columns <= 0) {
				while (!sc.hasNextInt() && columns <= 0) {
					System.out.println("Please enter a positive integer: ");
					if (sc.hasNextInt()) {
						columns = sc.nextInt();
					}
				}
			}
		}
		double[][] array = new double[rows][columns];
		return array;
		
	}

	public static double[][] assignValues(Scanner sc, double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.printf("Entry for Matrix(%d, %d): ", i, j);
				while (!sc.hasNextInt()) {
					System.out.println("Please enter a real number: ");
					sc.next();
				}
				matrix[i][j] = sc.nextInt();
			}
		}
		return matrix;
	}

	//This may only work for square matrices
	public static double[][] declare2DIdentityMatrix(int n) {
		double[][] identity = new double[n][n];
		for (int i = 0; i < n; i++) {
			identity[i][i] = 1;
		}
		return identity;
	}

	public static boolean canAdd(double[][] aMatrix, double[][] bMatrix) {
		return aMatrix.length == bMatrix.length && aMatrix[0].length == bMatrix[0].length;
	}

	public static double[][] addTwoMatrices(double[][] aMatrix, double[][] bMatrix) {
		if (!canAdd(aMatrix, bMatrix)) throw new IllegalArgumentException("Cannot add matrices.");
		double[][] newMatrix = new double[aMatrix.length][aMatrix[0].length];
		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix[0].length; j++) {
				newMatrix[i][j] = aMatrix[i][j] + bMatrix[i][j];
			}
		}

		return newMatrix;
	}
	
	public static double[][] multiplyScalar(double[][] matrix, double scalar) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = matrix[i][j] * scalar;
			}
		}

		return matrix;
	}

	public static boolean canMultiply(double[][] firstMatrix, double[][] secondMatrix) {
		if (firstMatrix[0].length == secondMatrix.length) {
			return true;
		}
		return false;
	}

	public static double[][] multiply2DMatrices(double firstMatrix[][], double secondMatrix[][]){
		if (canMultiply(firstMatrix, secondMatrix)) {
			double[][] productMatrix = new double[firstMatrix.length][secondMatrix[0].length];

			for (int i = 0; i <= firstMatrix.length - 1; i++) {
				for(int j = 0; j <= secondMatrix[i].length - 1; j++) {
					for(int k = 0; k <= secondMatrix.length - 1; k++) {
						productMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
					}
				}
			}
			return productMatrix;
		}
		else {
			throw new IllegalArgumentException("These matrix cannot be multiplied.");
		}
	}

/**	public static void test() {
		double[][] myMatrix = declare3x3Matrix(1,2,3,4,5,4,3,2,1);
		printMatrix(myMatrix);
		double[][] keyMatrix = declare3x3Matrix(1,2,0,2,1,2,0,2,1);
		printMatrix(multiply2DMatrices(keyMatrix, myMatrix));
		double[][] encryptedMatrix = invertMatrix(multiply2DMatrices(keyMatrix, myMatrix));	
		printMatrix(encryptedMatrix);
		//Now this is "encrypted" with a sample key
		printMatrix(invertMatrix(encryptedMatrix));
		printMatrix(invertMatrix(keyMatrix));
		printMatrix(multiply2DMatrices(invertMatrix(keyMatrix),invertMatrix(encryptedMatrix)));

		printMatrix(multiply2DMatrices(encryptedMatrix, invertMatrix(encryptedMatrix)));
		printMatrix(multiply2DMatrices(invertMatrix(encryptedMatrix), encryptedMatrix));
	}
**/

	public static boolean isInvertible(double[][] matrix) {
		double product = 1;
		matrix = echelonForm(matrix);
		for(int i = 0; i < matrix.length; i++) {
			product *= matrix[i][i];

		}
		if (product < 0.0000001 && product > -0.0000001) {
			return false;
		}
		else return true;
	}	


	public static double[][] echelonForm(double[][] matrix) {
		double[][] solution = declare2DIdentityMatrix(matrix.length);
		double[][] eliminationMatrix = declare2DIdentityMatrix(matrix.length);

		//Create an one dimensional array of all needed multipliers for easy access
		//if the Matrix is square, and n = # of rows, there are (n*(n-1))/2 multipliers
		double[] multipliers = new double[(matrix.length*(matrix.length - 1))];
		int index = 0;
		for (int i = 0; i <= matrix.length - 2; i++) {
			for (int j = i + 1; j <= matrix[0].length - 1; j++) {
				multipliers[index] = matrix[j][i] / matrix[i][i];
				eliminationMatrix[j][i] = -1 * multipliers[index];
				matrix = multiply2DMatrices(eliminationMatrix,matrix);
				solution = multiply2DMatrices(eliminationMatrix,solution);
				//reset
				index++;
				eliminationMatrix[j][i] = 0;
			}
		}
		//Lower triangle matrix is produced
		return matrix;	
	}
	//Designed to invert square matrices only
	public static double[][] invertMatrix(double[][] matrix) {
		double[][] myMatrix = matrix;
		if (!isInvertible(myMatrix)) {
			throw new IllegalArgumentException("The matrix is not invertible.");
		}
		double[][] solution = declare2DIdentityMatrix(matrix.length);
		double[][] eliminationMatrix = declare2DIdentityMatrix(matrix.length);

		//Create an one dimensional array of all needed multipliers for easy access
		//if the Matrix is square, and n = # of rows, there are (n*(n-1))/2 multipliers
		double[] multipliers = new double[(matrix.length*(matrix.length - 1))];
		int index = 0;
		for (int i = 0; i <= matrix.length - 2; i++) {
			for (int j = i + 1; j <= matrix[0].length - 1; j++) {
				multipliers[index] = matrix[j][i] / matrix[i][i];
				eliminationMatrix[j][i] = -1 * multipliers[index];
				matrix = multiply2DMatrices(eliminationMatrix,matrix);
				solution = multiply2DMatrices(eliminationMatrix,solution);
				//reset
				index++;
				eliminationMatrix[j][i] = 0;
			}
		}
		//Lower triangle matrix is produced
		
		for (int i = matrix.length - 1; i > 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				multipliers[index] = matrix[j][i] / matrix[i][i];
				eliminationMatrix[j][i] = -1 * multipliers[index];
				matrix = multiply2DMatrices(eliminationMatrix,matrix);
				solution = multiply2DMatrices(eliminationMatrix,solution);
				//reset
				index++;
				eliminationMatrix[j][i] = 0;
			}
		}
		//A diagonal matrix is now formed

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				solution[i][j] /= (matrix[i][i]);
			}
		}

		return solution;
	}
	
	public static double[][] transposeMatrix(double[][] matrix) {
		int numRows = matrix[0].length;
		int numColumns = matrix.length;
		double[][] newMatrix = new double[numRows][numColumns];

		for (int i = 0; i < numColumns; i++) {
			for (int j = 0; j < numRows; j++) {
				newMatrix[j][i] = matrix[i][j];
			}
		}

		return newMatrix;
	}

	public static double[][] vectorOfBestFit(double[][] matrix, double[][] rightHandSide) {
		return multiply2DMatrices(multiply2DMatrices(invertMatrix(multiply2DMatrices(transposeMatrix(matrix), matrix)),transposeMatrix(matrix)), rightHandSide);
	}
}
