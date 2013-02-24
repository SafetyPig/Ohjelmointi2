package tiedot;

/**
 * Pelaaja olio
 * @author Hannu Viinikainen
 * @version 4.4.2012
 */
public class Pelaaja extends Tietue {
    
    private Kentta kentat[] = {
            new IntKentta("ID"),
            new JonoKentta("Nimi")
    };
    
    private static int seuraavaNro = 1;
    
    /**
     * Pelaaja konstruktori, jolle tuodaan parametrinä pelaajan nimi
     * @param nimi uuden pelaajan nimi
     */
    public Pelaaja(String nimi) {
        setKentat(getKentat());
        if (nimi.length() >= 4 && nimi.substring(0, 4).equals("new*"))
            aseta(1,nimi.substring(4,nimi.length()));
        else {
            parse(nimi);
            int suurinID = getID();
            if(suurinID >= seuraavaNro)
                seuraavaNro = suurinID + 1;
        }
    }
    
    /**
     * Tyhjä konstruktori
     */
    public Pelaaja() {
        setKentat(getKentat());
    }
      
    /**
     * Palauttaa kentät
     * @return olion kentät
     */
    private Kentta[] getKentat(){
        return kentat;
    }
    
    /**
     * Palauttaa pelaajan nimen
     */
    @Override
    public String getNimi() {
        return kentat[1].toString();
    }
    
    /**
     * Rekisteröi pelaajan, eli antaa pelaajalle järkevän ID-numeron
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
    public void rekisteroi() {
        aseta(0, seuraavaNro+"");
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
    public void vastaaPelaaja() {
        aseta(1, "Pate Pelaaja versio:" + rand(100, 5000));
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaaja pelaaja1 = new Pelaaja();
        Pelaaja pelaaja2 = new Pelaaja();
        
        pelaaja1.rekisteroi();
        pelaaja2.rekisteroi();
        
        pelaaja1.vastaaPelaaja();
        pelaaja1.tulosta(System.out);
        
        pelaaja2.vastaaPelaaja();
        pelaaja2.tulosta(System.out);
    }
}
