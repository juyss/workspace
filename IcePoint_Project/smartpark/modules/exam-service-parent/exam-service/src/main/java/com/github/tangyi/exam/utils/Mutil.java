package com.github.tangyi.exam.utils;

import java.math.BigDecimal;

/**
 * @author gaokx
 * @Description
 * @create 2019-11-20 13:09
 **/
public class Mutil {
  /**
   *
   *  * @描述: 加法 <br/>
   *  * @throws  
   */
  public static double add(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.add(b2).doubleValue();
  }

  /**
   *
   *  * @描述: 减法 <br/>
   *  * @throws  
   */
  public static double subtract(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.subtract(b2).doubleValue();
  }

  /**
   *
   *  * @描述: 乘法 <br/>
   *  * @throws  
   */
  public static double multiply(double d1, double d2) {// 进行乘法运算
    BigDecimal b1 = new BigDecimal(d1);
    BigDecimal b2 = new BigDecimal(d2);
    return b1.multiply(b2).doubleValue();
  }

  /**
   *
   *  * @描述: 除法 ，四舍五入<br/>

   *  * @throws  
   */
  public static double divide(double d1, double d2, int len) {// 进行除法运算
    BigDecimal b1 = new BigDecimal(d1);
    BigDecimal b2 = new BigDecimal(d2);

    return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
  }
  /**
   *
    * @描述:  除法，四舍五入取整数 ,例如：5/2=3(2.5四舍五入); 5/3=2(1.6四舍五入);<br/>

    * @throws   <br/>
    
   */
  public static double divide(double d1, double d2) {// 进行除法运算
    BigDecimal b1 = new BigDecimal(d1);
    BigDecimal b2 = new BigDecimal(d2);
    return b1.divide(b2, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  /**
   *
   *  * @描述: 四舍五入 <br/>
   *  * @方法名: round  * @param d <br/>
   *  * @throws <br/>
   *  
   */
  public static double round(double d, int len) {
    BigDecimal b1 = new BigDecimal(d);
    BigDecimal b2 = new BigDecimal(1);
    // 任何一个数字除以1都是原数字
    // ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
    return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
}


}
