package arrays;

import java.lang.reflect.*;
import java.util.*;

/**
 * This program demonstrates the use of reflectio for manipulating arrays.
 * 
 * @version 1.2 2012-05-04
 * @author CAy Horstmann
 */

public class CopyOfTest {
    public static void main(String[] args) {
        int[] a = { 1, 2, 3 };
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

    }

    public static Object goodCopyOf(Object a, int newLength) {
        Class<?> cl = a.getClass();
        Class<?> componentType = cl.getComponentType();

        if (!cl.isArray())
            return null;

        int length = Array.getLength(a);

        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));

        return newArray;
    }
}