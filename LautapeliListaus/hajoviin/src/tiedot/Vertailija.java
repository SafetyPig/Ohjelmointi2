
package tiedot;

import java.util.Comparator;

/**
 * Luokka, joka osaa verrata kahta tietuetta kesken��n.
 * @author Hannu Viinikainen
 * @version 18.4.2012
 */

public class Vertailija implements Comparator<Tietue> {
    
    private final int kentanNumero;
    
    /**
     * Alustetaan vertailija vertailemaan haluttua kentt��
     * @param kentta kentt�, jota halutaan vertailla
     */
    public Vertailija(int kentta) {
        this.kentanNumero = kentta;
    }
    
    /**
     * Vertaa kahta j�sent� toisiinsa
     * @return palauttaa tiedon, kumpi on suurempi. Palauttaa -1 jos vertailtava1 on
     * suurempi. 0, jos vertailtavat ovat yht� suuria. Palauttaa 1, jos vertailtava2 
     * on suurempi
     */
    @Override
    public int compare(Tietue vertailtava1, Tietue vertailtava2) {
        if(vertailtava1 == null || vertailtava2 == null)
            return 0;
        
        String verrattava1 = vertailtava1.anna(kentanNumero).toLowerCase();
        String verrattava2 = vertailtava2.anna(kentanNumero).toLowerCase();
        return verrattava1.compareTo(verrattava2);
    }
}
