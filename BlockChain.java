/**
 * This Class is for the structure of a BlockChain, which is a linked list with nodes 
 * at the start and end. Also, the size is been tracking.
 * 
 * Methods like get, check, as well as toString are provided.
 * 
 * @author Trung Le
 * @author Tony Li
 * @author Vivien Yan
 */

public class BlockChain {
  //field
  Node2<Block> first;
  Node2<Block> last;
  int size;

  BlockChain(int initial) {
    this.first = new Node2<Block>(new Block(0, initial, null));
    this.last = first;
    this.size = 1;
  } //constructor

  Block mine(int amount) {
    Block ret = new Block(size, amount, ((Block) last.value).getHash());
    return ret;
  } // method mine

  int getSize() {
    return this.size;
  }//method getsize

  void append(Block blk) throws IllegalAccessException {
    if (blk.getPrevHash() == null || blk.getPrevHash().equals(((Block) last.value).getHash())) {
      last = last.insertAfter(blk);
      size++;
    } else {
      throw new IllegalAccessException();
    }
  }//method append

  boolean removeLast() {
    if (size == 1) {
      return false;
    } else {
      Node2<Block> toDelete = this.last;
      this.last = toDelete.prev;
      toDelete.remove();
      size--;
      return true;
    }
  }//method removelast

  public Hash getHash() {
    return this.last.value.getHash();
  }//method gethash

  public void printBalances() {
    int alexisBalance = this.first.value.getAmount();
    Node2<Block> current = this.first.next;

    while (current != null) {
      alexisBalance += current.value.getAmount();
      current = current.next;
    }

    int blakeBalance = -alexisBalance + this.first.value.getAmount();

    System.out.println("Alexis: " + alexisBalance + ", Blake: " + blakeBalance);
  }//method printbalances

  public boolean isValidBlockChain() {
    int origBalance = this.first.value.getAmount();
    int alexisBalance = origBalance;
    Node2<Block> current = this.first.next;

    while (current != null) {
      Block block = current.value;

      alexisBalance += block.getAmount();

      if (alexisBalance < 0 || alexisBalance > origBalance) {
        return false;
      }

      if (!block.getPrevHash().equals(current.prev.value.getHash())) {
        return false;
      }

      current = current.next;
    }

    return true;
  }// method is valid blockchain

  public String toString() {
    StringBuilder builder = new StringBuilder();
    Node2<Block> current = this.first;
    while (current != null) {
      builder.append(current.value.toString()).append("\n");
      current = current.next;
    }
    return builder.toString();
  }// method tostring

}
