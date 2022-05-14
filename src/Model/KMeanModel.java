package Model;

import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;

public class KMeanModel extends KnowledgeModel {

  public SimpleKMeans kMeans;

  public KMeanModel(String filename) {
    super(filename);
  }

  public KMeanModel() { }

  public KMeanModel buildKMeanModel(String filename, int clusterers) {
    setDataSet(TYPE_DATASET.TRAIN, filename);

    try {
      kMeans = new SimpleKMeans();
      kMeans.setNumClusters(clusterers);
      kMeans.setDistanceFunction(new EuclideanDistance());
      kMeans.buildClusterer(trainDataset);
    } catch (Exception e) {
      System.out.println("Error");
    }

    return this;
  }

  public KMeanModel predictCluster(String fileIn) {
    setDataSet(TYPE_DATASET.UNLABEL, fileIn);
    unlabelDataset.setClassIndex(unlabelDataset.numAttributes() - 1);

    try {
      for(int i=0; i<unlabelDataset.numInstances(); i++) {
        double predict = kMeans.clusterInstance(unlabelDataset.instance(i));
        unlabelDataset.instance(i).setClassValue(predict);
      }
    } catch(Exception e) {
      System.out.println("Error predict");
    }

    return this;
  }
  
}
