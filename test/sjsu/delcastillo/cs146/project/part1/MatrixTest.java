/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjsu.delcastillo.cs146.project.part1;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kiyeon - Kyle Del Castillo
 * CS 146 - Data Structures and Algorithms
 * Project 1 Part 1 - Matrix Multiplication
 * 
 */
public class MatrixTest 
{
    Random rand = new Random();
    
    //Specific test
    int rows = 128;
    int columns = 128;
    //Randomized 2^n test
    int n = (int)Math.pow(2,rand.nextInt(14-2)+1);
    
    double[][] arrayA = {{4.0, 50.0, 3.7, 22.4}, {1.0, 0.0, 2.1, 12.6},{41.0, 31.0, 32.0, 1.4},{0.0, 84.0, 12.6, 11.2}};
    double[][] arrayB = {{9.0, 24.0, 6.6, 9.4}, {3.2, 5.9, 11.19, 13.7},{8.24, 10.66, 1.7, 18.24},{33.88, 10.0, 0, 77.1}};
    
    public MatrixTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of multiply method, of class Matrix.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        
        Matrix m1 = new Matrix(rows, columns);
        Matrix m2 = new Matrix(rows, columns);
        
     
        Matrix expResult = m1.multiply(m2);
        Matrix result = m1.multiply(m2);
        assertEquals(expResult, result);
        
        
    }


    /**
     * Test of printMatrix method, of class Matrix.
     */
    @Test
    public void testPrintMatrix() {
        System.out.println("printMatrix");
        
        Matrix m1 = new Matrix(rows, columns);
        Matrix instance = m1;
        instance.printMatrix();
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of multiplyStrassen method, of class Matrix.
     */
    @Test
    public void testMultiplyStrassen() {
        System.out.println("multiplyStrassen");
        
        Matrix m1 = new Matrix(rows, columns);
        Matrix m2 = new Matrix(rows, columns);
        
        Matrix expResult = m1.multiplyStrassen(m2);
        Matrix result = expResult;
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of strassen method, of class Matrix.
     */
    @Test
    public void testStrassen() {
        System.out.println("strassen");
        double[][] a = arrayA;
        double[][] b = arrayB;
        
        Matrix m1 = new Matrix(rows, columns);
        Matrix m2 = new Matrix(rows, columns);
        
        Matrix instance = m1.multiplyStrassen(m2);
        
        double[][] expResult = instance.strassen(a, b);
        double[][] result = instance.strassen(a, b);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of copy method, of class Matrix.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        
        double[][] c1 = arrayA;
        double[][] p1 = arrayB;
  
        Matrix instance = new Matrix(rows, columns);
        instance.copy(c1, p1, 0, 0);
    }

    /**
     * Test of divide method, of class Matrix.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        double[][] p1 = arrayA;
        double[][] c1 = arrayB;
        
        Matrix instance = new Matrix(rows, columns);
        instance.divide(p1, c1, 0, 0);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of sub method, of class Matrix.
     */
    @Test
    public void testSub() {
        System.out.println("sub");
        double[][] A = arrayA;
        double[][] B = arrayB;
        Matrix instance = new Matrix(rows, columns);
        double[][] expResult = instance.sub(A, B);
        double[][] result = instance.sub(A, B);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of add method, of class Matrix.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        double[][] A = arrayA;
        double[][] B = arrayB;
        Matrix instance = new Matrix(rows, columns);
        double[][] expResult = instance.add(A, B);
        double[][] result = instance.add(A, B);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of toString method, of class Matrix.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Matrix instance = new Matrix(rows, columns);
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of equals method, of class Matrix.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Object();
        Matrix instance = new Matrix(rows, columns);
        Matrix m2 = new Matrix(rows, columns);
        boolean expResult = true;
        boolean result = instance.equals(m2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
