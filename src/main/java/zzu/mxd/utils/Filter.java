package zzu.mxd.utils;

public class Filter {
    private static double rate = 0.000000001;
    private static Double[] audha = {
            1.0,
            -1.98388104166084,
            0.984009917549517
    };
    private static Double[] audhb = {
            0.991972739802589,
            -1.98394547960518,
            0.991972739802589
    };
    private static Double[] audla = {
            1.0,
            4.4841,
            9.2881,
            11.4399,
            9.0940,
            4.7529,
            1.5889 ,
            0.3097,
            0.0269
    };
    private static Double[] audlb = {
            0.1640*rate,
            1.3120*rate*10,
            4.5920*rate*10,
            9.1841*rate*100,
            11.4801*rate*100,
            9.1841*rate*100,
            4.5920*rate*10,
            1.3120*rate*10,
            0.1640*rate
    };

    private static Double[] in;
    private static Double[] out;
    private static Double[] outData;

    public static Double[] highpass(Double[] signal) {
        return filter(signal, audha, audhb);
    }

    public static Double[] lowpass(Double[] signal) {
        return filter(signal, audla, audlb);
    }

    private static Double[] filter(Double[] signal, Double[] a, Double[] b) {
        in = new Double[b.length];
        out = new Double[a.length - 1];
        outData = new Double[signal.length];
        for (int i = 0; i < signal.length; i++) {
            System.arraycopy(in, 0, in, 1, in.length - 1);  //in[1]=in[0],in[2]=in[1]...
            in[0] = signal[i];
            //calculate y based on a and b coefficients
            //and in and out.
            Double y = 0.0;
            for (int j = 0; j < b.length; j++) {
                if (in[j] != null) {
                    y += b[j] * in[j];
                }
            }


            for (int j = 0; j < a.length - 1; j++) {
                if (out[j] != null) {
                    y -= a[j + 1] * out[j];
                }
            }

            //shift the out array
            System.arraycopy(out, 0, out, 1, out.length - 1);
            out[0] = y;
            outData[i] = y;
        }
        return outData;
    }
}
