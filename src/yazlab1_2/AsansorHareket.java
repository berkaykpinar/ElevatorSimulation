//BERKAY AKPINAR 180201112 
package yazlab1_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Berkay
 */
public class AsansorHareket {
    
    int bulunanKat;
    int hedefKat;
    int yukarıMı;
    private boolean doluMu;
    int kişiSayısı;
    public static int toplamÇıkış = 0;
    int asansordekiKişiSayısı=0;
    
    public int call;
    public static ArrayList<Integer> floors = new ArrayList<Integer>(5);
    
    public static Queue foor1Queue = new LinkedList();
    public static Queue foor2Queue = new LinkedList();
    public static Queue foor3Queue = new LinkedList();
    public static Queue foor4Queue = new LinkedList();
    
    Random rand = new Random();
    Random gidecekKişi = new Random();
    
    LoginThread loginThread;
    ControlThread controlThread;
    
    public AsansorHareket() {
    }
    
    public AsansorHareket(LoginThread loginThread, ControlThread controlThread, int bulunanKat, int hedefKat, int yukarıMı, boolean doluMu, ArrayList floors) {
        this.controlThread = controlThread;
        this.loginThread = loginThread;
        bulunanKat = 0;
        this.doluMu = doluMu;
        this.yukarıMı = yukarıMı;
        
        floors.add(0);
        floors.add(0);
        floors.add(0);
        floors.add(0);
        floors.add(0);
        call = -1;
        
        this.floors = floors;
    }
    
    public Queue floorControl(int kat) {
        if (kat == 1) {
            return foor1Queue;
        } else if (kat == 2) {
            return foor2Queue;
        } else if (kat == 3) {
            return foor3Queue;
        } else {
            return foor4Queue;
        }
        
    }
    
