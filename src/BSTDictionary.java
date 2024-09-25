// Numan Salahuddin - 251264939
public class BSTDictionary implements BSTDictionaryADT {

    private BinarySearchTree binarySearchTree;
    public BSTDictionary() {
        this.binarySearchTree = new BinarySearchTree(); // Initializing BSTDictionary with an empty Binary Search Tree
    }

    @Override
    public Record get(Key k) {
        return getHelper(binarySearchTree.getRoot(), k);   // Method to get a record of specific key from the Dictionary
    }

    @Override
    public void put(Record d) throws DictionaryException {  // Method to Insert a Record into the Dictionary
        try {
            binarySearchTree.insert(binarySearchTree.getRoot(), d);
        } catch (DictionaryException e) {
            throw new DictionaryException("Record with the same key already exists.");
        }
    }

    @Override
    public void remove(Key k) throws DictionaryException {  // Method to remove a Record with the given Key from the Dictionary
        try {
            binarySearchTree.remove(binarySearchTree.getRoot(), k);
        } catch (DictionaryException e) {
            throw new DictionaryException("Record with the given key does not exist.");
        }
    }

    @Override
    public Record successor(Key k) {    // To get Record of the successor key of given key from the Dictionary
        BSTNode successorNode = binarySearchTree.successor(binarySearchTree.getRoot(), k);
        return (successorNode != null) ? successorNode.getRecord() : null;
    }

    @Override
    public Record predecessor(Key k) {  // To get Record of the predecessor key of given key from the Dictionary
        BSTNode predecessorNode = binarySearchTree.predecessor(binarySearchTree.getRoot(), k);
        return (predecessorNode != null) ? predecessorNode.getRecord() : null;
    }

    @Override
    public Record smallest() {  // To get Record of the smallest key from the Dictionary
        BSTNode smallestNode = binarySearchTree.smallest(binarySearchTree.getRoot());
        return (smallestNode != null) ? smallestNode.getRecord() : null;
    }

    @Override
    public Record largest() {   // To get Record of the largest key from the Dictionary
        BSTNode largestNode = binarySearchTree.largest(binarySearchTree.getRoot());
        return (largestNode != null) ? largestNode.getRecord() : null;
    }

    private Record getHelper(BSTNode r, Key k) {    // Helper method to recursively search for a key in the binary search tree
        if (r == null) {
            return null; // Key of Record not found
        }

        int cmp = k.compareTo(r.getRecord().getKey());

        if (cmp == 0) {
            return r.getRecord(); // Key of Record found
        } else if (cmp < 0) {
            return getHelper(r.getLeftChild(), k); // Search left Subtree
        } else {
            return getHelper(r.getRightChild(), k); // Search right Subtree
        }
    }
}
