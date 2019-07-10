package zzu.mxd.utils;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class ApacheCommonsMathUtil {

    public static Complex[] fft(double[] inputData){
        //创建傅里叶方法实例
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] result = fft.transform(inputData, TransformType.FORWARD);
        //将傅里叶变换前数据和变换后数据打印出来
        /*for(int i=0;i<inputData.length;i++){
            System.out.print(i+"个换前为："+inputData[i]+"\t");
            System.out.println(i+"个换后为："+result[i]);
        }*/
        return result;
    }

    public static void main(String[] args){
        //定义输入数据类型，并初始化
        double[] inputData = null;
        //定义数组长度
        int arrayLength = 4 * 1024;
        inputData = new double[arrayLength];
        for (int index = 0; index < inputData.length; index++){
            //将经过运算的随机数赋值给inputdata
            inputData[index] = (Math.random() - 0.5) * 100.0;
        }
        System.out.println("初始化完成");
    }
}
