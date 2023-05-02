// Name: Mahmood Al-Malki
// Program Description: GUI-Based Image Editor

import javax.swing.*; // Import javax.swing.*
import java.awt.*; // Import java.awt.*
import java.awt.event.ActionEvent; // Import java.awt.event.ActionEvent
import java.awt.event.ActionListener; // Import java.awt.event.ActionListener
import java.awt.image.BufferedImage; // Import java.awt.image.BufferedImage
import java.io.IOException; // Import java.io.IOException

public class ImageEditorDisplay { // Create a class named ImageEditorDisplay
    // Constants
    final static Dimension IMAGE_DIMENSIONS = new Dimension(500, 500); // Create a constant static variable named IMAGE_DIMENSIONS and assign Dimension of a width of 500 and a height of 500 to it

    public static void main(String[] args) { // Create the main() method
        JFrame frame = getFrame(); // keep the code for making the frame organized into methods
        frame.setVisible(true); // Make the frame appear on the screen
    }

    private static JFrame getFrame() { // Create a method named getFrame()
        // Basic frame properties
        // TODO: frame with title, default close behavior, dimensions 1200x600
        JFrame frame = new JFrame("Image Editor"); // Set frame with the title "Image Editor"
        frame.setPreferredSize(new Dimension(1200, 600)); // Set frame with the preferred size of a width of 1200 and a height of 600
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set frame with the default close behavior

        // mainPanel is the overall panel, which contains 3 panels
        // TODO declare and instantiate a mainPanel JPanel
        JPanel mainPanel = new JPanel(); // Declare and instantiate a mainPanel JPanel

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS)); // formats elements horizontally

        // TODO: Rather than using static, define the elements that need to be referenced in multiple components
        //       here, and pass in references to them to the elements that specifically need them.
        //       For example, the buttons that do the transforming need to know the image name, so they'll need
        //       reference to the inputTextField, which means it needs to be defined here.
        //       Check the parameters for each getXYZPanel method for guidance on what needs to be accessible by reference
        JPanel leftImagePanel = new JPanel(); // Define leftImagePanel
        JPanel rightImagePanel = new JPanel(); // Define rightImagePanel

        JTextField inputPathField = new JTextField("cake.ppm", 30); // default text and size

        // Modular panel pieces: left, middle, and right
        // TODO: create the left, middle, and right panels by calling the methods that create them. Pass in the needed references
        JPanel leftPanel = getLeftPanel(leftImagePanel, inputPathField, rightImagePanel); // Create leftPanel by calling the getLeftPanel() method
        JPanel middlePanel = getMiddlePanel(rightImagePanel, inputPathField); // Create middlePanel by calling the getMiddlePanel() method
        JPanel rightPanel = getRightPanel(rightImagePanel, inputPathField, leftImagePanel); // Create rightPanel by calling the getRightPanel() method

        // Final mainPanel and frame preparations before main() displays it
        // TODO: add everything to the mainPanel/frame appropriately, and remember to pack the frame
        mainPanel.add(leftPanel); // Add leftPanel to mainPanel
        mainPanel.add(middlePanel); // Add middlePanel to mainPanel
        mainPanel.add(rightPanel); // Add rightPanel to mainPanel
        frame.add(mainPanel); // Add mainPanel to frame
        frame.pack(); // Pack the frame

        return frame; // Return the frame
    }

    private static JPanel getLeftPanel(JPanel leftImagePanel, JTextField inputPathField, JPanel rightImagePanel) { // Create a method named getLeftPanel() that takes in a leftImagePanel, an inputPathField, and a rightImagePanel
        // Left Panel: contains a subpanel for the image, a text field, and a button
        JPanel leftPanel = new JPanel(); // Define leftPanel
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Set layout to leftPanel on the y-axis

        // leftImagePanel is a panel within leftPanel which contains only the component that draws the image
        // when an image needs to change, swap it out. This is why its defined statically in the class
        // Add the component that draws the image, but no constructor args because it doesn't draw an image
        // TODO: instantiate the leftImage using our custom JComponent subclass
        DrawnImageComponent leftImage = new DrawnImageComponent(); // Instantiate the leftImage using our custom JComponent subclass

        leftImage.setPreferredSize(IMAGE_DIMENSIONS); // this ensures the image isn't too small to see
        leftImagePanel.add(leftImage); // Add leftImage to leftImagePanel

        // Other elements
        // TODO: add a button for selecting an image with the text "Select Image"
        JButton selectButton = new JButton("Select Image"); // Add a button for selecting an image with the text "Select Image"

        class SelectImageHandler implements ActionListener { // Create an ActionListener named SelectImageHandler
            // Loads the image specified in the inputPathField and replaces the left image with it,
            // throwing an error if the image cannot be found. Clears the right image, too.
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                BufferedImage newImage; // Define a BufferedImage named newImage
                try { // Try
                    newImage = EditorHelperMethods.resolveImageFromString(inputPathField.getText()); // Load the image specified in the inputPathField
                } catch (IOException ex) { // Catch an IOException
                    System.out.println("Error: image '" + inputPathField.getText() + "' could not be found: " + ex.getMessage()); // Throw an error if the image cannot be found
                    return; // Return nothing
                }
                // TODO: use a helper method from this class twice to replace the left image with the new image
                //      and to clear the right image.
                replaceImage(leftImagePanel, newImage); // Replace the left image with the new image
                replaceImage(rightImagePanel, null); // Clear the right image
            }
        }
        // TODO: give the button the ActionListener above for selecting an image
        selectButton.addActionListener(new SelectImageHandler()); // Give the selectButton the SelectImageHandler

        // Last steps with the panel
        // TODO
        leftPanel.add(leftImagePanel); // Add leftImagePanel to leftPanel
        leftPanel.add(inputPathField); // Add inputPathField to leftPanel
        leftPanel.add(selectButton); // Add selectButton to leftPanel

        return leftPanel; // Return leftPanel
    }

    private static JPanel getMiddlePanel(JPanel rightImagePanel, JTextField inputPathField) { // Create a method named getMiddlePanel() that takes in a rightImagePanel and an inputPathField
        // Middle panel has many buttons, one per transformation, and that's it
        // TODO: this one's all on you. Make the buttons, give them appropriate text, and give them action listeners
        //       Throughout this file, define and instantiate the inner classes that implement ActionListener to make our buttons work.
        //       actionPerformed's body for each method should be really short: it just uses the premade method for doing the transform
        //       and passes in the appropriate transform code, rightImagePanel, and inputPathField.
        //       Make sure you don't hardcode the transform code -- use the constants defined in EditorHelperMethods so that
        //       way they always match up.

        JPanel middlePanel = new JPanel(); // Define middlePanel
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS)); // Set layout to leftPanel on the y-axis

        JButton grayscaleButton = new JButton("Grayscale"); // Create grayscaleButton
        JButton flipHorizontalButton = new JButton("Flip Horizontal"); // Create flipHorizontalButton
        JButton negateRedButton = new JButton("Negate Red"); // Create negateRedButton
        JButton negateGreenButton = new JButton("Negate Green"); // Create negateGreenButton
        JButton negateBlueButton = new JButton("Negate Blue"); // Create negateBlueButton
        JButton justTheRedsButton = new JButton("Just The Reds"); // Create justTheRedsButton
        JButton justTheGreensButton = new JButton("Just The Greens"); // Create justTheGreensButton
        JButton justTheBluesButton = new JButton("Just The Blues"); // Create justTheBluesButton

        class GrayscaleHandler implements ActionListener { // Create an ActionListener named GrayscaleHandler
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                performTransform(EditorHelperMethods.GRAYSCALE_TRANSFORM, rightImagePanel, inputPathField); // Perform the grayscale transform
            }
        }

        class FlipHorizontalHandler implements ActionListener { // Create an ActionListener named FlipHorizontalHandler
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                performTransform(EditorHelperMethods.FLIP_HORIZONTAL_TRANSFORM, rightImagePanel, inputPathField); // Perform the flip-horizontal transform
            }
        }

        class NegateRedHandler implements ActionListener { // Create an ActionListener named NegateRedHandler
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                performTransform(EditorHelperMethods.NEGATE_RED_TRANSFORM, rightImagePanel, inputPathField); // Perform the negate-red transform
            }
        }

        class NegateGreenHandler implements ActionListener { // Create an ActionListener named NegateGreenHandler
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                performTransform(EditorHelperMethods.NEGATE_GREEN_TRANSFORM, rightImagePanel, inputPathField); // Perform the negate-green transform
            }
        }

        class NegateBlueHandler implements ActionListener { // Create an ActionListener named NegateBlueHandler
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                performTransform(EditorHelperMethods.NEGATE_BLUE_TRANSFORM, rightImagePanel, inputPathField); // Perform the negate-blue transform
            }
        }

        class JustTheRedsHandler implements ActionListener { // Create an ActionListener named JustTheRedsHandler
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                performTransform(EditorHelperMethods.JUST_RED_TRANSFORM, rightImagePanel, inputPathField); // Perform the just-red transform
            }
        }

        class JustTheGreensHandler implements ActionListener { // Create an ActionListener named JustTheGreensHandler
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                performTransform(EditorHelperMethods.JUST_GREEN_TRANSFORM, rightImagePanel, inputPathField); // Perform the just-green transform
            }
        }

        class JustTheBluesHandler implements ActionListener { // Create an ActionListener named JustTheBluesHandler
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                performTransform(EditorHelperMethods.JUST_BLUE_TRANSFORM, rightImagePanel, inputPathField); // Perform the just-blue transform
            }
        }

        grayscaleButton.addActionListener(new GrayscaleHandler()); // Give the grayscaleButton the GrayscaleHandler
        flipHorizontalButton.addActionListener(new FlipHorizontalHandler()); // Give the flipHorizontalButton the FlipHorizontalHandler
        negateRedButton.addActionListener(new NegateRedHandler()); // Give the negateRedButton the NegateRedHandler
        negateGreenButton.addActionListener(new NegateGreenHandler()); // Give the negateGreenButton the NegateGreenHandler
        negateBlueButton.addActionListener(new NegateBlueHandler()); // Give the negateBlueButton the NegateBlueHandler
        justTheRedsButton.addActionListener(new JustTheRedsHandler()); // Give the justTheRedsButton the JustTheRedsHandler
        justTheGreensButton.addActionListener(new JustTheGreensHandler()); // Give the justTheGreensButton the JustTheGreensHandler
        justTheBluesButton.addActionListener(new JustTheBluesHandler()); // Give the justTheBluesButton the JustTheBluesHandler

        middlePanel.add(grayscaleButton); // Add grayscaleButton to middlePanel
        middlePanel.add(flipHorizontalButton); // Add flipHorizontalButton to middlePanel
        middlePanel.add(negateRedButton); // Add negateRedButton to middlePanel
        middlePanel.add(negateGreenButton); // Add negateGreenButton to middlePanel
        middlePanel.add(negateBlueButton); // Add negateBlueButton to middlePanel
        middlePanel.add(justTheRedsButton); // Add justTheRedsButton to middlePanel
        middlePanel.add(justTheGreensButton); // Add justTheGreensButton to middlePanel
        middlePanel.add(justTheBluesButton); // Add justTheBluesButton to middlePanel

        return middlePanel; // Return middlePanel
    }

    private static JPanel getRightPanel(JPanel rightImagePanel, JTextField inputPathField, JPanel leftImagePanel) { // Create a method named getRightPanel() that takes in a rightImagePanel, an inputPathField, and a leftImagePanel
        // Similar to left panel, but has the save button and an extra button that reuses the current output
        JPanel rightPanel = new JPanel(); // Define middlePanel
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); // Set layout to rightPanel on the y-axis

        // TODO: instantiate the rightImage using our custom JComponent subclass
        DrawnImageComponent rightImage = new DrawnImageComponent(); // Instantiate the rightImage using our custom JComponent subclass

        rightImage.setPreferredSize(IMAGE_DIMENSIONS); // this ensures the image isn't too small to see
        rightImagePanel.add(rightImage); // Add rightImage to rightImagePanel

        JTextField outputPathField = new JTextField(30); // TODO instantiate the JTextField with NO default text and 30 columns
        // TODO: create save and reuse buttons with appropriate text and event listeners. They are defined below
        JButton saveImageButton = new JButton("Save Image"); // Create saveImageButton
        JButton reuseImageButton = new JButton("Reuse Image"); // Create reuseImageButton

        class SaveImageHandler implements ActionListener { // Create an ActionListener named SaveImageHandler
            // Saves the output image to a file, based on what the user wants it to be called
            // Behind the scenes, though, we've already saved it in temp.ppm, so we just duplicate that file with the new name
            // That way temp.ppm isn't overwritten and their image is lost. This is done by the EditorHelperMethods method for you.
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                if (outputPathField.getText().equals("")) { // If outputPathField isn't entered
                    outputPathField.setText("out.ppm"); // default name for saving if one isn't entered
                }
                try { // Try
                    // TODO call the right EditorHelperMethod to save the temporary image to the desired file
                    EditorHelperMethods.saveTempImage("out.ppm"); // Save the temporary image to the desired file

                } catch (IOException ex) { // Catch IOException
                    System.out.println("Error: image '" + outputPathField.getText() + "' could not be found: " + ex.getMessage()); // Throw an error if the image cannot be found
                }
            }
        }

        class ReuseImageHandler implements ActionListener { // Create an ActionListener named ReuseImageHandler
            // This method does:
            // 1. If the output field text isn't a valid file name (i.e. if it doesn't end with .ppm),
            //    puts out.ppm into the input field.
            //    Otherwise, put the output file name into the input field.
            // 2. Saves the temp file with that new name that's now in input field, logging an error if this fails
            // 2. Clears the output path field
            // 3. Gets the output image as a BufferedImage (much like the other methods in this class) logging an error if
            //    this fails, and replaces the left image with it.
            // 4. Replaces the right image with null. This means nothing will be drawn, so the "no image to show" text is visible
            // TODO implement this logic
            public void actionPerformed(ActionEvent e) { // Override the actionPerformed() method
                BufferedImage newImage; // Define a BufferedImage named newImage

                if (!outputPathField.getText().endsWith(".ppm")) { // If the output field text isn't a valid file name (i.e. if it doesn't end with .ppm)
                    inputPathField.setText("out.ppm"); // Put out.ppm into the input field
                }
                else { // Otherwise
                    inputPathField.setText(outputPathField.getText()); // Put the output file name into the input field
                }

                try { // Try
                    EditorHelperMethods.saveTempImage(inputPathField.getText()); // Save the temp file with that new name that's now in input field
                    newImage = EditorHelperMethods.resolveImageFromString(outputPathField.getText()); // Get the output image as a BufferedImage
                    replaceImage(leftImagePanel, newImage); // Replace the left image with it
                } catch (IOException ex) { // Catch IOException
                    System.out.println(ex.getMessage()); // Log an error if this fails
                }

                outputPathField.setText(""); // Clear the output path field
                replaceImage(rightImagePanel, null); // Replace the right image with null
            }
        }
        // TODO: last steps for this method
        saveImageButton.addActionListener(new SaveImageHandler()); // Give the SaveImageHandler to the saveImageButton
        reuseImageButton.addActionListener(new ReuseImageHandler()); // Give the ReuseImageHandler to the reuseImageButton
        rightPanel.add(rightImagePanel); // Add rightImagePanel to rightPanel
        rightPanel.add(outputPathField); // Add outputPathField to rightPanel
        rightPanel.add(saveImageButton); // Add saveImageButton to rightPanel
        rightPanel.add(reuseImageButton); // Add reuseImageButton to rightPanel
        return rightPanel; // Return rightPanel
    }

    private static void replaceImage(JPanel imagePanel, BufferedImage newImage) { // Create a method named replaceImage() that takes in an imagePanel and a newImage
        // This helper method takes in the imagePanel containing the image component to be modified, and the new image to display.
        // It then removes the component inside the corresponding panel, generates the new one based on newImage and adds it, then
        // redraws the panel so the changes apply visually
        imagePanel.remove(0); // Remove the component inside the corresponding panel
        DrawnImageComponent newImageComponent = new DrawnImageComponent(newImage); // Generate the new component based on newImage
        newImageComponent.setPreferredSize(IMAGE_DIMENSIONS); // Set preferred size to newImageComponent
        imagePanel.add(newImageComponent); // Add newImageComponent to imagePanel
        imagePanel.revalidate(); // Redraw imagePanel so the changes apply visually
    }

    private static void performTransform(int transformCode, JPanel rightImagePanel, JTextField inputPathField) { // Create a method named performTransform() that takes a transformCode, a rightImagePanel, and an inputPathField
        // This helper method avoids duplicate code in the actionPerformed method bodies for each event handler.
        // It just needs to know the transform code we want to use, then will call the method in EditorHelperMethods
        // that is used to transform the image with that given transform code and the filename typed into the input field
        // Lastly, replace the right image with that new image.
        BufferedImage newImage; // Define a BufferedImage named newImage
        try { // Try
            newImage = EditorHelperMethods.transformImage(EditorHelperMethods.appendPathPrefix(inputPathField.getText()), transformCode); // Transform the image with the given transform code and the filename typed into the input field
        } catch (IOException ex) { // Catch IOException
            System.out.println("Error: image '" + inputPathField.getText() + "' could not be found: " + ex.getMessage());
            return; // Return nothing
        }
        replaceImage(rightImagePanel, newImage); // Replace the right image with the new image
    }
}