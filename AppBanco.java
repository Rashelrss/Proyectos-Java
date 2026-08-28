import java.util.Scanner;
public class AppBanco {
public static void main(String[] args) {
    Cuenta2[] cuentas = new Cuenta2[10];
    for (int i = 0; i < 5; i++) {
        cuentas[i] = new CuentaAhorro();
    }
    for (int i = 5; i < 10; i++) {
        cuentas[i] = new CuentaCorriente();
    }
    Scanner in = new Scanner(System.in);
    boolean done = false;
    while (!done) {
        System.out.print("D)EPOSITAR R)ETIRAR C)ONSULTAR S)ALIR: ");
        String op = in.next();
        if (op.equals("D") || op.equals("R")) {
            System.out.print("INGRESE NUMERO DE CUENTA Y MONTO: ");
            int num = in.nextInt();
            double monto = in.nextDouble();
            if (op.equals("D")) {
                cuentas[num].depositar(monto);
            } else {
                cuentas[num].retirar(monto);
            }
            System.out.println("SALDO: " + cuentas[num].getSaldo());
        }
        else if (op.equals("C")) {
            for (int n = 0; n < cuentas.length; n++) {
                cuentas[n].consultar();
                if (cuentas[n] instanceof CuentaAhorro){
                    System.out.println(n + "CUENTA AHORRO: " + cuentas[n].getSaldo());
                }else{
                    System.out.println(n + "CUENTA CORRIENTE:  " + cuentas[n].getSaldo());
                }
                
            }
        }
        else if (op.equals("S")) {
            done = true;
        }
    }
}
}