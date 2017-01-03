
public class Color {
	
	public static final Color WHITE  	= new Color(0xFFFFFFFF);
	public static final Color GRAY 		= new Color(0xFF7F7F7F);
	public static final Color BLACK 	= new Color(0xFF000000);
	
	public static final Color RED 		= new Color(0xFFFF0000);
	public static final Color GREEN		= new Color(0xFF00FF00);
	public static final Color BLUE 		= new Color(0xFF0000FF);
	
	public static final Color YELLOW  	= new Color(0xFFFFFF00);
	public static final Color ORANGE  	= new Color(0xFFFF7F00);
	public static final Color PURPLE  	= new Color(0xFFFF00FF);

	private final int value;
	
	public Color(){
		this.value = 0xFF7F7F7F;
	}
	public Color(int int_value){
		this.value = int_value;
	}
	public Color(int red, int green, int blue){
		int alpha 	= 255;
		red 		= Math.max(Math.min(red, 	255), 0);
		green 		= Math.max(Math.min(green, 	255), 0);
		blue 		= Math.max(Math.min(blue, 	255), 0);
		
		value = (((byte) alpha 	& 0xFF) << 24) |
				(((byte) red 	& 0xFF) << 16) |
				(((byte) green 	& 0xFF) << 8)  |
				(((byte) blue  	& 0xFF) << 0);
	}
	public Color(int alpha, int red, int green, int blue){
		alpha 	= Math.max(Math.min(alpha, 	255), 0);
		red 	= Math.max(Math.min(red, 	255), 0);
		green 	= Math.max(Math.min(green, 	255), 0);
		blue 	= Math.max(Math.min(blue, 	255), 0);
		
		value = (((byte) alpha 	& 0xFF) << 24) |
				(((byte) red 	& 0xFF) << 16) |
				(((byte) green 	& 0xFF) << 8)  |
				(((byte) blue  	& 0xFF) << 0);
	
	}
	
	public static Color random() {
		return new Color((int)(Math.random() * Integer.MAX_VALUE) | 0xFF000000);
	}
	public int get(){
		return value;
	}
	
	public int getAlpha(){
		return value >> 24 & 0xFF;
	}
	public int getRed(){
		return value >> 16 & 0xff;
	}
	public int getGreen(){
		return value >> 8 & 0xff;
	}
	public int getBlue(){
		return value >> 0 & 0xff;
	}
	
	public Color darken(double factor){
		int r = getRed();
		int g = getGreen();
		int b = getBlue();
		
		return new Color(
			Math.max((int)(r * factor), 0),
			Math.max((int)(g * factor), 0),
			Math.max((int)(b * factor), 0));
	}
	public Color brighten(double factor) {
		int r = getRed();
		int g = getGreen();
		int b = getBlue();
		
		if (r / factor > 255 || g / factor > 255 || b / factor > 255)
			return this;
		
		return new Color(
			Math.min((int)(r / factor), 255),
			Math.min((int)(g / factor), 255),
			Math.min((int)(b / factor), 255));
	}
	

	@Override
	public String toString(){
		return Integer.toHexString(value);
	}
	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj instanceof Color){
			Color c = (Color) obj;
			return this.value == c.value;
		}
		return false;
	}
	 
	public Color clone(){
		return new Color(value);
	}
}
