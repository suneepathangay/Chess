package test;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.IModel;
import src.model.Tile;
import src.model.pieces.King;
import src.model.pieces.Knight;
import src.model.pieces.Piece;
import src.model.pieces.Queen;
import src.model.pieces.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockModel implements IModel {

  private Color turn;

  private List<List<Tile>> board=new ArrayList<>();

  public MockModel(){
    initBoard();

    setupCheckMate();

    this.turn=Color.BLACK;
  }

  private void initBoard(){

    boolean startWhite=true;


    for(int i=0; i<8; i++){
      List<Tile> row=new ArrayList<>();
      boolean isWhite=false;
      if(startWhite){
        isWhite=true;
      }

      for(int j=0; j<8; j++){
        Coordinate coordinate=new Coordinate(i,j);
        Color color=null;
        if(isWhite){
          color=Color.WHITE;
        }else{
          color=Color.BLACK;
        }
        Tile tile=new Tile(coordinate,color);
        row.add(tile);
        isWhite=!isWhite;
      }
      startWhite=!startWhite;
      board.add(row);
    }
  }
  @Override
  public void startGame() {

  }

  @Override
  public void placePiece(Coordinate coordinate, Coordinate orgPos) {
    Tile tile=this.getTileAt(orgPos);
    Piece piece=tile.getPiece();
    if(piece.getColor()!=turn){
      System.out.println(turn);
      throw new IllegalStateException("not your turn");
    }
    tile.setPiece(null);
    Tile newTile=this.getTileAt(coordinate);
    newTile.setPiece(piece);
    setTurn();

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public List<List<Tile>> getBoard() {
    return this.board;
  }

  @Override
  public HashMap<Color, Integer> getScore() {
    return null;
  }

  @Override
  public Color getWinner() {
    return null;
  }

  @Override
  public Tile getTileAt(Coordinate coordinate) {
    return this.board.get(coordinate.getX()).get(coordinate.getY());
  }

  @Override
  public Color getTurn() {
    return this.turn;
  }

  @Override
  public Coordinate getPlayer() {
    return null;
  }



  private void testCheck(){
    Tile tile=this.getTileAt(new Coordinate(3,4));
    tile.setPiece(new King(Color.WHITE,new Coordinate(3,4),this));

    Tile tile2=this.getTileAt(new Coordinate(1,6));
    tile2.setPiece(new Queen(Color.BLACK,new Coordinate(1,6),this));

    Tile tile4=this.getTileAt(new Coordinate(1,2));
    tile4.setPiece(new Queen(Color.BLACK,new Coordinate(1,2),this));

    Tile tile3=this.getTileAt(new Coordinate(5,6));
    tile3.setPiece(new Queen(Color.BLACK,new Coordinate(5,6),this));

    Tile tile5=this.getTileAt(new Coordinate(5,2));
    tile5.setPiece(new Queen(Color.BLACK,new Coordinate(5,2),this));
  }

  private void setupCheckMate(){
    Tile tile=this.getTileAt(new Coordinate(3,4));
    tile.setPiece(new King(Color.WHITE,new Coordinate(3,4),this));

    Tile tile2=this.getTileAt(new Coordinate(1,6));
    tile2.setPiece(new Queen(Color.BLACK,new Coordinate(1,6),this));

    Tile tile4=this.getTileAt(new Coordinate(1,2));
    tile4.setPiece(new Queen(Color.BLACK,new Coordinate(1,2),this));

    Tile tile3=this.getTileAt(new Coordinate(5,6));
    tile3.setPiece(new Queen(Color.BLACK,new Coordinate(5,6),this));

    Tile tile5=this.getTileAt(new Coordinate(5,2));
    tile5.setPiece(new Queen(Color.BLACK,new Coordinate(5,2),this));

    //adding the rooks
    Tile tileRookOne=this.getTileAt(new Coordinate(3,2));
    tileRookOne.setPiece(new Rook(Color.BLACK,new Coordinate(3,2),this));

    Tile tileRookTwo=this.getTileAt(new Coordinate(3,6));
    tileRookTwo.setPiece(new Rook(Color.BLACK,new Coordinate(3,6),this));

  }

  private void setTurn(){
    if(this.turn==Color.BLACK){
      this.turn=Color.WHITE;
    }else{
      this.turn=Color.BLACK;
    }
  }
}
