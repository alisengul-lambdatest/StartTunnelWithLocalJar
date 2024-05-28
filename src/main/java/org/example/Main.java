package org.example;

import com.lambdatest.tunnel.Tunnel;
import org.testng.util.TimeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) throws Exception {
    String username = System.getProperty("MAVEN_TUNNEL_USERNAME");
    String access_key = System.getProperty("MAVEN_TUNNEL_ACCESS_KEY");
    Tunnel t = new Tunnel();
    String params = System.getProperty("MAVEN_TUNNEL_PARAMS");
    HashMap<String, String> options = new HashMap<>();
    String tunnelName = System.getenv("MAVEN_TUNNEL_NAME");
    if(tunnelName == null)
    {
      System.out.println("Tunnel name is not in env, not starting maven tunnel");
      return;
    }
    options.put("tunnelName", tunnelName);
    options.put("user", username);
    options.put("key", access_key);
    options.put("basicAuth", "https://admin:admin@the-internet.herokuapp.com");
    if(params != null)
    {
      List<String> paramsList = List.of(params.split(","));
      for (String s : paramsList) {
        String[] keyValue = s.split("=");
        options.put(keyValue[0], keyValue[1]);
      }
    }
    t.start(options);
    try {
      TimeUnit.MINUTES.sleep(30);
    }catch (Exception ignore) {}
  }
}