package OptionPanels;

import SimuWorld.World;
import Utils.Creators;
import javaFX.SceneManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ForcesScene extends OptionScene{

    public ForcesScene(int width, int height, Stage stage, SceneManager sceneManager)
    {
        super(width, height, stage, sceneManager, "FORCES");
    }

    @Override
    void addOptionGroup(Group optionPanel) {

    }

    @Override
    World[] addWorld() {
        return new SimuWorld.ForcesWorld[]{sceneManager.getWorldManager().getForcesWorld()};
    }


}
