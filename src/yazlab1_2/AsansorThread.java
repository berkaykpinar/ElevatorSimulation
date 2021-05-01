//BERKAY AKPINAR 180201112 
package yazlab1_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Queue;

/**
 *
 * @author Berkay
 */
public class AsansorThread extends Thread{

    public int bulunanKat = 0;
    public int hedefKat = 2;
    public int yukarıMı0 = -1;
    public static boolean doluMu0 = false;
    int kişiSayısı0;

    public static ArrayList<Integer> floors = new ArrayList<Integer>(5);

    private static volatile ArrayList<Boolean> isActive = new ArrayList<>(4);
    
    

    LoginThread loginThread;
    ControlThread controlThread;
    AsansorHareket ah1 = new AsansorHareket(loginThread, controlThread, bulunanKat, hedefKat, yukarıMı0, doluMu0, floors);
    AsansorHareket ah2 = new AsansorHareket(loginThread, controlThread, bulunanKat, hedefKat, yukarıMı0, doluMu0, floors);
    AsansorHareket ah3 = new AsansorHareket(loginThread, controlThread, bulunanKat, hedefKat, yukarıMı0, doluMu0, floors);
    AsansorHareket ah4 = new AsansorHareket(loginThread, controlThread, bulunanKat, hedefKat, yukarıMı0, doluMu0, floors);
    AsansorHareket ah5 = new AsansorHareket(loginThread, controlThread, bulunanKat, hedefKat, yukarıMı0, doluMu0, floors);


    public AsansorThread() {
       

        isActive.add(false);
        isActive.add(false);
        isActive.add(false);
        isActive.add(false);
        
        asansor1.start();
        asansor2.start();
        asansor3.start();
        asansor4.start();
        asansor5.start();
        
        
        
        
    }
    
    public AsansorThread(LoginThread loginThread, ControlThread controlThread) {
        this.loginThread = loginThread;
        this.controlThread = controlThread;

    }
    
 


Thread asansor1 = new Thread(new Runnable()
     {
        @Override
        public void run() {
            while (true) {

                ah1.asansorler();
                //System.out.println(""+isActive.get(1).booleanValue());
            }

        }
    });


    Thread asansor2 = new Thread(new Runnable() {
        @Override
        public void run() {

            while (true) {
                if (getIsActive().get(0).booleanValue() == true) {
                    ah2.asansorler();

                }
            }

        }
    });     
      

    Thread asansor3 = new Thread(new Runnable() {
        @Override
        public void run() {

            while (true) {
                if (getIsActive().get(1).booleanValue() == true) {
                    ah3.asansorler();

                }
            }

        }
    });
    Thread asansor4 = new Thread(new Runnable() {
        @Override
        public void run() {
            
            while (true) {
                if (getIsActive().get(2).booleanValue() == true) {
                    ah4.asansorler();
                }
            }

        }
    });
    Thread asansor5 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (getIsActive().get(3).booleanValue() == true) {
                    ah5.asansorler();
                }
            }

        }
    });

    /**
     * @return the isActive
     */
    public static ArrayList<Boolean> getIsActive() {
        return isActive;
    }

}
