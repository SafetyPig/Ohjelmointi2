
package tiedot;

/**
 * @author Hannu Viinikainen
 * @version 4.4.2012
 */
public abstract class NormiKentta implements Kentta{
    
    private final String tieto;
    /**
     * Viite yleiselle tarkastajalle, jotta kent�t voivat k�ytt�� omia tarkistajiaan
     */
    protected Tarkastaja tarkastaja;

    /**
     * Konstuktori, jolle tuodaan tieto siit�, mit� tietoa kentt� sis�lt��
     * @param tieto kent�n tiedon tyyli(pelaajam��r� tai vastaava)
     */
    public NormiKentta(String tieto) {
        this.tieto = tieto;
    }
    
    /**
     * Konstruktori, jolla tuodaan tieto, mit� kentt��n sy�tet��n, ja mill� se tarkistetaan
     * @param tieto tieto, joka kentt��n sy�tet��n
     * @param tarkastaja tarkastaja, joka tarkistaa tiedon oikeellisuuden
     */
    public NormiKentta(String tieto, Tarkastaja tarkastaja) {
        this.tieto = tieto;
        this.tarkastaja = tarkastaja;
    }
    
    
    /**
     * Asettaa kent�lle halutun arvon
     * @param arvo kentt��n sijoitettava arvo
     * @return palauttaa virheilmoituksen, jos arvo ei ole soveltuva kentt��n
     */
    public abstract String asetaArvo(String arvo);
    
    /**
     * Palauttaa kent�n sis�lt�m�n tiedon tyypin
     */
    public String getTieto() {
        return tieto;
    }

    /**
     * Palauttaa k�ytett�viss� olevan tarkastajan
     * @return palauttaa tarkastajan
     */
    public Tarkastaja getTarkastaja() {
        return tarkastaja;
    }
    
}
