
public class Canvas {

	public int[] pixels;
	public int width;
	public int height;	

	public Canvas(){
		this(100);
	}
	public Canvas(int size){
		this(size, size);
	}
	public Canvas(int width, int height){
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		clear();
	}

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getSize(){
		return pixels.length;
	}

	public int[] getIntArray(){
		return pixels;
	}
	public int getPixel(int x, int y){
		return pixels[getSize() - (width - x) + y * width];

	}

	public void setPixel(int x, int y, int color){
		if (x < 0 || x >= width || y < 0 || y >= height)  return;
		pixels[getSize() - (width - x) - y * width] = color;
	}
	
	public void setPixel(int x, int y, Color color){
		setPixel(x,y,color.get());
	}

	public void fade(double fade){
		for (int i = 0; i < getSize(); i++){
			Color c = new Color(pixels[i]);
			pixels[i] = c.darken(fade).get();
			
		}
	}
	
	public void clear(){
		for (int i = 0; i < getSize(); i++){
			pixels[i] = 0xFF000000;
		}
	}
}