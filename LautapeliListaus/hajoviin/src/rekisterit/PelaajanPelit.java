package rekisterit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import tiedot.Pelaaja;
import tiedot.PelaajanPeli;

/**
 * Osaa k‰sitell‰ pelaajan pelej‰, lis‰t‰ ja poistaa niit‰.
 * @author Hannu Viinikainen
 * @version 27.2.2012
 */
public class PelaajanPelit {
    
    private ArrayList<PelaajanPeli> alkiot = new ArrayList<PelaajanPeli>();
    private String tiedostonNimi = "pelaajanPelit.dat";
    /**
     * tyhj‰ konstruktori
     */
    public PelaajanPelit() {
        // Tyhj‰, koska konstruktori alustetaan valmiiksi
    }
    
    /**
     * Lis‰‰ pelaajan ja pelin yhdistelm‰n listaan
     * @param pelaajaID pelaaja, jolle lautapeli lis‰t‰‰n
     * @param lautapeliID  peli, joka lis‰t‰‰n
     * @throws OmaException virhe, jos kyseisell‰ pelaajalla on jo kysyinen peli
     * @example
     * <pre name="test">
     *  #THROWS OmaException
     *  #PACKAGEIMPORT
     *  PelaajanPelit pelaajanPelit = new PelaajanPelit();
     *  Pelaaja pate = new Pelaaja();
     *  Lautapeli peli = new Lautapeli();
     *  Lautapeli peli2 = new Lautapeli();
     *  pate.rekisteroi();
     *  peli.rekisteroi();
     *  peli2.rekisteroi();
     *  pelaajanPelit.lisaa(1,1);
     *  pelaajanPelit.lisaa(1,2);
     *  pelaajanPelit.lisaa(1,1); #THROWS OmaException
     * </pre>
     */
    public void lisaa(int pelaajaID, int lautapeliID) throws OmaException {
        PelaajanPeli lisattava = new PelaajanPeli(pelaajaID, lautapeliID);
        if (onkoTaulukossa(lisattava))
            throw new OmaException("Pelaajalla on jo kyseinen peli");
        
        alkiot.add(lisattava);
    }
    
    /**
     * Palauttaa tiedon, onko lis‰tt‰v‰ alkio jo valmiiksi listassa
     * @param PelaajanPeli lis‰tt‰v‰n alkion nimi
     * @return tieto, onko alkio jo taulukossa
     */
    public boolean onkoTaulukossa(PelaajanPeli PelaajanPeli) {
        for (PelaajanPeli peli : alkiot)
            if (peli.getLautapeliID() == PelaajanPeli.getLautapeliID()
                    && peli.getPelaajaID() == PelaajanPeli.getPelaajaID())
                return true;
        
        return false;
    }
    
    
    /**
     * Palauttaa lista pelaajan lautapeleist‰
     * @param pelaajaID pelaaja, jonka lautapelit halutaan
     * @return Palauttaa listan kysytyn k‰ytt‰j‰n lautapelien ID numeroista.
     * 
     */
    public ArrayList<Integer> annaPelaajanLautapelit(int pelaajaID) {
        ArrayList<Integer> lautapelit = new ArrayList<Integer>();
        for (PelaajanPeli peli : alkiot)
            if (peli.getPelaajaID() == pelaajaID)
                lautapelit.add(peli.getLautapeliID());

        return lautapelit;
    }
    
    
    /**
     * Poistaa pelaajalta pelin
     * @param pelaajaID pelaaja. jolta peli poistetaan
     * @param peliID peli, joka poistetaan
     */
    public void poista(int pelaajaID, int peliID) {
        for (int i = 0; i < alkiot.size(); i++) {
            PelaajanPeli peli = alkiot.get(i);
            if (peli.getPelaajaID() == pelaajaID && peli.getLautapeliID() == peliID)
                alkiot.remove(i);
        }
    }
    

    /**
     * Poistaa kaikki pelit pelaajalta.
     * @param pelaaja pelaaja, jonka pelit poistetaan
     */
    public void poista(Pelaaja pelaaja) {
        for(int i = 0; i < alkiot.size(); i++){
            PelaajanPeli peli = alkiot.get(i);
            if(peli.getPelaajaID() == pelaaja.getID())
                alkiot.remove(i);
        }
        
    }
    /**
     * lukee tiedostosta pelaajanPelit tiedot, mit‰ pelej‰ kukin pelaaja omaa
     * ja asettaa tiedot.s
     */
    public void lueTiedosto() {
        try(Scanner fi = new Scanner(new FileInputStream(new File(tiedostonNimi)))) {
            while(fi.hasNextLine()) {
                String rivi = fi.nextLine();
                PelaajanPeli lisattava = new PelaajanPeli(rivi);
                lisaa(lisattava.getPelaajaID(), lisattava.getLautapeliID());
            }
            
        } catch(FileNotFoundException ex){
            System.err.println("Tiedostoa pelaajat.dat ei saada auki" + ex);
            
        } catch (OmaException e) {
            System.err.println("Pelaajan pelin lis‰‰minen ei onnistu" + e);
        }
        
    }
    

    /**
     * Tallentaa tiedot pelaajien lautapeleist‰ tiedostoon pelaajanPelit.dat .
     */
    public void tallenna() {
           try (PrintStream tiedostoon = new PrintStream(new FileOutputStream(tiedostonNimi,false))) {
               for(PelaajanPeli pelaajanPeli: alkiot)
                   tiedostoon.println(pelaajanPeli.tiedostoMuotoon());
               
           } catch (FileNotFoundException ex) {
               System.err.println("Tiedostoa ei saada auki");
           }
    }

}
