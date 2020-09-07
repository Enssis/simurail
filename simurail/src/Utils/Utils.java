package Utils;

import Objects.Forces;
import Objects.Object;

public class Utils {

    Utils()
    {

    }

    public static void push() {
    }

    public static Object[] push(Object[] array, Object element)
    {
        Object[] output = new Object[array.length + 1];
        for(int i = 0; i < array.length; i ++)
        {
            output[i] = array[i];
        }
        output[array.length] = element;

        return output;
    }

    public static Forces[] push(Forces[] array, Forces element)
    {
        Forces[] output = new Forces[array.length + 1];
        for(int i = 0; i < array.length; i ++)
        {
            output[i] = array[i];
        }
        output[array.length] = element;

        return output;
    }

    public static Vector sumVector(Vector[] elements)
    {
        float sumX = 0;
        float sumY = 0;
        float sumZ = 0;
        for (Vector element: elements) {
            sumX += element.getX();
            sumY += element.getY();
            sumZ += element.getZ();
        }

        return new Vector(sumX, sumY, sumZ);
    }

}
