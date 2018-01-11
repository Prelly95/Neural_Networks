Perceptron p;

Point[] points = new Point[100];


void setup() {
    size(500, 500);
    p = new Perceptron();

    for(int i = 0; i < points.length; i++) {
        points[i] = new Point();
    }
    float[] inputs = {-1, 0.5};
    int guess = p.guess(inputs);
    println(guess);
}

void draw() {
    background(255);
    stroke(0);
    line(0,0,width,height);
    for(Point p : points) {
        p.show();
    }

}