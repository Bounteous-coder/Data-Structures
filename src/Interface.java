// Numan Salahuddin - 251264939
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Interface {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Interface inputFile");
            return;
        }

        String inputFile = args[0];

        BSTDictionary dictionary = new BSTDictionary(); // Creates an instance of BSTDictionary
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");    // Splitting line by whitespace
                String label = parts[0];
                int type = Integer.parseInt(parts[1]);
                StringBuilder dataBuilder = new StringBuilder();    // Combining the remaining parts as data
                for (int i = 2; i < parts.length; i++) {
                    dataBuilder.append(parts[i]);
                    if (i < parts.length - 1) {
                        dataBuilder.append(" ");
                    }
                }
                String data = dataBuilder.toString();
                Key key = new Key(label, type);
                Record record = new Record(key, data);

                try {
                    dictionary.put(record); // Insert the record into the dictionary
                } catch (DictionaryException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }

        System.out.println("Dictionary Contents:");
        printDictionary(dictionary);
    }
    private static void printDictionary(BSTDictionary dictionary) {
        Record current = dictionary.smallest(); // Prints the smallest to largest
        while (current != null) {
            System.out.println(current.getKey().getLabel() + " " + current.getKey().getType() + ": " + current.getDataItem());
            current = dictionary.successor(current.getKey());
        }
    }
}
