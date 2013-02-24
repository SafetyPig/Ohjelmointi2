package rekisterit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import tiedot.Lautapeli;
import tiedot.Vertailija;

/**
 * Lautapelilistauksen lautapelit. Osaa lis‰t‰ lautapelej‰ sek‰ poistaa niit‰.
 * Osaa myˆs antaa lautapelin ID:n perusteella.
 * @author Hannu Viinikainen
 * @version 20.2.2012
 */
public class Lautapelit {
    
    private static final int maxKoko = 5;
    private int lukumaara = 0;
    private Lautapeli alkiot[] = new Lautapeli[maxKoko];
    private String tiedostonNimi = "lautapelit.dat";
    
    /**
     * Oletus konstruktori
     */
    public Lautapelit() {
        // ei tee mit‰‰n, koska atribuutit alustetaan valmiiksi
    }
    
    /**
     * Palauttaa lautapelien m‰‰r‰n
     * @return palauttaa lautapelien m‰‰r‰n
     */
    public int getLukumaara() {
        return lukumaara;
    }
    
    /**
     * Palauttaa tiedon, onko lis‰tt‰v‰ alkio jo valmiiksi listassa
     * @param peli lis‰tt‰v‰n alkion nimi
     * @return tieto, onko alkio jo taulukossa
     */
    public boolean onkoTaulukossa(Lautapeli peli) {
        for (int i = 0; i < lukumaara; i++)
            if (alkiot[i] != null && alkiot[i].getNimi().equals(peli.getNimi()))
                return true;
        
        return false;
    }
    
    /**
     * Palauttaa halutulla ID:ll‰ olevan lautapelin. Sijoittaa lautapelin kohdalle null.
     * @param peliID halutun pelin ID numero
     * @return palauttaa lautapelin, joka omaa annetun ID:n
     * @throws IndexOutOfBoundsException jos ID ei ole sallitulla alueella
     */
    public Lautapeli anna(int peliID) {
        if (peliID < 0)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + peliID);
        for (Lautapeli peli : alkiot)
            if (peli != null && peli.getID() == peliID)
                return peli;
        
