public class BlockChain {
  
  Node2 first;
  Node2 last;
  int size;

  BlockChain(int initial){
    this.first = new Node2(new Block(0, initial, null));
    this.last = first;
    this.size = 1;
  }

}
