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
            gesamtAusgaben += betrag; // Bleibt im Minus
        }
    }

    public void datenSpeichern() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("finanzen.dat"))) {
            // 1. Zuerst schreiben wir die Anzahl der Einträge, damit wir beim Laden wissen, wie oft wir schleifen müssen
            dos.writeInt(eintraege.size());

            // 2. Jeden einzelnen Eintrag in Bytes zerlegt herausschreiben
            for (Eintrag e : eintraege) {
                dos.writeUTF(e.getZweck());
                dos.writeDouble(e.getBetrag());
            }

            System.out.println("Daten erfolgreich via Byte I/O gespeichert!");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Daten: " + e.getMessage());
        }
    }

    /**
     * Lädt die gespeicherten Einträge aus der Binärdatei und berechnet die Stände neu.
     */
    public void datenLaden() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("finanzen.dat"))) {
            // Liste und Beträge vor dem Laden zurücksetzen
            eintraege.clear();
            this.kontostand = 1000.00; // Startwert
            this.gesamtEinnahmen = 0.00;
            this.gesamtAusgaben = 0.00;

            // 1. Anzahl der gespeicherten Einträge einlesen
            int anzahl = dis.readInt();

            // 2. Schleife läuft genau so oft, wie Objekte existieren
            for (int i = 0; i < anzahl; i++) {
                String zweck = dis.readUTF();
                double betrag = dis.readDouble();

                // Nutze deine bestehende Methode, um die Werte direkt wieder zu verrechnen
                transaktionHinzufuegen(zweck, betrag);
            }

            System.out.println("Daten erfolgreich via Byte I/O geladen!");
        } catch (IOException e) {
            // Wenn die Datei beim ersten Start noch nicht existiert, ist das normal
            System.out.println("Keine alte Speicherdatei gefunden. Starte mit Leeren Daten.");
        }
    }

    public double getKontostand() { return kontostand; }
    public double getGesamtEinnahmen() { return gesamtEinnahmen; }
    public double getGesamtAusgaben() { return gesamtAusgaben; }
    public ArrayList<Eintrag> getEintraege() { return eintraege; }
}

