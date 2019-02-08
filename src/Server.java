import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;            //SERVER SOCKET PER L'ASCOLTO DEL SERVER
    private Socket socket;                  //SOCKET PER L'INVIO E LA RICEZIONE DEI DATI
    private final int porta;                //PORTA SULLA QUALE RIMANE IN ASCOLTO IL SERVER SOCKET

    public Server(int porta) {
        this.porta = porta;
    }

    /**
     * Questo metodo rimane in ascolto del client dal quale dovrà ricevere il numero delle banconote
     * che vuole prelevare.
     * @return restituisce il numero delle banconote richieste dal client.
     */
    private int riceviRichiesta(){
        int banconote = 0;
        try {
            this.server = new ServerSocket(this.porta);
            this.socket = this.server.accept();
            DataInputStream dIN = new DataInputStream(this.socket.getInputStream());
            banconote = dIN.readInt();
            this.socket.close();
        } catch (IOException erroreFlusso) {
            System.err.println("Errore del flusso in ingresso");
        }
        return banconote;
    }

    /**
     * Questo metodo restituisce al client l'esito della transazione, sia positivo che negativo.
     */
    public void restituisciBanconote() {
        boolean saldoOK = Bancomat.banconote(this.riceviRichiesta());
        try {
            this.socket = new Socket("localhost",1408);
            DataOutputStream dOut = new DataOutputStream(this.socket.getOutputStream());
            if (saldoOK) {
                dOut.writeUTF("Ecco le sue banconote");
                dOut.flush();
            } else {
                dOut.writeUTF("Ci dispiace il suo saldo non è sufficiente");
                dOut.flush();
            }
        } catch (IOException erroreFlusso) {
            System.err.println("Errore del flusso in uscita");
        }
    }
}
