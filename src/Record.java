// Numan Salahuddin - 251264939
class Record {
    private Key key;    // Private field to store Key of Record
    private String data;    // Private field to store data of Record

    public Record(Key k, String theData) {  // key and data of Record
        this.key = k;
        this.data = theData;
    }

    public Key getKey() {   // Getter Method to get the key of Record
        return key;
    }

    // Getter Method to get data of Record
    public String getDataItem() {
        return data;
    }
}
