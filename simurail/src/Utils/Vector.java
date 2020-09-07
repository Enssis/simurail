package Utils;

public class Vector {

    protected float x, y, z;

    public Vector(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void add(Vector a)
    {
        this.x = this.x + a.getX();
        this.y = this.y + a.getY();
        this.z = this.z + a.getZ();
    }

    public String present()
    {
        return ("X : " + x + " Y : " + y + " Z : " + z);
    }


    public void divideByConst(float a) {
        x = x / a;
        y = y / a;
        z = z / a;

    }
}
