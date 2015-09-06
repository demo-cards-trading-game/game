package data;

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
import demo.BigCard;
import demo.Card;
import demo.CardGui;

public class prueba extends JPanel 
{
	private boolean band,band1;
	private JTable leftTable;
    private JTable rightTable;
    private JButton addButton;
    private JButton removeButton;
	public LoadData data;
	public int cant;
	//CardGui current;
	BigCard current;
	public JPanel panel;
	 
	 
	public prueba()
	{
		
		setBounds(0,0,800,600);	
		setOpaque(false);
		
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
	        addButton = new JButton("Add >>");
	        addButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        	}
	        });
	        addButton.setBounds(331, 95, 121, 29);
	        removeButton = new JButton("<< Remove");
	        removeButton.setBounds(331, 514, 121, 34);
	        setLayout(null);

	      
	        add(addButton);
	        add(removeButton);
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(new Rectangle(580, 81, 154, 460));
	        add(scrollPane);
	        rightTable = new JTable(new SimpleColorTableModel());
	        scrollPane.setColumnHeaderView(rightTable);
	        setupTable(rightTable);

	       

	        JLabel label = new JLabel("Available Choices");
	        label.setHorizontalAlignment(SwingConstants.CENTER);
	        label.setBounds(77, 41, 100, 29);
	        add(label);
	        JLabel label_1 = new JLabel("Your Choices");
	        label_1.setHorizontalAlignment(SwingConstants.CENTER);
	        label_1.setBounds(603, 41, 100, 29);
	        add(label_1);

	      

	        JScrollPane scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(new Rectangle(500, 0, 250, 400));
	        scrollPane_1.setBounds(50, 95, 154, 446);
	        add(scrollPane_1);
	        leftTable = new JTable(new SimpleColorTableModel());
	        scrollPane_1.setViewportView(leftTable);
	        
	        	        setupTable(leftTable);
	        	        
	        	        populate((SimpleColorTableModel) leftTable.getModel());

	        setSize(800, 600);
	        
	        addButton.setEnabled(false);
	        removeButton.setEnabled(false);
	        
	     
	       
	       
	        
	        leftTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) 
	            {

	            	int count = leftTable.getSelectedRowCount();
	                
	                if(count>0&& band1 )
	                {	
	                	if (current!=null)
	                		remove(current);
	                	
	                	addButton.setEnabled(true);
	                	SimpleColorTableModel fromModel = (SimpleColorTableModel) leftTable.getModel();
	                	for (int index :leftTable.getSelectedRows()) 
	                	{

	                		Vector rowValue = (Vector) fromModel.getDataVector().get(index);
	                		int x=(int) rowValue.get(0);
	                		current=new BigCard(data.Data.Consultar(x),270,150);
	                	}
	                	add (current);
	                }
	                
       			 repaint();
           
               band1= !band1;  
	         } 
	        });
	        rightTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {

	                int count = rightTable.getSelectedRowCount();
	                
	                 
	                if(count>0 && band)
	                {	
	                	if(current!=null)
	                		remove(current);
	                	 removeButton.setEnabled(true);
	                	
	                	 SimpleColorTableModel fromModel = (SimpleColorTableModel) rightTable.getModel();
	                	   for (int index :rightTable.getSelectedRows()) {

	                           Vector rowValue = (Vector) fromModel.getDataVector().get(index);
	                           int x=(int) rowValue.get(0);
	                           current=new BigCard(data.Data.Consultar(x),270,150);
							
	                       }
	                	add (current);
	                	
	                }
	                	
	                	
	        			 repaint();
	            
	                band= !band;
	            }
	        });

	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	                moveSelectedRow(leftTable, rightTable);

	            }
	        });

	        removeButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	                moveSelectedRow(rightTable, leftTable);

	            }
	        });
}

	        



	
	
	
	
	
	
	
	  protected void setupTable(JTable table) {

	        table.setFillsViewportHeight(true);
	        table.getColumnModel().getColumn(2).setPreferredWidth(10);
	        table.getColumnModel().getColumn(0).setPreferredWidth(30);
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

    protected void moveSelectedRow(JTable from, JTable to) {

        SimpleColorTableModel fromModel = (SimpleColorTableModel) from.getModel();
        SimpleColorTableModel toModel = (SimpleColorTableModel) to.getModel();

        for (int index : from.getSelectedRows()) {

            Vector rowValue = (Vector) fromModel.getDataVector().get(index);

            toModel.addRow(rowValue);

        }

        int selectedRow = -1;
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
    	
    	for(i=1;i<=cant;i++)
		{
			x=data.Data.Consultar(i);
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
}
