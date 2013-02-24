/**
 * 
 */
package tiedot;

/**
 * Varmistaa, ettei merkkijono ole tyhj�
 * @author Hannu Viinikainen
 * @version 18.5.2012
 */
public class TyhjanTarkastaja implements Tarkastaja {
    @Override
    public String tarkista(String arvo) {
        if (arvo.length() <= 0)
            return "Ei voi asettaa tyhj�� merkkijonoa nimeksi";
        
        return null;
    }
    
}
