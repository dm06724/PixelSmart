package pixelsmart;

public class MathUtil {
    public static double clamp(double a, double min, double max) {
        if (a > max)
            return max;
        else if (a < min)
            return min;
        return a;
    }
}