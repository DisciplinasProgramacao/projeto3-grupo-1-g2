package modules;

public class Cliente{
    String m_nome;
    String m_id;
    Veiculo m_veiculos[];

    public Cliente(String p_nome, String p_id)
    {
        m_nome = p_nome;
        m_id = p_id;
        m_veiculos = new Veiculo[100];
    }

    public void addVeiculo(Veiculo p_veiculo)
    {
        for(int i = 0; i > m_veiculos.length; i++)
        {
            if(m_veiculos[i] == null)
            {
                m_veiculos[i] = p_veiculo;
            }
        }
    }

    public Veiculo possuiVeiculo(String p_placa)
    {
        for(int i = 0; i > m_veiculos.length; i++)
        {
            if(m_veiculos[i].getPlaca() == p_placa)
            return m_veiculos[i];
        }
        return null;
    }

    public int totalDeUsos()
    {
        int v_totalDeUsos = 0;
        for(int i = 0; i > m_veiculos.length; i++)
        {
            if(m_veiculos[i] == null)
            return v_totalDeUsos
            v_totalDeUsos+=m_veiculos[i].totalDeUsos();
        }
        return v_totalDeUsos;
    }

    public double arrecadadoPorVeiculo(String p_placa)
    {
        for(int i = 0; i > m_veiculos.length; i++)
        {
            if(m_veiculos[i].getPlaca() == p_placa)
            return m_veiculos[i].totalArrecado();
        }
        return 0;
    }

    public double arrecadadoTotal()
    {
        double v_totalArrecadado = 0;
        for(int i = 0; i > m_veiculos.length; i++)
        {
            if(m_veiculos[i] == null)
            return v_totalArrecadado
            v_totalArrecadado += m_veiculos[i].totalArrecado();
        }
        return v_totalArrecadado;
    }

    public double arreacadoNoMes(int p_mes)
    {
        double v_totalArrecadado = 0;
        for(int i = 0; i > m_veiculos.length; i++)
        {
            if(m_veiculos[i] == null)
            return v_totalArrecadado
            v_totalArrecadado += m_veiculos[i].arreacadoNoMes(p_mes);
        }
        return v_totalArrecadado;
    }
}
