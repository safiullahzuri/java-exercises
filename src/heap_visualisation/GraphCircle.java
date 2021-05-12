package heap_visualisation;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class GraphCircle extends Circle {
    private Point2D topLeftHook;
    private Point2D topRightHook;

    private Point2D bottomLeftHook;
    private Point2D bottomRightHook;

    public GraphCircle(double centerX, double centerY, double radius) {
        setCenterX(centerX);
        setCenterY(centerY);
        setRadius(radius);
        topLeftHook = setHook(225);
        topRightHook = setHook(315);
        bottomLeftHook = setHook(135);
        bottomRightHook = setHook(45);
    }

    private Point2D setHook(double degrees) {
        double x = getCenterX() + getRadius() * Math.cos(Math.toRadians(degrees));
        double y = getCenterY() + getRadius() * Math.sin(Math.toRadians(degrees));
        return new Point2D(x, y);
    }

    public Point2D getTopLeftHook() {
        return topLeftHook;
    }

    public Point2D getTopRightHook() {
        return topRightHook;
    }

    public Point2D getBottomLeftHook() {
        return bottomLeftHook;
    }

    public Point2D getBottomRightHook() {
        return bottomRightHook;
    }
}
