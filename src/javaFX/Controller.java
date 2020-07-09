package javaFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button button;

    @FXML
    private Line line;

    @FXML
    private Ellipse ball;

    @FXML
    private Line scale;


    public void startSimu(ActionEvent event)
    {
        ball.setCenterX(ball.getRadiusX() + 10);
    }


}
