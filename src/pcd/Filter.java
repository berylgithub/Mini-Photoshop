/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcd;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.lang.Math.sqrt;

/**
 *
 * @author Yorozuya
 */
public class Filter {
    public BufferedImage meanFilter3(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float mean;
        float[][] mFil=new float[3][3];
        int[][] mOri=new int[y+2][x+2];
        int[][] mRes=new int[y+2][x+2];
        int[][] mR=new int[y+2][x+2];
        int[][] mG=new int[y+2][x+2];
        int[][] mB=new int[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
//                mOri[i+1][j+1]=img.getRGB(j, i);
            }
        }
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(mR[i-1][j-1]+mR[i-1][j]+mR[i-1][j+1]
                            +mR[i][j-1]+mR[i][j]+mR[i][j+1]
                            +mR[i+1][j-1]+mR[i+1][j]+mR[i+1][j+1])/9;
                mGO[i][j]=(mG[i-1][j-1]+mG[i-1][j]+mG[i-1][j+1]
                            +mG[i][j-1]+mG[i][j]+mG[i][j+1]
                            +mG[i+1][j-1]+mG[i+1][j]+mG[i+1][j+1])/9;
                mBO[i][j]=(mB[i-1][j-1]+mB[i-1][j]+mB[i-1][j+1]
                            +mB[i][j-1]+mB[i][j]+mB[i][j+1]
                            +mB[i+1][j-1]+mB[i+1][j]+mB[i+1][j+1])/9;
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
    
    public BufferedImage medianFilter3(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] mR=new int[y+2][x+2];
        int[][] mG=new int[y+2][x+2];
        int[][] mB=new int[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] medR=new int[9];
        int[] medG=new int[9];
        int[] medB=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
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
                /// R E D ///
                medR[0]=mR[i-1][j-1];
                medR[1]=mR[i-1][j];
                medR[2]=mR[i-1][j+1];
                medR[3]=mR[i][j-1];
                medR[4]=mR[i][j];
                medR[5]=mR[i][j+1];
                medR[6]=mR[i+1][j-1];
                medR[7]=mR[i+1][j];
                medR[8]=mR[i+1][j+1];
                for(int k=0; k<9-1; k++){
                    int index=k;
                    for(int l=k+1; l<9; l++){
                        if(medR[l]<medR[index]){
                            index=l;
                        }
                    }
                    int sNum = medR[index];  
                    medR[index] = medR[k];
                    medR[k] = sNum;
                }
                mRO[i][j]=medR[4];
                
                /// G R E E N ///
                medG[0]=mG[i-1][j-1];
                medG[1]=mG[i-1][j];
                medG[2]=mG[i-1][j+1];
                medG[3]=mG[i][j-1];
                medG[4]=mG[i][j];
                medG[5]=mG[i][j+1];
                medG[6]=mG[i+1][j-1];
                medG[7]=mG[i+1][j];
                medG[8]=mG[i+1][j+1];
                for(int k=0; k<9-1; k++){
                    int index=k;
                    for(int l=k+1; l<9; l++){
                        if(medG[l]<medG[index]){
                            index=l;
                        }
                    }
                    int sNum = medG[index];  
                    medG[index] = medG[k];
                    medG[k] = sNum;
                }
                mGO[i][j]=medG[4];
                
                /// B L U E ///
                medB[0]=mB[i-1][j-1];
                medB[1]=mB[i-1][j];
                medB[2]=mB[i-1][j+1];
                medB[3]=mB[i][j-1];
                medB[4]=mB[i][j];
                medB[5]=mB[i][j+1];
                medB[6]=mB[i+1][j-1];
                medB[7]=mB[i+1][j];
                medB[8]=mB[i+1][j+1];
                for(int k=0; k<9-1; k++){
                    int index=k;
                    for(int l=k+1; l<9; l++){
                        if(medB[l]<medB[index]){
                            index=l;
                        }
                    }
                    int sNum = medB[index];  
                    medB[index] = medB[k];
                    medB[k] = sNum;
                }
                mBO[i][j]=medB[4];
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
    
