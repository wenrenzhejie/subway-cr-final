package zzu.mxd.utils;

import java.util.Random;

public class MathUtil {

    /**
     * 指定范围产生随机整数
     * @param max
     * @param min
     * @return
     */
    public static int randRange(int max,int min){
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
