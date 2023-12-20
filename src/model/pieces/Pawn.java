package src.model.pieces;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.Tile;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece implements IPiece {
  //specific rules for pawns

  //on the first move the pawn can move two squares in the vertical direction


  private PieceStatus status;

  private Coordinate position;

  private boolean hasMoved;
  private ChessModel model;

  private List<List<Tile>> board;

  private Color color;


  public Pawn(Color color, Coordinate position, ChessModel model) {
    this.color = color;
    this.position = position;
    this.model = model;
    this.board = model.getBoard();
    this.hasMoved=false;
  }

  @Override
  public void move(Coordinate coordinate) {
    if(!checkSameCoordinate(position,coordinate)){
      throw new IllegalStateException("coordinate is the same");
    }
    List<Coordinate> validMoves=getValidMoves();
    if(checkContain(validMoves,coordinate)){
      if(model.getTileAt(coordinate).getPiece()!=null){
        Piece piece=model.getTileAt(coordinate).getPiece();
        if(piece.getColor()==color){
          throw new IllegalStateException("you cant capture your own piece");
        }
      }
      model.placePiece(coordinate,position);
      this.position=coordinate;
      this.hasMoved=true;
    }else{
      throw new IllegalStateException("move is illegal");
    }
  }

  //get all the valid moves for a pawn

  public boolean checkSameCoordinate(Coordinate orgPos, Coordinate newPos) {
    if (orgPos.getX() == newPos.getX()) {
      if (orgPos.getY() == newPos.getY()) {
        return false;
      }
    }
    return true;
  }

  public boolean checkContain(List<Coordinate> validMoves,Coordinate coordinate){
    for(Coordinate move:validMoves){
      if(move.getX()==coordinate.getX() && move.getY()==coordinate.getY()){
        return true;
      }
    }
    return false;

  }

  private List<Coordinate> getValidMoves(){
    List<Coordinate> directions=getValidDirections();

    List<Coordinate> validMoves=new ArrayList<>();

    for(Coordinate d:directions){
      int newRow=d.getX()+position.getX();
      int newCol=d.getY()+position.getY();
      if(newRow>=0 && newRow<8){
        if(newCol>=0 && newCol<8){
          Coordinate validPos=new Coordinate(newRow,newCol);
          validMoves.add(validPos);
        }
      }

    }
    return validMoves;
  }

  @Override
  public PieceStatus getStatues() {
    return null;
  }

  @Override
  public Coordinate getPosition() {
    return this.position;
  }

  @Override
  public void setStatus(PieceStatus status) {
    this.status = status;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  private List<Coordinate> getValidDirections(){
    List<Coordinate> directions=new ArrayList<>();
    directions.add(new Coordinate(0,1));
    directions.add(new Coordinate(0,-1));
    directions.add(new Coordinate(1,0));
    directions.add(new Coordinate(-1,0));
    directions.add(new Coordinate(1,1));
    directions.add(new Coordinate(1,-1));
    directions.add(new Coordinate(-1,1));
    directions.add(new Coordinate(-1,-1));
    if(!hasMoved){
      directions.add(new Coordinate(-2,0));
      directions.add(new Coordinate(2,0));
    }

    return directions;
  }
}
