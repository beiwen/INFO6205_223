import java.util.concurrent.ThreadLocalRandom;

public class Color {
    int red;
    int green;
    int blue;

    public Color() {
        this.red = ThreadLocalRandom.current().nextInt(0, 256);
        this.green = ThreadLocalRandom.current().nextInt(0, 256);
        this.blue = ThreadLocalRandom.current().nextInt(0, 256);
    }
}
