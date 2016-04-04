/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UserTraping;

/**
 *
 * @author l
 */
import java.net.InetAddress;
import jpcap.packet.*;
import jpcap.*;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;
import java.util.Scanner;

class capture
{
        public static void main(String[] args) throws java.io.IOException{
        //Get the Device information - Start

            //Obtain the list of network interfaces
            NetworkInterface[] devices = JpcapCaptor.getDeviceList();

            //for each network interface
            for (int i = 0; i < devices.length; i++) {
              //print out its name and description
              System.out.println(i+": "+devices[i].name + "(" + devices[i].description+")");

              //print out its datalink name and description
              System.out.println(" datalink: "+devices[i].datalink_name + "(" + devices[i].datalink_description+")");

              //print out its MAC address
              System.out.print(" MAC address:");
              for (byte b : devices[i].mac_address)
                System.out.print(Integer.toHexString(b&0xff) + ":");
              System.out.println();

              //print out its IP address, subnet mask and broadcast address
              for (NetworkInterfaceAddress a : devices[i].addresses)
                System.out.println(" address:"+a.address + " " + a.subnet + " "+ a.broadcast);
            }
        //Get the Device information - End

//Capture the packets

                System.out.println("\n \n ");
                System.out.println("Please Enter the Device Name to Capture the Packet");
                Scanner in = new Scanner(System.in);
                int a = in.nextInt();
                if(a <= devices.length)
                {
                int index=a;  // set index of the interface that you want to open.

                //Open an interface with openDevice(NetworkInterface intrface, int snaplen, boolean promics, int to_ms)
                JpcapCaptor captor=JpcapCaptor.openDevice(devices[index], 65, true, 20);

               // JpcapCaptor captor=JpcapCaptor.openDevice(devices[index], index, true, index)
                //captor.setFilter("icmp",true);
               


                for(int i=0;i<50;i++){
                  //capture a single packet and print it out

                   // captor.processPacket(10,new PacketPrinter());

//captor.close();

                    System.out.println(captor.getPacket());
                 

                }

                }
                else
                System.out.println("Please Enter the correct value");
            }
}