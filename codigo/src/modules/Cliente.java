package modules;

public class Cliente{
    private String nome;
    private String cpf;
    private Veiculo[] veiculos;

    public Cliente(String p_nome, String p_cpf)
    {
        nome = p_nome;
        cpf = p_cpf;
        veiculos = new Veiculo[100];
    }

   public void addVeiculo(Veiculo p_veiculo)
   {
       for(int i = 0; i < veiculos.length; i++)
       {
           if(veiculos[i] == null)
           {
               veiculos[i] = p_veiculo;
               break;
           }
       }
   }

   public Veiculo possuiVeiculo(String p_placa)
   {
       for(int i = 0; i < veiculos.length; i++)
       {
           if(veiculos[i] != null)
           {
               if(veiculos[i].getPlaca().equals(p_placa))
               {
                   return veiculos[i];
               }
           }
       }
       return null;
   }

   public int totalDeUsos()
   {
       int totalArrecadado = 0;
       for(int i = 0; i < veiculos.length; i++)
       {
           if(veiculos[i] != null)
           {
               totalArrecadado += veiculos[i].totalDeUsos();
           }
       }
       return totalArrecadado;
   }

    public double arrecadadoPorVeiculo(String p_placa)
    {
        for(int i = 0; i > veiculos.length; i++)
        {
            if(veiculos[i].getPlaca() == p_placa)
                return veiculos[i].totalArrecado();
        }
        return 0;
    }

    public double arrecadadoTotal()
    {
        double totalArrecadado = 0;
        for(int i = 0; i < veiculos.length; i++)
        {
            if(veiculos[i] != null)
            {
                totalArrecadado += veiculos[i].totalArrecado();
            }
        }
        return totalArrecadado;
    }

    public double arrecadadoNoMes (int p_mes)
    {
        double total = 0;
        for(int i = 0; i < veiculos.length; i++)
        {
            if(veiculos[i] != null)
            {
                total += veiculos[i].arreacadoNoMes(p_mes);
            }
        }
        return total;
    }
}
