package Core;

import java.util.HashMap;

public class Application {
  private String basePath;
  private final HashMap<String, Object> instances = new HashMap<>();
  private final HashMap<String, String> paths = new HashMap<>();

  public Application() {
    bindPath();
    bindServices();
  }

  private void bindPath() {
    this.basePath = System.getProperty("user.dir").replaceAll("\\\\", "/");
    
    paths.put("base", this.basePath);
    paths.put("env", resolvePath(".env"));
  }

  private void bindServices() {
    this.bind("env", new Enviroment(this));
    this.resolve("env");
  }

  public String resolvePath(String path) {
    return this.basePath + "/" + path;
  }

  public String getPath(String key) {
    return this.paths.get(key);
  }

  public void bind(String key, Object value) {
    instances.put(key, value);
  }

  public Object resolve(String key) {
    return this.instances.get(key);
  }

  public Enviroment enviroment() {
    return (Enviroment) this.resolve("env");
  }

}
