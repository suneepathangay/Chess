package src.model.pieces;

import src.model.Coordinate;

import java.util.List;

public abstract class Piece implements IPiece {

  public boolean checkSameCoordinate(Coordinate orgPos, Coordinate newPos) {
    if (orgPos.getX() == newPos.getX()) {
      if (orgPos.getY() == newPos.getY()) {
        return false;
      }
    }
    return true;
  }

  public boolean checkContain(List<Coordinate> validMoves, Coordinate coordinate){
    for(Coordinate move:validMoves){
      if(move.getX()==coordinate.getX() && move.getY()==coordinate.getY()){
        return true;
      }
    }
    return false;
  }

}
