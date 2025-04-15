package main.java;

/***************************************************************
 * file: Block.java
 * author: Jeffrey Rodas, Jahdon Faulcon, Logan Bailey
 * class: CS 4450
 *
 * assignment: Checkpoint 2
 * date last modified: 4/14/2025
 *
 * purpose: This code creates the class for a block. It will allow the easy 
 * generation of blocks without having to write it out every we want to 
 * initialize one.
 ****************************************************************/ 

public class Block {
    private boolean IsActive;
    private BlockType Type;
    private float x,y,z;
    
    public enum BlockType {
        BlockType_Grass(0),
        BlockType_Sand(1),
        BlockType_Water(2),
        BlockType_Dirt(3),
        BlockType_Stone(4),
        BlockType_Bedrock(5),
        BlockType_Default(6);
        
        private int BlockID;
        
        BlockType(int i) {
            BlockID=i;
        }
        
        public int GetID() {
            return BlockID;
        }
        
        public void SetID(int i) {
            BlockID = i;
        }
    }
    
    public Block(BlockType type) {
        Type = type;
    }
    
    public void setCoords(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public boolean IsActive() {
        return IsActive;
    }
    
    public void SetActive(boolean active) {
        IsActive = active;
    }
    
    public int GetID() {
        return Type.GetID();
    }
}
