package src.model;

import src.model.pieces.King;
import src.model.pieces.Pawn;
import src.model.pieces.Piece;
import src.model.pieces.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessModel implements IModel {

  private boolean isGameStarted;

  private Color turn;

  Map<Color,Integer> scoreMap=new HashMap<>();

  List<List<Tile>> board=new ArrayList<>();
  public ChessModel(){
    this.isGameStarted=false;
    initBoard();
    placeKings();
    placeRooks();
    setupPawn();
    this.turn=Color.WHITE;
  }

  @Override
  public void placePiece(Coordinate coordinate,Coordinate orgPos){
    Tile tile=this.getTileAt(orgPos);
    Piece piece=tile.getPiece();
    if(piece.getColor()!=turn){
      throw new IllegalStateException("not your turn");
    }
    tile.setPiece(null);
    Tile newTile=this.getTileAt(coordinate);
    newTile.setPiece(piece);
    setTurn();
  }


  public Tile getTileAt(Coordinate coordinate){
    return this.board.get(coordinate.getX()).get(coordinate.getY());

  }

  private void setTurn(){
    if(this.turn==Color.BLACK){
      this.turn=Color.WHITE;
    }else{
      this.turn=Color.BLACK;
    }
  }


  @Override
  public List<List<Tile>> getBoard() {
    return board;
  }

  @Override
  public void startGame() {
    this.isGameStarted=true;
  }

  @Override
  public HashMap<Color, Integer> getScore() {
    return null;
  }

  @Override
  public Color getWinner() {
    return null;
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

  private void placeKings(){
    //initally we will just set the kings to a place in the board
    Tile tile=this.board.get(7).get(4);
    King king=new King(Color.WHITE,tile.getCoordinate(),this);
    tile.setPiece(king);

    Tile tile2=this.board.get(0).get(4);
    King king2=new King(Color.BLACK,tile2.getCoordinate(),this);
    tile2.setPiece(king2);
  }

  private void placeRooks(){
    Tile tile=this.board.get(7).get(0);
    Rook whiteOne=new Rook(Color.WHITE,tile.getCoordinate(),this);
    tile.setPiece(whiteOne);

    Tile tile2=this.board.get(7).get(7);
    Rook whiteTwo=new Rook(Color.WHITE,tile2.getCoordinate(),this);
    tile2.setPiece(whiteTwo);


    Tile tile3=this.board.get(0).get(0);
    Rook blackOne=new Rook(Color.BLACK,tile3.getCoordinate(),this);
    tile3.setPiece(blackOne);

    Tile tile4=this.board.get(0).get(7);
    Rook blackTwo=new Rook(Color.BLACK,tile4.getCoordinate(),this);
    tile4.setPiece(blackTwo);
  }

  private void setupPawn(){
    //pawn indexes are at 1 and 6
    for(int i=0; i<8; i++){
      Tile tile=this.board.get(1).get(i);
      Piece pawn=new Pawn(Color.BLACK,tile.getCoordinate(),this);
      tile.setPiece(pawn);
    }

    for(int i=0; i<8; i++){
      Tile tile=this.board.get(6).get(i);
      Piece pawn=new Pawn(Color.WHITE, tile.getCoordinate(),this);
      tile.setPiece(pawn);
    }

  }

}
