package model;

import java.awt.*;

/**
 * buff,
 * 坐标
 * 种类
 * 根据种类执行效果
 *
 * 回血
 * 改变子弹
 */

public class Prop {
    private int type;
    private int x;
    private int y;
    private boolean isAlive;
    private long time;
    private static final String[] img = new String[]{"pictures/prop/Plasma.png", "pictures/prop/Thruster.png", "pictures/prop/Shield.png", "pictures/prop/Repair_System.png"};
    private MyPanel father;
    private long lastTime;
    private int cnt;
    public Prop(int x, int y, int type, MyPanel father) {
        this.setX(x);
        this.setY(y);
        this.setType(type);
        this.setAlive(true);
        this.setTime(System.currentTimeMillis());
        this.setLastTime(System.currentTimeMillis());
        this.setFather(father);
        this.cnt = 0;
    }

    public static String[] getImg() {
        return img;
    }

    public void setFather(MyPanel father) {
        this.father = father;
    }

    public MyPanel getFather() {
        return this.father;
    }

    public void draw(Graphics g) {
        long t = System.currentTimeMillis();
        if (t - this.getTime() <= Const.prop_gap / 2) {
            g.drawImage(Toolkit.getDefaultToolkit().getImage(img[this.getType()]), this.getX(), this.getY(), Const.propWidth, Const.propWidth, this.getFather());
        } else if (t - this.getTime() <= Const.prop_gap) {
            if(t - this.getLastTime() >= 500) {
                this.cnt++;
                this.setLastTime(t);
            }
            if(cnt % 2 == 0) {
                g.drawImage(Toolkit.getDefaultToolkit().getImage(img[this.getType()]), this.getX(), this.getY(), Const.propWidth, Const.propWidth, this.getFather());
            }
        } else {
            this.setAlive(false);
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }
}
