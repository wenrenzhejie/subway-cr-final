package zzu.mxd.utils;

import org.apache.commons.math3.complex.Complex;
import java.util.HashMap;
import java.util.Map;

/**
 * 站姿加权
 */
public class StancePostureWeighting {
    //采样频率：∵10s采集一个数据，∴f=0.1
    static double fs = 2;//TODO fs=8，结果是;fs=2，结果是1568；fs=0.1，结果是0

    /**
     * 坐姿加权的X轴
     * @param inputData
     * @return
     */
    public static double weight_x(double[] inputData){
        //采样个数:例如512，必须是2的次方；
        int N = inputData.length;
        //定义一个数组，大小为N
        double[] n = new double[N];
        //给数组n赋值0~511
        for (int i = 0;i<N;i++){
            n[i] = i;
        }
        double[] fa = new double[N];
        //频率数组
        double[] f = new double[N/2];
        //求频率,f[]是fa[]的前一半
        for (int i = 0;i<N;i++){
            fa[i] = (n[i]*fs)/N;
        }
        for (int i = 0;i<N/2;i++){
            f[i] = fa[i];
        }
        org.apache.commons.math3.complex.Complex[] complexes = ApacheCommonsMathUtil.fft(inputData);//对x轴原数据快速傅里叶变换，得到复数数组
        //对数组内每个复数计算：复数×共轭复数；A[]是Aa[]的前一半
        double[] Aa = new double[N];
        double[] A = new double[N/2];
        for (int i = 0;i<N/2;i++){
            Aa[i] = Math.pow(complexes[i].getReal(),2)+Math.pow(complexes[i].getImaginary(),2);//复数×共轭复数=实部²+虚部²
        }
        //取Aa[]的前一半，赋给A[]
        for (int i = 0;i<N/2;i++){
            A[i] = Aa[i];
        }
        double sum_x = 0;//x轴求和
        //对A[]的每个数进行加权计算，并求和；
        //f所在范围决定了加权系数w；加权过程中，删除f小于0.4的行；
        //0.4~0.6为A1和f1
        for (int i = 0;i<A.length;i++){
            if ((0.4<f[i]) && (f[i]<=0.6)){
                sum_x = sum_x + Math.pow(A[i],2*f[i]);//如果f范围在0.4~0.6，加权系数为2f
            }else if ((0.6<f[i]) && (f[i]<=2)){
                sum_x+=A[i];//如果f范围在0.6~2，加权系数为1
            }else if ((2<f[i]) && (f[i]<=4)){
                sum_x+=Math.pow(A[i],-0.25*f[i]+1.5);//如果f范围在2~4，加权系数为-0.25f+1.5
            }
        }
        return Math.sqrt(sum_x);
    }

    /**
     * 坐姿加权的y轴
     * @param inputData
     * @return
     */
    public static double weight_y(double[] inputData){
        //采样个数:例如512，必须是2的次方；
        int N = inputData.length;
        //定义一个数组，大小为N
        double[] n = new double[N];
        //给数组n赋值0~511
        for (int i = 0;i<N;i++){
            n[i] = i;
        }
        double[] fa = new double[N];
        //频率数组
        double[] f = new double[N/2];
        //求频率,f[]是fa[]的前一半
        for (int i = 0;i<N;i++){
            fa[i] = (n[i]*fs)/N;
        }
        for (int i = 0;i<N/2;i++){
            f[i] = fa[i];
        }
        org.apache.commons.math3.complex.Complex[] complexes = ApacheCommonsMathUtil.fft(inputData);//对x轴原数据快速傅里叶变换，得到复数数组
        //对数组内每个复数计算：复数×共轭复数；A[]是Aa[]的前一半
        double[] Aa = new double[N];
        double[] A = new double[N/2];
        for (int i = 0;i<N/2;i++){
            Aa[i] = Math.pow(complexes[i].getReal(),2)+Math.pow(complexes[i].getImaginary(),2);//复数×共轭复数=实部²+虚部²
        }
        //取Aa[]的前一半，赋给A[]
        for (int i = 0;i<N/2;i++){
            A[i] = Aa[i];
        }
        double sum_y = 0;//y轴求和
        //对A[]的每个数进行加权计算，并求和；
        //f所在范围决定了加权系数w；加权过程中，删除f小于0.4的行；
        //0.4~0.6为A2，其余为A；0.6~2为A3和f2,其余为A
        for (int i = 0;i<A.length;i++){
            if (0.4<f[i] && f[i]<=0.6){
                sum_y+=Math.pow(A[i],2*f[i]);//如果f范围在0.4~0.6，加权系数为2f
            }else if (0.6<f[i] && f[i]<=2){
                sum_y+=A[i];//如果f范围在0.6~2，加权系数为1
            }else if(2<f[i] && f[i]<=4){
                sum_y+=Math.pow(A[i],(-0.25*f[i])+1.5);//如果f范围在2~4，加权系数为-0.25f+1.5
            }
        }
        return sum_y;
    }

