package src.model.pieces;

import src.model.Color;
import src.model.Coordinate;

/**
 * Interface for a single chess piece.
 */
public interface IPiece {

  /**
   * Moves a piece to the coordinate on the board
   * @param coordinate the proposed coordinate to move
   */
  void move(Coordinate coordinate);

  /**
   * Gets the status of a piece whether is captured or not.
   * @return the status of the piece
   */
  PieceStatus getStatues();

  /**
   * Returns the position of piece on the board.
   * @return the position of the piece
   */
  Coordinate getPosition();


  /**
   * Method to set the status of a piece
   */
  void setStatus(PieceStatus status);

  /**
   * Method to get the color of apiece
   * @return the color of the piece
   */
  Color getColor();

  /**
   * Method to get the name of the piece ex(King is K)
   * @return a String representing the type of piece
   */
  String getName();


}
