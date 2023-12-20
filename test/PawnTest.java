package test;

import org.junit.Assert;
import org.junit.Test;

import src.model.ChessModel;
import src.model.Coordinate;
import src.model.Tile;
import src.model.pieces.Pawn;
import src.model.pieces.Piece;
import src.view.ChessTextView;
import src.view.TextView;

public class PawnTest {

  //test if a pawn is created
  @Test
  public void testPawnCreated(){
    ChessModel model=new ChessModel();
    for(int i=0; i<8; i++){
      Tile tile=model.getTileAt(new Coordinate(1,i));
      Assert.assertNotEquals(null,tile.getPiece());
    }
    for(int i=0; i<8; i++){
      Tile tile=model.getTileAt(new Coordinate(6,i));
      Assert.assertNotEquals(null,tile.getPiece());
    }
  }

  @Test
  public void testPawnMovementFirst(){
    //test if white pawn can move first
    ChessModel model=new ChessModel();
    Piece pawn=model.getTileAt(new Coordinate(6,0)).getPiece();
    pawn.move(new Coordinate(4,0));

    Piece pawn2=model.getTileAt(new Coordinate(1,0)).getPiece();
    pawn2.move(new Coordinate(3,0));

    //TextView view=new ChessTextView(model);
    //view.display();
  }
  @Test
  public void testPawnMovement(){
    ChessModel model=new ChessModel();
    Piece pawn=model.getTileAt(new Coordinate(6,0)).getPiece();
    pawn.move(new Coordinate(4,0));

    Piece pawn2=model.getTileAt(new Coordinate(1,0)).getPiece();
    pawn2.move(new Coordinate(3,0));

    pawn.move(new Coordinate(3,0));
  }

  @Test
  public void testIllegalPawn(){
    ChessModel model=new ChessModel();
    Piece pawn=model.getTileAt(new Coordinate(6,0)).getPiece();
    Assert.assertThrows(IllegalStateException.class,()->pawn.move(new Coordinate(6,1)));

  }

}
