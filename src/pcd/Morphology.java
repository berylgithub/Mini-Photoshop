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
public class Morphology {
    public BufferedImage dilation(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] eS=new int[3][3];
        int[][] mR=new int[y+2][x+2];
        int[][] mG=new int[y+2][x+2];
        int[][] mB=new int[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        
        eS[0][0]=0;
        eS[0][1]=255;
        eS[0][2]=0;
        eS[1][0]=255;
        eS[1][1]=255;
        eS[1][2]=255;
        eS[2][0]=0;
        eS[2][1]=255;
        eS[2][2]=0;
        
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                ///RED///
                if(mR[i][j]==eS[1][1]){
                    mRO[i-1][j]=eS[0][1];
                    mRO[i][j-1]=eS[1][0];
                    mRO[i][j+1]=eS[1][2];
                    mRO[i+1][j]=eS[2][1];
                }
                ///GREEN///
                if(mG[i][j]==eS[1][1]){
                    mGO[i-1][j]=eS[0][1];
                    mGO[i][j-1]=eS[1][0];
                    mGO[i][j+1]=eS[1][2];
                    mGO[i+1][j]=eS[2][1];
                }
                ///BLUE///
                if(mB[i][j]==eS[1][1]){
                    mBO[i-1][j]=eS[0][1];
                    mBO[i][j-1]=eS[1][0];
                    mBO[i][j+1]=eS[1][2];
                    mBO[i+1][j]=eS[2][1];
                }
            }
        }
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                Color cOut = new Color(mRO[i][j], mGO[i][j], mBO[i][j]);
                imgOut.setRGB(j-1, i-1, cOut.getRGB());
            }
        }
        
        return imgOut;
    }
    
    public BufferedImage erosion(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] eS=new int[3][3];
        int[][] mR=new int[y+2][x+2];
        int[][] mG=new int[y+2][x+2];
        int[][] mB=new int[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        
        eS[0][0]=0;
        eS[0][1]=255;
        eS[0][2]=0;
        eS[1][0]=255;
        eS[1][1]=255;
        eS[1][2]=255;
        eS[2][0]=0;
        eS[2][1]=255;
        eS[2][2]=0;
        
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                ///RED///
                if(mR[i][j]==eS[1][1]){
                    mR[i-1][j]=0;
                    mR[i][j-1]=0;
                    mR[i][j+1]=0;
                    mR[i+1][j]=0;
                }
                ///GREEN///
                if(mG[i][j]==eS[1][1]){
                    mG[i-1][j]=0;
                    mG[i][j-1]=0;
                    mG[i][j+1]=0;
                    mG[i+1][j]=0;
                }
                ///BLUE///
                if(mB[i][j]==eS[1][1]){
                    mB[i-1][j]=0;
                    mB[i][j-1]=0;
                    mB[i][j+1]=0;
                    mB[i+1][j]=0;
                }
            }
        }
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                Color cOut = new Color(mR[i][j], mG[i][j], mB[i][j]);
                imgOut.setRGB(j-1, i-1, cOut.getRGB());
            }
        }
        
        return imgOut;
    }
}
