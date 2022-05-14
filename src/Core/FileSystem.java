package Core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystem {
  public static boolean writeFile(String filename, String content) {
    try {
      BufferedWriter bWriter = new BufferedWriter(new FileWriter(filename));
      bWriter.write(content);
      bWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
