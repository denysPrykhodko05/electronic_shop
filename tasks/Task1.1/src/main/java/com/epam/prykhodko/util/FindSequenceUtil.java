package com.epam.prykhodko.util;

public class FindSequenceUtil {

  private final String content;
  private StringBuilder mainSB = new StringBuilder();


  public FindSequenceUtil(String content) {
    this.content = content;
  }


  public void findSequence() {
    char[] buffer = content.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < buffer.length; i++) {
      sb.append(buffer[i]);
      for (int j = i; j < buffer.length; j++) {
        if (j != i) {
          sb.append(buffer[j]);
        }
        if (!hasDuplicate(sb.toString().toCharArray(), j)) {
          sb.deleteCharAt(sb.length() - 1);
          break;
        }
      }
      if (sb.length() > mainSB.length()) {
        mainSB = sb;
      }
      sb = new StringBuilder();
    }
  }

  private boolean hasDuplicate(char[] buffer, int i) {
    String sequence = String.valueOf(buffer);
    String subSequence = content.subSequence(i + 1, content.length()).toString();
    return subSequence.contains(sequence);
  }

  public String getSequence(){
    return mainSB.toString();
  }

}
