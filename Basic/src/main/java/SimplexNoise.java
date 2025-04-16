package main.java;

/***************************************************************
 * file: SimplexNoise.java
 * author: Jeffrey Rodas, Jahdon Faulcon, Logan Bailey
 * class: CS 4450
 *
 * assignment: Checkpoint 2
 * date last modified: 4/14/2025
 *
 * purpose: This code uses the SimplexNoise class to generate our terrain within
 * our Minecraft world so that mountains and valleys look more natural.
 ****************************************************************/ 
import java.util.Random;

public class SimplexNoise {

    SimplexNoise_octave[] octaves; //array stores individual noise layers
    double[] frequencys; //array stores frequency for each octave
    double[] amplitudes; //arrays stores amplitudes for each octave
    int largestFeature; //largest noise feature size
    double persistence; //octave influence decay (0 - 1)
    int seed; // seed for deterministic randomness

    public SimplexNoise(int largestFeature, double persistence, int seed) {//constructor for multi-octave simplex noise
        this.largestFeature = largestFeature;
        this.persistence = persistence;
        this.seed = seed;
//recieves a number (eg 128) and calculates what power of 2 it is (eg 2^7); calculates octaves abased on largest feature
        int numberOfOctaves = (int) Math.ceil(Math.log10(largestFeature) / Math.log10(2));
        octaves = new SimplexNoise_octave[numberOfOctaves];
        frequencys = new double[numberOfOctaves];
        amplitudes = new double[numberOfOctaves];
        Random rnd = new Random(seed);
        for (int i = 0; i < numberOfOctaves; i++) {
            octaves[i] = new SimplexNoise_octave(rnd.nextInt()); //creates octave with unique seed
            frequencys[i] = Math.pow(2, i); //sets frequency for iteration's octave
            amplitudes[i] = Math.pow(persistence, octaves.length - i); //sets amplitude of iteration's octave
        }
    }

    public double getNoise(int x, int y) {
        double result = 0;
        for (int i = 0; i < octaves.length; i++) {
//double frequency = Math.pow(2,i);
//double amplitude = Math.pow(persistence,octaves.length-i);
            result = result + octaves[i].noise(x / frequencys[i], y / frequencys[i])
                    * amplitudes[i];
        }
        return result;
    }

    public double getNoise(int x, int y, int z) { //generates 3d simplex noise by combining multiple octaves
        double result = 0;
        for (int i = 0; i < octaves.length; i++) {
            double frequency = Math.pow(2, i);
            double amplitude = Math.pow(persistence, octaves.length - i);
            result = result + octaves[i].noise(x / frequency, y / frequency, z / frequency)
                    * amplitude;
        }
        return result;
    }
}
