import java.util.Arrays;

public class Hash {
  byte[] hash;

  public Hash(byte[] data){
    this.hash = data;
  }//constructor

  public byte[] getData(){
    return this.hash;
  }//method getdata

  public boolean isValid(){
    return hash.length >= 3 && hash[0] == 0 && hash[1] == 0 && hash[2] == 0;
  }//method isvalid

  @Override
  public String toString() {
      StringBuilder hexString = new StringBuilder();
      for (byte b : hash) {
          String hex = Integer.toHexString(0xff & b);
          if (hex.length() == 1) {
              hexString.append('0');
          }
          hexString.append(hex);
      }
      return hexString.toString();
  }//method tostring

  public boolean equals(Object other){
    if (other instanceof Hash){
      Hash o = (Hash) other;
      return Arrays.equals(this.hash, o.getData());
    };
    return false;
  }//method equals
}
