package buzzz.imca.com.imcabuzzz;

public class Bowler {
    String name;
    int extra,score,wicket;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWicket() {
        return wicket;
    }

    public void setWicket(int wicket) {
        this.wicket = wicket;
    }

    public Bowler() {

    }

    public Bowler(String name, int extra, int score, int wicket) {

        this.name = name;
        this.extra = extra;
        this.score = score;
        this.wicket = wicket;
    }
}
