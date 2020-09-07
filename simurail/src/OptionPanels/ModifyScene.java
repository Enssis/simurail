package OptionPanels;

import SimuWorld.World;
import SimuWorld.WorldManager;
import Utils.Creators;

import javaFX.SceneManager;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ModifyScene extends OptionScene{

    public ModifyScene(int width, int height, Stage stage, SceneManager sceneManager)
    {
        super(width, height, stage, sceneManager, "MODIFY");
    }

    @Override
    void addOptionGroup(Group optionPanel)
    {
        Group newObject= createNewObjectGroup(optionPanel);
        Group selectionGroup = selectionGroup(newObject);
        optionPanel.getChildren().add(selectionGroup);
        optionPanel.getChildren().add(newObject);
    }

    @Override
    World[] addWorld() {
        return new SimuWorld.ModifyWorld[]{sceneManager.getWorldManager().getModifyWorld(), sceneManager.getWorldManager().getModifyWorld2()};
    }


    private Group selectionGroup(Group newObjectGroup)
    {
        //button create
        Group createButton = Creators.createButtonGroup(13 * WIDTH / 18, HEIGHT / 2, WIDTH / 9, 30,
                Color.DARKGREY, Color.BLACK, "new");

        Group modifyButton = Creators.createButtonGroup(15 * WIDTH / 18, HEIGHT / 2, WIDTH / 9, 30,
                Color.DARKGREY, Color.BLACK, "modify");

        Group finalCreateButton = Creators.addOverviewColor(createButton, Color.DARKGREY, Color.GREY);

        Group finalModifyButton = Creators.addOverviewColor(modifyButton, Color.DARKGREY, Color.GREY);

        EventHandler<MouseEvent> createEventHandlerClicked = e -> createNewObject(finalCreateButton, newObjectGroup);
        createButton.addEventFilter(MouseEvent.MOUSE_CLICKED, createEventHandlerClicked);


        return new Group(finalCreateButton, finalModifyButton);
    }

    private void createNewObject(Group group, Group newObjectGroup)
    {
        group.getParent().setVisible(false);
        newObjectGroup.setVisible(true);
    }

    private Group createNewObjectGroup(Group optionPanel)
    {

        //main grid

        GridPane mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(20, 20, 20, 20));
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);

        //size grid

        GridPane sizeGrid = new GridPane();
        sizeGrid.setPadding(new Insets(10, 10, 10, 10));
        sizeGrid.setVgap(5);
        sizeGrid.setHgap(5);

        Text sizeText = Creators.createCenteredText(20, 0, "Taille : ", Color.BLACK, 17);
        sizeGrid.add(sizeText, 0, 0, 1, 1);

            //height
        TextField heightField = new TextField();
        Label heightLabel = new Label("Hauteur : ");
        HBox heightHB = new HBox();
        heightHB.getChildren().add(heightLabel);
        heightHB.getChildren().add(heightField);
        heightHB.setSpacing(10);
        sizeGrid.add(heightHB, 0, 1, 1, 1);

            //width
        TextField widthField = new TextField();
        Label widthLabel = new Label("Largeur : ");
        HBox widthHB = new HBox(widthLabel, widthField);
        widthHB.setSpacing(10);
        sizeGrid.add(widthHB, 0, 2, 1, 1);

            //deep
        TextField deepField = new TextField();
        Label deepLabel = new Label("Profondeur : ");
        HBox deepHB = new HBox(deepLabel, deepField);
        deepHB.setSpacing(10);
        sizeGrid.add(deepHB, 0, 3, 1, 1);

        mainGrid.add(sizeGrid, 0, 0, 1, 1);


        //pos grid
        GridPane posGrid = new GridPane();
        posGrid.setPadding(new Insets(10, 10, 10, 10));
        posGrid.setVgap(5);
        posGrid.setHgap(5);

        Text posText = Creators.createCenteredText(20, 0, "Position : ", Color.BLACK, 17);
        posGrid.add(posText, 0, 0, 1, 1);

        //height
        TextField xField = new TextField();
        Label xLabel = new Label("X : ");
        HBox xHB = new HBox();
        xHB.getChildren().add(xLabel);
        xHB.getChildren().add(xField);
        xHB.setSpacing(10);
        posGrid.add(xHB, 0, 1, 1, 1);

        //width
        TextField yField = new TextField();
        Label yLabel = new Label("Y : ");
        HBox yHB = new HBox(yLabel, yField);
        yHB.setSpacing(10);
        posGrid.add(yHB, 0, 2, 1, 1);

        //deep
        TextField zField = new TextField();
        Label zLabel = new Label("Z : ");
        HBox zHB = new HBox(zLabel, zField);
        zHB.setSpacing(10);
        posGrid.add(zHB, 0, 3, 1, 1);

        mainGrid.add(posGrid, 0, 1, 1, 1);


        //carac grid

        GridPane caracGrid = new GridPane();
        caracGrid.setPadding(new Insets(10, 10, 10, 10));
        caracGrid.setVgap(5);
        caracGrid.setHgap(5);


        Text caracText = Creators.createCenteredText(20, 0, "Caractéristiques : ", Color.BLACK, 17);
        caracGrid.add(caracText, 0, 0, 1, 1);

            //mass
        TextField massField = new TextField();
        Label massLabel = new Label("Masse : ");
        HBox massHB = new HBox(massLabel, massField);
        massHB.setSpacing(10);
        caracGrid.add(massHB, 0, 1, 1, 1);

            //Label
        TextField labelField = new TextField();
        Label labelLabel = new Label("Label : ");
        HBox labelHB = new HBox(labelLabel, labelField);
        labelHB.setSpacing(10);
        caracGrid.add(labelHB, 0, 2, 1, 1);



        mainGrid.add(caracGrid, 0, 2, 1, 1);




        Group liaisonAddWindow = addLiaisonGroup();

        //liaisons grid

        GridPane liaisonGrid = new GridPane();
        liaisonGrid.setPadding(new Insets(10, 10, 10, 10));
        liaisonGrid.setVgap(5);
        liaisonGrid.setHgap(5);

        //liaison text

        Text liaisonText = Creators.createCenteredText(20, 0, "Liaisons : ", Color.BLACK, 17);
        liaisonGrid.add(liaisonText, 0, 0, 1, 1);

        //liaison add button

        Button liaisonButton = new Button("+");
        liaisonButton.setOnAction(e -> liaisonAddWindow.setVisible(true));
        liaisonGrid.add(liaisonButton, 0, 1, 1, 1);

        mainGrid.add(liaisonGrid, 0, 3, 1, 1);

        mainGrid.setTranslateX(2 * WIDTH / 3 + 20);
        mainGrid.setTranslateY(55);

        Group acceptButton = Creators.createButtonGroup(2 * WIDTH / 3 + 50, HEIGHT - 30, 80, 20, Color.WHITE, Color.BLACK, "Créer");

        Group cancelButton = Creators.createButtonGroup(WIDTH - 50 - 80, HEIGHT - 30, 80, 20, Color.WHITE, Color.BLACK, "Annuler");



        Group group = new Group(acceptButton, cancelButton, mainGrid, liaisonAddWindow);
        group.setVisible(false);

        EventHandler<MouseEvent> createEventHandlerClicked = e -> {
            createObject(widthField, heightField, deepField, xField, yField, zField, massField, labelField);
            group.setVisible(false);
            optionPanel.getChildren().get(4).setVisible(true);
        };
        acceptButton.addEventFilter(MouseEvent.MOUSE_CLICKED, createEventHandlerClicked);

        Creators.addOverviewColor(acceptButton, Color.WHITE, Color.LIGHTGREY);

        EventHandler<MouseEvent> cancelEventHandlerClicked = e -> {
            group.setVisible(false);
            optionPanel.getChildren().get(4).setVisible(true);
        };
        cancelButton.addEventFilter(MouseEvent.MOUSE_CLICKED, cancelEventHandlerClicked);

        Creators.addOverviewColor(cancelButton, Color.WHITE, Color.LIGHTGREY);

        return group;
    }

    public Group addLiaisonGroup()
    {

        int height = 300;

        //background
        Rectangle background = Creators.createBackground(WIDTH / 2 - 100, HEIGHT / 2 - (int)(height / 2), 200, height, Color.WHITE, 2, Color.BLACK);

        //title
        Text title = Creators.createCenteredText(WIDTH / 2, HEIGHT / 2 - (int)(height / 2) + 10, "ajouter une liaison", Color.BLACK, 15);

        //grid

        GridPane liaisonGrid = new GridPane();
        liaisonGrid.setPadding(new Insets(20, 20, 20, 20));
        liaisonGrid.setVgap(10);
        liaisonGrid.setHgap(10);

        //choices Box

        Label choiceLabel = new Label("liaison : ");

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Pivot", "Pivot glissant", "Encastrement", "glissière", "collision")
        );

        HBox choiceHB = new HBox(choiceLabel, cb);
        choiceHB.setSpacing(10);
        liaisonGrid.add(choiceHB, 0, 0, 1, 1);

        //with choice

        ChoiceBox withCB = new ChoiceBox(FXCollections.observableArrayList("Sol"));
        Label withLabel = new Label("Avec : ");
        HBox withHB = new HBox(withLabel, withCB);
        withHB.setSpacing(10);
        liaisonGrid.add(withHB, 0, 1, 1, 1);

        //liberty degres

        GridPane libertyGrid = new GridPane();
        libertyGrid.setPadding(new Insets(5, 5, 5, 5));
        libertyGrid.setVgap(5);
        libertyGrid.setHgap(5);

        CheckBox[][] libertyBox = {{new CheckBox("Tx"), new CheckBox("Ty"), new CheckBox("Tz")},
                {new CheckBox("Rx"), new CheckBox("Ry"), new CheckBox("Rz")}};

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                libertyGrid.add(libertyBox[i][j], i, j, 1, 1);
            }
        }
        liaisonGrid.add(libertyGrid, 0, 2, 1, 1);



        //add button
        Group addButton = Creators.createButtonGroup(WIDTH / 2 - 90, HEIGHT / 2 + (int)(height / 2) - 30, 80, 20, Color.WHITE, Color.BLACK, "Créer");
        Creators.addOverviewColor(addButton, Color.WHITE, Color.LIGHTGREY);



        //cancel button
        Group cancelButton = Creators.createButtonGroup(WIDTH / 2 + 10, HEIGHT / 2 + (int)(height / 2) - 30, 80, 20, Color.WHITE, Color.BLACK, "Annuler");
        Creators.addOverviewColor(cancelButton, Color.WHITE, Color.LIGHTGREY);


        liaisonGrid.setTranslateX(WIDTH / 2 - 100);
        liaisonGrid.setTranslateY(HEIGHT / 2 - (int)(height / 2) + 30);
        Group group = new Group(background, title, liaisonGrid, addButton, cancelButton);
        group.setVisible(false);

        EventHandler<MouseEvent> cancelEventHandlerClicked = e -> {
            group.setVisible(false);
        };
        cancelButton.addEventFilter(MouseEvent.MOUSE_CLICKED, cancelEventHandlerClicked);





        return group;
    }

    private void createObject(TextField widthField, TextField heightField, TextField deepField, TextField xField,
                              TextField yField, TextField zField, TextField massField, TextField labelField)
    {
        int width = Integer.parseInt(widthField.getText());
        int height = Integer.parseInt(heightField.getText());
        int deep = Integer.parseInt(deepField.getText());

        int x = Integer.parseInt(xField.getText());
        int y = Integer.parseInt(yField.getText());
        int z = Integer.parseInt(zField.getText());

        float mass = Float.parseFloat(massField.getText());
        String label = labelField.getText();

        WorldManager worldManger = sceneManager.getWorldManager();
        worldManger.addObject(width, height, deep,"rectangle", x, y, z, mass, label);


    }

}


