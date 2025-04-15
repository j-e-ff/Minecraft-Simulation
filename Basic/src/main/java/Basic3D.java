package main.java;

/***************************************************************
* file: Basic3D.java
* author: Jeffrey Rodas, Jahdon Faulcon, Logan Bailey
* class: CS 4450 Computer Graphics
*
* assignment: Checkpoint 1
* date last modified: 3/27/2025
*
* purpose: This program initializes a 3D window with a first person camera. 
* It initializes all processes and calls for other classes to run.
*
****************************************************************/

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

public class Basic3D {

    private FPCameraController camera;
    private DisplayMode displayMode;

    public Basic3D() {
    }

    // Method: start
    // Purpose: This method controls the initialization of the OpenGL window and begins the game loop
    public void start() {
        try {
            
            // Set the display mode and create the window
            displayMode = new DisplayMode(800, 600);
            Display.setDisplayMode(displayMode);
            Display.create(); // OpenGL context is created here
            initGL(); // Set up OpenGL settings, etc.

            // Initialize camera
            camera = new FPCameraController(0, 0, 0);
            camera.init();

            // Game loop
            while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                camera.render();
                Display.update();
                Display.sync(60);
            }
            Display.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method: initGL
    // Purpose: This method sets the background color of the window and sets it as a 3D space
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, (float) displayMode.getWidth() / (float) displayMode.getHeight(), 0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glEnable(GL_TEXTURE_2D);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
    }

    // Method: main
    // Purpose: This method starts the program
    public static void main(String[] args) {
        new Basic3D().start();
    }
}
