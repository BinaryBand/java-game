class Camera {

    private int width, height;
    private Object subject;
    private int x, y;

    Camera(int width, int height) {

        this.width = width;
        this.height = height;
    }

    void update() {

        this.x = (int) (this.subject.getX() + (this.subject.getWidth() / 2.0) - (this.width / 2.0));
        this.y = (int) (this.subject.getY() + (this.subject.getHeight() / 2.0) - (this.height / 2.0));
    }

    void setSubject(Object subject) { this.subject = subject; }

    double getX() { return x; }
    double getY() { return y; }
}
