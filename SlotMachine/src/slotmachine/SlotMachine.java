/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;

//imports
import java.awt.*;       // Using AWT's layouts
import java.awt.event.*; // Using AWT's event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import javax.swing.text.DefaultCaret;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



//public class
public class SlotMachine extends javax.swing.JFrame implements Runnable{

    // creates a private boolean that allows the the spinning to stop on click
    private boolean spinning = false;
    // Names for buttons textfield labels for the JFrame
    JButton addOne, statistics, betOne, betMaximum, reset, spinner;
    JTextField txfValue;
    JLabel lblreel1, lblreel2, lblreel3;
    JTextField creditArea, betArea, nuOfWins, nuOfLosses;
    Reel reel;
    // Variable names
    int credit = 10; 
    int betCred = 0;
    int maxbet = 0;
    int betcredit = 0;
    int newCredit;
    static int won = 0;
    static int lost = 0;
    static int displayCredits = 0;
    static int displayAverage = 0;
    static String saveme = "save me";  
    public SlotMachine(){
     
      Container cp = getContentPane();
      // set layout in the main panel
      cp.setLayout(new BorderLayout());
      
      //create a textField to show the value of the dice
    
     //creates a panel
     JPanel p1 = new JPanel();
     //assigns the lay out
     p1.setLayout(new GridLayout(1,3));
     // adds label and listener
     lblreel1 = new JLabel("");
     p1.add(lblreel1);
     lblreel1.addMouseListener(new stopReeling());
     lblreel2 = new JLabel("");
     p1.add(lblreel2);
     lblreel2.addMouseListener(new stopReeling());
     lblreel3 = new JLabel("");
     p1.add(lblreel3);
     lblreel3.addMouseListener(new stopReeling());
     //assigns to the center of the jframe
     cp.add(p1, BorderLayout.CENTER);
   
      
      //create a new panel
      JPanel p2 = new JPanel();
      // assigns new grid layout
      p2.setLayout(new GridLayout(1,2));
      //add area to input text in
      creditArea = new JTextField();
      p2.add(creditArea);
      betArea = new JTextField();
      p2.add(betArea);
      // assigns to the top of the jframe
      cp.add(p2, BorderLayout.NORTH);
      
 
      JPanel p3 = new JPanel();
      p3.setLayout(new GridLayout(2,3));
      
      //implements spin button
      spinner = new JButton("Spin");
      p3.add(spinner);
      //implements addOne Button and adds listener from the addCoin class
        addOne = new JButton("AddCoin");
      p3.add(addOne);
      addOne.addActionListener(new addCoins());
      
      //implements betOne button and adds listener from the betCoins class
      betOne = new JButton("betOne");
      p3.add(betOne);
      betOne.addActionListener(new betCoins());
      // implements betMaximum
      betMaximum = new JButton("bet Maximum");
      p3.add(betMaximum);
       betMaximum.addActionListener(new betMax());
      
      reset = new JButton("Reset");
      p3.add(reset);
      reset.addActionListener(new reset());
      
      statistics = new JButton("statistics");
      p3.add(statistics);
      statistics.addActionListener(new myStatistics());
      cp.add(p3, BorderLayout.SOUTH);
      
      
      // create a dice 
      reel = new Reel(); 
      
      
      Symbol firstFace = reel.spin();
      
      lblreel1.setIcon(firstFace.getSymbol());
       lblreel2.setIcon(firstFace.getSymbol());
       lblreel3.setIcon(firstFace.getSymbol());
       
      
      // add event handler to spin reel
      MyListener myListener = new MyListener();
      spinner.addActionListener(myListener);
      // sets text public SlotMachine class.
      creditArea.setText("Credit: " + credit);
     betArea.setText("Current bet: " + betCred);
        
     
    }
    
    
   // class which implements addCoins action listener
   class addCoins implements ActionListener{
    //performs action through method
    public void actionPerformed (ActionEvent event){
    
    credit = credit + 1;
    displayCredits = displayCredits + 1;
    creditArea.setText("Credit: " + credit); 
    
   
    
    }
    
    
    }
    class betCoins implements ActionListener {
 //performs action through method
        public void actionPerformed(ActionEvent event) {
// if statement which stops the buttons from working when bet is greater or the same as credit.
             if (betCred >= credit) {
                betArea.setText("Reset bet or add more coins!");
                betOne.setEnabled(false);
                betMaximum.setEnabled(false);
                spinner.setEnabled(false);
            }
// adds one everytime betCred button is clicked
            betCred = betCred + 1;
// set text for the betArea and displays text into the betArea
            betArea.setText("Current bet: " + betCred);
 //          
            creditArea.setText("Credit: " + credit);
            

        }
        
    }

