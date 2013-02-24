package lautapelilistaus;

import java.util.ArrayList;

import rekisterit.*;
import tiedot.*;

/**
 * Lautapelilistaus luokka, joka huolehtii muiden luokkien yhteystyˆst‰.
 * P‰‰sosin v‰litt‰‰ tietoa luokkien v‰lill‰.
 * @author Hannu Viinikainen
 * @version 08.04.2012
 */
public class Lautapelilistaus {
    private final Lautapelit lautapelit = new Lautapelit();
    private final Pelaajat pelaajat = new Pelaajat();
    private final Tapahtumat tapahtumat = new Tapahtumat();
    private final PelaajanPelit pelaajanPelit = new PelaajanPelit();
    private final TapahtumienPelaajatJaPelit tapahtumanTiedot = new TapahtumienPelaajatJaPelit();
    
    /**
     * @return palauttaa lautapelien lukum‰‰r‰n
     */
    public int getLautapelit() {
        return lautapelit.getLukumaara();
    }
    
    /**
     * Lis‰‰ tietokantaan uuden lautapelin
     * @param nimi lis‰tt‰v‰ olio
     * @throws OmaException Jos alkio on jo taulukossa
     * @example
     * <pre name="test">
     * #THROWS OmaException
     * #PACKAGEIMPORT
     * Lautapelit lautapelit = new Lautapelit();
     * Lautapeli Caylus1 = new Lautapeli();
     * Lautapeli Caylus2 = new Lautapeli();
     * Lautapeli Caylus3 = new Lautapeli();
     * Lautapeli Caylus4 = new Lautapeli();
     * Lautapeli Caylus5 = new Lautapeli();
     * Lautapeli Caylus6 = new Lautapeli();
     * lautapelit.getLukumaara() === 0;
     * 
     * Caylus1.rekisteroi();
     * Caylus2.rekisteroi();
     * Caylus3.rekisteroi();
     * Caylus4.rekisteroi();
     * Caylus5.rekisteroi();
     * Caylus6.rekisteroi();
     * 
     * Caylus1.vastaaPeli();
     * Caylus2.vastaaPeli();
     * Caylus3.vastaaPeli();
     * Caylus4.vastaaPeli();
     * Caylus5.vastaaPeli();
     * Caylus6.vastaaPeli();
     * 
     * lautapelit.lisaa(Caylus1); lautapelit.getLukumaara() === 1;
     * lautapelit.lisaa(Caylus2); lautapelit.getLukumaara() === 2;
     * lautapelit.lisaa(Caylus1); lautapelit.getLukumaara() === 2; #THROWS OmaException
     * lautapelit.anna(1) === Caylus1;
     * lautapelit.anna(2) === Caylus2;
     * lautapelit.lisaa(Caylus3);
     * lautapelit.lisaa(Caylus4);
     * lautapelit.lisaa(Caylus6);
     * lautapelit.lisaa(Caylus5);
     * </pre>
     */
    public void lisaa(Lautapeli nimi) throws OmaException {
        lautapelit.lisaa(nimi);
    }
    
    /**
     * Palauttaa i:nen lautapelin
     * @param i monesko lautapeli halutaan palauttaa
     * @return palauttaa lautapelin halutusta kohdasta
     * @throws OmaException jos indeksi ei ole sopiva
     */
    public Lautapeli annaLautapeli(int i) throws OmaException {
        return lautapelit.anna(i);
    }
    
    /**
     * Antaa lautapelin ID numeron, kun kysyy lautapelin nimell‰
     * @param nimi lautapelin nimi
     * @return lautapelin ID numero
     */
    public int anna(String nimi) {
        return lautapelit.anna(nimi);
    }
    
    
    /**
     * Poistaa halutun pelin lautapelilistalta. Siis poistaa tietokannasta kokonaan pois.
     * @param peli poistettava peli
     */
    public void poista(Lautapeli peli) {
        lautapelit.poista(peli);
        
    }
    
