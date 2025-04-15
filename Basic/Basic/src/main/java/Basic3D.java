package main.java;

/***************************************************************
* file: Basic3D.java
* author: Jeffrey Rodas, Jahdon Faulcon, Logan Bailey
* class: CS 4450 Computer Graphics
*
* assignment: Checkpoint 2
* date last modified: 4/15/2025
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

//Contains all methods and constructors for Basic3D
public class Basic3D {
    
    private FPCameraController fp = new FPCameraController(0, 0, 0);
    private DisplayMode displayMode;

    //method: start
    //purpose: This method controls the initialization of the OpenGL window and begins the game loop
    public void start() {
        try {
            createWindow();
            initGL();
            fp.initializeChunk();
            fp.gameLoop();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    //method: createWindow
    //purpose: This method creates the actual OpenGL window and sets it boundaries
    private void createWindow() throws Exception {
        Display.setFullscreen(false);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        //Uses a for loop to control the width and height of the window
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640 && d[i].getHeight() == 480 && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle("Hey Mom! I am using OpenGL!!!");
        Display.create();
    }

    //method: initGL
    //purpose: This method sets the background color of the window and sets it as a 3D space for the user to navigate
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
        /*
            glEnable(GL_LIGHTING);
            glEnable(GL_LIGHT0);
            float[] lightPosition = {0, 100, 0, 1}; // Position the light above
            glLightfv(GL_LIGHT0, GL_POSITION, lightPosition);
            float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f}; // White light
            glLightfv(GL_LIGHT0, GL_DIFFUSE, lightDiffuse);
        */
    }

    //method: main
    //purpose: This method calls for the program to begin
    public static void main(String[] args) {
        Basic3D basic = new Basic3D();
        basic.start();
    }
}