package task9.com.epam.prykhodko.entity;

import com.epam.prykhodko.entity.products.Product;
import java.io.BufferedWriter;

public interface WriteType {

  void writeProduct(Product product, BufferedWriter bufferedWriter);

  void writeCount(int size, BufferedWriter bufferedWriter);
}
