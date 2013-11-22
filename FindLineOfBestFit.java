public class FindLineOfBestFit {

	public static void main(String[] args) {
		System.out.println("Enter your matrix 'A': ");
		Matrix AMatrix = new Matrix();
		System.out.println("Enter your right hand side 'b': ");
		Matrix bVector = new Matrix();
		double[][] solution = Matrix.vectorOfBestFit(AMatrix.getMatrix(), bVector.getMatrix());
		Matrix xVector = new Matrix(solution);
		System.out.println("Solving Ax = b for x: \nA:");
		Matrix.printMatrix(AMatrix.getMatrix());
		System.out.println("x:");
		Matrix.printMatrix(xVector.getMatrix());
		System.out.println("b: ");
		Matrix.printMatrix(bVector.getMatrix());
	} 
}
