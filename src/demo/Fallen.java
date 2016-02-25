package demo;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import data.LoadData;
import data.prueba.ColorTableCellRenderer;
import data.prueba.SimpleColorTableModel;
import demo.BigCard;
import demo.Card;
import demo.CardGui;

public class Fallen extends JInternalFrame 

{
	private boolean band,band1;
	JTable leftTable;
    private String deckname;
	public LoadData data;
	public int cant;
	//CardGui current;
	BigCard current;
	public JPanel panel;
	private JPanel panel_1;
	public JButton confirmcardsfromfallen;
	int effectnumber;
	int position;
	public static int c;
	 
	public Fallen()
	{
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		
		setClosable(true);
		c=0;
		try {
			data=new LoadData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cant=data.Data.getCantidad();
	        getContentPane().setLayout(null);
	        setSize(736, 400);
	      

	        JScrollPane scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(new Rectangle(500, 0, 250, 400));
	        scrollPane_1.setBounds(50, 67, 271, 245);
	        getContentPane().add(scrollPane_1);
	        leftTable = new JTable(new SimpleColorTableModel());
	        leftTable.setOpaque(false);
	        
	        scrollPane_1.setViewportView(leftTable);
	        confirmcardsfromfallen=new JButton("Select");
	        confirmcardsfromfallen.setEnabled(false);
	        confirmcardsfromfallen.setBounds(127,320,122,40);
	       getContentPane().add(confirmcardsfromfallen);
	        	        setupTable(leftTable);
	        	        
	        	       

	       

	        panel_1 = new JPanel();
	        panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
	        panel_1.setBackground(new Color(102, 204, 255));
	        panel_1.setBounds(91, 29, 176, 29);
	        getContentPane().add(panel_1);
	         panel_1.setLayout(null);
	        	        
	        	        	       
	        	        
	        	        	        JLabel label = new JLabel("Available Choices");
	        	        	        label.setBounds(23, 11, 131, 14);
	        	        	        panel_1.add(label);
	        	        	        label.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
	        	        	        label.setHorizontalAlignment(SwingConstants.CENTER);
	     
	       
	       
	        
	        leftTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) 
	            {

	            	int count = leftTable.getSelectedRowCount();
	                
	                if(count>0 )
	                {	
	                	if (current!=null)
	                		remove(current);
	                
	                		
	                	
	                	SimpleColorTableModel fromModel = (SimpleColorTableModel) leftTable.getModel();
	                	for (int index :leftTable.getSelectedRows()) 
	                	{

	                		Vector rowValue = (Vector) fromModel.getDataVector().get(index);
	                		position=(int) rowValue.get(0);
	                		
	                		current=new BigCard(data.Data.Consultar(position),470,15);
	                	}
	                	getContentPane().add (current);
	                	if(effectnumber==13)
	                	{
	                		confirmcardsfromfallen.setEnabled(true);
	                	}
	                }
	                
       			 repaint();
           
               band1= !band1;  
	         } 
	        });
}

	        
public void remove()
{
	SimpleColorTableModel fromModel = (SimpleColorTableModel) leftTable.getModel();
	fromModel.removeRow(leftTable.getSelectedRow());
}


	
	
	
	
	
	
	  protected void setupTable(JTable table) {

	        table.setFillsViewportHeight(true);
	        table.getColumnModel().getColumn(2).setMaxWidth(10);
	        table.getColumnModel().getColumn(0).setMaxWidth(18);
	     //   table.getColumnModel().getColumn(0).setPreferredWidth(10);
	        
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
    	int i,id ;
    	
    	
    	
		
		
			switch (x.GetSource())
			{
			case "Water":	color=(new Color(0, 191, 255));
				break;
			case "Wind": 	color=Color.WHITE;	
				break;
			case "Fire":   	color=(Color.RED);
				break;
			case "Earth": 	color=new Color(160, 82, 45);
				break;
				
			default : 	color=Color.ORANGE;
			}
			
			id=x.GetCardNumber();
			Nombre=x.GetName();
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
}

