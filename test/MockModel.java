package test;

import src.model.ChessModel;
import src.model.Color;
import src.model.Coordinate;
import src.model.IModel;
import src.model.Tile;

import java.util.HashMap;
import java.util.List;

public class MockModel implements IModel {
  @Override
  public void startGame() {

  }

  @Override
  public void placePiece(Coordinate coordinate, Coordinate orgPos) {

  }

  @Override
  public List<List<Tile>> getBoard() {
    return null;
  }

  @Override
  public HashMap<Color, Integer> getScore() {
    return null;
  }

  @Override
  public Color getWinner() {
    return null;
  }

  @Override
  public Tile getTileAt(Coordinate coordinate) {
    return null;
  }

  @Override
  public Color getTurn() {
    return null;
  }

  @Override
  public Coordinate getPlayer() {
    return null;
  }
}
