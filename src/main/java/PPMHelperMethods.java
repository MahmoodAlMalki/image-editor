// Name: Mahmood Al-Malki
// Program Description: GUI-Based Image Editor

import java.io.File; // Import java.io.File
import java.io.FileNotFoundException; // Import java.io.FileNotFoundException
import java.io.PrintWriter; // Import java.io.PrintWriter
import java.util.Scanner; // Import java.util.Scanner

public class PPMHelperMethods { // Create a class named PPMHelperMethods
    // This file is already complete: it came from the A5 solution. You're welcome to replace it with your own code,
    // but you can totally just leave it alone. You'll have to look over it to see the names of the methods, though.
    // Note that some students for A5 modified in the input 3D array. As we've discussed many times, this is bad practice,
    // so if your solution did it that way, please do NOT replace this with your code!
    private final static int MAX_COLOR_VALUE = 255; // Create a constant static variable named MAX_COLOR_VALUE and assign 255 to it
    public static int[][][] parsePPMFile(String path) throws FileNotFoundException, ArrayIndexOutOfBoundsException { // Create a method named parsePPMFile() that takes in a path
        File file = new File(path); // Create a File object
        Scanner sc = new Scanner(file); // Create a Scanner object
        // Parsing header information
        sc.nextLine(); // dump first line "P3"
        int cols = sc.nextInt(); // i.e. width first
        int rows = sc.nextInt(); // i.e. height second
        sc.nextInt(); // dump max value "255"
        // Define array
        int[][][] result = new int[rows][cols][3]; // Define a 3D-array named result
        // Parsing body information
        for (int row = 0; row < rows; row++) { // Loop through each row
            for (int col = 0; col < cols; col++) { // Loop through each column
                for (int channel = 0; channel < 3; channel++) { // Loop through each channel
                    result[row][col][channel] = sc.nextInt(); // Assign each integer in result one by one
                }
            }
        }
        sc.close(); // Close sc
        return result; // Return result
    }

    public static void printToPPMFile(String path, int[][][] image) throws FileNotFoundException { // Create a method named printToPPMFile() that takes in a path and an image
        File file = new File(path); // Create a File object
        PrintWriter pw = new PrintWriter(file); // Create a PrintWriter object
        pw.println("P3"); // Write "P3" to the file
        pw.println(image[0].length + " " + image.length); // cols rows
        pw.println(MAX_COLOR_VALUE); // Write 255 to the file
        for (int[][] row : image) { // Loop through each row in image
            for (int[] pixel : row) { // Loop through each pixel in row
                for (int channel : pixel) { // Loop through each channel in pixel
                    pw.print(channel + " "); // Write channel following a space to the file
                }
                pw.print("    "); // Write spaces to the file
            }
            pw.println(); // Write a new line to the file
        }
        pw.close(); // Close the
    }

    public static int[][][] negateRed(int[][][] image) { // Create a method named negateRed() that takes in an image
        return negateAColor(image, 0); // Call the negateAColor() at channel 0
    }

    public static int[][][] negateGreen(int[][][] image) { // Create a method named negateGreen() that takes in an image
        return negateAColor(image, 1); // Call the negateAColor() at channel 1
    }

    public static int[][][] negateBlue(int[][][] image) { // Create a method named negateBlue() that takes in an image
        return negateAColor(image, 2); // Call the negateAColor() at channel 2
    }

    // Helper method to avoid duplicated code in negation methods above
    private static int[][][] negateAColor(int[][][] image, int channel) throws ArrayIndexOutOfBoundsException { // Create a method named negateAColor that takes in an image and a channel
        int[][][] result = new int[image.length][image[0].length][3]; // Define a 3D-array named result
        for (int row = 0; row < image.length; row++) { // Loop through each in image
            for (int col = 0; col < image[0].length; col++) { // Loop through each column in each row
                result[row][col][0] = (channel == 0 ? MAX_COLOR_VALUE - image[row][col][0] : image[row][col][0]); // Assign the negative of the first channel to the specified spot
                result[row][col][1] = (channel == 1 ? MAX_COLOR_VALUE - image[row][col][1] : image[row][col][1]); // Assign the negative of the second channel to the specified spot
                result[row][col][2] = (channel == 2 ? MAX_COLOR_VALUE - image[row][col][2] : image[row][col][2]); // Assign the negative of the third channel to the specified spot
            }
        }
        return result; // Return result
    }

