import java.util.Arrays;
/**
 * Klasse, welche die Schnittmenge der Einträge von zwei Arrays berechnet.
 */
public class ArrayIntersect {


    public static int[] arrayIntersection(int[] a, int[] b) {
        if (a.length == 0 || b.length == 0) {
            return new int[0];
        }
        return arrayIntersectionHelper(a, b, 0, new int[0]);
    }

    public static int[] arrayIntersectionHelper(int[] a, int [] b, int i, int[] org) {
        if (a.length <= 0) {
            return org;
        }

        if (i < b.length) {
            if (a[0] == b[i]) {
                int[] res = Arrays.copyOf(org, org.length + 1);
                res[org.length] = a[0];
                return arrayIntersectionHelper(Arrays.copyOfRange(a, 1, a.length), b, 0, res);
            } else {
                return arrayIntersectionHelper(a, b, i + 1, org);
            }
        } else {
            return arrayIntersectionHelper(Arrays.copyOfRange(a, 1, a.length), b, 0, org);
        }
    }

    public static int[] sortedArrayIntersection(int[] a, int[] b) {
        if (a.length == 0 || b.length == 0) {
            return new int[0];
        }
        return sortedArrayIntersectionHelper(a, b, new int[0]);
    }

    public static int[] sortedArrayIntersectionHelper(int[] a, int[] b, int[] org) {
        if (a.length <= 0 || b.length <= 0) {
            return org;
        }

        if (a[0] < b[0]) {
            a = Arrays.copyOfRange(a, 1, a.length);
        } else if (b[0] < a[0]) {
            b = Arrays.copyOfRange(b, 1, b.length);
        } else {
            int[] res = Arrays.copyOf(org, org.length + 1);
            res[org.length] = a[0];
            return sortedArrayIntersectionHelper(Arrays.copyOfRange(a, 1, a.length), Arrays.copyOfRange(b, 1, b.length), res);
        }
        return sortedArrayIntersectionHelper(a, b, org);
    }

}
