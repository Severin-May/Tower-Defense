package model;

public class AI extends Player{
    String Strategy;
    public AI(String NameForAI) {
        super (NameForAI);
    }

    public String getStrategy() {
        return Strategy;
    }

    public void setStrategy(String strategy) {
        Strategy = strategy;
    }
}
