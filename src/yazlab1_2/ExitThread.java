//BERKAY AKPINAR 180201112 
package yazlab1_2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Berkay
 */
public class ExitThread implements Runnable{
    
    Random rand = new Random();
    int x;
    
    AsansorThread asansorThread ;
    AsansorHareket asansorHareket;
    
    public ExitThread() {
    }
    
    public ExitThread(AsansorThread asansorThread,AsansorHareket asansorHareket) {
        this.asansorThread=asansorThread;
        this.asansorHareket=asansorHareket;
    }

    @Override
    public void run() {
        while(true){
            x=rand.nextInt(5)+1;
            
            if(asansorHareket.floors.get(1)>10){
                for(int i=0;i<x;i++){
                     asansorHareket.foor1Queue.add("person");
                    
                }
                
               asansorHareket.floors.set(1, asansorHareket.floors.get(1)-x);
            
            }
            x=rand.nextInt(5)+1;
            if(asansorHareket.floors.get(2)>10){
                for(int i=0;i<x;i++){
                     asansorHareket.foor2Queue.add("person");
                }
               asansorHareket.floors.set(2, asansorHareket.floors.get(2)-x);
            
            }
            x=rand.nextInt(5)+1;
            if(asansorHareket.floors.get(3)>10){
                for(int i=0;i<x;i++){
                     asansorHareket.foor3Queue.add("person");
                }
               asansorHareket.floors.set(3, asansorHareket.floors.get(3)-x);
            
            }
            x=rand.nextInt(5)+1;
            if(asansorHareket.floors.get(4)>10){
                for(int i=0;i<x;i++){
                     asansorHareket.foor4Queue.add("person");
                }
               asansorHareket.floors.set(4, asansorHareket.floors.get(4)-x);
            
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExitThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       
        }
        
        
    }
    
    
   
    
}
