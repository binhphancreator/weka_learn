package Model;

import weka.classifiers.lazy.IBk;
import weka.classifiers.Evaluation;
import java.util.Random;

public class KNNModel extends KnowLedgeModel {
  private IBk knn;
  private Evaluation evaluation;

  public KNNModel(String filename) {
    super(filename);
  }

  public void buildKNN(String filename) {

  }
  
  public void evaluateKNN() {
    Random random = new Random();
    int folds = 10;
  }
}
