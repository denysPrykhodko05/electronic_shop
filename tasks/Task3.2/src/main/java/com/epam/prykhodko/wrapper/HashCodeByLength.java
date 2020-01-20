package com.epam.prykhodko.wrapper;

import java.util.Objects;

public class HashCodeByLength {

  private String string;

  public HashCodeByLength(String string) {
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
    HashCodeByLength that = (HashCodeByLength) o;
    return Objects.equals(string, that.string);
  }

  @Override
  public int hashCode() {
    return string.length() + string.charAt(0) + string.charAt(string.length() - 1);
  }

  @Override
  public String toString() {
    return "HashCodeByLength{"
        + "string='" + string + '\''
        + '}';
  }
}