        return null;
    }
    
    /**
     * Palauttaa pelin id numeron, kun tuodaan pelin nimi parametrin‰.
     * @param peli lautapelin nimi
     * @return pelin id numero, jos peli‰ ei lˆdy palauttaa -1
     */
    public int anna(String peli) {
        for (int i = 0; i < lukumaara; i++) {
            Lautapeli tarkastettava = alkiot[i];
            String pelinNimi = tarkastettava.getNimi();
            if (peli.length() != pelinNimi.length())
                continue;
            if (pelinNimi.equals(peli))
                return tarkastettava.getID();
        }
        
        return -1;
        
    }
    
    /**
     * Lis‰‰ annetun pelin lautapeli-listaan
     * @param peli lis‰tt‰v‰ peli
     * @throws OmaException Jos tietorakenne on t‰ynn‰ tai alkio on jo taulukossa
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
     * Caylus1.rekisteroi();
     * Caylus2.rekisteroi();
     * Caylus3.rekisteroi();
     * Caylus4.rekisteroi();
     * Caylus5.rekisteroi();
     * Caylus6.rekisteroi();
     * Caylus1.vastaaPeli();
     * Caylus2.vastaaPeli();
     * Caylus3.vastaaPeli();
     * Caylus4.vastaaPeli();
     * Caylus5.vastaaPeli();
     * Caylus6.vastaaPeli();
     * lautapelit.getLukumaara() === 0;
     * lautapelit.lisaa(Caylus1); lautapelit.getLukumaara() === 1;
     * lautapelit.lisaa(Caylus2); lautapelit.getLukumaara() === 2;
     * lautapelit.lisaa(Caylus1); #THROWS OmaException
     * lautapelit.anna(1) === Caylus1;
     * lautapelit.anna(2) === Caylus2;
     * lautapelit.lisaa(Caylus3);
     * lautapelit.lisaa(Caylus4);
     * lautapelit.lisaa(Caylus6);
     * lautapelit.lisaa(Caylus6); #THROWS OmaException
     * </pre>
     */
    public void lisaa(Lautapeli peli) throws OmaException {
        if (lukumaara >= alkiot.length)
            luoUusiTaulukko();
        if (onkoTaulukossa(peli))
            throw new OmaException("On jo taulukossa");
        alkiot[lukumaara] = peli;
        lukumaara++;
    }
    
    /**
     * Alkiot taulukon t‰yttyess‰ kopio tiedot uuteen taulukkoon ja 
     * muuttaa alkiot taulukon viitteen siihen
     */
    private void luoUusiTaulukko() {
        Lautapeli[] apu = new Lautapeli[alkiot.length * 2];
        for (int i = 0; i < alkiot.length; i++)
            apu[i] = alkiot[i];
        
        alkiot = apu;
        
    }
    
    /**
     * Poistaa listalta halutun lautapelin
     * @param peli poistettava peli
     */
    public void poista(Lautapeli peli) {
        for (int i = 0; i < lukumaara; i++) {
            if (alkiot[i] != null && peli.getID() == alkiot[i].getID())
                alkiot[i] = null;
        }
    }
    
    /**
     * Poistaa listalta halutun lautapelin
     * @param peli poistettavan pelin id
     */
    public void poista(int peli) {
        for (int i = 0; i < lukumaara; i++) {
            if (alkiot[i] != null && peli == alkiot[i].getID())
                alkiot[i] = null;
        }
    }
    /**
     * Lukee lautapelien tiedot lautapelit.dat tiedostosta
     */
    public void lueTiedosto() {
        try (Scanner fi = new Scanner(new FileInputStream(new File(
                tiedostonNimi)))) {
            while (fi.hasNextLine()) {
                String rivi = fi.nextLine();
                Lautapeli lisattava = new Lautapeli(rivi);
                lisaa(lisattava);
            }
            
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedostoa lautapelit.dat ei saada auki" + ex);
            
        } catch (OmaException e) {
            System.err.println("Lautapelin lis‰ys ei onnistu" + e);
        }
        
    }
    
    /**
     * Tallentaa lautapelien tiedot tiedostoon.
     */
    public void tallenna() {
        try (PrintStream tiedostoon = new PrintStream(new FileOutputStream(
                tiedostonNimi, false))) {
            for (int i = 0; i < lukumaara; i++)
                tiedostoon.println(alkiot[i].tiedostoMuotoon());
            
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedostoa ei saada auki");
        }
    }
    
    /**
     * testip‰‰ohjelma
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Lautapelit lautapelit = new Lautapelit();
        Lautapeli Caylus1 = new Lautapeli();
        Lautapeli Caylus2 = new Lautapeli();
        Lautapeli Caylus3 = new Lautapeli();
        Lautapeli Caylus4 = new Lautapeli();
        Lautapeli Caylus5 = new Lautapeli();
        
        Caylus1.rekisteroi();
        Caylus2.rekisteroi();
        Caylus3.rekisteroi();
        Caylus4.rekisteroi();
        Caylus5.rekisteroi();
        
        Caylus1.vastaaPeli();
        Caylus2.vastaaPeli();
        Caylus3.vastaaPeli();
        Caylus4.vastaaPeli();
        Caylus5.vastaaPeli();
        
        try {
            lautapelit.lisaa(Caylus1);
            lautapelit.lisaa(Caylus2);
            lautapelit.lisaa(Caylus3);
            lautapelit.lisaa(Caylus4);
            
            System.out.println("============= Lautapelin tiedot =============");
            for (int i = 1; i <= lautapelit.getLukumaara(); i++) {
                Lautapeli lautapeli = lautapelit.anna(i);
                System.out.println("Lautapelin numero: " + i);
                lautapeli.tulosta(System.out);
            }
            
            lautapelit.poista(Caylus3);
            lautapelit.poista(Caylus4);
            
            System.out.println("============= Lautapelin tiedot =============");
            for (int i = 1; i <= lautapelit.getLukumaara(); i++) {
                Lautapeli lautapeli = lautapelit.anna(i);
                if (lautapeli != null) {
                    System.out.println("Lautapelin numero: " + i);
                    lautapeli.tulosta(System.out);
                }
            }
            
            lautapelit.lisaa(Caylus4);
            
            System.out.println("============= Lautapelin tiedot =============");
            for (int i = 1; i <= lautapelit.getLukumaara(); i++) {
                Lautapeli lautapeli = lautapelit.anna(i);
                if (lautapeli != null) {
                    System.out.println("Lautapelin numero: " + i);
                    lautapeli.tulosta(System.out);
                }
            }
            
        } catch (OmaException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * J‰rjestaa lautapelilistan halutun kent‰n ja ehdon mukaan
     * @param pelienLista lista, joka j‰rjestet‰‰n
     * @param ehto ehto, jonka lautapelin tulee t‰ytt‰‰ pysy‰kseen listalla
     * @param hakuTyyppi mink‰ perusteella j‰rjestell‰‰n
     * @return j‰rjestetty lista, josta poistettu hakuja vastaamattomat lautapelit.
     */
    public ArrayList<Lautapeli> jarjestaLista(ArrayList<Lautapeli> pelienLista,
            String ehto, int hakuTyyppi) {
        for (int i = pelienLista.size() - 1; i >= 0; i--) {
            if (!(pelienLista.get(i).anna(hakuTyyppi).toLowerCase()
                    .startsWith(ehto)))
                pelienLista.remove(i);
        }
        
        Collections.sort(pelienLista, new Vertailija(hakuTyyppi));
        return pelienLista;
    }
}
