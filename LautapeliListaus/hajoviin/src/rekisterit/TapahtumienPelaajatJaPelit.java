package rekisterit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import tiedot.Lautapeli;
import tiedot.Pelaaja;
import tiedot.TapahtumanPelaajaJaPeli;

/**
 * Sis‰lt‰‰ listan, jossa on tiedot kaikista tapahtumista ja niihin osallistuvista pelaajista,
 * sek‰ siit‰, mit‰ pelej‰ kukin pelaaja on tuomassa mukanaan.
 * 
 * @author Hannu Viinikainen
 * @version 1.3.2012
 */
public class TapahtumienPelaajatJaPelit {
    
    private ArrayList<TapahtumanPelaajaJaPeli> alkiot = new ArrayList<TapahtumanPelaajaJaPeli>();
    private String tiedostonNimi = "tapahtumanPelaajatJaPelit.dat";
    
    /**
     * tyhj‰ konstruktori
     */
    public TapahtumienPelaajatJaPelit() {
    }
    
    /**
     * Lis‰t‰‰n tapahtuman, pelaajan ja h‰nen pelins‰ yhdistelm‰ listaan.
     * @param tapahtumaID tapahtumaID
     * @param pelaajaID pelaajan ID-numero
     * @param peliID lautapelin ID-numero
     * @throws OmaException virhe, jos pelaajalla on jo kyseinen peli mukana tapahtumassa
     * @example
     * <pre name="test">
     *  #THROWS OmaException
     *  #PACKAGEIMPORT
     *  TapahtumienPelaajatJaPelit tiedot = new TapahtumienPelaajatJaPelit();
     *  Tapahtuma tapahtuma = new Tapahtuma();
     *  Pelaaja pate = new Pelaaja();
     *  Lautapeli peli = new Lautapeli();
     *  Lautapeli peli2 = new Lautapeli();
     *  
     *  tapahtuma.rekisteroi(0);
     *  pate.rekisteroi();
     *  peli.rekisteroi();
     *  peli2.rekisteroi();
     * 
     *  tiedot.lisaa(1,1,1);
     *  tiedot.lisaa(1,1,2);
     *  tiedot.lisaa(1,1,2); #THROWS OmaException
     * </pre>
     */
    public void lisaa(int tapahtumaID, int pelaajaID, int peliID)
            throws OmaException {
        TapahtumanPelaajaJaPeli yhdiste = new TapahtumanPelaajaJaPeli(
                tapahtumaID, pelaajaID, peliID);
        if (onkoTaulukossa(yhdiste))
            throw new OmaException(
                    "Sinulla on jo kyseinen peli tapahtumassa mukanasi");
        
        alkiot.add(yhdiste);
    }
    
    /**
     * Lis‰‰ pelaajan tapahtumaan
     * @param tapahtumaID tapahtuma
     * @param pelaajaID pelaaja, joka osallistuu tapahtumaan
     * @throws OmaException Jos pelaaja on jo tapahtumassa
     */
    public void lisaa(int tapahtumaID, int pelaajaID) throws OmaException {
        TapahtumanPelaajaJaPeli yhdiste = new TapahtumanPelaajaJaPeli(
                tapahtumaID, pelaajaID);
        if (onkoTaulukossa(yhdiste))
            throw new OmaException("Pelaaja on jo mukana tapahtumassa");
        
        alkiot.add(yhdiste);
    }
    
    /**
     * Palauttaa tiedon, onko tietyss‰ tapahtumasta tietty pelaaja ja h‰nelt‰ tietty peli.
     * @param yhdiste jonka olemassaolo listasta tarkastetaan.
     */
    private boolean onkoTaulukossa(TapahtumanPelaajaJaPeli yhdiste) {
        for (TapahtumanPelaajaJaPeli alkio : alkiot)
            if (alkio.getTapahtumaID() == yhdiste.getTapahtumaID()
                    && alkio.getPelaajaID() == yhdiste.getPelaajaID()
                    && alkio.getPeliID() == yhdiste.getPeliID())
                return true;
        return false;
    }
    
