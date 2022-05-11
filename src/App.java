
import Model.KNNModel;

public class App {
    public static void main(String[] args) throws Exception {
        KNNModel model = new KNNModel("C:/Project/weka_learn/data/train_iris.arff");
        model.buildKNN("C:/Project/weka_learn/data/train_iris.arff");
        model.evaluateKNN("C:/Project/weka_learn/data/test_iris.arff");
        model.predictClassLable("C:/Project/weka_learn/data/unlable_iris.arff", "");
    }
}
