package listausSwing;

import ikkunatGUI.Alkuikkuna;
import ikkunatGUI.PelinLisaaminen;
import ikkunatGUI.TapahtumaGUI;
import ikkunatGUI.TapahtumanLuonti;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import fi.jyu.mit.gui.*;
import rekisterit.*;
import tiedot.*;

import lautapelilistaus.Lautapelilistaus;

/**
 * Lautapelilistauksen k‰ytt‰m‰t omatekem‰t aliohjelmat. Lukee tiedon, mit‰
 * k‰ytt‰j‰ lis‰‰ sek‰ pyˆritt‰‰ toimintaa.
 * @author Hannu Viinikainen
 * @version 21.04.2012
 * TODO: 
 *       Profiilissa tapahtuman hakeminen ei toimi Adminin osalta(vertaa id:hen ei nimeen)
 *       
 *       Luodun tapahtuman tietojen muokkaus
 *       
 *      Jos painaa tallenna, valitsee listPelien ylimm‰n alkion aina. Muokkaa joskus.  
 *      Kun lis‰‰ uuden pelin, ei valitse sit‰ automaattisesti vaan ottaa ylimm‰n pelin
 *          
 *       Jos kahdella hengell‰ on sama peli mukana tapahtumassa, listassa on vain yhden kerran peli.  
 *       Tee taulukkoon ruutu, joka kertoo kell‰ kaikilla kyseinen peli on mukana.(pikkuvika)
 *       
 *       Osallistuminen nappi ei toimi kunnolla. Ei ole pohjassa kun on osallistunut.(pikkuvika)
 */
public class ListausSwing {
    
    private final Lautapelilistaus lautapeliListaus;
    private Pelaaja pelaaja;
    private Tapahtuma tapahtuma;
    private AbstractChooser<Lautapeli> listPelit;
    private AbstractChooser<Tapahtuma> listTapahtumat;
    private JTextField haePeleista;
    private JTextField haeTapahtumista;
    private JTextField haeOsallistujista;
    private JTextField haePelaajanPeleista;
    private JComponent panel_tiedot;
    private JLabel labelVirhe;
    private static Color virheVari = new Color(200, 50, 0);
    private Lautapeli lautapeliKohdalla = null;
    
    private AbstractChooser<Pelaaja> listOsallistujat;
    private AbstractChooser<Lautapeli> listPelaajanLautapelit;
    private JTextField textNimi;
    private StringTable tableTiedot;
    private AbstractChooser<Lautapeli> tapahtumanLautapelit;
    
    private AbstractChooser<String> comboBox_lautapelit;
    private AbstractChooser<String> comboBox_tapahtumat;
    private AbstractChooser<String> tapahtumanCombo;
    private JPanel tapahtumanTiedot;
    private EditPanel[] tietoPalkit; // Jokaisen lautapelin kent‰n editPanel on
                                     // tiedossa.
    private Lautapeli apuPeli = new Lautapeli();
    private Tapahtuma apuTapahtuma = new Tapahtuma();
    
    private EditKeyReleased editKeyReleased = new EditKeyReleased();
    
    /**
     * Alustaa luokan niin, ett‰ voi k‰ytt‰‰ Swing komponentteja 
     */
    public ListausSwing() {
        lautapeliListaus = new Lautapelilistaus();
        
    }
    
