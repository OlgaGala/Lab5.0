package com.client.util;

import com.api.entity.Dragon;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class Painter {

    private final GraphicsContext gc;
    private final GraphicsContext gcForSelection;

    private Stack<Dragon> mDataSet;

    public Painter(GraphicsContext gc1, GraphicsContext gc2, Stack<Dragon> mDataSet) {
        this.gc = gc1;
        this.gcForSelection = gc2;
        this.mDataSet = mDataSet;
    }


    public void drawHuman(Dragon dragon, int xC, int yC, int size){
//        gc.beginPath();
//        gc.save();
//        gc.scale(0.4 * size,0.4 * size);
//        gc.strokeArc(20 + xC, 85 + yC, 20, 40, 70, 280, ArcType.OPEN); // ������� ������ ���
//        gc.strokeArc(170 + xC, 85 + yC, 20, 42, 80, 360, ArcType.OPEN); // ������� ������� ���
//        gc.setFill(Color.valueOf(dragon.getUser_name()));
//        gc.fillOval(30 + xC,35 + yC,150,150);    // For face
//        gc.fillOval(20 + xC, 85 + yC,20, 40 );   // ������� ������ ���
//        gc.fillOval(170 + xC, 85 + yC,20, 42 );  // ������� ������� ���
//        gc.strokeOval(30 + xC,35 + yC,150,150);  // ������� ����
//        gc.setFill(Color.BLACK);
//        gc.fillOval(60 + xC,80 + yC,15,25);      // Left Eye
//        gc.fillOval(130 + xC,80 + yC,15,25);     // Right Eye
//        gc.setFill(Color.SADDLEBROWN);
//        gc.strokeOval(5 + xC, 25 + yC, 195, 62); // ������� ����� �����
//        gc.fillOval(5 + xC, 25 + yC, 195, 62);   // ���� �����
//        gc.setFill(Color.BLACK);
//        gc.strokeArc(73 + xC, 35 + yC, 60, 30, 180, 180, ArcType.OPEN); // ������� ������� �����
//        gc.strokeArc(73 + xC, 0 + yC, 60, 100, 0, 180, ArcType.OPEN); // ������ �����
//        gc.setFill(Color.SADDLEBROWN);
//        gc.fillArc(73 + xC, 0 + yC, 60, 100, 0, 180, ArcType.OPEN); // ������ �����
//        gc.setFill(Color.BLACK);
//        double x[] = {104 + (double) xC,94 + (double) xC,104 + (double) xC,104 + (double) xC};
//        double y[] = {105 + (double) yC,124 + (double) yC,124 + (double) yC,105 + (double) yC};
//        gc.fillPolygon(x , y, 4);           // Nose
//        gc.arc(105 + xC,139 + yC,37,22,0,-180);  // ��� ���
//
//        gc.setFill(Color.web("#e35d6a"));
//        gc.fill();
//        gc.setFill(Color.WHITE);
//        gc.fillRect(68.5 + xC,138 + yC,73,8);
//        gc.strokeArc(67.5 + xC,116 + yC,75,45,0,-180,ArcType.ROUND);
//        gc.restore();
//        gc.closePath();

    }

    public void cleanCanvas(){
//        gc.clearRect(0, 0, 351, 380);
//        storage.stream().forEach(x ->
//                drawHuman(x, x.getX(), x.getY(), x.getSize()));
    }

    public void moveHuman(Dragon dragon, double fromX, double fromY, double toX, double toY, double fromSize, double toSize){
//        DoubleProperty x  = new SimpleDoubleProperty();
//        DoubleProperty y  = new SimpleDoubleProperty();
//        DoubleProperty size  = new SimpleDoubleProperty();
//
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.millis(0),
//                        new KeyValue(x, fromX),
//                        new KeyValue(y, fromY),
//                        new KeyValue(size, fromSize)
//                ),
//                new KeyFrame(Duration.millis(340),
//                        new KeyValue(x, toX),
//                        new KeyValue(y, toY),
//                        new KeyValue(size, toSize)
//                )
//        );
//        timeline.delayProperty();
//
//        timeline.setAutoReverse(false);
//        timeline.setCycleCount(1);
//
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                cleanCanvasDrawExcept(dragon);
//                drawHuman(dragon, x.intValue(), y.intValue(), size.intValue());
//            }
//        };
//
//        timer.start();
//        timeline.play();
    }

    public void cleanCanvasDrawExcept(Dragon dragon){
//        gc.clearRect(0, 0, 351, 380);
//        mDataSet
//                .stream()
//                .filter(y -> !y.getId().equals(dragon.getId()))
//                .forEach(x -> drawHuman(x, x.getX(), x.getY(), x.getSize()));
    }

    public void drawSleepyHuman(Dragon dragon, int x, int y) {
//        gc.beginPath();
//        gc.save();
//        gc.scale(0.4 * dragon.getSize(),0.4 * dragon.getSize());
//        gc.strokeArc(20 + x, 85 + y, 20, 40, 70, 280, ArcType.OPEN); // ������� ������ ���
//        gc.strokeArc(170 + x, 85 + y, 20, 42, 80, 360, ArcType.OPEN); // ������� ������� ���
//        gc.setFill(Color.valueOf(server.DataBaseConnection.encryptString(dragon.getOwner()).substring(0,6)));
//        gc.fillOval(30 + x,35 + y,150,150);    // For face
//        gc.fillOval(20 + x, 85 + y,20, 40 );   // ������� ������ ���
//        gc.fillOval(170 + x, 85 + y,20, 42 );  // ������� ������� ���
//        gc.strokeOval(30 + x,35 + y,150,150);  // ������� ����
//        gc.setFill(Color.BLACK);
//        gc.strokeLine(125 + x,100 + y,155 + x,100 + y);
//        gc.strokeLine(50 + x,100 + y,80 + x,100 + y);
//        gc.setFill(Color.SADDLEBROWN);
//        gc.strokeOval(5 + x, 25 + y, 195, 62); // ������� ����� �����
//        gc.fillOval(5 + x, 25 + y, 195, 62);   // ���� �����
//        gc.setFill(Color.BLACK);
//        gc.strokeArc(73 + x, 35 + y, 60, 30, 180, 180, ArcType.OPEN); // ������� ������� �����
//        gc.strokeArc(73 + x, 0 + y, 60, 100, 0, 180, ArcType.OPEN); // ������ �����
//        gc.setFill(Color.SADDLEBROWN);
//        gc.fillArc(73 + x, 0 + y, 60, 100, 0, 180, ArcType.OPEN); // ������ �����
//        gc.setFill(Color.BLACK);
//        double x1[] = {104 + (double) x,94 + (double) x,104 + (double) x,104 + (double) x};
//        double y1[] = {105 + (double) y,124 + (double) y,124 + (double) y,105 + (double) y};
//        gc.fillPolygon(x1 , y1, 4);           // Nose
//        gc.strokeLine(70 + x,150 + y,135 + x,150 + y);
//        gc.restore();
//        gc.closePath();
    }
}
