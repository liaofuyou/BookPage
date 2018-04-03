package me.ajax.bookpage;

import android.graphics.Point;
import android.util.Log;

/**
 * Created by aj on 2018/4/3
 */

public class GeometryUtils {

    public static void main(String[] args) {
        GeometryUtils.getIntersection(new Point(1, 2), new Point(1, 2),
                new Point(1, 2), new Point(1, 2));
        GeometryUtils.getIntersection(new Point(1, 2), new Point(1, 2),
                new Point(1, 4), new Point(1, 4));
        GeometryUtils.getIntersection(new Point(100, 1), new Point(100, 100),
                new Point(100, 101), new Point(100, 400));
        GeometryUtils.getIntersection(new Point(5, 5), new Point(100, 100),
                new Point(100, 5), new Point(5, 100));
    }

    /**
     * 判断两条线是否相交 a 线段1起点坐标 b 线段1终点坐标 c 线段2起点坐标 d 线段2终点坐标 intersection 相交点坐标
     * reutrn 是否相交: 0 : 两线平行 -1 : 不平行且未相交 1 : 两线相交
     */

    private static int getIntersection(Point a, Point b, Point c, Point d) {
        Point intersection = new Point(0, 0);

        if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) + Math.abs(d.y - c.y)
                + Math.abs(d.x - c.x) == 0) {
            if ((c.x - a.x) + (c.y - a.y) == 0) {
                System.out.println("ABCD是同一个点！");
            } else {
                System.out.println("AB是一个点，CD是一个点，且AC不同！");
            }
            return 0;
        }

        if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) == 0) {
            if ((a.x - d.x) * (c.y - d.y) - (a.y - d.y) * (c.x - d.x) == 0) {
                System.out.println("A、B是一个点，且在CD线段上！");
            } else {
                System.out.println("A、B是一个点，且不在CD线段上！");
            }
            return 0;
        }
        if (Math.abs(d.y - c.y) + Math.abs(d.x - c.x) == 0) {
            if ((d.x - b.x) * (a.y - b.y) - (d.y - b.y) * (a.x - b.x) == 0) {
                System.out.println("C、D是一个点，且在AB线段上！");
            } else {
                System.out.println("C、D是一个点，且不在AB线段上！");
            }
            return 0;
        }

        if ((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y) == 0) {
            System.out.println("线段平行，无交点！");
            return 0;
        }

        intersection.x = ((b.x - a.x) * (c.x - d.x) * (c.y - a.y) -
                c.x * (b.x - a.x) * (c.y - d.y) + a.x * (b.y - a.y) * (c.x - d.x)) /
                ((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y));

        intersection.y = ((b.y - a.y) * (c.y - d.y) * (c.x - a.x) - c.y
                * (b.y - a.y) * (c.x - d.x) + a.y * (b.x - a.x) * (c.y - d.y))
                / ((b.x - a.x) * (c.y - d.y) - (b.y - a.y) * (c.x - d.x));

        if ((intersection.x - a.x) * (intersection.x - b.x) <= 0
                && (intersection.x - c.x) * (intersection.x - d.x) <= 0
                && (intersection.y - a.y) * (intersection.y - b.y) <= 0
                && (intersection.y - c.y) * (intersection.y - d.y) <= 0) {

            System.out.println("线段相交于点(" + intersection.x + "," + intersection.y + ")！");
            return 1; // '相交
        } else {
            System.out.println("线段相交于虚交点(" + intersection.x + "," + intersection.y + ")！");
            return -1; // '相交但不在线段上
        }
    }

    /**
     * 取得直线交点X
     * <p>
     * 参考： http://www.360doc.com/content/15/0325/22/15028327_458051859.shtml
     */
    double intersectionX(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return ((x2 - x1) * (x3 - x4) * (y3 - y1) -
                x3 * (x2 - x1) * (y3 - y4) + x1 * (y2 - y1) * (x3 - x4)) /
                ((y2 - y1) * (x3 - x4) - (x2 - x1) * (y3 - y4));
    }

    /**
     * 取得直线交点Y
     */
    double intersectionY(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return ((y2 - y1) * (y3 - y4) * (x3 - x1) -
                y3 * (y2 - y1) * (x3 - x4) + y1 * (x2 - x1) * (y3 - y4)) /
                ((y2 - y1) * (y3 - y4) - (y2 - y1) * (x3 - x4));
    }

    float intersectionY(float p, double angle) {
        return (float) (p * Math.sin(Math.toRadians(angle)));
    }

    /**
     * 极坐标转换为直角坐标
     */
    float getXFromPolar(float p, double angle) {
        return (float) (p * Math.cos(Math.toRadians(angle)));
    }

    /**
     * 极坐标转换为直角坐标
     */
    float getYFromPolar(float p, double angle) {
        return (float) (p * Math.sin(Math.toRadians(angle)));
    }

    void l(Object o) {
        Log.e("######", o.toString());
    }
}
