//BERKAY AKPINAR 180201112 

package yazlab1_2;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Berkay
 */
public class ControlThread implements Runnable {

    LoginThread loginThread;
    AsansorThread asansorThread;
    int k = 0;
 
ElevatorSimulator elevatorSimulator= new ElevatorSimulator();
    public ControlThread() {
    }

    public ControlThread(LoginThread loginThread, AsansorThread asansorThread) {
        this.loginThread = loginThread;
        this.asansorThread = asansorThread;
    

        
    }

    public int floorControl(int kat) {
        
        

        synchronized (asansorThread) {

            if (kat == 0) {
                if (loginThread.kuyruk.size() > 20) {
                    return 0;
                }
            } else if (kat == 1) {
                if (loginThread.kuyruk.size() > 20 || asansorThread.ah1.foor1Queue.size() > 20) {
                    return 1;
                }

            } else if (kat == 2) {
                if (loginThread.kuyruk.size() > 20 || asansorThread.ah1.foor2Queue.size() > 20) {
                    return 2;
                }

            } else if (kat == 3) {
                if (loginThread.kuyruk.size() > 20 || asansorThread.ah1.foor3Queue.size() > 20) {
                    return 3;
                }

            } else if (kat == 4) {
                if (loginThread.kuyruk.size() > 20 || asansorThread.ah1.foor4Queue.size() > 20) {
                    return 4;
                }

            } else {
                return -1;
            }
        }
        return -1;

    }
    
    

    @Override
    public void run() {

        while (true) {           
            
            ekran();


            for (int i = 0; i < 5; i++) {
                if (floorControl(i) != -1) {
                    System.out.println(floorControl(i));
                    for (int j = 0; j < 4; j++) {
                        if (asansorThread.getIsActive().get(j) == false) {
                           //aktiflik.set(j, true);
                            
                            asansorThread.getIsActive().set(j, true);

                            if (asansorThread.getIsActive().get(0) == true) {
                                asansorThread.ah2.call = floorControl(i);
                            } else if (asansorThread.getIsActive().get(1) == true) {
                                asansorThread.ah3.call = floorControl(i);
                            } else if (asansorThread.getIsActive().get(2) == true) {
                                asansorThread.ah4.call = floorControl(i);
                            } else if (asansorThread.getIsActive().get(3) == true) {
                                asansorThread.ah5.call = floorControl(i);
                            }
                            break;

                        }

                    }
                } 
                if (floorControl(i) == -1) {
                    for (int j = 0; j < 4; j++) {
                        if (asansorThread.getIsActive().get(j) == true) {
                            asansorThread.getIsActive().set(j, false);
                            //aktiflik.set(j, false);

                            if (asansorThread.getIsActive().get(0) == false) {
                                asansorThread.ah2.call = 0;
                            } else if (asansorThread.getIsActive().get(1) == false) {
                                asansorThread.ah3.call = 0;
                            } else if (asansorThread.getIsActive().get(2) == false) {
                                asansorThread.ah4.call = 0;
                            } else if (asansorThread.getIsActive().get(3) == false) {
                                asansorThread.ah5.call = 0;
                            }
                            break;

                        }
                    }
                }

            }

        }

    }
    
