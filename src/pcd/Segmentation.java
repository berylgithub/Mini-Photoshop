/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcd;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yorozuya
 */
public class Segmentation {
    public BufferedImage binary(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] mTR = new int[y][x];
        int[][] mTG = new int[y][x];
        int[][] mTB = new int[y][x];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        int tresh=255;
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mTR[i][j]=c.getRed();
                if(mTR[i][j]>=tresh){
                    mTR[i][j]=255;
                }
                else{
                    mTR[i][j]=0;
                }
                mTG[i][j]=c.getGreen();
                if(mTG[i][j]>=tresh){
                    mTG[i][j]=255;
                }
                else{
                    mTG[i][j]=0;
                }
                mTB[i][j]=c.getBlue();
                if(mTB[i][j]>=tresh){
                    mTB[i][j]=255;
                }
                else{
                    mTB[i][j]=0;
                }
                
            }
        }
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color cOut = new Color(mTR[i][j], mTG[i][j], mTB[i][j]);
                imgOut.setRGB(j, i, cOut.getRGB());
            }
        }
        return imgOut;
    }
    
    public BufferedImage threshold(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] mTR = new int[y][x];
        int[][] mTG = new int[y][x];
        int[][] mTB = new int[y][x];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        int tresh=245;
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mTR[i][j]=c.getRed();
                if(mTR[i][j]>=tresh){
                    mTR[i][j]=0;
                }
                else{
                    mTR[i][j]=255;
                }
                mTG[i][j]=c.getGreen();
                if(mTG[i][j]>=tresh){
                    mTG[i][j]=0;
                }
                else{
                    mTG[i][j]=255;
                }
                mTB[i][j]=c.getBlue();
                if(mTB[i][j]>=tresh){
                    mTB[i][j]=0;
                }
                else{
                    mTB[i][j]=255;
                }
                
            }
        }
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color cOut = new Color(mTR[i][j], mTG[i][j], mTB[i][j]);
                imgOut.setRGB(j, i, cOut.getRGB());
            }
        }
        return imgOut;
    }
}
