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
public class Flip {

    public Flip() {
    }
    public BufferedImage flipH(BufferedImage img){
        BufferedImage imgTemp = new BufferedImage(img.getWidth()*2, img.getWidth()*2, BufferedImage.TYPE_INT_RGB);
        BufferedImage imgOut = new BufferedImage(img.getWidth(), img.getWidth(), BufferedImage.TYPE_INT_RGB);
        int x = img.getWidth();
        int y = img.getHeight();
        int i,j,k;
        k = img.getWidth();
        for(i=0; i<y; i++){
            for(j=0; j<x; j++){
                imgTemp.setRGB(k, j, img.getRGB(i,j));
            }
            k=k-1;
        }
        for(i=0; i<y; i++){
            for(j=0; j<x; j++){
                imgOut.setRGB(j, i, imgTemp.getRGB(j, i));
            }
        }
        return imgOut;
    }
    
    public BufferedImage flipV(BufferedImage img){
        BufferedImage imgOut = new BufferedImage(img.getWidth()*4, img.getWidth()*4, BufferedImage.TYPE_INT_RGB);
        int x = img.getWidth();
        int y = img.getHeight();
        int i,j,k;
        k = img.getHeight();
        for(i=0; i<y; i++){
            for(j=0; j<x; j++){
                imgOut.setRGB(i, k, img.getRGB(i,j));
                k=k-1;
            }
            
        }
        return imgOut;
    }
}
