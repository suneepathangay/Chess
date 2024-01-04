package test;

import org.junit.Assert;
import org.junit.Test;

import src.model.Coordinate;
import src.model.pieces.King;
import src.model.pieces.Piece;
import src.view.ChessTextView;

/**
 * Mock Model
 */
public class MockTests {

  @Test
  public void testMockModelCheck(){
    MockModel mock=new MockModel();
    ChessTextView view=new ChessTextView(mock);
    view.display();
    Piece piece=mock.getTileAt(new Coordinate(3,4)).getPiece();
    if(piece instanceof King){
      System.out.println(((King) piece).isInCheck());
      Assert.assertEquals(true,((King) piece).isInCheck());
    }
    Piece knight=mock.getTileAt(new Coordinate(5,5)).getPiece();
    knight.move(new Coordinate(3,6));
    view=new ChessTextView(mock);
    view.display();
    if(piece instanceof King){
      System.out.println(((King) piece).isInCheck());
      Assert.assertEquals(false,((King) piece).isInCheck());
    }
  }


}
