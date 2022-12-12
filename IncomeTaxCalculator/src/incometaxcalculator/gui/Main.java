
package incometaxcalculator.gui;

import java.awt.EventQueue;

public class Main {
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GraphicalInterface frame = new GraphicalInterface();
          frame.setVisible(true);
          frame.setTitle("Income Tax Calculator ");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}