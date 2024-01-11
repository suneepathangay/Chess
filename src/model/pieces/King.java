package src.model.pieces;

import src.model.Color;
import src.model.Coordinate;
import src.model.IModel;
import src.model.Tile;
import src.model.pieces.IPiece;
import src.model.pieces.Piece;
import src.model.pieces.PieceStatus;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece implements IPiece{

  private Color color;
  private Coordinate position;

  private IModel model;

  private boolean hasMoved;

  private PieceStatus status;

  private List<List<Tile>> board;

  public King(Color color,Coordinate position,IModel model){
    this.hasMoved=false;
    this.color=color;
    this.position=position;
    this.model=model;
    this.board=model.getBoard();
  }

  @Override
  public void move(Coordinate coordinate) {
    if(coordinate.equals(position)){
      throw new IllegalStateException("");
    }
    List<Coordinate> validMoves=getValidMoves();
    if(checkContain(validMoves,coordinate)){
      if(model.getTileAt(coordinate).getPiece()!=null){
        Piece piece=model.getTileAt(coordinate).getPiece();
        if(piece.getColor()==this.color){
          throw new IllegalStateException("cannot capture your own piece");
        }
      }
      model.placePiece(coordinate,position);
      this.position=coordinate;
    }else{
      throw new IllegalStateException("This move is illegal");
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
    if(this.color==Color.WHITE){
      return "WK";
    }
    return "BK";
  }

  @Override
  public boolean isValidMove(Coordinate dest) {
    return false;
  }

  private List<Coordinate> getValidMoves(){
    List<Coordinate> directions=getValidDirections();
    List<Coordinate> validMoves=new ArrayList<>();

    for(Coordinate d:directions){
      int newX=position.getX()+d.getX();
      int newY=position.getY()+d.getY();
      //checking if there is a piece there
      if(newX>=0 && newX<8){
        if(newY>=0 && newY<8){
          //validMoves.add(new Coordinate(newX,newY));
          Coordinate newPosition=new Coordinate(newX,newY);
          //check if going to the new postion puts our king in harms way

        }
      }

    }


    return validMoves;
  }

  public boolean isInCheckMate(){
    List<Coordinate> moves=getValidMoves();
    return false;
  }

  private List<Coordinate> getValidDirections(){
    List<Coordinate> directions=new ArrayList<>();
    directions.add(new Coordinate(1,0)); //going down
    directions.add(new Coordinate(-1,0)); //going up
    directions.add(new Coordinate(0,1));//going right
    directions.add(new Coordinate(0,-1));//going left
    directions.add(new Coordinate(1,1));//going bottom right
    directions.add(new Coordinate(-1,1));//going top right
    directions.add(new Coordinate(1,-1));//going bottom left
    directions.add(new Coordinate(-1,-1));//going top left

    return directions;
  }

  public boolean isInCheck(){
    return false;
  }

  private Color getOppColor(){
    if(color==Color.BLACK){
      return Color.WHITE;
    }
    return Color.BLACK;
  }
}
