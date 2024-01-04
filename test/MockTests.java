package test;

import org.junit.Assert;
import org.junit.Test;

import src.controller.Controller;
import src.model.Coordinate;
import src.model.pieces.King;
import src.model.pieces.Piece;
import src.view.ChessTextView;
import src.view.Frame;
import src.view.IView;

/**
 * Mock Model
 */
public class MockTests {

  //TODO Make sure that pieces cant capture KINGS
  @Test
  public void testCheck(){
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


  @Test
  public void testCheckMate(){
    MockModel mock=new MockModel();
    ChessTextView view=new ChessTextView(mock);
    view.display();
    Piece piece=mock.getTileAt(new Coordinate(3,4)).getPiece();
    if(piece instanceof King){
      ((King) piece).isInCheckMate();
      System.out.println(((King) piece).isInCheckMate());
    }

  }

  //test to make sure that king cant capture a protected piece
  @Test
  public void protectedPiece(){

  }



}
