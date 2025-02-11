package conwaygame;
import java.util.ArrayList;

import edu.princeton.cs.algs4.QuickUnionUF;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {
       StdIn.setFile(file);
        // WRITE YOUR CODE HERE
       int r = StdIn.readInt();
       int c = StdIn.readInt();
       grid = new boolean[r][c];
       for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                grid[i][j] = StdIn.readBoolean();
        }
       }

    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        // WRITE YOUR CODE HERE
        //boolean[][] returnedValue = getGrid();
        return getGrid()[row][col];// update this line, provided so that code compiles
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        // WRITE YOUR CODE HERE
        boolean returned = DEAD;
        for(int i = 0; i < getGrid().length; i++){
            for(int j = 0; j < getGrid()[0].length; j++){
                if(getGrid()[i][j] == ALIVE){
                    returned = ALIVE;
                }

            }
        }
        return returned; // update this line, provided so that code compiles
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {
        int returnedInt = 0;

        //CELLS TOP LEFT  DIAGONAL NEIGHBOR
        if(row-1 == -1 && col-1 == -1){
            if(getGrid()[getGrid().length-1][getGrid()[0].length-1] == ALIVE){
                returnedInt++;
            }
        }else{
            if(col-1 == -1){
                if(getGrid()[row-1][getGrid()[0].length-1] == ALIVE){
                    returnedInt++;
                }
            }else{
                if(row-1==-1){
                    if(getGrid()[getGrid().length-1][col-1] == ALIVE){
                        returnedInt++;
                    }
                }else{
                if(getGrid()[row-1][col-1] == ALIVE){
                    returnedInt++;
                }
                }
            }

        }

        //CELLS LEFT HORIZONTAL NEIGHBOR
        if(col-1 == -1){
            if(getGrid()[row][getGrid()[0].length - 1] == ALIVE){
                returnedInt++;
            }
        }else{
            if(getGrid()[row][col-1] == ALIVE){
                returnedInt++;
            }
        }

        //CELLS BOTTOM LEFT DIAGONAL NEIGHBOR
        if(row == getGrid().length-1 && col-1 == -1){
            if(getGrid()[0][getGrid()[0].length-1]){
                returnedInt++;
            }
        }else{
            if(col-1 == -1){
                if(getGrid()[row+1][getGrid()[0].length - 1] == ALIVE){
                    returnedInt++;
                }
            }else{
                if(row == getGrid().length-1){
                    if(getGrid()[0][col-1] == ALIVE){
                        returnedInt++;
                    }
                }else{
                if(getGrid()[row+1][col-1] == ALIVE){
                    returnedInt++;
                }
                }
            }
        }

        //CELLS BOTTOM VERTICAL NEIGHBOR
        if(row == getGrid().length-1){
            if(getGrid()[0][col] == ALIVE){
                returnedInt++;
            }
        }else{
            if(getGrid()[row+1][col] == ALIVE){
                returnedInt++;
            }
        }

        //CELLS BOTTOM RIGHT DIAGONAL NEIGHBOR
        if(row == (getGrid().length-1) && col == (getGrid()[0].length-1)){
            if(getGrid()[0][0] == ALIVE){
                returnedInt++;
            }
        }else{
            if(col == (getGrid()[0].length-1)){
                if(getGrid()[row+1][0] == ALIVE){
                    returnedInt++;
                }
            }else{
                if(row == getGrid().length - 1){
                    if(getGrid()[0][col+1] == ALIVE){
                        returnedInt++;
                    }
                }else{
                    if(getGrid()[row+1][col+1] == ALIVE){
                        returnedInt++;
                    }
                }
            }
        }

        //CELLS RIGHT HORIZONTAL NEIGHBOR
        if(col == getGrid()[0].length-1){
            if(getGrid()[row][0] == ALIVE){
                returnedInt++;

            }
        }else{
            if(getGrid()[row][col+1]){
                returnedInt++;
            }
        }

        //CELLS TOP RIGHT DIAGONAL NEIGHBOR
        if(row == 0 && col == getGrid()[0].length-1){
            if(getGrid()[getGrid().length-1][0] == ALIVE){
                returnedInt++;
            }
        }else{
            if(col == ((getGrid()[0].length)-1)){
                if(getGrid()[row-1][0] == ALIVE){
                    returnedInt++;
                }
            }else{
                if(row == 0){
                    if(getGrid()[getGrid().length-1][col+1] == ALIVE){
                        returnedInt++;
                    }
                }else{
                if(getGrid()[row-1][col+1] == ALIVE){
                    returnedInt++;
                }
                }
            }
        }

        //CELLS TOP VERTICAL NEIGHBOR
        if(row == 0){
            if(getGrid()[getGrid().length-1][col] == ALIVE){
                returnedInt++;
            }
        }else{
            if(getGrid()[row-1][col] == ALIVE){
                returnedInt++;
            }
        }

        return returnedInt;

        }

        
        
    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {
        boolean[][] newGrid = new boolean[getGrid().length][getGrid()[0].length];
        for(int i = 0; i < getGrid().length; i++){
            for(int j = 0; j < getGrid()[0].length; j++){
                newGrid[i][j] = getGrid()[i][j];
            }
        }
        // WRITE YOUR CODE HERE
        for(int i = 0; i < newGrid.length; i++){
            for(int j = 0; j < newGrid[0].length; j++){
                int numAliveCells = numOfAliveNeighbors(i, j);
                
                if(getGrid()[i][j] == ALIVE && (numAliveCells == 0 || numAliveCells == 1)){
                    newGrid[i][j] = DEAD;
                }
                
                if(getGrid()[i][j] == DEAD && numAliveCells == 3){
                    newGrid[i][j] = ALIVE;
                }

                if(getGrid()[i][j] == ALIVE && ((numAliveCells == 2)) || (numAliveCells == 3)) {
                    newGrid[i][j] = ALIVE;
                }
                if(getGrid()[i][j] == ALIVE && (numAliveCells >= 4)){
                    newGrid[i][j] = DEAD;
                }
                
            }
        }

        return newGrid;// update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        // WRITE YOUR CODE HERE
        grid = computeNewGrid();
        totalAliveCells = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == ALIVE){
                    totalAliveCells ++;
                }
            }
        }
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {
        for(int i = 0; i < n; i++){
            nextGeneration();
        }

        // WRITE YOUR CODE HERE
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {
        ArrayList<Integer> allCommunities = new ArrayList<Integer>();
        int indCommunities = 0;
        // WRITE YOUR CODE HE
        WeightedQuickUnionUF a = new WeightedQuickUnionUF(getGrid().length, getGrid()[0].length);
        for(int i = 0; i < getGrid().length; i++){
            for(int j = 0; j < getGrid()[0].length; j++){
                    if(getGrid()[i][j] == ALIVE && numOfAliveNeighbors(i, j) == 0){
                        indCommunities++;
                    }
                    //if(i <= getGrid().length && j <= getGrid()[0].length){
                    if(getGrid()[i][j] == ALIVE){
                    if(i-1 == -1 && j-1 == -1){
                        if(getGrid()[getGrid().length-1][getGrid()[0].length-1] == ALIVE){
                            a.union(i, j, getGrid().length-1, getGrid()[0].length);
                        }
                    }else{
                        if(j-1 == -1){
                            if(getGrid()[i-1][getGrid()[0].length-1] == ALIVE){
                                a.union(i, j, i-1, getGrid()[0].length-1);
                            }
                        }else{
                            if(i-1==-1){
                                if(getGrid()[getGrid().length-1][j-1] == ALIVE){
                                    a.union(i, j, getGrid().length-1, j-1);
                                }
                            }else{
                            if(getGrid()[i-1][j-1] == ALIVE){
                                a.union(i, j, i-1, j-1);
                            }
                            }
                        }
            
                    }
            
                    //CELLS LEFT HORIZONTAL NEIGHBOR
                    if(j-1 == -1){
                        if(getGrid()[i][getGrid()[0].length - 1] == ALIVE){
                            a.union(i, j, i, getGrid()[0].length-1);
                        }
                    }else{
                        if(getGrid()[i][j-1] == ALIVE){
                            a.union(i, j, i, j-1);
                        }
                    }
            
                    //CELLS BOTTOM LEFT DIAGONAL NEIGHBOR
                    if(i == getGrid().length-1 && j-1 == -1){
                        if(getGrid()[0][getGrid()[0].length-1]){
                            a.union(i, j, 0 , getGrid()[0].length-1);
                        }
                    }else{
                        if(j-1 == -1){
                            if(getGrid()[i+1][getGrid()[0].length - 1] == ALIVE){
                                a.union(i, j, i+1, getGrid()[0].length-1);
                            }
                        }else{
                            if(i == getGrid().length-1){
                                if(getGrid()[0][j-1] == ALIVE){
                                    a.union(i, j, 0, j-1);
                                }
                            }else{
                            if(getGrid()[i+1][j-1] == ALIVE){
                                a.union(i, j, i+1, j-1);
                            }
                            }
                        }
                    }
            
                    //CELLS BOTTOM VERTICAL NEIGHBOR
                    if(i == getGrid().length-1){
                        if(getGrid()[0][j] == ALIVE){
                            a.union(i, j, 0, j);
                        }
                    }else{
                        if(getGrid()[i+1][j] == ALIVE){
                            a.union(i, j, i+1, j);
                        }
                    }
            
                    //CELLS BOTTOM RIGHT DIAGONAL NEIGHBOR
                    if(i == (getGrid().length-1) && j == (getGrid()[0].length-1)){
                        if(getGrid()[0][0] == ALIVE){
                            a.union(i, j, 0, 0);
                        }
                    }else{
                        if(j == (getGrid()[0].length-1)){
                            if(getGrid()[i+1][0] == ALIVE){
                                a.union(i, j, i+1, 0);
                            }
                        }else{
                            if(i == getGrid().length - 1){
                                if(getGrid()[0][j+1] == ALIVE){
                                    a.union(i, j, 0, j+1);
                                }
                            }else{
                                if(getGrid()[i+1][j+1] == ALIVE){
                                    a.union(i, j, i+1, j+1);
                                }
                            }
                        }
                    }
            
                    //CELLS RIGHT HORIZONTAL NEIGHBOR
                    if(j == getGrid()[0].length-1){
                        if(getGrid()[i][0] == ALIVE){
                            a.union(i, j, i, 0);
            
                        }
                    }else{
                        if(getGrid()[i][j+1]){
                            a.union(i,j,i,j+1);
                        }
                    }
            
                    //CELLS TOP RIGHT DIAGONAL NEIGHBOR
                    if(i == 0 && j == getGrid()[0].length-1){
                        if(getGrid()[getGrid().length-1][0] == ALIVE){
                            a.union(i, j, getGrid().length-1, 0);
                        }
                    }else{
                        if(j == ((getGrid()[0].length)-1)){
                            if(getGrid()[i-1][0] == ALIVE){
                                a.union(i, j, i-1, 0);
                            }
                        }else{
                            if(i == 0){
                                if(getGrid()[getGrid().length-1][j+1] == ALIVE){
                                    a.union(i, j, getGrid().length-1, j+1);
                                }
                            }else{
                            if(getGrid()[i-1][j+1] == ALIVE){
                                a.union(i, j, i-1, j+1);
                            }
                            }
                        }
                    }
            
                    //CELLS TOP VERTICAL NEIGHBOR
                    if(i == 0){
                        if(getGrid()[getGrid().length-1][j] == ALIVE){
                            a.union(i, j, getGrid().length-1, j);
                        }
                    }else{
                        if(getGrid()[i-1][j] == ALIVE){
                            a.union(i, j, i-1, j);
                        }
                    }
                }
                }
            }
                
            for(int k = 0; k < getGrid().length; k++){
                for(int l = 0; l < getGrid()[0].length; l++){
                    if(getGrid()[k][l] == ALIVE){
                        int currentParent = a.find(k,l);
                        //System.out.println(currentParent);
                        if(allCommunities.contains(currentParent) == false){
                            allCommunities.add(currentParent);
                        }
                    }
                }

            }
                
                
            
        //}
        
       return allCommunities.size() +  indCommunities;
        //fo(int i )
        ///return numOfCommunities; // update this line, provided so that code compiles
    
}
}