    /**
     * 坐姿加权的z轴
     * @param inputData
     * @return
     */
    public static double weight_z(double[] inputData){
        //采样个数:例如512，必须是2的次方；
        int N = inputData.length;
        //定义一个数组，大小为N
        double[] n = new double[N];
        //给数组n赋值0~511
        for (int i = 0;i<N;i++){
            n[i] = i;
        }
        double[] fa = new double[N];
        //频率数组
        double[] f = new double[N/2];
        //求频率,f[]是fa[]的前一半
        for (int i = 0;i<N;i++){
            fa[i] = (n[i]*fs)/N;
        }
        for (int i = 0;i<N/2;i++){
            f[i] = fa[i];
        }
        org.apache.commons.math3.complex.Complex[] complexes = ApacheCommonsMathUtil.fft(inputData);//对x轴原数据快速傅里叶变换，得到复数数组
        //对数组内每个复数计算：复数×共轭复数；A[]是Aa[]的前一半
        double[] Aa = new double[N];
        double[] A = new double[N/2];
        for (int i = 0;i<N/2;i++){
            Aa[i] = Math.pow(complexes[i].getReal(),2)+Math.pow(complexes[i].getImaginary(),2);//复数×共轭复数=实部²+虚部²
        }
        //取Aa[]的前一半，赋给A[]
        for (int i = 0;i<N/2;i++){
            A[i] = Aa[i];
        }
        double sum_z = 0;//y轴求和
        //对A[]的每个数进行加权计算，并求和；
        //f所在范围决定了加权系数w；加权过程中，删除f小于0.4的行；
        //0.4~0.6为A2，其余为A；0.6~2为A3和f2,其余为A
        for (int i = 0;i<A.length;i++){
            if (0.4<f[i] && f[i]<=0.6){
                sum_z+=Math.pow(A[i],0.5*f[i]+0.1);//如果f范围在0.4~0.6，加权系数为0.5f+0.1
            }else if (0.6<f[i] && f[i]<=2){
                sum_z+=A[i];//如果f范围在0.6~2，加权系数为0.4
            }else if(2<f[i] && f[i]<=4){
                sum_z+=Math.pow(A[i],0.2*f[i]);//如果f范围在2~4，加权系数为0.2f
            }
        }
        return sum_z;
    }

    /**
     * 站姿舒适度评价公式
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static double stancePostureWeighting(double x,double y,double z){
        double N = Math.pow((16*Math.pow(x,2)+4*Math.pow(y,2)+Math.pow(z,2)),1/3);//站姿舒适度评价公式得到舒适性指数N
        return N;
    }

    /**
     * 频谱图
     */
    public static Map<Double,Double> spectrumPicture(double[] inputData){
        //采样个数:例如512，必须是2的次方；
        int N = inputData.length;
        //定义一个数组，包含100个值
        double[] n = new double[N];
        //给数组n赋值0~511
        for (int i = 0;i<N;i++){
            n[i] = i;
        }
        //求频率f[]
        double[] f = new double[N];
        for (int i = 0;i<N;i++){
            f[i] = (n[i]*fs)/N;
        }
        Complex[] complexes = ApacheCommonsMathUtil.fft(inputData);//对x轴原数据快速傅里叶变换，得到复数数组
        double[] amplitude = new double[N];
        for (int i = 0;i<complexes.length;i++){
            amplitude[i] = complexes[i].abs();//对复数求模运算，得出振幅
        }
        Map<Double,Double> map = new HashMap<>();
        for (int i = 0;i<amplitude.length;i++){
            map.put(f[i],amplitude[i]);//幅值amplitude与频率f的对应关系
        }
        return map;
    }
}
