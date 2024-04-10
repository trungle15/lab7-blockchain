import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BlockChainDriver {
  public static void main(String[] args)throws Exception {
    if (args.length != 1) {
      System.err.println("Usage: java BlockChainDriver <initial_amount>");
      System.exit(1);
  }

    int initialAmount = Integer.parseInt(args[0]);
    BlockChain blockChain = new BlockChain(initialAmount);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while(true){
      System.out.println("\nCommand?");
      try {
        String command = reader.readLine().trim().toLowerCase();

        switch (command) {
            case "mine":
                mineBlock(reader, blockChain);
                break;
            case "append":
                appendBlock(reader, blockChain);
                break;
            case "remove":
                removeLastBlock(blockChain);
                break;
            case "check":
                checkBlockChain(blockChain);
                break;
            case "report":
                reportBalances(blockChain);
                break;
            case "help":
                printHelp();
                break;
            case "quit":
                System.exit(0);
            default:
                System.out.println("Invalid command. Type 'help' instruction.");
                break;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
  }

  public static void mineBlock(BufferedReader reader, BlockChain blockChain) throws NumberFormatException, IOException {
    System.out.print("Amount transferred? ");
    int amount = Integer.parseInt(reader.readLine().trim());
  }

  public static void appendBlock(BufferedReader reader, BlockChain blockChain) throws NumberFormatException, IOException {
    System.out.print("Amount transferred? ");
    int amount = Integer.parseInt(reader.readLine().trim());
    System.out.print("Nonce? ");
    long nonce = Long.parseLong(reader.readLine().trim());
  }

  public static void removeLastBlock(BlockChain blockChain) {
    if (blockChain.removeLast()) {
      
  } else {
      System.out.println("only one block exists. Cannot remove.");
    }
  }

  public static void checkBlockChain(BlockChain blockChain) {

  }

  public static void reportBalances(BlockChain blockChain) {

  }

  
  public static void printHelp() {
    System.out.println("Valid commands:\n" +
            "    mine: discovers the nonce for a given transaction\n" +
            "    append: appends a new block onto the end of the chain\n" +
            "    remove: removes the last block from the end of the chain\n" +
            "    check: checks that the block chain is valid\n" +
            "    report: reports the balances of Alexis and Blake\n" +
            "    help: prints this list of commands\n" +
            "    quit: quits the program");
}
}
