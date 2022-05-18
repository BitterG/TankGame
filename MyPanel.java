package tankgame5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 苦瓜
 * 我亦无他，惟手熟尔。
 * Time:2022年05月09日
 */
// 绘图区域-背景
// 为使Panel不停的更新 也要实现Runnable当作线程使用
public class MyPanel extends JPanel implements KeyListener,Runnable {
    // 定义我的坦克
    MyTank myTank = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Node> nodes = null;    // 定义一个存放Node对象的Vector
    int enemyTankSize = 9;  // 设置敌人数量
    // 定义一个Vector，存放炸弹
    // 当子弹击中坦克时加入一个explode对象到Vector
    Vector<Explode> explodes = new Vector<>();
    // 定义三张图片，显示爆炸
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {    // key 输入代表新游戏还是继续上一局
        nodes = Recorder.getNodesAndEnemyTankNwm(); // 读取恢复
        // 将MyPanel对象的EnemyTanks设置给Recorder
        Recorder.setEnemyTanks(enemyTanks);

        myTank = new MyTank(5, 100);  //  初始化坦克
        myTank.setSPEED(5); // 设置速度

        switch (key) {
            case "1":
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank(100*(i + 1), 20);   // 创建敌方坦克对象
                    EnemyTank enemyTank1 = new EnemyTank(100*(i + 1), 300);
                    EnemyTank enemyTank2 = new EnemyTank(100*(i + 1), 500);

                    enemyTank.setEnemyTanks(enemyTanks);    // 将enmyTanks设置给enemyTank~
                    enemyTank1.setEnemyTanks(enemyTanks);
                    enemyTank2.setEnemyTanks(enemyTanks);

                    enemyTank.setDirect(2); // 改方向
                    enemyTank1.setDirect(2);
                    enemyTank2.setDirect(2);
                    // 启动敌人坦克线程
                    new Thread(enemyTank).start();
                    new Thread(enemyTank1).start();
                    new Thread(enemyTank2).start();
                    Shot shot = new Shot(enemyTank.getX()+22, enemyTank.getY()+60, enemyTank.getDirect());  // 给敌方坦克 加入子弹
                    Shot shot1 = new Shot(enemyTank1.getX()+22, enemyTank1.getY()+60, enemyTank1.getDirect());
                    Shot shot2 = new Shot(enemyTank2.getX()+22, enemyTank2.getY()+60, enemyTank2.getDirect());
                    enemyTank.shots.add(shot);  // 加入
                    enemyTank.shots.add(shot1);
                    enemyTank.shots.add(shot2);
                    new Thread(shot).start();   // 当作线程启动
                    new Thread(shot1).start();
                    new Thread(shot2).start();
                    enemyTanks.add(enemyTank);  // 添加到数组
                    enemyTanks.add(enemyTank1);
                    enemyTanks.add(enemyTank2);
                }
                break;
            case "2":   // 继续上局游戏
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());   // 创建敌方坦克对象
                    enemyTank.setEnemyTanks(enemyTanks);    // 将enmyTanks设置给enemyTank~
                    enemyTank.setDirect(node.getDirect()); // 改方向
                    // 启动敌人坦克线程
                    new Thread(enemyTank).start();
                    Shot shot = new Shot(enemyTank.getX()+22, enemyTank.getY()+60, enemyTank.getDirect());  // 给敌方坦克 加入子弹
                    enemyTank.shots.add(shot);  // 加入
                    new Thread(shot).start();   // 当作线程启动
                    enemyTanks.add(enemyTank);  // 添加到数组
                }
                break;
            default:
                System.out.println("输入有误！");
        }
        // 初始化敌人坦克

        // 初始化爆炸图片对象
        image1 = Toolkit.getDefaultToolkit().getImage("out/production/explode_0.png");
        image2 = Toolkit.getDefaultToolkit().getImage("out/production/explode_1.png");
        image3 = Toolkit.getDefaultToolkit().getImage("out/production/explode_2.png");
    }
    // 提示信息，显示击毁信息
    public void showinfo(Graphics g) {
        // 显示成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("累计击毁数量: ",1024, 30);
        g.drawString("X",1100, 80);
        g.drawString(Recorder.getAllEnemyTankNum() + "",1150, 80); // 数量显示
        drawTank(1020, 40, g, 0, 1);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750); // 填充背景
        showinfo(g);
        // 绘制坦克
        if (myTank.isLive) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0);
        }
        // 画出我方子弹
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot = myTank.shots.get(i);
            if (shot != null && shot.isLive) {
            g.fill3DRect(shot.x, shot.y, 3, 3, false);
            } else {
                myTank.shots.remove(shot);
            }
        }
