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

    /**
     * Constructor for creating a new block.
     * @param data The data to be stored in the block.
     * @param previousHash The hash of the previous block in the chain.
     */
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    /**
     * Calculates the hash of the block.
     * @return The calculated hash value.
     */
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

    /**
     * Mines the block with a given difficulty.
     * @param difficulty The difficulty level for mining.
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    // Getter for block data
    public String getData() { return data; }
}

class BlockchainARM {
    private List<Block> chain;
    private int difficulty;

    /**
     * Constructor for creating a new blockchain.
     * @param difficulty The difficulty level for mining blocks.
     */
    public BlockchainARM(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        chain.add(createGenesisBlock());
    }

    /**
     * Creates the genesis block for the blockchain.
     * @return The genesis block.
     */
    private Block createGenesisBlock() {
        return new Block("Genesis Block", "0");
    }

    /**
     * Adds a new block to the blockchain.
     * @param data The data to be stored in the new block.
     */
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

    /**
     * Prints the contents of the blockchain.
     */
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
