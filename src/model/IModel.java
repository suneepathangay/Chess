package src.model;

import java.util.HashMap;
import java.util.List;

/**
 * Interface to represent the model of the game
 */
public interface IModel extends ReadOnlyChess {

  /**
   * Method to start the game.
   */
  void startGame();

  /**
   * method to move a piece to the requested coordinate.
   * @param coordinate the coordiante to move to
   */
  void placePiece(Coordinate coordinate,Coordinate orgPos);

  /**
   * Determines if the game is over.
   * @return a boolean if the game is over
   */
  boolean isGameOver();

}
