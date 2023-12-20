package test;

import org.junit.Assert;
import org.junit.Test;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.Tile;
import src.model.pieces.Piece;
import src.model.pieces.Rook;
import src.view.ChessTextView;
import src.view.TextView;

public class RookTest {


  @Test
  public void testRookCreated(){
    ChessModel model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    Tile tile=model.getTileAt(new Coordinate(7,0));
    Piece piece=tile.getPiece();
    piece.move(new Coordinate(5,0));//moving the rook up
    Assert.assertThrows(IllegalStateException.class,()->piece.move(new Coordinate(3,4)));
    piece.move(new Coordinate(7,0));//moving the rook right
  }

  @Test
  public void testRookMovement(){
    ChessModel model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    Piece rook=model.getTileAt(new Coordinate(7,7)).getPiece();
    rook.move(new Coordinate(7,6));
    Assert.assertEquals(Color.WHITE,model.getTileAt(new Coordinate(7,6)).getPiece().getColor());
  }

  @Test
  public void testRookBadMovement(){
    ChessModel model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    Piece rook=model.getTileAt(new Coordinate(0,0)).getPiece();
    Assert.assertThrows(IllegalStateException.class,()->rook.move(new Coordinate(0,5)));
  }
  @Test
  public void testRookCantCaptureSamePiece(){
    ChessModel model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    Piece rook=model.getTileAt(new Coordinate(0,0)).getPiece();
   Assert.assertThrows(IllegalStateException.class,()->rook.move(new Coordinate(0,4)));
  }
}
