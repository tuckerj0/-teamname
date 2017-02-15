import org.junit.Test;
import static org.junit.Assert.*;

import copenhagen.Hnefatafl;

public class HnefataflTest {
        
        
        /**
         This tests that the validMove method marks invalid all destinations that are not
         along a straight path from the start space
         */
        @Test
        public void testValidMovesHorzVert() {
                int row = 0;
                int col = 2;
                
                Hnefatafl.main(null);
                char piece = 'w';
                boolean[][] validMoves = Hnefatafl.getValidMoves(piece,row,col);
                int boardLength = validMoves.length;
                
                for(int pieceType = 0; pieceType <=1 ; pieceType++) {
                        for (int i = 0 ; i < boardLength ; i++){
                                for (int j = 0 ; j < boardLength ; j++){
                                        
                                        if(i != row && j != col) {
                                                assertEquals(validMoves[i][j], false);
                                        }
                                }
                        }
                }
                piece = 'k';
        }
        /**
         This tests the validMoves method to make sure a piece
         in the way (same row or column) makes all spaces beyond that piece invalid. To test this, the validMoves array is compared against the pieceLayout array.
         */
        
        @Test
        public void testValidMovesObstacles(){
                
                Hnefatafl.main(null);
                char[][] pieceLayout = Hnefatafl.pieceLayout;
                int boardLength = pieceLayout.length;
                boolean[][] validMoves;
                boolean pieceInWay;
                int row, col;
                char piece = 'k';
                
                // Lets make sure validMoves method returns the correct valid vertical moves from every square on the board
                
                //for (row = 1; row<boardLength-1; row++ ) {
                        //for (col = 1; col<boardLength-1; col++) {
                                row = 1;
                                col = 1;
                                validMoves = Hnefatafl.getValidMoves(piece, row,col);
                                
                                // Check to the South
                                pieceInWay = false;
                                
                                for (int i = row+1; i < boardLength ; i++){
                                        
                                        if ((pieceLayout[i][col] != '0' && pieceLayout[i][col] != 'c')  || pieceInWay == true){
                                                assertEquals(validMoves[i][col], false);
                                        } else {
                                                assertEquals(validMoves[i][col], true);
                                        }
                                        if (pieceLayout[i][col] != '0' && pieceLayout[i][col] != 'c')
                                                pieceInWay = true;
                                }
                                // Check to the North
                                pieceInWay = false;
                                
                                for (int i = row-1; i >= 0 ; i--){
                                        if ((pieceLayout[i][col] != '0' && pieceLayout[i][col] != 'c') || pieceInWay == true){
                                                assertEquals(validMoves[i][col], false);
                                        } else {
                                                assertEquals(validMoves[i][col], true);
                                        }
                                        if (pieceLayout[i][col] != '0' && pieceLayout[i][col] != 'c')
                                                pieceInWay = true;
                                }
                                // Check to the East
                                pieceInWay = false;
                                
                                for (int i = col+1; i < boardLength ; i++){
                                        if ((pieceLayout[i][col] != '0' && pieceLayout[i][col] != 'c') || pieceInWay == true){
                                                assertEquals(validMoves[row][i], false);
                                        }
                                        if (pieceLayout[i][col] != '0' && pieceLayout[i][col] != 'c')
                                                pieceInWay = true;
                                        
                                }
                                // Check to the West
                                pieceInWay = false;
                                
                                for (int i = col-1; i >= 0 ; i--){
                                        if ((pieceLayout[i][col] != '0' && pieceLayout[i][col] != 'c') || pieceInWay == true){
                                                assertEquals(validMoves[row][i], false);
                                        }
                                        if (pieceLayout[i][col] != '0' && pieceLayout[i][col] != 'c')
                                                pieceInWay = true;
                                        
                                }
                                
                        //}
                //}
        }
}
