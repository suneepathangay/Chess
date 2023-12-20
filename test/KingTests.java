package test;

import org.junit.Assert;
import org.junit.Test;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.Tile;
import src.model.pieces.King;
import src.model.pieces.Piece;

public class KingTests {

  @Test
  public void testKingCreated(){
    ChessModel model=new ChessModel();
    Tile tile=model.getTileAt(new Coordinate(7,4));
    Tile tile2=model.getTileAt(new Coordinate(0,4));
    Assert.assertNotEquals(null,tile.getPiece());
    Assert.assertNotEquals(null,tile2.getPiece());

    Assert.assertEquals(Color.WHITE,tile.getPiece().getColor());
    Assert.assertEquals(Color.BLACK,tile2.getPiece().getColor());
  }

  @Test
  public void testKingMovement(){
    ChessModel model=new ChessModel();
    Tile tile=model.getTileAt(new Coordinate(7,4));
    Piece king=tile.getPiece();
    Assert.assertThrows(IllegalStateException.class,()->king.move(new Coordinate(4,5)));
    king.move(new Coordinate(7,5));
    Tile tile2=model.getTileAt(new Coordinate(7,5));
    Assert.assertNotEquals(null,tile2.getPiece());

  }

  @Test
  public void testCastling(){

  }
}
