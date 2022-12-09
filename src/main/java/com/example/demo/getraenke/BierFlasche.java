package com.example.demo.getraenke;

public class BierFlasche {

    Bier inhalt = null;

    public boolean istLeer() {
        return (inhalt == null);
    }

    public void fuellen(Bier g) {
        inhalt = g;
    }

    public Bier leeren() {
        Bier result = inhalt;
        inhalt = null;
        return result;
    }

    public static void main(String[] varargs) {
        // f1 nur für Bier dienen
        BierFlasche f1 = new BierFlasche();
        f1.fuellen(new Bier("DHBW-Bräu"));
        System.out.println("f1 geleert mit " + f1.leeren());


        f1.fuellen(new Wein("DHBW-Export"));
        System.out.println("f1 geleert mit " + f1.leeren());
    }
}
