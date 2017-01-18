package game.interfaces;

import javafx.geometry.Bounds;

public interface JavaFX {

    Bounds getBoundsInLocal();

    Bounds localToParent(Bounds localBounds);

    Bounds getBoundsInParent();

    double getTranslateX();

    void setTranslateX(double value);

    double getTranslateY();

    void setTranslateY(double value);

    double getOpacity();

    void setOpacity(double value);
}

