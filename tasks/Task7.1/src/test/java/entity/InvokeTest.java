package entity;

import com.epam.prykhodko.task1.entity.Product;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvokeTest {
  Object proxy;
  @BeforeEach
  void proxyInit(){
    InvocationHandler handler = new Invoke(new Product(1, new BigDecimal(10), "Apple"));
    proxy = Proxy.newProxyInstance(ProductInterface.class.getClassLoader(),
        new Class[]{ProductInterface.class}, handler);
  }

  @Test
  void invokeShouldReturnPriceEqualsTen() {

    BigDecimal actual = ((ProductInterface) proxy).getPrice();
    BigDecimal expected = new BigDecimal(10);
    Assertions.assertEquals(expected, actual);
  }
  @Test
  void invokeShouldThrowUnsupportedOperationException() {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      ((ProductInterface) proxy).setId(10);
    });
  }

}