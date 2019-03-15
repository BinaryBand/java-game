import java.util.ArrayList;

class Camera {

    private int x, y, screenWith, screenHeight;
    private ArrayList<Object> subjects = new ArrayList<>();
    private double angle, zoom;

    Camera(int width, int height) {

        this.screenWith = width;
        this.screenHeight = height;

        this.angle = 0;
        this.zoom = 1;
    }

    void update() {

        if (subjects.size() == 0) {

            x = 0;
            y = 0;
        }
        else {

            int tempX = 0;
            int tempY = 0;

            ArrayList<Object> removeList = new ArrayList<>();

            for (Object item : subjects) {

                if (item.getExists()) {

                    tempX += item.getX() + item.getWidth() / 2;
                    tempY += item.getY() + item.getHeight() / 2;
                }
                else {

                    removeList.add(item);
                }
            }

            for (Object item : removeList) {

                subjects.remove(item);
            }

            x = ((tempX / subjects.size()) + x) / 2;
            y = ((tempY / subjects.size()) + y) / 2;
        }
    }

    void addSubject(Object subject) { subjects.add(subject); }

    int getX() { return x - screenWith / 2; }
    int getY() { return y - screenHeight / 2; }

    int getWidth() { return screenWith; }
    int getHeight() { return screenHeight; }

    double getAngle() { return angle; }
    void setAngle(double angle) { this.angle = angle; }

    double getZoom() { return zoom; }
    public void setZoom(double zoom) { this.zoom = zoom; }
}
