//BERKAY AKPINAR 180201112 
package yazlab1_2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Berkay
 */
public class LoginThread implements Runnable {

    int sayı = 0;
    int giren;
    int i = 0;
    Random rand = new Random();
    public static Queue kuyruk = new LinkedList();

    ControlThread controlThread;

    public LoginThread() {
    }

    public LoginThread(ControlThread controlThread) {
        this.controlThread = controlThread;
    }

    @Override
    public void run() {
        while (true) {
            
           
                giren = rand.nextInt(11);

                while (i < giren) {
                    kuyruk.add("person");
                    i++;
                }
                
                i=0;
               //System.out.println("Kuyruk uzunluğu "+kuyruk.size());
                


            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoginThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }



    }

    public void deQueue() {
        kuyruk.poll();
    }

}
