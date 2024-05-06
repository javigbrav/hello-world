package graphics;
import hsa2.*;
//import java.lang.Thread;
import java.awt.*;

/**
 * @Author: Javiera Garrido Bravo
 * Date: November 18
 * Description: program displays 3 different screensavers
 */
public class ScreenSaver {
	
	private static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();  // getScreenSize() returns the size of the screen in pixels 
	static final int GRWIDTH = (int)size.getWidth(); // width will store the width of the screen 
	static final int GRHEIGHT = (int)size.getHeight();  // height will store the height of the screen 
	private static GraphicsConsole gc = new GraphicsConsole (GRWIDTH, GRHEIGHT);//initialize graphics console
	
	
	//first screensaver (circle) variables
	private int circDiameter = 30; //diameter of the initial circle in the first screen
	private int circle_x= (GRWIDTH/2)-circDiameter; //position of the same circle
	private int circle_y=(GRHEIGHT/2)-circDiameter;
	private Color color1= new Color (117, 16, 195); //main color of this 1st event
	private int widthSpiral = 5; //values of size of spiral made
	private int heightSpiral = 5;
	private int addCirc = 50; //how much is added to each circle
	
	
	//variables of moving ant
	private int antSpeed_x = 30; //sets the horizontal speed of the ant
	private int antSpeed_y = 30;//sets the vertical speed of the ant
	private int antStart_x = 0; //where the ant will start to move
	private int antStart_y = 0;
	private final int pixelSizeX=6;
	private final int pixelSizeY=6; //size of the squares that the ant is made of
	private int antSizeX = pixelSizeX*14; //total width of ant
	private int antSizeY = pixelSizeY*16;//total height of ant
	private int secAnt = 30; //time ant will function
	
	//Variables of line pattern
	private Color colorPattern = new Color (192, 91, 2); //color used for pattern
	private int widthRect = 25; //width of the rectangle 
	
	public static void main (String[] args) {
		new ScreenSaver();
	}
	/*
	* Method Name: ScreenSaver
	* Creation Date; November 15, 2023
	* Modified Date: November 16, 2023
	* Description: puts all the animations together
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: ScreenSaver (constructor)
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private ScreenSaver() {
		while (true) {
			initializeCircle();
			initializeAnt();
			initializePattern();
		}
	}
	
	
	////////////////////////****CIRCLE****////////////////////
	
