//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGame implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public Image Background;
    public Image Background2;

    public BufferStrategy bufferStrategy;
    public Image astroPic;
    public Image luigipic;
    public Image bensonpic;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private Astro2 astro;
    private Astronaut luigi;
    public Astronaut ninja;
    public Astronaut benson;
    public int score;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGame ex = new BasicGame();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGame() {

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        Background = Toolkit.getDefaultToolkit().getImage("background2.png");//load the picture
        Background2 = Toolkit.getDefaultToolkit().getImage("background3.png");
        astroPic = Toolkit.getDefaultToolkit().getImage("mariopic.png");
        astro = new Astro2(10,100);
        luigi = new Astronaut(10,100, 0, 1);
        luigipic = Toolkit.getDefaultToolkit().getImage("luigipic.jpeg");
        ninja = new Astronaut(50, 40, 0,0);

        benson= new Astronaut (12,100, 2, 2);
        bensonpic = Toolkit.getDefaultToolkit().getImage("benson123.png");




    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {

            moveThings();  //move all the game objects
            checkIntersections();
            render();  // paint the graphics
            pause(1); // sleep for 10 ms
        }
    }


    public void moveThings()
    {
        //calls the move( ) code in the objects
        astro.move();
        luigi.move();
        benson.move();


    }
public void checkIntersections(){
if(astro.rec.intersects(luigi.rec)){
    astro.isAlive= false;
    System.out.println("INTERSECTED");
    score +=1;
}
}

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ){
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of the astronaut

        g.drawImage(Background, 0, 0, 1000, 700, null);
        g.drawImage(Background2, 0, 0, 1000, 700, null);

        if (astro.xpos>950) {
            astro.isAlive=true;
        }
        if (astro.xpos<5) {
            astro.isAlive=false;
        }

        if(astro.isAlive==true){
            g.drawImage(Background, 0, 0, 1000, 700, null);
        }
        if(astro.isAlive==false){
            g.drawImage(Background2, 0, 0, 1000, 700, null);
        }

        g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
        g.drawImage(luigipic, luigi.xpos, luigi.ypos, luigi.width, luigi.height, null);
        g.drawImage(bensonpic, benson.xpos, benson.ypos, benson.width, benson.height, null );
        g.setColor(Color.red);
        g.fillRect(195, 190, 70, 15);
        g.setColor(Color.black);
        g.drawString("Score:"+ score, 200, 200);

        g.dispose();

        bufferStrategy.show();
        }


}