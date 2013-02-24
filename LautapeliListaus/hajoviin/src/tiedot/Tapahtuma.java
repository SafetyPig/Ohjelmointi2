package tiedot;

/**
 * Tapahtuma olio, joka tiet�� tapahtuman tiedot, ja osaa lukea tarvittavan tiedon listalta
 * @author Hannu Viinikainen
 * @version 4.4.2012
 */
public class Tapahtuma extends Tietue {
    
    private Kentta kentat[] = { 
            new IntKentta("ID"),
            new IntKentta("Admin"),
            new JonoKentta("Nimi"),
            new JonoKentta("Paivamaara", new PaivaTarkastaja()),
            new JonoKentta("Alkamisaika"), 
            new JonoKentta("Paikka"),
            };
    
    private static int seuraavaNro = 1;
    
    /**
     * Olion konstruktori.
     * @param jono merkkijono, josta olio luodaan
     */
    public Tapahtuma(String jono) {
        setKentat(getUudetKentat());
        parse(jono);
        int suurinID = getID();
        if (suurinID >= seuraavaNro)
            seuraavaNro = suurinID + 1;
    }
    
    /**
     * Tyhj� konstruktori
     */
    public Tapahtuma() {
        setKentat(getUudetKentat());
    }
    
    /**
     * Palauttaa lautapelin tietokent�t
     * @return lautapelin tietokent�t
     */
    private Kentta[] getUudetKentat() {
        return kentat;
    }
    
    /**
     * Palauttaa k�ytt�j�lle ensimm�isen hy�dyllisen tietokent�n sis�ll�n
     * @return k�ytt�j�lle ensimm�isen hy�dyllisen tietokent�n sis�lt�
     */
    @Override
    public int getEkaTietoKentta() {
        return 2;
    }
    
    /**
     * @return palauttaa lautapelin nimen
     */
    @Override
    public String getNimi() {
        return anna(2);
    }
    
    /**
     * Antaa esimerkki vastauksen lautapeli oliolta
     */
    public void vastaaTapahtuma() {
        aseta(2, "Peli-ilta versio:" + rand(100, 500));
        aseta(3, "13.05.2012");
        aseta(4, "17.00");
        aseta(5, "Pelaajankatu 26");
    }
    
    /**
     * Rekister�i tapahtuman, eli antaa tapahtumalle j�rkev�n ID-numeron sek� adminin id numeron
     * @param i Pelaaja, joka j�rjest�� tapahtuman eli on tapahtuman Admin
     * @example
     * <pre name="test">
     * Pelaaja pelaaja1 = new Pelaaja();
     * pelaaja1.rekisteroi();
     * Pelaaja pelaaja2 = new Pelaaja();
     * pelaaja2.rekisteroi();
     * 
     * int n = pelaaja1.getID();
     * int m = pelaaja2.getID();
     * 
     * n === m-1;
     * </pre>
     */
    public void rekisteroi(int i) {
        aseta(0, seuraavaNro + "");
        aseta(1, i + "");
        seuraavaNro++;
    }
    
    /**
     * Testip��ohjelma
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        Tapahtuma Tapahtuma1 = new Tapahtuma();
        Tapahtuma Tapahtuma2 = new Tapahtuma();
        
        Tapahtuma1.rekisteroi(0);
        Tapahtuma2.rekisteroi(0);
        
        Tapahtuma1.vastaaTapahtuma();
        Tapahtuma1.tulosta(System.out);
        
        Tapahtuma2.vastaaTapahtuma();
        Tapahtuma2.tulosta(System.out);
        
    }
}