    /**
     * Poistaa pelin, jonka ID numero on annettu
     * @param peli poistettavan pelin ID
     */
    public void poista(int peli) {
        lautapelit.poista(peli);
        
    }
    
    /**
     * Lukee lautapelilistausksen tiedot
     */
    public void lueLautapeli() {
        lautapelit.lueTiedosto();
    }
    
    /**
     * J‰rjestaa lautapelilistan halutun ehdon mukaan.
     * @param pelienLista pelilista, joka j‰rjestet‰‰n
     * @param ehto ehto, joka pelin tulee t‰ytt‰‰, jotta pysyy listalla
     * @param hakuTyyppi Mink‰ perusteella j‰rjestet‰‰n.
     * @return j‰rjestetty lista
     */
    public ArrayList<Lautapeli> jarjestaLista(ArrayList<Lautapeli> pelienLista, String ehto, int hakuTyyppi) {
        return lautapelit.jarjestaLista(pelienLista, ehto, hakuTyyppi);     
    }
    
    /**
     * @return Pelaajien lukum‰‰r‰n
     */
    public int getPelaajat() {
        return pelaajat.getLukumaara();
    }
    
    /**
     * Lis‰‰ annetun pelin Pelaaja-listaan
     * @param pelaaja lis‰tt‰v‰n pelin nimi
     * @throws OmaException Jos tietorakenne on t‰ynn‰ tai alkio on jo taulukossa
     * @example
     * <pre name="test">
     * #THROWS OmaException
     * #PACKAGEIMPORT
     * Pelaajat Pelaajat = new Pelaajat();
     * Pelaaja Pelaaja1 = new Pelaaja();
     * Pelaaja Pelaaja2 = new Pelaaja();
     * Pelaaja Pelaaja3 = new Pelaaja();
     * Pelaajat.getLukumaara() === 0;
     * Pelaaja1.rekisteroi();
     * Pelaaja2.rekisteroi();
     * Pelaaja1.vastaaPelaaja();
     * Pelaaja2.vastaaPelaaja();
     * Pelaajat.lisaa(Pelaaja1); Pelaajat.getLukumaara() === 1;
     * Pelaajat.lisaa(Pelaaja2); Pelaajat.getLukumaara() === 2;
     * Pelaajat.lisaa(Pelaaja1); Pelaajat.getLukumaara() === 2; #THROWS OmaException
     * Pelaajat.anna(1) === Pelaaja1;
     * Pelaajat.anna(2) === Pelaaja2;
     * </pre>
     */
    public void lisaa(Pelaaja pelaaja) throws OmaException {
        pelaajat.lisaa(pelaaja);
    }
    
    /**
     * Palauttaa pelaajan, jonka ID kysyt‰‰n
     * @param ID palautettavan pelaajan ID
     * @return palauttaa pelaajan, jonka ID kysyt‰‰n
     * @throws OmaException jos indeksi ei ole sopiva
     */
    public Pelaaja annaPelaaja(int ID) throws OmaException {
        return pelaajat.anna(ID);
    }
    
    /**
     * Poistaa tietokannasta valitun pelaajan
     * @param pelaaja poistettava pelaaja
     */
    public void poista(Pelaaja pelaaja) {
        pelaajat.poista(pelaaja);
        pelaajanPelit.poista(pelaaja);
        tapahtumanTiedot.poista(pelaaja.getID());
    }
    
    
    /**
     * Palauttaa kaikkien pelaajien nimet
     * @return palauttaa pelaajien nimet
     */
    public ArrayList<String> annaPelaajat() {
        return pelaajat.annaNimet();
    }
    
    

    /**
     * Antaa pelaaja olion nimen perusteella
     * @param nimi pelaajan nimi
     * @return Pelaaja olio, jolla on haluttu nimi
     */
    public Pelaaja annaPelaaja(String nimi) {
        return pelaajat.anna(nimi);
    }
    
    /**
     * @return Tilaisuuksien lukum‰‰r‰n
     */
    public int getTapahtumienMaara() {
        return tapahtumat.getLukumaara();
    }
      
