
package tiedot;

/**
 * @author Hannu Viinikainen
 * @version 25.4.2012
 * Tarkastajaluokka, joka osaa katsoa, ettei pelin nimeä ole aiemmin käytetty.
 *
 */
//TODO: Mieti miten saat lautapelit tälle tarkastajalle fiksusti....
public class MerkkiTarkastaja implements Tarkastaja {

    private String hyvaksyttavatMerkit;
    /**
     * Tyhjä konstruktori
     */
    public MerkkiTarkastaja() {     
    }
    
    /**
     * Konstruktori, jolle tuodaan merkit, jotka hyväksytään kenttään
     * @param merkit hyväksyttävät merkit
     */
    public MerkkiTarkastaja(String merkit) {
        this.hyvaksyttavatMerkit = merkit;
    }
    
    
    /**
     * Tarkistaa, että nimi ei ole aiemmin käytössä
     * @param arvo tarkastettava arvo
     */
    @Override
    public String tarkista(String arvo) {
        for(int i = 0; i < arvo.length(); i++)
            if(hyvaksyttavatMerkit.indexOf(arvo.charAt(i)) == -1)
                return "Kielletty merkki";
        
        return null;
    }
    
}
