import java.util.ArrayList;

class GameFunctions {

    void handleCollisions(ArrayList<Object> items) {

        int x1 = 0;
        for (Object item : items) {

            if (item.getX() < x1) {

                x1 = (int) (item.getX());
            }
        }

        int y1 = 0;
        for (Object item : items) {

            if (item.getY() < y1) {

                y1 = (int) (item.getY());
            }
        }

        int x2 = 0;
        for (Object item : items) {

            if (item.getX() + item.getWidth() > x2) {

                x2 = (int) (item.getX() + item.getWidth());
            }
        }

        int y2 = 0;
        for (Object item : items) {

            if (item.getY() + item.getHeight() > y2) {

                y2 = (int) (item.getY() + item.getHeight());
            }
        }

        // Check for collisions
        quadTree(x1, y1, x2, y2, items);
    }

    private void quadTree(int x1, int y1, int x3, int y3, ArrayList<Object> objects) {

        if (4 <= objects.size() && 8 <= x3 - x1) {

            ArrayList<Object> q1 = new ArrayList<>();
            ArrayList<Object> q2 = new ArrayList<>();
            ArrayList<Object> q3 = new ArrayList<>();
            ArrayList<Object> q4 = new ArrayList<>();

            int x2 = ((x3 - x1) / 2) + x1;
            int y2 = ((y3 - y1) / 2) + y1;

            for (Object item : objects) {

                if ((x2 > item.getX() + item.getXSpeed()) &&
                        (y2 > item.getY() + item.getYSpeed()) &&
                        (item.getX() + item.getWidth() + item.getXSpeed() > x1) &&
                        (item.getY() + item.getHeight() + item.getYSpeed() > y1)) {

                    q1.add(item);
                }

                if ((x3 > item.getX() + item.getXSpeed()) &&
                        (y2 > item.getY() + item.getYSpeed()) &&
                        (item.getX() + item.getWidth() + item.getXSpeed()> x2) &&
                        (item.getY() + item.getHeight() + item.getYSpeed() > y1)) {

                    q2.add(item);
                }

                if ((x2 > item.getX() + item.getXSpeed()) &&
                        (y3 > item.getY() + item.getYSpeed()) &&
                        (item.getX() + item.getWidth() + item.getXSpeed()> x1) &&
                        (item.getY() + item.getHeight() + item.getYSpeed() > y2)) {

                    q3.add(item);
                }

                if ((x3 > item.getX() + item.getXSpeed()) &&
                        (y3 > item.getY() + item.getYSpeed()) &&
                        (item.getX() + item.getWidth() + item.getXSpeed()> x2) &&
                        (item.getY() + item.getHeight() + item.getYSpeed() > y2)) {

                    q4.add(item);
                }
            }

            quadTree(x1, y1, x2, y2, q1);
            quadTree(x2, y1, x3, y2, q2);
            quadTree(x1, y2, x2, y3, q3);
            quadTree(x2, y2, x3, y3, q4);
        }
        else {

            for (int i = 0; i < objects.size(); i++) {

                Object first = objects.get(i);
                for (int j = i + 1; j < objects.size(); j++) {

                    Object second = objects.get(j);

                    if ((second.getX() + second.getWidth() + second.getXSpeed() > first.getX() + first.getXSpeed()) &&
                            (second.getY() + second.getHeight() + second.getYSpeed() > first.getY() + first.getYSpeed()) &&
                            (first.getX() + first.getWidth() + first.getXSpeed() > second.getX() + second.getXSpeed()) &&
                            (first.getY() + first.getHeight() + first.getYSpeed() > second.getY() + second.getYSpeed())) {

                        first.addCollision(second);
                        second.addCollision(first);
                    }
                }
            }
        }
    }

}
