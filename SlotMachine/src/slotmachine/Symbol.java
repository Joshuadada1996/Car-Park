
package slotmachine;

import javax.swing.ImageIcon;
/**
 *
 * @author w1556165
 */
public class Symbol {
   private int value;
   private ImageIcon symbol;
   
   //Constructer 1
   
   public Symbol () {
       value = 0;
       symbol = null;   
   }
   
   public Symbol(int value, ImageIcon symbol) {
       this.value = value;
       this.symbol = symbol;
   }
   
   public void setValue(int value){
       this.value = value;
   }
   
   public void setSymbol(ImageIcon symbol) {
       this.symbol = symbol;
   }
   
   public int getValue(){
       return value;
   }
   
   public ImageIcon getSymbol(){
       return symbol;
   }
    
}