    public void asansorler() {
        
        if (call == 0) {
            while (getBulunanKat() > 0) {
                setBulunanKat(getBulunanKat() - 1);
                
                if (getBulunanKat() == 0) {
                    setYukarıMı(1);
                    setDoluMu(false);
      
                }
                
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AsansorThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            call = -1;
            
        } else if (call > 0) {
            while (getBulunanKat() < getHedefKat()) {
                setBulunanKat(getBulunanKat() + 1);
                
                if (getBulunanKat() == getHedefKat()) {
                    setYukarıMı(-1);
                    setDoluMu(false);
                    
                    if (floorControl(getBulunanKat()).size() > 0) {
                        if (floorControl(getBulunanKat()).size() > 10) {
                            for (int i = 0; i < 10; i++) {
                                floorControl(getBulunanKat()).poll();
                            }
                            floors.set(getBulunanKat(), floors.get(getBulunanKat()) - 10);
                            toplamÇıkış = toplamÇıkış + 10;
                            asansordekiKişiSayısı=10;
                        } else {
                            for (int i = 0; i < floorControl(getBulunanKat()).size(); i++) {
                                floorControl(getBulunanKat()).poll();
                            }
                            floors.set(getBulunanKat(), floors.get(getBulunanKat()) - floorControl(getBulunanKat()).size());
                            toplamÇıkış = toplamÇıkış + floorControl(getBulunanKat()).size();            
                            asansordekiKişiSayısı=floorControl(getBulunanKat()).size();
                            
                        }
                        setDoluMu(true);
                    }
                    
                    call = -1;

                }
                
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AsansorThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        }
        
        setHedefKat(rand.nextInt(4) + 1);

        if (getHedefKat() > 0 && getBulunanKat() == 0) {
            setYukarıMı(1);
            setDoluMu(false);
        }
        
        if (getYukarıMı() == 1 && isDoluMu() == false) {
            
            if (loginThread.kuyruk.size() < 10) {
                kişiSayısı = loginThread.kuyruk.size();
            } else {
                kişiSayısı = 10;
            }
            
            for (int i = 0; i < kişiSayısı; i++) {
                loginThread.kuyruk.poll();
            }
            //System.out.println("Asansöre bindiler");
            asansordekiKişiSayısı=kişiSayısı;
            while (getBulunanKat() < getHedefKat()) {
                //System.out.println("Asansör yukarı çıkıyor " + getBulunanKat());
                setBulunanKat(getBulunanKat() + 1);
                
                if (getBulunanKat() == getHedefKat()) {
                    setYukarıMı(-1);
                    setDoluMu(false);

                    floors.set(getBulunanKat(), floors.get(getBulunanKat()) + kişiSayısı);  
                    asansordekiKişiSayısı=0;
                    
                    //System.out.println("Asansör kata ulaştı:" + getBulunanKat() + " katlar: k1:" + floors.get(1) + " k2:" + floors.get(2) + " k3:" + floors.get(3) + " k4:" + floors.get(4));
                    
                    if (floorControl(getBulunanKat()).size() > 0) {
                        if (floorControl(getBulunanKat()).size() > 10) {
                            for (int i = 0; i < 10; i++) {
                                floorControl(getBulunanKat()).poll();
                            }
                            floors.set(getBulunanKat(), floors.get(getBulunanKat()) - 10);
                            toplamÇıkış = toplamÇıkış + 10;
                            asansordekiKişiSayısı=10;
                        } else {
                            for (int i = 0; i < floorControl(getBulunanKat()).size(); i++) {
                                floorControl(getBulunanKat()).poll();
                            }
                            floors.set(getBulunanKat(), floors.get(getBulunanKat()) - floorControl(getBulunanKat()).size());
                            toplamÇıkış = toplamÇıkış + floorControl(getBulunanKat()).size();
                            asansordekiKişiSayısı=floorControl(getBulunanKat()).size();
                            
                        }
                        setDoluMu(true);
                        setYukarıMı(-1);
                    }
                    
                }
                
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AsansorThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        if (getYukarıMı() == -1 && isDoluMu() == false) {
            while (getBulunanKat() > 0) {
                // System.out.println("Asansör aşağı iniyor katı " + getBulunanKat());
                setBulunanKat(getBulunanKat() - 1);
                
                if (getBulunanKat() == 0) {
                    setYukarıMı(1);
                    setDoluMu(false);

                }
                
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AsansorThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        if (getYukarıMı() == -1 && isDoluMu() == true) {
            while (getBulunanKat() > 0) {
                // System.out.println("Asansör aşağı iniyor katı " + getBulunanKat());
                setBulunanKat(getBulunanKat() - 1);
                
                if (getBulunanKat() == 0) {
                    setYukarıMı(1);
                    setDoluMu(false);
                    
                    asansordekiKişiSayısı=0;

                }
                
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AsansorThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }

        //System.out.println("YukarıMı:" + getYukarıMı() + " doluMNu:" + isDoluMu());
    }

    /**
     * @return the bulunanKat
     */
    public int getBulunanKat() {
        return bulunanKat;
    }

    /**
     * @param bulunanKat the bulunanKat to set
     */
    public void setBulunanKat(int bulunanKat) {
        this.bulunanKat = bulunanKat;
    }

    /**
     * @return the hedefKat
     */
    public int getHedefKat() {
        return hedefKat;
    }

    /**
     * @param hedefKat the hedefKat to set
     */
    public void setHedefKat(int hedefKat) {
        this.hedefKat = hedefKat;
    }

    /**
     * @return the yukarıMı
     */
    public int getYukarıMı() {
        return yukarıMı;
    }

    /**
     * @param yukarıMı the yukarıMı to set
     */
    public void setYukarıMı(int yukarıMı) {
        this.yukarıMı = yukarıMı;
    }

    /**
     * @return the doluMu
     */
    public boolean isDoluMu() {
        return doluMu;
    }

    /**
     * @param doluMu the doluMu to set
     */
    public void setDoluMu(boolean doluMu) {
        this.doluMu = doluMu;
    }
    
}
