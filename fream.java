import javax.swing.JFrame;

public class fream extends JFrame {
	public fream()
	{
		this.add(new panale());
		this.setTitle("snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
