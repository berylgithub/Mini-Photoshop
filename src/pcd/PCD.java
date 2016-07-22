/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcd;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 *
 * @author Yorozuya
 */
public class PCD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BufferedImage img = null;
        String dir="D:/Users/Yorozuya/Documents/Matkul smt 5/Image Processing/Tugas 2 Downsampling+Kuantisasi/peppers.jpg";
        String dir1="D:/Users/Yorozuya/Documents/Matkul smt 5/Image Processing/Output Gambar/Original_Image2.jpg";
        String dir2="D:/OutputImage.png";
        int h = 256;
        int w = 256;
//        JFrame f = new JFrame("Loaded Image");
        try {
            img = ImageIO.read(new File(dir1));
            //DownSample downs=new DownSample(img);
            
        } catch (IOException ex) {
            Logger.getLogger(PCD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
//        f.addWindowListener(new WindowAdapter(){
//                public void windowClosing(WindowEvent e) {
//                    System.exit(0);
//                }
//            });
// 
//        f.add(new LoadImage());
//        f.pack();
//        f.setVisible(true);
        
//        BufferedImage imgOut=new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
//        BufferedImage imgGray=new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        LoadImage li = new LoadImage(dir1,dir2);
        Sampling ds = new Sampling();
        Quantize qi = new Quantize();
        Zoom zin=new Zoom();
        Rotate rot45=new Rotate();
        Flip flip = new Flip();
        Crop crop = new Crop();
        Histogram hist = new Histogram();
        Filter FM = new Filter();
        Segmentation SG = new Segmentation();
        Morphology mp = new Morphology();
        
        
        MainFrame F=new MainFrame(img,li,ds,qi,zin,rot45,flip,crop,hist,FM,SG,mp);
        F.setVisible(true);
        
//        imgOut=crop.cropSquare(img);
//        imgOut=flip.flipH(img);
//        imgOut=rot45.rotateBeta(img);
//        imgOut=zin.zoomIn(img);
//        imgOut=zin.zoomOut(img);
//        imgOut=qi.quantizeImage(img);
//        imgOut=ds.Sampling(img);
//        imgOut=hist.histogramEqualization(img);
//        imgGray=hist.toGrayScale(img);
        
/////////H  I   S   T   O   G   R   A   M///////       
//        int[][] arrRGB=new int[3][512*512];
//        int[][] sumRGB=new int[3][256];
//        float[][] cdfRGB=new float[3][256];
//        
//        img=hist.toGrayScale(img);
//        arrRGB=hist.getRGB(img);
//        sumRGB=hist.sumRGB(img, arrRGB);
//        hist.printHistogramRGB(sumRGB);
//        cdfRGB=hist.cdfRGB(img, sumRGB);
//        imgOut=hist.histEq(img, cdfRGB);
   /////////H  I   S   T   O   G   R   A   M///////          
        
        ConvolutionMatrix CM=new ConvolutionMatrix();
        int[][] A=new int[10][10];
        int[][] B=new int[3][3];
        int[][] result=new int[10][10];
        
        A=CM.createInitialMatrix();
        CM.printInitialMatrix(A);
        
        B=CM.createMaskMatrix();
        CM.printMaskMatrix(B);
        
        result=CM.convolutionWithEdge(A, B);
        CM.printResult(result);
        
   /////////F I L T E R/////// 
//        int[][] filteredMat=new int[512][512];
//        imgOut=FM.meanFilter3(img);
//        imgOut=FM.medianFilter3(img);
//        imgOut=FM.modusFilter3(img);
//        imgOut=FM.sharpen(img);
//        imgOut=FM.edgePrewitt(img);
//        imgOut=FM.edgeSobel(img);
//        imgOut=FM.edgeSobelX(img);
//        imgOut=FM.edgeSobelY(img);
//        imgOut=FM.edgeLaplaceP(img);
//        imgOut=FM.edgeLaplaceN(img);
//        imgOut=FM.edgeLaplace(img);
//        imgOut=FM.edgeFreiChan(img);
//        imgOut=FM.edgePrewitt2N(img);
//        imgOut=FM.edgePrewitt2NW(img);     
//        imgOut=FM.edgePrewitt2W(img);
//        imgOut=FM.edgePrewitt2SW(img);
//        imgOut=FM.edgePrewitt2S(img);
//        imgOut=FM.edgePrewitt2SE(img);
//        imgOut=FM.edgePrewitt2E(img);
//        imgOut=FM.edgePrewitt2NE(img);
        
////////// S E G M E N T A T I O N ///////////////
//        imgOut=SG.binary(img);
        
////////// M O R P H O L O G Y ////////////
//        imgOut=mp.dilation(img);
//        imgOut=mp.erosion(imgOut);
//        try {
//            li.saveImage(imgOut);
//        } catch (IOException ex) {
//            Logger.getLogger(PCD.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
