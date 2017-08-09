/**
 * Created by asus on 02-Aug-17.
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import java.math.*;
public class hsv {

    public static  float [] Rgbtohsv(int r ,int gree, int b ){

        int red = r , green = gree , blue = b;
        int Chigh = Math.max(red , Math.max(green , blue));
        int Clow = Math.min(red , Math.min(green , blue));
        int Crange = Chigh - Clow;
        float h = 0, s = 0, v = 0  ;
        float Cmax = 255.0f;

        // compute value v
        v = Chigh /Cmax ;

        // compute saturation s

        if (Chigh > 0 )
            s = (float) Crange/Chigh ;

        //compute hue h

        if (Crange > 0 ){
            float rr = (float) (Chigh - red)/Crange;
            float gg = (float) (Chigh - green)/Crange;
            float bb = (float) (Chigh - blue )/Crange;
            float hh ;
            if (red == Chigh)
                hh = bb - gg ;
            else if (green == Chigh)
                hh = rr - bb +2.0f;
            else
                hh = gg - rr + 4.0f;
            if (hh< 0 )
                hh  = hh + 6 ;
            h= hh / 6;
        }

        return  new float[] { h , s , v };

    }
    public static  void main (String []args ){
        BufferedImage img = null  ;
        File f = null ;
        int red =0   ;
        int green= 0  ;
        int blue = 0 ;
        try{
            f = new File ("D:\\Image\\Taj.jpg");
            img = ImageIO.read(f);
        }catch (IOException e) {
            System.out.println("Error " + e);
        }
        for (int i = 0 ; i<img.getWidth() ; i++){
            for (int j = 0 ; j<img.getHeight() ; j++){
                red = new Color(img.getRGB(j , i)).getRed();
                green = new Color (img.getRGB(j , i )).getGreen();
                blue = new Color (img.getRGB(j , i )).getBlue();


                float hsv [] = new float[3];
                for (int x = 0 ; x<3 ; x++){
                    hsv = Rgbtohsv(red , green, blue);
                    System.out.println("Rgb [" +red+ ","+green +","+blue +"] to Hsv ["+hsv[0]+","+hsv[1]+","+hsv[2]+"]");

                }
            }
        }
    }
}
