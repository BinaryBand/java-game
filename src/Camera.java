class Camera {

    private int x, y, width, height;
    private Object subject;

    Camera(int width, int height) {

        this.width = width;
        this.height = height;
    }

    void update() {

        if (subject != null) {

            x = (int) (subject.getX() + (subject.getWidth() / 2.0) - (width / 2.0));
            y = (int) (subject.getY() + (subject.getHeight() / 2.0) - (height / 2.0));
        }
        else {

            x = 0;
            y = 0;
        }
    }

    void setSubject(Object subject) { this.subject = subject; }

    int getWidth() { return this.width; }
    int getHeight() { return this.height; }

    int getX() { return x; }
    int getY() { return y; }
}
