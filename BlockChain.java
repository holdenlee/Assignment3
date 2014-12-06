import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* Block Chain should maintain only limited block nodes to satisfy the functions
   You should not have the all the blocks added to the block chain in memory 
   as it would overflow memory
 */

public class BlockChain {
   public static final int CUT_OFF_AGE = 10;
   
   private HashMap<Integer, HashSet<BlockNode>> nodesAtHeight;
   private int maxHeight;

   // all information required in handling a block in block chain
   private class BlockNode {
      public Block b;
      public int parentHash;
      public ArrayList<BlockNode> children;
      public int height;
      // utxo pool for making a new block on top of this block
      private UTXOPool uPool;

      public BlockNode(Block b, BlockNode parent, UTXOPool uPool) {
         this.b = b;
         children = new ArrayList<BlockNode>();
         this.uPool = uPool;
         if (parent != null) {
        	this.parentHash = parent.hashCode();
            height = parent.height + 1;
            parent.children.add(this);
         } else {
        	this.parentHash = 0;
            height = 1;
         }
      }

      public UTXOPool getUTXOPoolCopy() {
         return new UTXOPool(uPool);
      }
   }

   /* create an empty block chain with just a genesis block.
    * Assume genesis block is a valid block
    */
   public BlockChain(Block genesisBlock) {
	  ArrayList<Transaction> txs = genesisBlock.getTransactions();
	  UTXOPool uPool = new UTXOPool();
	  
	  int index = 0;
	  
	  for(Transaction tx : txs) {
		  index = 0;
		  for(Transaction.Output output : tx.getOutputs()) {
			  uPool.addUTXO(new UTXO(tx.getHash(), index), output);
			  index++;
		  }
	  }
	  
      BlockNode genBlockNode = new BlockNode(genesisBlock, null, uPool);
      
      this.nodesAtHeight = new HashMap<Integer, HashSet<BlockNode>>();
      this.nodesAtHeight.put(0, new HashSet<BlockNode>());
      this.nodesAtHeight.get(0).add(genBlockNode);
      this.maxHeight = 0;
   }

   /* Get the maximum height block
    */
   public Block getMaxHeightBlock() {
      for(BlockNode bn : this.nodesAtHeight.get(this.maxHeight)) {
    	  return bn.b;
      }
      return null;
   }
   
   /* Get the UTXOPool for mining a new block on top of 
    * max height block
    */
   public UTXOPool getMaxHeightUTXOPool() {
	   for(BlockNode bn : this.nodesAtHeight.get(this.maxHeight)) {
	    	  return bn.getUTXOPoolCopy();
	   }
	   return null;
   }
   
   /* Get the transaction pool to mine a new block
    */
   public TransactionPool getTransactionPool() {
      // IMPLEMENT THIS
   }

   /* Add a block to block chain if it is valid.
    * For validity, all transactions should be valid
    * and block should be at height > (maxHeight - CUT_OFF_AGE).
    * For example, you can try creating a new block over genesis block 
    * (block height 2) if blockChain height is <= CUT_OFF_AGE + 1. 
    * As soon as height > CUT_OFF_AGE + 1, you cannot create a new block at height 2.
    * Return true of block is successfully added
    */
   public boolean addBlock(Block b) {
	   
       // IMPLEMENT THIS
   }

   /* Add a transaction in transaction pool
    */
   public void addTransaction(Transaction tx) {
      // IMPLEMENT THIS
   }
}