    /**
     * Hakee kaikki tapahtumat, jotka vastaavat hakuehtoa
     * @param ehto hakuehto
     * @param hakuTyyppi Tyyppi, jonka mukaan haetaan
     * @return tapahtumat luokan alkiot taulukko
     */
    public ArrayList<Tapahtuma> getTapahtumat(String ehto, int hakuTyyppi) {
        return tapahtumat.getTapahtumat(ehto, hakuTyyppi);
    }
    
    /**
     * Lis‰‰ annetun pelin Tapahtuma-listaan
     * @param tapahtuma lis‰tt‰v‰n tilaisuuden nimi
     * @throws OmaException Jos alkio on jo taulukossa
     * @example
     * <pre name="test">
     * #THROWS OmaException
     * #PACKAGEIMPORT
     * Tapahtumat Tapahtumat = new Tapahtumat();
     * Tapahtuma tapahtuma1 = new Tapahtuma();
     * Tapahtuma tapahtuma2 = new Tapahtuma();
     * Tapahtuma tapahtuma3 = new Tapahtuma();
     * Tapahtumat.getLukumaara() === 0;
     * tapahtuma1.rekisteroi(0);
     * tapahtuma2.rekisteroi(0);
     * tapahtuma3.rekisteroi(0);
     * tapahtuma1.vastaaTapahtuma();
     * tapahtuma2.vastaaTapahtuma();
     * Tapahtumat.lisaa(tapahtuma1); Tapahtumat.getLukumaara() === 1;
     * Tapahtumat.lisaa(tapahtuma2); Tapahtumat.getLukumaara() === 2;
     * Tapahtumat.lisaa(tapahtuma1); #THROWS OmaException
     * Tapahtumat.anna(1) === tapahtuma1;
     * Tapahtumat.anna(2) === tapahtuma2;
     * </pre>
     */
    public void lisaa(Tapahtuma tapahtuma) throws OmaException {
        tapahtumat.lisaa(tapahtuma);
    }
    
    /**
     * Palauttaa i:nen lautapelin
     * @param i monesko lautapeli halutaan palauttaa
     * @return palauttaa lautapelin halutusta kohdasta
     * @throws OmaException jos indeksi ei ole sopiva
     */
    public Tapahtuma annaTapahtuma(int i) throws OmaException {
        return tapahtumat.anna(i);
    }
    
    /**
     * Poistaa tapahtuman
     * @param tapahtuma poistaa tapahtuman
     */
    public void poista(Tapahtuma tapahtuma) {
        tapahtumat.poista(tapahtuma);
    }
    
    /**
     * Lis‰‰ halutulle pelaajalle annetun peli
     * @param pelaajaID pelaaja, jolle peli lis‰t‰‰n
     * @param lautapeliID peli, joka lis‰t‰‰n pelaajalle
     * @throws OmaException Jos yritet‰‰n lis‰t‰ peli‰, joka on jo pelaajalla
     */
    public void lisaa(int pelaajaID, int lautapeliID) throws OmaException {
        pelaajanPelit.lisaa(pelaajaID, lautapeliID);
    }
    
    /**
     * Antaa valitun pelaajan lautapelit, sek‰ j‰rjest‰‰ ne haluttuun j‰rjestykseen.
     * @param pelaajaID pelaaja, jonka pelit halutaan
     * @return palauttaa listan pelaajan pelien ID numeroista
     */
    public ArrayList<Integer> annaPelaajanPelit(int pelaajaID) {
       return pelaajanPelit.annaPelaajanLautapelit(pelaajaID);
    }
    
    /**
     * Poistaa annetulta pelaajalta annetun pelin
     * @param pelaajaID pelaaja
     * @param lautapeliID peli, joka poistetaan
     */
    public void poista(int pelaajaID, int lautapeliID) {
        pelaajanPelit.poista(pelaajaID, lautapeliID);
    }

    
    /**
     * Lis‰‰ tapahtumaan pelaajan ja pelin
     * @param tapahtumaID tapahtuman ID numero
     * @param pelaajaID pelaajan ID numero
     * @param lautapeliID lautapelinID numero
     * @throws OmaException virhe, jos pelaalla on jo kyseinen peli mukana t‰ss‰ tapahtumassa
     */
    public void lisaa(int tapahtumaID, int pelaajaID, int lautapeliID)
            throws OmaException {
        tapahtumanTiedot.lisaa(tapahtumaID, pelaajaID, lautapeliID);
    }
    