    public String getUpDown(int x){
        if(x==1){
            return "up";
        }else
            return "down";
    }
    
    
    public void ekran(){
        elevatorSimulator.setVisible(true);
      elevatorSimulator.getAa1().setText("["+asansorThread.ah1.ki??iSay??s??+","+asansorThread.ah1.hedefKat+"] ["+        
asansorThread.ah2.ki??iSay??s??+","+asansorThread.ah2.hedefKat+"] ["+asansorThread.ah3.ki??iSay??s??+","+asansorThread.ah3.hedefKat+"] ["
        +asansorThread.ah4.ki??iSay??s??+","+asansorThread.ah4.hedefKat+"] ["+asansorThread.ah5.ki??iSay??s??+","+asansorThread.ah5.hedefKat+"]");
        
        elevatorSimulator.getAa2().setText("["+asansorThread.ah1.foor1Queue.size()+",0]");
        elevatorSimulator.getAa3().setText("["+asansorThread.ah1.foor2Queue.size()+",0]");
        elevatorSimulator.getAa4().setText("["+asansorThread.ah1.foor3Queue.size()+",0]");
        elevatorSimulator.getAa5().setText("["+asansorThread.ah1.foor4Queue.size()+",0]");
        
        elevatorSimulator.getjLabel7().setText(""+loginThread.kuyruk.size());
        elevatorSimulator.getKat1().setText(""+asansorThread.floors.get(1));
        elevatorSimulator.getKat2().setText(""+asansorThread.floors.get(2));
        elevatorSimulator.getKat3().setText(""+asansorThread.floors.get(3));
        elevatorSimulator.getKat4().setText(""+asansorThread.floors.get(4));
        elevatorSimulator.getKat1queue().setText(""+asansorThread.ah1.foor1Queue.size());
        elevatorSimulator.getKat2queue().setText(""+asansorThread.ah1.foor2Queue.size());
        elevatorSimulator.getKat3queue().setText(""+asansorThread.ah1.foor3Queue.size());
        elevatorSimulator.getKat4queue().setText(""+asansorThread.ah1.foor4Queue.size());

        elevatorSimulator.getAsansor1anl??k().setText(""+asansorThread.ah1.bulunanKat);
        elevatorSimulator.getAsansor1hedef().setText(""+asansorThread.ah1.hedefKat);
        elevatorSimulator.getAsansor1Yukar??M??().setText(""+getUpDown(asansorThread.ah1.yukar??M??));
        elevatorSimulator.getAsansor1????erdeki().setText(""+asansorThread.ah1.asansordekiKi??iSay??s??);
        elevatorSimulator.getAsansor1hedefki??i().setText("["+asansorThread.ah1.asansordekiKi??iSay??s??+" , "+asansorThread.ah1.getHedefKat()+"]");
        

        elevatorSimulator.getAsansor2anl??k().setText(""+asansorThread.ah2.bulunanKat);
        elevatorSimulator.getAsansor2hedef().setText(""+asansorThread.ah2.hedefKat);
        elevatorSimulator.getAsansor2Yukar??M??().setText(""+getUpDown(asansorThread.ah2.yukar??M??));
        elevatorSimulator.getAsansor2????erdeki().setText(""+asansorThread.ah2.asansordekiKi??iSay??s??);
        elevatorSimulator.getAsansor2hedefki??i().setText("["+asansorThread.ah2.asansordekiKi??iSay??s??+" , "+asansorThread.ah2.getHedefKat()+"]");
        

        elevatorSimulator.getAsansor3anl??k().setText(""+asansorThread.ah3.bulunanKat);
        elevatorSimulator.getAsansor3hedef().setText(""+asansorThread.ah3.hedefKat);
        elevatorSimulator.getAsansor3Yukar??M??().setText(""+getUpDown(asansorThread.ah3.yukar??M??));
        elevatorSimulator.getAsansor3????erdeki().setText(""+asansorThread.ah3.asansordekiKi??iSay??s??);
        elevatorSimulator.getAsansor3hedefki??i().setText("["+asansorThread.ah3.asansordekiKi??iSay??s??+" , "+asansorThread.ah3.getHedefKat()+"]");
        

        elevatorSimulator.getAsansor4anl??k().setText(""+asansorThread.ah4.bulunanKat);
        elevatorSimulator.getAsansor4hedef().setText(""+asansorThread.ah4.hedefKat);
        elevatorSimulator.getAsansor4Yukar??M??().setText(""+getUpDown(asansorThread.ah4.yukar??M??));
        elevatorSimulator.getAsansor4????erdeki().setText(""+asansorThread.ah4.asansordekiKi??iSay??s??);
        elevatorSimulator.getAsansor4hedefki??i().setText("["+asansorThread.ah4.asansordekiKi??iSay??s??+" , "+asansorThread.ah4.getHedefKat()+"]");
        

        elevatorSimulator.getAsansor5anl??k().setText(""+asansorThread.ah5.bulunanKat);
        elevatorSimulator.getAsansor5hedef().setText(""+asansorThread.ah5.hedefKat);
        elevatorSimulator.getAsansor5Yukar??M??().setText(""+getUpDown(asansorThread.ah5.yukar??M??));
        elevatorSimulator.getAsansor5????erdeki().setText(""+asansorThread.ah5.asansordekiKi??iSay??s??);
        elevatorSimulator.getAsansor5hedefki??i().setText("["+asansorThread.ah5.asansordekiKi??iSay??s??+" , "+asansorThread.ah5.getHedefKat()+"]");
        elevatorSimulator.getC??k????Say??s??().setText(""+asansorThread.ah1.toplam????k????);
    }

}
