package Finanzmanager;

import java.util.ArrayList;

public class FinanzmanagerLogik {
    private ArrayList<Eintrag> eintraege;
    private double kontostand;
    private double gesamtEinnahmen;
    private double gesamtAusgaben;

    public FinanzmanagerLogik() {
        this.eintraege = new ArrayList<>();
        this.kontostand = 1000.00;
        this.gesamtEinnahmen = 0.00;
        this.gesamtAusgaben = 0.00;
    }

    public void transaktionHinzufuegen(String zweck, double betrag) {
        Eintrag neuerEintrag = new Eintrag(zweck, betrag);
        eintraege.add(neuerEintrag);

        kontostand += betrag;

        if (betrag > 0) {
            gesamtEinnahmen += betrag;
        } else {
            gesamtAusgaben += betrag; // Bleibt im Minus
        }
    }

    public double getKontostand() { return kontostand; }
    public double getGesamtEinnahmen() { return gesamtEinnahmen; }
    public double getGesamtAusgaben() { return gesamtAusgaben; }
    public ArrayList<Eintrag> getEintraege() { return eintraege; }
}

