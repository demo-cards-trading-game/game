package demo;
import data.LoadData;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class Fallen extends JInternalFrame implements ActionListener{
	private boolean band1;
	public JTable leftTable;
	public LoadData data;
	public int cant;
	public BigCard current;
	public int selecting=0;
	public JPanel panel;
	public JButton confirmcardsfromfallen;
	public int effectnumber;
	public int position;
	public SmallCard[] cards=new SmallCard[4];
	public static int c;
	public JButton button;
	public int a;

	public Fallen() throws IOException {
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setClosable(true);
		getContentPane().setLayout(null);
		setSize(836, 450);

		c=0;
		data=new LoadData();
		cant=data.Data.getCantidad();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(new Rectangle(500, 0, 250, 400));
		scrollPane_1.setBounds(50, 67, 271, 245);
		getContentPane().add(scrollPane_1);

		leftTable = new JTable(new SimpleColorTableModel());
		leftTable.setOpaque(false);
		scrollPane_1.setViewportView(leftTable);

		confirmcardsfromfallen=new JButton("CONFIRM");
		confirmcardsfromfallen.setEnabled(false);
		confirmcardsfromfallen.setBounds(390,370,122,40);
		getContentPane().add(confirmcardsfromfallen);

		setupTable(leftTable);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_1.setBackground(new Color(102, 204, 255));
		panel_1.setBounds(91, 29, 176, 29);
		panel_1.setLayout(null);
		getContentPane().add(panel_1);

		JLabel label = new JLabel("Available Choices");
		label.setBounds(23, 11, 131, 14);
		label.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);

		button = new JButton("Select");
		button.setEnabled(false);
		button.setBounds(119, 370, 122, 40);
		button.addActionListener(this);
		getContentPane().add(button);
	        	        	        
		JLabel lblSelecting = new JLabel("selecting");
		lblSelecting.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblSelecting.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecting.setBounds(390, 11, 122, 29);
		getContentPane().add(lblSelecting);
	        
		leftTable.getSelectionModel().addListSelectionListener(e -> {
            int count = leftTable.getSelectedRowCount();

            if(count>0 ){
                if (current!=null)
                    remove(current);

                SimpleColorTableModel fromModel = (SimpleColorTableModel) leftTable.getModel();
                for (int index :leftTable.getSelectedRows()){
                    Vector rowValue = (Vector) fromModel.getDataVector().get(index);
                    position=(int) rowValue.get(0);

					try {
						current=new BigCard(data.Data.Consultar(position),570,30);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					if(cards[selecting]!=null)
                        remove(cards[selecting]);
				}

				try {
					cards[selecting]=new SmallCard(current.getcard(),0,0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				switch(selecting){
                    case 0:cards[selecting].setBounds(400,40,100,145);
                        break;
                    case 1: cards[selecting].setBounds(400,200,100,145);
                        break;
                }
                add(cards[selecting]);
                add (current);

                if(effectnumber==13){
                    confirmcardsfromfallen.setEnabled(true);
                }
            }
            repaint();
            band1= !band1;
        });
	}

	public void remove(){
		SimpleColorTableModel fromModel = (SimpleColorTableModel) leftTable.getModel();
		fromModel.removeRow(leftTable.getSelectedRow());
	}
	
	protected void setupTable(JTable table){
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

	protected void populate(SimpleColorTableModel model,Card x) {
		Color color;
		String Nombre;
		int id ;

		color = prueba2.selectColor(x);
		id=x.getCardNumber();
		Nombre=x.getName();
		model.addRow(new Object[]{id,Nombre, color});
		repaint();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button){
			if(effectnumber==13){
				confirmcardsfromfallen.setEnabled(true);
			}else{
				if(cards[selecting].getCard().getSource().equals("Water")){
					if (selecting < 1){
						selecting++;
						remove();
					}else{
						confirmcardsfromfallen.setEnabled(true);
					}
				}
			}
		}
	}
}