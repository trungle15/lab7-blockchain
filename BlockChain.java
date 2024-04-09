public class BlockChain {
  
  Node2<Block> first;
  Node2<Block> last;
  int size;

  BlockChain(int initial){
    this.first = new Node2(new Block(0, initial, null));
    this.last = first;
    this.size = 1;
  }
  
  Block mine(int amount){
    Block ret = new Block(size, amount, ((Block) last.value).getHash());
    return ret;
  }

  int getSize(){
    return this.size;
  }

  void append(Block blk) throws IllegalAccessException{
    if (blk.getPrevHash() == null || blk.getPrevHash().equals(((Block) last.value).getHash())){ 
      last.insertAfter(blk);
      size++;
    }
    else{
      throw new IllegalAccessException();
    }
  }

  boolean removeLast(){
    if (size == 1){
      return false;
    } else{
      this.last.remove();
      size--;
      return true;
    }
  }
}
