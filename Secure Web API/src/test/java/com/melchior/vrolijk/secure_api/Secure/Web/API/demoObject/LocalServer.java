package com.melchior.vrolijk.secure_api.Secure.Web.API.demoObject;

import java.net.Socket;

public class LocalServer
{
    private static final String HOST = "https://localhost";
    private static final int PORT = 3000;

    public static boolean isRunning()
    {
        Socket socket = null;
        try{
            socket = new Socket(HOST,PORT);
            return true;
        }catch (Exception e)
        {
            return false;
        }finally {
            if (socket != null)
                try{
                    socket.close();
                }catch (Exception e) { }
        }
    }
}
