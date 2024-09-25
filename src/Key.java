// Numan Salahuddin - 251264939
class Key implements Comparable<Key> {
    private String label; // Private field to store labels
    private int type; // Private field to store type of key

    public Key(String theLabel, int theType) {
        this.label = theLabel.toLowerCase(); // Convert label to lowercase
        this.type = theType; // Set type of key
    }

    public String getLabel() {  // Getter method to get the label of key
        return label;
    }

    public int getType() {  // Getter method to get the type of key
        return type;
    }

    @Override
    public int compareTo(Key k) {   // Method to Compare 2 key objects
        if (this.label.equals(k.getLabel())) {
            return Integer.compare(this.type, k.getType());
        } else {
            return this.label.compareTo(k.getLabel());
        }
    }
}
