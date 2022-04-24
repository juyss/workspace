package com.github.tangyi.gateway.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gaokx
 * @Description
 * @create 2021-03-10 20:25
 **/
public class SensitiveWord {
  public static volatile SensitiveWord sensitiveWord = null;

  private StringBuilder replaceAll;
  private String encoding = "UTF-8";
  private String replaceStr = "*";
  private int replaceSize = 500;
  private String fileName = "CensorWords.txt";
  @Setter
  @Getter
  private List<String> arrayList;

  public static SensitiveWord getInstance() {
    if (sensitiveWord == null) {
      synchronized (SensitiveWord.class) {
        if (sensitiveWord == null) {
          sensitiveWord = new SensitiveWord();
        }
      }
    }
    return sensitiveWord;
  }

  private SensitiveWord() {
    InitializationWork();
  }

  /**
   * 文件要求路径在src或resource下，默认文件名为CensorWords.txt
   *
   * @param fileName 词库文件名(含后缀)
   */
  public SensitiveWord(String fileName) {
    this.fileName = fileName;
  }


  /**
   * @param str 将要被过滤信息
   * @return 过滤后的信息
   */
  public String getSensitiveWordStr(String str) {
    StringBuilder buffer = new StringBuilder(str);
    HashMap<Integer, Integer> hash = new HashMap<>(arrayList.size());
    String temp;
    for (int x = 0; x < arrayList.size(); x++) {
      temp = arrayList.get(x);
      int findIndexSize = 0;
      for (int start = -1; (start = buffer.indexOf(temp, findIndexSize)) > -1; ) {
        //从已找到的后面开始找
        findIndexSize = start + temp.length();
        //起始位置
        Integer mapStart = hash.get(start);
        //满足1个，即可更新map
        if (mapStart == null || (mapStart != null && findIndexSize > mapStart)) {
          hash.put(start, findIndexSize);
          break;
        }
      }
    }
    Collection<Integer> values = hash.keySet();
    for (Integer startIndex : values) {
      Integer endIndex = hash.get(startIndex);
      //获取敏感词，找到后就返回
      String sensitive = buffer.substring(startIndex, endIndex);
      return sensitive;
    }
    hash.clear();
    return "";
  }

  /**
   * 初始化敏感词库
   */
  public void InitializationWork() {
    replaceAll = new StringBuilder(replaceSize);
    for (int x = 0; x < replaceSize; x++) {
      replaceAll.append(replaceStr);
    }
    //加载词库
    arrayList = new ArrayList<>();
    InputStreamReader read = null;
    BufferedReader bufferedReader = null;
    try {
      read = new InputStreamReader(
          SensitiveWord.class.getClassLoader().getResourceAsStream(fileName), encoding);
      bufferedReader = new BufferedReader(read);
      for (String txt = null; (txt = bufferedReader.readLine()) != null; ) {
        if (!arrayList.contains(txt)) {
          arrayList.add(txt);
        }
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (null != bufferedReader) {
          bufferedReader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        if (null != read) {
          read.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    long startNumer = System.currentTimeMillis();
    SensitiveWord sw = new SensitiveWord("CensorWords.txt");
    sw.InitializationWork();
    String str1 = sw.getSensitiveWordStr("22222222222");
    long endNumber = System.currentTimeMillis();
    System.out.println("总共耗时:" + (endNumber - startNumer) + "ms");
    System.out.println("替换后的字符串为:\n" + str1);
  }
}
