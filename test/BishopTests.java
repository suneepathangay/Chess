package test;

import org.junit.Assert;
import org.junit.Test;

import src.model.ChessModel;
import src.model.Coordinate;
import src.model.pieces.Piece;
import src.view.ChessTextView;

public class BishopTests {

  @Test
  public void testBishopSetUp(){
    ChessModel model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    //moving the white pawn
    Piece pawn1=model.getTileAt(new Coordinate(6,3)).getPiece();
    pawn1.move(new Coordinate(4,3));
    //moving the black pawn
    Piece pawn2=model.getTileAt(new Coordinate(1,1)).getPiece();
    pawn2.move(new Coordinate(2,1));
    view=new ChessTextView(model);
    view.display();

    //testing the bishop
    Piece bishop=model.getTileAt(new Coordinate(7,2)).getPiece();
    bishop.move(new Coordinate(5,4));
    view=new ChessTextView(model);
    view.display();

    pawn2.move(new Coordinate(3,1));
    Assert.assertThrows(IllegalStateException.class,()->bishop.move(new Coordinate(5,3)));
  }
}
