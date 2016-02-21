class daemonThread implements Runnable{  
  public void run(){  
     System.out.println("[ info ] Daemon service started!");
     while(true) { //loop forever
         try {
             Thread.sleep(120000); //sleep for two minutes
             if(daemon.doDaemon() == true) {
                 //daemon worked fine
             } else {
                 System.out.println("[ error ] Daemon service failed to execute!");
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