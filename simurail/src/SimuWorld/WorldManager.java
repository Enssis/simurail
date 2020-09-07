package SimuWorld;

import javaFX.SceneManager;

public class WorldManager {

    private static MainWorld mainWorld;
    private static MainWorld mainWorld2;
    private static ForcesWorld forcesWorld;
    private static ForcesWorld forcesWorld2;
    private static ModifyWorld modifyWorld;
    private static ModifyWorld modifyWorld2;

    public WorldManager(int originX, int originY, SceneManager sceneManager)
    {
        mainWorld = new MainWorld(originX, originY,sceneManager);
        mainWorld2 = new MainWorld(originX + sceneManager.getWidth() / 3, originY, sceneManager);
        modifyWorld = new ModifyWorld(originX, originY, sceneManager);
        modifyWorld2 = new ModifyWorld(originX + sceneManager.getWidth() / 3, originY, sceneManager);
        forcesWorld = new ForcesWorld(originX, originY, sceneManager);
        forcesWorld2 = new  ForcesWorld(originX + sceneManager.getWidth() / 3, originY, sceneManager);
    }

    public MainWorld getMainWorld()
    {
        return mainWorld;
    }

    public MainWorld getMainWorld2()
    {
        return mainWorld2;
    }

    public ForcesWorld getForcesWorld()
    {
        return forcesWorld;
    }

    public ForcesWorld getForcesWorld2() {
        return forcesWorld2;
    }

    public ModifyWorld getModifyWorld()
    {
        return modifyWorld;
    }

    public ModifyWorld getModifyWorld2() {
        return modifyWorld2;
    }

    public void addObject(int width, int height, int deep, String shape, int x, int y, int z, float mass, String label) {

        mainWorld.addObject(width, height, deep,shape, x, y, z, mass, label, 1);
        mainWorld2.addObject(width, height, deep,shape, x, y, z, mass, label, 2);
        forcesWorld.addObject(width, height, deep,shape, x, y, z, mass, label, 1);
        forcesWorld2.addObject(width, height, deep,shape, x, y, z, mass, label, 2);
        modifyWorld.addObject(width, height, deep,shape, x, y, z, mass, label, 1);
        modifyWorld2.addObject(width, height, deep,shape, x, y, z, mass, label, 2);
    }

    public void applyForces(int view)
    {
        mainWorld.applyForces(view);
    }

    public void applyForces(int view1, int view2)
    {
        mainWorld.applyForces(view1);
        mainWorld2.applyForces(view2);
    }


}
