package src.model.pieces;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.IModel;
import src.model.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends Piece implements IPiece{

  private PieceStatus status;

  private Coordinate position;

  private boolean hasMoved;

  private IModel model;

  private List<List<Tile>> board;



  private Color color;

  public King(Color color, Coordinate position, IModel model){
    this.status=PieceStatus.PLAYING;
    this.hasMoved=false;
    this.color=color;
    this.position=position;
    this.model=model;
    this.board=model.getBoard();

  }
  @Override
  public void move(Coordinate coordinate) {
    if(checkSameCoordinate(position,coordinate)){
      throw new IllegalStateException("need to move to different spot");
    }

    this.hasMoved=true;
    if(isValidMove(coordinate)) {
      if(model.getTileAt(coordinate).getPiece()!=null){
        Piece piece=model.getTileAt(coordinate).getPiece();
        if(piece.getColor()==this.color){
          throw new IllegalStateException("you cannot capture your own piece");
        }
      }

      model.placePiece(coordinate, position);
      this.position=coordinate;
    }else{
      throw new IllegalStateException("illegal move");
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
      return "BK";
    }
    return "WK";
  }

  @Override
  public boolean isValidMove(Coordinate coordinate){
    List<Coordinate> validMoves=new ArrayList<>();


    if(coordinate.getX()<0 || coordinate.getY()<0){
      throw new IllegalStateException("out of bounds");
    }
    if(coordinate.getX()>=8 || coordinate.getY()>=8){
      throw new IllegalStateException("out of bounds");
    }

    List<Coordinate> validDirections=getValidDirections();

    for(Coordinate coor:validDirections) {
      int newPos = coor.getX() + position.getX();
      int newCol = coor.getY() + position.getY();
      Coordinate newCoor = new Coordinate(newPos, newCol);
      validMoves.add(newCoor);
    }

    return checkContain(validMoves,coordinate);
  }

  private boolean canCastle(){
    //if the king is at its original position and there is a rook exactly two spots over
    if(position.getX()==0 || position.getX()==7){
      if(!hasMoved && position.getY()==4){
        //call getBoardAtMethod to check for a rook
      }
    }

    return false;
  }


  private List<Coordinate> getValidMoves(){
    List<Coordinate> validMoves=new ArrayList<>();

    List<Coordinate> validDirections=getValidDirections();

    //TODO make sure that the place where we are mvoing cannot be a valid move for another piece
    //TODO create a mock model for testing purposes
    for(Coordinate coor:validDirections) {
      int newPos = coor.getX() + position.getX();
      int newCol = coor.getY() + position.getY();
      Coordinate newCoor=new Coordinate(newPos,newCol);
      for(int i=0; i<board.size(); i++){
        for(int j=0; j<board.size(); j++){
          Tile tile=model.getTileAt(new Coordinate(i,j));
          if(tile.getPiece()!=null){
            Piece piece=tile.getPiece();
            if(piece instanceof King){
              if(!Objects.equals(piece.getName(), this.getName())){
                //if a piece can capture where our
                 if(!piece.isValidMove(newCoor) && piece.getColor()!=this.color){
                    validDirections.add(newCoor);
                 }
                //if that coordinate coouldnt be captured by another piece but now can be
                if(piece.isValidMove(newCoor) && checkContain(validMoves,newCoor)){
                  validMoves.remove(newCoor);
                }

              }
            }else{
              //if the piece is not null
              if(!piece.isValidMove(newCoor)){
                validDirections.add(newCoor);
              }
              if(piece.isValidMove(newCoor) && checkContain(validMoves,newCoor)){
                validMoves.remove(newCoor);
              }
            }
          }
        }
      }
    }
    return validMoves;
  }


  //initialize a list of valid directions for a king
  public List<Coordinate> getValidDirections(){
    List<Coordinate> validDirections=new ArrayList<>();
    validDirections.add(new Coordinate(1,0));
    validDirections.add(new Coordinate(-1,0));
    validDirections.add(new Coordinate(0,1));
    validDirections.add(new Coordinate(-1,0));
    validDirections.add(new Coordinate(1,1));
    validDirections.add(new Coordinate(1,-1));
    validDirections.add(new Coordinate(-1,-1));
    validDirections.add(new Coordinate(-1,1));
    //if the king can castle
    if(canCastle()){
      validDirections.add(new Coordinate(2,0));
    }
    return validDirections;
  }

  /**
   * Method to determine if the king is in check.
   * @return a boolean variable determining if the king is in check
   */
  public boolean isInCheck(){
    //iterate over the board and for each piece check if a the position of the king is a valid move
    for(int row=0; row<board.size(); row++){
      for(int col=0; col<board.size(); col++){
        Coordinate coor=new Coordinate(row,col);
        Tile tile=model.getTileAt(coor);
        if(tile.getPiece()!=null){
          Piece piece=tile.getPiece();
          if(piece.isValidMove(this.position)){
            return true;
          }

        }

      }
    }
    return false;
  }

  /**
   * Method to determine if the game is over through checkmate
   * @return
   */
  public boolean isInCheckMate(){
    return this.isInCheck();
  }


}
