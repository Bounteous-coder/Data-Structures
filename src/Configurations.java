// Numan Salahuddin - 251264939
public class Configurations { // Class implementing all methods needed by algorithm computerPlay
    private char[][] board;
    private int boardSize;
    private int lengthToWin;
    private int maxLevels;
    private HashDictionary hashDictionary;

    public Configurations(int boardSize, int lengthToWin, int maxLevels) {  // Constructor parameters: board size, length to win, maximum levels
        this.boardSize = boardSize;
        this.lengthToWin = lengthToWin;
        this.maxLevels = maxLevels;
        this.board = new char[boardSize][boardSize];
        initializeBoard();
        int tableSize = findPrimeBetween(6000, 10000);
        this.hashDictionary = new HashDictionary(tableSize);
    }

    public HashDictionary createDictionary() { // Returns an empty HashDictionary with a prime-sized hash table between 6,000 and 10,000.

        return this.hashDictionary;

    }

    public int repeatedConfiguration(HashDictionary hashTable) { // Checks if the configuration represented by the current state of the board is already stored in the hash table.

        StringBuilder boardStr = new StringBuilder();
        for(int i = 0; i < this.boardSize; ++i) {
            for(int j = 0; j < this.boardSize; ++j) {
                boardStr.append(this.board[i][j]);
            }
        }

        String config = boardStr.toString();
        return hashTable.get(config);
        // If found, returns the associated score otherwise, returns -1.
    }

    public void addConfiguration(HashDictionary hashDictionary, int score) {  // Adds the current configuration of the game board along with the provided score to the hash table.
        StringBuilder boardStr = new StringBuilder();

        for(int i = 0; i < this.boardSize; ++i) {
            for(int j = 0; j < this.boardSize; ++j) {
                boardStr.append(this.board[i][j]); // iterates over each cell of the board
            }
        }

        String config = boardStr.toString();
        Data data = new Data(config, score); // creates a Data object with this string configuration and the given score.
        hashDictionary.put(data); // puts this data into the provided HashDictionary.
    }

    public void savePlay(int row, int col, char symbol) {
        this.board[row][col] = symbol;
    }

    public boolean squareIsEmpty(int row, int col) {
        return this.board[row][col] == ' ';
    }

    public boolean wins(char symbol) { // Win Checker
        // Checks horizontally, vertically, diagonally for win.
        return false;
    }

    public boolean isDraw() {
        for(int i = 0; i < this.boardSize; ++i) {
            for(int j = 0; j < this.boardSize; ++j) {
                if (this.board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public int evalBoard() { // Evaluates board.
        // Return values: 3 - computer wins, 0 - human wins, 2 - draw, 1 - undecided
        return -1; // Placeholder
    }

    private void initializeBoard() {  // Initializes board size.
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    private int findPrimeBetween(int start, int end) {  // Finds prime number to check for collisions.
        for (int num = start; num <= end; ++num) {
            if (isPrime(num)) {
                return num;
            }
        }
        return end;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        } else if (n <= 3) {
            return true;
        } else if (n % 2 != 0 && n % 3 != 0) {
            for (int i = 5; i * i <= n; i += 6) {
                if (n % i == 0 || n % (i + 2) == 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
