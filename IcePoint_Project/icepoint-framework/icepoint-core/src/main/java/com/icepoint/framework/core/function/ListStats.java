package com.icepoint.framework.core.function;

import com.icepoint.framework.core.support.ReadWriteProperties;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.Streams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.DoubleStream;

public class ListStats extends AbstractProcessor<ListStats.Parameters, Map<String, Object>> {
//    public static final int Max = 0x00000001;
//    public static final int AVG = 0x00000002;
//    public static final int MIN = 0x00000004;
//    public static final int SUM = 0x00000008;
//    public static final int DIF = 0x00000010;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Parameters {
        /**
         * 对象列表
         */
        private @Nullable List objList;
        /**
         * 统计字段
         */
        private @Nullable String field;
        /**
         * 以最大值、最小值、平均值、中位数、众数、Alpha、极差、标准差为顺序
         */
        private int statType;
    }


    @Override
    public Map<String, Object> processInternal(Parameters parameters) {
    	List objList = parameters.getObjList();
        int statType = parameters.getStatType();
        String field = parameters.getField();

        Map<String, Object> map = new HashMap<>();
        if (ObjectUtils.isEmpty(objList) || ObjectUtils.isEmpty(field)) {
            return Collections.singletonMap("result", "null");
        }
        //获取对象所有的字段
        Class<?> objClass = objList.iterator().next().getClass();
        ReadWriteProperties<?> properties = ReadWriteProperties.of(objClass);
        //获取流
        DoubleStream doubleStream = null;
        doubleStream = Streams.stream(objList)
                .mapToDouble(f -> Double.parseDouble(properties.readProperty(CastUtils.cast(f), field).toString()));
        //求最大值;
        OptionalDouble max = doubleStream.max();
        if (max.isPresent()) {
            map.put("max", max.getAsDouble());
        } else {
            map.put("max", "");
        }
        doubleStream = Streams.stream(objList)
                .mapToDouble(f -> Double.parseDouble(properties.readProperty(CastUtils.cast(f), field).toString()));
        //最小值
        OptionalDouble min = doubleStream.min();
        if (min.isPresent()) {
            map.put("min", min.getAsDouble());
        } else {
            map.put("min", "");
        }
        //平均值
        doubleStream = Streams.stream(objList)
                .mapToDouble(f -> Double.parseDouble(properties.readProperty(CastUtils.cast(f), field).toString()));
        OptionalDouble average = doubleStream.average();
        if (average.isPresent()) {
            map.put("average", average.getAsDouble());
        } else {
            map.put("average", "");
        }
        //求中位数
        doubleStream = Streams.stream(objList)
                .mapToDouble(f -> Double.parseDouble(properties.readProperty(CastUtils.cast(f), field).toString()));
        double[] doubles = doubleStream.sorted().toArray();
        if (doubles.length % 2 == 1) {
            map.put("median", doubles[((doubles.length - 1) / 2)]);
        } else {
            map.put("median", (doubles[(doubles.length / 2 - 1)] + doubles[(doubles.length / 2)] + 0.0) / 2);
        }
        //求和
        doubleStream = Streams.stream(objList)
                .mapToDouble(f -> Double.parseDouble(properties.readProperty(CastUtils.cast(f), field).toString()));
        double sum = doubleStream.sum();
        map.put("sum", sum);
        //众数
        double mode = majorityElement(doubles);
        map.put("mode", mode);
        double standard = standardDiviation(doubles, average.getAsDouble());
        map.put("standard", standard);
        return map;

    }


    @Override
    protected Map<String, Object> wrapResult(Map<String, Object> result) {
        return result;
    }

    public double majorityElement(double[] nums) {
        Arrays.sort(nums);
        double mode = 0;
        for (int i = 0; i < nums.length; ) {
            int count = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                } else {
                    break;
                }
            }
            if (count > nums.length / 2) {
                mode = nums[i];
                break;
            }
            i += count;
        }
        return mode;
    }

    /**
     * 传入一个数列x计算标准差
     * 标准差σ=sqrt(s^2)，即标准差=方差的平方根
     *
     * @param x 要计算的数列
     * @return 标准差
     */
    public static double standardDiviation(double[] x, double avg) {
        return Math.sqrt(variance(x, avg));
    }


    /**
     * 传入一个数列x计算方差
     * 方差s^2=[（x1-x）^2+（x2-x）^2+......（xn-x）^2]/（n）（x为平均数）
     *
     * @param x 要计算的数列
     * @return 方差
     */
    public static double variance(double[] x, double avg) {
        int n = x.length;            //数列元素个数
        double var = 0;
        for (double i : x) {
            var += (i - avg) * (i - avg);    //（x1-x）^2+（x2-x）^2+......（xn-x）^2
        }
        return var / n;
    }

}

