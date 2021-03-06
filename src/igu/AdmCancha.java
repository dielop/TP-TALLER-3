package igu;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;



import javax.swing.SwingUtilities;



public class AdmCancha extends JFrame implements ActionListener {
	//Variables Globales
	JButton btnCaja, btnReservar, btnVReservas;
	private JLabel lblDatos;
	JToggleButton btnCancha, btnCancha2, btnHora[], btnHora2[], btnCancelar;
	public JTextField nombre, apellido, telefono;
	JButton btn1[], btn2[];
	int c=0, contador = 0, p = 0, z = 1, primeraEscritura = 0, contador1 = 0, temp1 = 0, contador2 = 0, temp2 = 0;
    static int n = 0, k = 0;
	Color green = new Color(10, 47, 30);
	Color selectedGreen = new Color(75, 139,  59);
    ArrayList <String> horario1 = new ArrayList <String>();
    ArrayList <String> horario2 = new ArrayList <String>();
    String dataNombre = "", dataApellido = "", dataTelefono = "";
   
    
    String opcion;
    boolean esCancha1;

	public AdmCancha() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(730,560);
		setLocationRelativeTo(null);
		setTitle("Tu cancha");
		
		Container ctx = getContentPane();
		JPanel MenuCanchas = new JPanel();
		MenuCanchas.setLayout(null);
		MenuCanchas.setBackground(Color.BLACK);
		
		/* --------------------------------------------------------------------*/

		//BOTONES DE RESERVA CANCHA 1 O CANCHA 2
		btnCancha = new JToggleButton("Cancha 1", false);
		UIManager.put("ToggleButton.select", selectedGreen);
		SwingUtilities.updateComponentTreeUI(btnCancha);
		btnCancha.setBorder(UIManager.getBorder("RadioButton.border"));
		btnCancha.setBounds(122, 59, 162, 377);
		btnCancha.addActionListener(this);
		btnCancha.setBackground(green);
		btnCancha.setForeground(Color.WHITE);
		MenuCanchas.add(btnCancha);
		
		btnCancha2 = new JToggleButton("Cancha 2", false);
		UIManager.put("ToggleButton.select", selectedGreen);
		SwingUtilities.updateComponentTreeUI(btnCancha2);
		btnCancha2.setBorder(UIManager.getBorder("RadioButton.border"));
		btnCancha2.setBounds(300, 59, 162, 377);
		btnCancha2.addActionListener(this);
		btnCancha2.setBackground(green);
		btnCancha2.setForeground(Color.WHITE);
		MenuCanchas.add(btnCancha2);
		
		/* --------------------------------------------------------------------*/

		//BOTONES DE HORARIOS - CREACION Y ADHESION		
		btn1 = new JButton[10];
		btn2 = new JButton[10];
		
		int x = 40, a = 470, b = 59, c = 75, d = 33;
		
		for(int i = 0; i<10; i++) {		
			btn1[i] = new JButton(Integer.toString(i+13) + "hs");
			btn1[i].setEnabled(false);
			btn1[i].setName(Integer.toString(i+13) + "hs");
			btn1[i].setBounds(x, b, c, d);
			btn1[i].addActionListener (new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					JButton o = (JButton)e.getSource();
					String name = o.getName();
					
					btnReservar.setEnabled(true);
					
					esCancha1 = true;
					opcion = name;
					
					/* --------------------------------------------------------------------*/
					/* Si el boton cancelar seleccionado, elimina horario del ArrayList    */
					/* reescribe el archivo con los horarios                               */
					if(btnCancelar.isEnabled() && btnCancelar.isSelected()) {
						if(horario1.contains(opcion)) {									
							try {
								int indexes = horario1.indexOf(opcion);	
								for(int i = 0; i < 4; i++) {
									horario1.remove(indexes - i);
									
									if(i == 3) {
										createCSV(horario1, horario2);	
									}
								}	
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							for(int x =0; x < 10; x++) {
								if(btn1[x].getName() == opcion)
									btn1[x].setBackground(new JButton().getBackground());
							}
							
							JOptionPane.showMessageDialog(null, "Cancelacion exitosa");
						}
						btnCancelar.setSelected(false);
						btnCancha.setSelected(false);
					}
					
				}
		    });
			MenuCanchas.add(btn1[i]);
			
