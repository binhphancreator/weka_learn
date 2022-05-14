package Model;

import weka.classifiers.lazy.IBk;
import weka.classifiers.Evaluation;
import java.util.Random;

public class KNNModel extends KnowledgeModel {
  private IBk knn;
  public Evaluation evaluation;

  public KNNModel(String filename) {
    super(filename);
  }

  public KNNModel() { }

  public KNNModel buildKNN(String filename) {
    try {
      setDataSet(TYPE_DATASET.TRAIN, filename);
      this.trainDataset.setClassIndex(this.trainDataset.numAttributes() - 1);
      this.knn = new IBk();
      //knn.setOptions(new String[]{"-A", "5"});
      knn.buildClassifier(trainDataset);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return this;

  }
  
  public KNNModel evaluateKNN(String filename) {
    setDataSet(TYPE_DATASET.TEST, filename);
    this.testDataset.setClassIndex(this.testDataset.numAttributes() - 1);

    Random random = new Random();
    int folds = 10;

    try {
      evaluation = new Evaluation(this.testDataset);
      evaluation.crossValidateModel(knn, this.testDataset, folds, random);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return this;
  }

  public KNNModel predictClassLable(String filename) {
    setDataSet(TYPE_DATASET.UNLABEL, filename);
    unlabelDataset.setClassIndex(unlabelDataset.numAttributes() - 1);
    
    
    for (int i=0; i<unlabelDataset.numInstances(); i++) {
      try {
        double predict = knn.classifyInstance(unlabelDataset.instance(i));
        unlabelDataset.instance(i).setClassValue(predict);
      } catch (Exception e) {
        System.out.println("Error prediction");
      }
    }
    return this;
  }
}
