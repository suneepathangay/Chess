package test;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.IModel;
import src.model.Tile;
import src.model.pieces.King;
import src.model.pieces.Knight;
import src.model.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockModel implements IModel {

  private Color turn;

  private List<List<Tile>> board=new ArrayList<>();

  public MockModel(){
    initBoard();
    setupKings();
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

  private void setupKings(){
    Tile tile=this.getTileAt(new Coordinate(3,4));
    tile.setPiece(new King(Color.WHITE,new Coordinate(3,4),this));

    //placing a
    Tile tile2=this.getTileAt(new Coordinate(5,5));
    tile2.setPiece(new Knight(Color.BLACK,new Coordinate(5,5),this));

  }

  private void setTurn(){
    if(this.turn==Color.BLACK){
      this.turn=Color.WHITE;
    }else{
      this.turn=Color.BLACK;
    }
  }
}
