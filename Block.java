import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
  int number;
  int amount;
  Hash prevHash;
  long nonce;
  Hash hash;

  public Block(int num, int amount, Hash prevHash) {
    this.number = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.hash = calculateHash();
    mineBlock();
  }// constructor

  public Block(int num, int amount, Hash prevHash, long nonce) {
    this.number = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
    this.hash = calculateHash();
  }//constructor

  public void mineBlock() {
    nonce = 0;
    hash = calculateHash();

    while (!hash.isValid()) {
      nonce++;
      hash = calculateHash();
    }
  }//mineblock

  private Hash calculateHash() {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      ByteBuffer buffer = ByteBuffer
          .allocate(Integer.BYTES * 2 + Long.BYTES + (prevHash != null ? prevHash.getData().length : 0));

      buffer.putInt(number).putInt(amount);

      if (prevHash != null) {
        buffer.put(prevHash.getData());
      }

      buffer.putLong(nonce);
      byte[] blockHash = digest.digest(buffer.array());
      return new Hash(blockHash);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Hashing algorithm not found", e);
    }
  }// calculate hash

  public int getNum() {
    return number;
  }//method getnum

  public int getAmount() {
    return amount;
  }//method getamount

  public long getNonce() {
    return nonce;
  }//method getnounce

  public Hash getPrevHash() {
    return prevHash;
  }// method getprevhash

  public Hash getHash() {
    return hash;
  }//method gethash

  @Override
  public String toString() {
    return String.format("Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s)",
        number, amount, nonce,
        prevHash == null ? "null" : prevHash.toString(),
        hash.toString());
  }//method tostring
}
