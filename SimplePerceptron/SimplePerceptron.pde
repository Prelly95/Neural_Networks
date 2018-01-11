Perceptron p;
void setup() {
    size(200, 200);
    p = new Perceptron();
    float[] inputs = {-1, 0.5};
    int guess = p.guess(inputs);
    println(guess);
}

void draw() {

}