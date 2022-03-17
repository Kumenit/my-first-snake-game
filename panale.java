import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;
;public class panale extends JPanel implements ActionListener {
	static final int width=600;
	static final int hight=600;
	static final int unit=25;
	static final int g_unit=(width*hight)/unit;
	static final int delay=100;
	final int x[]=new int[g_unit];
	final int y[]=new int[g_unit];
	int body=6;
	int appleten=0;
	int k;
	int applex;
	int appley;
	int applez;
	int applev;
	char directoin='R';
	boolean running=false;
	Timer t;
	Random r=new Random();
	public panale()
	{
		this.setPreferredSize(new Dimension(width,hight));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new mykey());
		start();
	}
	public void start()
	{
		newApple();
		running=true;
		t=new Timer(delay,this);
		t.start();
	}
	public void paint(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g)
	{
		if(running)
		{
			/*
		for(int i=0;i<hight/unit;i++)
		{
			g.drawLine(i*unit, 0,i*unit,hight);
			g.drawLine(0,i*unit,width,i*unit);
		}
		*/
		g.setColor(Color.yellow);
		g.fillOval(applex,appley,unit,unit);
		if((k>0) & (k%11==0))
		{
		g.setColor(Color.blue);
		g.fillOval(applez,applev,unit,unit);
		}
		for(int i=0;i<body;i++)
		{
			if(i==0)
			{
				g.setColor(Color.GREEN);
				g.fillRect(x[i],y[i],unit,unit);
	         }
			else {
				g.setColor(new Color(45,180,0));
				g.fillRect(x[i],y[i],unit,unit);
			}
	     }
		g.setColor(Color.red);
		g.setFont(new Font("Ink free",Font.BOLD,40));
		FontMetrics m=getFontMetrics(g.getFont());
		g.drawString("Score: "+appleten,(width - m.stringWidth("Score: "+appleten))/2,g.getFont().getSize());
		}else
		{
			gameover(g);
		}
	}
	public void newApple()
	{
		applex=r.nextInt((int)(width/unit))*unit;
		appley=r.nextInt((int)(hight/unit))*unit;
			applez=r.nextInt((int)(width/unit))*unit;
			applev=r.nextInt((int)(hight/unit))*unit;
	}
	public void move()
	{
		for(int i=body;i>0;i--)
		{
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch(directoin)
		{
		case 'R': x[0]= x[0] + unit;
		break;
		case 'L':
		x[0]= x[0] - unit;
		break;
		case 'U':
		y[0] =y[0] - unit;
		break;
		case 'D':
		y[0]= y[0] + unit;
		break;
		}
	}
	public void apple()
	{
		if((x[0]==applex) && (y[0]==appley))
		{
			body++;
			appleten++;
			k++;
			newApple();
		}
		if((x[0]==applez) && (y[0]==applev))
		{
			body=body+3;
			appleten=appleten+3;
			k=k+3;
			newApple();
		}
	}
	public void coliusoin()
	{
	for(int i=body;i>0;i--)
	{
		if((x[0]==x[i]) && (y[0]==y[i]))
		{
			running=false;
		}
	}
	if(x[0] < 0)
	{
		running=false;
	}
	if(x[0] > width)
	{
		running=false;
	}
	if(y[0] < 0)
	{
		running=false;
	}
	if(y[0] > hight)
	{
		running=false;
	}
	if(!running)
	{
		t.stop();

	}
	}
	public void gameover(Graphics g)
	{
		g.setColor(Color.red);
		g.setFont(new Font("Ink free",Font.BOLD,75));
		FontMetrics m=getFontMetrics(g.getFont());
		g.drawString("GameOver",(width - m.stringWidth("GameOver"))/2,hight/2);
		g.setColor(Color.red);
		g.setFont(new Font("Ink free",Font.BOLD,40));
		FontMetrics m2=getFontMetrics(g.getFont());
		g.drawString("Score: "+appleten,(width - m2.stringWidth("Score: "+appleten))/2,g.getFont().getSize());

		g.setColor(Color.GREEN);
		g.setFont(new Font("Ink Free",Font.BOLD,30));
	   FontMetrics fm=getFontMetrics(g.getFont());
	   g.drawString("press r for restarte",(width-fm.stringWidth("press r for restate"))/2,hight-unit);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running)
		{
			move();
			apple();
			coliusoin();
		}
		repaint();
	}
	public class mykey extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_RIGHT:
				if(directoin!='L')
				{
					directoin='R';
				}
				break;
			case KeyEvent.VK_LEFT:
				if(directoin!='R')
				{
					directoin='L';
				}
				break;
			case KeyEvent.VK_UP:
				if(directoin!='D')
				{
					directoin='U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(directoin!='U')
				{
					directoin='D';
				}
				break;

			}
		}

	}


}
