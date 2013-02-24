package tiedot;

/**
 * Rajapinta lautapeli listaukset tietueen yhdelle kent‰lle
 * @author Hannu Viinikainen
 * @version 4.4.2012
 */
public interface Kentta {
    
    /**
     * Palauttaa kent‰n tiedot merkkijononoa
     * @return kent‰n tiedot merkkijonona
     */
    @Override
    String toString();
    
    /**
     * Asettaa annetun arvon kentt‰‰n
     * @param arvo arvo, joka asetetaan kentt‰‰n
     * @return Palauttaa virhe tekstin, jos asettaminen ei onnistu
     */
    String asetaArvo(String arvo);
     
    /**
     * Palauttaa merkkijonon, joka kertoo, mit‰ tietoa (nimi, julkaisuvuosi yms.) kentt‰ sis‰lt‰‰
     * @return mist‰ kentt‰ sis‰lt‰‰ tietoa.
     */
    String getTieto();
}
