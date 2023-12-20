package src.model;

import src.model.pieces.Piece;

public class Tile {

  private Coordinate coordinate;

  private Color color;

  private Piece piece;

  public Tile(Coordinate coordinate,Color color){
    this.coordinate=coordinate;
    this.color=color;
  }

  public Coordinate getCoordinate(){
    return this.coordinate;
  }

  public void setPiece(Piece piece){
    this.piece=piece;
  }

  public Piece getPiece(){
    return this.piece;
  }

  public Color getColor(){
    return this.color;
  }
}
