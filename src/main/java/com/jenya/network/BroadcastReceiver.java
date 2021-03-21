package com.jenya.network;
import java.io.IOException;
import java.net.*;

import static com.jenya.Constants.UDP_RECEIVE_PORT;

public class BroadcastReceiver implements Runnable {

    public void run() {
        try (DatagramSocket serverSocket = new DatagramSocket(UDP_RECEIVE_PORT)) {
            BroadcastPocket receivePocket = new BroadcastPocket();
            System.out.printf("Listening on udp:%s:%d%n", InetAddress.getLocalHost().getHostAddress(), UDP_RECEIVE_PORT);
            DatagramPacket receivePacket = new DatagramPacket(receivePocket.POCKET, receivePocket.POCKET.length);
            while (true) {
                serverSocket.receive(receivePacket);
                System.out.println("RECEIVED: " + receivePocket.getName() + " (" + receivePocket.getChoosenX() + ", " + receivePocket.getChoosenY() +
                ") " + " (" + receivePocket.getPlaceX() + ", " + receivePocket.getPlaceY() +
                        ") ");
            }
        } catch (Throwable e) {
            System.out.println(e);
        }
    }

}