
package slotmachine;

import java.util.*;
import javax.swing.*;

    public class Reel {
        private ArrayList<Symbol> theSymbol;
        
    
    
        public Reel(){
            Symbol bell = null;
            Symbol cherry = null;
            Symbol lemon = null;
            Symbol plum = null;
            Symbol redseven = null;
            Symbol watermelon = null;
            
           theSymbol = new ArrayList<Symbol>();
           
           try{
               bell = new Symbol(1, new ImageIcon(getClass().getResource("bell.png")));
           } catch(NullPointerException e) {
               System.out.println("The image bell cannot be found");
           }
               
           try{
               cherry = new Symbol(2, new ImageIcon(getClass().getResource("cherry.png")));
           } catch(NullPointerException e) {
               System.out.println("The image cherry cannot be found");
           }
           try{
               lemon = new Symbol(3, new ImageIcon(getClass().getResource("lemon.png")));
           } catch(NullPointerException e) {
               System.out.println("The image lemon cannot be found");
           }
           try{
               plum = new Symbol(4, new ImageIcon(getClass().getResource("plum.png")));
           } catch(NullPointerException e) {
               System.out.println("The image plumb cannot be found");
           }
           try{
              redseven = new Symbol(5, new ImageIcon(getClass().getResource("redseven.png")));
           } catch(NullPointerException e) {
               System.out.println("The image redseven cannot be found");
           }
           
           try{
              watermelon = new Symbol(6, new ImageIcon(getClass().getResource("watermelon.png")));
           } catch(NullPointerException e) {
               System.out.println("The image watermelon  cannot be found");
           }
           
           // adds the symbols to the array
           theSymbol.add(bell);
           theSymbol.add(cherry);
           theSymbol.add(lemon);
           theSymbol.add(plum);
           theSymbol.add(redseven);
           theSymbol.add(watermelon);
               
           }
        
            public Symbol spin(){
                // retrieve one of the symbols randomly
                Random rand = new Random();
                int randomSymb;
                randomSymb = rand.nextInt(theSymbol.size());
                
                return theSymbol.get(randomSymb);
                
            }
            
            
        }
       
        
    

