package src.view;

import src.controller.Features;
import src.model.Color;
import src.model.Coordinate;
import src.model.ReadOnlyChess;
import src.model.Tile;
import src.model.pieces.Piece;
import src.model.pieces.Rook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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

  private List<String> coorList=new ArrayList<>(2);

  private HashMap<String,JLabel> nameToPiece=new HashMap<>();

  private int prevSelectedRow = -1;
  private int prevSelectedCol = -1;

  private Color prevSelectedColor = null;

  private Coordinate currSelectedPiece=null;

  public Panel(ReadOnlyChess model) {
    this.model = model;
    this.currBoard = model.getBoard();
    setFocusable(true);
    requestFocusInWindow();
    pieces=constructPieces();
    generateMap();
    System.out.println(nameToPiece.keySet());

  }

  private void handleClick(int x, int y,Features features) {
    int panelWidth = getWidth();
    int panelHeight = getHeight();
    int boardSize = currBoard.size();
    int squareSize = 70;

    int boardWidth = boardSize * squareSize;
    int boardHeight = boardSize * squareSize;

    int boardX = (panelWidth - boardWidth) / 2;
    int boardY = (panelHeight - boardHeight) / 2;

    int relativeX = x - boardX;
    int relativeY = y - boardY;

    int row = relativeY / squareSize;
    int col = relativeX / squareSize;

    //checks if the board is within  bounds
    if (row >= 0 && row < currBoard.size() && col >= 0 && col < currBoard.size() && model.getTileAt(new Coordinate(row,col)).getPiece()!=null) {
      Tile tile = model.getTileAt(new Coordinate(row, col));

      if (prevSelectedRow != -1 && prevSelectedCol != -1) {
        Coordinate prevCoor = new Coordinate(prevSelectedRow, prevSelectedCol);
        Tile prevTile = model.getTileAt(prevCoor);
        prevTile.setColor(prevSelectedColor);
        repaint();
      }

        prevSelectedColor = tile.getColor();
        tile.setColor(Color.BLUE);



      prevSelectedRow = row;
      prevSelectedCol = col;

      currSelectedPiece=tile.getCoordinate();
      repaint();
    }else{
      //we are clicking on a empty square or we are trying to capture an opposite piece
      Coordinate coor=clickToCoor(x,y);
      System.out.println("executing");
      if(currSelectedPiece!=null ){


        System.out.println(coor.getX()+" "+coor.getY());
        //gett the piece original coordinate

        features.movePiece(currSelectedPiece,coor);
        //resetting the piece to null
        currSelectedPiece=null;
        repaint();
      }
    }
  }

  private Coordinate clickToCoor(int x, int y){
    int panelWidth = getWidth();
    int panelHeight = getHeight();
    int boardSize = currBoard.size();
    int squareSize = 70;

    int boardWidth = boardSize * squareSize;
    int boardHeight = boardSize * squareSize;

    int boardX = (panelWidth - boardWidth) / 2;
    int boardY = (panelHeight - boardHeight) / 2;

    int relativeX = x - boardX;
    int relativeY = y - boardY;

    int row = relativeY / squareSize;
    int col = relativeX / squareSize;
    return new Coordinate(row,col);

  }






  public void addFeatures(Features features){
    this.addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 1) {
          handleClick(e.getX(), e.getY(),features);
        } else if (e.getClickCount() == 2) {
          // Double-click detected, deselect the cell
          if (prevSelectedRow != -1 && prevSelectedCol != -1) {
            Coordinate prevCoor = new Coordinate(prevSelectedRow, prevSelectedCol);
            Tile prevTile = model.getTileAt(prevCoor);
            prevTile.setColor(prevSelectedColor);
            prevSelectedRow = -1;
            prevSelectedCol = -1;
            prevSelectedColor = null;
            repaint();
          }
        }
      }



    });




  }

  private void constructBoard(Graphics g) {
    // Remove all components from the panel before redrawing
    removeAll();

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

        int squareX = x + col * squareSize / 1000;
        int squareY = y + row * squareSize / 1000;

        Tile tile = model.getTileAt(new Coordinate(row, col));
        if (tile.getPiece() != null) {
          Piece piece = tile.getPiece();

          //TODO problem might arise here because we might need deep copies instead of shallow copies
          JLabel pieceImg = nameToPiece.get(piece.getName());
          JLabel copiedLabel = shallowCopyJLabel(pieceImg);

          int pieceX = squareX + (squareSize - pieceImg.getIcon().getIconWidth()) - 10;
          int pieceY = squareY + (squareSize - pieceImg.getIcon().getIconHeight()) - 5;

          copiedLabel.setBounds(pieceX, pieceY, pieceImg.getIcon().getIconWidth(), pieceImg.getIcon().getIconHeight());

          add(copiedLabel);  // Add the JLabel to the panel
        }

        Color color = tile.getColor();
        g.setColor(getColor(color));

        g.fillRect(x, y, squareSize, squareSize);
        x += squareSize;
        isWhite = !isWhite;
      }

      y += squareSize;
      x = (panelWidth - boardWidth) / 2;
      isWhite = !isWhite;
    }

    // Repaint the panel to reflect the changes
    repaint();
  }


  private JLabel shallowCopyJLabel(JLabel originalLabel) {
    JLabel copiedLabel = new JLabel(originalLabel.getIcon());
    copiedLabel.setBounds(originalLabel.getBounds());
    copiedLabel.setName(originalLabel.getName());
    // Copy other properties as needed

    return copiedLabel;
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


  public java.awt.Color getColor(Color color){
    switch (color){
      case BLACK:
        return java.awt.Color.DARK_GRAY;
      case WHITE:
        return java.awt.Color.WHITE;
      case BLUE:
        return java.awt.Color.BLUE;
    }
    return null;
  }





  @Override
  protected void paintComponent(Graphics g) {
    constructBoard(g);
  }
}
