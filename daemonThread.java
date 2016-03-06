class daemonThread implements Runnable{  
  public void run(){  
     if(jLinuxInfo.guiEnabled() == false) {
         System.out.println("[ info ] Daemon service started!");
        }
     while(true) { //loop forever
         try {
             Thread.sleep(120000); //sleep for two minutes
             if(daemon.doDaemon() == true) {
                 //daemon worked fine
             } else {
                 /* System.out.println("[ error ] Daemon service failed to execute!"); */
                 /* Don't show the warning as it annoys users that disable the feature */
             }
        } catch (InterruptedException ie) {
            System.out.println("[ error ] Daemon service crashed!");
            System.exit(1);
        }
     }
  }   
  public static void main(String args[]){  
     daemonThread dt = new daemonThread();  
     Thread dtt =new Thread(dt);  
     dtt.start();  
    }  
}