    public BufferedImage modusFilter3(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] mR=new int[y+2][x+2];
        int[][] mG=new int[y+2][x+2];
        int[][] mB=new int[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] modR=new int[9];
        int[] modG=new int[9];
        int[] modB=new int[9];
        int maxCountR=0;
        int maxCountG=0;
        int maxCountB=0;
        int maxValueR=0;
        int maxValueG=0;
        int maxValueB=0;
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
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
                /// R E D ///
                modR[0]=mR[i-1][j-1];
                modR[1]=mR[i-1][j];
                modR[2]=mR[i-1][j+1];
                modR[3]=mR[i][j-1];
                modR[4]=mR[i][j];
                modR[5]=mR[i][j+1];
                modR[6]=mR[i+1][j-1];
                modR[7]=mR[i+1][j];
                modR[8]=mR[i+1][j+1];
                for (int k=0; k<9; k++) {
                    int countR=0;
                    for (int l=0; l<9; l++) {
                        if (modR[l]==modR[k]){
                            countR++;
                        }
                    }
                    if (countR>maxCountR) {
                        maxCountR=countR;
                        maxValueR=modR[k];
                    }
                }
                mRO[i][j]=maxValueR;
                
                /// G R E E N ///
                modG[0]=mG[i-1][j-1];
                modG[1]=mG[i-1][j];
                modG[2]=mG[i-1][j+1];
                modG[3]=mG[i][j-1];
                modG[4]=mG[i][j];
                modG[5]=mG[i][j+1];
                modG[6]=mG[i+1][j-1];
                modG[7]=mG[i+1][j];
                modG[8]=mG[i+1][j+1];
                for (int k=0; k<9; k++) {
                    int countG=0;
                    for (int l=0; l<9; l++) {
                        if (modG[l]==modG[k]){
                            countG++;
                        }
                    }
                    if (countG>maxCountG) {
                        maxCountG=countG;
                        maxValueG=modG[k];
                    }
                }
                mGO[i][j]=maxValueG;
                
