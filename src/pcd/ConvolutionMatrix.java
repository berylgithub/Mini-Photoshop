/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcd;

import static java.lang.Math.max;
import java.util.Random;

/**
 *
 * @author Yorozuya
 */
public class ConvolutionMatrix {
    public int[][] createInitialMatrix(){
        int[][] A=new int[10][10];
        for(int i =0; i<10; i++){
            for (int j=0; j<10; j++){
                Random rand = new Random();
                int randomNum = rand.nextInt((1 - 0) + 1) + 0;
                A[j][i]=randomNum;
            }
        }
        return A;
    }
    public void printInitialMatrix(int[][] A){
        System.out.println("Initial Matrix (10x10) :");
        for(int i =0; i<10; i++){
            for (int j=0; j<10; j++){
                System.out.print(A[j][i]+" ");
            }
            System.out.println(" ");
        }
    }
    
    public int[][] createMaskMatrix(){
        int[][] B=new int[3][3];
        for(int i =0; i<3; i++){
            for (int j=0; j<3; j++){
                Random rand = new Random();
                int randomNum = rand.nextInt((1 - 0) + 1) + 0;
                B[j][i]=randomNum;
            }
        }
        return B;
    }
    
    public void printMaskMatrix(int[][] B){
        System.out.println("Mask Matrix (3x3) :");
        for(int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                System.out.print(B[j][i]+" ");
            }
            System.out.println(" ");
        }
    }
    
    public int[][] convolutionWithEdge(int[][] A, int[][] B){
        int[][] tempM1=new int[10+2][10+2];
        int[][] tempM2=new int[10+2][10+2];
        int[][] result=new int[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                tempM1[j+1][i+1]=A[j][i];
            }
        }
        for(int i=1; i<=10+2-2;i++){
            for(int j=1; j<=10+2-2;j++){
                tempM2[j][i] =  tempM1[j-1][i-1]*B[0][0]+
                                tempM1[j-1][i]*B[0][1]+
                                tempM1[j-1][i+1]*B[0][2]+
                                tempM1[j][i-1]*B[1][0]+
                                tempM1[j][i]*B[1][1]+
                                tempM1[j][i+1]*B[1][2]+
                                tempM1[j+1][i-1]*B[2][0]+
                                tempM1[j+1][i]*B[2][1]+
                                tempM1[j+1][i+1]*B[2][2];
            }
        }
        for(int i=1; i<12-1; i++){
            for(int j=1; j<12-1; j++){
                result[j-1][i-1]=tempM2[j][i];
            }
        }
        return result;
    }
    public int[][] convolution(int[][] A, int[][] B){
        int[][] result=new int[10][10];
        for(int i=1; i<=10-2;i++){
            for(int j=1; j<=10-2;j++){
                result[j][i] =  A[j-1][i-1]*B[0][0]+
                                A[j-1][i]*B[0][1]+
                                A[j-1][i+1]*B[0][2]+
                                A[j][i-1]*B[1][0]+
                                A[j][i]*B[1][1]+
                                A[j][i+1]*B[1][2]+
                                A[j+1][i-1]*B[2][0]+
                                A[j+1][i]*B[2][1]+
                                A[j+1][i+1]*B[2][2];
            }
        }
        return result;
    }
    public void printResult(int[][] result){
        System.out.println("Result (10x10) :");
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                System.out.print(result[j][i]+" ");
            }
            System.out.println(" ");
        }
    }
    public void printTemp(int[][] temp){
        System.out.println("Temp Matrix (12x12) : ");
        for (int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                System.out.print(temp[j][i]+" ");
            }
            System.out.println(" ");
        }
    }
}
