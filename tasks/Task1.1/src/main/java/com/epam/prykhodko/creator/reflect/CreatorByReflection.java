package com.epam.prykhodko.creator.reflect;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.annotation.Description;
import com.epam.prykhodko.entity.invoke.input.InputType;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.util.convert.TypesUtilImpl;
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

public abstract class CreatorByReflection {

  private static final Logger LOGGER = Logger.getLogger(CreatorByReflection.class);
  private final ResourceBundle resourceBundle;
  protected InputType inputType;
  protected Locale locale;


  public CreatorByReflection(InputType inputType, Locale locale) {
    this.inputType = inputType;
    this.locale = locale;
    resourceBundle = ResourceBundle.getBundle("product", locale);
  }

  public abstract Product create();

  public void setParameters(Product product) {
    final List<Field> fieldList = new ArrayList<>();

    Arrays.stream(product.getClass().getDeclaredFields())
        .filter(e -> e.isAnnotationPresent(Description.class))
        .forEach(fieldList::add);

    if (fieldList.isEmpty()) {
      return;
    }

    final List<Method> methodList = new ArrayList<>(
        Arrays.asList(TypesUtilImpl.class.getDeclaredMethods()));
    boolean correctInput = false;
    while (!correctInput) {
      String value;
      for (Field e : fieldList) {
        try {
          LOGGER.info(resourceBundle.getString(e.getAnnotation(Description.class).title()));
          e.setAccessible(true);
          value = inputType.readLine();
          Optional<Method> optional = methodList
              .stream()
              .filter(t -> t.getReturnType().equals(e.getType()))
              .findFirst();
          if (optional.isPresent()) {
            Method method = optional.get();
            e.set(product, method.invoke(null, value));
            correctInput = true;
          }
        } catch (IllegalAccessException | InvocationTargetException | IOException ex) {
          LOGGER.error(INCORRECT_INPUT);
          correctInput = false;
          break;
        }
      }
    }
    fieldList.clear();
  }
}
