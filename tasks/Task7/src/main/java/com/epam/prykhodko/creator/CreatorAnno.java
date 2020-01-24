package com.epam.prykhodko.creator;

import static com.epam.prykhodko.constants.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.strategy.impl.TypesUtilImpl;
import com.epam.prykhodko.task1.annotation.Description;
import com.epam.prykhodko.task1.entity.Product;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public abstract class CreatorAnno {

  protected InputType inputType;
  protected Locale locale;

  public CreatorAnno(InputType inputType, Locale locale) {
    this.inputType = inputType;
    this.locale = locale;
  }

  public abstract Product create();

  public void setParameters(Product product) {
    final Logger logger = Logger.getLogger(ProductCreator.class);
    final List<Field> fieldList = new ArrayList<>();
    final ResourceBundle resourceBundle = ResourceBundle.getBundle("product", locale);
    final List<Method> methodList = new ArrayList<>(
        Arrays.asList(TypesUtilImpl.class.getDeclaredMethods()));
    boolean correctInput = false;

    Arrays.stream(product.getClass().getDeclaredFields())
        .forEach((e) -> {
          if (e.isAnnotationPresent(Description.class)) {
            fieldList.add(e);
          }
        });

    if (fieldList.isEmpty()) {
      correctInput = true;
    }

    while (!correctInput) {
      String value;
      for (Field e : fieldList) {
        try {
          logger.info(resourceBundle.getString(e.getAnnotation(Description.class).title()));
          e.setAccessible(true);
          value = inputType.readLine();
          Optional<Method> optional = methodList
              .stream()
              .filter(t -> t.getReturnType().equals(e.getType()))
              .findFirst();
          Method method = optional.get();

          e.set(product, method.invoke(null, value));
        } catch (IllegalAccessException | InvocationTargetException | IOException ex) {
          logger.error(INCORRECT_INPUT);
          break;
        }
        correctInput = true;
      }
    }
  }
}
