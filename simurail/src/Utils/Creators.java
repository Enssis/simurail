package Utils;

import javaFX.SceneManager;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Creators {

    private static boolean reduced = false;

    public Creators()
    {

    }

    public static Line createLine(float x1, float y1, float x2, float y2, int stroke, Paint lineColor)
    {
        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(stroke);
        line.setStroke(lineColor);

        return line;
    }

    public static Rectangle createBackground(float x, float y, float width, float height, Paint backgroundColor)
    {
        Rectangle background = new Rectangle(x, y, width, height);
        background.setFill(backgroundColor);

        return background;
    }

    public static Rectangle createBackground(float x, float y, float width, float height, Paint backgroundColor, int stroke, Paint strokeColor)
    {
        Rectangle background = new Rectangle(x, y, width, height);
        background.setFill(backgroundColor);
        background.setStrokeWidth(stroke);
        background.setStroke(strokeColor);

        return background;
    }

    public static Text createCenteredText(float x, float y, String text, Paint textColor, int textSize)
    {
        Text endText = new Text(x - 0.5 * textSize * (float)text.length() / 2, y, text);
        endText.setTextOrigin(VPos.CENTER);
        endText.setFont(Font.font("verdana", FontPosture.REGULAR, textSize));
        endText.setFill(textColor);

        return endText;
    }


    public static Group createButtonGroup(float x, float y, float width, float height, Paint backgroundColor, Paint textColor, String text, boolean vertical, boolean horizontal)
    {
        //background
        Rectangle buttonBackground = createBackground(x, y, width, height, backgroundColor);

        //text
        Text buttonText = createCenteredText(x + width / 2, y + height / 2, text, textColor, 15);

        Group group = new Group(buttonBackground, buttonText);

        if(vertical) {
            //border Bottom
            Line buttonBottomBorder = createLine(x, y + height, x + width, height, 1, Color.BLACK);

            //border Top
            Line buttonTopBorder = createLine(x, y, x + width, y, 1, Color.BLACK);

            group.getChildren().add(buttonTopBorder);
            group.getChildren().add(buttonBottomBorder);
        }

        if(horizontal) {
            //right border
            Line buttonRightBorder = createLine(x + width, y, x + width, y + height, 1, Color.BLACK);

            //left Border
            Line buttonLeftBorder = createLine(x, y, x, y+height, 1, Color.BLACK);

            group.getChildren().add(buttonRightBorder);
            group.getChildren().add(buttonLeftBorder);
        }


        return group;
    }

    public static Group createButtonGroup(float x, float y, float width, float height, Paint backgroundColor, Paint textColor, String text)
    {
        //background
        Rectangle buttonBackground = createBackground(x, y, width, height, backgroundColor, 1, Color.BLACK);

        //text
        Text buttonText = createCenteredText(x + width / 2, y + height / 2, text, textColor, 15);

        return new Group(buttonBackground, buttonText);
    }


    public static Group optionTitle(String title, float width)
    {
        //text
        Text text = createCenteredText(5 * width / 6, 40, title, Color.DARKGREY, 20);

        //under Line
        Line underLine = createLine((float)(5 * width / 6 - (float)title.length() * 20 * 0.5 - 15), 55, (float)(5 * width / 6 + (float)title.length() * 20 * 0.5 + 25), 55, 2, Color.GREY);

        return new Group(text, underLine);
    }

    public static Group addOverviewColor(Group group, Paint normalColor, Paint overviewColor)
    {
        Node nodeOut = group.getChildren().get(0);
        if(nodeOut instanceof Rectangle) {
            Rectangle background = (Rectangle) nodeOut;

            EventHandler<MouseEvent> EventHandlerEntered = e -> background.setFill(overviewColor);
            EventHandler<MouseEvent> EventHandlerExited = e -> background.setFill(normalColor);

            //group
            group.addEventFilter(MouseEvent.MOUSE_ENTERED, EventHandlerEntered);
            group.addEventFilter(MouseEvent.MOUSE_EXITED, EventHandlerExited);
        }
        return group;
    }


    public static Group createOptionPanel(Stage primaryStage, float width, float height, SceneManager sceneManager)
    {
        //background
        Rectangle optionBackground =
                createBackground(2 * width / 3, 0, width / 3, height, Color.WHITE);


        //separation line
        Line optionSeparation =
                createLine(2 * width / 3, 0, 2 * width / 3, height, 3, Color.BLACK);


        Group optionChooseLineGroup = createOptionChoosingLine(primaryStage, width, sceneManager);


        return new Group(optionBackground, optionChooseLineGroup, optionSeparation);

    }

    public static Group createOptionChoosingLine(Stage primaryStage, float width, SceneManager sceneManager)
    {
        //create option choosing line

        //---------------------create reduction button-------------------------------------

        Group reductionGroup = createButtonGroup(2 * width / 3 + 1, 0, 20, 20, Color.WHITE, Color.BLACK, "-");


        Group finalReductionGroup = addOverviewColor(reductionGroup, Color.WHITE, Color.LIGHTGREY);


        Node nodeOut = reductionGroup.getChildren().get(1);
        if(nodeOut instanceof Text) {
            Text reductionText = (Text)nodeOut;
            EventHandler<MouseEvent> EventHandlerClicked = e -> {
                Node parent = finalReductionGroup.getParent().getParent();
                if(!reduced) {
                    reductionText.setText("+");
                    reductionText.setX(reductionText.getX() - 3);
                    parent.setTranslateX(width / 3 - 21);
                }else{
                    reductionText.setText("-");
                    reductionText.setX(reductionText.getX() + 3);
                    parent.setTranslateX(0);
                }
                reduced = !reduced;
            };

            reductionGroup.addEventFilter(MouseEvent.MOUSE_CLICKED, EventHandlerClicked);
        }



        //----------------------create main button----------------------------------

        float buttonsWidth = (width - 2 * width / 3 - 21) / 3;

        Group mainButtonGroup = createButtonGroup(2 * width / 3 + 21, 0, buttonsWidth, 20, Color.WHITE, Color.BLACK, "Main", true, false);

        Group finalMainButtonGroup = addOverviewColor(mainButtonGroup, Color.WHITE, Color.LIGHTGREY);

        EventHandler<MouseEvent> mainEventHandlerClicked = e -> primaryStage.setScene(sceneManager.optionMainScene);
        finalMainButtonGroup.addEventFilter(MouseEvent.MOUSE_CLICKED, mainEventHandlerClicked);



        //---------------------create Forces Button-------------------------------------------

        Group forcesButtonGroup = createButtonGroup(2 * width / 3 + 21 + buttonsWidth, 0, buttonsWidth, 20, Color.WHITE, Color.BLACK, "Forces", true, false);

        Group finalForcesButtonGroup = addOverviewColor(forcesButtonGroup, Color.WHITE, Color.LIGHTGREY);

        EventHandler<MouseEvent> forcesEventHandlerClicked = e -> primaryStage.setScene(sceneManager.optionForcesScene);
        finalForcesButtonGroup.addEventFilter(MouseEvent.MOUSE_CLICKED, forcesEventHandlerClicked);



        //------------------------Create modify Button--------------------------------------------------

        Group modifyButtonGroup = createButtonGroup(2 * width / 3 + 21 + 2 * buttonsWidth, 0, buttonsWidth, 20, Color.WHITE, Color.BLACK, "Modify", true, false);


        Group finalModifyButtonGroup = addOverviewColor(modifyButtonGroup, Color.WHITE, Color.LIGHTGREY);


        EventHandler<MouseEvent> modifyEventHandlerClicked = e -> {
            primaryStage.setScene(sceneManager.optionModifyScene);
        };
        finalModifyButtonGroup.addEventFilter(MouseEvent.MOUSE_CLICKED, modifyEventHandlerClicked);



        //----------------------create separation lines------------------------------
        //line1 : - / main
        Line optionSeparationLine1 = new Line(2 * width / 3 + 21, 0, 2 * width / 3 + 21, 20);
        optionSeparationLine1.setStrokeWidth(1);
        optionSeparationLine1.setStroke(Color.BLACK);

        //line2 : main / Forces
        Line optionSeparationLine2 = new Line(2 * width / 3 + 21 + buttonsWidth, 0, 2 * width / 3 + 21 + buttonsWidth, 20);
        optionSeparationLine2.setStrokeWidth(1);
        optionSeparationLine2.setStroke(Color.BLACK);

        //line3 : Forces / modify
        Line optionSeparationLine3 = new Line(2 * width / 3 + 21 + 2 * buttonsWidth, 0, 2 * width / 3 + 21 +  2 * buttonsWidth, 20);
        optionSeparationLine3.setStrokeWidth(1);
        optionSeparationLine3.setStroke(Color.BLACK);


        //group
        Group optionSeparationGroup = new Group(optionSeparationLine1, optionSeparationLine2, optionSeparationLine3);


        //-----------option choosing line group--------------------
        return new Group(reductionGroup, mainButtonGroup, forcesButtonGroup, modifyButtonGroup, optionSeparationGroup);
    }



}
