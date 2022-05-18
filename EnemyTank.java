package tankgame5;

import java.util.Vector;

/**
 * @author 苦瓜
 * 我亦无他，惟手熟尔。
 * Time:2022年05月09日
 */
public class EnemyTank extends Tank implements Runnable {
    // 敌方坦克
    Vector<Shot> shots = new Vector<>();    // 列表保留坦克多个敌人子弹线程
    //    boolean isLive = true;  // 存活
    // 增加属性，EnemyTank 可以得到敌人坦克的Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) { // 提供一个方法将MyPanel里的EnemyVecotr得到
        this.enemyTanks = enemyTanks;
    }

    //  编写方法，检测当前坦克和集合中的其它坦克是否重合
    public boolean isTouchEnemyTank() {
        // 判断当前敌人坦克（this）方向
        switch (this.getDirect()) {
            case 0: // 上
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 取出一个坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 排除自己
                    if (enemyTank != this) {
                        // 如果敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
//                            enemyTank.getX(), enemyTank.getX() + 50
//                            enemyTank.getY(), enemyTank.getY() + 60
//                            enemyTank.getX(), enemyTank.getY()
//                            enemyTank.getX() + 50, enemyTank.getY()
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 50
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 50 >= enemyTank.getX() && this.getX() + 50 <= enemyTank.getX() + 50
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }
                            if (this.getX() + 50 >= enemyTank.getX() && this.getX() + 50 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }
                        }
                    }
                }
            }
                break;
            case 1: // 右
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 取出一个坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 排除自己
                    if (enemyTank != this) {
                        // 如果敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {

                            if (this.getX() + 60>= enemyTank.getX() && this.getX() + 60<= enemyTank.getX() + 50
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 50
                                    && this.getY() + 50 >= enemyTank.getY() && this.getY() + 50 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() + 60>= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 50 >= enemyTank.getY() && this.getY() + 50 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                    }
                }
            }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 取出一个坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 排除自己
                    if (enemyTank != this) {
                        // 如果敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 50
                                    && this.getY() + 60>= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 50 >= enemyTank.getX() && this.getX() + 50 <= enemyTank.getX() + 50
                                    && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                                if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                        && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 50) {
                                    return true;
                                }
                                if (this.getX() + 50 >= enemyTank.getX() && this.getX() + 50 <= enemyTank.getX() + 60
                                        && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60<= enemyTank.getY() + 50) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 取出一个坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 排除自己
                    if (enemyTank != this) {
                        // 如果敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {

                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 50
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 50
                                    && this.getY() + 50 >= enemyTank.getY() && this.getY() + 50 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                                if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                        && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                    return true;
                                }
                                if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                        && this.getY() + 50 >= enemyTank.getY() && this.getY() + 50 <= enemyTank.getY() + 60) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {  // 循环发射子弹
            if (isLive && shots.size() < 10) {
                Shot s = null;
                // 判断坦克方向创建对应子弹
                switch (getDirect()) {
                    case 0: // 向上
                        s = new Shot(getX() + 22, getY() + 3, 0);
                        break;
                    case 1: // 向右
                        s = new Shot(getX() + 63, getY() + 22, 1);
                        break;
                    case 2: // 向下
                        s = new Shot(getX() + 22, getY() + 60, 2);
                        break;
                    case 3: // 向左
                        s = new Shot(getX() - 5, getY() + 22, 3);
                        break;
                }
                shots.add(s);
                // 启动子弹
                new Thread(s).start();
            }
            // 根据朝向移动
            switch (getDirect()) {
                case 0: // 上
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchEnemyTank()) {
                            moveUp();
                        }
                        // 休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1: // 右
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchEnemyTank()) {
                            moveRight();
                        }
                        // 休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2: // 下
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchEnemyTank()) {
                            moveDown();
                        }
                        // 休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3: // 左
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchEnemyTank()) {
                            moveLeft();
                        }
                        // 休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            // 随机改变方向
            setDirect((int) (Math.random() * 4));
            // 被击中 isLive 为 false 取反 为结束线程条件
            if (!isLive) {
                break;
            }
        }
    }
}