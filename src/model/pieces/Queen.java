package src.model.pieces;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.Tile;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece implements IPiece{

  private Color color;

  private Coordinate position;

  private ChessModel model;
  private List<List<Tile>> board;
  private PieceStatus status;

  public Queen(Color color, Coordinate position,ChessModel model){
    this.color=color;
    this.position=position;
    this.model=model;
    this.board=model.getBoard();
  }


  @Override
  public void move(Coordinate coordinate) {
    if(!checkSameCoordinate(position,coordinate)){
      throw new IllegalStateException("cannot move to the same location");
    }
    List<Coordinate> validMoves=getValidMoves();
    if(checkContain(validMoves,coordinate)){
      Piece piece=this.model.getTileAt(coordinate).getPiece();
      if(piece!=null){
        if(piece.getColor()==this.color){
          throw new IllegalStateException("move is illegal");
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
      return "BQ";
    }
    return "WQ";
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
    List<Coordinate> validMoves=new ArrayList<>();
    List<Coordinate> directions=getDirections();

    for(Coordinate d:directions){
      int row=position.getX();
      int col=position.getY();

      while(true){
        row+=d.getX();
        col+=d.getY();
        if(row<0 || row>=8){
          break;
        }
        if(col<0 || col>=8){
          break;
        }
        Coordinate coor=new Coordinate(row,col);
        if(model.getTileAt(coor).getPiece()!=null){
          validMoves.add(coor);
          break;
        }else{
          validMoves.add(coor);
        }

      }

    }
    return validMoves;
  }

  private List<Coordinate> getDirections(){
    List<Coordinate> directions=new ArrayList<>();
    //adding the rook directions
    directions.add(new Coordinate(1,0));
    directions.add(new Coordinate(-1,0));
    directions.add(new Coordinate(0,1));
    directions.add(new Coordinate(0,-1));
    //adding the bishop directions
    directions.add(new Coordinate(1,1));
    directions.add(new Coordinate(-1,1));
    directions.add(new Coordinate(1,-1));
    directions.add(new Coordinate(-1,-1));

    return directions;

  }
}
