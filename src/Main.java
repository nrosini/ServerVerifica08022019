public class Main {
    public static void main(String[] args) {
        Server server = new Server(7520);
        Bancomat bancomat = new Bancomat(4);
        server.restituisciBanconote();
    }
}
