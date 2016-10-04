/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package Common;

public class Vector2 {
    // makes more sense to be public rather than encapsulated for no reason
    public double x, y;
    public final double DELTA = 0.0001;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    // basic calculations
    public Vector2 normalizedCopy() {
        Vector2 norm = this.copy();
        norm.setDistance(1);
        return norm;
    }

    public boolean equals(Vector2 n) {
        if(Math.abs(n.x - this.x) < DELTA && Math.abs(n.y - this.y) < DELTA)
            return true;
        return false;
    }

    public Vector2 copy() {
        Vector2 n = new Vector2(x, y);
        return n;
    }

    public Vector2 multiply(double v) {
        Vector2 n = new Vector2(this.x, this.y);
        n.x *= v;
        n.y *= v;
        return n;
    }

    public Vector2 add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 add(Vector2 pos) {
        this.x += pos.x;
        this.y += pos.y;
        return this;
    }

    // advanced attributes and setters

    public Vector2 vectorTo(Vector2 towards) {
        return new Vector2(towards.x - x, towards.y - y);
    }

    public double distance(Vector2 c) {
        double a = (x - c.x);
        double b = (y - c.y);
        return Math.sqrt(a * a + b * b);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public void setDistance(double d) {
        double mag = magnitude();
        y = y / mag * d;
        x = x / mag * d;
    }

    // Setters for convenience and single-line operations

    public void set(Vector2 pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "X=" + x + ", Y=" + y;
    }
}
