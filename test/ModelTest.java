package test;

import org.junit.Test;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.pieces.King;
import src.view.ChessTextView;
import src.view.TextView;

public class ModelTest {

  @Test
  public void testCoordinateSystem(){
    ChessModel model=new ChessModel();
    TextView view=new ChessTextView(model);
    model.getBoard().get(7).get(0).setPiece(new King(Color.WHITE, new Coordinate(7,0),model));
    view=new ChessTextView(model);

  }
}