    /**
     * Palauttaa halutun tapahtuman pelaajat
     * @param tapahtumaID tapahtuma, jonka tiedot palautettaan
     * @return palauttaa tapahtuman pelaajat
     */
    public ArrayList<Integer> annaTapahtumanPelaajat(int tapahtumaID) {
        
        ArrayList<Integer> tiedot = new ArrayList<Integer>();
        
        for (TapahtumanPelaajaJaPeli tapahtuma : alkiot)
            if (tapahtumaID == tapahtuma.getTapahtumaID()
                    && !(onkoTaulukossa(tiedot, tapahtuma.getPelaajaID())))
                tiedot.add(tapahtuma.getPelaajaID());
        
        return tiedot;
    }
    
    /**
     * Palauttaa tiedon, onko tarkastettava pelaaja listalla
     * @param tiedot lista, jolta tarkastetaan
     * @param vertailtava tarkastaa onko pelaaja jo listalla 
     * @return tieto siit‰, onko pelaaja taulukossa
     */
    public boolean onkoTaulukossa(ArrayList<Integer> tiedot, int vertailtava) {
        for (int i = 0; i < tiedot.size(); i++)
            if (vertailtava == tiedot.get(i))
                return true;
        
        return false;
    }
    
    /**
     * Palauttaa halutun tapahtuman lautapelit
     * @param tapahtumaID tapahtuma, jonka tiedot palautettaan
     * @return palauttaa tapahtuman pelit
     */
    public ArrayList<Integer> annaTapahtumanPelit(int tapahtumaID) {
        
        ArrayList<Integer> tiedot = new ArrayList<Integer>();
        
        for (TapahtumanPelaajaJaPeli tapahtuma : alkiot)
            if (tapahtumaID == tapahtuma.getTapahtumaID()
                    && !(onkoTaulukossa(tiedot, tapahtuma.getPeliID()))
                    && tapahtuma.getPeliID() != 0)
                
                tiedot.add(tapahtuma.getPeliID());
        
        return tiedot;
    }
    
    /**
     * Poistaa pelaajan tapahtumasta
     * @param tapahtumaID tapahtuma, josta pelaaja poistetaan
     * @param pelaajaID pelaaja, joka poistetaan tapahtumasta
     */
    public void poista(int tapahtumaID, int pelaajaID) {
        for (int i = alkiot.size() - 1; i >= 0; i--) {
            TapahtumanPelaajaJaPeli pelaajanTapahtuma = alkiot.get(i);
            if (pelaajanTapahtuma.getTapahtumaID() == tapahtumaID && pelaajanTapahtuma.getPelaajaID() == pelaajaID)
                alkiot.remove(i);
        }
    }
    

    /**
     * Poistaa pelaajan kaikista tapahtumista sek‰ poistaa kaikki pelaajan j‰rjest‰m‰t tapahtumat
     * @param pelaajaID pelaajan ID numero
     */
    //TODO: Tee siten, ett‰ poistaa viel‰ kaikki tapahtumat, joissa kyseinen pelaaja on adminin‰.
    public void poista(int pelaajaID) {
        for (int i = alkiot.size() - 1; i >= 0; i--) {
            TapahtumanPelaajaJaPeli pelaaja = alkiot.get(i);
            if (pelaaja.getPelaajaID() == pelaajaID)
                alkiot.remove(i);
        }
        
    }
    
    /**
     * Poistaa lautapelin tapahtumasta, jos valittu peli on kyseisen pelaajan oma
     * @param tapahtumaID tapahtuma, josta peli poistetaan
     * @param pelaajaID lautapeli, joka poistettan
     * @param lautapeliID pelaaja, jolta peli poistetaan
     */
    public void poistaPeliTapahtumasta(int tapahtumaID, int pelaajaID,
            int lautapeliID) {
        for (int i = alkiot.size() - 1; i >= 0; i--) {
            TapahtumanPelaajaJaPeli alkio = alkiot.get(i);
            if ((alkio.getTapahtumaID() == tapahtumaID
                    && alkio.getPelaajaID() == pelaajaID && alkio.getPeliID() == lautapeliID))
                alkiot.remove(i);
        }
        
    }
    
