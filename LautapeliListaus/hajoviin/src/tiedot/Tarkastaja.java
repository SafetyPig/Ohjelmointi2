
package tiedot;

/**
 * @author Hannu Viinikainen
 * @version 25.4.2012
 * Tarkastaja rajapinta. Saadaan kenttiin yhden tyylisell� konstruktorilla monenlaisia
 * tarkastajia
 */
public interface Tarkastaja {
    
    /**
     * tarkistaa kent�n tekstin oikeellisuuden.
     * @param arvo tarkistettava arvo
     * @return palauttaa virheilmoituksen, null, jos ei havaita virhett�
     */
    String tarkista(String arvo);
}
