import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        String nomeArq = "menuEstacionamento";
        int opcao = -1;
        while(opcao != 0){
            opcao = menu(nomeArq);
            switch(opcao){
                case 1-> pedidos.addElemento(menuPedido());
                case 2->{
                    limparTela();
                    double totalPedidos = pedidos.somadorGenerico(ped->ped.precoFinal());
                    System.out.println("Valor total dos pedidos: "+formatMoeda.format(totalPedidos));
                    System.out.println();
                    Pedido maisCaro = pedidos.maiorElemento( 
                            (p1,p2) -> p1.precoFinal()>p2.precoFinal()?1:-1 
                        );
                    System.out.println("Pedido de maior valor:");
                    System.out.println(maisCaro);
                    pausa();   
                }
            }
        }
        System.out.println("VLW FLW OBG T+.");
        teclado.close();
    }
}
