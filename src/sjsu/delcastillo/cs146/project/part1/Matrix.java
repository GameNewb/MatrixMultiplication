/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjsu.delcastillo.cs146.project.part1;

/**
 *
 * @author Kiyeon - Kyle Del Castillo
 * CS 146 - Data Structures and Algorithms
 * Project 1 Part 1 - Matrix Multiplication
 * 
 */
import java.util.Random;
import java.text.DecimalFormat;

class Matrix 
{ 
    //Global Variables
    double[][] arrayA;
    double rangeMin = 0.00;
    double rangeMax = 100.00;
    
    //Passing arrays
    public Matrix(double[][] array) 
    {
        arrayA = array;
    }
    
    //Create matrix values
    public Matrix(int rows, int columns) 
    {
        Random rand = new Random(); //Generate random number values for the matrix
        double[][] array = new double[rows][columns]; //Create the matrix array
        
        for(int i = 0; i < array.length; i++) //Random matrix values
        {
            for(int j = 0; j < array[i].length; j++)
            {
                array[i][j] = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
                
            }
            arrayA = array; //Store values
        }
    }

    //Basic matrix multiplication
    public Matrix multiply(Matrix multiplied) 
    {
        
        double[][] arrayB = multiplied.arrayA; //Pass the second array
        double[][] c = new double[arrayA.length][arrayA.length]; //Array for the multiplied values
        
        
        for(int i = 0; i < arrayA.length; i++)
        {
            for(int j = 0; j < arrayB[0].length; j++)
            {
                for(int k = 0; k < arrayA[0].length; k++)
                {
                    c[i][j] += arrayA[i][k] * arrayB[k][j]; //Multiplying the values and storing it into array c
                }
            }

        }
 
        return new Matrix(c);
    }
    
    //Object function for strassen's algorithm
    public Matrix multiplyStrassen(Matrix m) 
    {
        double[][] arrayB = m.arrayA;
        double[][] resultArray = strassen(arrayA,arrayB); //Calculate the product result using strassens
        
        return new Matrix(resultArray); //Change the resultArray into an object and return
        
    }
    
    //Multiply the matrix using strassen's algorithm
    public double[][] strassen(double[][] matrixA, double[][] matrixB) 
    {
	int n = matrixA.length;
	double[][] result = new double[n][n];
        
	if(n == 1) //Base Case
	{
            result[0][0] = matrixA[0][0] * matrixB[0][0];
	}
	else
	{
            //Sub-matrices
            double[][] A11 = new double[n/2][n/2];
            double[][] A12 = new double[n/2][n/2];
            double[][] A21 = new double[n/2][n/2];
            double[][] A22 = new double[n/2][n/2];

            double[][] B11 = new double[n/2][n/2];
            double[][] B12 = new double[n/2][n/2];
            double[][] B21 = new double[n/2][n/2];
            double[][] B22 = new double[n/2][n/2];
            
            //Divide the first Matrix into 4
            divide(matrixA, A11, 0 , 0);
            divide(matrixA, A12, 0 , n/2);
            divide(matrixA, A21, n/2, 0);
            divide(matrixA, A22, n/2, n/2);

            //Divice the second Matrix into 4
            divide(matrixB, B11, 0 , 0);
            divide(matrixB, B12, 0 , n/2);
            divide(matrixB, B21, n/2, 0);
            divide(matrixB, B22, n/2, n/2);
            
            //Partitioning 7T
            double[][] P1 = strassen(add(A11, A22), add(B11, B22)); //P1 = (A11 + A22)(B11 + B22)
            double[][] P2 = strassen(add(A21, A22), B11); //P2 = (A21 + A22) B11
            double[][] P3 = strassen(A11, sub(B12, B22)); //P3 = A11 (B12 - B22)
            double[][] P4 = strassen(A22, sub(B21, B11)); //P4 = A22 (B21 - B11)
            double[][] P5 = strassen(add(A11, A12), B22); //P5 = (A11 + A12) B22
            double[][] P6 = strassen(sub(A21, A11), add(B11, B12)); //P6 = (A21 - A11) (B11 + B12)
            double[][] P7 = strassen(sub(A12, A22), add(B21, B22)); //P7 = (A12 - A22) (B21 + B22)
            
            //Add the values for the submatrix C
            double[][] C11 = add(sub(add(P1, P4), P5), P7); //C11 = P1 + P4 - P5 + P7
            double[][] C12 = add(P3, P5); //C12 = P3 + P5
            double[][] C21 = add(P2, P4); //C21 = P2 + P4
            double[][] C22 = add(sub(add(P1, P3), P2), P6); //C22 = P1 - P2 + P3 + P6

            //Combine all the halves together
            copy(C11, result, 0 , 0);
            copy(C12, result, 0 , n/2);
            copy(C21, result, n/2, 0);
            copy(C22, result, n/2, n/2);
	}
        
	return result;
    }
    
    //Copy function to combine the values of the Matrix
    public void copy(double[][] subMatrix, double[][] resultMatrix, int rows, int columns)
    {
	for(int i = 0, i1 = rows; i < subMatrix.length; i++, i1++)
        {
            for(int j = 0, j1 = columns; j < subMatrix.length; j++, j1++)
            {
		resultMatrix[i1][j1] = subMatrix[i][j];
            }
	}
    }
    
    
    //Divide function that splits the Matrix into multiple matrices for easier computation
    public void divide(double[][] matrices, double[][] subMatrix, int rows, int columns)
    {
	for(int i = 0, i1 = rows; i < subMatrix.length; i++, i1++)
        {		
            for(int j = 0, j1 = columns; j < subMatrix.length; j++, j1++)
            {
		subMatrix[i][j] = matrices[i1][j1];
            }
	}
    }
    
    
    //Sub function for Strassen
    public double[][] sub(double[][] A, double[][] B)
    {
	int n = A.length;

	double[][] result = new double[n][n];

	for(int i=0; i < n; i++)
        {
            for(int j=0; j < n; j++)
            {
                result[i][j] = A[i][j] - B[i][j];
            }
			
        }
			
        return result;
    }
    
    //Add function for Strassen
    public double[][] add(double[][] A, double[][] B)
    {
	int n = A.length;

	double[][] result = new double[n][n];

	for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                result[i][j] = A[i][j] + B[i][j];
            }
			
        }
			
        return result;
    }
    
    //Prints the Matrix
    public void printMatrix()
    {
        for (double[] arrayA1 : arrayA) {
            for (int j = 0; j < arrayA[0].length; j++) {
                System.out.printf("%.2f" + " | ", arrayA1[j]);
            }
            System.out.println();
        }
    }
    
    //Overrides the standard toString to print out the values of the object array
    @Override
    public String toString()
    {
        String arr;
        DecimalFormat df = new DecimalFormat("0.00"); //Format the string to 2 decimal places
       
        for(int i = 0; i < arrayA.length; i++)
        {
            for(int j = 0; j < arrayA[0].length;j++)
            {
                arr = df.format(arrayA[i][j]);
                System.out.print(arr + " | ");
            } 
            System.out.println();
        }
       
        return "";
    }
    
    //Override the equals method for objects
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
        {
            return true;
        }
        
        if (arrayA == obj)
        {
            return true;
        }
            
        if (obj == null)
        {
            return false;
        }
            
        return obj instanceof Matrix;
    }
    
}
