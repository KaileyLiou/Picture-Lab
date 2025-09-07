import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
* A class that represents a picture.  This class inherits from
* SimplePicture and allows the student to add functionality to
* the Picture class.
*
* @author Barbara Ericson ericson@cc.gatech.edu
*/
public class Picture extends SimplePicture
{
 ///////////////////// constructors //////////////////////////////////
 /**
  * Constructor that takes no arguments
  */
 public Picture ()
 {
   /* not needed but use it to show students the implicit call to super()
    * child constructors always call a parent constructor
    */
   super();
 }
 /**
  * Constructor that takes a file name and creates the picture
  * @param fileName the name of the file to create the picture from
  */
 public Picture(String fileName)
 {
   // let the parent class handle this fileName
   super(fileName);
 }
 /**
  * Constructor that takes the width and height
  * @param height the height of the desired picture
  * @param width the width of the desired picture
  */
 public Picture(int height, int width)
 {
   // let the parent class handle this width and height
   super(width,height);
 }
 /**
  * Constructor that takes a picture and creates a
  * copy of that picture
  * @param copyPicture the picture to copy
  */
 public Picture(Picture copyPicture)
 {
   // let the parent class do the copy
   super(copyPicture);
 }
 /**
  * Constructor that takes a buffered image
  * @param image the buffered image to use
  */
 public Picture(BufferedImage image)
 {
   super(image);
 }
 ////////////////////// methods ///////////////////////////////////////
 /**
  * Method to return a string with information about this picture.
  * @return a string with information about the picture such as fileName,
  * height and width.
  */
 public String toString()
 {
   String output = "Picture, filename " + getFileName() +
     " height " + getHeight()
     + " width " + getWidth();
   return output;

 }
 /** Method to set the blue to 0 */
 public void zeroBlue()
 {
   Pixel[][] pixels = this.getPixels2D();
   for (Pixel[] rowArray : pixels)
   {
     for (Pixel pixelObj : rowArray)
     {
       pixelObj.setBlue(0);
     }
   }
 }
 /** Method to set the red and blue to 0 */
 public void greenOnly()
 {
   Pixel[][] pixels = this.getPixels2D();
   for (Pixel[] rowArray : pixels)
   {
     for (Pixel pixelObj : rowArray)
     {
       pixelObj.setBlue(0);
       pixelObj.setRed(0);
     }
   }
 }
 /** Method to set the red,green,blue to (255 - its original value)  */
 public void negate()
 {
   Pixel[][] pixels = this.getPixels2D();
   for (Pixel[] rowArray : pixels)
   {
     for (Pixel pixelObj : rowArray)
     {
       pixelObj.setBlue(255 - pixelObj.getBlue());
       pixelObj.setRed(255 - pixelObj.getRed());
       pixelObj.setGreen(255 - pixelObj.getGreen());
     }
   }
 }
 /** Method to set all red,green,blue to the average of the three values  */
 public void grayscale()
 {
   Pixel[][] pixels = this.getPixels2D();
   for (Pixel[] rowArray : pixels)
   {
     for (Pixel pixelObj : rowArray)
     {
       int average = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen()) / 3;
       pixelObj.setRed(average);
       pixelObj.setBlue(average);
       pixelObj.setGreen(average);
     }
   }
 }
 /** Method to make the shape of the fish stand out  */
 public void enhanceFish()
 {
   Pixel[][] pixels = this.getPixels2D();
   for (Pixel[] rowArray : pixels)
   {
     for (Pixel pixelObj : rowArray)
     {
       int blue = pixelObj.getBlue();
       int red = pixelObj.getRed();
       int green = pixelObj.getGreen();

       if(blue > red && blue > green) {
         pixelObj.setBlue(255);
         pixelObj.setRed(255);
         pixelObj.setGreen(255);
       }
     }
   }
 }
 /** Method to highlight the edges of object in a picture by checking large changes in color */
 public void edgeDetection()
 {
   Pixel[][] pixels = this.getPixels2D();
   for (int r = 0; r < pixels.length; r++)
   {
     for (int c = 0; c < pixels[0].length - 1; c++)
     {
       int value = 10;
       Pixel pixel1 = pixels[r][c];
       Pixel pixel2 = pixels[r][c+1];
       Color color1 = pixel1.getColor();
       Color color2 = pixel2.getColor();

       double colorDistance = pixel1.colorDistance(color2);

       if(colorDistance > value ) {
         pixel1.setBlue(0);
         pixel1.setRed(0);
         pixel1.setGreen(0);
       }
       else {
         pixel1.setBlue(255);
         pixel1.setRed(255);
         pixel1.setGreen(255);
       }
     }
   }
 }
 /** Method to mirror the picture around a vertical line in the center of the picture from left to right */
 public void mirrorVertical()
 {
   Pixel[][] pixels = this.getPixels2D();
   int width = pixels[0].length;

   for (int r = 0; r < pixels.length; r++)
   {
     for (int c = 0; c < width / 2; c++)
     {
       Pixel left = pixels[r][c];
       Pixel right = pixels[r][width - c - 1];
       right.setColor(left.getColor());
     }
   }
 }
 /** Method to mirror around a diagonal line from bottom left to top right */
 public void mirrorDiagonal()
 {
   Pixel[][] pixels = this.getPixels2D();

   for (int r = 0; r < pixels.length; r++)
   {
     for (int c = 0; c < r; c++)
     {
       Pixel pixel1 = pixels[r][c];
       Pixel pixel2 = pixels[c][r];
       pixel2.setColor(pixel1.getColor());
     }
   }
 }

 /** Method to mirror just part of a picture of a temple to fix the broken part of the temple */
 public void mirrorTemple()
 {
   Pixel[][] pixels = this.getPixels2D();
   int left = 16;
   int right = 538;
   int top = 30;
   int bottom = 95;

   for (int r = top; r < bottom; r++)
   {
     for (int c = left; c < right / 2; c++)
     {
       Pixel pixel = pixels[r][c];
       Pixel newPixel = pixels[r][right - c - 1];
       newPixel.setColor(pixel.getColor());
     }
   }
 }
 /** Method to mirror just part of a picture of a snowman, so that it will have four arms instead of two */
 public void mirrorArms()
 {
   Pixel[][] pixels = this.getPixels2D();
  //  int left = 105;
  //  int right = 170;
  //  int top = 160;
  //  int bottom = 190;

   for (int r = 160; r < 190; r++)
   {
     for (int c = 105; c < 170; c++)
     {
       Pixel pixel = pixels[r][c];
       Pixel newPixel = pixels[2 * 190 - r][c];
       newPixel.setColor(pixel.getColor());
     }
   }

  //  int left = 238;
  //  int right = 293;
  //  int top = 172;
  //  int bottom = 197;

   for (int r = 172; r < 197; r++)
   {
     for (int c = 238; c < 293; c++)
     {
       Pixel pixel = pixels[r][c];
       Pixel newPixel = pixels[2 * 190 - r][c];
       newPixel.setColor(pixel.getColor());
     }
   }

 }
 /** Method to copy the gull in the picture to another location of the picture */
 public void copyGull()
 {
   Pixel[][] pixels = this.getPixels2D();
  //  int left = 239;
  //  int right = 343;
  //  int top = 233;
  //  int bottom = 321;

   for (int r = 233; r < 321; r++)
   {
     for (int c = 239; c < 343; c++)
     {
       Pixel pixel = pixels[r][c];
       Pixel newPixel = pixels[r][2 * 344 - c];
       newPixel.setColor(pixel.getColor());
     }
   }
 }

 /** Method to replace the blue background with the pixels in the newBack picture
   * @param newBack the picture to copy from
   */
 public void chromakey(Picture newBack)
 {
  Pixel[][] pixels = this.getPixels2D();
  Pixel[][] newBackPixels = newBack.getPixels2D();

  for (int r = 0; r < pixels.length; r++)
  {
    for (int c = 0; c < pixels[0].length; c++)
    {
      Pixel pixel = pixels[r][c];

      int blue = pixel.getBlue();
      int red = pixel.getRed();
      int green = pixel.getGreen();

      if(blue > red && blue > green) {
        Pixel newPixel = newBackPixels[r][c];
        pixel.setColor(newPixel.getColor());
      }
    }
  }
 }
 /** Method to decode a message hidden in the red value of the current picture */
 public void decode()
 {
   Pixel[][] pixels = this.getPixels2D();
 
   for (int r = 0; r < pixels.length; r++)
   {
     for (int c = 0; c < pixels[0].length; c++)
     {
       Pixel pixel = pixels[r][c];
 
       if(pixel.getRed() % 2 == 0) {
        pixel.setBlue(0);
        pixel.setRed(0);
        pixel.setGreen(0);
       } else {
        pixel.setBlue(255);
        pixel.setRed(255);
        pixel.setGreen(255);
       }
     }
   }
 }
 /** Hide a black and white message in the current picture by changing the green to even and then setting it to odd if the message pixel is black
   * @param messagePict the picture with a message
   */
 public void encodeGreen(Picture messagePict)
 {
  Pixel[][] pixels = this.getPixels2D();
  Pixel[][] messagePictPixels = messagePict.getPixels2D();

  for (int r = 0; r < pixels.length; r++)
  {
    for (int c = 0; c < pixels[0].length; c++)
    {
      Pixel pixel = pixels[r][c];
      Pixel messagePictPixel = messagePictPixels[r][c];

      if(messagePictPixel.getRed() == 0) {
        int greenVal = pixel.getGreen();
        if(greenVal % 2 == 0) {
          greenVal++;
        }
        pixel.setGreen(greenVal);
      }
      else {
        int greenVal = pixel.getGreen();
        if(greenVal % 2 == 1) {
          greenVal--;
        }
        pixel.setGreen(greenVal);
      }
    }
  }
 }

 /** Your own customized method*/
 public void customized()
 {
  Pixel[][] pixels = this.getPixels2D();

  for (int r = 0; r < pixels.length; r++) {
      for (int c = 0; c < pixels[0].length; c++) {
          Pixel pixel = pixels[r][c];

          int red = pixel.getRed();
          int green = pixel.getGreen();
          int blue = pixel.getBlue();

          red = (red / 85) * 85;
          green = (green / 50) * 50;
          blue = (blue / 40) * 40;

          // red = red / 2;
          blue = Math.min(255, blue + 30);
          green = Math.min(255, green + 55);

          pixel.setRed(red);
          pixel.setGreen(green);
          pixel.setBlue(blue);
      }
  }
 }
}