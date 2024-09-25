// Numan Salahuddin - 251264939
class BinarySearchTree {
    private BSTNode root;   // Private field for the root node of BST.
    public BinarySearchTree() {
        this.root = null; // Initialize an empty BST w/ a leaf node as root
    }

    public BSTNode getRoot() {  // Getter method to get the root node of the binary search tree
        return root;
    }

    public BSTNode get(BSTNode r, Key k) {  // Method to retrieve a node storing the given key
        if (r == null) {
            return null;
        }
        int cmp = k.compareTo(r.getRecord().getKey());
        if (cmp == 0) {
            return r;
        } else if (cmp < 0) {
            return get(r.getLeftChild(), k);   // Searches the left subtree
        } else {
            return get(r.getRightChild(), k);   // Searches the right subtree
        }
    }

    public void insert(BSTNode r, Record d) throws DictionaryException {    // Method to insert a record into the binary search tree
        if (r == null) {
            root = new BSTNode(d);  // If tree is empty, insert at root.
        } else {
            insertNode(r, d);
        }
    }

    private void insertNode(BSTNode r, Record d) throws DictionaryException {
        Key key = d.getKey();
        int cmp = key.compareTo(r.getRecord().getKey());

        if (cmp == 0) {
            throw new DictionaryException("Duplicate key found");
        } else if (cmp < 0) {
            if (r.getLeftChild() == null) {
                r.setLeftChild(new BSTNode(d)); // Insert on the left
            } else {
                insertNode(r.getLeftChild(), d);
            }
        } else {
            if (r.getRightChild() == null) {
                r.setRightChild(new BSTNode(d)); // Insert on the right
            } else {
                insertNode(r.getRightChild(), d);
            }
        }
    }

    public void remove(BSTNode r, Key k) throws DictionaryException {   // Method to remove a node with the given key from the tree
        root = removeNode(r, k);
    }

    private BSTNode removeNode(BSTNode r, Key k) throws DictionaryException {
        if (r == null) {
            throw new DictionaryException("Key not found");
        }

        int cmp = k.compareTo(r.getRecord().getKey());

        if (cmp < 0) {
            r.setLeftChild(removeNode(r.getLeftChild(), k));
        } else if (cmp > 0) {
            r.setRightChild(removeNode(r.getRightChild(), k));
        } else {
            if (r.getLeftChild() == null) { // Node with only one child or no child
                return r.getRightChild();
            } else if (r.getRightChild() == null) {
                return r.getLeftChild();
            }

            r.setRecord(smallest(r.getRightChild()).getRecord());   // Node with two children || smallest in the right subtree
            r.setRightChild(removeNode(r.getRightChild(), r.getRecord().getKey())); // Removes the inorder successor
        }
        return r;
    }

    public BSTNode successor(BSTNode r, Key k) {    // Method to find the successor of the node with the given key
        BSTNode node = get(r, k);

        if (node == null) {
            return null;
        }

        if (node.getRightChild() != null) {
            return smallest(node.getRightChild());
        } else {
            BSTNode parent = node.getParent();
            while (parent != null && node == parent.getRightChild()) {
                node = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }

    public BSTNode predecessor(BSTNode r, Key k) {  // Same process as above. Method to find the predecessor of the node with the given key
        BSTNode node = get(r, k);

        if (node == null) {
            return null;
        }

        if (node.getLeftChild() != null) {
            return largest(node.getLeftChild());
        } else {
            BSTNode parent = node.getParent();
            while (parent != null && node == parent.getLeftChild()) {
                node = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }

    public BSTNode smallest(BSTNode r) { // To find smallest node in the subtree
        if (r == null) {
            return null;
        }

        while (r.getLeftChild() != null) {
            r = r.getLeftChild();
        }

        return r;
    }

    public BSTNode largest(BSTNode r) { // To find the largest node in the subtree
        if (r == null) {
            return null;
        }

        while (r.getRightChild() != null) {
            r = r.getRightChild();
        }

        return r;
    }
}
