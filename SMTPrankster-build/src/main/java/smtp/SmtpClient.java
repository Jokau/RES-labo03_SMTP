package smtp;

import model.mail.Mail;

import java.io.*;
import java.net.Socket;

public class SmtpClient implements ISmtpClient {

    Socket clientSocket;
    BufferedReader reader;
    PrintWriter writer;

    /**
     * Create a connection with the smtp server.
     * Create the input and outputstream of this connection
     * Start a session by sending 'EHLO local'
     * @param address
     * @param port
     * @throws IOException
     */
    public SmtpClient(String address, int port) throws IOException {

        clientSocket = new Socket(address, port);

        OutputStream os = clientSocket.getOutputStream();
        InputStream is = clientSocket.getInputStream();

        reader = new BufferedReader(new InputStreamReader(is));
        writer = new PrintWriter(new OutputStreamWriter(os));

        String rcv = reader.readLine();
        System.out.println(rcv);

        writer.println("EHLO local");
        writer.flush();

        while( (rcv = reader.readLine()).charAt(3) != ' ' ) {
            System.out.println(rcv);
        }
        System.out.println(rcv);
    }

    /**
     * Send a mail if a session is active
     * @param mail
     */
    public void sendMail(Mail mail) throws IOException{

        String smtpFrom = "MAIL FROM: " + "prankster@prank.com";
        String smtpTo = "RCPT TO: " + mail.getReceiver().getEmailAddress();
        String smtpData = "DATA";
        String data = mail.getContent();

        writer.println(smtpFrom);
        writer.flush();
        reader.readLine();

        writer.println(smtpTo);
        writer.flush();
        reader.readLine();

        writer.println(smtpData);
        writer.flush();
        reader.readLine();

        writer.println(data);
        writer.flush();

        writer.println(".");
        writer.flush();

        reader.readLine();

    }

    public void close() {

        //TODO: send quit?

        if(writer != null){
            writer.close();
        }
        if(reader != null){
            try{
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(clientSocket != null){
            try{
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
