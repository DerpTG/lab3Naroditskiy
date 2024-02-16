/** Project: Lab3 Database Assignment
 * Purpose Details: Navigate and perform database operations
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 2/7/2024
 * Last Date Changed: 2/14/2024
 * Rev: 1.0

 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        try {
            String input = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    // Getters
    public String getData() { return data; }
}

class BlockchainARM {
    private List<Block> chain;
    private int difficulty;

    public BlockchainARM(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block("Genesis Block", "0");
    }

    public void addBlock(String data) {
        if (chain.isEmpty()) {
            System.err.println("Blockchain is uninitialized, missing genesis block.");
            return;
        }
        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(data, previousBlock.hash);
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    public void printBlockchain() {
        if (chain.isEmpty()) {
            System.out.println("Blockchain is empty.");
            return;
        }
        for (Block block : chain) {
            System.out.println("Block Data: " + block.getData());
            System.out.println("Hash: " + block.hash);
            System.out.println("Previous Hash: " + block.previousHash);
            System.out.println("--------------------------------");
        }
    }

    // Getters and Setters
    public List<Block> getChain() { return new ArrayList<>(this.chain); }
    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }
}
