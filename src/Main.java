package src;

import src.model.ChessModel;
import src.model.ReadOnlyChess;
import src.view.ChessTextView;
import src.view.Frame;
import src.view.TextView;

public class Main {

  public static void main(String[] args){
    ReadOnlyChess model=new ChessModel();
    Frame frame=new Frame(model);
    frame.display();

  }


}
