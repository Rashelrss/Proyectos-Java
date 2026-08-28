public class CuentaAhorro extends Cuenta2 {
private double tasaInteres;
private double minSaldo;

public CuentaAhorro() {
    minSaldo = 0;
}
public void setTasaInteres(double interes) {
    tasaInteres = interes;
}
public void retirar(double monto) {
    super.retirar(monto);
    double saldo = getSaldo();
    if (saldo < minSaldo) {
        minSaldo = saldo;
    }
}
public void consultar() {
    double interes = minSaldo * tasaInteres / 100;
    depositar(interes);
    minSaldo = getSaldo();
}
}