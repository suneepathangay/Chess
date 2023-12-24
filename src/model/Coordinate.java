package src.model;

/**
 * Coordinate class for representing all the coordinates in the board.
 */
public class Coordinate {

  private int x;

  private int y;

  public Coordinate(int x,int y){
    this.x=x;
    this.y=y;
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

  public boolean equals(Coordinate other){
    if(this.getX()==other.getX() && this.getY()== other.getY()){
      return true;
    }
    return false;
  }

}
