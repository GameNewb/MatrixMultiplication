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
import java.util.Calendar;

public class MatrixMultiplication 
{
    public static void main(String[] args) 
    {
        //Random number generator
        Random rand = new Random();
        
        //Assuming n is power of 2(n = 2^k) for some k. I am limitting n to 4096, since I don't want the program to run for a long time
        int n = (int)Math.pow(2,rand.nextInt(14-2)+1); 
        
        //Print n
        System.out.println("The matrices has " + n + " randomized rows");
        System.out.println("The matrices has " + n + " randomized column");
        
        System.out.println();
        
        //Initialize and print the first matrix
        System.out.println("The first matrix has the values: ");
        Matrix m1 = new Matrix(n, n);
        m1.printMatrix();
       
        
        System.out.println("\n---------------------------------\n");
        
        //Initialize and print the second matrix
        System.out.println("The second matrix has the values: ");
        Matrix m2 = new Matrix(n, n);
        m2.printMatrix();
        
        //Matrix Multiplication-------------------------------------------------------------------------------------------------//
        System.out.println();
        System.out.println("Product of A and B using Matrix Multiplication is: ");
        
        //Running time start calculation
        long startTime = System.currentTimeMillis();
        Calendar t1 = Calendar.getInstance();
        
        Matrix productRegular = m1.multiply(m2);
        
        //Prevents the printing of huge matrices
        if(n >= 2048)
        {
            System.out.println("The matrix is too large.");
        }
        else
        {
             System.out.println(productRegular);
        }
        
        //End running time calculation
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        Calendar t2 = Calendar.getInstance();
	System.out.print("\nTime taken to solve: "+(t2.getTimeInMillis() - t1.getTimeInMillis()  ) + " milliseconds" );
        System.out.println("\nThe running time is " + totalTime + " milliseconds.");
        System.out.println("\n-----------------------------------------------------------");
        
        //Strassens------------------------------------------------------------------------------------------------------//
        
        //Running time start calculation
        startTime = System.currentTimeMillis();
        t1 = Calendar.getInstance();
        
        System.out.println("\nProduct of A and B in strassens is: ");
        Matrix productStrassen = m1.multiplyStrassen(m2);
        
        //Prevents the printing of huge matrices
        if(n >= 2048)
        {
            System.out.println("The matrix is too large.");
        }
        else
        {
            System.out.println(productStrassen);
        }
        
        
        //End running time calculation
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        t2 = Calendar.getInstance();
	System.out.print("\nTime taken to solve: "+(t2.getTimeInMillis() - t1.getTimeInMillis()  ) + " milliseconds");
        System.out.println("\nThe running time is " + totalTime + " milliseconds.");
        
        //Compares the 2 products that was obtained from 2 different algorithms
        System.out.println();
        System.out.println("Are matrices the same? " + productStrassen.equals(productRegular));
     
       
    }
    
}
