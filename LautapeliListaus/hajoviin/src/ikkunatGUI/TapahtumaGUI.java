package ikkunatGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;

import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import fi.jyu.mit.gui.*;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import listausSwing.ListausSwing;
import java.awt.Dimension;

/**
 * Luo tapahtuma ikkunan. Tapahtuma ikkunassa on mahdollista osallistua tapahtumiin ja lisätä
 * pelejä joita, aikoo tuoda
 * @author Hannu
 * @version 08.04.2012
 *
 */
public class TapahtumaGUI extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtHaeOsallistujista = new JTextField();
    private JTextField txtHaePelaajanPeleista = new JTextField();
    private final Action lisaaPeliTapahtumaan = new LisaaPeliTapahtumaan();
    private final Action osallistuminen = new Osallistuminen();
    private ListChooser listOsallistujat = new ListChooser();
    private ListChooser listPelaajanLautapelit = new ListChooser();
    private ListChooser tapahtumanPelit = new ListChooser();
    StringTable stringTiedot = new StringTable();
    ComboBoxChooser comboTapahtumanPeli = new ComboBoxChooser();
    
    private JPanel panel_tapahtumantiedot = new JPanel();
    private final Action poistaPeliTapahtumasta = new PoistaPeliTapahtumasta();
    private ListausSwing listausSwing;
    private final Action palaaProfiiliin = new PalaaProfiiliin();
    
    /**
     * Launch the application.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TapahtumaGUI frame = new TapahtumaGUI(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     * @param listausSwing mitä listausta käyttää
     */
    public TapahtumaGUI(final ListausSwing listausSwing) {
        this.listausSwing = listausSwing;
        
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        
        listausSwing.setListOsallistujat(listOsallistujat);
        listausSwing.setListPelaajanLautapelit(listPelaajanLautapelit);
        listausSwing.setStringTiedot(stringTiedot);
        listausSwing.setTapahtumanPelit(tapahtumanPelit);
        listausSwing.setHaut(txtHaeOsallistujista, txtHaePelaajanPeleista);
        listausSwing.setTapahtumanCombo(comboTapahtumanPeli);
        
        listausSwing.setTapahtumanTiedot(panel_tapahtumantiedot);
        
        setTitle("Tapahtuma");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 642, 407);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnTiedosto = new JMenu("Tiedosto");
        menuBar.add(mnTiedosto);
        
        JMenuItem mntmPalaaProfiiliin = new JMenuItem("Palaa profiiliin");
        mntmPalaaProfiiliin.setAction(palaaProfiiliin);
        mnTiedosto.add(mntmPalaaProfiiliin);
        
        JMenu mnMuokkaa = new JMenu("Muokkaa");
        menuBar.add(mnMuokkaa);
        
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
        mntmNewMenuItem_3.setAction(lisaaPeliTapahtumaan);
        mnMuokkaa.add(mntmNewMenuItem_3);
        
        JMenuItem mntmPoistaPeliTapahtumasta = new JMenuItem(
                "Poista peli tapahtumasta");
        mntmPoistaPeliTapahtumasta.setAction(poistaPeliTapahtumasta);
        mnMuokkaa.add(mntmPoistaPeliTapahtumasta);
        
        JMenuItem mntmNewMenuItem_4 = new JMenuItem("New menu item");
        mntmNewMenuItem_4.setAction(osallistuminen);
        mnMuokkaa.add(mntmNewMenuItem_4);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_nappulat = new JPanel();
        contentPane.add(panel_nappulat, BorderLayout.SOUTH);
        panel_nappulat.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_nappula1 = new JPanel();
        panel_nappulat.add(panel_nappula1, BorderLayout.WEST);
        
        JToggleButton Osallistuminen = new JToggleButton("Osallistuminen");
        Osallistuminen.setAction(osallistuminen);
        panel_nappula1.add(Osallistuminen);
        
        JButton btnLisPeliTapahtumaan = new JButton(
                "Lis\u00E4\u00E4 peli tapahtumaan");
        btnLisPeliTapahtumaan.setAction(lisaaPeliTapahtumaan);
        
        panel_nappula1.add(btnLisPeliTapahtumaan);
        
        JPanel panel_nappulaProfiiliin = new JPanel();
        panel_nappulat.add(panel_nappulaProfiiliin, BorderLayout.EAST);
        
        JButton btnPalaaProfiiliin = new JButton("Palaa profiiliin");
        btnPalaaProfiiliin.setAction(palaaProfiiliin);
        btnPalaaProfiiliin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        panel_nappulaProfiiliin.add(btnPalaaProfiiliin);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
                null));
        contentPane.add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
        
        panel_1.add(panel_tapahtumantiedot);
        panel_tapahtumantiedot.setLayout(new BoxLayout(panel_tapahtumantiedot,
                BoxLayout.X_AXIS));
        
        JLabel label_tapahtumanNimi = new JLabel("Peli-ilta");
        label_tapahtumanNimi.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_tapahtumantiedot.add(label_tapahtumanNimi);
        
        JLabel label = new JLabel("      ");
        panel_tapahtumantiedot.add(label);
        
        JLabel label_paivamaara = new JLabel("13.05.2012");
        label_paivamaara.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_tapahtumantiedot.add(label_paivamaara);
        
        JLabel lblKlo = new JLabel("       klo:");
        lblKlo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblKlo.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_tapahtumantiedot.add(lblKlo);
        
        JLabel label_alkamisaika = new JLabel("17.00");
        label_alkamisaika.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_tapahtumantiedot.add(label_alkamisaika);
        
        JLabel label_2 = new JLabel("      ");
        panel_tapahtumantiedot.add(label_2);
        
        JLabel label_Paikka = new JLabel("Pelaajankatu 26");
        label_Paikka.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_tapahtumantiedot.add(label_Paikka);
        
        JPanel panel_osallistujat = new JPanel();
        contentPane.add(panel_osallistujat, BorderLayout.WEST);
        panel_osallistujat.setLayout(new BorderLayout(0, 0));
        
        JSplitPane splitPane = new JSplitPane();
        panel_osallistujat.add(splitPane, BorderLayout.CENTER);
        
        JPanel panel_osallistujalista = new JPanel();
        splitPane.setLeftComponent(panel_osallistujalista);
        panel_osallistujalista.setLayout(new BorderLayout(0, 0));
        
        JSplitPane splitPane_1 = new JSplitPane();
        splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel_osallistujalista.add(splitPane_1, BorderLayout.NORTH);
        
        JLabel lblOsallistujat = new JLabel("   Osallistujat   ");
        lblOsallistujat.setHorizontalAlignment(SwingConstants.CENTER);
        lblOsallistujat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        splitPane_1.setLeftComponent(lblOsallistujat);
        
        txtHaeOsallistujista.setFont(new Font("Tahoma", Font.ITALIC, 11));
        splitPane_1.setRightComponent(txtHaeOsallistujista);
        txtHaeOsallistujista.setColumns(10);
        listOsallistujat.setMaximumSize(new Dimension(100, 125));
        listOsallistujat.setPreferredSize(new Dimension(100, 130));
        
        listOsallistujat
                .setKohteet(new String[] { "Pate Pelaaja", "Sorsa Late" });
        panel_osallistujalista.add(listOsallistujat, BorderLayout.CENTER);
        
        JPanel panel_lautapelit = new JPanel();
        splitPane.setRightComponent(panel_lautapelit);
        panel_lautapelit.setLayout(new BorderLayout(0, 0));
        
        JSplitPane splitPane_2 = new JSplitPane();
        splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel_lautapelit.add(splitPane_2, BorderLayout.NORTH);
        
        JLabel lblLautapelit = new JLabel(" Lautapelit   ");
        lblLautapelit.setHorizontalAlignment(SwingConstants.CENTER);
        lblLautapelit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        splitPane_2.setLeftComponent(lblLautapelit);
        
        JSplitPane splitPane_3 = new JSplitPane();
        splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane_2.setRightComponent(splitPane_3);
        
        txtHaePelaajanPeleista.setFont(new Font("Tahoma", Font.ITALIC, 11));
        splitPane_3.setRightComponent(txtHaePelaajanPeleista);
        txtHaePelaajanPeleista.setColumns(10);
        
        comboTapahtumanPeli.setSelectedIndex(0);
        comboTapahtumanPeli.setKohteet(new String[] { "Nimi", "Genre" });
        splitPane_3.setLeftComponent(comboTapahtumanPeli);
        listPelaajanLautapelit.setMaximumSize(new Dimension(125, 130));
        listPelaajanLautapelit.setPreferredSize(new Dimension(125, 130));
        
        listPelaajanLautapelit.setKohteet(new String[] { "Caylus", "Kimble" });
        panel_lautapelit.add(listPelaajanLautapelit, BorderLayout.CENTER);
        
        JPanel panel_tilaisuudenPelit = new JPanel();
        contentPane.add(panel_tilaisuudenPelit, BorderLayout.EAST);
        panel_tilaisuudenPelit.setLayout(new BorderLayout(0, 0));
        
        JSplitPane splitPane_4 = new JSplitPane();
        splitPane_4.setPreferredSize(new Dimension(125, 25));
        splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel_tilaisuudenPelit.add(splitPane_4);
        
        JLabel lblTilaisuudenPelit = new JLabel("Tapahtuman pelit");
        lblTilaisuudenPelit.setHorizontalAlignment(SwingConstants.CENTER);
        lblTilaisuudenPelit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        splitPane_4.setLeftComponent(lblTilaisuudenPelit);
        tapahtumanPelit.setMaximumSize(new Dimension(125, 120));
        tapahtumanPelit.setPreferredSize(new Dimension(125, 130));
        
        tapahtumanPelit.setKohteet(new String[] { "Caylus" });
        splitPane_4.setRightComponent(tapahtumanPelit);
        
        JPanel panel_tiedotPelaista = new JPanel();
        contentPane.add(panel_tiedotPelaista, BorderLayout.CENTER);
        panel_tiedotPelaista.setLayout(new BorderLayout(0, 0));
        
        JSplitPane splitPane_5 = new JSplitPane();
        splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel_tiedotPelaista.add(splitPane_5, BorderLayout.CENTER);
        
        JLabel lblTiedotLautapelist = new JLabel("Tiedot lautapelist\u00E4");
        lblTiedotLautapelist.setHorizontalAlignment(SwingConstants.CENTER);
        lblTiedotLautapelist.setFont(new Font("Tahoma", Font.PLAIN, 15));
        splitPane_5.setLeftComponent(lblTiedotLautapelist);
        
        stringTiedot.getTable().setModel(
                new DefaultTableModel(new Object[][] { { "Nimi", "Caylus" },
                        { "Genre", "Strategia" }, { "Julkaisuvuosi", "2005" },
                        { "Pelaajam\u00E4\u00E4r\u00E4", "2-5" },
                        { "Pelin kesto(min)", "60-150" },
                        { "Tuuri faktori", "Olematon" },
                        { "Ik\u00E4suositus", "12+" }, },
                        new String[] { "", "" }));
        stringTiedot.getTable().getColumnModel().getColumn(0)
                .setPreferredWidth(82);
        stringTiedot.getTable().getColumnModel().getColumn(1)
                .setPreferredWidth(86);
        stringTiedot.getTable().setEnabled(false);
        splitPane_5.setRightComponent(stringTiedot);
    }
    
    private class LisaaPeliTapahtumaan extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public LisaaPeliTapahtumaan() {
            putValue(NAME, "Lisaa peli tapahtumaan");
            putValue(SHORT_DESCRIPTION, "Lisää valitun pelin tapahtumaan");
        }
        
        public void actionPerformed(ActionEvent e) {
            listausSwing.lisaaPeliTapahtumaan();
        }
    }
    
    private class Osallistuminen extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public Osallistuminen() {
            putValue(NAME, "Osallistuminen");
            putValue(SHORT_DESCRIPTION, "Tapahtumaan osallistuminen");
        }
        
        public void actionPerformed(ActionEvent e) {
            listausSwing.osallistuminen();
        }
    }
    
    private class PoistaPeliTapahtumasta extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public PoistaPeliTapahtumasta() {
            putValue(NAME, "Poista peli tapahtumasta");
            putValue(SHORT_DESCRIPTION,
                    "Poistaa listalta valitun pelin tapahtumasta");
        }
        
        public void actionPerformed(ActionEvent e) {
            listausSwing.poistaPeliTapahtumasta();
        }
    }
    
    private class PalaaProfiiliin extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public PalaaProfiiliin() {
            putValue(NAME, "Palaa Profiiliin");
            putValue(SHORT_DESCRIPTION, "Palaa profiili ikkunaan");
        }
        
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
