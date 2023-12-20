package src.model.pieces;

import src.model.Coordinate;

public abstract class Piece implements IPiece {

  public boolean checkCoordinates(Coordinate coor1, Coordinate coor2){
    if(coor1.getX()==coor2.getX() && coor1.getY()==coor2.getY()){
      return true;
    }
    return false;
  }

}
