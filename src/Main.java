package src;

import src.controller.Controller;
import src.model.ChessModel;
import src.model.ReadOnlyChess;
import src.view.ChessTextView;
import src.view.Frame;
import src.view.IView;
import src.view.TextView;

public class Main {

  public static void main(String[] args){
    ChessModel model=new ChessModel();
     IView view=new Frame(model);
    Controller controller=new Controller(model,view);


  }


}
