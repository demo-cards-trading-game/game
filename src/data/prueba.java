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
    private String deckname;
	public LoadData data;
	public int cant;
	//CardGui current;
	BigCard current;
	public JPanel panel;
	private JPanel panel_1;
	private JLabel Count; 
	public static int c;
	private JLabel lblCardsOnDeck;
	private JButton Create;
	 
	public prueba()
	{
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		setBounds(0,0,800,600);	
		setOpaque(false);
		Count = new JLabel("0");
		Count.setForeground(new Color(0, 204, 204));
		Count.setFont(new Font("Tahoma", Font.PLAIN, 57));
		Count.setHorizontalAlignment(SwingConstants.CENTER);
		Count.setLocation(390, 350);
		Count.setSize(78, 62);
		add(Count);
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
	        addButton = new JButton("add to deck");
	        addButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        	}
	        });
	        addButton.setBounds(367, 163, 121, 29);
	        removeButton = new JButton("Remove from deck");
	        removeButton.setBounds(367, 203, 121, 34);
	        setLayout(null);
	      
	        add(addButton);
	        add(removeButton);
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(new Rectangle(50, 343, 271, 246));
	        add(scrollPane);
	        rightTable = new JTable(new SimpleColorTableModel());
	        rightTable.setBackground(new Color(0, 204, 204));
	        scrollPane.setColumnHeaderView(rightTable);
	        setupTable(rightTable);
	        JLabel label_1 = new JLabel("Your Choices");
	        label_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
	        label_1.setOpaque(true);
	        label_1.setBackground(new Color(0, 204, 255));
	        label_1.setHorizontalAlignment(SwingConstants.CENTER);
	        label_1.setBounds(141, 303, 100, 29);
	        add(label_1);

	      

	        JScrollPane scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(new Rectangle(500, 0, 250, 400));
	        scrollPane_1.setBounds(50, 67, 271, 208);
	        add(scrollPane_1);
	        leftTable = new JTable(new SimpleColorTableModel());
	        leftTable.setBackground(new Color(153, 204, 255));
	        scrollPane_1.setViewportView(leftTable);
	        
	        	        setupTable(leftTable);
	        	        
	        	        populate((SimpleColorTableModel) leftTable.getModel());

	        setSize(800, 600);
	        
	        addButton.setEnabled(false);
	        removeButton.setEnabled(false);

	        panel_1 = new JPanel();
	        panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
	        panel_1.setBackground(new Color(102, 204, 255));
	        panel_1.setBounds(105, 27, 176, 29);
	        add(panel_1);
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
	        	        add(lblPreview);
	        	        
	        	        lblCardsOnDeck = new JLabel("Cards on Deck");
	        	        lblCardsOnDeck.setOpaque(true);
	        	        lblCardsOnDeck.setHorizontalAlignment(SwingConstants.CENTER);
	        	        lblCardsOnDeck.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
	        	        lblCardsOnDeck.setBackground(new Color(0, 204, 255));
	        	        lblCardsOnDeck.setBounds(380, 310, 100, 29);
	        	        add(lblCardsOnDeck);
	        	        
	        	        Create = new JButton("Create");
	        	        Create.setBounds(599, 505, 141, 52);
	        	        add(Create);
	        	        Create.setEnabled(false);
	     
	       
	       
	        
	        leftTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) 
	            {

	            	int count = leftTable.getSelectedRowCount();
	                
	                if(count>0&& band1 )
	                {	
	                	if (current!=null)
	                		remove(current);
	                	if(c < 10)
	                		addButton.setEnabled(true);
	                	
	                	SimpleColorTableModel fromModel = (SimpleColorTableModel) leftTable.getModel();
	                	for (int index :leftTable.getSelectedRows()) 
	                	{

	                		Vector rowValue = (Vector) fromModel.getDataVector().get(index);
	                		int x=(int) rowValue.get(0);
	                		current=new BigCard(data.Data.Consultar(x),520,120);
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
	                           current=new BigCard(data.Data.Consultar(x),520,120);
							
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
	                c=c+1;
	                Count.setText(""+c);
	                Count.repaint();
	                addButton.setEnabled(false);
	                if(c==10)
	                	Create.setEnabled(true);
	            }
	        });

	        removeButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	                
	            	moveSelectedRow(rightTable, leftTable);
	                c=c-1;
	                Count.setText(""+c);
	                Count.repaint();
	                if(c==9)
	                	Create.setEnabled(false);
	            }
	        });
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