			btn2[i] = new JButton(Integer.toString(i+13) + "hs");
			btn2[i].setEnabled(false);
			btn2[i].setName(Integer.toString(i+13) + "hs");
			btn2[i].setBounds(a, b, c, d);
			btn2[i].addActionListener (new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					JButton o = (JButton)e.getSource();
					String name = o.getName();
					
					btnReservar.setEnabled(true);
					
					esCancha1 = false;
					opcion = name;
					
					/* --------------------------------------------------------------------*/
					/* Si el boton cancelar seleccionado, elimina horario del ArrayList    */
					/* reescribe el archivo con los horarios                               */
					if(btnCancelar.isEnabled() && btnCancelar.isSelected()) {
						if(horario2.contains(opcion)) {											
							try {
								int indexes = horario2.indexOf(opcion);	
								for(int i = 0; i < 4; i++) {
									horario2.remove(indexes - i);

									if(i == 3) {
										createCSV(horario1, horario2);	
									}
								}	
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							for(int x =0; x < 10; x++) {
								if(btn2[x].getName() == opcion)
									btn2[x].setBackground(new JButton().getBackground());
							}
						
							JOptionPane.showMessageDialog(null, "Cancelacion exitosa");
						}
						btnCancelar.setSelected(false);
						btnCancha2.setSelected(false);
					}
				}
		    });
			MenuCanchas.add(btn2[i]);
			b = b + 38;
		}
		
		//BOTON DE CAJA
		btnCaja = new JButton("Ver caja");
		btnCaja.setBounds(570, 403, 106, 33);
		btnCaja.addActionListener(this);
		MenuCanchas.add(btnCaja);
		
		/* --------------------------------------------------------------------*/
		//TEXTFIELDS PARA COMPLETAR DATOS DE LAS PERSONAS QUE RESERVAN
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setForeground(Color.WHITE);
        lblNombre.setBounds(595, 40, 70, 40);
        MenuCanchas.add(lblNombre);
        
		nombre = new JTextField();
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		nombre.setBounds(570, 69, 106, 40);
		nombre.setColumns(10);
		nombre.addActionListener(this);
		MenuCanchas.add(nombre);
				
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellido.setForeground(Color.WHITE);
        lblApellido.setBounds(595, 100, 70, 40);
        MenuCanchas.add(lblApellido);
        
		apellido = new JTextField();
		apellido.setHorizontalAlignment(SwingConstants.CENTER);
		apellido.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		apellido.setBounds(570, 130, 106, 40);
		apellido.addActionListener(this);
		MenuCanchas.add(apellido);
				
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefono.setForeground(Color.WHITE);
        lblTelefono.setBounds(595, 161, 70, 40);
        MenuCanchas.add(lblTelefono);
        
		telefono = new JTextField();
		telefono.setHorizontalAlignment(SwingConstants.CENTER);
		telefono.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		telefono.setBounds(570, 191, 106, 40);
		telefono.addActionListener(this);
		MenuCanchas.add(telefono);
		
		/* --------------------------------------------------------------------*/
		
		//BOTONES DE RESERVA Y CANCELACION DE TURNO
		
		btnReservar = new JButton("Reservar");
		btnReservar.setBounds(570, 240, 106, 51);
		btnReservar.setEnabled(false);
		btnReservar.addActionListener(this);
		MenuCanchas.add(btnReservar);
		
		btnCancelar = new JToggleButton("Cancelar");
		btnCancelar.setBounds(570, 300, 106, 51);
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(this);
		MenuCanchas.add(btnCancelar);
		
		/* --------------------------------------------------------------------*/

		//ETIQUETAS - TITULOS DE BOTONES
		JLabel lblHorarios = new JLabel("Canchas con sus horarios");
		lblHorarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHorarios.setForeground(Color.WHITE);
        lblHorarios.setBounds(170, 20 , 440, 33);
        MenuCanchas.add(lblHorarios);
		
		lblDatos = new JLabel("Acciones");
		lblDatos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDatos.setForeground(Color.WHITE);
		lblDatos.setBounds(580, 20, 120, 33);
		MenuCanchas.add(lblDatos);
		
		ctx.add(MenuCanchas);

		setVisible(true);
	}
	    /* --------------------------------------------------------------------*/
	    /* Creo el archivo de reservas */
	public static boolean createCSV (ArrayList <String> horario1, ArrayList <String> horario2) throws Exception {
		boolean exito = false;
		PrintWriter writer = null;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File("reservas.csv"));
			writer = new PrintWriter(out);
			StringBuilder sb = new StringBuilder();
			
			sb.append("Nombre");
			sb.append(",");
			sb.append("Apellido");
			sb.append(",");
			sb.append("Telefono");
			sb.append(",");
			sb.append("Horario");
		    sb.append(',');
		    sb.append("Cancha");
		    sb.append('\n');
		
		    if(horario1.size() > 0) {
		    	int t = 4, x = 0;
			    for(String hora : horario1) {
			    	sb.append(hora);
			    	sb.append(',');
			    	x++;
			    	
			    	if(x == t) {
				    	t = t+4;
				    	sb.append("1");
				    	sb.append('\n');
			    	}
			    }	
		    }
		   
		    if(horario2.size() > 0) {
			    int p = 4, l = 0;
			    for (String hora : horario2) {
			    	sb.append(hora);
			    	sb.append(',');
			    	l++;
			    	
			    	if(l == p) {
				    	p = p + 4;
				    	sb.append("2");
				    	sb.append('\n');
			    	}
			    }
		    }
		    
		    writer.write(sb.toString());
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
	
	/* --------------------------------------------------------------------*/
	/* Acciones de los botones*/
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/* --------------------------------------------------------------------*/
		/* -------------------------------- RESERVAS --------------------------*/
		/* --------------------------------------------------------------------*/
		if(btnReservar == e.getSource()) {
			dataNombre = nombre.getText();	
			dataApellido = apellido.getText();	
			dataTelefono = telefono.getText();	
			
			// Valido que haya ingresado los datos del cliente para poder reservar
			if(!dataNombre.isEmpty() && !dataApellido.isEmpty() && !dataTelefono.isEmpty()) {
				if(esCancha1) {
					horario1.add(dataNombre);
					horario1.add(dataApellido);
					horario1.add(dataTelefono);
					horario1.add(opcion);
					
					for(int i = 0; i < 10; i++) {
						if(btn1[i].getName() == opcion) {
							btn1[i].setEnabled(false);
							btn1[i].setBackground(Color.DARK_GRAY);
						}
					}
				} else {
					horario2.add(dataNombre);
					horario2.add(dataApellido);
					horario2.add(dataTelefono);
					horario2.add(opcion);
					
					for(int i = 0; i < 10; i++) {
						if(btn2[i].getName() == opcion) {
							btn2[i].setEnabled(false);
							btn2[i].setBackground(Color.DARK_GRAY);
						}
					}
				}				
				
				try {
					createCSV(horario1, horario2);
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
				
				nombre.setText("");
				apellido.setText("");
				telefono.setText("");
				btnReservar.setEnabled(false);
				btnCancha.setSelected(false);
				btnCancha2.setSelected(false);
				
				JOptionPane.showMessageDialog(null, "Reserva exitosa");
			} else {
				JOptionPane.showMessageDialog(null, "Son obligatorios los datos del usuario");
			}
		}
		
		// Cuando se selecciona una cancha se deselecciona la otra
		if (btnCancha2 == e.getSource()) {
			btnCancha.setSelected(false);       
			btnReservar.setEnabled(false);
		} else if(btnCancha == e.getSource()) {
			btnCancha2.setSelected(false);
			btnReservar.setEnabled(false);
		}
		
		// Habilita horarios segun cancha seleccionada si aun no fueron reservados		 
		for(int i = 0; i<10; i++) {
			if(btnCancha.isSelected() && !horario1.contains(btn1[i].getName())) {
				btn1[i].setEnabled(true);	
				btn2[i].setEnabled(false);		
			} else if (btnCancha2.isSelected() && !horario2.contains(btn2[i].getName())) {
				btn2[i].setEnabled(true);	
				btn1[i].setEnabled(false);	 
			} else {				
				btn2[i].setEnabled(false);
				btn1[i].setEnabled(false);
				btnReservar.setEnabled(false);
			}
		}
		
		/* --------------------------------------------------------------------*/
		/* ------------------- HABILITA BOTONES PARA CANCELACION --------------*/
		/* --------------------------------------------------------------------*/
		if(btnCancha.isSelected() && horario1.size() > 0 || btnCancha2.isSelected() && horario2.size() > 0) {
			btnCancelar.setEnabled(true);
		} else {
			btnCancelar.setEnabled(false);
		}
		for(int i = 0; i<10; i++) {
			if(btnCancelar.isSelected()) {					
				btn1[i].setEnabled(true);
			}
		}
		for(int i = 0; i<10; i++) {
			if(btnCancelar.isSelected()) {					
				btn2[i].setEnabled(true);
			}
		}
		
		/* --------------------------------------------------------------------*/
		/* -------------------------------- CAJA ------------------------------*/
		/* --------------------------------------------------------------------*/
		if(btnCaja == e.getSource()) {
			JOptionPane.showMessageDialog(null, "Abriendo caja");
			IGcaja mover = new IGcaja();
			mover.setVisible(true);
		}		
	}
}