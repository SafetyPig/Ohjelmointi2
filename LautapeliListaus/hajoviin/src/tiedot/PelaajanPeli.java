/**
 * 
 */
package tiedot;

/**
 * Yhden pelaajan yksi peli
 * @author Hannu Viinikainen
 * @version 27.2.2012
 */
public class PelaajanPeli {
    
    private int pelaajaID;
    private int lautapeliID;
    
    /**
     * Luo olion, joka kertoo, mik‰ lautapeli on pelaajalla
     * @param pelaajaID lautapelin omistaja
     * @param lautapeliID lautapeli, joka lis‰t‰‰n pelaajalle
     */
    public PelaajanPeli(int pelaajaID, int lautapeliID) {
        this.pelaajaID = pelaajaID;
        this.lautapeliID = lautapeliID;
    }
    
    /**
     * tyhj‰ konstruktori
     */
    public PelaajanPeli() {
        //tyhj‰
    }
    
    /**
     * Konstuktori, joka luo olion tiedostosta saadusta tiedosta
     * @param tiedot tiedostosta luettu rivi
     */
    public PelaajanPeli(String tiedot) {
        parse(tiedot);
    }
    
    
    /**
     * Muokkaa rivin muotoa int|int pelaajan tiedoiksi
     * @param tiedot string, josta tiedot luetaan
     */
    private void parse(String tiedot) {
        String[] tieto = tiedot.split("\\|");
        pelaajaID = Integer.parseInt(tieto[0]);
        lautapeliID = Integer.parseInt(tieto[1]);      
    }

    /**
     * @return  Palauttaa pelaajan
     */
    public int getPelaajaID() {
        return pelaajaID;
    }
    
    /**
     * @return palauttaa lautapelin
     */
    public int getLautapeliID() {
        return lautapeliID;
    }

    /**
     * Tekee oliosta strigin, joka on sopiva tiedostoon tallentamista varten
     * @return tiedostomuotoon soveltuva string
     */
    public String tiedostoMuotoon() {
        return pelaajaID + "|" + lautapeliID;
    }

}
