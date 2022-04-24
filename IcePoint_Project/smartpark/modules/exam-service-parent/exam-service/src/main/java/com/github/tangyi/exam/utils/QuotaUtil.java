package com.github.tangyi.exam.utils;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gaokx
 * @Description
 * @create 2018-10-17 11:34
 * 专业考试各项指标计算
 **/
public class QuotaUtil {

    /**
     * 百分位计算
     *
     * @param data 数据
     * @param quartile    具体的百分位 比如%25 则输入0.25
     * @return
     */
    public static double getQuartile(Double[] data, double quartile) {
        int len = data.length;
        if (len < 1) {
            return 0;
        }
        List<Double> list = Arrays.asList(data);
        return getQuartile(list, quartile);
    }

    /**
     * 中位数
     */
    static  double HALF_QUARTILE = 0.5;

    /**
     * 百分位计算
     *
     * @param list 数据
     * @param quartile    具体的百分位 比如%25 则输入0.25
     * @return
     */
    public  static double getQuartile(List<Double> list,double quartile) {
        int len = list.size();
        if (len < 1) {
            return 0;
        }
        List<Double> sList = list.parallelStream().sorted().collect(Collectors.toList());
        if (quartile == HALF_QUARTILE) {
            int index = len >> 1;
            if ((len &1) == 0) {
                return (sList.get(index == 0 ? 0 : (index - 1)) + sList.get(len >>1)) / 2;
            } else {
                return sList.get(index);
            }
        } else {
            int index = (int) Math.rint(len * quartile);
            return sList.get(index == 0 ? 0 : (index - 1));
        }
    }

    /**
     * 偏度
     *
     * @param list
     * @return
     */
    public static double skewness(List<Double> list) {
        if (list == null || list.size() <= 0) {
            return 0;
        }
        int n = list.size();
        double sum = 0;
        double sum2 = 0;
        for (int i = 0; i < list.size(); i++) {
            double d = list.get(i);
            sum += d;
            sum2 += d * d;
        }
        double avg = sum / n;
        double std = (Math.sqrt(n * sum2 - sum * sum)) / n;
        if (std == 0) {
            return 0;
        }
        double sum3 = 0;
        for (int i = 0; i < list.size(); i++) {
            double d = list.get(i);
            double dd = (d - avg) / std;
            sum3 += dd * dd * dd;
        }
        double re = sum3 / n;
        return re;
    }

    /**
     * 峰度
     *
     * @param list
     * @return
     */
    public static double kurtosis(List<Double> list) {
        if (list == null || list.size() <= 0) {
            return 0;
        }
        //return  kurtosis(ConvertUtils.ListTodouble(list));

        int n = list.size();
        double sum = 0;
        double sum2 = 0;
        for (int i = 0; i < list.size(); i++) {
            double d = list.get(i);
            sum += d;
            sum2 += d * d;
        }
        double avg = sum / n;
        double std = (Math.sqrt(n * sum2 - sum * sum)) / n;
        if (std == 0) {
            return 0;
        }
        double sum3 = 0;
        for (int i = 0; i < list.size(); i++) {
            double d = list.get(i);
            double dd = (d - avg) / std;
            sum3 += dd * dd * dd * dd;
        }
        double re = sum3 / n - 3;
        return re;
    }

    /**
     * 集中趋势量数：计算中位数
     * @param in
     * @return
     */
    public static double median(double[] in) {
        if (in == null) {
            return 0;
        }
        Arrays.sort(in);

        // for (int i = 0; i < in.length; i++) {
        // log.debug("sort: "+i+":::"+in[i]);
        // }
        if (in.length % 2 == 1) {
            return in[(int) Math.floor(in.length / 2)];
        } else {
            double[] avg = new double[2];
            avg[0] = in[(int) Math.floor(in.length / 2) - 1];
            avg[1] = in[(int) Math.floor(in.length / 2)];
            return mean(avg);

        }
    }

    /**
     * 计算众数
     * @param in
     * @return
     */
    public static List mode(double[] in) {
        if (in == null) {
            return Lists.newArrayList();
        }
        HashMap map = new HashMap();
        double imode = 0;
        for (int i = 0; i < in.length; i++) {
            double x = in[i];
            if (map.containsKey(String.valueOf(x))) {
                // 如果出现多次，取出以前的计数，然后加1
                int len = Integer.parseInt(map.get(String.valueOf(x)).toString());
                map.put(String.valueOf(x), String.valueOf(len + 1));
                imode = Math.max(imode, len + 1);
            } else {
                // 如果是第一次出现，计数为1
                map.put(String.valueOf(x), String.valueOf(1));
                imode = Math.max(imode, 1);
            }
        }
        Iterator iter = map.keySet().iterator();
        ArrayList lst = new ArrayList();
        while (iter.hasNext()) {
            Object key = iter.next();
            Object v = map.get(key);
            if (Integer.parseInt(v.toString()) == imode) {
                lst.add(key);
            }
        }
        return lst;
    }

    /**
     *  均值的计算公式：\bar{X} = \frac{\sum X}{n}
     *
     * 公式说明：“X把”表示数据组的平均数或者均值；∑都懂，X表示具体的数值；n表示样本规模
     *
     *  * @描述:集中趋势量数：均值/算术平均数（arithmetic mean) <br/>
     *  
     */
    public static double mean(double[] in) {
        if (in == null) {
            throw new NumberFormatException();
        }
        if (in.length == 1) {
            return in[0];
        }
        double sum = 0;
        for (int i = 0; i < in.length; i++) {
            sum = Mutil.add(sum, in[i]);
            // sum += in[i];
        }
        // return sum/in.length;
        return Mutil.divide(sum, in.length, 2);
    }

