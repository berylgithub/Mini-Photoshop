/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcd;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Yorozuya
 */
public class LoadImage extends Component{
    private BufferedImage img;
    static String dirOut;
    public static String dir;
    
    public LoadImage(String dirIn, String dirOut) {
        this.dir=dirIn;
        this.dirOut=dirOut;
        try {     
             img = ImageIO.read(new File(dir));
        } catch (IOException e) {

        }


     }
    
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
    
    
 
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(1024,1024);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
    
    public static void saveImage(BufferedImage img) throws FileNotFoundException, IOException
    {
        File outputfile = new File(dirOut);
        ImageIO.write(img, "png", outputfile);
    }
    
}
