package src.view;

import src.model.ReadOnlyChess;
import src.model.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Public class for the JPanel view of the board
 */
public class Panel extends JPanel implements IPanel {

  private ReadOnlyChess model;

  private List<List<Tile>> currBoard;

  public Panel(ReadOnlyChess model){
    this.model=model;
    this.currBoard=model.getBoard();
    setFocusable(true);
    requestFocusInWindow();
  }

  private void constructBoard(Graphics g) {
    int panelWidth = getWidth();
    int panelHeight = getHeight();
    int boardSize = currBoard.size();
    int squareSize = 70;

    int boardWidth = boardSize * squareSize;
    int boardHeight = boardSize * squareSize;

    int x = (panelWidth - boardWidth) / 2;
    int y = (panelHeight - boardHeight) / 2;

    boolean isWhite = true;

    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (isWhite) {
          g.setColor(Color.WHITE);
        } else {
          g.setColor(Color.BLACK);
        }
        g.fillRect(x, y, squareSize, squareSize);
        x += squareSize;
        isWhite = !isWhite;
      }

      y += squareSize;
      x = (panelWidth - boardWidth) / 2;
      isWhite = !isWhite;
    }


  }


  private List<JLabel> constructPieces(){


    List<JLabel> pieces=new ArrayList<>();


    try {
      BufferedImage image = ImageIO.read(new File("/Users/suneetpathangay/Desktop/chess.png"));

      int rows=2;
      int cols=6;

      int chunkWidth=image.getWidth()/cols;
      int chunkHeight=image.getHeight()/rows;

      for(int i=0; i<rows; i++){
        for(int j=0; j<cols; j++){
          int x = j * chunkWidth;
          int y = i * chunkHeight;
          BufferedImage chunk = image.getSubimage(x, y, chunkWidth, chunkHeight);

          Image resizedImage = chunk.getScaledInstance(50, 50, Image.SCALE_SMOOTH);


          ImageIcon chunkIcon = new ImageIcon(resizedImage);
          JLabel chunkLabel = new JLabel(chunkIcon);
          pieces.add(chunkLabel);

        }
      }

    }catch(IOException e){

    }
    return pieces;
  }




  @Override
  protected void paintComponent(Graphics g) {
    int squareSize=70;
    constructBoard(g);

    List<JLabel> pieces = constructPieces();

    JLabel piece = pieces.get(3);
    int x = (getWidth() - squareSize * currBoard.size()) / 2 + squareSize;
    int y = (getHeight() - squareSize * currBoard.size()) / 2;
    piece.setBounds(x + 10, y + 10, 50, 50);
    this.add(piece);



  }


}
