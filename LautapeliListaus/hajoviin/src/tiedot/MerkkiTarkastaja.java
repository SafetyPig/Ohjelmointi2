
package tiedot;

/**
 * @author Hannu Viinikainen
 * @version 25.4.2012
 * Tarkastajaluokka, joka osaa katsoa, ettei pelin nime� ole aiemmin k�ytetty.
 *
 */
//TODO: Mieti miten saat lautapelit t�lle tarkastajalle fiksusti....
public class MerkkiTarkastaja implements Tarkastaja {

    private String hyvaksyttavatMerkit;
    /**
     * Tyhj� konstruktori
     */
    public MerkkiTarkastaja() {     
    }
    
    /**
     * Konstruktori, jolle tuodaan merkit, jotka hyv�ksyt��n kentt��n
     * @param merkit hyv�ksytt�v�t merkit
     */
    public MerkkiTarkastaja(String merkit) {
        this.hyvaksyttavatMerkit = merkit;
    }
    
    
    /**
     * Tarkistaa, ett� nimi ei ole aiemmin k�yt�ss�
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
