package Controller;

import Model.Level;
import Model.Placeable;
import Model.SunToken;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.*;
import java.io.*;

public class SunTokenController extends PlaceableController {

    public SunTokenController(LevelController levelController, SunToken sunToken, ImageView imageView) {
        super(levelController, sunToken, imageView);

    }

    @Override
    public void run() {
        SunToken sunToken = (SunToken) placeable;
        while (levelController.getLevel().isRunning()) {
            while (levelController.isPause() & levelController.getLevel().isRunning()) {}
            if(!sunToken.isAlive()) {
                levelController.getLevel().removeSunToken(sunToken);
                break;
            }
            if(sunToken.isMoving()) {
                sunToken.move();
                AnchorPane.setLeftAnchor(imageView, (double)sunToken.getPosition().getX());
                AnchorPane.setTopAnchor(imageView, (double)sunToken.getPosition().getY());
                try {
                    Thread.sleep(LevelController.ANIMATION_TIMEGAP);
                } catch (InterruptedException e) {}

                if(LevelController.getGridPosition(sunToken.getPosition()).getRow() >= LevelController.GROUND_ROW) {
                    sunToken.setMoving(false);
                }
            }
        }
    }
}
