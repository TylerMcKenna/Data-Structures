class Coordinates {
    public int x;
    public int y;
    public int w;
    public int h;
    public Coordinates next;

    public Coordinates(int x, int y, int w, int h, Coordinates next) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.next = next;
    }

    public String toString() {
        String f = "[%d, %d, %d, %d]";
        return String.format(f, this.x, this.y, this.w, this.h);
    }
}

public class CoordinateList {
    public int length;
    private Coordinates head;
    private Coordinates current;

    public CoordinateList() {
        this.head = new Coordinates(-1, -1, -1 , -1, null);
        this.current = this.head;
        this.length = 0;
    }

    public int add(int x, int y, int w, int h) {
        Coordinates toAdd = new Coordinates(x, y, w , h, null);
        this.current.next = toAdd;
        this.current = this.current.next;
        return ++this.length;
    }

    public Coordinates take() {
        Coordinates ret = null;
        if(!this.isEmpty()) {
            ret = this.head.next;
            this.head.next = ret.next;
            this.length--;
        }
        return ret;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }
}