    class betMax implements ActionListener {
 //performs action through method
        public void actionPerformed(ActionEvent event) {

            betCred = betCred + 3;
            betArea.setText("Current bet: " + betCred);
 
            betMaximum.setEnabled(false);
            
            if (betCred >= credit) {
        creditArea.setText("Credit: " + credit+ " - Add more to continue playing...");
        spinner.setEnabled(true);
                                   }

                                                       }
                                           }
    
    class reset implements ActionListener {
         //performs action through method
        public void actionPerformed(ActionEvent event) {
            betCred = 0;
            betArea.setText("Current bet: " + 0);
            spinner.setEnabled(true);
            betOne.setEnabled(true);
            betMaximum.setEnabled(true);
            
        }
    }
    
    
    private class MyListener implements ActionListener{
      //performs action through method
      public void actionPerformed(ActionEvent evt) {
        
           makeSpin();
       
      }
      }
    
    
    

    

        public void makeSpin(){

     Thread t1 = new Thread(this);
     Thread t2 = new Thread(this);
     Thread t3 = new Thread(this);

     if (!(t1.isAlive() && t2.isAlive() && t3.isAlive())){

     t1.start();
     t2.start();
     t3.start();

     }
     spinning = true;

     }
       
       @Override 
       public void run (){
       
           //catch errors
       try{
           //while loop, which runs while the spinning is activated
           while (spinning == true && spinning == true && spinning == true){
               Symbol firstReel = reel.spin();
          lblreel1.setIcon(firstReel.getSymbol());
        
          Symbol secondReel = reel.spin();
        lblreel2.setIcon(secondReel.getSymbol());
        
         Symbol thirdReel = reel.spin();
        lblreel3.setIcon(thirdReel.getSymbol());
           }
           
       } catch (UnsupportedOperationException e) {
           System.out.println("Error");
       }
       
       
       
       }
       
       //a public class with a mouse adapter
    public class stopReeling extends MouseAdapter{ 
        // a method tht has a mouse event
       public void mouseClicked(MouseEvent e){
           
           //condition
       if (e.getComponent().equals(lblreel1)){
           spinning = false;
       }
        //condition
       if (e.getComponent().equals(lblreel2)){
           spinning = false;
       }
        //condition
       if (e.getComponent().equals(lblreel3)){
           spinning = false;
       }
       // makes a new variable which is a combination of credit minus hte betCred
       // the value is when the betcredit is used to remove 
       // added amount from the credit
                newCredit = credit - betCred;
                credit = credit - betCred;
                creditArea.setText("Credit: " + credit);
                
           // sets the first symbol/reel
        Symbol firstReel = reel.spin();
           lblreel1.setIcon(firstReel.getSymbol());
        // sets the second symbol/reel
        Symbol secondReel = reel.spin();
        lblreel2.setIcon(secondReel.getSymbol());
        // sets the third symbol/reel
        Symbol thirdReel = reel.spin();
        lblreel3.setIcon(thirdReel.getSymbol());
           
       
      //condition
        if (firstReel != secondReel || firstReel != thirdReel || secondReel != thirdReel) {
                //sets the betCred with text
                betArea.setText("Sorry you lost! Bet: " + betCred);
                // adds to lost 
                lost = lost + 1;
            }
            
            //condition
            if (credit < betCred) {
                // if the credit is lower than the bet it wouldn't make sense 
                // to bet more, so i did this so the user doesn't go on overdraft
                    creditArea.setText("Credit: " + credit + " - Add more to continue playing...");
                    spinner.setEnabled(false);
                    
                }
            
            //condition if first reel and second reel are the same image displays win
            if (firstReel == secondReel) {
            betArea.setText("Congratulations! You matched 2 in a row! Bet was " + betCred);
            credit = newCredit + (betCred * (firstReel.getValue()));
            // since credit is won from a win, user recieves credit
            creditArea.setText("Credit: " + credit);
            // adds to matches won
           won = won + 1;
                
            
        
        }
            //  if 2 or more games won is false, adds to lost games.
            else if (false){
                lost = lost + 1;
            }
              //condition if second reel and third reel are the same image displays win
             if (secondReel == thirdReel) {
                 //display congrats message
            betArea.setText("Congratulations! You matched 2 in a row! Bet was " + betCred);
            credit = newCredit + (betCred * (secondReel.getValue()));
            
            creditArea.setText("Credit: " + credit);
             won = won + 1;
           
        
        }
              // if 2 or more games won is false, adds to lost games.
             else if (false){
                lost = lost + 1;
            }
             
             if (firstReel == thirdReel) {
                 //display congrats message
            betArea.setText("Congratulations! You matched 2 in a row! Bet was " + betCred);
            credit = newCredit + (betCred * (thirdReel.getValue()));
            // credits won
            creditArea.setText("Credit: " + credit);
            won = won + 1;
           
        
        }
             // if 2 or more games won is false, adds to lost games.
             else if (false){
                lost = lost + 1;
            }
            
            if (firstReel == secondReel && firstReel == thirdReel){
            betArea.setText("Congratulations! You matched 3 in a row! Bet was " + betCred);
            credit = newCredit + (betCred * (firstReel.getValue()));
            
            creditArea.setText("Credit: " + credit);
            won = won + 1;
                  
        }
            // if 2 or more games won is false, adds to lost games.
            else if (false){
                lost = lost + 1;
            }
           
            
            
            
        }
        }
    
