/**
 * 
 */
package rekisterit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import tiedot.Tapahtuma;
import tiedot.Vertailija;

/**
 * Lautapelilistauksen tilaisuudet, osaa lis‰t‰ tilaisuuden yms.
 * @author Hannu Viinikainen
 * @version 21.2.2012
 */
public class Tapahtumat {
    
    private int lukumaara = 0;
    private ArrayList<Tapahtuma> alkiot = new ArrayList<Tapahtuma>();
    private String tiedostonNimi = "tapahtumat.dat";
    
    /**
     * Tyhj‰ kontruktori
     */
    public Tapahtumat() {
    }
    
    /**
     * palauttaa listana kaikki tapahtuman alkiot, jotka vastaavat hakuehtoa
     * @param ehto hakuehto
     * @param hakuTyyppi Tyyppi, jonka mukaan haetaan
     * @return palauttaa listan tapahtuman alkiosta, jotka vastaavat hakuehtoa
     */
    public ArrayList<Tapahtuma> getTapahtumat(String ehto, int hakuTyyppi) {
        ArrayList<Tapahtuma> tapahtumat = new ArrayList<Tapahtuma>();
        for (Tapahtuma alkio : alkiot) {
            if (alkio.anna(hakuTyyppi).toLowerCase().startsWith(ehto))
                tapahtumat.add(alkio);
        }
        Collections.sort(tapahtumat, new Vertailija(hakuTyyppi));  
        
        return tapahtumat;
    }
    
    /**
     * Palauttaa lautapelien lukum‰‰r‰n
     * @return palauttaa tapahtumien lukum‰‰r‰n
     */
    public int getLukumaara() {
        return lukumaara;
    }
    
    /**
     * Lis‰‰ annetun pelin Tapahtuma-listaan
     * @param Tapahtuma lis‰tt‰v‰n tilaisuuden nimi
     * @throws OmaException Jos tietorakenne on t‰ynn‰ tai alkio on jo taulukossa
     * @example
     * <pre name="test">
     * #THROWS OmaException
     * #PACKAGEIMPORT
     * Tapahtumat Tapahtumat = new Tapahtumat();
     * Tapahtuma Tapahtuma1 = new Tapahtuma();
     * Tapahtuma Tapahtuma2 = new Tapahtuma();
     * Tapahtuma Tapahtuma3 = new Tapahtuma();
     * Tapahtumat.getLukumaara() === 0;
     * Tapahtuma1.rekisteroi(0);
     * Tapahtuma2.rekisteroi(1);
     * Tapahtuma1.vastaaTapahtuma();
     * Tapahtuma2.vastaaTapahtuma();
     * Tapahtumat.lisaa(Tapahtuma1); Tapahtumat.getLukumaara() === 1;
     * Tapahtumat.lisaa(Tapahtuma2); Tapahtumat.getLukumaara() === 2;
     * Tapahtumat.lisaa(Tapahtuma1); #THROWS OmaException
     * Tapahtumat.anna(1) === Tapahtuma1;
     * Tapahtumat.anna(2) === Tapahtuma2;
     * </pre>
     */
    public void lisaa(Tapahtuma Tapahtuma) throws OmaException {
        if (onkoTaulukossa(Tapahtuma))
            throw new OmaException("On jo taulukossa");
        alkiot.add(Tapahtuma);
        lukumaara++;
    }
    
    /**
     * Palauttaa tiedon, onko lis‰tt‰v‰ alkio jo valmiiksi listassa
     * @param Tapahtuma lis‰tt‰v‰n alkion nimi
     * @return tieto, onko alkio jo taulukossa
     */
    public boolean onkoTaulukossa(Tapahtuma Tapahtuma) {
        for (int i = 0; i < alkiot.size(); i++)
            if (alkiot.get(i).getNimi().equals(Tapahtuma.getNimi()))
                return true;
        
        return false;
    }
    
    /**
     * Palauttaa halutulla ID:ll‰ olevan tapahtuma
     * @param tapahtumaID ID, jonka tapahtuma halutaan palauttaa
     * @return annetulla ID:ll‰ oleva tapahtuma
     */
    public Tapahtuma anna(int tapahtumaID) {
        if (tapahtumaID <= 0)
            throw new IndexOutOfBoundsException("Laiton indeksi: "
                    + tapahtumaID);
        
        for (Tapahtuma tapahtuma : alkiot)
            if (tapahtuma.getID() == tapahtumaID)
                return tapahtuma;
        
        return null;
    }
    
    /**
     * Poistaa valitun tapahtuman
     * @param tapahtuma tapahtuma, joka halutaan poistaa
     */
    public void poista(Tapahtuma tapahtuma) {
        alkiot.remove(tapahtuma);
    }
    
    /**
     * lukee tiedostosta pelaajat.dat pelaajien tiedot ja asettaa
     * pelaahien tiedon ohjelmaan
     */
    public void lueTiedosto() {
        try (Scanner fi = new Scanner(new FileInputStream(new File(
                tiedostonNimi)))) {
            while (fi.hasNextLine()) {
                String rivi = fi.nextLine();
                Tapahtuma lisattava = new Tapahtuma(rivi);
                lisaa(lisattava);
            }
            
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedostoa tapahtumat.dat ei saada auki" + ex);
            
        } catch (OmaException e) {
            System.err.println("Tapahtuman lis‰ys ei onnistu" + e);
        }
        
    }
    
    /**
     * Tallentaa tapahtumat tapahtumat.dat tiedostoon
     */
    public void tallenna() {
        try (PrintStream tiedostoon = new PrintStream(new FileOutputStream(
                tiedostonNimi, false))) {
            for (Tapahtuma tapahtuma : alkiot)
                tiedostoon.println(tapahtuma.tiedostoMuotoon());
            
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedostoa ei saada auki");
        }
    }
    
    /**
     * testip‰‰ohjelma
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Tapahtumat tapahtumat = new Tapahtumat();
        Tapahtuma Tapahtuma1 = new Tapahtuma();
        Tapahtuma Tapahtuma2 = new Tapahtuma();
        Tapahtuma Tapahtuma3 = new Tapahtuma();
        
        Tapahtuma1.rekisteroi(0);
        Tapahtuma2.rekisteroi(0);
        Tapahtuma3.rekisteroi(0);
        
        Tapahtuma1.vastaaTapahtuma();
        Tapahtuma2.vastaaTapahtuma();
        Tapahtuma3.vastaaTapahtuma();
        
        try {
            tapahtumat.lisaa(Tapahtuma1);
            tapahtumat.lisaa(Tapahtuma2);
            tapahtumat.lisaa(Tapahtuma3);
            
            System.out.println("============= Tapahtuman tiedot =============");
            for (int i = 1; i <= tapahtumat.alkiot.size(); i++) {
                Tapahtuma Tapahtuma = tapahtumat.anna(i);
                System.out.println("Tapahtuman numero: " + i);
                Tapahtuma.tulosta(System.out);
            }
            
            tapahtumat.poista(Tapahtuma1);
            
            System.out.println("============= Tapahtuman tiedot =============");
            for (Tapahtuma tapahtuma : tapahtumat.alkiot) {
                System.out.println("Tapahtuma: ");
                tapahtuma.tulosta(System.out);
                
            }
            
        } catch (OmaException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
