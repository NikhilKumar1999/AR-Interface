
/*
// A C# Program for Server 
using System; 
using System.Net; 
using System.Net.Sockets; 
using System.Text; 
  
namespace Server
{

    class Program
    {

        // Main Method 
        static void Main(string[] args)
        {
            ExecuteServer();
        }

        public static void ExecuteServer()
        {
            // Establish the local endpoint  
            // for the socket. Dns.GetHostName 
            // returns the name of the host  
            // running the application. 
            IPAddress ipAddr = IPAddress.Parse("192.168.0.102");
            IPEndPoint localEndPoint = new IPEndPoint(ipAddr, 11111);

            // Creation TCP/IP Socket using  
            // Socket Class Costructor 
            Socket listener = new Socket(ipAddr.AddressFamily,
                         SocketType.Stream, ProtocolType.Tcp);

            try
            {

                // Using Bind() method we associate a 
                // network address to the Server Socket 
                // All client that will connect to this  
                // Server Socket must know this network 
                // Address 
                listener.Bind(localEndPoint);

                // Using Listen() method we create  
                // the Client list that will want 
                // to connect to Server 
                listener.Listen(10);

                while (true)
                {

                    Console.WriteLine("Waiting connection ... ");

                    // Suspend while waiting for 
                    // incoming connection Using  
                    // Accept() method the server  
                    // will accept connection of client 
                    Socket clientSocket = listener.Accept();

                    // Data buffer 
                    byte[] bytes = new Byte[1024];
                    string data = null;

                    while (true)
                    {

                        int numByte = clientSocket.Receive(bytes);

                        data += Encoding.ASCII.GetString(bytes,
                                                   0, numByte);

                        if (data.IndexOf("<EOF>") > -1)
                            break;
                    }

                    Console.WriteLine("Text received -> {0} ", data);
                    byte[] message = Encoding.ASCII.GetBytes("Test Server");

                    // Send a message to Client  
                    // using Send() method 
                    clientSocket.Send(message);

                    // Close client Socket using the 
                    // Close() method. After closing, 
                    // we can use the closed Socket  
                    // for a new Client Connection 
                    clientSocket.Shutdown(SocketShutdown.Both);
                    clientSocket.Close();
                }
            }

            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
        }
    }
}*/

using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.IO.Ports;

namespace SocketBasedSerialCommunication
{
    class Program
    {
        static void Main(string[] args)
        {
            IPAddress addr = IPAddress.Parse("192.168.43.213");
            IPEndPoint end = new IPEndPoint(addr,10);

            Socket soc = new Socket(addr.AddressFamily, SocketType.Stream, ProtocolType.Tcp);
            try
            {
                soc.Bind(end);
            }catch(System.Net.Sockets.SocketException e)
            {
                Console.WriteLine(e.ErrorCode);
            }
            try
            {
                Socket newclient;
                soc.Listen(10);
                Console.WriteLine("Listening........");
                newclient = soc.Accept();
                Console.WriteLine("Accepted.........");
                while (true)
                {
                    int m;
                    int k = 0;
                    byte[] inter = new byte[4];
                    newclient.Receive(inter);
                    foreach (byte b in inter)
                    {
                        if (b > 48 && b < 57)
                        {
                            m = (int)b - 48;
                            k *= 10;
                            k += m;
                        }
                    }
                    Console.Write(k+"Message end\n");
                    if (k == 0)
                        break;
                }
                newclient.Shutdown(SocketShutdown.Both);
                newclient.Close();
            }
            catch(Exception e)
            {
                Console.WriteLine(e);
            }
        }
    }
}

