package src.controller;

import src.model.ChessModel;
import src.model.Coordinate;
import src.model.pieces.Piece;
import src.view.IView;

public class Controller implements Features {

  private ChessModel model;

  private IView view;

  public Controller(ChessModel model, IView view){
    this.model=model;
    this.view=view;
    view.addFeatures(this);
    view.display();
  }


  @Override
  public void movePiece(Coordinate orgCoor, Coordinate destCoor) {
    Piece piece=model.getTileAt(orgCoor).getPiece();


    //there is no piece at the orgCoor
    if(piece==null){
      throw new IllegalStateException("You need to move a piece");
    }

    //dont call the model move method call the piece mthod
    piece.move(destCoor);
  }
}
