package rekisterit;

/**
 * Poikkeusrakenne tietorakenteessa havaitulle virheelle, esim. kun yritet��n lis�t�
 * alkiota taulukkoon, josta kyseinen alkio l�ytyy jo.
 * @author Hannu Viinikainen
 * @version 20.2.2012
 */
public class OmaException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeuksen konstruktori
     * @param viesti viesti, joka n�ytet��n virheen yhteydess�
     */
    public OmaException(String viesti) {
        super(viesti);
    }
    
    
}
