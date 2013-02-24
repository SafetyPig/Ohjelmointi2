package rekisterit;

/**
 * Poikkeusrakenne tietorakenteessa havaitulle virheelle, esim. kun yritetään lisätä
 * alkiota taulukkoon, josta kyseinen alkio löytyy jo.
 * @author Hannu Viinikainen
 * @version 20.2.2012
 */
public class OmaException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeuksen konstruktori
     * @param viesti viesti, joka näytetään virheen yhteydessä
     */
    public OmaException(String viesti) {
        super(viesti);
    }
    
    
}