    /**
     * Lis‰‰ tapahtumaan pelaajan ja pelin
     * @param tapahtumaID tapahtuman ID numero
     * @param pelaajaID pelaajan ID numero
     * @throws OmaException virhe, jos pelaalla on jo kyseinen peli mukana t‰ss‰ tapahtumassa
     */
    public void lisaaPelaajaTapahtumaan(int tapahtumaID, int pelaajaID)
            throws OmaException {
        tapahtumanTiedot.lisaa(tapahtumaID, pelaajaID);
    }
    
    /**
     * Palauttaa tapahtuman pelaajat
     * @param tapahtumaID tapahtuma, jonka pelaajat palautetaan
     * @return tapahtuman pelaaja lista
     */
    public ArrayList<Integer> annaTapahtumanPelaajat(int tapahtumaID) {
        return tapahtumanTiedot.annaTapahtumanPelaajat(tapahtumaID);
    }
    
    /**
     * Palauttaa tapahtuman pelit
     * @param tapahtumaID tapahtuma, jonka pelaajat palautetaan
     * @return tapahtuman peli lista
     */
    public ArrayList<Integer> annaTapahtumanPelit(int tapahtumaID) {
        return tapahtumanTiedot.annaTapahtumanPelit(tapahtumaID);
    }
    
    /**
     * Poistaa halutun pelaajan halutusta tapahtumasta
     * @param tapahtumaID tapahtuma, josta pelaaja poistetaan
     * @param pelaajaID pelaaja, joka poistetaan tapahtumasta
     */
    public void poistaPelaajaTapahtumasta(int tapahtumaID, int pelaajaID){
        tapahtumanTiedot.poista(tapahtumaID, pelaajaID);
    }
    
    /**
     * Poistaa halutun pelaajan kaikista tapahtumista.
     * @param pelaajaID pelaaja, joka poistetaan tapahtumista
     */
    public void poistaPelaajaTapahtumista(int pelaajaID){
        tapahtumanTiedot.poista(pelaajaID);
    }
    /**
     * Poistaa tapahtumasta valitun pelin.
     * @param tapahtumaID tapahtuma, josta peli poistetaan
     * @param lautapeliID peli, joka poistetaan
     * @param pelaajaID pelaaja, jolta peli poistetaan
     */
    public void poistaPeliTapahtumasta(int tapahtumaID, int pelaajaID, int lautapeliID) {
        tapahtumanTiedot.poistaPeliTapahtumasta(tapahtumaID, pelaajaID, lautapeliID);        
    }
    
    

    /**
     * Tarkastaa kuuluuko kyseinen peli kyseiselle pelaajalle
     * @param pelaaja pelaaja, jolle pelin tulisi kuulua
     * @param tarkastettava peli, jonka kuuluvuus pelaajalle tarkastetaan 
     * @return tieto, siit‰ kuuluuko peli kyseiselle henkilˆlle
     */
    public boolean onkoPelaajanPeli(Pelaaja pelaaja, Lautapeli tarkastettava) {
        return tapahtumanTiedot.onkoPelaajanPeli(pelaaja, tarkastettava);
    }
    

    /**
     * Lukee tallennetut tiedostot, joissa on tietokannat
     */
    public void lueTiedostot() {
         pelaajat.lueTiedosto();
         lautapelit.lueTiedosto();
         tapahtumat.lueTiedosto();
         pelaajanPelit.lueTiedosto();
         tapahtumanTiedot.lueTiedosto();
    }
    