                /// B L U E ///
                modB[0]=mB[i-1][j-1];
                modB[1]=mB[i-1][j];
                modB[2]=mB[i-1][j+1];
                modB[3]=mB[i][j-1];
                modB[4]=mB[i][j];
                modB[5]=mB[i][j+1];
                modB[6]=mB[i+1][j-1];
                modB[7]=mB[i+1][j];
                modB[8]=mB[i+1][j+1];
                for (int k=0; k<9; k++) {
                    int countB=0;
                    for (int l=0; l<9; l++) {
                        if (modB[l]==modB[k]){
                            countB++;
                        }
                    }
                    if (countB>maxCountB) {
                        maxCountB=countB;
                        maxValueB=modB[k];
                    }
                }
                mBO[i][j]=maxValueB;
                System.out.print(mRO[i][j]+" ");
            }
            System.out.println();
        }
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                Color cOut = new Color(mR[i][j], mG[i][j], mB[i][j]);
                imgOut.setRGB(j-1, i-1, cOut.getRGB());
            }
        }
        return imgOut;
    }
    
    public BufferedImage sharpen(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] mR=new int[y+2][x+2];
        int[][] mG=new int[y+2][x+2];
        int[][] mB=new int[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mSharp=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mSharp[0]=-1;
        mSharp[1]=-1;
        mSharp[2]=-1;
        mSharp[3]=-1;
        mSharp[4]=9;
        mSharp[5]=-1;
        mSharp[6]=-1;
        mSharp[7]=-1;
        mSharp[8]=-1;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=  mR[i-1][j-1]*mSharp[0]
                            +mR[i-1][j]*mSharp[1]
                            +mR[i-1][j+1]*mSharp[2]
                            +mR[i][j-1]*mSharp[3]
                            +mR[i][j]*mSharp[4]
                            +mR[i][j+1]*mSharp[5]
                            +mR[i+1][j-1]*mSharp[6]
                            +mR[i+1][j]*mSharp[7]
                            +mR[i+1][j+1]*mSharp[8];
                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                
                mGO[i][j]=  mG[i-1][j-1]*mSharp[0]
                            +mG[i-1][j]*mSharp[1]
                            +mG[i-1][j+1]*mSharp[2]
                            +mG[i][j-1]*mSharp[3]
                            +mG[i][j]*mSharp[4]
                            +mG[i][j+1]*mSharp[5]
                            +mG[i+1][j-1]*mSharp[6]
                            +mG[i+1][j]*mSharp[7]
                            +mG[i+1][j+1]*mSharp[8];
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                
                mBO[i][j]=  mB[i-1][j-1]*mSharp[0]
                            +mB[i-1][j]*mSharp[1]
                            +mB[i-1][j+1]*mSharp[2]
                            +mB[i][j-1]*mSharp[3]
                            +mB[i][j]*mSharp[4]
                            +mB[i][j+1]*mSharp[5]
                            +mB[i+1][j-1]*mSharp[6]
                            +mB[i+1][j]*mSharp[7]
                            +mB[i+1][j+1]*mSharp[8];
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    
    public BufferedImage edgePrewitt(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=-1;
        mX[1]=-1;
        mX[2]=-1;
        mX[3]=0;
        mX[4]=0;
        mX[5]=0;
        mX[6]=1;
        mX[7]=1;
        mX[8]=1;
        
        mY[0]=-1;
        mY[1]=0;
        mY[2]=1;
        mY[3]=-1;
        mY[4]=0;
        mY[5]=1;
        mY[6]=-1;
        mY[7]=0;
        mY[8]=1;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int) Math.round(sqrt((mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8])*
                                            (mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8])
                                            +
                                            (mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8])*
                                            (mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) Math.round(sqrt((mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8])*
                                            (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8])
                                            +
                                            (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8])*
                                            (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) Math.round(sqrt((mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8])*
                                            (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8])
                                            +
                                            (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8])*
                                            (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgeSobel(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=-1;
        mX[1]=0;
        mX[2]=1;
        mX[3]=-2;
        mX[4]=0;
        mX[5]=2;
        mX[6]=-1;
        mX[7]=0;
        mX[8]=1;
        
        mY[0]=-1;
        mY[1]=2;
        mY[2]=1;
        mY[3]=0;
        mY[4]=0;
        mY[5]=0;
        mY[6]=-1;
        mY[7]=-2;
        mY[8]=-1;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int) Math.round(sqrt((mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8])*
                                            (mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8])
                                            +
                                            (mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8])*
                                            (mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) Math.round(sqrt((mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8])*
                                            (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8])
                                            +
                                            (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8])*
                                            (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) Math.round(sqrt((mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8])*
                                            (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8])
                                            +
                                            (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8])*
                                            (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgeSobelX(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=-1;
        mX[1]=0;
        mX[2]=1;
        mX[3]=-2;
        mX[4]=0;
        mX[5]=2;
        mX[6]=-1;
        mX[7]=0;
        mX[8]=1;
        
        mY[0]=-1;
        mY[1]=2;
        mY[2]=1;
        mY[3]=0;
        mY[4]=0;
        mY[5]=0;
        mY[6]=-1;
        mY[7]=-2;
        mY[8]=-1;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgeSobelY(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=-1;
        mX[1]=0;
        mX[2]=1;
        mX[3]=-2;
        mX[4]=0;
        mX[5]=2;
        mX[6]=-1;
        mX[7]=0;
        mX[8]=1;
        
        mY[0]=-1;
        mY[1]=2;
        mY[2]=1;
        mY[3]=0;
        mY[4]=0;
        mY[5]=0;
        mY[6]=-1;
        mY[7]=-2;
        mY[8]=-1;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgeLaplaceP(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=0;
        mX[1]=1;
        mX[2]=0;
        mX[3]=1;
        mX[4]=-4;
        mX[5]=1;
        mX[6]=0;
        mX[7]=1;
        mX[8]=0;
        
        mY[0]=0;
        mY[1]=-1;
        mY[2]=0;
        mY[3]=-1;
        mY[4]=4;
        mY[5]=-1;
        mY[6]=0;
        mY[7]=-1;
        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgeLaplaceN(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=0;
        mX[1]=1;
        mX[2]=0;
        mX[3]=1;
        mX[4]=-4;
        mX[5]=1;
        mX[6]=0;
        mX[7]=1;
        mX[8]=0;
        
        mY[0]=0;
        mY[1]=-1;
        mY[2]=0;
        mY[3]=-1;
        mY[4]=4;
        mY[5]=-1;
        mY[6]=0;
        mY[7]=-1;
        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgeLaplace(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=0;
        mX[1]=1;
        mX[2]=0;
        mX[3]=1;
        mX[4]=-4;
        mX[5]=1;
        mX[6]=0;
        mX[7]=1;
        mX[8]=0;
        
        mY[0]=0;
        mY[1]=-1;
        mY[2]=0;
        mY[3]=-1;
        mY[4]=4;
        mY[5]=-1;
        mY[6]=0;
        mY[7]=-1;
        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int) Math.round(sqrt((mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8])*
                                            (mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8])
                                            +
                                            (mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8])*
                                            (mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) Math.round(sqrt((mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8])*
                                            (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8])
                                            +
                                            (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8])*
                                            (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) Math.round(sqrt((mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8])*
                                            (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8])
                                            +
                                            (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8])*
                                            (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgeFreiChan(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        float[] mX=new float[9];
        float[] mY=new float[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=-1;
        mX[1]=0;
        mX[2]=1;
        mX[3]=(float) (-2-sqrt(1/2));
        mX[4]=0;
        mX[5]=(float) (2+sqrt(1/2));
        mX[6]=-1;
        mX[7]=0;
        mX[8]=1;
        
        mY[0]=-1;
        mY[1]=(float) (-2-sqrt(1/2));
        mY[2]=-1;
        mY[3]=0;
        mY[4]=0;
        mY[5]=0;
        mY[6]=1;
        mY[7]=(float) (2+sqrt(1/2));
        mY[8]=1;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int) Math.round(sqrt((mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8])*
                                            (mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8])
                                            +
                                            (mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8])*
                                            (mR[i-1][j-1]*mY[0]+
                                            mR[i-1][j]*mY[1]+
                                            mR[i-1][j+1]*mY[2]+
                                            mR[i][j-1]*mY[3]+
                                            mR[i][j]*mY[4]+
                                            mR[i][j+1]*mY[5]+
                                            mR[i+1][j-1]*mY[6]+
                                            mR[i+1][j]*mY[7]+
                                            mR[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) Math.round(sqrt((mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8])*
                                            (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8])
                                            +
                                            (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8])*
                                            (mG[i-1][j-1]*mY[0]+
                                            mG[i-1][j]*mY[1]+
                                            mG[i-1][j+1]*mY[2]+
                                            mG[i][j-1]*mY[3]+
                                            mG[i][j]*mY[4]+
                                            mG[i][j+1]*mY[5]+
                                            mG[i+1][j-1]*mY[6]+
                                            mG[i+1][j]*mY[7]+
                                            mG[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) Math.round(sqrt((mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8])*
                                            (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8])
                                            +
                                            (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8])*
                                            (mB[i-1][j-1]*mY[0]+
                                            mB[i-1][j]*mY[1]+
                                            mB[i-1][j+1]*mY[2]+
                                            mB[i][j-1]*mY[3]+
                                            mB[i][j]*mY[4]+
                                            mB[i][j+1]*mY[5]+
                                            mB[i+1][j-1]*mY[6]+
                                            mB[i+1][j]*mY[7]+
                                            mB[i+1][j+1]*mY[8])
                                        )
                                    );
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    
    public BufferedImage edgePrewitt2N(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=-1;
        mX[1]=-1;
        mX[2]=-1;
        mX[3]=1;
        mX[4]=-2;
        mX[5]=1;
        mX[6]=1;
        mX[7]=1;
        mX[8]=1;
        
//        mY[0]=0;
//        mY[1]=-1;
//        mY[2]=0;
//        mY[3]=-1;
//        mY[4]=4;
//        mY[5]=-1;
//        mY[6]=0;
//        mY[7]=-1;
//        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgePrewitt2NW(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=-1;
        mX[1]=-1;
        mX[2]=1;
        mX[3]=-1;
        mX[4]=-2;
        mX[5]=1;
        mX[6]=1;
        mX[7]=1;
        mX[8]=1;
        
//        mY[0]=0;
//        mY[1]=-1;
//        mY[2]=0;
//        mY[3]=-1;
//        mY[4]=4;
//        mY[5]=-1;
//        mY[6]=0;
//        mY[7]=-1;
//        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgePrewitt2W(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=-1;
        mX[1]=1;
        mX[2]=1;
        mX[3]=-1;
        mX[4]=-2;
        mX[5]=1;
        mX[6]=-1;
        mX[7]=1;
        mX[8]=1;
        
//        mY[0]=0;
//        mY[1]=-1;
//        mY[2]=0;
//        mY[3]=-1;
//        mY[4]=4;
//        mY[5]=-1;
//        mY[6]=0;
//        mY[7]=-1;
//        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgePrewitt2SW(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=1;
        mX[1]=1;
        mX[2]=1;
        mX[3]=-1;
        mX[4]=-2;
        mX[5]=1;
        mX[6]=-1;
        mX[7]=-1;
        mX[8]=1;
        
//        mY[0]=0;
//        mY[1]=-1;
//        mY[2]=0;
//        mY[3]=-1;
//        mY[4]=4;
//        mY[5]=-1;
//        mY[6]=0;
//        mY[7]=-1;
//        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgePrewitt2S(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=1;
        mX[1]=1;
        mX[2]=1;
        mX[3]=1;
        mX[4]=-2;
        mX[5]=1;
        mX[6]=-1;
        mX[7]=-1;
        mX[8]=-1;
        
//        mY[0]=0;
//        mY[1]=-1;
//        mY[2]=0;
//        mY[3]=-1;
//        mY[4]=4;
//        mY[5]=-1;
//        mY[6]=0;
//        mY[7]=-1;
//        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgePrewitt2SE(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=1;
        mX[1]=1;
        mX[2]=1;
        mX[3]=1;
        mX[4]=-2;
        mX[5]=-1;
        mX[6]=1;
        mX[7]=-1;
        mX[8]=-1;
        
//        mY[0]=0;
//        mY[1]=-1;
//        mY[2]=0;
//        mY[3]=-1;
//        mY[4]=4;
//        mY[5]=-1;
//        mY[6]=0;
//        mY[7]=-1;
//        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgePrewitt2E(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=1;
        mX[1]=1;
        mX[2]=-1;
        mX[3]=1;
        mX[4]=-2;
        mX[5]=-1;
        mX[6]=1;
        mX[7]=1;
        mX[8]=-1;
        
//        mY[0]=0;
//        mY[1]=-1;
//        mY[2]=0;
//        mY[3]=-1;
//        mY[4]=4;
//        mY[5]=-1;
//        mY[6]=0;
//        mY[7]=-1;
//        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
    public BufferedImage edgePrewitt2NE(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        float[][] mR=new float[y+2][x+2];
        float[][] mG=new float[y+2][x+2];
        float[][] mB=new float[y+2][x+2];
        int[][] mRO=new int[y+2][x+2];
        int[][] mGO=new int[y+2][x+2];
        int[][] mBO=new int[y+2][x+2];
        int[] mX=new int[9];
        int[] mY=new int[9];
        BufferedImage imgOut=new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                Color c = new Color(img.getRGB(j, i));
                mR[i+1][j+1]=c.getRed();
                mG[i+1][j+1]=c.getGreen();
                mB[i+1][j+1]=c.getBlue();
            }
        }
        mX[0]=1;
        mX[1]=-1;
        mX[2]=-1;
        mX[3]=1;
        mX[4]=-2;
        mX[5]=-1;
        mX[6]=1;
        mX[7]=1;
        mX[8]=1;
        
//        mY[0]=0;
//        mY[1]=-1;
//        mY[2]=0;
//        mY[3]=-1;
//        mY[4]=4;
//        mY[5]=-1;
//        mY[6]=0;
//        mY[7]=-1;
//        mY[8]=0;
        
        for(int i=1; i<=y; i++){
            for(int j=1; j<=x; j++){
                mRO[i][j]=(int)(mR[i-1][j-1]*mX[0]+
                                            mR[i-1][j]*mX[1]+
                                            mR[i-1][j+1]*mX[2]+
                                            mR[i][j-1]*mX[3]+
                                            mR[i][j]*mX[4]+
                                            mR[i][j+1]*mX[5]+
                                            mR[i+1][j-1]*mX[6]+
                                            mR[i+1][j]*mX[7]+
                                            mR[i+1][j+1]*mX[8]);

                if((mRO[i][j])>=255||mRO[i][j]<=0){
                    if(mRO[i][j]>=255){
                        mRO[i][j]=255;
                    }
                    else if(mRO[i][j]<=0){
                        mRO[i][j]=0;
                    }
                }
                mGO[i][j]=(int) (mG[i-1][j-1]*mX[0]+
                                            mG[i-1][j]*mX[1]+
                                            mG[i-1][j+1]*mX[2]+
                                            mG[i][j-1]*mX[3]+
                                            mG[i][j]*mX[4]+
                                            mG[i][j+1]*mX[5]+
                                            mG[i+1][j-1]*mX[6]+
                                            mG[i+1][j]*mX[7]+
                                            mG[i+1][j+1]*mX[8]);
                if((mGO[i][j])>=255||mGO[i][j]<=0){
                    if(mGO[i][j]>=255){
                        mGO[i][j]=255;
                    }
                    else if(mGO[i][j]<=0){
                        mGO[i][j]=0;
                    }
                }
                mBO[i][j]=(int) (mB[i-1][j-1]*mX[0]+
                                            mB[i-1][j]*mX[1]+
                                            mB[i-1][j+1]*mX[2]+
                                            mB[i][j-1]*mX[3]+
                                            mB[i][j]*mX[4]+
                                            mB[i][j+1]*mX[5]+
                                            mB[i+1][j-1]*mX[6]+
                                            mB[i+1][j]*mX[7]+
                                            mB[i+1][j+1]*mX[8]);
                if((mBO[i][j])>=255||mBO[i][j]<=0){
                    if(mBO[i][j]>=255){
                        mBO[i][j]=255;
                    }
                    else if(mBO[i][j]<=0){
                        mBO[i][j]=0;
                    }
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
}
