# Mini-Project 7: Blockchains

Author: Tony Li, Trung Le, Vivien Yan.

## Overview: 

Practice working with linked structures by developing our simple implementation of blockchains.

## Feature: 
- `Hash` class is a wrapper class that wraps up a byte array along with some operations we would like to perform on it. 
- `Block` class contained each node of the blockchain.
- `BlockChain` is a class of linked structure consisit of all block.
- `BlockChaindriver` allows us to interact with our BlockChain. 

The `main` method creates a blockchain with the initial dollar amount and then repeatedly:

1. Prints out the contents of the blockchain.
2. Reads in a command from the user.
3. Executes that command, potentially updating the blockchain and reporting back to the user.

valid command include:
- `mine`: discovers the nonce for a given transaction
- `append`: appends a new block onto the end of the chain
- `remove`: removes the last block from the end of the chain
- `check`: checks that the block chain is valid
- `report`: reports the balances of Alexis and Blake
- `help`: prints this list of commands
- `quit`: quits the program

Acknowlegment: lab on doubliy linked list, eboards, Danny Wen.
