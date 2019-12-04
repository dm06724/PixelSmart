package pixelsmart.util;

import java.nio.ByteBuffer;

public class MathUtil {
    public static double clamp(double a, double min, double max) {
        if (a > max)
            return max;
        else if (a < min)
            return min;
        return a;
    }

    public static int clamp(int a, int min, int max) {
        if (a > max)
            return max;
        else if (a < min)
            return min;
        return a;
    }

    public static double map(double v, double a, double b, double c, double d) {
        return (v - a) * (d - c) / (b - a) + c;
    }

    public static int map(int v, int a, int b, int c, int d) {
        return (int) ((v - a) * ((double) (d - c)) / (b - a) + c);
    }

    public static String intToByteString(int a) {
        String k = new String(ByteBuffer.allocate(4).putInt(a).array());
        return k;
    }

    public static int byteStringToInt(String a) {
    	
        int k = 0;
        int v1 = a.charAt(0);
        int v2 = a.charAt(1);
        int v3 = a.charAt(2);
        int v4 = a.charAt(3);
        
        k += (int) ((a.charAt(0) & 0xFF) << 24);
        k += (int) ((a.charAt(1) & 0xFF) << 16);
        k += (int) ((a.charAt(2) & 0xFF) << 8);
        k += (int) ((a.charAt(3) & 0xFF) << 0);

        return k;
    }
}