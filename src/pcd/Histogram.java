/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcd;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 *
 * @author Yorozuya
 */
public class Histogram{

    public Histogram() {
       
    }
    
    public BufferedImage toGrayScale(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                float r = (float)c.getRed();
                float g = (float)c.getGreen();
                float b = (float)c.getBlue();
                float gray = (r+g+b)/3;
                Color grayCol = new Color((int)gray, (int)gray, (int)gray);
                img.setRGB(j, i, grayCol.getRGB());   
                }
        }
        return img;
    }
    
    public int[][] getRGB(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        int size = x*y;
        int k = 0;
        int[][] arrRGB = new int[3][size];
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c=new Color(img.getRGB(j, i));
                arrRGB[0][k]=c.getRed();
                arrRGB[1][k]=c.getGreen();
                arrRGB[2][k]=c.getBlue();
                k++;
            }
        }
        return arrRGB;
        
    }
    public int[][] sumRGB(BufferedImage img, int[][] arrRGB){
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] rgbSum = new int[3][256];
        int size = x*y;
        for(int k=0; k<256; k++){
            int sumR = 0;
            int sumG = 0;
            int sumB = 0;
            for(int i=0; i<size; i++){
                if(arrRGB[0][i]==k){
                    sumR++;
                }
            }
            rgbSum[0][k]=sumR;
            for(int i=0; i<size; i++){
                if(arrRGB[1][i]==k){
                    sumG++;
                }
            }
            rgbSum[1][k]=sumG;
            for(int i=0; i<size; i++){
                if(arrRGB[2][i]==k){
                    sumB++;
                }
            }
            rgbSum[2][k]=sumB;
        }
        return rgbSum;
        
    }
    public float[][] cdfRGB(BufferedImage img, int[][] rgbSum){
        float size = (img.getHeight()*img.getWidth());
        float[][] rgbProb=new float [3][256];
        float[][] rgbCDF=new float [3][256];
        for(int i=0; i<256; i++){
            rgbProb[0][i]=(float)rgbSum[0][i]/size;
            rgbProb[1][i]=(float)rgbSum[1][i]/size;
            rgbProb[2][i]=(float)rgbSum[2][i]/size;
            System.out.println("Probability level "+i+" R="+rgbProb[0][i]+" G="+rgbProb[1][i]+" B="+rgbProb[2][i]);
        }
        float sumR=0,sumG=0,sumB=0;
        for(int i=0; i<256; i++){
            sumR=sumR+rgbProb[0][i];
            rgbCDF[0][i]=sumR;
            
            sumG=sumG+rgbProb[1][i];
            rgbCDF[1][i]=sumG;
            
            sumB=sumB+rgbProb[2][i];
            rgbCDF[2][i]=sumB;
            
            System.out.println("CDF level "+i+" R="+rgbCDF[0][i]+" G="+rgbCDF[1][i]+" B="+rgbCDF[2][i]);
        }
        return rgbCDF;
        
    }
    
    public BufferedImage histEq(BufferedImage img, float[][] rgbCDF){
        BufferedImage imgEq=new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int[][] rgbEq=new int[3][256];
        for(int i=0; i<256; i++){
            rgbEq[0][i]=Math.round(255*rgbCDF[0][i]);
            rgbEq[1][i]=Math.round(255*rgbCDF[1][i]);
            rgbEq[2][i]=Math.round(255*rgbCDF[2][i]);
            System.out.println("Equalized level "+i+" R="+rgbEq[0][i]+" G="+rgbEq[1][i]+" B="+rgbEq[2][i]);
        }
        int x=img.getWidth();
        int y=img.getHeight();
        int r=0;
        int g=0;
        int b=0;
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                r=c.getRed();
                g=c.getGreen();
                b=c.getBlue();
                
                r=rgbEq[0][r];
                g=rgbEq[1][g];
                b=rgbEq[2][b];
                
                Color c1= new Color(r,g,b);
                imgEq.setRGB(j, i, c1.getRGB());
            }
        }
        return imgEq;
        
    }
    
    public void printHistogramRGB(int[][] rgbSum){
        for(int i=0; i<256; i++){
            System.out.println("level "+i+": Red="+rgbSum[0][i]+" Green="+rgbSum[1][i]+" Blue="+rgbSum[2][i]);
        }
    }
    
}
