
//BERKAY AKPINAR 180201112 


package yazlab1_2;

import java.util.Random;

/**
 *
 * @author Berkay
 */
public class Main {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LoginThread loginThread = new LoginThread();
        ControlThread controlThread= new ControlThread();
        AsansorThread asansorThread = new AsansorThread();
        AsansorHareket asansorHareket= new AsansorHareket();
        ExitThread exitThread = new ExitThread();
        
        Thread t1 = new Thread(new LoginThread(controlThread));
        
        Thread a1 = new Thread(new AsansorThread(loginThread,controlThread));
       
        Thread c = new Thread(new ControlThread(loginThread,asansorThread));
        
        Thread e1= new Thread(new ExitThread(asansorThread,asansorHareket));
        
        t1.start();
        c.start();
        e1.start();
        //a1.start();
         
        
        
        
        
        
                  
    }
    
}
