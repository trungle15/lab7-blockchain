import java.util.Arrays;

public class Hash {
  byte[] hash;

  public Hash(byte[] data){
    this.hash = data;
  }

  public byte[] getData(){
    return this.hash;
  }

  public boolean isValid(){
    return hash.length >= 3 && hash[0] == 0 && hash[1] == 0 && hash[2] == 0;
  }

  public String toString(){
    String ret = new String();

    for (byte r : hash) {
     ret = String.format(ret, Byte.toUnsignedInt(r));
    }

    return ret;
  }

  public boolean equals(Object other){
    if (other instanceof Hash){
      Hash o = (Hash) other;
      return Arrays.equals(this.hash, o.getData());
    };
    return false;
  }
}
