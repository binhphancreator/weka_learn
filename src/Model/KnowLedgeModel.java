package Model;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class KnowLedgeModel {
  protected DataSource source;
  protected Instances dataset;
  public static enum TYPE_DATA_SET {
    TRAIN,
    TEST
  };

  public KnowLedgeModel(String filename) {
    try  {
      this.source = new DataSource(filename);
      this.dataset = this.source.getDataSet();
    } catch (Exception e) {
      System.out.println("Error reading dataset");
    }
  }

  public void setDataSet(TYPE_DATA_SET type, String filename) {
    switch(type) {
      case TRAIN:
        break;
      case TEST:
        break;
      default:
        break;
    }
  }

  public String toString() {
    return dataset.toSummaryString();
  }
}
