package main.java;
/***************************************************************
 * file: World.java
 * author: Jeffrey Rodas, Jahdon Faulcon, Logan Bailey
 * class: CS 4450
 *
 * assignment: Checkpoint 2
 * date last modified: 4/15/2025
 *
 * purpose: This code manages our Minecraft "world" and allows us to generate 
 * multiple chunks easily without overburdening the computer. 
 ****************************************************************/
public class World {

    private static final int WORLD_SIZE = 5; // 5x5 chunks = 150x150 if chunk size is 30
    private Chunk[][] chunks; // 2d arraystores all chunks in world

    public World() {
        chunks = new Chunk[WORLD_SIZE][WORLD_SIZE];
        for (int x = 0; x < WORLD_SIZE; x++) { // for loop iterates through creation of each chunk's position in world
            for (int z = 0; z < WORLD_SIZE; z++) {
                // Create chunks with proper positioning
                chunks[x][z] = new Chunk(
                        x * Chunk.CHUNK_SIZE * Chunk.CUBE_LENGTH, // x coordinate in world space
                        0, // y coordinate in world space (0 indicates base height)
                        z * Chunk.CHUNK_SIZE * Chunk.CUBE_LENGTH // z coordinate in world space
                );
            }
        }
    }

    public void render() {
        // Render all chunks
        for (int x = 0; x < WORLD_SIZE; x++) { // for loop iterates through and renders each chunk
            for (int z = 0; z < WORLD_SIZE; z++) {
                chunks[x][z].render();
            }
        }
    }

    // Additional methods for updating chunks, etc.
}
