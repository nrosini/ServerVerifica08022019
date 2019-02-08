public class Bancomat {
    private static int numeroBanconote;                     //NUMERO DELLE BANCONOTE DISPONIBILI

    public Bancomat(int numeroBanconote) {
        Bancomat.numeroBanconote = numeroBanconote;
    }

    /**
     * Questo metodo controlla se il numero delle banconote che vuole prelevare il client è disponibile nel
     * bancomat
     * @param numeroBanconote numero delle banconote richieste dal client
     * @return restituisce un valore booleano che indica l'esito della transazione : true = transazione andata
     * a buon fine; false = transazione fallita.
     */
    public static boolean banconote(int numeroBanconote) {
        boolean saldoOk = true;
        Bancomat.numeroBanconote = Bancomat.numeroBanconote - numeroBanconote;
        if (Bancomat.numeroBanconote < 0) {
            saldoOk = false;
        }
        return saldoOk;
    }
}
