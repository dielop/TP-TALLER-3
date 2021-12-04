package IGU;

import java.awt.Color;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class IGcaja extends JFrame implements ActionListener {
	private JTextField canText;
	private JTextField desText;
	private JTextField valText;
	JButton btnActualizar, btnCierre, btnAtras, btnExport;
	JPanel Box;
	ArrayList <String> canchas = new ArrayList <String>();
    ArrayList <String> descripciones = new ArrayList <String>();
    ArrayList <String> valores = new ArrayList <String>();
    int contador = 0;
    		
	public static void main(String[] args) {
		IGcaja C = new IGcaja();
	}
	
	public IGcaja() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,230);
		setLocationRelativeTo(null);
		setTitle("Tu cancha - Caja");
		
		Container ctx = getContentPane();
		JPanel Box = new JPanel();
		Box.setLayout(null);
		Box.setBackground(Color.WHITE);

		
		JLabel Cancha = new JLabel("Cancha");
		Cancha.setBounds(44, 11, 66, 14);
		Box.add(Cancha);
		
		JLabel Ing = new JLabel("Ingreso");
		Ing.setBounds(196, 11, 86, 14);
		Box.add(Ing);
		
		JLabel Valor = new JLabel("Valor");
		Valor.setBounds(372, 11, 46, 14);
		Box.add(Valor);
		
		canText = new JTextField();
		canText.setBounds(24, 33, 86, 20);
		canText.addActionListener(this);
		Box.add(canText);
		
		desText = new JTextField();
		desText.setBounds(125, 33, 216, 20);
		Box.add(desText);
		
		valText = new JTextField();
		valText.setBounds(351, 33, 86, 20);
		Box.add(valText);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(173, 79, 120, 23);
		btnActualizar.addActionListener(this);
		Box.add(btnActualizar);
		
		btnExport = new JButton("Exportar");
		btnExport.setBounds(173, 113, 120, 23);
		btnExport.addActionListener(this);
		Box.add(btnExport);
	
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(173, 147, 120, 23);
		btnAtras.addActionListener(this);
		Box.add(btnAtras);
						
		ctx.add(Box);
		
		setResizable(false);
		//setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(btnAtras == e.getSource()) {
			dispose();			
		}
		
		if(btnActualizar == e.getSource()) {
			String dataCancha = canText.getText();			
			String dataDescripcion = desText.getText();
			String dataValor = valText.getText();
			
			if(dataCancha.length() > 0 && dataDescripcion.length() > 0 && dataValor.length() > 0) {				
				canchas.add(dataCancha);
				descripciones.add(dataDescripcion);
				valores.add(dataValor);
				
				try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("caja.csv"),true))) {
					
					StringBuilder sb = new StringBuilder();
				    
				    sb.append(dataCancha);
				    sb.append(',');
				    sb.append(dataDescripcion);
				    sb.append(',');
				    sb.append(dataValor);
				    sb.append('\n');
				    
				    writer.write(sb.toString());
	
				    JOptionPane.showMessageDialog(null, "Ingreso cargado con éxito!");
				} catch (FileNotFoundException i) {
					System.out.println(i.getMessage());
				}  
			} else {
				JOptionPane.showMessageDialog(null, "Es necesario completar todos los campos");
			}
		}
		
		if(btnExport == e.getSource()) {
			JOptionPane.showMessageDialog(null, "Exportando la caja...");
				
			String newLine;
			try (BufferedReader csvReader = new BufferedReader(new FileReader("caja.csv"))) {
				int bucle = 0;
				while ((newLine = csvReader.readLine()) != null) {
					if(bucle == 0) {
						bucle = 1;
					} else {
					    String[] data = newLine.split(",");
					    if(data[2].length() > 0) {
					    	contador = contador + (Integer.parseInt(data[2]));		
					    }		
					}
				}
				
				csvReader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			
			if(contador > 0) {
				try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("caja.csv"),true))) {
					StringBuilder sb = new StringBuilder();
				    
				    sb.append(',');
				    sb.append(',');
				    sb.append(',');
				    sb.append(contador);
				    sb.append('\n');
				    
				    writer.write(sb.toString());
	
				    JOptionPane.showMessageDialog(null, "Planilla exportada con exito!");
				} catch (FileNotFoundException i) {
					System.out.println(i.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aun no hay ingresos en caja");
			}

		}	
	}
}
