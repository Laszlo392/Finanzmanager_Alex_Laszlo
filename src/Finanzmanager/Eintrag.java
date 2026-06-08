package Finanzmanager;

public class Eintrag {
    private String zweck;
    private double betrag;

    public Eintrag(String zweck, double betrag) {
        this.zweck = zweck;
        this.betrag = betrag;
    }

    public String getZweck() {
        return zweck;
    }

    public void setZweck(String zweck) {
        this.zweck = zweck;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }
}
