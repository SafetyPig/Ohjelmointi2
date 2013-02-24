/**
 * 
 */
package tiedot;

/**
 * Varmistaa, ettei merkkijono ole tyhjä
 * @author Hannu Viinikainen
 * @version 18.5.2012
 */
public class TyhjanTarkastaja implements Tarkastaja {
    @Override
    public String tarkista(String arvo) {
        if (arvo.length() <= 0)
            return "Ei voi asettaa tyhjää merkkijonoa nimeksi";
        
        return null;
    }
    
}
