package OptionPanels;

import SimuWorld.World;
import Utils.Creators;
import javaFX.SceneManager;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Objects.Object;

import java.awt.*;

public class MainScene extends OptionScene{

    public int view = 1;

    public MainScene(int width, int height, Stage stage, SceneManager sceneManager)
    {
        super(width, height, stage, sceneManager, "MAIN");
    }

    @Override
    void addOptionGroup(Group optionPanel) {
        optionPanel.getChildren().add(mainBody());
    }

    @Override
    World[] addWorld() {
        return new SimuWorld.MainWorld[]{sceneManager.getWorldManager().getMainWorld(), sceneManager.getWorldManager().getMainWorld2()};
    }



    private Group mainBody()
    {

        //View selector

        Group viewSelector = new Group();

        Text viewButtonTitle = Creators.createCenteredText(5 * WIDTH / 6, HEIGHT / 2 - 40, "View Selector", Color.BLACK, 10);

        Group viewButton1 = Creators.createButtonGroup(2 * WIDTH / 3 + 50, HEIGHT / 2, 140, 20, Color.WHITE, Color.BLACK,"1 vue" );
        Group viewButton2 = Creators.createButtonGroup(WIDTH - 50 - 140, HEIGHT / 2, 140, 20, Color.WHITE, Color.BLACK,"2 vues" );


        Node nodeOut = viewButton1.getChildren().get(0);
        if(nodeOut instanceof javafx.scene.shape.Rectangle) {
            javafx.scene.shape.Rectangle background = (Rectangle) nodeOut;

            Color normalColor = Color.GREY;
            if(view != 1){
                normalColor = Color.WHITE;
            }

            EventHandler<MouseEvent> EventHandlerEntered = e -> background.setFill(Color.LIGHTGREY);
            Color finalNormalColor = normalColor;
            EventHandler<MouseEvent> EventHandlerExited = e -> background.setFill(finalNormalColor);

            //group
            viewButton1.addEventFilter(MouseEvent.MOUSE_ENTERED, EventHandlerEntered);
            viewButton1.addEventFilter(MouseEvent.MOUSE_EXITED, EventHandlerExited);

        }

        EventHandler<MouseEvent> EventHandlerClicked = e -> view = 1 ;

        viewButton1.addEventFilter(MouseEvent.MOUSE_CLICKED, EventHandlerClicked);



        nodeOut = viewButton2.getChildren().get(0);
        if(nodeOut instanceof javafx.scene.shape.Rectangle) {
            javafx.scene.shape.Rectangle background = (Rectangle) nodeOut;

            Color normalColor = Color.GREY;
            if(view != 2){
                normalColor = Color.WHITE;
            }

            EventHandler<MouseEvent> EventHandlerEntered = e -> background.setFill(Color.LIGHTGREY);
            Color finalNormalColor = normalColor;
            EventHandler<MouseEvent> EventHandlerExited = e -> background.setFill(finalNormalColor);

            //group
            viewButton2.addEventFilter(MouseEvent.MOUSE_ENTERED, EventHandlerEntered);
            viewButton2.addEventFilter(MouseEvent.MOUSE_EXITED, EventHandlerExited);

        }

        EventHandler<MouseEvent> EventHandlerClicked2 = e -> view = 2;

        viewButton2.addEventFilter(MouseEvent.MOUSE_CLICKED, EventHandlerClicked2);



        viewSelector.getChildren().setAll(viewButtonTitle, viewButton1, viewButton2);



        //start buttons
        Group startButton = Creators.createButtonGroup(2 * WIDTH / 3 + 50, HEIGHT - 30, 140, 20, Color.WHITE, Color.BLACK, "Commencer");

        Creators.addOverviewColor(startButton, Color.WHITE, Color.LIGHTGREY);

        EventHandler<MouseEvent> startEventHandlerClicked = e -> {
            sceneManager.setStart(true);
            sceneManager.startTask();
        };
        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, startEventHandlerClicked);

        //stop button

        Group endButton = Creators.createButtonGroup(WIDTH - 50 - 140, HEIGHT - 30, 140, 20, Color.WHITE, Color.BLACK, "Arreter");

        Creators.addOverviewColor(endButton, Color.WHITE, Color.LIGHTGREY);

        EventHandler<MouseEvent> endEventHandlerClicked = e -> {
            sceneManager.setStart(false);
            reinitialise();
        };
        endButton.addEventFilter(MouseEvent.MOUSE_CLICKED, endEventHandlerClicked);

        return new Group(viewSelector, startButton, endButton);
    }

    private void reinitialise()
    {
        Object[] modifyObjects = sceneManager.getWorldManager().getModifyWorld().getObjects();
        Object[] mainObjects = sceneManager.getWorldManager().getMainWorld().getObjects();
        Object[] modifyObjects2 = sceneManager.getWorldManager().getModifyWorld2().getObjects();
        Object[] mainObjects2 = sceneManager.getWorldManager().getMainWorld2().getObjects();

        for (int i = 0; i < mainObjects.length ; i++) {
            mainObjects[i].copy(modifyObjects[i]);
            mainObjects2[i].copy(modifyObjects2[i]);
        }
    }

}
