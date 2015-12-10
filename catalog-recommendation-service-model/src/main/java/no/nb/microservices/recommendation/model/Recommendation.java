package no.nb.microservices.recommendation.model;


public class Recommendation {
    private String itemId;
    private double score;

    public Recommendation() {
    }

    public Recommendation(String itemId, double score) {
        this.itemId = itemId;
        this.score = score;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