    /**
     * Aliohjelma alustamaan profiiliIkkuna valmiiksi. Asetetaan kuuntelijat tarvittaviin
     * kenttiin, jotta ikkunan p‰ivitt‰minen onnistuu
     */
    public void alustaProfiili() {
        
        luoProfiiliNaytto();
        
        haePeleista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                naytaProfiilinLautapelit();
            }
        });
        
        haeTapahtumista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                naytaTapahtumat();
            }
        });
        
        comboBox_lautapelit
                .addSelectionChangeListener(new SelectionChangeListener<String>() {
                    @Override
                    public void selectionChange(
                            IStringListChooser<String> sender) {
                        naytaProfiilinLautapelit();
                    }
                });
        
        comboBox_tapahtumat
                .addSelectionChangeListener(new SelectionChangeListener<String>() {
                    @Override
                    public void selectionChange(
                            IStringListChooser<String> sender) {
                        naytaTapahtumat();
                    }
                });
        
        listPelit
                .addSelectionChangeListener(new SelectionChangeListener<Lautapeli>() {
                    @Override
                    public void selectionChange(
                            IStringListChooser<Lautapeli> sender) {
                        naytaLautapeli(listPelit);
                        
                    }
                });
        
        naytaProfiilinLautapelit();
        
    }
    
    /**
     * Alustaa tapahtumaikkunan
     */
    public void alustaTapahtuma() {
        
        haeOsallistujista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                naytaOsallistujat();
            }
        });
        
        haePelaajanPeleista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                naytaLautapeliLista();
            }
        });
        
        listOsallistujat
                .addSelectionChangeListener(new SelectionChangeListener<Pelaaja>() {
                    @Override
                    public void selectionChange(
                            IStringListChooser<Pelaaja> sender) {
                        naytaLautapeliLista();
                    }
                });
        
        listPelaajanLautapelit
                .addSelectionChangeListener(new SelectionChangeListener<Lautapeli>() {
                    @Override
                    public void selectionChange(
                            IStringListChooser<Lautapeli> sender) {
                        asetaLautapelinTiedot(listPelaajanLautapelit);
                    }
                });
        
        tapahtumanLautapelit
                .addSelectionChangeListener(new SelectionChangeListener<Lautapeli>() {
                    @Override
                    public void selectionChange(
                            IStringListChooser<Lautapeli> sender) {
                        asetaLautapelinTiedot(tapahtumanLautapelit);
                    }
                });
        
        tapahtumanCombo
                .addSelectionChangeListener(new SelectionChangeListener<String>() {
                    @Override
                    public void selectionChange(
                            IStringListChooser<String> sender) {
                        naytaLautapeliLista();
                    }
                });
        
        luoTapahtumaNaytto();
    }
    
    /**
     * Laittaa n‰kyville kaikki osallistujat, jotka ovat ilmoittautuneet tapahtumaan.
     */
    protected void naytaOsallistujat() {
        
        listOsallistujat.clear();
        
        ArrayList<Integer> osallistujat = lautapeliListaus
                .annaTapahtumanPelaajat(listTapahtumat.getSelectedObject()
                        .getID());
        
        String ehto = haeOsallistujista.getText().toLowerCase();
        
        for (int osallistuja : osallistujat)
            try {
                String osallistujanNimi = lautapeliListaus.annaPelaaja(
                        osallistuja).getNimi();
                if (osallistujanNimi.toLowerCase().startsWith(ehto))
                    listOsallistujat.add(osallistujanNimi,
                            lautapeliListaus.annaPelaaja(osallistuja));
                
            } catch (OmaException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ei onnistuttu lis‰‰m‰‰n pelaajaa listalle "
                                + ex.getMessage());
            }
        
    }
    
    /**
     * N‰ytt‰‰ pelaajan lautapelit tapahtuma-ikkunassa
     */
    protected void naytaLautapeliLista() {
        
        listPelaajanLautapelit.clear();
        
        if (listOsallistujat.getSelectedObject() != null) {
            ArrayList<Integer> pelit = lautapeliListaus
                    .annaPelaajanPelit(listOsallistujat.getSelectedObject()
                            .getID());
            
            ArrayList<Lautapeli> pelienLista = new ArrayList<Lautapeli>();
            
            for (int i = 0; i < pelit.size(); i++) {
                int peli = pelit.get(i);
                try {
                    pelienLista.add(lautapeliListaus.annaLautapeli(peli));
                } catch (OmaException e) {
                    JOptionPane.showMessageDialog(null,
                            "Virhe pelin lis‰‰misess‰ listaan");
                }
            }
            
            String ehto = "";
            ehto = haePelaajanPeleista.getText().toLowerCase();
            int combonIndeksi = tapahtumanCombo.getSelectedIndex();
            int hakuTyyppi = 0;
            
            // CombonIndeksi 0 = nimi, combonIndeksi 1 = genre;
            switch (combonIndeksi) {
            case 0:
                hakuTyyppi = 1;
                break;
            
            case 1:
                hakuTyyppi = 2;
                break;
            }
            
            lautapeliListaus.jarjestaLista(pelienLista, ehto, hakuTyyppi);
            
            for (int i = 0; i < pelienLista.size(); i++) {
                listPelaajanLautapelit.add(pelienLista.get(i).getNimi(),
                        pelienLista.get(i));
            }
        }
        
    }
    
    /**
     * N‰ytt‰‰ listasta valitun lautapelin tiedot
     * @param lista mink‰listan lautapelit n‰ytett‰‰n
     */
    protected void naytaLautapeli(AbstractChooser<Lautapeli> lista) {
        int ind = lista.getSelectedIndex();
        if (ind < 0)
            return;
        lautapeliKohdalla = lista.getSelectedObject();
        for (int i = 0, k = apuPeli.getEkaTietoKentta(); i < tietoPalkit.length; i++, k++)
            tietoPalkit[i].setText(lautapeliKohdalla.anna(k));
        
    }
    
    /**
     * Asettaa profiili-ikkunaan n‰kyville pelaajan lautapelit
     */
    protected void naytaProfiilinLautapelit() {
        listPelit.clear();

        ArrayList<Integer> pelit = lautapeliListaus.annaPelaajanPelit(pelaaja
                .getID());
        
        ArrayList<Lautapeli> pelienLista = new ArrayList<Lautapeli>();
        
        for (int i = 0; i < pelit.size(); i++) {
            int peli = pelit.get(i);
            try {
                pelienLista.add(lautapeliListaus.annaLautapeli(peli));
            } catch (OmaException e) {
                JOptionPane.showMessageDialog(null,
                        "Virhe pelin lis‰‰misess‰ listaan");
            }
        }
        
        String ehto = "";
        ehto = haePeleista.getText().toLowerCase();
        int combonIndeksi = comboBox_lautapelit.getSelectedIndex();
        int hakuTyyppi = 0;
        
        // CombonIndeksi 0 = nimi, combonIndeksi 1 = genre;
        switch (combonIndeksi) {
        case 0:
            hakuTyyppi = 1;
            break;
        
        case 1:
            hakuTyyppi = 2;
            break;
        }
        
        lautapeliListaus.jarjestaLista(pelienLista, ehto, hakuTyyppi);
        
        for (int i = 0; i < pelienLista.size(); i++) {
            listPelit.add(pelienLista.get(i).getNimi(), pelienLista.get(i));
        }
     
        listPelit.setSelectedIndex(0);
        
    }
    
    /**
     * Asettaa profiili-ikkunaan tapahtumat n‰kyviin.
     * @throws OmaException virhe haussa
     */
    protected void naytaProfiilinTapahtumat() throws OmaException {
        listTapahtumat.clear();
        int index = 0;
        for (int i = 1; i <= lautapeliListaus.getTapahtumienMaara(); i++) {
            Tapahtuma tapahtuma = lautapeliListaus.annaTapahtuma(i);
            if (tapahtuma != null) {
                listTapahtumat.add(tapahtuma.getNimi(), tapahtuma);
            }
        }
        
        listTapahtumat.setSelectedIndex(index);
    }
    
    /**
     * Ilmoittaa jos haettaessa tulee virhe
     * @param virhe virheteksti
     */
    public void setVirhe(String virhe) {
        if (labelVirhe == null)
            return;
        if (virhe == null) {
            labelVirhe.setVisible(false);
            return;
        }
        labelVirhe.setVisible(true);
        labelVirhe.setText(virhe);
        labelVirhe.setBackground(virheVari);
    }
    
    /**
     * Asettaa profiili n‰yttˆˆn editPanel kent‰t, sek‰ pelaajan nimen oikealla kohdalla.
     * Lis‰‰ myˆs tapahtumat listaan sek‰ kirjautuneen pelaajan lautapelit. 
     */
    public void luoProfiiliNaytto() {
        
        textNimi.setText("Profiili: " + pelaaja.getNimi());
        panel_tiedot.removeAll();
        panel_tiedot.setPreferredSize(new Dimension(250, 14));
        panel_tiedot.setLayout(new BoxLayout(panel_tiedot, BoxLayout.Y_AXIS));
        tietoPalkit = new EditPanel[apuPeli.getKenttia()
                - apuPeli.getEkaTietoKentta()];
        
        for (int i = apuPeli.getEkaTietoKentta(), k = 0; i < apuPeli
                .getKenttia(); i++, k++) {
            EditPanel tietoPalkki = new EditPanel();
            tietoPalkki.setCaption(apuPeli.getTieto(i));
            tietoPalkki.setName(i + "tietopalkki");
            tietoPalkki.getEdit().setName(i + "tieto");
            tietoPalkit[k] = tietoPalkki;
            panel_tiedot.add(tietoPalkki);
            tietoPalkki.addKeyListener(editKeyReleased);
        }
        
        asetaMuokattavuus();
        
        naytaTapahtumat();
    }
    
    /**
      * K‰sittelij‰luokka edit kent‰n n‰pp‰imen vapauttamiselle 
      */
    protected class EditKeyReleased extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            muuttuneenTiedonKasittely((JTextField) e.getComponent());
        }
    }
    
    /**
     * K‰sittelee tapahtuman, kun edit kent‰n tietoa muutetaan
     * @param kentta kentt‰, jonka tietoa muutetaan
     */
    private void muuttuneenTiedonKasittely(JTextField kentta) {
        Lautapeli muokattavaPeli = listPelit.getSelectedObject();
        String alkuperainenNimi = muokattavaPeli.getNimi();
        
        String teksti = kentta.getText();
        int kentanNumero = Integer.parseInt(kentta.getName().substring(0, 1));
        int varjattavaKentta = kentanNumero - muokattavaPeli.getEkaTietoKentta();
        
        if (kentanNumero == muokattavaPeli.getEkaTietoKentta()) {
            if (lautapeliListaus.anna(teksti) != -1 && !(alkuperainenNimi.equals(teksti))) {
                tietoPalkit[varjattavaKentta].setBackground(virheVari);
                return;
            }
        }
        
        tietoPalkit[varjattavaKentta].setBackground(null);
        
        if (muokattavaPeli.aseta(kentanNumero, teksti) != null)
            tietoPalkit[varjattavaKentta].setBackground(virheVari);
        
        else {
            tietoPalkit[varjattavaKentta].setBackground(null);
        }
    }
    
    /**
      * Luo Tapahtuma n‰yttˆ ikkunan oikeinlaiseksi, Tekee tyhj‰n tekstikent‰n
      * johon tiedot laitetaan
       */
    private void luoTapahtumaNaytto() {
        tapahtuma = listTapahtumat.getSelectedObject();
        
        asetaLautapelinTiedot(listPelit);
        listPelaajanLautapelit.clear();
        listOsallistujat.clear();
        
        naytaOsallistujat();
        
        tapahtumanLautapelit.clear();
        naytaTapahtumanPelit();
        
        tapahtumanTiedot.removeAll();
        tapahtumanTiedot.setLayout(new BoxLayout(tapahtumanTiedot,
                BoxLayout.X_AXIS));
        
        Pelaaja jarjestaja;
        int admin = tapahtuma.getEkaTietoKentta() - 1;
        try {
            jarjestaja = lautapeliListaus.annaPelaaja(admin);
        } catch (OmaException e) {
            return;
        }
        
        JLabel jarjestajanPaikka = new JLabel("J‰rjest‰j‰ :"
                + jarjestaja.getNimi());
        tapahtumanTiedot.add(jarjestajanPaikka);
        
        for (int i = tapahtuma.getEkaTietoKentta(); i < tapahtuma.getKenttia(); i++) {
            JLabel vali = new JLabel("    ");
            tapahtumanTiedot.add(vali);
            JLabel info = new JLabel(tapahtuma.anna(i));
            tapahtumanTiedot.add(info);
        }
        
    }
    
    /**
     * Asettaa lautapelin tiedot taulukkoon. Taulukko on tapahtuma ikkunassa
     */
    private void asetaLautapelinTiedot(AbstractChooser<Lautapeli> lista) {
        
        lautapeliKohdalla = lista.getSelectedObject();
        if (lautapeliKohdalla == null)
            return;
        
        String[][] tiedot = new String[apuPeli.getKenttia()
                - apuPeli.getEkaTietoKentta()][2];
        
        for (int ix = 0; ix < tiedot.length; ix++)
            for (int iy = 0; iy < 1; iy++)
                tiedot[ix][iy] = apuPeli.getTieto(ix + 1);
        
        for (int ix = 0; ix < tiedot.length; ix++)
            for (int iy = 1; iy < 2; iy++)
                tiedot[ix][iy] = lautapeliKohdalla.anna(ix + 1);
        
        tableTiedot.getTable().setModel(
                new DefaultTableModel(tiedot, new String[] { "", "" }));
        
        tableTiedot.setPreferredSize(new Dimension(500, 500));
        tableTiedot.setEnabled(false);
    }
    
    /**
     * Alustaa henkilˆn nimen perusteella profiili-ikkunan kuntoon
     * @param nimi henkilˆn nimi
     * @return null jos onnistuu, muuten virheilmoitus
     */
    public String alusta(String nimi) {
        if (haePeleista == null)
            return "haePeleista on alustamatta";
        if (listPelit == null)
            return "listPelit on alustamatta";
        if (panel_tiedot == null)
            return "panel_tiedot on alustamatta";
        
        String virhe;
        
        if (nimi.length() >= 4 && nimi.substring(0, 4).equals("new*")) {
            virhe = lisaaPelaaja(nimi);
            if (virhe != null)
                return "Virhe";
        }
        
        else {
            pelaaja = lautapeliListaus.annaPelaaja(nimi);
        }
        
        alustaProfiili();
        tallenna();
        return null;
    }
    
    /**
     * Lisaa uuden pelin k‰ytt‰j‰lle ja j‰rjestelm‰‰n.
     */
    public void lisaaPeli() {
        String nimi = PelinLisaaminen.uusiPeli();
        if (nimi.equals(""))
            return;
        Lautapeli peli = new Lautapeli(nimi);
        try {
            peli.rekisteroi();
            lautapeliListaus.lisaa(peli);
            lautapeliListaus.lisaa(pelaaja.getID(), peli.getID());
            lautapeliKohdalla = peli;
            naytaProfiilinLautapelit();
        } catch (OmaException ex) {
            
            try {
                lautapeliListaus.lisaa(pelaaja.getID(),
                        lautapeliListaus.anna(peli.getNimi()));
                naytaProfiilinLautapelit();
            } catch (OmaException virhe) {
                JOptionPane
                        .showMessageDialog(null, "Omistat jo kyseisen pelin");
            }
        }
        
        for (int i = 0; i < tietoPalkit.length; i++) {
            EditPanel tietoPalkki = tietoPalkit[i];
            tietoPalkki.getEdit().setEnabled(true);
        }
    }
    
    /**
     *  Lis‰‰ tapahtuman j‰rjestelm‰‰n. P‰ivitt‰‰ samalla myˆs tapahtumalistan
     */
    public void luoTapahtuma() {
        String tapahtumaTeksti = TapahtumanLuonti.uusiTapahtuma(apuTapahtuma);
        if (tapahtumaTeksti == null)
            return;
        
        tapahtuma = new Tapahtuma(tapahtumaTeksti);
        tapahtuma.rekisteroi(pelaaja.getID());
        try {
            lautapeliListaus.lisaa(tapahtuma);
            naytaProfiilinTapahtumat();
        } catch (OmaException ex) {
            JOptionPane
                    .showMessageDialog(null,
                            "Annetun niminen tapahtuma on jo olemassa. Valitse jokin toinen nimi");
        }
        naytaTapahtumat();
        
    }
    
    /**
     * Tallentaa ohjelmaan muokatut tiedot.
     */
    public void tallenna() {
        lautapeliListaus.tallenna();
        naytaProfiilinLautapelit();
    }
    
    /**
     * Kysyy k‰ytt‰j‰ tunnuksen alkuikkunan avulla. Mahdollisuus alkuikkunassa myˆs luoda uusi k‰ytt‰j‰
     * @param pelaajat kaikki j‰rjestelm‰n pelaajat, tarkistamista varten
     */
    public void kysyKayttaja(ArrayList<String> pelaajat) {
        String pelaaja = Alkuikkuna.kysyNimi(pelaajat);
        if (alusta(pelaaja) != null) {
            JOptionPane.showMessageDialog(null,
                    "K‰ytt‰j‰ \"" + pelaaja.substring(4) + "\" on jo olemassa");
            kysyKayttaja(pelaajat);
        }
    }
    
    /**
     * Siirtyy valittuun tapahtumaan.
     * @param tapahtumanNimi tapahtuma johon siirryt‰‰n
     * @param listausSwing mihink‰ listaukseen tekee tapahtumaikkuna muutokset
     */
    public void siirryTapahtumaan(String tapahtumanNimi,
            final ListausSwing listausSwing) {
        if (tapahtumanNimi.equals(""))
            JOptionPane.showMessageDialog(null,
                    "Valitse tapahtuma, johon haluat siirty‰");
        
        else {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        TapahtumaGUI frame = new TapahtumaGUI(listausSwing);
                        frame.setVisible(true);
                        alustaTapahtuma();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    
    /**
     *  Poistaa valitun pelin
     */
    public void poistaPeli() {
        if (listPelit.getSelectedIndex() == -1)
            JOptionPane.showMessageDialog(null, "Valitse poistettava peli");
        
        else {
            lautapeliListaus.poista(pelaaja.getID(), listPelit
                    .getSelectedObject().getID());
            naytaProfiilinLautapelit();
        }
        asetaMuokattavuus();

    }
    
    /**
     * Poistaa k‰ytt‰j‰n profiilin
     */
    public void poistaProfiili() {
        lautapeliListaus.poista(pelaaja);
        tallenna();
    }
    
    /**
     * Lis‰‰ valitun pelin tapahtumaan
     */
    public void lisaaPeliTapahtumaan() {
        try {
            
            if (listPelaajanLautapelit.getSelectedObject() == null)
                JOptionPane.showMessageDialog(null, "Valitse lis‰tt‰v‰ peli");
            else {
                lautapeliListaus.lisaa(tapahtuma.getID(), pelaaja.getID(),
                        listPelaajanLautapelit.getSelectedObject().getID());
                naytaTapahtumanPelit();
            }
        } catch (OmaException ex) {
            JOptionPane.showMessageDialog(null, "virhe" + ex);
        }
    }
    
    /**
     * N‰ytt‰‰ tapahtuman lautapelit
     */
    public void naytaTapahtumanPelit() {
        
        tapahtumanLautapelit.clear();
        ArrayList<Integer> pelit = lautapeliListaus
                .annaTapahtumanPelit(listTapahtumat.getSelectedObject().getID());
        for (int peli : pelit) {
            
            try {
                if (lautapeliListaus.annaLautapeli(peli) != null) {
                    tapahtumanLautapelit.add(
                            lautapeliListaus.annaLautapeli(peli).getNimi(),
                            lautapeliListaus.annaLautapeli(peli));
                }
            } catch (OmaException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
        
    }
    
    /**
     * N‰ytt‰‰ kaikki tapahtumat ja lis‰‰ ne listTapahtumiin, eli profiili-ikkunan
     * tapahtumalistaan
     */
    public void naytaTapahtumat() {
        listTapahtumat.clear();
        
        int combonIndeksi = comboBox_tapahtumat.getSelectedIndex();
        int hakuTyyppi = 0;
        
        // combonindeksi: 0--> nimi, 1--> admin
        switch (combonIndeksi) {
        case 0:
            hakuTyyppi = 2;
            break;
        case 1:
            hakuTyyppi = 1;
            break;
        }
        
        String ehto = haeTapahtumista.getText().toLowerCase();
        
        ArrayList<Tapahtuma> tapahtumat = (lautapeliListaus.getTapahtumat(ehto,
                hakuTyyppi));
        
        for (Tapahtuma tapahtuma : tapahtumat)
            listTapahtumat.add(tapahtuma.getNimi(), tapahtuma);
    }
    
    /**
     * Sulkee ohjelman
     */
    public void lopeta() {
        System.exit(0);
        
    }
    
    /**
     * Hallitsee k‰ytt‰j‰n osallistumista tapahtumassa
     */
    public void osallistuminen() {
        
        if (!(onkoTapahtumassa())) {
            try {
                listOsallistujat
                        .add(lautapeliListaus.annaPelaaja(pelaaja.getID())
                                .getNimi(), lautapeliListaus
                                .annaPelaaja(pelaaja.getID()));
                lautapeliListaus.lisaaPelaajaTapahtumaan(tapahtuma.getID(),
                        pelaaja.getID());
            } catch (OmaException ex) {
                JOptionPane.showMessageDialog(null, "virhe" + ex);
            }
            
            listOsallistujat.setSelectedIndex(0);
            
        }
        
        else {
            lautapeliListaus.poistaPelaajaTapahtumasta(tapahtuma.getID(),
                    pelaaja.getID());
            naytaOsallistujat();
            naytaTapahtumanPelit();
            listPelaajanLautapelit.clear();
            
        }
    }
    
    /**
     * Tarkistaa, onko pelaaja jo jossain tapahtumassa.
     * @return tieto, onko pelaaja jo tapahtumassa.
     */
    public boolean onkoTapahtumassa() {
        ArrayList<Integer> pelaajat = lautapeliListaus
                .annaTapahtumanPelaajat(tapahtuma.getID());
        for (int pelaaja : pelaajat)
            if (pelaaja == this.pelaaja.getID())
                return true;
        return false;
    }
    
    /**
     * Asettaa hakukent‰ksi annetun textfieldin
     * @param peliHaku JTextField hakua varten
     */
    public void setHaePeleista(JTextField peliHaku) {
        this.haePeleista = peliHaku;
    }
    
    /**
     * @param listPelit lista, johon lautapelit laitetaan
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setLautapeliLista(AbstractChooser listPelit) {
        this.listPelit = listPelit;
    }
    
    /**
     * @param tiedot tietopalkkien paneli
     */
    public void setPanelTiedot(JPanel tiedot) {
        this.panel_tiedot = tiedot;
        
    }
    
    /**
     * Asettaa profiilin ikkunaan tapahtumat.
     * @param listTapahtumat Tapahtumalista
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setTapahtumaLista(AbstractChooser listTapahtumat) {
        this.listTapahtumat = listTapahtumat;
        
    }
    
    /**
     * Asettaa tapahtumaikkunaan listan, johon laitetaan kaikki osallistujat.
     * @param listOsallistujat Tapahtuma ikkunan osallistuja lista
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setListOsallistujat(AbstractChooser listOsallistujat) {
        this.listOsallistujat = listOsallistujat;
    }
    
    /**
     * Alustaa tapahtumaikkunan
     */
    public void alustaTapahtumaIkkuna() {
        alustaTapahtuma();
    }
    
    /**
     * Asettaa tapahtuma-ikkunaan listan, joka sis‰lt‰‰ tiedot pelaajan lautapeleist‰
     * @param listPelaajanLautapelit lista, joka n‰ytt‰‰ tapahtuma ikkunassa pelaajan lautapelit
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setListPelaajanLautapelit(AbstractChooser listPelaajanLautapelit) {
        this.listPelaajanLautapelit = listPelaajanLautapelit;
    }
    
    /**
     * Asetetaan tapahtumaikkunan tietopalkki
     * @param stringTiedot tapahtumaikkunan taulukko, jossa tiedot ovat
     */
    public void setStringTiedot(StringTable stringTiedot) {
        this.tableTiedot = stringTiedot;
    }
    
    /**
     * Asettaa annetun text kent‰n profiili-ikkunaan nimen kohdalle.
     * @param textNimi Profiilin ikkunan nimi
     */
    public void setTextNimi(JTextField textNimi) {
        this.textNimi = textNimi;
        
    }
    
    /**
     * Lis‰‰ pelaajan j‰rjestelm‰‰n
     * @param nimi uuden pelaajan Nimi
     * @return Palauttaa virheilmoituksen, jos lis‰‰minen ei onnistu. Null, jos lis‰‰minen onnistuu
     */
    public String lisaaPelaaja(String nimi) {
        pelaaja = new Pelaaja(nimi);
        pelaaja.rekisteroi();
        
        try {
            lautapeliListaus.lisaa(pelaaja);
        } catch (OmaException ex) {
            return "virhe";
        }
        
        return null;
    }
    
    /**
     * Asettaa tapahtumanpeli listaksi parametrin‰ tuodun listan
     * @param tapahtumanPelit lista, joka asetetaan tapahtuman pelien listaksi
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setTapahtumanPelit(AbstractChooser tapahtumanPelit) {
        this.tapahtumanLautapelit = tapahtumanPelit;
    }
    
    /**
     * Tapahtumaikkunan yl‰palkin tiedot
     * @param tapahtumanTiedot alue, johon tapahtuman tiedot kirjoitetaan
     */
    public void setTapahtumanTiedot(JPanel tapahtumanTiedot) {
        this.tapahtumanTiedot = tapahtumanTiedot;
    }
    
    /**
     * Poistaa tapahtumalistalta valitun tapahtuman
     */
    public void poistaTapahtuma() {
        if (listTapahtumat.getSelectedIndex() == -1) {
            JOptionPane
                    .showMessageDialog(null, "Valitse poistettava tapahtuma");
            return;
        }
        
        if (!(listTapahtumat.getSelectedObject().anna(1).equals(pelaaja.getID()
                + "")))
            JOptionPane.showMessageDialog(null,
                    "Et ole kyseisen tapahtuman admin");
        
        else {
            lautapeliListaus.poista(listTapahtumat.getSelectedObject());
            try {
                naytaProfiilinTapahtumat();
            } catch (OmaException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            naytaTapahtumat();
        }
    }
    
    /**
     * Poistaa tapahtumasta valitun pelin, jos itse on tuomassa kyseist‰ peli‰ tapahtumaan
     */
    public void poistaPeliTapahtumasta() {
        
        Lautapeli poistettava = tapahtumanLautapelit.getSelectedObject();
        if (poistettava == null)
            return;
        
        lautapeliListaus.poistaPeliTapahtumasta(listTapahtumat
                .getSelectedObject().getID(), pelaaja.getID(), poistettava
                .getID());
        
        naytaTapahtumanPelit();
                
    }
    
    /**
     * Asettaa editPaneleille muokattavuuden, jos pelaajalla on pelej‰
     */
    private void asetaMuokattavuus() {
        for (int i = 0; i < tietoPalkit.length; i++) {
            EditPanel tietoPalkki = tietoPalkit[i];
            if (lautapeliListaus.annaPelaajanPelit(pelaaja.getID()).size() <= 0)
                tietoPalkki.getEdit().setEnabled(false);
        }
        
    }

    /**
     * Lukee ohjelman tarvitsemat tiedostot, jotka toimivat tietokantana
     */
    public void lueTiedostot() {
        lautapeliListaus.lueTiedostot();
    }
    
    /**
     * Antaa kaikkien pelaajien nimet listassa
     * @return kaikkien pelaajien nimet listassa
     */
    public ArrayList<String> annaPelaajat() {
        return lautapeliListaus.annaPelaajat();
    }
    
    /**
     * Asettaa comboboxit, joista voidaan tarkastaa, mink‰ suhteen listat j‰rjestet‰‰n
     * @param comboBox_lautapelit lautapelien combobox.
     * @param comboBox_tapahtumat tapahtumien combobox.
     */
    public void setComboBoxes(AbstractChooser<String> comboBox_lautapelit,
            AbstractChooser<String> comboBox_tapahtumat) {
        this.comboBox_lautapelit = comboBox_lautapelit;
        this.comboBox_tapahtumat = comboBox_tapahtumat;
    }
    
    /**
     * Asettaa hakupalkkien tekstit
     * @param txtHaePeleist pelien haun teksti kentt‰
     * @param haeTapahtumista tapahtumien haun teksti kentt‰
     */
    public void setHakupalkit(JTextField txtHaePeleist,
            JTextField haeTapahtumista) {
        this.haePeleista = txtHaePeleist;
        this.haeTapahtumista = haeTapahtumista;
    }
    
    /**
     * Asetetaan tapahtumaikkunan hakupalkit
     * @param txtHaeOsallistujista haku osallistujista
     * @param txtHaePelaajanPeleista haku Pelaajan peleist‰
     */
    public void setHaut(JTextField txtHaeOsallistujista,
            JTextField txtHaePelaajanPeleista) {
        this.haeOsallistujista = txtHaeOsallistujista;
        this.haePelaajanPeleista = txtHaePelaajanPeleista;
        
    }
    
    /**
     * Asettaa tapahtumaikkunan pelien comboboksin
     * @param comboTapahtumanPeli Tapahtuman combobox
     */
    public void setTapahtumanCombo(AbstractChooser<String> comboTapahtumanPeli) {
        this.tapahtumanCombo = comboTapahtumanPeli;
        
    }
}
