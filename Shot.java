package tankgame5;

/**
 * @author 苦瓜
 * 我亦无他，惟手熟尔。
 * Time:2022年05月12日
 */
public class Shot implements Runnable {
    int x;  // 子弹坐标
    int y;
    int direct = 0; // 子弹坐标
    int speed = 10;
    boolean isLive = true;  // 子弹是否存活

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() { // 子弹线程
            while (true) {
                // 子弹（线程）休眠
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 根据方向改变坐标
                switch (direct) {
                    case 0: // 上射击
                        y -= speed;
                        break;
                    case 2: // 下射击
                        y += speed;
                        break;
                    case 1: // 右射击
                        x += speed;
                        break;
                    case 3: // 左射击
                        x -= speed;
                        break;
                }

                // 测试
                System.out.println("x=" + x + " y=" + y);
                //  子弹击中坦克销毁子弹
                //子弹销毁边界
                if ( !(x >= 0 && x <= 1000 && y >= 0 && y <= 750  && isLive)) {
                    isLive = false;
                    break;
                }
            }
    }
}
