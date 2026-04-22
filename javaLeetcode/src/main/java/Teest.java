import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Teest {
    public static void main(String[] args) {
        Map<Key, Integer> map = new HashMap<>(1, 0.01f);
        map.put(new Key("test"), 10);
        System.out.println(map);

        map.put(new Key("test1"), 20);
        System.out.println(map);

        map.put(new Key("test3"), 30);
        System.out.println(map);

        for (int i = 4; i <=  1_000; i++) {
            map.put(new Key("test" + i), i);
        }

        System.out.println(map);
    }

    static class Key {
        String key;

        public Key(String key) {
            this.key = key;
        }

        @Override
        public int hashCode() {
            return 118;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Key key1)) return false;
            return Objects.equals(key, key1.key);
        }
    }
}
