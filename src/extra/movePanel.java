package extra;

import javax.swing.JPanel;

import demo.CardGui;

public class movePanel extends JPanel {
	public boolean animating=false;
	public boolean exmutua;
	public CardGui moving;
	public movePanel()
	{
		this.setBounds(0,0,1024,768);
		this.setLayout(null);
	
	}
	public void appear(final CardGui card) {
		moving=card;
		add(moving);
		Thread t = new Thread(new Runnable() {

			
			public void start() {
				this.start();
			}

			public void run() {
				
		
				
				
				moving.setBounds(925-62,609-93,0,0);
				
				animating= true;
				
				int i=0,j=0;
				while (i<=124 || j<=186) {
					
					try {
						if(i<=124){
						i++;
						moving.setBounds(925-62,609-93,i,j);
						Thread.sleep(1);
						}
						if(j<=186){
						j++;
						moving.setBounds(925-62,609-93,i,j);
					
						Thread.sleep(1);
						}
					
					} catch (InterruptedException e) {
						e.printStackTrace();

					}
					
				}
				i=925-62;
				while(i>=652)
				{
					i--;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					moving.setLocation(i, 609-93);
				}
				remove(moving);
				animating=false;
			}
		});
		t.start();

	}

}
