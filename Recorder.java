package tankgame5;

import java.io.*;
import java.util.Vector;

/**
 * @author 苦瓜
 * 我亦无他，惟手熟尔。
 * Time:2022年05月18日
 */
// 纪录相关信息 IO交互
public class Recorder {
    private static int allEnemyTankNum = 0;
    // 定义IO对象
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String RecorderFile = "src\\myRecorer.txt";
    private static Vector<EnemyTank> enemyTanks = null; // 定义属性的到敌方坦克列表
    // 定义一个Node 的 Vector，用于报存敌人信息
    private static Vector<Node> nodes = new Vector<>();
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
    // 读取文件数据，恢复上局游戏
    public static Vector<Node> getNodesAndEnemyTankNwm() {
        try {
            br = new BufferedReader(new FileReader(RecorderFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            // 循环读取文件，生成集合
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return nodes;
    }
    public static void savainfo() {

        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(RecorderFile), "utf8");
            bw = new BufferedWriter(osw);
            bw.write(allEnemyTankNum + "");
            bw.newLine();

            // 保存敌人坦克的信息，继续游戏
            for (int i = 0; i < enemyTanks.size(); i++) {
                // 取出保存
                EnemyTank enemyTank = enemyTanks.get(i);
                // 保存
                String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                // 写入到文件
                bw.write(record);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    public static void addALLEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }
}
