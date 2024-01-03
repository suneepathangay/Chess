package src.controller;

import src.model.Coordinate;
import src.model.pieces.Piece;

public interface Features {

  /**
   * Method to move a piece from a coordinate to a destination coordinate
   * @param destCoor the destination coordinate for the piece
   */

  void movePiece(Coordinate orgCoor, Coordinate destCoor);

  /**
   * Method to set the current player
   * @param player the new clicked player
   */
  void setCurrPlayer(Coordinate player);
}
