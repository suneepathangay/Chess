package src.model.pieces;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.Tile;

import java.util.ArrayList;
import java.util.List;

//rules for a rook

//can only move left right and down

//when the piece moves there cannot be any other piece in its way

public class Rook extends Piece implements IPiece{

  private PieceStatus status;

  private Coordinate position;

  private boolean hasMoved;

  private ChessModel model;

  private List<List<Tile>> board;

  private Color color;

  private String name;

  public Rook(Color color, Coordinate position,ChessModel model){
    this.color=color;
    this.position=position;
    this.model=model;
    this.board=model.getBoard();
  }
  @Override
  public void move(Coordinate coordinate) {
    if(!checkSameCoordinate(position,coordinate)){
      throw new IllegalStateException("cannot move to the same tile");
    }
    //getting all the valid moves
    List<Coordinate> validMoves=getValidMoves();

    if(checkContain(validMoves,coordinate)){
      if(model.getTileAt(coordinate).getPiece()!=null){
        Piece piece=model.getTileAt(coordinate).getPiece();
        if(piece.getColor()==this.color){
          throw new IllegalStateException("you cannot capture your own piece");
        }
      }

      model.placePiece(coordinate,position);
      this.position=coordinate;
    }else{
      throw new IllegalStateException("Move is illegal");
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

  }

  private List<Coordinate> getValidMoves(){

    List<Coordinate> valdiMoves=new ArrayList<>();


    List<Coordinate> directions=getDirections();

    //looping through all the directions
    for(Coordinate d:directions) {
      boolean isPiece=true;
      boolean isValid=true;

      int row = position.getX();
      int col = position.getY();

      while(isValid && isPiece){
        row+=d.getX();
        col+=d.getY();
        if(row<0 || row>=8){
          break;
        }
        if(col<0 || col>=8){
          break;
        }
        //getting the curent tile
        if(model.getTileAt(new Coordinate(row,col)).getPiece()!=null){
          //there is a piece in that tile but we cant hop over it so we cant go further
          //but we can still capture that piece
          System.out.println("coordinates"+row+" "+col);
          valdiMoves.add(new Coordinate(row,col));
          break;
        }else{
          valdiMoves.add(new Coordinate(row,col));
        }
      }
    }

    return valdiMoves;
  }

  private List<Coordinate> getDirections(){
    List<Coordinate> directions=new ArrayList<>();
    directions.add(new Coordinate(1,0));
    directions.add(new Coordinate(-1,0));
    directions.add(new Coordinate(0,1));
    directions.add(new Coordinate(0,-1));
    return directions;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String getName() {
    if(color==Color.BLACK){
      this.name="BR";
      return this.name;
    }
    this.name="WR";
    return this.name;
  }

  @Override
  public boolean isValidMove(Coordinate dest) {
    List<Coordinate> validMoves=getValidMoves();
    if(checkContain(validMoves,dest)){
      return true;
    }
    return false;
  }


}
