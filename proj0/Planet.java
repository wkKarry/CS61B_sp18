import java.awt.*;

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static double G = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return G * this.mass * p.mass / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return calcForceExertedBy(p) * dx / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return calcForceExertedBy(p) * dy / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double NetForce = 0;
        for (Planet planet : planets) {
            if (this.equals(planet)) {
                continue;
            } else {
                NetForce += calcForceExertedByX(planet);
            }
        }
        return NetForce;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double NetForce = 0;
        for (Planet planet : planets) {
            if (this.equals(planet)){
                continue;
            } else {
                NetForce += calcForceExertedByY(planet);
            }
        }
        return NetForce;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx/this.mass;
        double ay = fy/this.mass;
        this.xxVel = this.xxVel+ax*dt;
        this.yyVel = this.yyVel+ay*dt;
        this.xxPos += this.xxVel*dt;
        this.yyPos += this.yyVel*dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}
