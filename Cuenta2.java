public class Cuenta2 {
private double saldo;
public void depositar(double monto) {
    saldo = saldo + monto;
}
public void retirar(double monto) {
    saldo = saldo - monto;
}
public void consultar() {
}
public double getSaldo() {
    return saldo;
}
}