    /**
     * 计算极差(不包含)
     * @param in
     * @return
     */
    public static double range(double[] in) {
        if (in == null) {
            throw new NumberFormatException();
        }
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < in.length; i++) {
            max = Math.max(max, in[i]);
            min = Math.min(min, in[i]);
        }
        // return max - min;
        return Mutil.subtract(max, min);
    }

    /**
     * 计算极差(包含)
     * @param in
     * @return
     */
    public static double range2(double[] in) {
        if (in == null) {
            throw new NumberFormatException();
        }
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < in.length; i++) {
            max = Math.max(max, in[i]);
            min = Math.min(min, in[i]);
        }
        // return max - min + 1;
        return Mutil.subtract(max, min) + 1;
    }

    /**
     * 峰度
     * @param in
     * @return
     */
    public static double kurtosis(double[] in) {
        double mean = mean(in);
        double SD = standardDeviation(in);
        int n = in.length;
        double sum = 0;
        for (int i = 0; i < in.length; i++) {
            sum = Mutil.add(sum, Math.pow(Mutil.divide(Mutil.subtract(in[i], mean), SD, 2), 4));
        }
        return Mutil.round(Mutil.divide(sum, n, 2) - 3, 2);
    }




    /**
     * 变异性量数：方差
     * @param in
     * @return
     */
    public static double variance(double[] in) {
        double t_mean = mean(in);
        double t_sumPerPow = 0;
        for (int i = 0; i < in.length; i++) {
            t_sumPerPow = Mutil.add(t_sumPerPow, Math.pow(Mutil.subtract(in[i], t_mean), 2));
        }
        return Mutil.divide(t_sumPerPow, (in.length - 1), 2);
    }

    /**
     * 变异性量数：标准差（无偏估计）
     * @param in
     * @return
     */
    public static double standardDeviation(double[] in) {
        return Math.sqrt(variance(in));
    }

    /**
     * 变异性量数：标准差（有偏估计）
     * @param in
     * @return
     */
    public static double standardDeviation2(double[] in) {
        double t_mean = mean(in);
        double t_sumPerPow = 0;
        for (int i = 0; i < in.length; i++) {
            t_sumPerPow = Mutil.add(t_sumPerPow, Math.pow(Mutil.subtract(in[i], t_mean), 2));
        }
        return Math.sqrt(Mutil.divide(t_sumPerPow, (in.length), 2));
    }


    /**
     * 求众数,返回多个众数
     * @param nums
     * @return
     */
    public  static  List<Double> getMode(List<Double> nums) {
        //记录每个数字出现的次数
        Map<Double, Integer> map1 = new HashMap<>();
        //记录每个次数下对应的数字
        Map<Integer, Set> map2 = new HashMap<>();
        int n = nums.size();
        //统计每个元素出现的次数
        for (double num : nums)
        {
            Integer count = map1.get(num);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            map1.put(num, count);
            //如果大于长度的一半直接返回
            if (map1.get(num) > n >>1) {
                List rList = new ArrayList(1);
                rList.add(num);
                return rList;
            }
            Set set = map2.get(count);
            int lCount = count - 1;
            //移除之前的次数记录下的此数字
            if(map2.containsKey(lCount)){
                map2.get(lCount).remove(num);
            }
            if (set != null) {
                set.add(num);
            }else {
                set = new HashSet();
                set.add(num);
                map2.put(count,set);
            }
        }
        if(map2.size()>0){
            //直接构造函数即可
            List<Integer> list = new ArrayList(map2.keySet());
            int size = list.size();
            Integer max = list.parallelStream().sorted().collect(Collectors.toList()).get(size-1);
            Iterator iterator = map2.get(max).iterator() ;
            List res = new ArrayList(map2.get(max).size());
            while(iterator.hasNext()){
                Double value = (Double)iterator.next();
                if(Math.round(value) == value){
                    res.add(value.intValue());
                }else {
                    res.add(String.format("%.1f", value));
                }
            }
            //返回最大的数字组
            return res;
        }
        return Lists.newArrayList();
    }


    public static void main(String[] args) {
        List<Double> list=Lists.newArrayList();
        list.add(70.00);
        list.add(74.00);
        list.add(72.00);
        list.add(70.00);
        list.add(66.00);
        list.add(48.00);
        list.add(57.00);
        list.add(69.00);
        list.add(78.00);
        list.add(71.00);
        list.add(45.00);
        list.add(73.00);
        list.add(77.00);
        list.add(62.00);
        list.add(62.00);
        list.add(63.00);
        list.add(71.00);
        list.add(87.00);
        list.add(80.00);
        list.add(74.00);
        list.add(78.00);
        list.add(52.00);
        list.add(74.00);
        list.add(80.00);
        list.add(80.00);
        list.add(80.00);
        list.add(67.00);
        list.add(59.00);
        list.add(71.00);
        list.add(0.00);
        list.add(58.00);
        System.out.println("峰度:"+ QuotaUtil.kurtosis(list));
        System.out.println("偏度:"+ QuotaUtil.skewness(list));
    }
}
