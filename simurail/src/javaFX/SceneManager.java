package javaFX;

import OptionPanels.ForcesScene;
import OptionPanels.MainScene;
import OptionPanels.ModifyScene;
import SimuWorld.MainWorld;
import SimuWorld.WorldManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class SceneManager extends Application {

    private static WorldManager worldManager;
    private static boolean start = false;
    public int height = 600;
    public int width = 1200;
    public Scene optionMainScene, optionForcesScene, optionModifyScene;

    @Override
    public void start(Stage primaryStage) throws Exception {


        //create main World
        worldManager = new WorldManager(40, height - 40, this);


    //---------------------------MAIN OPTION SCENE--------------------------------------------------


        //creating main scene
        MainScene mainScene = new MainScene(width, height, primaryStage,this);

        optionMainScene = mainScene.getScene();

        //setting color to the scene
        optionMainScene.setFill(Color.DARKGRAY);


    //---------------------------FORCES SCENE OPTION--------------------------------------------

        //creating main object
       ForcesScene forcesScene = new ForcesScene(width, height, primaryStage, this);

        //Creating a Scene by passing the group object, height and width
        optionForcesScene = forcesScene.getScene();

        //setting color to the scene
        optionForcesScene.setFill(Color.DARKGRAY);


    //----------------------------------MODIFY SCENE OPTION---------------------------------------


        //creating main group object
        ModifyScene modifyScene = new ModifyScene(width, height, primaryStage, this);

        //Creating a Scene by passing the group object, height and width
        optionModifyScene = modifyScene.getScene();

        //setting color to the scene
        optionModifyScene.setFill(Color.DARKGRAY);



    //---------------------------------STAGE------------------------------------------------------

        //Setting the title to Stage.
        primaryStage.setTitle("App");

        //Adding the scene to Stage
        primaryStage.setScene(optionMainScene);

        //Displaying the contents of the stage
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void setStart(boolean newStart)
    {
        start = newStart;
    }

    public WorldManager getWorldManager()
    {
        return worldManager;
    }

    public void startTask()
    {
        // Create a Runnable
        Runnable task = this::runTask;

        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

    public void runTask()
    {
        while(start)
        {
            try
            {
                // Update the Label on the JavaFx Application Thread
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        worldManager.applyForces(1, 2);
                    }
                });
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}