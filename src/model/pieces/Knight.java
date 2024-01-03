package src.model.pieces;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.Tile;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece implements IPiece {
  //class for the knight
  private PieceStatus status;

  private Coordinate position;

  private boolean hasMoved;
  private ChessModel model;

  private List<List<Tile>> board;

  private Color color;


  public Knight(Color color, Coordinate position, ChessModel model){
    this.color=color;
    this.position=position;
    this.model=model;
    this.board = model.getBoard();
  }

  @Override
  public void move(Coordinate coordinate) {
    System.out.println(this.color);
    if(!checkSameCoordinate(position,coordinate)){
      throw new IllegalStateException("cannot move to the same location");
    }
    List<Coordinate> validPos=this.getValidMoves();
    if(checkContain(validPos,coordinate)){
      Piece piece=this.model.getTileAt(coordinate).getPiece();
      if(piece!=null){
        if(piece.getColor()==this.color){
          throw new IllegalStateException("you cannot capture your own pieces");
        }
      }
      model.placePiece(coordinate,position);
      this.position=coordinate;
    }else{
      throw new IllegalStateException("this move is illegal");
    }



  }

  @Override
  public PieceStatus getStatues() {
    return this.status;
  }

  @Override
  public Coordinate getPosition() {
    return this.position;
  }

  @Override
  public void setStatus(PieceStatus status) {
    this.status=status;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String getName() {
    if(color==Color.BLACK){
      return "BN";
    }
    return "WN";
  }

  @Override
  public boolean isValidMove(Coordinate dest) {
    List<Coordinate> validMoves=getValidMoves();
    if(checkContain(validMoves,dest)){
      return true;
    }
    return false;
  }

  private List<Coordinate> getValidMoves(){
    List<Coordinate> directions=getValidDirections();
    List<Coordinate> validPos=new ArrayList<>();
    for(Coordinate d:directions){
      int newRow=position.getX()+d.getX();
      int newCol=position.getY()+d.getY();

      if(newRow>=0 && newRow<8){
        if(newCol>=0 && newCol<8){
          Coordinate coor=new Coordinate(newRow,newCol);
          validPos.add(coor);
        }
      }

    }
    return validPos;
  }



  private List<Coordinate> getValidDirections(){
    List<Coordinate> directions=new ArrayList<>();
    directions.add(new Coordinate(1,2));
    directions.add(new Coordinate(-1,2));
    directions.add(new Coordinate(-2,1));
    directions.add(new Coordinate(-2,-1));
    directions.add(new Coordinate(1,-2));
    directions.add(new Coordinate(-1,-2));
    directions.add(new Coordinate(2,-1));
    directions.add(new Coordinate(2,1));
    return directions;
  }
}
