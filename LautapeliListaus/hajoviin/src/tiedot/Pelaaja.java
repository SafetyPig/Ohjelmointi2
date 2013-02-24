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
     * Pelaaja konstruktori, jolle tuodaan parametrin� pelaajan nimi
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
     * Tyhj� konstruktori
     */
    public Pelaaja() {
        setKentat(getKentat());
    }
      
    /**
     * Palauttaa kent�t
     * @return olion kent�t
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
     * Rekister�i pelaajan, eli antaa pelaajalle j�rkev�n ID-numeron
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
     * Palauttaa k�ytt�j�lle ensimm�isen hy�dyllisen tietokent�n sis�ll�n
     * @return k�ytt�j�lle ensimm�isen hy�dyllisen tietokent�n sis�lt�
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
     * Testip��ohjelma
     * @param args ei k�yt�ss�
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
