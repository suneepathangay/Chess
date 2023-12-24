package src;

import src.model.ChessModel;
import src.model.ReadOnlyChess;
import src.view.ChessTextView;
import src.view.Frame;
import src.view.TextView;

public class Main {

  public static void main(String[] args){
    ReadOnlyChess model=new ChessModel();
    ChessTextView view=new ChessTextView(model);
    view.display();
    Frame frame=new Frame(model);
    frame.display();

  }


}
