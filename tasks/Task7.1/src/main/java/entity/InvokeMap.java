package entity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InvokeMap implements InvocationHandler {

  private final Map<String, Object> productMap = new HashMap<>();

  @Override
  public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
    String field = method.getName().substring(3);

    if (method.getName().startsWith("set")) {
      return productMap.put(field.toLowerCase(), objects[0]);
    }
    if (method.getName().startsWith("get")) {
      return productMap.get(field.toLowerCase());
    }
    throw new UnsupportedOperationException();
  }
}
