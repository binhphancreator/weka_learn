package Model;

import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.classifiers.Evaluation;
import java.util.Random;

public class KNNModel extends KnowledgeModel {
  private IBk knn;
  private Evaluation evaluation;

  public KNNModel(String filename) {
    super(filename);
  }

  public void buildKNN(String filename) {
    try {
      setDataSet(TYPE_DATASET.TRAIN, filename);
      this.trainDataset.setClassIndex(this.trainDataset.numAttributes() - 1);
      this.knn = new IBk();
      //knn.setOptions(new String[]{"-A", "5"});
      knn.buildClassifier(trainDataset);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  
  public void evaluateKNN(String filename) {
    setDataSet(TYPE_DATASET.TEST, filename);
    this.testDataset.setClassIndex(this.testDataset.numAttributes() - 1);

    Random random = new Random();
    int folds = 10;

    try {
      evaluation = new Evaluation(this.testDataset);
      evaluation.crossValidateModel(knn, this.testDataset, folds, random);
      System.out.println(evaluation.toSummaryString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void predictClassLable(String fileIn, String fileOut) {
    Instances unlabel = loadDataSet(fileIn);
    unlabel.setClassIndex(unlabel.numAttributes() - 1);
    
    for (int i=0; i<unlabel.numInstances(); i++) {
      try {
        double predict = knn.classifyInstance(unlabel.instance(i));
        unlabel.instance(i).setClassValue(predict);
      } catch (Exception e) {
        System.out.println("Error prediction");
      }
    }
    System.out.println(unlabel.toString());
  }
}
