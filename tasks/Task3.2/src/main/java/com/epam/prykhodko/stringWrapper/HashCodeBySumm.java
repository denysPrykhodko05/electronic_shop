package com.epam.prykhodko.stringWrapper;

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
    if (string.length() < 4) {
      for (int i = 0; i < string.length(); i++) {
        sum += string.charAt(i);
      }
      return sum;
    }
    return string.charAt(0) + string.charAt(1) + string.charAt(2) + string.charAt(4);
  }

  @Override
  public String toString() {
    return "HashCodeBySumm{" +
        "string='" + string + '\'' +
        '}';
  }
}