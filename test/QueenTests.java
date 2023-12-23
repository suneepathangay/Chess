package test;

import org.junit.Test;

import src.model.ChessModel;
import src.model.Coordinate;
import src.model.pieces.Piece;
import src.view.ChessTextView;

public class QueenTests {
  @Test
  public void testQueen(){
    ChessModel model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    //view.display();

    //moving the white pawn
    Piece pawn=model.getTileAt(new Coordinate(6,3)).getPiece();
    pawn.move(new Coordinate(4,3));

    //moving the black pawn
    Piece pawn2=model.getTileAt(new Coordinate(1,0)).getPiece();
    pawn2.move(new Coordinate(2,0));

    //moving the queen
    Piece queen=model.getTileAt(new Coordinate(7,3)).getPiece();
    queen.move(new Coordinate(5,3));
    view=new ChessTextView(model);
   // view.display();

    pawn2.move(new Coordinate(3,0));
    queen.move(new Coordinate(3,5));
    view=new ChessTextView(model);
    //view.display();

    pawn2.move(new Coordinate(4,0));
    queen.move(new Coordinate(4,6));
    view=new ChessTextView(model);
    view.display();

    pawn2.move(new Coordinate(5,0));
    queen.move(new Coordinate(4,5));
    view=new ChessTextView(model);
    view.display();
  }
}
