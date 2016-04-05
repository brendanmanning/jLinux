
/**
 * Writes to the log file all messages from the last 30 seconds, then waits 30 (in a loop)
 * 
 * @author (Brendan Manning) 
 * @version (March 28, 2016)
 */
public class logThread implements Runnable
{
    public void run() {
        while(true) { //loop forever 
            try {
                 utils.writeToFile(jLinuxInfo.logLocation(), log.getLog());
            } finally {
                log.clearLog();
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ie) {
                utils.writeToFile(jLinuxInfo.logLocation(), "***ERROR: ****\nLOG THREAD RAN TOO SOON");
            }
        }
    }
    public static void main(String[] args) {
        logThread lt = new logThread();
        Thread ltThread = new Thread(lt);
        ltThread.start();
    }
}
