package Objects;

import Utils.Vector;

public class Forces extends Vector {

    private float mass;

    public Forces(float fx, float fy, float fz, float mass, String type)
    {
        super(fx, fy, fz);
        if (type.toLowerCase().equals("gravity")) {
            setX(fx * mass);
            setY(fy * mass);
            setZ(fz * mass);
        }
        this.mass = mass;
    }

}
