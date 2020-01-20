package com.epam.prykhodko.wrapper;

import java.util.Objects;

public class HashCodeBySumm {

  private String string;

  public HashCodeBySumm(String string) {
    this.string = string;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HashCodeBySumm that = (HashCodeBySumm) o;
    return Objects.equals(string, that.string);
  }

  @Override
  public int hashCode() {
    int sum = 0;
    for (int i = 0; i < string.length(); i++) {
      if (i == 4) {
        break;
      }
      sum += string.charAt(i);
    }
    return sum;
  }

  @Override
  public String toString() {
    return "HashCodeBySumm{"
            + "string='" + string + '\''
            + '}';
  }
}