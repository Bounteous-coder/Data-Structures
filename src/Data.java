// Numan Salahuddin - 251264939
public class Data { // Data class stores records in HashDictionary
    private String configuration; // Private class configuration
    private int score; // Private class score

    // Constructor
    public Data(String config, int score) {     // A constructor which initializes a new Data object with the specified configuration and score.
        this.configuration = config;
        this.score = score;
    }


    public String getConfiguration() {  // Public class getConfiguration() to retrieve Configurations in Data.
        return configuration;   // configuration that is returned gets stored in this data object.
    }

    public int getScore() {   // Public class getScore() to retrieve Score in Data.
        return score;   // returns the score in this Data.
    }
}
