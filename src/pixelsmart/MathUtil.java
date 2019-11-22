package pixelsmart;

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
        String k = "";
        k += (char) ((a >> 24) & 0xFF);
        k += (char) ((a >> 16) & 0xFF);
        k += (char) ((a >> 8) & 0xFF);
        k += (char) ((a >> 0) & 0xFF);

        return k;
    }

    public static int byteStringToInt(String a) {
        int k = 0;
        k += (int) (a.charAt(0) << 24);
        k += (int) (a.charAt(1) << 16);
        k += (int) (a.charAt(2) << 8);
        k += (int) (a.charAt(3) << 0);

        return k;
    }
}