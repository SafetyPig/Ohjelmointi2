package tiedot;

/**
 * Lautapeli-olio
 * @author Hannu Viinikainen
 * @version 4.4.2012
 */
public class Lautapeli extends Tietue {
    
    private Kentta kentat[] = { 
            new IntKentta("ID"), 
            new JonoKentta("Nimi" ,new TyhjanTarkastaja()),
            new JonoKentta("Genre"),
            new IntKentta("Julkaisuvuosi"),
            new JonoKentta("Pelaajamaara", new MerkkiTarkastaja("0123456789+-")), 
            new JonoKentta("Peli aika", new MerkkiTarkastaja("0123456789+-n")),
            new JonoKentta("Tuuri"), 
            new JonoKentta("Ikasuositus", new MerkkiTarkastaja("0123456789+-")),
            new JonoKentta("Laatu") };
    
    private static int seuraavaNro = 1;
    
   
    /**
     * Palauttaa lautapelin tietokentät
     * @return lautapelin tietokentät
     */
    private Kentta[] getUudetKentat() {
        return kentat;
    }
    
    /**
     * Lautapeliolion konstruktori, jota voi kutsua, joko uudenlautapelin nimen avulla tai
     * rivin avulla, jolta löytyy kaikki lautapelin tiedot.
     * @param jono lautapelin tiedot
     */
    public Lautapeli(String jono) {
        //Tekee uuden lautapelin, joka rekisteröidään myöhemmin.
        setKentat(getUudetKentat());
        if (jono.substring(0, 4).equals("new*"))
            aseta(1, jono.substring(4, jono.length()));
        
        else {
            parse(jono);
            int suurinID = getID();
            if(suurinID >= seuraavaNro)
                seuraavaNro = suurinID + 1;
        }
    }
    
    /**
     * tyhjä muodostaja
     */
    public Lautapeli() {
        setKentat(getUudetKentat());
    }
    
    /**
     * Rekisteröi lautapelin, eli antaa lautapelille järkevän ID-numeron
     * @example
     * <pre name="test">
     * Lautapeli peli1 = new Lautapeli();
     * peli1.rekisteroi();
     * Lautapeli peli2 = new Lautapeli();
     * peli2.rekisteroi();
     * 
     * int n = peli1.getID();
     * int m = peli2.getID();
     * 
     * n === m-1;
     * </pre>
     */
    public void rekisteroi() {
        aseta(0, seuraavaNro + "");
        seuraavaNro++;
    }
    
    /**
     * Palauttaa käyttäjälle ensimmäisen hyödyllisen tietokentän sisällön
     * @return käyttäjälle ensimmäisen hyödyllisen tietokentän sisältö
     */
    @Override
    public int getEkaTietoKentta() {
        return 1;
    }
    
    
    /**
     * Antaa esimerkki vastauksen lautapeli oliolta
     */
    public void vastaaPeli() {
        aseta(1, "Caylus versio:" + rand(1000, 9999));
        aseta(2, "Strategia");
        aseta(3, "2005");
        aseta(4, "1-" + rand(2, 5));
        aseta(5, "60-150");
        aseta(6, "olematon");
        aseta(7, "12");
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Lautapeli peli = new Lautapeli();
        Lautapeli peli2 = new Lautapeli();
        
        peli.rekisteroi();
        peli2.rekisteroi();
        peli.vastaaPeli();
        peli2.vastaaPeli();
        peli.tulosta(System.out);
        System.out.println();
        peli2.tulosta(System.out);
    }
    
}
