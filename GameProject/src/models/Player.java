package models;

public class Player {

    private String name;
    private int lives;
    private long score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.lives = 3;

        // TODO: gain 1 life every X points
    }

    public long getScore() {
        return this.score;
    }

    public void setScore(long score) {
        this.score += score;
    }

    public int getLives() {
        return this.lives;
    }

    public void removeLife() {
        this.lives -= 1;
    }
    public void receiveLife() {
        this.lives += 1;
    }

    public boolean isAlive() {
        return this.lives > 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
