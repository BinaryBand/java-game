class Camera {

    private int x, y, width, height;
    private Object subject;

    Camera(int width, int height) {

        this.width = width;
        this.height = height;
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

    int getWidth() { return this.width; }
    int getHeight() { return this.height; }

    int getX() { return x; }
    int getY() { return y; }
}
