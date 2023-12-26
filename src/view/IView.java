package src.view;

import src.controller.Features;

/**
 * Interface for the IFrame of the view
 */
public interface IView {
  /**
   * Method to display the frame.
   */
  void display();

  /**
   * Method to add the controller as a listener to the view.
   * @param features the controller
   */
  void addFeatures(Features features);

}
