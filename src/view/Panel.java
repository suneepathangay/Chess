package src.view;

import src.model.Coordinate;
import src.model.ReadOnlyChess;
import src.model.Tile;
import src.model.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Public class for the JPanel view of the board
 */
public class Panel extends JPanel implements IPanel {

  private ReadOnlyChess model;
  private List<List<Tile>> currBoard;
  private List<JLabel> pieces;

  private HashMap<String,JLabel> nameToPiece=new HashMap<>();

  public Panel(ReadOnlyChess model) {
    this.model = model;
    this.currBoard = model.getBoard();
    setFocusable(true);
    requestFocusInWindow();
    pieces=constructPieces();
    generateMap();

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

        int squareX = x + col * squareSize/1000;
        int squareY = y + row * squareSize/1000;

        Tile tile=model.getTileAt(new Coordinate(row,col));
        if(tile.getPiece()!=null){
          Piece piece=tile.getPiece();
          JLabel pieceImg=nameToPiece.get(piece.getName());

          // Calculate the center coordinates of the piece within the square
          int pieceX = squareX + (squareSize - pieceImg.getIcon().getIconWidth())-10 ;
          int pieceY = squareY + (squareSize - pieceImg.getIcon().getIconHeight())-5;

          // Set the bounds for the piece
          pieceImg.setBounds(pieceX, pieceY, pieceImg.getIcon().getIconWidth(), pieceImg.getIcon().getIconHeight());

          // Add the piece to the panel
          this.add(pieceImg);
        }

        if (isWhite) {
          g.setColor(Color.WHITE);
        } else {
          g.setColor(Color.DARK_GRAY);
        }
        g.fillRect(x, y, squareSize, squareSize);
        x += squareSize;
        isWhite = !isWhite;

        //adding the pieces

      }

      y += squareSize;
      x = (panelWidth - boardWidth) / 2;
      isWhite = !isWhite;
    }



  }

  private List<JLabel> constructPieces() {
    List<JLabel> pieces = new ArrayList<>();
    try {
      BufferedImage image = ImageIO.read(new File("/Users/suneetpathangay/Desktop/chess.png"));

      int rows = 2;
      int cols = 6;

      int chunkWidth = image.getWidth() / cols;
      int chunkHeight = image.getHeight() / rows;

      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          int x = j * chunkWidth;
          int y = i * chunkHeight;
          BufferedImage chunk = image.getSubimage(x, y, chunkWidth, chunkHeight);

          Image resizedImage = chunk.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

          ImageIcon chunkIcon = new ImageIcon(resizedImage);
          JLabel chunkLabel = new JLabel(chunkIcon);
          pieces.add(chunkLabel);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return pieces;
  }

  private void generateMap(){
    for(int i=0; i<12; i++){
      if(i==0){
        nameToPiece.put("WK",pieces.get(0));
      }
      if(i==1){
        nameToPiece.put("WQ",pieces.get(1));
      }
      if(i==2){
        nameToPiece.put("WB",pieces.get(2));
      }
      if(i==3){
        nameToPiece.put("WN",pieces.get(3));
      }
      if(i==4){
        nameToPiece.put("WR",pieces.get(4));
      }
      if(i==5){
        nameToPiece.put("WP",pieces.get(5));
      }
      if(i==6){
        nameToPiece.put("BK",pieces.get(6));
      }
      if(i==7){
        nameToPiece.put("BQ",pieces.get(7));
      }
      if(i==8){
        nameToPiece.put("BB",pieces.get(8));
      }
      if(i==9){
        nameToPiece.put("BN",pieces.get(9));
      }
      if(i==10){
        nameToPiece.put("BR",pieces.get(10));
      }
      if(i==11){
        nameToPiece.put("BP",pieces.get(11));
      }



    }
  }






  @Override
  protected void paintComponent(Graphics g) {
    constructBoard(g);
  }
}
