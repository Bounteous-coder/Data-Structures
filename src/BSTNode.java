// Numan Salahuddin - 251264939
class BSTNode {
    private Record record;  // Private field to store record
    private BSTNode leftChild; // Private field to store left child of the tree
    private BSTNode rightChild; // Private field to store right child of the tree
    private BSTNode parent; // Private field to store parent node of current node

    public BSTNode(Record item) {   // Method to initialize a node with a Record
        this.record = item;  // Storing an item in node.
    }

    public Record getRecord() { // Getter Method to get Record of node
        return record;
    }

    public BSTNode getLeftChild() {     // Getter Method to get left child
        return leftChild;
    }

    public BSTNode getRightChild() {    // Getter Method to get right child
        return rightChild;
    }

    public BSTNode getParent() {    // Getter Method to get parent
        return parent;
    }

    public void setRecord(Record d) {   // Setter Method to set Record of node
        this.record = d;
    }

    public void setLeftChild(BSTNode u) {   // Setter Method to set left child
        this.leftChild = u;
    }

    public void setRightChild(BSTNode u) {  // Setter Method to set right child
        this.rightChild = u;
    }

    public void setParent(BSTNode u) {  // Setter Method to set parent
        this.parent = u;
    }

    public boolean isLeaf() {   // Method to check if Node is a leaf node or not
        return (leftChild == null && rightChild == null);
    }
}
