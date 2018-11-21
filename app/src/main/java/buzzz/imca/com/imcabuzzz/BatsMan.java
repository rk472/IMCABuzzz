package buzzz.imca.com.imcabuzzz;

public class BatsMan {
    String name;
    int score;
    boolean out;

    public BatsMan(String name, int score, boolean out) {
        this.name = name;
        this.score = score;
        this.out = out;
    }

    public BatsMan() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }
}
