package HashTable;
import java.util.*;
public class ValidSudoku {
    //https://www.youtube.com/watch?v=7gIlJKVK4Ok
    //https://www.youtube.com/watch?v=TjFXEUCMqI8
    //https://www.youtube.com/watch?v=Pl7mMcBm2b8&t=558s
    public boolean isValidSudoku2(char[][] board) {

        for(int i=0; i<9; i++){
            Set<Character> rowMap = new HashSet<>();
            Set<Character> colMap = new HashSet<>();
            Set<Character> boxMap = new HashSet<>();

            for(int j = 0; j<9; j++){

                char cRow = board[i][j];
                char cCol = board[j][i];

                //Getting the board number 3*j + i;


                if(cRow != '.' && !rowMap.add(cRow)){
                    return false;
                }

                if(cCol != '.' && !colMap.add(cCol)){
                    return false;
                }

                int rowIndex = 3*(i/3);
                int colIndex = 3*(i%3);

                char boxChar = board[rowIndex + j/3][colIndex + j%3];

                if(boxChar != '.' && !boxMap.add(boxChar)) {
                    return false;
                }

            }


        }

        return true;

    }
    //https://www.youtube.com/watch?v=Pl7mMcBm2b8&t=558s
    public boolean isValidSudoku(char[][] board) {

        HashSet<String> seen = new HashSet<>();

        for(int i=0; i<9; i++){

            for(int j = 0; j<9; j++){

                char currentVal = board[i][j];

                if(currentVal != '.'){
                    if(!seen.add("row "+i +" value"+ currentVal)||
                            !seen.add("col "+j+ " value "+currentVal) ||
                            !seen.add("box "+ i/3+ " - " +j/3+" value" +currentVal)){
                        return false;
                    }
                }

            }


        }

        return true;

    }
}
