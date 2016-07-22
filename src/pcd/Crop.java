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
public class Crop {

    public Crop() {
    }
    public BufferedImage cropSquare(BufferedImage img){
        BufferedImage imgOut = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int x = img.getWidth();
        int y = img.getHeight();
        int i,j;
        for(i=0; i<y; i++){
            for(j=0; j<x; j++){
                imgOut.setRGB(j, i, img.getRGB(j,i));
            }
            
        }
         for(i=0; i<y-256; i++){
            for(j=0; j<x-256; j++){
                Color grayCol = new Color(0, 0, 0);
                imgOut.setRGB(j, i, grayCol.getRGB());
                imgOut.setRGB(j+256, i+256, img.getRGB(j,i));
            }
            
        }
        return imgOut;
    }
}
