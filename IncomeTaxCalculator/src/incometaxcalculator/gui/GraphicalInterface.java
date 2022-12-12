package incometaxcalculator.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class GraphicalInterface extends JFrame{

  
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private TaxpayerManager taxpayerManager = new TaxpayerManager();
  private JTextField txtTaxRegistrationNumber;
  JButton btnLoadTaxpayer;
  
  public GraphicalInterface() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 600, 600);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(245, 245, 245));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
;
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    

    txtTaxRegistrationNumber = new JTextField();
    txtTaxRegistrationNumber.setEditable(false);
    txtTaxRegistrationNumber.setBackground(new Color(255, 255, 240));
    txtTaxRegistrationNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
    txtTaxRegistrationNumber.setText("Tax Registration Number:");
    txtTaxRegistrationNumber.setBounds(90, 80, 400, 20);
    contentPane.add(txtTaxRegistrationNumber);
    txtTaxRegistrationNumber.setColumns(15);
    

    DefaultListModel<String> taxRegisterNumberModel = new DefaultListModel<String>();
    JList<String> taxRegisterNumberList = new JList<String>(taxRegisterNumberModel);
    taxRegisterNumberList.setBackground(new Color(255, 255, 215));
    taxRegisterNumberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    taxRegisterNumberList.setSelectedIndex(0);
    taxRegisterNumberList.setVisibleRowCount(3);

    JScrollPane taxRegisterNumberListScrollPane = new JScrollPane(taxRegisterNumberList);
    taxRegisterNumberListScrollPane.setSize(400, 400);
    taxRegisterNumberListScrollPane.setLocation(90, 100);
    contentPane.add(taxRegisterNumberListScrollPane);
    btnLoadTaxpayer = new JButton("Load Taxpayer");
    btnLoadTaxpayer.addActionListener(new ActionListener() {
      
      @Override
    public void actionPerformed(ActionEvent e) 
    {
      if(e.getSource()==btnLoadTaxpayer)
      { 
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int response = fileChooser.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          String name =file.getName();
          
          try {
            String taxRegistrationNumber= name.substring(0,name.length()-9);
            int trn  = Integer.parseInt(taxRegistrationNumber);
            if(taxpayerManager.containsTaxpayer(trn)) {
              JOptionPane.showMessageDialog(null, "This taxpayer is already loaded.");
            }
            
            else if(file.getName().contains("_INFO.txt") || file.getName().contains("_INFO.xml")) {
              taxpayerManager.loadTaxpayer(file.getName());
              taxRegisterNumberModel.addElement(taxRegistrationNumber);
              
              }
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null,
                "The tax registration number must have only digits.");
          } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "The file doesn't exists.");
          } catch (WrongFileFormatException e1) {
            JOptionPane.showMessageDialog(null, "Please check your file format and try again.");
          } catch (WrongFileEndingException e1) {
            JOptionPane.showMessageDialog(null, "Please check your file ending and try again.");
          } catch (WrongTaxpayerStatusException e1) {
            JOptionPane.showMessageDialog(null, "Please check taxpayer's status and try again.");
          } catch (WrongReceiptKindException e1) {
            JOptionPane.showMessageDialog(null, "Please check receipts kind and try again.");
          } catch (WrongReceiptDateException e1) {
            JOptionPane.showMessageDialog(null,
                "Please make sure your date is " + "DD/MM/YYYY and try again.");
            }
            
            }
        }
      }
 });

  btnLoadTaxpayer.setBounds(0, 0, 200, 40);
  contentPane.add(btnLoadTaxpayer);
  
  JButton btnSelectTaxpayer = new JButton("Select Taxpayer");
  btnSelectTaxpayer.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==btnSelectTaxpayer) {
        int index = taxRegisterNumberList.getSelectedIndex();
        if(index == -1)
        {
          JOptionPane.showMessageDialog(null,"Please select a taxpayer.");
        }
        else {
          String trn = (String) taxRegisterNumberList.getSelectedValue();
          int taxRegistrationNumber = Integer.parseInt(trn);
          if (taxpayerManager.containsTaxpayer(taxRegistrationNumber)) {
            TaxpayerData taxpayerData = new TaxpayerData(taxRegistrationNumber,taxpayerManager);
            taxpayerData.setVisible(true);
          }
          
        }
      }
      
    }
    
  });
  btnSelectTaxpayer.setBounds(200, 0, 199, 40);
  contentPane.add(btnSelectTaxpayer);
  
  

  JButton btnDeleteTaxpayer = new JButton("Delete Taxpayer");
  btnDeleteTaxpayer.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==btnDeleteTaxpayer) {
        int index = taxRegisterNumberList.getSelectedIndex();
        if(index == -1)
        {
          JOptionPane.showMessageDialog(null,"Please select a taxpayer.");
        }
        else {
          String trn = (String) taxRegisterNumberList.getSelectedValue();
          int taxRegistrationNumber = Integer.parseInt(trn);
          int input =  JOptionPane.showConfirmDialog(null,"Are you sure you want to delete taxpayer with TRN: "+ trn + "?", "Select an Option...",JOptionPane.YES_NO_OPTION);
          if(input==0) {
            taxpayerManager.removeTaxpayer(taxRegistrationNumber);
            taxRegisterNumberModel.removeElement(trn);
          }
          System.out.println(input);
        }
      }
      
    } 
  });
  btnDeleteTaxpayer.setBounds(399, 0, 199, 40);
  contentPane.add(btnDeleteTaxpayer);
  
  
  
  
  
  
  
  
  
  
  }
}