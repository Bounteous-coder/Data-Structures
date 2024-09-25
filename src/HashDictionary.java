// Numan Salahuddin - 251264939
import java.util.Iterator;
import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {  // This class represents a HashDictionary table implementation of a DictionaryADT.
    private int size;
    private LinkedList<Data>[] table;

    public HashDictionary(int size) {   // initializes the HashDictionary table with the specified size and creates empty linked lists for each slot.
        this.size = size;
        this.table = new LinkedList[size];

        for (int i = 0; i < size; ++i) {
            this.table[i] = new LinkedList<>();
        }
    }

    public int put(Data record) throws DictionaryException {    // put method adds a Data record to the HashDictionary table.
        String config = record.getConfiguration();
        int tableHash = hashFunction(config, size);
        LinkedList<Data> list = table[tableHash];

        for (Data data : list) {
            if (data.getConfiguration().equals(config)) { // Check if the configuration already exists
                throw new DictionaryException();
            }
        }

        list.add(record);
        return list.size() > 1 ? 1 : 0; // Return 1 if collision, else 0
    }

    public void remove(String config) throws DictionaryException {  // remove method removes a record from the HashDictionary table based on its configuration.
        int tableHash = hashFunction(config, size);
        LinkedList<Data> list = table[tableHash];

        Iterator<Data> iterator = list.iterator();
        while (iterator.hasNext()) {
            Data data = iterator.next();
            if (data.getConfiguration().equals(config)) {
                iterator.remove();
                return;
            }
        }

        throw new DictionaryException(); // Configuration not found
    }

    public int get(String config) { // retrieves the score associated with a configuration from the HashDictionary table.
        int tableHash = hashFunction(config, size);
        LinkedList<Data> list = table[tableHash];

        for (Data data : list) {
            if (data.getConfiguration().equals(config)) {
                return data.getScore();
            }
        }

        return -1; // If Configuration not found
    }

    public int numRecords() { // returns the total number of records stored in the hash table.
        int count = 0;
        for (LinkedList<Data> list : table) {
            count += list.size();
        }
        return count;
    }

    private int hashFunction(String key, int tableSize) {
        int tableHash = 0;
        char[] chars = key.toCharArray();
        for (char c : chars) {
            tableHash = (tableHash * 39 + c) % tableSize;   // Helps with Number of Collisions with the help of a prime number.
        }
        return Math.abs(tableHash);
    }
}