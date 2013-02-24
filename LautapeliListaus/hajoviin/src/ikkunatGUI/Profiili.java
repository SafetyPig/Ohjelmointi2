package ikkunatGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import fi.jyu.mit.gui.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Point;
import javax.swing.AbstractAction;
import javax.swing.Action;

import java.util.ArrayList;

import listausSwing.ListausSwing;
import java.awt.Dimension;

/**
 * Profiili ikkunan luonti. Ennen luomista kysyy k‰ytt‰j‰n nimen, joka ohjelmaa k‰ytt‰‰.
 * Profiili toimii koko ohjelman ytimen‰.
 * @author Hannu Viinikainen
 * @version 7.4.2012
 *
 */
public class Profiili extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private final ListausSwing listausSwing = new ListausSwing();
    private JTextField textNimi = new JTextField();
    private JTextField txtHaePeleist = new JTextField();
    private JTextField haeTapahtumista = new JTextField();
    
    final AbstractChooser<String> listPelit = new ListChooser();
    final AbstractChooser<String> listTapahtumat = new ListChooser();
    
    private EditPanel editPanel_tuuri = new EditPanel();
    private EditPanel editPanel_kesto = new EditPanel();
    private EditPanel editPanel_maara = new EditPanel();
    private EditPanel editPanel_nimi = new EditPanel();
    private EditPanel editPanel_genre = new EditPanel();
    private EditPanel editPanel_vuosi = new EditPanel();
    private EditPanel editPanel_ika = new EditPanel();
    
    private ComboBoxChooser comboBox_lautapelit = new ComboBoxChooser();
    private ComboBoxChooser comboBox_tapahtumat = new ComboBoxChooser();
    private JPanel panelTiedot = new JPanel();
    private JPanel panel_lista = new JPanel();
    private JPanel panel_haku = new JPanel();
    private JPanel panel_lautapelit = new JPanel();
    private JSplitPane splitPane_lautapelit = new JSplitPane();
    private JPanel panel_henkilo = new JPanel();
    
    JMenuItem mntmTallenna = new JMenuItem("Tallenna");
    
    private final Action tallenna = new Tallenna();
    private final Action kirjauduUlos = new KirjauduUlos();
    private final Action lisaaPeli = new LisaaPeli();
    private final Action lisaaTapahtuma = new LisaaTapahtuma();
    private final Action poistaPeli = new PoistaPeli();
    private final Action poistaProfiili = new PoistaProfiili();
    private final Action lopeta = new Lopeta();
    private final Action action = new poistaTapahtuma();
    
    /**
     * Launch the application.
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Profiili frame = new Profiili();
                    frame.lueTiedosto();
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Luo profiili ikkunan
     */
    public Profiili() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                listausSwing.tallenna();
                System.exit(0);
            }
        });
        listausSwing.setLautapeliLista(listPelit);
        listausSwing.setTapahtumaLista(listTapahtumat);
        listausSwing.setPanelTiedot(panelTiedot);
        listausSwing.setTextNimi(textNimi);
        listausSwing.setComboBoxes(comboBox_lautapelit, comboBox_tapahtumat);
        listausSwing.setHakupalkit(txtHaePeleist, haeTapahtumista);
        
        setTitle("Profiili");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 626, 408);
        
        getContentPane().add(panel_lautapelit, BorderLayout.WEST);
        panel_lautapelit.setLayout(new BoxLayout(panel_lautapelit,
                BoxLayout.X_AXIS));
        
        splitPane_lautapelit.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel_lautapelit.add(splitPane_lautapelit);
        
        splitPane_lautapelit.setLeftComponent(panel_haku);
        panel_haku.setLayout(new BoxLayout(panel_haku, BoxLayout.Y_AXIS));
        
        JLabel lblNewLabel = new JLabel("Lautapelit     ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_haku.add(lblNewLabel);
        comboBox_lautapelit.setPreferredSize(new Dimension(28, 18));
        comboBox_lautapelit.setSelectedIndex(0);
        comboBox_lautapelit.setKohteet(new String[] { "Nimi", "Genre" });
        
        comboBox_lautapelit.setFont(new Font("Tahoma", Font.PLAIN, 10));
        comboBox_lautapelit.setMinimumSize(new Dimension(28, 15));
        panel_haku.add(comboBox_lautapelit);
        
        listausSwing.setHaePeleista(txtHaePeleist);
        panel_haku.add(txtHaePeleist);
        txtHaePeleist.setFont(new Font("Tahoma", Font.ITALIC, 11));
        
        txtHaePeleist.setColumns(10);
        
        splitPane_lautapelit.setRightComponent(panel_lista);
        panel_lista.setLayout(new BorderLayout(0, 0));
        
        JButton btnNewButton = new JButton("Lis\u00E4\u00E4 peli");
        btnNewButton.setAction(lisaaPeli);
        panel_lista.add(btnNewButton, BorderLayout.SOUTH);
        listPelit.setMaximumSize(new Dimension(135, 130));
        listPelit.setPreferredSize(new Dimension(135, 130));
        
        listPelit.setKohteet(new String[] { "Caylus", "Kimble" });
        panel_lista.add(listPelit, BorderLayout.CENTER);
        
        getContentPane().add(panel_henkilo, BorderLayout.NORTH);
        panel_henkilo.setLayout(new BoxLayout(panel_henkilo, BoxLayout.X_AXIS));
        
        JSplitPane splitPane_menu = new JSplitPane();
        splitPane_menu.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel_henkilo.add(splitPane_menu);
        
        JMenuBar menuBar = new JMenuBar();
        splitPane_menu.setLeftComponent(menuBar);
        
        JMenu menu = new JMenu("");
        menuBar.add(menu);
        
        JMenu mnTiedosto = new JMenu("Tiedosto");
        menuBar.add(mnTiedosto);
        
        mntmTallenna.setAction(tallenna);
        mnTiedosto.add(mntmTallenna);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Kirjaudu ulos");
        mntmNewMenuItem.setAction(kirjauduUlos);
        mnTiedosto.add(mntmNewMenuItem);
        
        JMenuItem mntmLopeta = new JMenuItem("Lopeta");
        mntmLopeta.setAction(lopeta);
        mnTiedosto.add(mntmLopeta);
        
        JMenu mnMuokkaa = new JMenu("Muokkaa");
        menuBar.add(mnMuokkaa);
        
        JMenuItem mntmUusiPeli = new JMenuItem("Uusi peli");
        mntmUusiPeli.setAction(lisaaPeli);
        mnMuokkaa.add(mntmUusiPeli);
        
        JMenuItem mntmLisTapahtuma = new JMenuItem("Lis\u00E4\u00E4 tapahtuma");
        mntmLisTapahtuma.setAction(lisaaTapahtuma);
        mnMuokkaa.add(mntmLisTapahtuma);
        
        JMenuItem mntmPoistaPeli = new JMenuItem("Poista peli");
        mntmPoistaPeli.setAction(poistaPeli);
        mnMuokkaa.add(mntmPoistaPeli);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
        mntmNewMenuItem_1.setAction(action);
        mnMuokkaa.add(mntmNewMenuItem_1);
        
        JMenuItem mntmPoistaProfiili = new JMenuItem("Poista profiili");
        mntmPoistaProfiili.setAction(poistaProfiili);
        mnMuokkaa.add(mntmPoistaProfiili);
        
        textNimi.setText("");
        textNimi.setFont(new Font("Tahoma", Font.PLAIN, 25));
        textNimi.setEditable(false);
        textNimi.setHorizontalAlignment(SwingConstants.CENTER);
        splitPane_menu.setRightComponent(textNimi);
        textNimi.setColumns(10);
        
        JPanel panel_tapahtuma = new JPanel();
        getContentPane().add(panel_tapahtuma, BorderLayout.EAST);
        panel_tapahtuma.setLayout(new BorderLayout(0, 0));
        
        JSplitPane splitPane_tapahtumat = new JSplitPane();
        splitPane_tapahtumat.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel_tapahtuma.add(splitPane_tapahtumat);
        
        JPanel panel = new JPanel();
        splitPane_tapahtumat.setLeftComponent(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel lblTapahtumat = new JLabel("Tapahtumat");
        panel.add(lblTapahtumat);
        lblTapahtumat.setHorizontalAlignment(SwingConstants.CENTER);
        lblTapahtumat.setFont(new Font("Tahoma", Font.BOLD, 13));
        comboBox_tapahtumat.setPreferredSize(new Dimension(28, 18));
        comboBox_tapahtumat.setSelectedIndex(0);
        comboBox_tapahtumat.setKohteet(new String[] { "Nimi", "Admin" });
        
        comboBox_tapahtumat.setFont(new Font("Tahoma", Font.PLAIN, 10));
        panel.add(comboBox_tapahtumat);
        
        panel.add(haeTapahtumista);
        haeTapahtumista.setFont(new Font("Tahoma", Font.ITALIC, 11));
        haeTapahtumista.setColumns(10);
        
        JPanel panel_1 = new JPanel();
        splitPane_tapahtumat.setRightComponent(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        listTapahtumat.setPreferredSize(new Dimension(135, 130));
        listTapahtumat.setMaximumSize(new Dimension(135, 130));
        
        listTapahtumat.setKohteet(new String[] { "Peli-ilta" });
        panel_1.add(listTapahtumat, BorderLayout.CENTER);
        
        JButton btnNewButton_1 = new JButton("Siirry tapahtumaan");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listausSwing.siirryTapahtumaan(
                        listTapahtumat.getSelectedText(), listausSwing);
            }
        });
        btnNewButton_1.setLocation(new Point(0, 50));
        listTapahtumat.add(btnNewButton_1, BorderLayout.SOUTH);
        
        JPanel panel_tiedotJaButton = new JPanel();
        getContentPane().add(panel_tiedotJaButton, BorderLayout.CENTER);
        panel_tiedotJaButton.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_1 = new JLabel("Tiedot pelist\u00E4");
        lblNewLabel_1.setPreferredSize(new Dimension(250, 14));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_tiedotJaButton.add(lblNewLabel_1, BorderLayout.NORTH);
        panelTiedot.setPreferredSize(new Dimension(150, 10));
        
        panel_tiedotJaButton.add(panelTiedot, BorderLayout.CENTER);
        panelTiedot.setLayout(new BoxLayout(panelTiedot, BoxLayout.Y_AXIS));
        
        editPanel_nimi.setText("Caylus");
        panelTiedot.add(editPanel_nimi);
        
        editPanel_genre.setText("Strategia");
        editPanel_genre.setCaption("Genre");
        panelTiedot.add(editPanel_genre);
        
        editPanel_vuosi.setText("2005");
        editPanel_vuosi.setCaption("Julkaisuvuosi");
        panelTiedot.add(editPanel_vuosi);
        
        editPanel_maara.setText("2-5");
        editPanel_maara.setCaption("Pelaajam\u00E4\u00E4r\u00E4");
        panelTiedot.add(editPanel_maara);
        
        editPanel_kesto.setText("60-150");
        editPanel_kesto.setCaption("Pelin kesto(min)");
        panelTiedot.add(editPanel_kesto);
        
        editPanel_tuuri.setText("Olematon");
        editPanel_tuuri.setCaption("Tuuri faktori");
        panelTiedot.add(editPanel_tuuri);
        
        editPanel_ika.setText("12+");
        editPanel_ika.setCaption("Ik\u00E4suositus");
        panelTiedot.add(editPanel_ika);
        
        JPanel panel_nappula = new JPanel();
        panel_tiedotJaButton.add(panel_nappula, BorderLayout.SOUTH);
        
        JButton btnTallennaTiedot = new JButton("Tallenna Tiedot");
        btnTallennaTiedot.setAction(tallenna);
        panel_nappula.add(btnTallennaTiedot);
        
    }
    
    private class Tallenna extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public Tallenna() {
            putValue(NAME, "Tallenna");
            putValue(SHORT_DESCRIPTION, "Tallentaa tiedot");
        }
        
        public void actionPerformed(ActionEvent e) {
            listausSwing.tallenna();
        }
    }
    
    private class KirjauduUlos extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public KirjauduUlos() {
            putValue(NAME, "Kirjaudu ulos");
            putValue(SHORT_DESCRIPTION, "Kirjaudu ohjelmtasta pois");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();           
            listausSwing.tallenna();
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Profiili frame = new Profiili();
                        frame.lueTiedosto();
                        frame.setVisible(true);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            
        }
        
    }
    
    private class LisaaPeli extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public LisaaPeli() {
            putValue(NAME, "Lis‰‰ peli...");
            putValue(SHORT_DESCRIPTION,
                    "Avaa ikkunan, jonka avulla k‰ytt‰j‰ voi lis‰t‰ pelin");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            listausSwing.lisaaPeli();
        }
    }
    
    private class LisaaTapahtuma extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public LisaaTapahtuma() {
            putValue(NAME, "Luo tapahtuma...");
            putValue(SHORT_DESCRIPTION, "Avaa tapahtuman lis‰‰mis ikkunan");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            listausSwing.luoTapahtuma();
        }
    }
    
    private class PoistaPeli extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public PoistaPeli() {
            putValue(NAME, "Poista peli");
            putValue(SHORT_DESCRIPTION, "Poistaa valitun pelin");
        }
        
        public void actionPerformed(ActionEvent e) {
            listausSwing.poistaPeli();
        }
    }
    
    private class PoistaProfiili extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public PoistaProfiili() {
            putValue(NAME, "Poista profiili");
            putValue(SHORT_DESCRIPTION, "Poistaa profiilisi");
        }
        
        public void actionPerformed(ActionEvent e) {
            dispose();
            listausSwing.poistaProfiili();
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Profiili frame = new Profiili();
                        frame.lueTiedosto();
                        frame.setVisible(true);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            
        }
    }
    
    private class Lopeta extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public Lopeta() {
            putValue(NAME, "Lopeta");
            putValue(SHORT_DESCRIPTION, "Lopettaa ohjelman ja tallentaa tiedot");
        }
        
        public void actionPerformed(ActionEvent e) {
            listausSwing.lopeta();
        }
    }
    
    /**
     * Lukee kaikki ohjelman tarvitsemat tiedostot ja alustaa listausSwingin niiden avulla.
     */
    public void lueTiedosto() {
        listausSwing.lueTiedostot();
        ArrayList<String> pelaajat = listausSwing.annaPelaajat();
        listausSwing.kysyKayttaja(pelaajat);
    }
    
    private class poistaTapahtuma extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public poistaTapahtuma() {
            putValue(NAME, "Poista tapahtuma");
            putValue(SHORT_DESCRIPTION, "Poistaa listalta valitun tapahtuman");
        }
        
        public void actionPerformed(ActionEvent e) {
            listausSwing.poistaTapahtuma();
        }
    }
}