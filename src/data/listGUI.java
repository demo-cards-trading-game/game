package data;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JList;
import demo.CardGui;
import data.LoadData;

public class listGUI extends JPanel {

	public JComboBox<CardGui> list;
	public LoadData data;

	public listGUI() {
		setBounds(new Rectangle(0, 0, 800, 600));
		setLayout(null);
		try {
			data=new LoadData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CardGui x = new CardGui(data.Data.Consultar(1));
		list = new JComboBox();
		list.setBounds(new Rectangle(100, 100, 600, 400));
		list.setBounds(121, 75, 600, 481);
		list.add(x);
		add(list);

	}
}
