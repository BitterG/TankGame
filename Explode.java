package tankgame5;

/**
 * @author 苦瓜
 * 我亦无他，惟手熟尔。
 * Time:2022年05月13日
 */
public class Explode {
    int x, y;   // 爆炸位置
    int life = 9;   // 炸弹生命周期
    boolean isLive = true; // 存活

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 减少生命值
    public void lifeDown() {    // 配合图片出现爆炸效果
        if (life > 0) {
            life --;
        } else {
            isLive = false;
        }
    }
}
