import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class BlockChainDriver {
  private static Object minedBlock;
  private static Scanner scanner= new Scanner(System.in);


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
    int amount = scanner.nextInt();  // Read the transaction amount
    scanner.nextLine();  // Consume the newline left-over

    minedBlock = blockChain.mine(amount);  // Mine a new block

    // Print the details of the mined block
    System.out.println("amount = " + amount + ", nonce = " + ((Block) minedBlock).getNonce());
    System.out.println(blockChain.toString());
}
  

  public static void appendBlock(BufferedReader reader, BlockChain blockChain) throws NumberFormatException, IOException, IllegalAccessException {
    System.out.print("Amount transferred? ");
    int amount = scanner.nextInt();  // Confirm the transaction amount

    System.out.print("Nonce? ");
    long nonce = scanner.nextLong();  // Confirm the nonce
    scanner.nextLine();  // Consume the newline left-over
    if (amount == ((Block) minedBlock).getAmount() && nonce == ((Block) minedBlock).getNonce()){
      blockChain.append((Block) minedBlock);  // Append the mined block
      System.out.println(blockChain.toString());
      minedBlock = null;  // Clear the mined block after appending
    }
}
  

  public static void removeLastBlock(BlockChain blockChain) {
    if (blockChain.removeLast()) {
      System.out.println(blockChain.toString());//print out all the blocks
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
