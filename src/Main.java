
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings({"serial"})
public class Main extends JFrame implements KeyListener{

	public BufferedImage image;
	public Timer clock;
	public Canvas canvas;
	
	public int width;
	public int height;

	public double type = 0;
	
	public double t = 0;
	
	public double x = Math.random();
	public double y = Math.random();
	public double a = 0.4321115419043551;
	public double b = -0.018188861015270508;
	public double c = 0.6804092013803134;
	public double d = -2.43;
	
	public int iterations = 10000;
	
	public double zoom = 10;
	public double pos_x = 0;
	public double pos_y = 0;
	
	public double fade = 0.99;
	public double dcolor = 50;
	
	public Color color = Color.WHITE;
	
	public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
	}
	
	public Main(){
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	width  = (int) (screen.getWidth()  / 1f);
    	height = (int) (screen.getHeight() / 1f);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setUndecorated(true);
        setTitle("Hopalong");
        setPreferredSize(new Dimension(width,height));
        setLocation((int)((screen.getWidth() - width) / 2), (int)((screen.getHeight() - height) / 2));
        pack();
        setVisible(true);
        addKeyListener(this);
        canvas = new Canvas(width, height);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        setContentPane(new JPanel(){
        	@Override 
        	public void paintComponent(Graphics g) {
        		g.drawImage(image, 0, 0, null);
        	}
        });

        clock = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw();
		        image.setRGB(0, 0, width, height, canvas.getIntArray(), 0, width);
				repaint();
				canvas.fade(fade);
			}
		});
        clock.start();            
	}
	
	
	public void draw(){
		for (int i = 0; i < iterations; i++){
			double nx, ny = 0;
			t += 0.01;
			if (type == 0){
				nx = (y - Math.abs(x) / x * Math.sqrt(Math.abs(b * x - c)));
				ny = (a - x);
			}
			else if (type == 1){
				nx = y - Math.sin(x);
				ny = a - x;
			}
			else{
				nx = Math.sin(t * a);
				ny = Math.sin(t * b);
			}
			x = nx;
			y = ny;
			for (int j = 1; j < 2; j++){
				canvas.setPixel((int)(j * zoom * (x + pos_x) + width / 2), (int)(j * zoom * (y + pos_y) + height / 2) , color);
			}
		}
		color = new Color(
				color.getRed()   + (int)((Math.random() - 0.5) * 50), 
				color.getGreen() + (int)((Math.random() - 0.5) * 50), 
				color.getBlue()  + (int)((Math.random() - 0.5) * 50));
	}

	public void printValues(){
		System.out.println(
				"a = " + a + ";\n" + 
				"b = " + b + ";\n" + 
				"c = " + c		
		);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case (KeyEvent.VK_W):
			pos_y += 10 / zoom;
			break;
		case (KeyEvent.VK_S):
			pos_y -= 10 / zoom;
			break;
		case (KeyEvent.VK_A):
			pos_x -= 10 / zoom;
			break;
		case (KeyEvent.VK_D):
			pos_x += 10 / zoom;
			break;
		case (KeyEvent.VK_E):
			zoom /= 1.1;
			break;
		case (KeyEvent.VK_Q):
			zoom *= 1.1;
			break;

		case (KeyEvent.VK_T):
			type++;
			if (type > 2)
				type = 0;
			break;
		case (KeyEvent.VK_Z):
			a = 200 * (Math.random() - 0.5);
			printValues();
			break;
		case (KeyEvent.VK_X):
			b = 200 * (Math.random() - 0.5);
			printValues();
			break;
		case (KeyEvent.VK_C):
			c = 200 * (Math.random() - 0.5);
			printValues();
			break;
		case (KeyEvent.VK_V):
			d = 200 * (Math.random() - 0.5);
			printValues();
			break;
			

		case (KeyEvent.VK_RIGHT):
			iterations *= 2;
			break;
		case (KeyEvent.VK_LEFT):
			iterations /= 2;
			if (iterations < 1)
				iterations = 1;
			break;

		case (KeyEvent.VK_UP):
			fade += 0.05;
			if (fade > 1)
				fade = 1;
			break;
		case (KeyEvent.VK_DOWN):
			fade -= 0.05;
			if (fade < 0)
				fade = 0;
			break;

		case (KeyEvent.VK_R):
			a = 2 * (Math.random() - 0.5);
			b = 2 * (Math.random() - 0.5);
			c = 2 * (Math.random() - 0.5);
			d = 2 * (Math.random() - 0.5);
			canvas.clear();
			x = Math.random();
			y = Math.random();
			printValues();
			break;

		case (KeyEvent.VK_SPACE):
			canvas.clear();
			break;
		case (KeyEvent.VK_BACK_SPACE):
			canvas.clear();
			x = Math.random();
			y = Math.random();
			break;
		case (KeyEvent.VK_ESCAPE):
			System.exit(0);
			break;
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
