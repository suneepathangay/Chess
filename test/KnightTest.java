package test;

import org.junit.Assert;
import org.junit.Test;

import src.model.ChessModel;
import src.model.Coordinate;
import src.model.pieces.Knight;
import src.model.pieces.Piece;
import src.view.ChessTextView;

public class KnightTest {

  @Test
  public void testKnightMovement(){
    ChessModel model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    Piece knight=model.getTileAt(new Coordinate(7,1)).getPiece();
    knight.move(new Coordinate(5,2));
    Assert.assertNotEquals(null,model.getTileAt(new Coordinate(5,2)));
    view.display();
    Piece pawn=model.getTileAt(new Coordinate(1,0)).getPiece();
    pawn.move(new Coordinate(2,0));
    view=new ChessTextView(model);
    view.display();
    knight.move(new Coordinate(7,1));
    Assert.assertNotEquals(null,model.getTileAt(new Coordinate(7,1)));
  }

  @Test
  public void testKnightMoveRight(){
    ChessModel model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    Piece knight=model.getTileAt(new Coordinate(7,1)).getPiece();
    Assert.assertThrows(IllegalStateException.class,()->knight.move(new Coordinate(6,3)));
    view.display();
    knight.move(new Coordinate(5,2));
    Piece pawn=model.getTileAt(new Coordinate(1,0)).getPiece();
    pawn.move(new Coordinate(2,0));
    view=new ChessTextView(model);
    view.display();
    knight.move(new Coordinate(4,4));
    view=new ChessTextView(model);
    view.display();
  }

  @Test
  public void testMoveLeft(){
    ChessModel model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    Piece knight=model.getTileAt(new Coordinate(7,1)).getPiece();
    knight.move(new Coordinate(5,2));
    Piece pawn=model.getTileAt(new Coordinate(1,0)).getPiece();
    pawn.move(new Coordinate(2,0));
    knight.move(new Coordinate(4,0));
  }



}
