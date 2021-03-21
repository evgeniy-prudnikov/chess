import com.jenya.network.BroadcastPocket;
import com.jenya.network.BroadcastReceiver;
import com.jenya.network.BroadcastSender;
import org.junit.jupiter.api.Test;

public class BroadcastTester {

    @Test
    public void sendCheck()throws Throwable{
        BroadcastPocket pocket = new BroadcastPocket();
        BroadcastPocket pocket1 = new BroadcastPocket();
        pocket.setName("Genya");
        pocket.setChoosen(3, 2);
        pocket.setPlace(4, 6);

        pocket1.setName("aaaa");
        pocket1.setChoosen(12, 9);
        pocket1.setPlace(4, 3);
        new Thread(new BroadcastReceiver()).start();
        BroadcastSender sender = new BroadcastSender();
        sender.sendData(pocket);
        sender.sendData(pocket1);
        Thread.sleep(5000L);
    }

}
