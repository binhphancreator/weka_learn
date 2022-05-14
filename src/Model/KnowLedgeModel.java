package Model;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public abstract class KnowledgeModel {
  protected Instances dataset;
  protected Instances trainDataset;
  protected Instances testDataset;
  protected Instances unlabelDataset;

  public static enum TYPE_DATASET {
    TRAIN,
    TEST,
    UNLABEL
  };

  public KnowledgeModel(String filename) {
    this.dataset = loadDataSet(filename);
  }

  public KnowledgeModel() {}

  public DataSource loadDataSource(String sourceFile) {
    try {
      DataSource dataSource = new DataSource(sourceFile);
      return dataSource;
    } catch (Exception e) {
      System.out.println("Error loading datasource");
      return null;
    }
  }

  public Instances loadDataSet(String sourceFile) {
    try {
      Instances instances = loadDataSource(sourceFile).getDataSet();
      return instances;
    } catch (Exception e) {
      System.out.println("Error loading dataset");
      return null;
    }
  }

  public boolean setDataSet(TYPE_DATASET type, String filename) {
    try {
      switch(type) {
        case TRAIN:
          this.trainDataset = loadDataSet(filename);
          break;
        case TEST:
          this.testDataset = loadDataSet(filename);
          break;
        case UNLABEL:
          this.unlabelDataset = loadDataSet(filename);
          break;
        default:
          break;
      }
      return true;
    } catch(Exception e) {
      System.out.println("Error loading dataset");
      return false;
    }
    
  }

  public String toString() {
    return dataset.toSummaryString();
  }

  public Instances getDataSet(TYPE_DATASET type) {
    switch(type) {
      case TRAIN:
        return trainDataset;
      case TEST:
        return testDataset;
      default:
        return unlabelDataset;
    }
  }

}
