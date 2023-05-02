// Name: Mahmood Al-Malki
// Program Description: GUI-Based Image Editor

import javax.swing.*; // Import javax.swing.*
import java.awt.*; // Import java.awt.*
import java.awt.image.BufferedImage; // Import java.awt.image.BufferedImage

public class DrawnImageComponent extends JComponent { // Create a subclass of JComponent named DrawnImageComponent used for drawing
    // Yes, our subclasses of JComponent used for drawing can have constructors with arguments!
    // We use this to choose what image to draw, if any. If the default constructor is used OR if the BufferedImage
    // is null, nothing is drawn for the image, and instead, text saying "No image to display" is visible.
    // Since drawing overwrites what was there before, we can just always draw the text and not worry about it being
    // visible, even if the image is very small. That's because we blow up the image to match the same size every time.
    // This means the aspect ratio doesn't appear to be preserved in the app, but the true output file will still have
    // the same resolution as the input.
    private final BufferedImage imageToDraw; // Create a final instance variable of type BufferedImage named imageToDraw

    public DrawnImageComponent() { // Create a default, no-args constructor
        // TODO: assign value to imageToDraw based on description above
        this.imageToDraw = null; // Assign null to imageToDraw
    }
    // TODO: add the constructor that takes in a BufferedImage and assigns it to the instance variable
    public DrawnImageComponent(BufferedImage imageToDraw) { // Create another constructor that takes in a BufferedImage
        this.imageToDraw = imageToDraw; // Assign imageToDraw to the current imageToDraw
    }

    public void paintComponent(Graphics g) { // Create a method named paintComponent() that takes in a Graphics
        g.setColor(Color.BLACK); // Set a black color to the Graphics
        g.drawString("No image to display", 200, 300); // Draw a "No image to display" string to the Graphics at the position (200, 300)
        g.drawImage(imageToDraw, 0, 0, ImageEditorDisplay.IMAGE_DIMENSIONS.width, ImageEditorDisplay.IMAGE_DIMENSIONS.height, this); // Draw an imageToDraw image with a width of 500 and a height of 500 at the position (0, 0)
    }
}