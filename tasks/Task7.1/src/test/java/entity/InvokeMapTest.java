package entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvokeMapTest {

  Object proxy;

  @BeforeEach
  void init() {
    InvocationHandler handler = new InvokeMap();
    proxy = Proxy.newProxyInstance(ProductInterface.class.getClassLoader(),
        new Class[]{ProductInterface.class}, handler);
  }

  @Test
  void invokeShouldAddElement() {
    ((ProductInterface) proxy).setManufacturer("Apple");
    assertNotNull(((ProductInterface) proxy).getManufacturer());
  }
  @Test
  void invokeShouldThrowUnsupportedOperationException() {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
       proxy.toString();
    });
  }
}