package buzzz.imca.com.imcabuzzz;

public class Matches {
    String title,feedback;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Matches() {

    }

    public Matches(String title, String feedback) {

        this.title = title;
        this.feedback = feedback;
    }
}
