/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcd;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yorozuya
 */
public class Sampling {

    public Sampling() {
        
    }
    public BufferedImage Sampling(BufferedImage img){
        int x = img.getWidth();
        int y = img.getHeight();
        int[][] tempM = new int[x][y];
        for(int i=0; i<y; i++){
            for(int j=8; j<x; j=j+4){
                if(i%2==0){
                    img.setRGB(j-4, i, img.getRGB(j, i));
                }
                else{
                    img.setRGB(j-5, i, img.getRGB(j, i));
                }
                
            }
        }
        return img;
    }
}
