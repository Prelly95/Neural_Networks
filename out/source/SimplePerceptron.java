import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SimplePerceptron extends PApplet {

Perceptron p;

Point[] points = new Point[100];


public void setup() {
    
    p = new Perceptron();

    for(int i = 0; i < points.length; i++) {
        points[i] = new Point();
    }
    float[] inputs = {-1, 0.5f};
    int guess = p.guess(inputs);
    println(guess);
}

public void draw() {
    background(255);
    stroke(0);
    line(0,0,width,height);
    for(Point p : points) {
        p.show();
    }

}
// the activation function
public int sign(float n) {
    if (n >= 0) {
        return 1;
    } else {
        return -1;
    }
}

class Perceptron {
    float[] weights = new float[2];

    // constructor
    Perceptron() {
        // Initalise the weightw randomly
        for(int i = 0; i < weights.length; i++) {
            weights[i] = random(-1, 1);
        }
    }

    public int guess(float[] inputs) {
        float sum = 0;
        for(int i = 0; i < weights.length; i++) {
            sum += inputs[i]*weights[i];
        }
        int output = sign(sum);
        return output;
    }
}
class Point {
    float x;
    float y;
    int label;

    Point() {
        x = random(width);
        y = random(height);

        if (x > y) {
            label = 1;
        } else {
            label = -1;
        }
    }

    public void show() {
        stroke(0);
        if(label ==1) {
            fill(255);
        } else {
            fill(0);
        }
        ellipse(x, y, 8, 8);
    }
}
    public void settings() {  size(500, 500); }
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "SimplePerceptron" };
        if (passedArgs != null) {
          PApplet.main(concat(appletArgs, passedArgs));
        } else {
          PApplet.main(appletArgs);
        }
    }
}
