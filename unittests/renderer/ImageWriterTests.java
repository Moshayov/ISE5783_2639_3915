package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTests {

    @Test
    void testWriteToImage() {
    /* The code creates an image with the specified dimensions and resolution.
     It generates a pattern where the grid cells are colored black, and the spaces between the grid cells are colored pink.
      The resulting image is saved to the disk with the name "FirstImage"
    * */
        int width = 16;
        int height = 10;
        int nX = 800;
        int nY = 500;
        Color pink = new Color(254,102,254);
        Color black = new Color(0,0,0);
        ImageWriter image = new ImageWriter("FirstImage",nX,nY);
        double space= (double) image.getNx() /width;
        for (int i=0;i<nX;i++)//scan the width
        {
            for(int j=0;j<nY;j++)
            {
                if (j % space==0 || i % space==0)
                {
                    image.writePixel(i,j,black);
                }
                else
                {
                    image.writePixel(i,j,pink);
                }
            }
        }
        image.writeToImage();


    }

}