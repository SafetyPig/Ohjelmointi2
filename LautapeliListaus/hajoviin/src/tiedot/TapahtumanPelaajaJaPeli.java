package tiedot;

/**
 * Olio sis‰lt‰‰ tiedot yhden tapahtuma pelaajasta ja h‰nen yhdest‰ pelist‰‰n.
 * @author Hannu Viinikainen
 * @version 1.3.2012
 */
public class TapahtumanPelaajaJaPeli {
    
    private int tapahtumaID;
    private int pelaajaID;
    private int lautapeliID;
    
    /**
     * konstuktori, jolla saadaa kaikki kolme asetettua
     * @param tapahtumaID tapahtuman ID
     * @param pelaajaID pelaajan ID
     * @param peliID pelin ID
     */
    public TapahtumanPelaajaJaPeli(int tapahtumaID, int pelaajaID, int peliID) {
        this.tapahtumaID = tapahtumaID;
        this.pelaajaID = pelaajaID;
        this.lautapeliID = peliID;
    }
    
    /**
     * Lis‰‰ pelaajan tapahtumaan
     * @param tapahtumaID tapahtuman ID-numero
     * @param pelaajaID pelaajan ID-nnumero
     */
    public TapahtumanPelaajaJaPeli(int tapahtumaID, int pelaajaID) {
        this.tapahtumaID = tapahtumaID;
        this.pelaajaID = pelaajaID;
    }
    
    /**
     * Konstruktori, joka lukee tiedot stringist‰
     * @param rivi olion tarvitsemat tiedot muodossa int|int|int
     */
    public TapahtumanPelaajaJaPeli(String rivi) {
        String[] tieto = rivi.split("\\|");
        tapahtumaID = Integer.parseInt(tieto[0]);
        pelaajaID = Integer.parseInt(tieto[1]);
        lautapeliID = Integer.parseInt(tieto[2]);
        
    }
    
    /**
     * @return palauttaa tapahtumaID:n
     */
    public int getTapahtumaID() {
        return tapahtumaID;
    }
    
    /**
     * @return palauttaa pelaajan ID:n
     */
    public int getPelaajaID() {
        return pelaajaID;
    }
    
    /**
     * @return palauttaa pelin ID:n
     */
    public int getPeliID() {
        return lautapeliID;
    }
    
    /**
     * Tekee oliosto stringin, joka sopii tiedostoon tallentamiseen
     * @return palauttaa olion tiedot stringin‰, joka sopii tiedostoon tallentamista varten
     */
    public String tiedostoMuotoon() {     
        return tapahtumaID + "|" + pelaajaID + "|" + lautapeliID;
    }
}
