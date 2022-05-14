
import Core.Application;
import Model.KMeanModel;
import Model.KNNModel;
import Model.KnowledgeModel.TYPE_DATASET;
import Core.FileSystem;

public class WekaLearn {
    public static void main(String[] args) throws Exception {
        Application app = new Application();
        KNNModel knnModel = new KNNModel();

        knnModel.buildKNN(app.enviroment().env("TRAIN_DATA"))
            .evaluateKNN(app.enviroment().env("TEST_DATA"))
            .predictClassLable(app.enviroment().env("UNLABEL_DATA"));


        String knnOut = app.resolvePath("output/knn.arff");
        FileSystem.writeFile(knnOut, knnModel.getDataSet(TYPE_DATASET.UNLABEL).toString());
        System.out.println("Write file " + knnOut + " successfully");

        KMeanModel kMeanModel = new KMeanModel();
        kMeanModel.buildKMeanModel(app.enviroment().env("TRAIN_DATA"), 3)
            .predictCluster(app.enviroment().env("UNLABEL_DATA"));

        String kmeanOut = app.resolvePath("output/kmean.arff");
        FileSystem.writeFile(kmeanOut, kMeanModel.getDataSet(TYPE_DATASET.UNLABEL).toString());
        System.out.println("Write file " + kmeanOut + " successfully");
        
    }
}
