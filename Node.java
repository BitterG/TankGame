package tankgame5;

/**
 * @author 苦瓜
 * 我亦无他，惟手熟尔。
 * Time:2022年05月18日
 */
public class Node {
    private int x;
    private int y;
    private int direct;

    public Node(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirect() {
        return direct;
    }
}
