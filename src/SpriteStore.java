
/**
 * SpriteStore class, retrieves sprite resources and caches.
 * Implemented as a Singleton, meaning that there's only a single
 * instance of the object available statically.
 */

import javax.imageio.ImageIO;


public class SpriteStore {
    private static SpriteStore single = new SpriteStore();

    public static SpriteStore get() {
        return this.single;
    }
}