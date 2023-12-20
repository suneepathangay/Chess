package src.view;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.ReadOnlyChess;
import src.model.Tile;
import src.model.pieces.King;
import src.model.pieces.Pawn;
import src.model.pieces.Piece;
import src.model.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessTextView implements TextView {


  private ReadOnlyChess model;

  public ChessTextView(ReadOnlyChess model){
    this.model=model;
  }

  private String constructView(){
    StringBuilder sb=new StringBuilder();
    List<List<Tile>> board=model.getBoard();



    for(int i=0; i<board.size(); i++){
      StringBuilder row=new StringBuilder();
      for(int j=0; j<board.size(); j++){
        Tile tile=board.get(i).get(j);
        if(tile.getPiece()!=null){
          if(tile.getPiece() instanceof King){
            row.append("K ");
          }
          if(tile.getPiece() instanceof Rook){
            row.append("R ");
          }
          if(tile.getPiece() instanceof Pawn){
            row.append("P ");
          }
        }else{
          Color color=tile.getColor();
          if(color==Color.WHITE){
            row.append("O ");
          }else{
            row.append("X ");
          }
        }
      }
      sb.append(row.toString()).append("\n");
    }
    return sb.toString();
  }




  @Override
  public void display() {
    System.out.println(constructView());

  }

  public static void main(String[] args){
    ReadOnlyChess model=new ChessModel();
    TextView view=new ChessTextView(model);
    view.display();

    //trying to move the king
    Piece king=model.getBoard().get(7).get(4).getPiece();
    king.move(new Coordinate(7,5));
    view=new ChessTextView(model);
    view.display();

  }

}
