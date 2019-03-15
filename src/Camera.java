import java.util.ArrayList;

class Camera {

    private int x, y, width, height;
    private ArrayList<Object> subjects = new ArrayList<>();
    private double angle, zoom;

    Camera(int width, int height) {

        this.width = width;
        this.height = height;

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

            tempX -= width;
            tempY -= height;

            x = ((tempX / subjects.size()) + getX()) / 2;
            y = ((tempY / subjects.size()) + getY()) / 2;
        }
    }

    void addSubject(Object subject) { subjects.add(subject); }

    int getX() { return x; }
    int getY() { return y; }

    int getWidth() { return width; }
    int getHeight() { return height; }

    double getAngle() { return angle; }
    void setAngle(double angle) { this.angle = angle; }

    double getZoom() { return zoom; }
    public void setZoom(double zoom) { this.zoom = zoom; }
}
