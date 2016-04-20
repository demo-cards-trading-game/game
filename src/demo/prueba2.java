package demo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class prueba2 extends JInternalFrame
{
	private boolean band1;
	private JTable leftTable;
	private JTable rightTable;
	private JButton addButton;
	public int cant;
	public BigCard current;
	public JPanel panel;
	private JLabel Count;
	public static int c;
	private JButton Create;
	public deck mazo;
	public JInternalFrame opciones;
	public JButton aceptar; 
	public int num;
	
	public prueba2(deck d)
	{
		this.mazo= new deck();
		this.mazo=d;
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setBounds(0,0,800,600);	
		setOpaque(false);
		Count = new JLabel("0");
		Count.setForeground(new Color(0, 204, 204));
		Count.setFont(new Font("Tahoma", Font.PLAIN, 57));
		Count.setHorizontalAlignment(SwingConstants.CENTER);
		Count.setLocation(390, 350);
		Count.setSize(78, 62);
		c=0;

		cant=mazo.cardsLeft();
		addButton = new JButton("add to deck");
		addButton.addActionListener(arg0 -> {
        });
		addButton.setBounds(367, 163, 121, 29);
		JButton removeButton = new JButton("Remove from deck");
		removeButton.setBounds(367, 203, 121, 34);
		setLayout(null);
	      
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(new Rectangle(50, 343, 271, 246));
		rightTable = new JTable(new SimpleColorTableModel());
		rightTable.setBackground(new Color(0, 204, 204));
		scrollPane.setColumnHeaderView(rightTable);
		JLabel label_1 = new JLabel("Your Choices");
		label_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label_1.setOpaque(true);
		label_1.setBackground(new Color(0, 204, 255));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(141, 303, 100, 29);
		add(label_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(new Rectangle(500, 0, 250, 400));
		scrollPane_1.setBounds(20, 20, 271, 208);
		add(scrollPane_1);
		leftTable = new JTable(new SimpleColorTableModel());
		leftTable.setBackground(new Color(153, 204, 255));
		scrollPane_1.setViewportView(leftTable);
		setupTable(leftTable);
		populate((SimpleColorTableModel) leftTable.getModel());
		setSize(620, 420);
		addButton.setEnabled(false);
		removeButton.setEnabled(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_1.setBackground(new Color(102, 204, 255));
		panel_1.setBounds(105, 27, 176, 29);
		panel_1.setLayout(null);
		JLabel label = new JLabel("Available Choices");
		label.setBounds(21, 11, 131, 14);
		panel_1.add(label);
		label.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
	        	        
		JLabel lblPreview = new JLabel("Preview");
		lblPreview.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblPreview.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		lblPreview.setBackground(new Color(0, 204, 204));
		lblPreview.setOpaque(true);
		lblPreview.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreview.setBounds(585, 39, 121, 29);

		JLabel lblCardsOnDeck = new JLabel("Cards on Deck");
		lblCardsOnDeck.setOpaque(true);
		lblCardsOnDeck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardsOnDeck.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblCardsOnDeck.setBackground(new Color(0, 204, 255));
		lblCardsOnDeck.setBounds(380, 310, 100, 29);

		Create = new JButton("Create");
		Create.setBounds(599, 505, 141, 52);
		Create.setEnabled(false);

		leftTable.getSelectionModel().addListSelectionListener(e -> {
            int indice;
            String aux;
            char a;
            aux = e.getSource().toString();
            indice= e.getSource().toString().indexOf("{")+1;
            a= aux.charAt(indice);
            num = Integer.parseInt(""+a);
            a= aux.charAt(indice+1);
            if(a!='}'){
                num=num*10;
                num=num+Integer.parseInt(""+a);
            }

            int count = leftTable.getSelectedRowCount();

            if(count>0&& band1 )
            {
                if (current!=null)
                    remove(current);

                for (int ignored :leftTable.getSelectedRows())
                {
                    current=new BigCard(mazo.Consultar(num),340,30);
                    current.addMouseListener(new MouseListener(){

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(e.getSource()==current){
                                opciones.setVisible(true);
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent arg0) {
                        }

                        @Override
                        public void mouseExited(MouseEvent arg0) {
                        }

                        @Override
                        public void mousePressed(MouseEvent arg0) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent arg0) {
                        }
                    });
                }
                add (current);
            }
            repaint();
            band1= !band1;
        });

		addButton.addActionListener(e -> {
            moveSelectedRow(leftTable, rightTable);
            c=c+1;
            Count.setText(""+c);
            Count.repaint();
            addButton.setEnabled(false);
            if(c==10)
                Create.setEnabled(true);
        });

		removeButton.addActionListener(e -> {
            moveSelectedRow(rightTable, leftTable);
            c=c-1;
            Count.setText(""+c);
            Count.repaint();
            if(c==9)
                Create.setEnabled(false);
        });

		this.opciones = new JInternalFrame();
		this.opciones.getContentPane().setLayout(null);
		this.aceptar = new JButton("aceptar");
		this.aceptar.setBounds(15, 11, 100, 20);
		this.opciones.getContentPane().add(this.aceptar);
		this.opciones.setClosable(true);
		this.opciones.setBounds(380,160,130,70);
		this.opciones.setVisible(false);
		add(this.opciones);
		this.opciones.moveToFront();
	}

	protected void setupTable(JTable table) {
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(2).setMaxWidth(10);
		table.getColumnModel().getColumn(0).setMaxWidth(18);
		table.setDefaultRenderer(Color.class, new ColorTableCellRenderer());
	}
	
	public class ColorTableCellRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			setText(null);

			if (value instanceof Color) {
				setOpaque(true);
				setBackground((Color)value);
			}
			return this;
		}
	}

	public static void moveSelectedRow(JTable from, JTable to) {
		SimpleColorTableModel fromModel = (SimpleColorTableModel) from.getModel();
		SimpleColorTableModel toModel = (SimpleColorTableModel) to.getModel();

		for (int index : from.getSelectedRows()) {
			Vector rowValue = (Vector) fromModel.getDataVector().get(index);
			toModel.addRow(rowValue);
		}

		int selectedRow;
		while ((selectedRow = from.getSelectedRow()) != -1) {
			fromModel.removeRow(selectedRow);
		}
		from.clearSelection();
	}
	
	protected void populate(SimpleColorTableModel model) {
		Color color;
		String Nombre;
		int i,id ;
		Card x;
    	
		for(i=0;i<cant;i++)
		{
			x= this.mazo.Consultar(i);
			color = selectColor(x);

			id=x.GetCardNumber();
			Nombre=x.GetName();
			model.addRow(new Object[]{id,Nombre, color});
		}
	}
	
	public class SimpleColorTableModel extends DefaultTableModel {
		public SimpleColorTableModel() {
			addColumn("Id");
			addColumn("Name");
			addColumn("S");
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			Class clazz = String.class;
			switch (columnIndex) {
				case 2:
					clazz = Color.class;
					break;
			}
			return clazz;
		}
	}

	public static Color selectColor(Card x){
		switch (x.GetSource())
		{
			case "Water":	return (new Color(0, 191, 255));
			case "Wind": 	return Color.WHITE;
			case "Fire":   	return (Color.RED);
			case "Earth": 	return new Color(160, 82, 45);
			default : 	return Color.ORANGE;
		}
	}
}