package Objects;

import SimuWorld.World;
import Utils.Utils;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Utils.Vector;


public class Object {

    private float width;
    private float height;
    private float deep;
    private String shape;
    private float mass;
    private String label;
    private int x;
    private int y;
    private int z;
    private final World world;
    private Forces[] forces;
    private Vector speed;
    private Node node;
    private boolean gravity;
    private final Vector gravityForce;


    public Object(float width, float height, float deep, String shape, int x, int y, int z, World world, float mass, String label)
    {
        this.width = width;
        this.height = height;
        this.deep = deep;
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.mass = mass;
        this.forces = new Forces[0];
        this.speed = new Vector(0, 0, 0);
        this.label = label;

        gravityForce = new Forces(0, - (float)9.81, 0, mass, "gravity");
    }

    //view : 1 -> x,y ; 2 -> y,z ; 3 -> z,x;
    public Group getGroup(int view)
    {
        Node solidShape = getShape(view);
        node = solidShape;

        return new Group(solidShape);
    }

    private Node getShape(int view)
    {
        switch (shape.toLowerCase())
        {
            case "rectangle" :
                return createRectangle(view);

            case "circle" :
                break;

            case "line" :
                break;

            case "cylinder" :
                break;


            default:
                return null;
        }

        return null;
    }

    public int getY() {
        return y;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    private Rectangle createRectangle(int view)
    {
        Rectangle rectangle = new Rectangle();
        int originX = world.getOriginX();
        int originY = world.getOriginY();
        switch (view){
            case 1:
                rectangle.setWidth(width);
                rectangle.setHeight(height);
                rectangle.setTranslateX(originX + x);
                rectangle.setTranslateY(originY - y);
                break;

            case 2:
                rectangle.setWidth(deep);
                rectangle.setHeight(width);
                rectangle.setTranslateX(originX + y);
                rectangle.setTranslateY(originY - z);
                break;

            case 3:
                rectangle.setWidth(height);
                rectangle.setHeight(deep);
                rectangle.setTranslateX(originX + z);
                rectangle.setTranslateY(originY - x);
                break;
        }

        rectangle.setFill(Color.BLACK);

        return rectangle;
    }

    public void addForce(Forces force){
        forces = Utils.push(forces, force);
    }

    public void applyForces(int view)
    {
        Vector acc = Utils.sumVector(forces);
        if (gravity){
            acc.add(gravityForce);
        }
        acc.divideByConst(mass);
        acc.divideByConst(100);

        speed.add(acc);

        this.x += speed.getX();
        this.y += speed.getY();
        this.z += speed.getZ();

        int originX = world.getOriginX();
        int originY = world.getOriginY();

        switch (view){
            case 1:
                node.setTranslateX(this.x + originX);
                node.setTranslateY(originY - this.y);
                break;

            case 2:
                node.setTranslateX(this.y + originX);
                node.setTranslateY(originY - this.z);
                break;

            case 3:
                node.setTranslateX(this.z + originX);
                node.setTranslateY(originY - this.x);
                break;
        }

    }

    public void copy(Object object)
    {
        this.width = object.width;
        this.height = object.height;
        this.deep = object.deep;
        this.shape = object.shape;
        this.x = object.x;
        this.y = object.y;
        this.z = object.z;
        this.speed = new Vector(0, 0, 0);
        this.mass = object.mass;
        this.label = object.label;

        int originX = world.getOriginX();
        int originY = world.getOriginY();
        node.setTranslateX(this.x + originX);
        node.setTranslateY(originY - this.y);

    }


}
