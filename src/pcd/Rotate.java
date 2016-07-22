/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcd;

import java.awt.image.BufferedImage;

/**
 *
 * @author Yorozuya
 */
public class Rotate {

    public Rotate() {
    }
    
    public BufferedImage rotateBeta(BufferedImage img){
        BufferedImage imgOut = new BufferedImage(img.getWidth()*3, img.getHeight()*3, BufferedImage.TYPE_INT_RGB);
        int x = img.getWidth();
        int y = img.getHeight();
        int i,j;
        double degree = 135.0;
        double rad = Math.toRadians(degree);
        double cosB = Math.cos(rad);
        double sinB = Math.sin(rad);
        i=0;
        j=0;

        //c=a.cos  – b.sin 
        //d=b.cos  + a.sin 
        for(i=0; i<y; i++){
            for(j=0; j<x; j++){
                double xRotate=(j*cosB)-(i*sinB);
                double yRotate=(j*cosB)+(i*sinB);
                imgOut.setRGB((int)Math.round(xRotate)+768, (int)Math.round(yRotate)+768, img.getRGB(j,i));
            }
        }
        return imgOut;
    }
}
