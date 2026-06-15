package Finanzmanager;

import java.io.*;
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

        datenLaden();
    }

    public void transaktionHinzufuegen(String zweck, double betrag) {
        Eintrag neuerEintrag = new Eintrag(zweck, betrag);
        eintraege.add(neuerEintrag);

        kontostand += betrag;

        if (betrag > 0) {
            gesamtEinnahmen += betrag;
        } else {
            gesamtAusgaben += betrag;
        }
    }

    public void datenSpeichern() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("finanzen.dat"))) {
            // Anzahl der Einträge
            dos.writeInt(eintraege.size());

            // Einträge in Bytes
            for (Eintrag e : eintraege) {
                dos.writeUTF(e.getZweck());
                dos.writeDouble(e.getBetrag());
            }

            System.out.println("Daten gespeichert!");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public void datenLaden() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("finanzen.dat"))) {
            // Liste und Beträge vor dem Laden zurücksetzen
            eintraege.clear();
            this.kontostand = 1000.00; // Startwert
            this.gesamtEinnahmen = 0.00;
            this.gesamtAusgaben = 0.00;

            //gespeicherten Einträge einlesen
            int anzahl = dis.readInt();

            for (int i = 0; i < anzahl; i++) {
                String zweck = dis.readUTF();
                double betrag = dis.readDouble();

                //Werte direkt wieder zu berechnen
                transaktionHinzufuegen(zweck, betrag);
            }

            System.out.println("Daten geladen!");
        } catch (IOException e) {
            // Wenn die Datei noch nicht existiert
            System.out.println("Keine alte Speicherdatei gefunden");
        }
    }

    public double getKontostand() { return kontostand; }
    public double getGesamtEinnahmen() { return gesamtEinnahmen; }
    public double getGesamtAusgaben() { return gesamtAusgaben; }
    public ArrayList<Eintrag> getEintraege() { return eintraege; }
}

