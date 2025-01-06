import java.util.Arrays;

public class GameOfLife {
    private int rows;
    private int cols;
    private Cell[][] grid;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(false);
            }
        }
    }

    public void setCellAlive(int row, int col, boolean alive) {
        grid[row][col].setAlive(alive);
    }

    public void nextGeneration() {
        Cell[][] newGrid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newGrid[i][j] = new Cell(grid[i][j].isAlive());
                int aliveNeighbors = countAliveNeighbors(i, j);
                if (grid[i][j].isAlive()) {
                    if (aliveNeighbors < 2 || aliveNeighbors > 3) {
                        newGrid[i][j].setAlive(false);
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        newGrid[i][j].setAlive(true);
                    }
                }
            }
        }
        grid = newGrid;
    }

    private int countAliveNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c].isAlive()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].isAlive() ? "O" : ".");
            }
            System.out.println();
        }
    }
    
    
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife(5, 5);
        game.setCellAlive(1, 2, true);
        game.setCellAlive(2, 2, true);
        game.setCellAlive(3, 2, true);

        System.out.println("Initial Generation:");
        game.printGrid();

        game.nextGeneration();
        System.out.println("Next Generation:");
        game.printGrid();
    }
}
