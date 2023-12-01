package org.example.game_service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;


@Service
public class GameRunning {
    private final int bulletSpeed = 15;
    private final int speed = 5;

    @PostConstruct
    private void init() {
        GameInitialize.gameRunning = this;
    }
    protected Integer getDegrees(Point p1, Point p2) {
        //得到两个坐标点的差值， 其实得到的dx 和dy 就是我们线条的向量了
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;

        if (Math.abs(dx) < 13 && Math.abs(dx) < 13)
            return null;
        double angleRadians = Math.atan2(dy, dx);
        //根据该方法，可以直接获取坐标点和x轴的夹角，返回的是一个-π到π之间的弧度值
        double degrees = Math.toDegrees(angleRadians);
        //调用Math的API 将弧度转为角度，角度值范围为±180°。
        if (degrees < 0)
            degrees += 90;
        else if (degrees > 0 && degrees < 90)
            degrees = 90 + degrees;
        else if (degrees > 90)
            degrees = 90 + degrees;
        return (int) degrees;
    }

    protected Point getAircraftAndBulletSpeed(Point aircraftCoordinate, Point mouseCoordinate) {

        int distanceX = mouseCoordinate.x - aircraftCoordinate.x;
        int distanceY = mouseCoordinate.y - aircraftCoordinate.y;

        int DX = Math.abs(distanceX);
        int DY = Math.abs(distanceY);

        int DA = 5;
        if (DX < 3 && DY < 3)
            return null;

        int speedX, speedY, bulletSpeedX, bulletSpeedY;

        double proportion = (Math.min(DX, DY) + .0) / Math.max(DX, DY);
        if (DX > DY) {
            speedX = this.speed;
            bulletSpeedX = this.speed + bulletSpeed;
            speedY = (int) Math.round(this.speed * proportion);
            bulletSpeedY = (int) Math.round((this.speed + bulletSpeed) * proportion);
        } else {
            speedY = this.speed;
            bulletSpeedY = this.speed + bulletSpeed;
            speedX = (int) Math.round(this.speed * proportion);
            bulletSpeedX = (int) Math.round((this.speed + bulletSpeed) * proportion);
        }

        if (distanceX > 0) {
            aircraftCoordinate.x += speedX;
            if (DX < DA && DY < DA) aircraftCoordinate.x += distanceX;
        } else if (distanceX < 0) {
            bulletSpeedX = -bulletSpeedX;
            speedX = -speedX;
            aircraftCoordinate.x += speedX;
            if (DX < DA && DY < DA) aircraftCoordinate.x += -distanceX;
        }

        if (distanceY > 0) {
            aircraftCoordinate.y += speedY;
            if (DX < DA && DY < DA) aircraftCoordinate.y += distanceY;
        } else if (distanceY< 0) {
            bulletSpeedY =-bulletSpeedY;
            speedY = -speedY;
            aircraftCoordinate.y += speedY;
            if (DX < DA && DY < DA) aircraftCoordinate.y += -distanceY;
        }

        return new Point(bulletSpeedX, bulletSpeedY);
    }


}
