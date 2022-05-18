package tankgame5;

import java.util.Vector;

/**
 * @author 苦瓜
 * 我亦无他，惟手熟尔。
 * Time:2022年05月09日
 */
public class MyTank extends Tank {

    Shot shot = null;   // 射击行为
//    boolean isLive = true;  // 存活

    // 自己的坦克
    public MyTank(int x, int y) {
        super(x, y);
    }
    // 子弹集合
    Vector<Shot> shots = new Vector<>();
    // 射击
    public void myTankShot() {
        if (shots.size() > 4) {
            return;
        }
        // 创建shot对象，根据坦克方向位置创建子弹对象
        switch (getDirect()) {
            case 0: // 向上
                shot = new Shot(getX()+22, getY()+3, 0);
                break;
            case 1: // 向右
                shot = new Shot(getX()+63, getY()+22, 1);
                break;
            case 2: // 向下
                shot = new Shot(getX()+22, getY()+60, 2);
                break;
            case 3: // 向左
                shot = new Shot(getX()-5, getY()+22, 3);
                break;
        }

        // 新建子弹加入集合
        shots.add(shot);
        // 启动子弹线程
        new Thread(shot).start();
    }

}