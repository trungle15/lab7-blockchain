import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * This Class includes an main so that the arguement can be taken in and various 
 * features for Blockchain such as mine, add, remove, and so on can be called 
 * and if the BlockChain is valid or not can be checked.
 * 
 * @author Trung Le
 * @author Tony Li
 * @author Vivien Yan
 */

public class BlockChainDriver {
  private static Object minedBlock;
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage: java BlockChainDriver <initial_amount>");
      System.exit(1);
    }
    int initialAmount = Integer.parseInt(args[0]);
    BlockChain blockChain = new BlockChain(initialAmount);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      System.out.print("\nCommand? ");
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
    int amount = scanner.nextInt();
    scanner.nextLine();

    minedBlock = blockChain.mine(amount);
    
    System.out.println("amount = " + amount + ", nonce = " + ((Block) minedBlock).getNonce());
    System.out.println();
    System.out.println(blockChain.toString());
  }

  public static void appendBlock(BufferedReader reader, BlockChain blockChain)
      throws NumberFormatException, IOException, IllegalAccessException {
    System.out.print("Amount transferred? ");
    int amount = scanner.nextInt();

    System.out.print("Nonce? ");
    long nonce = scanner.nextLong();
    scanner.nextLine();
    if (amount == ((Block) minedBlock).getAmount() && nonce == ((Block) minedBlock).getNonce()) {
      blockChain.append((Block) minedBlock);
      System.out.println();
      System.out.println(blockChain.toString());
      minedBlock = null;
    }
  }

  public static void removeLastBlock(BlockChain blockChain) {
    if (blockChain.removeLast()) {
      System.out.println();
      System.out.println(blockChain.toString());// print out all the blocks
    } else {
      System.out.println("only one block exists. Cannot remove.");
    }
  }

  private static void checkBlockChain(BlockChain blockChain) {
    if (blockChain.isValidBlockChain()) {
      System.out.println("Chain is valid!");
    } else {
      System.out.println("Chain is invalid.");
    }

    System.out.println();
    System.out.println(blockChain.toString());// print out all the blocks
  }

  private static void reportBalances(BlockChain blockChain) {
    blockChain.printBalances();
    System.out.println();
    System.out.println(blockChain.toString());// print out all the blocks
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
