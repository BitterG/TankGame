package tankgame5;

/**
 * @author 苦瓜
 * 我亦无他，惟手熟尔。
 * Time:2022年05月09日
 */
public class Tank {
    private int x;  // 坦克横坐标
    private int y;  // 坦克纵坐标
    private int direct; // 坦克方向0上 1右 2下 3左

    private int SPEED = 5;
    boolean isLive = true;

    public void moveUp() {
        if (getY() > 0) {
            y -= SPEED;
        }
    }
    public void moveDown() {
        if (getY()+95 < 750) {
            y += SPEED;
        }
    }
    public void moveRight() {
        if (getX()+75 < 1000) {
            x += SPEED;
        }
    }
    public void moveLeft() {
        if (getX() > 0) {
            x -= SPEED;
        }
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
