package IGU;

import java.awt.Color;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
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
    int contador = 0, primeraEscritura = 0;
    		
	public static void main(String[] args) throws Exception {
		IGcaja C = new IGcaja();
	}
	
	public IGcaja() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,200);
		setLocationRelativeTo(null);
		setTitle("Tu cancha - Caja");
		
		Container ctx = getContentPane();
		JPanel Box = new JPanel();
		Box.setLayout(null);
		Box.setBackground(Color.WHITE);

		/* --------------------------------------------------------------------*/
		/* ETIQUETAS */
		
		JLabel Cancha = new JLabel("Cancha");
		Cancha.setBounds(44, 11, 66, 14);
		Box.add(Cancha);
		
		JLabel Ing = new JLabel("Ingreso");
		Ing.setBounds(196, 11, 86, 14);
		Box.add(Ing);
		
		JLabel Valor = new JLabel("Valor");
		Valor.setBounds(372, 11, 46, 14);
		Box.add(Valor);
		
		/* --------------------------------------------------------------------*/
		/* TEXTFIELD PARA LOS VALORES DE CAJA */
		
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
		
		/* --------------------------------------------------------------------*/
		/* BOTONES */
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(173, 79, 120, 23);
		btnActualizar.addActionListener(this);
		Box.add(btnActualizar);
	
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(173, 117, 120, 23);
		btnAtras.addActionListener(this);
		Box.add(btnAtras);
		
		/* --------------------------------------------------------------------*/
						
		ctx.add(Box);
		
		setResizable(false);
	}

	public static int createCSV(String dataCancha, String dataDescripcion, String dataValor, int primeraEscritura) throws Exception {
		PrintWriter writer = null;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File("caja.csv"));
            writer = new PrintWriter(out);
            StringBuilder sb = new StringBuilder();

			sb.append("Cancha");
			sb.append(',');
			sb.append("Ingreso");
			sb.append(',');
			sb.append("Valor");
			sb.append(',');
			sb.append("Total acumulado");
			sb.append('\n');
		    
		    sb.append(dataCancha);
		    sb.append(',');
		    sb.append(dataDescripcion);
		    sb.append(',');
		    sb.append(dataValor);
		    sb.append(',');
		    sb.append(dataValor);
		    sb.append('\n');
		    
		    writer.write(sb.toString());
		    
		    primeraEscritura = 1;
		    JOptionPane.showMessageDialog(null, "Ingreso cargado con éxito!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return primeraEscritura;
    }

	public static int readCSV(int contador) throws Exception {
		BufferedReader reader = null;     
        
        String newLine;
        try {
            reader = new BufferedReader(new FileReader("caja.csv")); 

            int bucle = 0;
			while ((newLine = reader.readLine()) != null) {
				if(bucle == 0) {
					bucle = 1;
				} else {
				    String[] data = newLine.split(",");
				    if(data[3].length() > 0) {
				    	contador = (Integer.parseInt(data[3]));		
				    }		
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        return contador;
    }
	
	public static boolean updateCSV(String dataCancha, String dataDescripcion, String dataValor, int acumulador) throws Exception {
		PrintWriter writer = null;
		FileOutputStream out = null;
		boolean exito = false;
		try {
			out = new FileOutputStream(new File("caja.csv"),true);
			writer = new PrintWriter(out);
			StringBuilder sb = new StringBuilder();
		    
		    sb.append(dataCancha);
		    sb.append(',');
		    sb.append(dataDescripcion);
		    sb.append(',');
		    sb.append(dataValor);
		    sb.append(',');
		    sb.append(acumulador + (Integer.parseInt(dataValor)));
		    sb.append('\n');
		    
		    writer.write(sb.toString());
	
		    JOptionPane.showMessageDialog(null, "Ingreso cargado con Exito!");
		    
		    exito = true;
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		return exito;
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
				
				if(primeraEscritura == 0) {
					try {
						int pudoEscribir = createCSV(dataCancha, dataDescripcion, dataValor, primeraEscritura);
						primeraEscritura = pudoEscribir;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						int acumulador = readCSV(contador);
						updateCSV(dataCancha, dataDescripcion, dataValor, acumulador);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} 
			} else {
				JOptionPane.showMessageDialog(null, "Es necesario completar todos los campos");
			}
		}
	}
}
