public class CuentaCorriente extends Cuenta2 {
private double retiros;
public CuentaCorriente() {
    retiros = 0;
}
public void retirar(double monto) {
    super.retirar(monto);
    retiros++;
    if (retiros > 3) {
        super.retirar(3.0);
    }
}
public void consultar() {
    retiros = 0;
}
}