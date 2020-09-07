package OptionPanels;

import SimuWorld.World;
import Utils.Creators;
import javaFX.SceneManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class OptionScene {

    protected static float WIDTH;
    protected static float HEIGHT;
    protected Group mainGroup = new Group();
    protected static Stage STAGE;
    protected static SceneManager sceneManager;
    protected static String TITLE;

    OptionScene(int width, int height, Stage stage, SceneManager sceneManager, String title)
    {
        WIDTH = width;
        HEIGHT = height;
        STAGE = stage;
        TITLE = title;
        OptionScene.sceneManager = sceneManager;

        //create group
        Group optionPanel = Creators.createOptionPanel(stage, width, height, sceneManager);

        //assign group
        optionPanel.getChildren().add(Creators.optionTitle(title, WIDTH));
        addOptionGroup(optionPanel);
        World[] Worlds = addWorld();
        for (int i = 0; i < Worlds.length; i++) {
            mainGroup.getChildren().add(Worlds[i].getWorldGroup());
        }
        mainGroup.getChildren().add(optionPanel);
    }

    abstract void addOptionGroup(Group optionPanel);

    abstract World[] addWorld();

    public Scene getScene()
    {
        return new Scene(mainGroup, WIDTH, HEIGHT);
    }

}
