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
public class Quantize {

    public Quantize() {
        
    }
    public BufferedImage quantizeImage(BufferedImage img){
        
        int x = img.getWidth();
        int y = img.getHeight();
        int bitmask1=0xff808080;
        int bitmask2=0xffc0c0c0;
        int bitmask3=0xffe0e0e0;
        int bitmask4=0xfff0f0f0;
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                img.setRGB(i, j, img.getRGB(i, j)&bitmask1);
                }
        }
        return img;
    }
}