    public static int[][][] flipHorizontal(int[][][] image) throws ArrayIndexOutOfBoundsException { // Create a method named flipHorizontal() that takes in an image
        int[][][] result = new int[image.length][image[0].length][3]; // Define a 3D-array named result
        for (int row = 0; row < image.length; row++) { // Loop through each row in image
            for (int col = 0; col < image[0].length / 2; col++) { // go thru left half of image, not incl. middle for odd # of cols
                for (int channel = 0; channel < 3; channel++) { // Loop through each channel
                    result[row][col][channel] = image[row][image[0].length-1-col][channel]; // Flip each spot horizontally
                    result[row][image[0].length-1-col][channel] = image[row][col][channel]; // Flip each spot horizontally
                }
            }
        }
        return result; // Return result
    }

    public static int[][][] grayScale(int[][][] image) { // Create a method named grayScale() that takes in an image
        int[][][] result = new int[image.length][image[0].length][3]; // Define a 3D-array named result
        for (int row = 0; row < image.length; row++) { // Loop through each row in image
            for (int col = 0; col < image[0].length; col++) { // Loop through each column in each row
                int averageValue = image[row][col][0] + image[row][col][1] + image[row][col][2] / 3;
                result[row][col][0] = averageValue; // Assign averageValue to the first channel of each pixel
                result[row][col][1] = averageValue; // Assign averageValue to the second channel of each pixel
                result[row][col][2] = averageValue; // Assign averageValue to the third channel of each pixel
            }
        }
        return result; // Return result
    }

    static int[][][] flattenRed(int[][][] image) { // Create a method named flattenRed() that takes in an image
        return flattenAChannel(image, 0); // Call the flattenAChannel() at channel 0
    }

    static int[][][] flattenGreen(int[][][] image) { // Create a method named flattenGreen() that takes in an image
        return flattenAChannel(image, 1); // Call the flattenAChannel() at channel 1
    }

    static int[][][] flattenBlue(int[][][] image) { // Create a method named flattenBlue() that takes in an image
        return flattenAChannel(image, 2); // Call the flattenAChannel() at channel 2
    }

    // Helper method to avoid duplicated code in flatten methods above
    private static int[][][] flattenAChannel(int[][][] image, int channel) { // Create a method named flattenAChannel() that takes in an image and a channel
        int[][][] result = new int[image.length][image[0].length][3]; // Define a 3D-array named result
        for (int row = 0; row < image.length; row++) { // Loop through each row in image
            for (int col = 0; col < image[0].length; col++) { // Loop through column in each row
                result[row][col][0] = (channel == 0 ? 0 : image[row][col][0]); // Set each channel to 0
                result[row][col][1] = (channel == 1 ? 0 : image[row][col][1]); // Set each channel to 0
                result[row][col][2] = (channel == 2 ? 0 : image[row][col][2]); // Set each channel to 0
            }
        }
        return result; // Return result
    }

    // Methods corresponding to actual menu operations:
    public static int[][][] justTheReds(int[][][] image) { // Create a method named justTheReds() that takes in an image
        return flattenGreen(flattenBlue(image)); // Call the method flattenGreen() with an argument of flattenBlue(image)
    }

    public static int[][][] justTheGreens(int[][][] image) { // Create a method named justTheGreens() that takes in an image
        return flattenRed(flattenBlue(image)); // Call the method flattenRed() with an argument of flattenBlue(image)
    }

    public static int[][][] justTheBlues(int[][][] image) { // Create a method named justTheBlues() that takes in an image
        return flattenRed(flattenGreen(image)); // Call the method flattenRed() with an argument of flattenGreen(image)
    }
}