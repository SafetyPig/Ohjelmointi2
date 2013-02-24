package ikkunatGUI;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JTextField;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Dialogi-ikkuna, jolla voi lisätä pelin
 * @author Hannu Viinikainen
 * @version 24.01.2012
 *
 */
public class PelinLisaaminen extends JDialog {
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNimi;
    
    /**
     * Launch the application.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PelinLisaaminen frame = new PelinLisaaminen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Palauttaa lisättävän pelin nimen
     * @return luotavan käyttäjän nimi. Asettaa nimen eteen new*, jotta saadaan
     * käsiteltyä erikseen kokonaan uuden pelin lisääminen sekä pelin, joka on jo tietokannassa
     * lisääminen
     */
    public static String uusiPeli() {
        String nimi = "";
        PelinLisaaminen dialog = new PelinLisaaminen();
        dialog.setVisible(true);
        nimi = dialog.getTulos();
        return nimi;
        
    }
    
    private String tulos = "";
    
    /**
     * Palauttaa dialogin saaman tuloksen
     * @return dialogin tulos
     */
    private String getTulos() {
        return tulos;
    }
    
    /**
     * Palauttaa textFieldNimen sisällön
     * @return textFieldNimen sisältö
     */
    private String getTextFieldNimi() {
        return textFieldNimi.getText();
    }
    
    /**
     * Asettaa dialogille tuloksen
     * @param tulos dialogin tulos
     */
    private void setTulos(String tulos) {
        this.tulos = tulos;
    }
    
    
    /**
     * Create the frame.
     */
    public PelinLisaaminen() {
        setModalityType(ModalityType.APPLICATION_MODAL); 
        setTitle("Pelin lis\u00E4\u00E4minen");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 311, 158);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel_nappulat = new JPanel();
        contentPane.add(panel_nappulat, BorderLayout.SOUTH);
        
        JSplitPane splitPane = new JSplitPane();
        panel_nappulat.add(splitPane);
        
        JButton btnNewButton = new JButton("   Lis\u00E4\u00E4    ");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nimi = getTextFieldNimi();
                if(nimi.equals(""))
                    JOptionPane.showMessageDialog(null, "Anna uuden pelin nimi");
                
                else{
                setTulos("new*" + getTextFieldNimi());
                setVisible(false);
                }
            }
        });
        splitPane.setLeftComponent(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("   Palaa    ");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                setTulos("");
            }
        });
        splitPane.setRightComponent(btnNewButton_1);
        
        JPanel panel_otsikko = new JPanel();
        panel_otsikko.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        contentPane.add(panel_otsikko, BorderLayout.NORTH);
        
        JLabel lblLisPeli = new JLabel("Lis\u00E4\u00E4 peli");
        lblLisPeli.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_otsikko.add(lblLisPeli);
        
        JPanel panel_peli = new JPanel();
        contentPane.add(panel_peli, BorderLayout.CENTER);
        
        JLabel lblPelinNimi = new JLabel("Pelin nimi");
        lblPelinNimi.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_peli.add(lblPelinNimi);
        
        textFieldNimi = new JTextField();
        panel_peli.add(textFieldNimi);
        textFieldNimi.setColumns(10);
       
    }
    
}