    /**
     * Tarkistaa omistaako pelaaja halutun pelin
     * @param pelaaja pelaaja, jolta tarkastetaan
     * @param tarkastettava peli, joka tarkastetaan
     * @return palauttaa tiedon omistaako pelaaja kyseisen pelin
     */
    public boolean onkoPelaajanPeli(Pelaaja pelaaja, Lautapeli tarkastettava) {
        for (TapahtumanPelaajaJaPeli alkio : alkiot)
            if (alkio.getPelaajaID() == pelaaja.getID()
                    && alkio.getPeliID() == tarkastettava.getID())
                return true;
        
        return false;
    }
    

    /**
     * Lukee tiedostosta tiedot tapahtuman pelaajista ja peleist‰, sek‰ asettaa
     * tiedot.
     */
    public void lueTiedosto() {
        try(Scanner fi = new Scanner(new FileInputStream(new File(tiedostonNimi)))) {
            while(fi.hasNextLine()) {
                String rivi = fi.nextLine();
                TapahtumanPelaajaJaPeli lisattava = new TapahtumanPelaajaJaPeli(rivi);
                lisaa(lisattava.getTapahtumaID(), lisattava.getPelaajaID(), lisattava.getPeliID());
            }
            
        } catch(FileNotFoundException ex){
            System.err.println("Tiedostoa pelaajat.dat ei saada auki" + ex);
            
        } catch (OmaException e) {
            System.err.println("Pelaajan pelin lis‰‰minen ei onnistu" + e);
        }
        
    }
    

    /**
     * Tallentaa pelaajien tiedot pelaajat.dat tiedostoon.
     */
    public void tallenna() {
           try (PrintStream tiedostoon = new PrintStream(new FileOutputStream(tiedostonNimi,false))) {
               for(TapahtumanPelaajaJaPeli tapahtumanPelaajaJaPeli: alkiot)
                   tiedostoon.println(tapahtumanPelaajaJaPeli.tiedostoMuotoon());
               
           } catch (FileNotFoundException ex) {
               System.err.println("Tiedostoa ei saada auki");
           }
    }
    
    /**
     * Testip‰‰ohjelma
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        TapahtumienPelaajatJaPelit tapahtumat = new TapahtumienPelaajatJaPelit();
        try {
            tapahtumat.lisaa(1, 1);
            tapahtumat.lisaa(1, 2);
            tapahtumat.lisaa(1, 3, 3);
            tapahtumat.lisaa(1, 3);
            
            tapahtumat.lisaa(1, 2, 1);
            tapahtumat.lisaa(1, 1, 1);
            tapahtumat.lisaa(1, 1, 2);
            tapahtumat.lisaa(1, 1, 3);
            tapahtumat.lisaa(1, 3, 2);
            tapahtumat.lisaa(1, 3, 1);
            
            ArrayList<Integer> pelaajat = tapahtumat.annaTapahtumanPelaajat(1);
            String apu = "";
            for (int i = 0; i < pelaajat.size(); i++)
                apu += pelaajat.get(i);
            
            System.out.println(apu);
            
            ArrayList<Integer> pelit = tapahtumat.annaTapahtumanPelit(1);
            
            String apu2 = "";
            for (int i = 0; i < pelit.size(); i++)
                apu2 += pelit.get(i);
            
            System.out.println(apu2);
            
            tapahtumat.poista(1, 3);
            
            pelaajat = tapahtumat.annaTapahtumanPelaajat(1);
            apu = "";
            for (int i = 0; i < pelaajat.size(); i++)
                apu += pelaajat.get(i);
            
            System.out.println(apu);
            
        } catch (OmaException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

}
