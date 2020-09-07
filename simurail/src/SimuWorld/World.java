package SimuWorld;

import Objects.Object;
import Utils.Utils;
import javaFX.SceneManager;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public abstract class World {

    protected Object[] objects = new Object[0];
    protected final Circle origin;
    protected Line separation;
    protected final Group objectsGroup = new Group();

    public World(int originX, int originY, SceneManager sceneManager)
    {
        origin = new Circle();
        origin.setCenterX(originX);
        origin.setCenterY(originY);
        origin.setRadius(2);
        origin.setFill(Color.DARKBLUE);

        separation = new Line((int)(sceneManager.width / 3), 0, (int)(sceneManager.width / 3), sceneManager.getHeight());
        separation.setStroke(Color.BLACK);
        separation.setStrokeWidth(2);

    }

    public Group getWorldGroup()
    {
        return new Group(separation, origin, objectsGroup);
    }

    public int getOriginX()
    {
        return (int)origin.getCenterX();
    }

    public int getOriginY()
    {
        return (int)origin.getCenterY();
    }

    public void addObject(int width, int height, int deep, String shape, int x, int y, int z, float mass, String label, int view)
    {
        Object newObj = new Object(width, height, deep, shape, x, y, z , this, mass, label);
        newObj.setGravity(true);

        this.objects = Utils.push(objects, newObj);
        objectsGroup.getChildren().add(newObj.getGroup(view));
    }

    public void applyForces(int view)
    {
        for (Object object: objects) {
            object.applyForces(view);
        }
    }

    public Object[] getObjects()
    {
        return objects;
    }
}
