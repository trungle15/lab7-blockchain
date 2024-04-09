import java.nio.ByteBuffer;  
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
  int number;
  int amount;
  Hash prehash;
  long nonce;
  Hash nowhash;

  
  Block(int num, int amount, Hash prevHash){
    this.number = num;
    this.amount = amount;
    this.prehash = prevHash;
  }

  Block(int num, int amount, Hash prevHash, long nonce){
    this.number = num;
    this.amount = amount;
    this.prehash = prevHash;
    this.nonce = nonce;
  }



public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(msg.getBytes());
    
    byte[] hash = md.digest;
    return hash;
} 



  int getAmount(){
    return this.amount;
  }

  long getNonce(){
    return this.nonce;
  }

  Hash getPrevHash(){
    return this.prehash;
  }

  Hash getHash(){
    return this.nowhash;
  }

  String toString(){
    byte[] number = ByteBuffer.allocate(Integer.BYTES).putInt(this.number).array();
    byte[] amount = ByteBuffer.allocate(Integer.BYTES).putInt(this.amount).array();
    byte[] nonce = ByteBuffer.allocate(Integer.BYTES).putLong(this.nonce).array();
  }

}
