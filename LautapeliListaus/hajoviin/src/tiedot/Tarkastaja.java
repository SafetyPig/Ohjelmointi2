
package tiedot;

/**
 * @author Hannu Viinikainen
 * @version 25.4.2012
 * Tarkastaja rajapinta. Saadaan kenttiin yhden tyylisellä konstruktorilla monenlaisia
 * tarkastajia
 */
public interface Tarkastaja {
    
    /**
     * tarkistaa kentän tekstin oikeellisuuden.
     * @param arvo tarkistettava arvo
     * @return palauttaa virheilmoituksen, null, jos ei havaita virhettä
     */
    String tarkista(String arvo);
}
