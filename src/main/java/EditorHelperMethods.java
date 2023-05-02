// Name: Mahmood Al-Malki
// Program Description: GUI-Based Image Editor

import javax.imageio.ImageIO; // Import javax.imageio.ImageIO
import java.awt.image.BufferedImage; // Import java.awt.image.BufferedImage
import java.io.File; // Import java.io.File
import java.io.IOException; // Import java.io.IOException
import java.nio.file.Files; // Import java.nio.file.Files
import java.nio.file.Path; // Import java.nio.file.Path
import java.nio.file.StandardCopyOption; // Import java.nio.file.StandardCopyOption

public class EditorHelperMethods { // Create a class named EditorHelperMethods
    // Constants
    public static final int GRAYSCALE_TRANSFORM = 1; // Create a constant static variable named GRAYSCALE_TRANSFORM with a value of 1
    public static final int FLIP_HORIZONTAL_TRANSFORM = 2; // Create a constant static variable named FLIP_HORIZONTAL_TRANSFORM with a value of 2
    public static final int NEGATE_RED_TRANSFORM = 3; // Create a constant static variable named NEGATE_RED_TRANSFORM with a value of 3
    public static final int NEGATE_GREEN_TRANSFORM = 4; // Create a constant static variable named NEGATE_GREEN_TRANSFORM with a value of 4
    public static final int NEGATE_BLUE_TRANSFORM = 5; // Create a constant static variable named NEGATE_BLUE_TRANSFORM with a value of 5
    public static final int JUST_RED_TRANSFORM = 6; // Create a constant static variable named JUST_RED_TRANSFORM with a value of 6
    public static final int JUST_GREEN_TRANSFORM = 7; // Create a constant static variable named JUST_GREEN_TRANSFORM with a value of 7
    public static final int JUST_BLUE_TRANSFORM = 8; // Create a constant static variable named JUST_BLUE_TRANSFORM with a value of 8
    public static final String IMAGES_PATH_PREFIX = "src/main/images/"; // Create a constant static variable named IMAGES_PATH_PREFIX with a value of "src/main/images/"
    public static final String TEMP_FILE_NAME = "temp.ppm"; // Create a constant static variable named TEMP_FILE_NAME with a value of "temp.ppm"

    public static String appendPathPrefix(String relativePath) { // Create a method named appendPathPrefix() that takes in a relativePath
        // This method lets our user just type "cake.ppm", which we know really means "src/main/images/cake.ppm"
        return IMAGES_PATH_PREFIX + relativePath; // Return "src/main/images/{relativePath}"
    }
    public static BufferedImage resolveImageFromString(String relativePath) throws IOException { // Create a method named resolveImageFromString() that takes in a relativePath
        // Creates a new BufferedImage out of the path. Thanks, TwelveMonkeys!
        return ImageIO.read(new File(appendPathPrefix(relativePath))); // Return a new BufferedImage out of the path
    }

    public static BufferedImage transformImage(String relativePath, int transformCode) throws IOException { // Create a method named transformImage() that takes in a relativePath and a transformCode
        // Gets the input and output 3D arrays ready using PPMHelperMethods.
        int[][][] inputImagePixels = PPMHelperMethods.parsePPMFile(relativePath); // TODO: call the right PPMHelperMethod ***and give it the right path!!!***
        int[][][] outputImagePixels; // Declare a variable named outputImagePixels
        // Call the appropriate method in PPMHelperMethods to assign the right value to outputImagePixels.
        // For example, if the transform code matches GRAYSCALE_TRANSFORM, call the grayScale method, and so on...
        // TODO: implement that functionality, calling methods from PPMHelperMethods
        if (transformCode == GRAYSCALE_TRANSFORM) { // If transformCode matches GRAYSCALE_TRANSFORM
            outputImagePixels = PPMHelperMethods.grayScale(inputImagePixels); // Call the grayScale() method and assign it to outputImagePixels
        }
        else if (transformCode == FLIP_HORIZONTAL_TRANSFORM) { // Else if transformCode matches GRAYSCALE_TRANSFORM
            outputImagePixels = PPMHelperMethods.flipHorizontal(inputImagePixels); // Call the flipHorizontal() method and assign it to outputImagePixels
        }
        else if (transformCode == NEGATE_RED_TRANSFORM) { // Else if transformCode matches NEGATE_RED_TRANSFORM
            outputImagePixels = PPMHelperMethods.negateRed(inputImagePixels); // Call the negateRed() method and assign it to outputImagePixels
        }
        else if (transformCode == NEGATE_GREEN_TRANSFORM) { // Else if transformCode matches NEGATE_GREEN_TRANSFORM
            outputImagePixels = PPMHelperMethods.negateGreen(inputImagePixels); // Call the negateGreen() method and assign it to outputImagePixels
        }
        else if (transformCode == NEGATE_BLUE_TRANSFORM) { // Else if transformCode matches NEGATE_BLUE_TRANSFORM
            outputImagePixels = PPMHelperMethods.negateBlue(inputImagePixels); // Call the negateBlue() method and assign it to outputImagePixels
        }
        else if (transformCode == JUST_RED_TRANSFORM) { // Else if transformCode matches JUST_RED_TRANSFORM
            outputImagePixels = PPMHelperMethods.justTheReds(inputImagePixels); // Call the justTheReds() method and assign it to outputImagePixels
        }
        else if (transformCode == JUST_GREEN_TRANSFORM) { // Else if transformCode matches JUST_GREEN_TRANSFORM
            outputImagePixels = PPMHelperMethods.justTheGreens(inputImagePixels); // Call the justTheGreens() method and assign it to outputImagePixels
        }
        else if (transformCode == JUST_BLUE_TRANSFORM) { // Else if transformCode matches JUST_BLUE_TRANSFORM
            outputImagePixels = PPMHelperMethods.justTheBlues(inputImagePixels); // Call the justTheBlues() method and assign it to outputImagePixels
        }
        else throw new IllegalArgumentException("Unrecognized transform code: " + transformCode); // Otherwise, throw an IllegalArgumentException
        // Lastly, save the outputImagePixels to a new PPM file, using the default temp.ppm name
        // TODO do that ^
        PPMHelperMethods.printToPPMFile(appendPathPrefix(TEMP_FILE_NAME), outputImagePixels); // Save the outputImagePixels to a new PPM file, using the default temp.ppm name

        // Once we've saved this image to a file, return the BufferedImage that we get when we read it in
        return resolveImageFromString(TEMP_FILE_NAME); // Return the BufferedImage that we get when we read it in
    }

    public static void saveTempImage(String relativePath) throws IOException { // Create a method named saveTempImage() that takes in a relativePath
        // When the user wants to save temp.ppm, they give us the name of the file they want to save to, and we duplicate
        // the current temp.ppm to that new file name. If a file with that name already exists, just overwrite it.
        Files.copy(Path.of(appendPathPrefix(TEMP_FILE_NAME)), Path.of(appendPathPrefix(relativePath)), StandardCopyOption.REPLACE_EXISTING);
    }
}