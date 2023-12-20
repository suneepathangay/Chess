package src.model;

import java.util.HashMap;
import java.util.List;

/**
 * Read only model version for chess.
 */
public interface ReadOnlyChess {

  /**
   * Method to get the current state of the board
   * @return a 2d array representing the board
   */
  List<List<Tile>> getBoard();

  /**
   * Method to get the scores of the game
   * @return a map of the scores of the game
   */
  HashMap<Color,Integer> getScore();

  /**
   * Returns the winner for a particular game.
   * @returns the winner of a game.
   */
  Color getWinner();

  /**
   * Method to get the tile at a certain position
   * @param coordinate the position requested
   * @return the tile at that position
   */
  Tile getTileAt(Coordinate coordinate);
}