    // main class
    public static void main(String[] args) {
        // Invoke the constructor by allocating an anonymous instance
        // adds frame
      SlotMachine myFrame = new SlotMachine();
      //design for the jframe
      
      // frame interface
      myFrame.setSize(700, 360);       // "super" Frame sets initial size
      myFrame.setTitle("Slot Machine");           // "super" Frame sets title
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.setVisible(true);                  // show "super" Frame
      
      
      
         
    
   
    
    
}
                            public static void statistics(){
        EventQueue.invokeLater(new Runnable()
        {
            //To let the user know that they're overriding a method or a parent class
            @Override
            public void run()
            {
                // new j frame
                JFrame frame = new JFrame("Stats");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try
                {
                    //manages User interface, the design
                 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());   
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //adds new j panel
                JPanel panel = new JPanel();
                // sets the layout of the panel
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                //set as not see through
                panel.setOpaque(true);
                // sets text area size
                JTextArea statistics = new JTextArea(15, 50);
                //style of text
                statistics.setWrapStyleWord(true);
                // make the text editabgle
                statistics.setEditable(false);
                // makes the text san serif
                statistics.setFont(Font.getFont(Font.SANS_SERIF));
                // adds scroller
                JScrollPane scroller = new JScrollPane(statistics);
                //vertical scroller
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                //horizontal scroller
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                
                JPanel panelforinput = new JPanel();
                panelforinput.setLayout(new FlowLayout());
                JTextField input = new JTextField(20);
                JButton save = new JButton("Save");
                
                
                DefaultCaret caret = (DefaultCaret) statistics.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
                panelforinput.add(save);
                panel.add(panelforinput);
              //sets a content pane to add data into, also sets location, makes visible and resizable.
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
                input.requestFocus();
                
                
                //For loop to display asterik for matches won
                StringBuilder wonAsterik = new StringBuilder();
                for (int w = 0; w < won; w++)
                    wonAsterik.append("*");
                
                //For loop to display asterik for matches lost
                StringBuilder asteriskLost = new StringBuilder();
                for (int l = 0; l < lost; l++)
                    asteriskLost.append("*");
                   
//gets text from statistic class
                String line1 = statistics.getText();
                //displays matches won
                line1 = "Won: " + won + " ";
                
                //display matches lost
                String line2 = statistics.getText();
                
                line2 = "Lost: " + lost + " ";
                
                //gets text from statistic and and creates new variable
                String histogramLine = statistics.getText();
                
                histogramLine = "Histograms" + " ";
                
                //gets text from statistic and and creates new variable
                String line3 = statistics.getText();
                
                line3 = "Won: " + wonAsterik + " = " + won + " ";
                
                //gets text from statistic and and creates new variable
                String line4 = statistics.getText();
                
                line4 = "Lost: " + asteriskLost + " = " + lost + " ";
                
                statistics.setText(line1 + "\n" + line2 + "\n" +  "\n" + histogramLine + "\n" + line3 + "\n" + line4); //line which shows all text in textArea
                
                //
               try {
                   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
                   Date date = new Date();
                   
                   PrintWriter out = new PrintWriter(new FileWriter(dateFormat.format(date)+ " Statistics.log", true));
                   out.println(statistics.getText());
                   out.close();
               }catch(Exception e){
                   
               }
                
            }
                    
                
                });
                }     
    //statistics class which impliments the listener for the statistics button
class myStatistics implements ActionListener{
        public void actionPerformed(ActionEvent event){
            statistics();
            
           
        }
    }
//this class implements the action listener for save my stats and adds the betOne button from the betCoins class
class savemystats implements ActionListener{
    public void actionPerformed(ActionEvent event){
         betOne.addActionListener(new betCoins());
    }
    
//saves file as a text
 public void location(String[] args)  {
     String string = "save me";          
     
     // contains a blcok of programm statements, in this case the filewriter and bufferedwriter
     try{
         BufferedWriter writer =
                 //area where the file txt is saved
                 new BufferedWriter (new FileWriter("\\STATS.txt"));
                     
         writer.write(string);
              
          }

       
      catch(Exception e){
       e.printStackTrace();
      }
                
            }
 }

     
}
    


