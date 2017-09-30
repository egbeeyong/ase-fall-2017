/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1;

/**
 *
 * @author Egbe
 */
import static junit.framework.Assert.assertEquals;
import org.junit.*;
public class Calculator implements CalculatorIF {
    
    public static void main (String [] arg){
        Calculator test = new Calculator();
        int sumOf = test.divide(7, 3);
        int multi = test.divide(15,5);
        System.out.println(multi);
        
        // assert statements
        assertEquals("10 x 10 must be 100", 100, test.multiply(10, 10));
        assertEquals("12 + 10 must be 22", 22, test.sum(12, 10));
        assertEquals("13 - 8 must be 5", 5, test.substract(13, 8));
        assertEquals("15 / 5 must be 3", 3, test.divide(12, 4));
        
    }
   @Override
   public int sum(int m, int n){
       int result = m;
       for(int i = 1; i<=n; i++){
           result +=1;
       }
       return result;
   }
   @Override
   public int divide(int m, int n){
       
       int count = 0;
       while(m > 0){
                m -= n;
                count++;
        }
       if (m != 0)
           count -= 1;
       
       return count;
   }
    @Override
    public int multiply(int m, int n) {
        int result = 0;
        for (int i = 1; i<=n; i++){
            result += m;
        }
        return result;
    }

    @Override
    public int substract(int m, int n) {
       int result = m;
       for(int i = 1; i<=n; i++){
           result -=1;
       }
       return result;       
    }
}