	/*
	* Method Name: createCircle
	* Creation Date; November 8, 2023
	* Modified Date: November 10, 2023
	* Description: creates the outline of circles until the reach the end of the screen
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void createCircle(){
		gc.setColor(color1);
		int n=0; //circle number
		int circDiam = circDiameter; //saves diameter to not change value of global variable
		gc.drawOval(circle_x, circle_y, circDiam, circDiam);
		while (circDiam-200<=GRWIDTH) {
			n++;
			circDiam+=addCirc; //adds 50 every time
			gc.sleep(250); 
			gc.drawOval(circle_x-(addCirc/2*n), circle_y-(25*n), circDiam , circDiam); 
		}
	}
	
	/*
	* Method Name: fillCircle
	* Creation Date; November 8, 2023
	* Modified Date: November 10, 2023
	* Description: creates filled circles until the reach the end of the screen
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void fillCircle() {
		int n = 0; //circle number
		int circDiam = circDiameter; //saves diameter to not change value of global variable
		gc.fillOval(circle_x, circle_y, circDiam, circDiam);
		while ((circDiam-300)<=GRWIDTH) {
			n++;
			circDiam+=addCirc;
			gc.sleep(250);  
			gc.fillOval(circle_x-(addCirc/2*n), circle_y-(25*n), circDiam, circDiam);
		}
	}
	/*
	* Method Name: drawSpiral
	* Creation Date; November 10, 2023
	* Modified Date: November 11, 2023
	* Description: creates an spiral with arc desired
	* @Parameters: Integer: arcAngle
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	public void drawSpiral(int arcAngle) {
		 gc.setColor(Color.black);
	     int x = GRWIDTH/2-widthSpiral; //makes it start at the middle
	     int y = GRHEIGHT/2-heightSpiral;
	     int height = heightSpiral;
	     int width = widthSpiral;
	     int startAngle = 0; //it will always start at 0
	     for (int i = 0; i < GRWIDTH/2+200; i++) {
	    	 gc.sleep(5);
	         gc.drawArc(x, y, width, height, startAngle, arcAngle);
	         x--;
	         y--;
	         width += 2;
	         height += 2;
	         startAngle = startAngle - arcAngle; //changes angle
	      }
	   }
 
	/*
	* Method Name: initializeCircle
	* Creation Date; November 10, 2023
	* Modified Date: November 11, 2023
	* Description: brings all the events of the 1st event together
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void initializeCircle() {
		//set up gc
		gc.setBackgroundColor(Color.BLACK);
		gc.clear();
		createCircle();
		fillCircle();
		drawSpiral(20);
		drawSpiral(350);
		gc.clear();
	}
	
	
	//////////////////////////////***ANT***///////////////////////////////////
	/*
	* Method Name: moveAnt
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: Controls the ant bumping into walls
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void moveAnt () {
			antStart_x += antSpeed_x; //moves animation of ant at speed set
			antStart_y += antSpeed_y;
			animateAnt(); //makes ant do animation
			//bottom of the screen
		    if ((antStart_y + antSizeY) > gc.getDrawHeight()) {
		    	antSpeed_y *= -1;
		    }
		    //right side of the screen
		    if ((antStart_x + antSizeX) > gc.getDrawWidth()) {
		    	antSpeed_x *= -1;
		    }
		    //top of screen
			if (antStart_y < 0) {
				antSpeed_y *=-1;
			}
			//left side of screen
			if (antStart_x < 0) {
				antSpeed_x *=-1;
			}
		
	}
	
	/*
	* Method Name: animateAnt
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: makes ant move by alternating between different drawings and clearing everything
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void animateAnt () {
		
		drawing1(antStart_x, antStart_y);
		gc.sleep(250);	
		gc.clear();
		drawing2 (antStart_x, antStart_y);
		gc.sleep(150);
		gc.clear();
		drawing3 (antStart_x, antStart_y);
		gc.sleep(200);
		gc.clear();
		
	}
	
	/*
	* Method Name: drawing1
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: Brings everything together for the first frame of the ant
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void drawing1(int x, int y) {
		legs1(x, y); 
		body1 (x, y);
		body2 (x, y);
		body3 (x, y);
	}
	
	/*
	* Method Name: drawing2
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: Brings everything together for the second frame of the ant
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void drawing2 (int x, int y) {
		legsRight(x, y);
		body1 (x, y);
		body2 (x, y);
		body3 (x, y);
	}
	
	/*
	* Method Name: drawing3
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: Brings everything together for the third frame of the ant
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void drawing3 (int x, int y) {
		legsLeft(x, y);
		body1 (x, y);
		body2 (x, y);
		body3 (x, y);
	}
	
	/*
	* Method Name: body1
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: Drawing of lower center of the body
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void body1 (int x, int y) {
		pixel (x+(4*pixelSizeX),y, 1, 1);
		pixel (x+(4*pixelSizeX), y+(2*pixelSizeY), 5, 2);
		pixel (x+(5*pixelSizeX), y+(1*pixelSizeY), 3, 1);
		pixel (x+(8*pixelSizeX),y, 1, 1);
	}
	
	/*
	* Method Name: body2
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: Drawing of central part of body
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void body2 (int x, int y) {
		int x1= x+(4*pixelSizeX); //to not mess up the value of x and y
		int y1= y+(6*pixelSizeY); //moves them to the corrects spot
		pixel(x1, y1, 5, 2);
		pixel(x1+(1*pixelSizeX), y1-(1*pixelSizeY), 3, 1);
		pixel(x1+(1*pixelSizeX), y1+(2*pixelSizeY), 3, 1);
		
	}
	
	/*
	* Method Name: body3
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: Drawing of top center part of body
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void body3 (int x, int y) {
		
		pixel (x+(4*pixelSizeX), y+(11*pixelSizeY), 5, 4);
		pixel (x+(5*pixelSizeX), y+(10*pixelSizeY), 3, 1);
		pixel (x+(5*pixelSizeX), y+(15*pixelSizeY), 3, 1);
		
	}
	
	/*
	* Method Name: legs1
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: Drawing of first arrangement of the legs
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void legs1 (int x, int y) { //draws a leg of the ant
		 //I drew them separately, i know there must be an easier way with a for loop but it's too late I can't think
		 
		 //leg on the top left
		 pixel (x, y, 1, 1);
		 pixel (x+(1*pixelSizeX), y+(1*pixelSizeY), 1, 2);
		 pixel (x+(2*pixelSizeX), y+(3*pixelSizeY), 1, 1);
		 pixel (x+(3*pixelSizeX), y+(4*pixelSizeY), 1, 1);
		 
		//leg on the top right
		 pixel (x+(12*pixelSizeX),y, 1, 1);
		 pixel (x+(11*pixelSizeX), y+(1*pixelSizeY), 1, 2);
		 pixel (x+(10*pixelSizeX), y+(3*pixelSizeY), 1, 1);
		 pixel (x+(9*pixelSizeX), y+(4*pixelSizeY), 1, 1);
		 
		//leg on the bottom left
		 pixel (x, y+(13*pixelSizeX), 1, 1);
		 pixel (x+(1*pixelSizeX), y+(11*pixelSizeY), 1, 2);
		 pixel (x+(2*pixelSizeX), y+(10*pixelSizeY), 1, 1);
		 pixel (x+(3*pixelSizeX), y+(9*pixelSizeY), 1, 1);
		  
		 //leg on the bottom right
		 pixel (x+(12*pixelSizeX),y+(13*pixelSizeY), 1, 1);
		 pixel (x+(11*pixelSizeX), y+(11*pixelSizeY), 1, 2);
		 pixel (x+(10*pixelSizeX), y+(10*pixelSizeY), 1, 1);
		 pixel (x+(9*pixelSizeX), y+(9*pixelSizeY), 1, 1);
		 
		 //middle left
		 pixel (x-(1*pixelSizeX),y+(5*pixelSizeY), 2, 1);
	 	 pixel (x+(1*pixelSizeX), y+(6*pixelSizeY), 1, 1);
		 pixel (x+(2*pixelSizeX), y+(7*pixelSizeY), 1, 1);
		
	 	 //middle right
		 pixel (x+(12*pixelSizeX),y+(5*pixelSizeY), 2, 1);
		 pixel (x+(11*pixelSizeX), y+(6*pixelSizeY), 1, 1);
		 pixel (x+(10*pixelSizeX), y+(7*pixelSizeY), 1, 1);
	 }
	/*
	* Method Name: legsRight
	* Creation Date; November 14, 2023
	* Modified Date: November 15, 2023
	* Description: Right legs change but rest is the same
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void legsRight (int x, int y) { //draws part of the animation of the leg of the ant
		 
			//leg on the top left
			 pixel (x, y, 1, 1);
			 pixel (x+(1*pixelSizeX), y+(1*pixelSizeY), 1, 2);
			 pixel (x+(2*pixelSizeX), y+(3*pixelSizeY), 1, 1);
			 pixel (x+(3*pixelSizeX), y+(4*pixelSizeY), 1, 1);
			 
			//leg on the top right transformed
			 pixel (x+(11*pixelSizeX), y+(2*pixelSizeY), 1, 1);
			 pixel (x+(10*pixelSizeX), y+(3*pixelSizeY), 3, 1);
			 pixel (x+(9*pixelSizeX), y+(4*pixelSizeY), 1, 1); //closer to body
			 
			//leg on the bottom left
			 pixel (x, y+(13*pixelSizeX), 1, 1);
			 pixel (x+(1*pixelSizeX), y+(11*pixelSizeY), 1, 2);
			 pixel (x+(2*pixelSizeX), y+(10*pixelSizeY), 1, 1);
			 pixel (x+(3*pixelSizeX), y+(9*pixelSizeY), 1, 1);
			  
			 //leg on the bottom right transformed
			 pixel (x+(11*pixelSizeX),y+(11*pixelSizeY), 1, 1); 
			 pixel (x+(10*pixelSizeX), y+(10*pixelSizeY), 3, 1);
			 pixel (x+(9*pixelSizeX), y+(9*pixelSizeY), 1, 1);//kept the same as in the other one
			 
			//middle left
			 pixel (x-(1*pixelSizeX),y+(5*pixelSizeY), 2, 1);
			 pixel (x+(1*pixelSizeX), y+(6*pixelSizeY), 1, 1);
	  		 pixel (x+(2*pixelSizeX), y+(7*pixelSizeY), 1, 1);
			
			 //middle right transformed
			 pixel (x+(10*pixelSizeX), y+(7*pixelSizeY), 3, 1); //closer
		 }
	    /*
		* Method Name: legsLeft
		* Creation Date; November 14, 2023
		* Modified Date: November 15, 2023
		* Description: Left legs change but rest is the same
		* @Parameters: n/a
		* @Return Value: n/a
		* Data Type: void
		* Dependencies: n/a
		* Throws/Exceptions: n/a
		*/
		private void legsLeft (int x, int y) { //draws part of the animation of the leg of the ant
			//leg on the top left transformed
			 pixel (x+(1*pixelSizeX), y+(2*pixelSizeY), 1, 1);
			 pixel (x+(0*pixelSizeX), y+(3*pixelSizeY), 3, 1);
			 pixel (x+(3*pixelSizeX), y+(4*pixelSizeY), 1, 1); //closer to body
			 
			//leg on the top right
			 pixel (x+(12*pixelSizeX),y, 1, 1);
			 pixel (x+(11*pixelSizeX), y+(1*pixelSizeY), 1, 2);
			 pixel (x+(10*pixelSizeX), y+(3*pixelSizeY), 1, 1);
			 pixel (x+(9*pixelSizeX), y+(4*pixelSizeY), 1, 1);
			 
			//leg on the bottom left transformed
			 pixel (x+(1*pixelSizeX), y+(11*pixelSizeY), 1, 1); 
			 pixel (x+(0*pixelSizeX), y+(10*pixelSizeY), 3, 1);
			 pixel (x+(3*pixelSizeX), y+(9*pixelSizeY), 1, 1);//kept the same, closer to body
			  
			//leg on the bottom right
			 pixel (x+(12*pixelSizeX),y+(13*pixelSizeY), 1, 1);
			 pixel (x+(11*pixelSizeX), y+(11*pixelSizeY), 1, 2);
			 pixel (x+(10*pixelSizeX), y+(10*pixelSizeY), 1, 1);
			 pixel (x+(9*pixelSizeX), y+(9*pixelSizeY), 1, 1);
	
			 
			//middle left transformed
			pixel (x, y+(7*pixelSizeY), 3, 1); //closer
			
			//middle right
			pixel (x+(12*pixelSizeX),y+(5*pixelSizeY), 2, 1);
			pixel (x+(11*pixelSizeX), y+(6*pixelSizeY), 1, 1);
			pixel (x+(10*pixelSizeX), y+(7*pixelSizeY), 1, 1);
		 }
		/*
		* Method Name: pixel
		* Creation Date; November 14, 2023
		* Modified Date: November 15, 2023
		* Description: Creates a rectangle made out of determined number of "pixels"
		* @Parameters: int x, int y, int width, int height
		* @Return Value: n/a
		* Data Type: void
		* Dependencies: n/a
		* Throws/Exceptions: n/a
		*/
		private void pixel (int x, int y, int width, int height){// height and width in pixels 
		int x1 = x; //saves x value
		for (int i = 0; i < height; i++) {
			for (int j = 0; j< width; j++) {
				gc.setColor(Color.white);
				gc.fillRect(x- pixelSizeX, y-pixelSizeY, pixelSizeX, pixelSizeY); //draws the squares
				gc.setColor(Color.black);
				gc.drawRect(x- pixelSizeX, y-pixelSizeY, pixelSizeX, pixelSizeY); //draws black outline to distinguish between pixels
				x += pixelSizeX;
			}
			x = x1; //resets the value of x to start over
			y += pixelSizeY; //goes to "next line"
		}
			
	}
	/*
	* Method Name: initializeAnt
	* Creation Date; November 16, 2023
	* Modified Date: November 16, 2023
	* Description: initializes ant and measures how much time it works
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void initializeAnt () {
		long start = System.currentTimeMillis(); //current time
		long end = start + secAnt * 1000; //end of loop
		
		while (System.currentTimeMillis() < end) { //only functions for 30 seconds
			gc.clear();
			moveAnt();
			gc.sleep(10); //sleep 10 milliseconds before moving
		}
		gc.clear(); //clear to leave empty for next screen
	}
	
	//////////////////////******LINE PATTERN*****/////////////////
	/*
	* Method Name: initializePattern
	* Creation Date; November 16, 2023
	* Modified Date: November 16, 2023
	* Description: initializes third screen
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void initializePattern() {
		gc.setAntiAlias(true);
		gc.clear();
		draw1();
		draw2();
		gc.clear();
	}
	/*
	* Method Name: draw1
	* Creation Date; November 16, 2023
	* Modified Date: November 16, 2023
	* Description: horizontal lines are made
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void draw1 () {
		gc.setColor(colorPattern);
		int j = 0; //start of left
		int m = GRWIDTH; //start of right
		while (j<=GRWIDTH) { //keeps going until left reaches the end of the screen
			for (int i = 0; i<GRHEIGHT; i+=(widthRect*2)) { //copies the rectangle until the end of the screen
				gc.drawRect(j, i, 1, widthRect); //draws rectangle at left
				gc.drawRect(m, i+widthRect, 1, widthRect); //draws rectangle at right
			}
			j+=2; //speed of rectangle
			m-=2;
			gc.sleep(10); //not clearing the background because we want a continuous line, not a rectangle that moves
		}
	}
	/*
	* Method Name: draw2
	* Creation Date; November 16, 2023
	* Modified Date: November 16, 2023
	* Description: vertical lines are made
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: void
	* Dependencies: n/a
	* Throws/Exceptions: n/a
	*/
	private void draw2 () {
		gc.setColor(Color.black);
		int j = 0; //where on top start
		int m = GRHEIGHT; //where lines at the bottom start
		while (j<=GRHEIGHT) {
			for (int i = 0; i<GRWIDTH; i+=(widthRect*2)) { //keeps going until it reaches end of the screen
				gc.drawRect(i, j, widthRect, 1); //draws rectangle at the top
				gc.drawRect(i+widthRect, m, widthRect, 1); //draws rectangle at the bottom
			}
			j+=2; //speed of rectangle
			m-=2;
			gc.sleep(10); //sleeps 10 millisecond to see it move
		}
	}
}
