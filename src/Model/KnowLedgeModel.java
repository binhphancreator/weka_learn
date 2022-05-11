package Model;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class KnowLedgeModel {
  protected Instances dataset;
  protected Instances trainDataset;
  protected Instances testDataset;

  public static enum TYPE_DATASET {
    TRAIN,
    TEST
  };

  public KnowLedgeModel(String filename) {
    this.dataset = loadDataSet(filename);
  }

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

}
