package zzu.mxd.utils;

import org.apache.commons.math3.complex.Complex;
import zzu.mxd.subway.entity.Amplitude;
import java.util.ArrayList;
import java.util.List;

/**
 * 坐姿加权
 */
public class SittingPostureWeighting {
    //采样频率：fs=0.1,意思是每10s采集一次数据
    static double fs = 6.4;//TODO fs=8，参数范围是0.4~0.6~2~4；fs=6.4参数范围是0.32~0.48~1.6~3.2；fs=0.25参数范围是0.0125~0.01875~0.0625~0.125

    /**
     * 坐姿加权的X轴
     * @param inputData
     * @return
     */
    public static double weight_x(double[] inputData){

        //采样个数:必须是2的次方，例如32
        int N = inputData.length;
        double[] n = new double[N];
        for (int i = 0;i<N;i++){
            n[i] = i;
        }
        //求频率f
        double[] fa = new double[N];
        double[] f = new double[N/2];
        for (int i = 0;i<N;i++){
            fa[i] = (n[i]*fs)/N;//频率f与采样频率fs成正比：采样频率变化时，加权标准里的频率范围正比变化
        }
        for (int i = 0;i<N/2;i++){
            f[i] = fa[i];
        }
        //对x轴原数据快速傅里叶变换，得到复数数组
        Complex[] complexes = ApacheCommonsMathUtil.fft(inputData);
        //对数组内每个复数计算：复数×共轭复数；A[]是Aa[]的前一半
        double[] Aa = new double[N];
        double[] A = new double[N/2];
        for (int i = 0;i<N/2;i++){
            Aa[i] = Math.pow(complexes[i].getReal(),2)+Math.pow(complexes[i].getImaginary(),2);//复数×共轭复数=实部²+虚部²
        }
        for (int i = 0;i<N/2;i++){
            A[i] = Aa[i];//取Aa[]的前一半，赋给A[]

        }
        double sum_x = 0;//x轴求和
        //对A[]的每个数进行加权计算，并求和；
        //f所在范围决定了加权系数w；加权过程中，删除f小于0.4的行；
        for (int i = 0;i<A.length;i++){
            if ((0.32<f[i]) && (f[i]<=0.48)){
                sum_x = sum_x + Math.pow(A[i],2*f[i]);//0.4~0.6,加权系数为2f
                System.out.println(sum_x);
            }else if ((0.48<f[i]) && (f[i]<=3.2)){
                sum_x+=A[i];//0.6~4,加权系数为1
                System.out.println(sum_x);
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
        int N = inputData.length;
        double[] n = new double[N];
        for (int i = 0;i<N;i++){
            n[i] = i;
        }
        //求频率f
        double[] fa = new double[N];
        double[] f = new double[N/2];
        for (int i = 0;i<N;i++){
            fa[i] = (n[i]*fs)/N;
        }
        for (int i = 0;i<N/2;i++){
            f[i] = fa[i];
        }
        //对y轴原数据快速傅里叶变换，得到复数数组
        Complex[] complexes = ApacheCommonsMathUtil.fft(inputData);
        //对数组内每个复数计算：复数×共轭复数；A[]是Aa[]的前一半
        double[] Aa = new double[N];
        double[] A = new double[N/2];
        for (int i = 0;i<N/2;i++){
            Aa[i] = Math.pow(complexes[i].getReal(),2)+Math.pow(complexes[i].getImaginary(),2);//复数×共轭复数=实部²+虚部²
        }
        for (int i = 0;i<N/2;i++){
            A[i] = Aa[i];
        }
        double sum_y = 0;//y轴求和
        //对A[]的每个数进行加权计算，并求和；
        //f所在范围决定了加权系数w；加权过程中，删除f小于0.4的行；
        for (int i = 0;i<A.length;i++){
            if (0.32<f[i] && f[i]<=0.48){
                sum_y+=Math.pow(A[i],2*f[i]);//0.4~0.6,加权系数为2f
            }else if (0.48<f[i] && f[i]<=1.6){
                sum_y+=A[i];//0.6~2,加权系数为1
            }else if(1.6<f[i] && f[i]<=3.2){
                sum_y+=Math.pow(A[i],(-0.25*f[i])+1);//2~4,加权系数为-0.25f+1
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
        int N = inputData.length;
        double[] n = new double[N];
        for (int i = 0;i<N;i++){
            n[i] = i;
        }
        //求频率f
        double[] fa = new double[N];
        double[] f = new double[N/2];
        for (int i = 0;i<N;i++){
            fa[i] = (n[i]*fs)/N;
        }
        for (int i = 0;i<N/2;i++){
            f[i] = fa[i];
        }
        //对z轴原数据快速傅里叶变换，得到复数数组
        Complex[] complexes = ApacheCommonsMathUtil.fft(inputData);
        //对数组内每个复数计算：复数×共轭复数；A[]是Aa[]的前一半
        double[] Aa = new double[N];
        double[] A = new double[N/2];
        for (int i = 0;i<N/2;i++){
            Aa[i] = Math.pow(complexes[i].getReal(),2)+Math.pow(complexes[i].getImaginary(),2);//复数×共轭复数=实部²+虚部²
        }
        for (int i = 0;i<N/2;i++){
            A[i] = Aa[i];
        }
        double sum_z = 0;//y轴求和
        //对A[]的每个数进行加权计算，并求和；
        //f所在范围决定了加权系数w；加权过程中，删除f小于0.4的行；
        for (int i = 0;i<A.length;i++){
            if (0.32<f[i] && f[i]<=0.48){
                sum_z+=Math.pow(A[i],0.5*f[i]+0.1);//0.4~0.6，加权系数为0.5f+0.1
            }else if (0.48<f[i] && f[i]<=1.6){
                sum_z+=A[i];//0.6~2，加权系数为0.4
            }else if(1.6<f[i] && f[i]<=3.2){
                sum_z+=Math.pow(A[i],0.2*f[i]);//2~4，加权系数为0.2f
            }
        }
        return sum_z;
    }

    /**
     * 坐姿舒适度评价公式
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static double sitPostureWeighting(double x,double y,double z){
        double N = 4*Math.pow(z,2)+2*Math.sqrt(Math.pow(y,2)+Math.pow(z,2))+4*x;//坐姿舒适度评价公式得到舒适性指数N
        return N;
    }

    /**
     * 频谱图
     */
    public static List<Amplitude> spectrumPicture(double[] inputData){
        //采样个数:例如64，必须是2的次方；
        int N = inputData.length;
        //定义一个数组，个数为N
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
        List<Amplitude> amplitudes = new ArrayList<>();
        for (int i = 0;i<amplitude.length;i++){
            amplitudes.add(new Amplitude(f[i],amplitude[i]));//幅值amplitude与频率f的对应关系
        }
        return amplitudes;
    }
}
