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
public class Zoom {

    public Zoom() {
    }
    
    public BufferedImage zoomIn(BufferedImage img){
        BufferedImage imgOut = new BufferedImage(img.getWidth()*2, img.getHeight()*2, BufferedImage.TYPE_INT_RGB);
        int x = img.getWidth();
        int y = img.getHeight();
        int i,j,iout,jout;
        iout=0;
        jout=0;
        i=0;
        j=0;
        for(i=0; i<y; i++){
            for(j=0; j<x; j++){
                imgOut.setRGB(jout, iout, img.getRGB(i,j));
                imgOut.setRGB(jout+1, iout, img.getRGB(i,j));
                imgOut.setRGB(jout, iout+1, img.getRGB(i,j));
                imgOut.setRGB(jout+1, iout+1, img.getRGB(i,j));
                iout=iout+2;
            }
            jout=jout+2;
            iout=0;
        }
        return imgOut;
    }
    
    public BufferedImage zoomOut(BufferedImage img){
        BufferedImage imgOut = new BufferedImage(img.getWidth()/2, img.getHeight()/2, BufferedImage.TYPE_INT_RGB);
        int x = img.getWidth();
        int y = img.getHeight();
        int i,j,iout=0,jout=0;
        for(i=0; i<y; i=i+2){
            for(j=0; j<x; j=j+2){
                imgOut.setRGB(jout, iout, img.getRGB(j,i));
                jout++;
            }
            iout++;
            jout=0;
        }
        return imgOut;
    }
}