
import Core.Application;
import Model.KNNModel;

public class WekaLearn {
    public static void main(String[] args) throws Exception {
        Application app = new Application();
        KNNModel model = new KNNModel(app.enviroment().env("TRAIN_DATA"));
        model.buildKNN(app.enviroment().env("TRAIN_DATA"));
        model.evaluateKNN(app.enviroment().env("TEST_DATA"));
        model.predictClassLable(app.enviroment().env("UNLABEL_DATA"), "");
    }
}
