package model;

import java.awt.*;
import java.util.Random;

public class Tank implements Attacked{
    private int x;
    private int y;
    private int dir;
    private int hp;
    private int bulletType;
    private int tankType;
    private boolean isAlive;
    private int speed;
    private MyPanel father;
    private Random random;
    private long lastFireTime;
    private String[] img;
    private long[] propTime;

    // 传入的是 敌人 、 玩家
    public Tank(int tankType, MyPanel father, int x, int y){
        this.setX(x);
        this.setY(y);
        this.hp = Const.MAX_HP;
        this.isAlive = true;
        this.tankType = tankType;
        this.setFather(father);
        this.random = new Random();
        this.setLastFireTime(System.currentTimeMillis());
        propTime = new long[]{0, 0, 0};
    }

    public void move(){
        if(canMove()){
            switch (this.dir){
                case Const.UP:
                    this.setY(this.getY()-speed);
                    break;
                case Const.DOWN:
                    this.setY(this.getY()+speed);
                    break;
                case Const.LEFT:
                    this.setX(this.getX()-speed);
                    break;
                case Const.RIGHT:
                    this.setX(this.getX()+speed);
                    break;
            }
        }
    }

    /**
     * 等待被重写
     * @return true or false
     */
    public boolean canMove(){
        return false;
    }

    public void fire(){
        if(canFire()){
            addBullet();
        }
    }

    /**
     * 是否可以发射子弹,对于不同的子类需要重写
     * @return true 可以  false 不可以
     */
    public boolean canFire(){
        return false;
    }

    public void addBullet(){
        int x = this.getX();
        int y = this.getY();
        switch (this.getDir()){
            case Const.UP:
                x += Const.width;
                y -= Const.width;
                break;
            case Const.DOWN:
                x += Const.width;
                y += Const.TankWidth;
                break;
            case Const.RIGHT:
                x += Const.TankWidth;
                y += Const.width;
                break;
            case Const.LEFT:
                x -= Const.width;
                y += Const.width;
                break;
        }

        this.getFather().getBullets().add(new Bullet(x, y, this.getDir(), this.getBulletType(), this.getTankType(), this.getFather()));
//        System.out.println("添加炮弹成功, 横坐标："+this.getX()+"纵坐标："+this.getY()+",还有"+this.getFather().getBullets().size()+"颗炮弹");
    }

    public void draw(Graphics g1){
        int width = 50;
        int y;
        Graphics2D g = (Graphics2D) g1;
        String path = this.getImg()[this.getDir()-1];
        g.drawImage(Toolkit.getDefaultToolkit().getImage(path), this.getX(), this.getY(), Const.TankWidth, Const.TankWidth, this.getFather());
        int hp = this.getHp();
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(3));
        if(this.getY() <= 10) {
            y = this.getY() + Const.TankWidth + 10;
        } else {
            y = this.getY() - 10;
        }
        g.drawRect(this.getX() + 5, y, width, 5);
        g.setColor(Color.red);
        g.fillRect(this.getX() + 5, y, width * hp / Const.MAX_HP, 5);
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDir() {
        return dir;
    }

    public long getLastFireTime() {
        return lastFireTime;
    }

    public void setLastFireTime(long lastFireTime) {
        this.lastFireTime = lastFireTime;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getHp() {
        return hp;
    }

    public MyPanel getFather() {
        return father;
    }

    public void setFather(MyPanel father) {
        this.father = father;
    }

    public void setHp(int hp) {
        if(hp < 0) {
            this.hp = 0;
            this.setAlive(false);
        }else {
            this.hp = hp;
        }
    }

    public int getBulletType() {
        return bulletType;
    }

    public void setBulletType(int bulletType) {
        this.bulletType = bulletType;
    }

    public int getTankType() {
        return tankType;
    }

    public void setTankType(int tankType) {
        this.tankType = tankType;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    public long[] getPropTime() {
        return propTime;
    }

    public void setPropTime(long time, int type) {
        this.propTime[type] = time;
    }

    @Override
    public void beAttacked(int damage) {
        this.setHp(this.getHp()-damage);
//        this.setHp(this.getHp());
    }
}
