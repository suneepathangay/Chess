package src.view;

import src.model.ReadOnlyChess;

import java.awt.*;

import javax.swing.*;

public class Frame extends JFrame implements IFrame {

  private JFrame frame;

  private Panel panel;



  public Frame(ReadOnlyChess model){
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 800);

    panel=new Panel(model);
    frame.add(panel);


  }

  @Override
  public void display() {
    frame.setVisible(true);
  }
}
