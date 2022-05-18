package tankgame5;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author 苦瓜
 * 我亦无他，惟手熟尔。
 * Time:2022年05月09日
 */
public class TankGame05 extends JFrame {
    //定义画笔
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TankGame05 tankGame01 = new TankGame05();
    }

    public TankGame05() {
        System.out.println("请输入先择（1：新游戏，2：继续游戏）：");
        String key = scanner.next();
        mp = new MyPanel(key);

        Thread thread = new Thread(mp); // 将mp放入Thread启动
        thread.start();

        this.add(mp);
        this.setSize(1300, 750);    // 设置背景大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 关闭窗口结束进程
        this.addKeyListener(mp);    // 窗口JFrame 对象可以监听到面板发生的事件
        this.setVisible(true);  // 可视化

        // JFrame 增加关闭响应窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.savainfo();
                System.out.println("===检测到窗口关闭===");
            }
        });
    }
}
