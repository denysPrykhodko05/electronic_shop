package com.epam.prykhodko.creator;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Telephone;
import java.util.Locale;

public class TelephoneCreator extends ProductCreator {

  public TelephoneCreator(InputType inputType, Locale locale) {
    super(inputType, locale);
  }

  @Override
  public Product create() {
    final Telephone telephone = new Telephone(super.create());
    setParameters(telephone);
    return telephone;
  }

 /* private void setParameters(Telephone telephone) {
    boolean correctInput = false;
    methodList.addAll(Arrays.asList(TypesUtilImpl.class.getDeclaredMethods()));
    ResourceBundle resourceBundle = ResourceBundle.getBundle("product", locale);

    Arrays.stream(Telephone.class.getDeclaredFields())
        .forEach((e) -> {
          if (e.isAnnotationPresent(Description.class)) {
            fieldList.add(e);
          }
        });

    while (!correctInput) {
      String value;
      for (Field e : fieldList) {
        LOGGER.info(resourceBundle.getString(e.getAnnotation(Description.class).title()));
        e.setAccessible(true);
        value = ConsoleScanner.readLine();
        Optional<Method> optional = methodList
            .stream()
            .filter(t -> t.getReturnType().equals(e.getType()))
            .findFirst();
        Method method = optional.get();
        try {
          e.set(telephone, method.invoke(null, value));
        } catch (IllegalAccessException | InvocationTargetException ex) {
          LOGGER.error(METHOD_NOT_FOUND);
          break;
        }
        correctInput = true;
      }
    }
  }*/
}