    /**
     * Tallentaa tiedot.
     */
    public void tallenna() {
        lautapelit.tallenna();
        pelaajat.tallenna();
        tapahtumat.tallenna();
        pelaajanPelit.tallenna();
        tapahtumanTiedot.tallenna();
    }
    
    
    /**
     * testip‰‰ohjelma
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Lautapelilistaus lautapelilistaus = new Lautapelilistaus();
        
        try {
            
            Lautapeli Caylus1 = new Lautapeli();
            Lautapeli Caylus2 = new Lautapeli();
            Lautapeli Caylus3 = new Lautapeli();
            
            Pelaaja pelaaja1 = new Pelaaja();
            Pelaaja pelaaja2 = new Pelaaja();
            Pelaaja pelaaja3 = new Pelaaja();
            Tapahtuma tapahtuma1 = new Tapahtuma();
            Tapahtuma tapahtuma2 = new Tapahtuma();
            
            Caylus1.rekisteroi();
            Caylus1.vastaaPeli();
            Caylus2.rekisteroi();
            Caylus2.vastaaPeli();
            Caylus3.rekisteroi();
            Caylus3.vastaaPeli();
            
            pelaaja1.rekisteroi();
            pelaaja1.vastaaPelaaja();
            pelaaja2.rekisteroi();
            pelaaja2.vastaaPelaaja();
            pelaaja3.rekisteroi();
            pelaaja3.vastaaPelaaja();
            
            tapahtuma1.rekisteroi(0);
            tapahtuma1.vastaaTapahtuma();
            tapahtuma2.rekisteroi(0);
            tapahtuma2.vastaaTapahtuma();
            
            lautapelilistaus.lisaa(Caylus1);
            lautapelilistaus.lisaa(Caylus2);
            lautapelilistaus.lisaa(Caylus3);
            
            lautapelilistaus.lisaa(pelaaja1);
            lautapelilistaus.lisaa(pelaaja2);
            lautapelilistaus.lisaa(pelaaja3);
            
            lautapelilistaus.lisaa(tapahtuma1);
            lautapelilistaus.lisaa(tapahtuma2);
            
            lautapelilistaus.lisaa(pelaaja1.getID(), Caylus1.getID());
            lautapelilistaus.lisaa(pelaaja1.getID(), Caylus3.getID());
            lautapelilistaus.lisaa(pelaaja2.getID(), Caylus1.getID());
            lautapelilistaus.lisaa(pelaaja2.getID(), Caylus2.getID());
            lautapelilistaus.lisaa(pelaaja2.getID(), Caylus3.getID());
            lautapelilistaus.lisaa(pelaaja3.getID(), Caylus1.getID());
            
            lautapelilistaus.lisaaPelaajaTapahtumaan(tapahtuma1.getID(),
                    pelaaja1.getID());
            lautapelilistaus.lisaaPelaajaTapahtumaan(tapahtuma1.getID(),
                    pelaaja2.getID());
            lautapelilistaus.lisaaPelaajaTapahtumaan(tapahtuma1.getID(),
                    pelaaja3.getID());
            
            lautapelilistaus.lisaa(tapahtuma1.getID(), pelaaja3.getID(),
                    Caylus1.getID());
            lautapelilistaus.lisaa(tapahtuma1.getID(), pelaaja2.getID(),
                    Caylus2.getID());
            lautapelilistaus.lisaa(tapahtuma1.getID(), pelaaja2.getID(),
                    Caylus1.getID());
            
            System.out
                    .println("============= Lautapelilistauksen testi =================");
            
            for (int i = 1; i <= lautapelilistaus.getPelaajat(); i++) {
                Pelaaja peli = lautapelilistaus.annaPelaaja(i);
                System.out.println("Pelaajan ID on: " + i);
                peli.tulosta(System.out);
            }
            
            for (int i = 1; i <= lautapelilistaus.getLautapelit(); i++) {
                Lautapeli peli = lautapelilistaus.annaLautapeli(i);
                System.out.println("Lautapelin ID on: " + i);
                peli.tulosta(System.out);
            }
            
            for (int i = 1; i <= lautapelilistaus.getTapahtumienMaara(); i++) {
                Tapahtuma Tapahtuma = lautapelilistaus.annaTapahtuma(i);
                System.out.println("Tapahtuman ID on: " + i);
                Tapahtuma.tulosta(System.out);
            }
            

            ArrayList<Integer> pelit1 = lautapelilistaus
                    .annaPelaajanPelit(pelaaja1.getID());
            ArrayList<Integer> pelit2 = lautapelilistaus
                    .annaPelaajanPelit(pelaaja2.getID());
            ArrayList<Integer> pelit3 = lautapelilistaus
                    .annaPelaajanPelit(pelaaja3.getID());
            
            for (int i = 0; i < pelit1.size(); i++) {
                int peli = pelit1.get(i);
                System.out.println("Pelaaja " + pelaaja1.getNimi()
                        + " omistaa pelin:");
                lautapelilistaus.annaLautapeli(peli).tulosta(System.out);
            }
            
            for (int i = 0; i < pelit2.size(); i++) {
                int peli = pelit2.get(i);
                System.out.println("Pelaaja " + pelaaja2.getNimi()
                        + " omistaa pelin:");
                lautapelilistaus.annaLautapeli(peli).tulosta(System.out);
            }
            
            for (int i = 0; i < pelit3.size(); i++) {
                int peli = pelit3.get(i);
                System.out.println("Pelaaja " + pelaaja3.getNimi()
                        + " omistaa pelin:");
                lautapelilistaus.annaLautapeli(peli).tulosta(System.out);
            }
            
            for (int i = 0; i < pelit3.size(); i++) {
                int peli = pelit3.get(i);
                System.out.println("Pelaaja " + pelaaja3.getNimi()
                        + " omistaa pelin:");
                lautapelilistaus.annaLautapeli(peli).tulosta(System.out);
            }
            
            ArrayList<Integer> tapahtumanPelaajat = lautapelilistaus
                    .annaTapahtumanPelaajat(tapahtuma1.getID());
            
            System.out.println("Tapahtumaan " + tapahtuma1.getNimi()
                    + " osallistuu: ");
            
            for (int i = 0; i < tapahtumanPelaajat.size(); i++) {
                int pelaaja = tapahtumanPelaajat.get(i);
                lautapelilistaus.annaPelaaja(pelaaja).tulosta(System.out);
            }
            
            ArrayList<Integer> tapahtumanPelit = lautapelilistaus
                    .annaTapahtumanPelit(tapahtuma1.getID());
            
            System.out.println("Tapahtumaan " + tapahtuma1.getNimi()
                    + " tuodaan seuraavia pelej‰: ");
            
            for (int i = 0; i < tapahtumanPelit.size(); i++) {
                int peli = tapahtumanPelit.get(i);
                lautapelilistaus.annaLautapeli(peli).tulosta(System.out);
            }
            
            lautapelilistaus.poista(Caylus2);
            
            for (int i = 1; i <= lautapelilistaus.getLautapelit(); i++) {
                Lautapeli peli = lautapelilistaus.annaLautapeli(i);
                if (peli != null) {
                    System.out.println("Lautapelin ID on: " + i);
                    peli.tulosta(System.out);
                }
            }
            
            lautapelilistaus.poista(tapahtuma1);
            
            System.out.println("============= Tapahtuman tiedot =============");
            for (Tapahtuma tapahtuma : lautapelilistaus.getTapahtumat("",0)) {
                System.out.println("Tapahtuma: ");
                tapahtuma.tulosta(System.out);
                
            }
            
            lautapelilistaus.poista(pelaaja2.getID(), Caylus1.getID());

            
            pelit2 = lautapelilistaus.annaPelaajanPelit(pelaaja2.getID());
            
            for (int i = 0; i < pelit2.size(); i++) {
                int peli = pelit2.get(i);
                if (lautapelilistaus.annaLautapeli(peli) != null) {
                    System.out.println("Pelaaja " + pelaaja2.getNimi()
                            + " omistaa pelin:");
                    lautapelilistaus.annaLautapeli(peli).tulosta(System.out);
                }
            }
            
        } catch (OmaException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
