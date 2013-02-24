package rekisterit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import tiedot.Pelaaja;

/**
 * Lautapelilistauksen pelaajat. Osaa lis‰t‰ pelaajan yms.
 * @author Hannu Viinikainen
 * @version 10.04.2012
 */
public class Pelaajat {
    
    private int lukumaara = 0;
    private ArrayList<Pelaaja> alkiot = new ArrayList<Pelaaja>();
    private String tiedostonNimi = "pelaajat.dat";
    
    /**
     * tyhj‰ konstruktri
     */
    public Pelaajat() {
        //ei tarvitse tehd‰ mit‰‰n, koska alustetaan valmiiksi
    }
    

    /**
     * Palauttaa pelaajien lukum‰‰r‰n
     * @return palauttaa pelaajien lukum‰‰r‰n
     */
    public int getLukumaara() {
        return lukumaara;
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
     * Pelaaja1.rekisteroi();
     * Pelaaja2.rekisteroi();
     * Pelaaja3.rekisteroi();
     * Pelaaja1.vastaaPelaaja();
     * Pelaaja2.vastaaPelaaja();
     * Pelaajat.getLukumaara() === 0;
     * Pelaajat.lisaa(Pelaaja1); Pelaajat.getLukumaara() === 1;
     * Pelaajat.lisaa(Pelaaja2); Pelaajat.getLukumaara() === 2;
     * Pelaajat.lisaa(Pelaaja1); #THROWS OmaException
     * Pelaajat.anna(1) === Pelaaja1;
     * Pelaajat.anna(2) === Pelaaja2;
     * </pre>
     */
    public void lisaa(Pelaaja pelaaja) throws OmaException {
        if (onkoTaulukossa(pelaaja))
            throw new OmaException("On jo taulukossa");
        alkiot.add(pelaaja);
        lukumaara++;
    }
    
    
    /**
     * Palauttaa tiedon, onko lis‰tt‰v‰ alkio jo valmiiksi listassa
     * @param pelaaja lis‰tt‰v‰n alkion nimi
     * @return tieto, onko alkio jo taulukossa
     */
    public boolean onkoTaulukossa(Pelaaja pelaaja) {
        for (Pelaaja taulukonPelaaja : alkiot)
            if (taulukonPelaaja.getNimi().equals(pelaaja.getNimi()))
                return true;
        
        return false;
    }
    
    /**
     * Palauttaa annetulla ID:lle olevan pelaajan
     * @param pelaajanID pelaajan ID numero.
     * @return palauttaa pelaajan, joka omaa kyseisen ID numeron
     */
    public Pelaaja anna(int pelaajanID) {
        if (pelaajanID < 0)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + pelaajanID);
        
        for(Pelaaja pelaaja: alkiot)
            if(pelaaja.getID() == pelaajanID)
                return pelaaja;
        
        return null;
    }

    
    /**
     * Poistaa pelaajan tietokannasta
     * @param pelaaja poistettava pelaaja
     */
    public void poista(Pelaaja pelaaja) {
        alkiot.remove(pelaaja);
    }


    /**
     * Antaa kaikkien pelaajien nimet
     * @return pelaajien nimet listassa
     */
    public ArrayList<String> annaNimet() {
        ArrayList<String> nimet = new ArrayList<String>();
        for(Pelaaja pelaaja: alkiot){
            nimet.add(pelaaja.getNimi());
        }
        return nimet;
    } 
    

    /**
     * Antaa pelaaja olion nimen perusteella
     * @param nimi pelaajan haluttu nimi
     * @return pelaaja olio, jolla on haluttu nimi
     */
    public Pelaaja anna(String nimi) {
        for(Pelaaja pelaaja: alkiot)
            if(pelaaja.getNimi().equals(nimi))
                return pelaaja;
        
        return null;
    }
    
    

    /**
     * lukee tiedostosta pelaajat.dat pelaajien tiedot ja asettaa
     * pelaahien tiedon ohjelmaan
     */
    public void lueTiedosto() {
        try(Scanner fi = new Scanner(new FileInputStream(new File(tiedostonNimi)))) {
            while(fi.hasNextLine()) {
                String rivi = fi.nextLine();
                Pelaaja lisattava = new Pelaaja(rivi);
                lisaa(lisattava);
            }
            
        } catch(FileNotFoundException ex){
            System.err.println("Tiedostoa pelaajat.dat ei saada auki" + ex);
            
        } catch (OmaException e) {
            System.err.println("Pelaajan lis‰ys ei onnistu" + e);
        }
        
    }
    

    /**
     * Tallentaa pelaajien tiedot pelaajat.dat tiedostoon.
     */
    public void tallenna() {
           try (PrintStream tiedostoon = new PrintStream(new FileOutputStream(tiedostonNimi,false))) {
               for(Pelaaja pelaaja: alkiot)
                   tiedostoon.println(pelaaja.tiedostoMuotoon());
               
           } catch (FileNotFoundException ex) {
               System.err.println("Tiedostoa ei saada auki");
           }
    }

    /**
     * testip‰‰ohjelma
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Pelaajat pelaajat = new Pelaajat();
        Pelaaja Pelaaja1 = new Pelaaja();
        Pelaaja Pelaaja2 = new Pelaaja();
        Pelaaja Pelaaja3 = new Pelaaja();

        
        Pelaaja1.rekisteroi();
        Pelaaja2.rekisteroi();
        Pelaaja3.rekisteroi();
        
        Pelaaja1.vastaaPelaaja();
        Pelaaja2.vastaaPelaaja();
        Pelaaja3.vastaaPelaaja();
        
        try {
            pelaajat.lisaa(Pelaaja1);
            pelaajat.lisaa(Pelaaja2);
            pelaajat.lisaa(Pelaaja3);
            
            System.out.println("============= Pelaajien tiedot =============");
            for (int i = 1; i <= pelaajat.getLukumaara(); i++) {
                Pelaaja pelaaja = pelaajat.anna(i);
                System.out.println("Pelaajan numero: " + i);
                pelaaja.tulosta(System.out);
            }
            
            pelaajat.poista(Pelaaja1);
            pelaajat.poista(Pelaaja2);
            
            pelaajat.lisaa(Pelaaja1);
            
            
            System.out.println("============= Pelaajien tiedot =============");
            for (Pelaaja pelaaja : pelaajat.alkiot) {
                System.out.println("Pelaajat: ");
                pelaaja.tulosta(System.out);
            }
            
            
            ArrayList<String> nimet = pelaajat.annaNimet();
            System.out.println("Pelaajien nimet");
            for(String nimi : nimet)
                System.out.println(nimi);
            
        } catch (OmaException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
