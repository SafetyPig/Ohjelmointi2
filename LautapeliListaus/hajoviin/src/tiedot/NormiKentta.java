
package tiedot;

/**
 * @author Hannu Viinikainen
 * @version 4.4.2012
 */
public abstract class NormiKentta implements Kentta{
    
    private final String tieto;
    /**
     * Viite yleiselle tarkastajalle, jotta kentät voivat käyttää omia tarkistajiaan
     */
    protected Tarkastaja tarkastaja;

    /**
     * Konstuktori, jolle tuodaan tieto siitä, mitä tietoa kenttä sisältää
     * @param tieto kentän tiedon tyyli(pelaajamäärä tai vastaava)
     */
    public NormiKentta(String tieto) {
        this.tieto = tieto;
    }
    
    /**
     * Konstruktori, jolla tuodaan tieto, mitä kenttään syötetään, ja millä se tarkistetaan
     * @param tieto tieto, joka kenttään syötetään
     * @param tarkastaja tarkastaja, joka tarkistaa tiedon oikeellisuuden
     */
    public NormiKentta(String tieto, Tarkastaja tarkastaja) {
        this.tieto = tieto;
        this.tarkastaja = tarkastaja;
    }
    
    
    /**
     * Asettaa kentälle halutun arvon
     * @param arvo kenttään sijoitettava arvo
     * @return palauttaa virheilmoituksen, jos arvo ei ole soveltuva kenttään
     */
    public abstract String asetaArvo(String arvo);
    
    /**
     * Palauttaa kentän sisältämän tiedon tyypin
     */
    public String getTieto() {
        return tieto;
    }

    /**
     * Palauttaa käytettävissä olevan tarkastajan
     * @return palauttaa tarkastajan
     */
    public Tarkastaja getTarkastaja() {
        return tarkastaja;
    }
    
}
