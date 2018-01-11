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

Perceptron brain;

Point[] points = new Point[100];
int trainingIndex = 0;

public void setup() {
    
    brain = new Perceptron();

    for(int i = 0; i < points.length; i++) {
        points[i] = new Point();
    }
    float[] inputs = {-1, 0.5f};
    int guess = brain.guess(inputs);
    println(guess);
}

public void draw() {
    background(255);
    stroke(0);
    line(0,0,width,height);

    for(Point pt : points) {
        pt.show();
    }

    for(Point pt : points) {
        float[] inputs = {pt.x, pt.y};
        int target = pt.label;
        int guess = brain.guess(inputs);
        if(guess == target) {
            fill(0, 255, 0);
        } else {
            fill(255, 0, 0);
        }
        noStroke();
        ellipse(pt.x, pt.y, 32, 32);
    }

    Point training = points[trainingIndex];
    float[] inputs = {training.x, training.y};
    int target = training.label;
    brain.train(inputs, target);
    trainingIndex++;
    if(trainingIndex == points.length) {
        trainingIndex = 0;
    }
}

public void mousePressed() {
    for(Point pt : points) {
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
    float lr = 0.1f;

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

    public void train(float[] inputs, int target){
        int guess = guess(inputs);
        int error = target - guess;

        // Tune all the weights
        for(int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * lr;
        }
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
        ellipse(x, y, 32, 32);
    }
}
    public void settings() {  size(800, 800); }
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "SimplePerceptron" };
        if (passedArgs != null) {
          PApplet.main(concat(appletArgs, passedArgs));
        } else {
          PApplet.main(appletArgs);
        }
    }
}
