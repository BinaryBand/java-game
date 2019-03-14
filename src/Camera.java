class Camera {

    private int x, y, width, height;
    private Object subject;
    private double angle;

    Camera(int width, int height) {

        this.width = width;
        this.height = height;

        this.angle = 0;
    }

    void update() {

        if (subject != null) {

            x = subject.getX() + (subject.getWidth() / 2) - (width / 2);
            y = subject.getY() + (subject.getHeight() / 2) - (height / 2);
        }
        else {

            x = 0;
            y = 0;
        }
    }

    void setSubject(Object subject) { this.subject = subject; }

    int getX() { return x; }
    int getY() { return y; }

    int getWidth() { return width; }
    int getHeight() { return height; }

    double getAngle() { return angle; }
    void setAngle(double angle) { this.angle = angle; }
}
