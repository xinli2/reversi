public class ReversiModel {
    public static class Posi{
        public Posi() {
        }

        public Posi(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    }
    public static final String title = "Reversi";
    public static int value = 0;
    public static int[] last = {0,0,0,0};
    public static int[] cp = {0,0,0,0};
    public static int b = 2;
    public static int w = 2;
    public static final String[] Colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple"};

    public static class Wrap {
        int id;

        public int getAndUpdate() {
            int c = color;
            color = (color + 1) % 6;
            return c;
        }


        public Wrap(int id, int color) {
            this.id = id;
            this.color = color;
        }

        int color;
    }

}