//        if (myTank.shot != null && myTank.shot.isLive) {
//            g.fill3DRect(myTank.shot.x, myTank.shot.y, 3, 3, false);
//        }
        // 若Explode集合不为空则需要画出炸弹

        for (int i = 0; i < explodes.size(); i++) {
            // 取出炸弹
            Explode explode = explodes.get(i);
            if (explode.life > 6) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g.drawImage(image3, explode.x, explode.y, 60, 60, this);
            } else if (explode.life > 3) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g.drawImage(image2, explode.x, explode.y, 60, 60, this);
            } else {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g.drawImage(image1, explode.x, explode.y, 60, 60, this);
            }
            // 炸弹生命值减少
            explode.lifeDown();
            // 如果爆炸的生命值为0后，从集合中移除
            if (explode.life == 0) {
                explodes.remove(explode);
            }
        }

        // 遍历画出敌人坦克 遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            // 判断坦克是否存活 才画出
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                // 画出敌人所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    // 取出
                    Shot shot = enemyTank.shots.get(j);
                    // 绘制
                    if (shot.isLive) { // islive == true
                        g.fill3DRect(shot.x, shot.y, 3, 3, false);
                    } else {
                        // 从Vector移除false线程
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

    }
    // 编写方法，绘制坦克

    /**
     *
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param g 画笔
     * @param direct 坦克朝向
     * @param type 坦克类型（颜色变化）
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        // 根据阵营设置不同颜色
        switch (type) {
            case 0: // 我方坦克
                g.setColor(Color.GREEN);
                break;
            case 1: // 敌方坦克
                g.setColor(Color.magenta);
                break;
        }

        // 根据方向，绘制不同方向坦克
        switch (direct) {   // direct 0上 1右 2下 3左
            case 0: //  默认0为朝上
                g.fill3DRect(x, y, 10, 60, false);  // 左轮
                g.fill3DRect(x+40, y, 10, 60, false);   // 右轮
                g.fill3DRect(x+10, y+10, 30, 40, false);   // 中心方块
                g.drawOval(x+12, y+18, 25, 25);   // 中心顶盖
                g.fill3DRect(x+22, y-5, 3, 25, false);   // 炮筒
                break;
            case 1: //  默认1为右
                g.fill3DRect(x, y, 60, 10, false);  // 左轮
                g.fill3DRect(x, y+40, 60, 10, false);   // 右轮
                g.fill3DRect(x+10, y+10, 40, 30, false);   // 中心方块
                g.drawOval(x+18, y+12, 25, 25);   // 中心顶盖
                g.fill3DRect(x+40, y+22, 25, 3, false);   // 炮筒
                break;
            case 2: //  默认2为朝下
                g.fill3DRect(x, y, 10, 60, false);  // 左轮
                g.fill3DRect(x+40, y, 10, 60, false);   // 右轮
                g.fill3DRect(x+10, y+10, 30, 40, false);   // 中心方块
                g.drawOval(x+12, y+18, 25, 25);   // 中心顶盖
                g.fill3DRect(x+22, y+42, 3, 25, false);   // 炮筒
                break;
            case 3: //  默认1为右
                g.fill3DRect(x, y, 60, 10, false);  // 左轮
                g.fill3DRect(x, y+40, 60, 10, false);   // 右轮
                g.fill3DRect(x+10, y+10, 40, 30, false);   // 中心方块
                g.drawOval(x+18, y+12, 25, 25);   // 中心顶盖
                g.fill3DRect(x-6, y+22, 25, 3, false);   // 炮筒
                break;
            default:
                System.out.println("其他方向待定");
        }
    }

    public void hitEnemyTank(Shot shot, EnemyTank enemyTank) {
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot1 = myTank.shots.get(i);
            // 判断坦克是否销毁（被击中）
            if (shot1 != null && shot1.isLive) {
                // 遍历敌人坦克
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank1 = enemyTanks.get(j);    // 取出每一个坦克判断
                    hitTank(shot, myTank);
                }
            }
        }
    }
    // 判断敌方坦克击中我方
    public void hitMyTank() {
        // 遍历所有敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            // 取出
            EnemyTank enemyTank = enemyTanks.get(i);
            // 遍历坦克所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                // 判断是否击中我方坦克
                if (myTank.isLive && shot.isLive) {
                    hitTank(shot, myTank);
                }
            }
        }
    }
    //  判断子弹是否击中
    public void hitEnemyTank() {
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot1 = myTank.shots.get(i);
            // 判断坦克是否销毁（被击中）
            if (shot1 != null && shot1.isLive) {
                // 遍历敌人坦克
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank1 = enemyTanks.get(j);    // 取出每一个坦克判断
                    hitTank(shot1, enemyTank1);
                }
            }
        }
    }
    public void hitTank(Shot s, Tank enemyTank) {
        // 判断击中
        switch (enemyTank.getDirect()) {
            case 0: // 上下方向
            case 2:
                if ( (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 50) &&
                        (s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) ) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    // 创建explode对象，加入到Vector
                    Explode explode = new Explode(enemyTank.getX() + 10, enemyTank.getY() + 10);
                    explodes.add(explode);
                    enemyTanks.remove(enemyTank);   // 移除死亡坦克
                    if (enemyTank instanceof EnemyTank) {
                        Recorder.addALLEnemyTankNum();
                    }
                }
                break;
            case 1:
            case 3:
                if ( (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60) &&
                        (s.y > enemyTank.getY() && s.y < enemyTank.getY() + 50) ) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    Explode explode = new Explode(enemyTank.getX() + 20, enemyTank.getY() + 8);
                    explodes.add(explode);
                    enemyTanks.remove(enemyTank);   // 移除死亡坦克
                    if (enemyTank instanceof EnemyTank) {
                        Recorder.addALLEnemyTankNum();
                    }
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 处理按键的输入
    @Override
    public void keyPressed(KeyEvent e) {
        int speed = myTank.getSPEED();
        // 改变方向
        if (e.getKeyCode() == KeyEvent.VK_W) {          // w 上 0
            myTank.setDirect(0);
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {   // A 左 3
            myTank.setDirect(3);
            myTank.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {   // S 下 2
            myTank.setDirect(2);
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {   // D 右 1
            myTank.setDirect(1);
            myTank.moveRight();
        }
        //  J 键发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            // 判断子弹消亡
//            if (myTank.shot == null || !myTank.shot.isLive) { // 每次单颗子弹方案
//                myTank.myTankShot();
//            }
            myTank.myTankShot();    // 连发方案
        }

        this.repaint(); // 重绘（更新面板）
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() { // sleep间隔自动重绘
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hitEnemyTank();
            hitMyTank();
            this.repaint();
        }
    }
}
