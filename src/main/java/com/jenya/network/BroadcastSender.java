package com.jenya.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static com.jenya.Constants.BROADCAST;
import static com.jenya.Constants.UDP_RECEIVE_PORT;

public class BroadcastSender {

    public void sendData(BroadcastPocket pocket)throws Exception{
        InetAddress address = InetAddress.getByAddress(BROADCAST);
        DatagramPacket packet = new DatagramPacket(pocket.POCKET, pocket.POCKET.length, address, UDP_RECEIVE_PORT);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packet);
    }
}