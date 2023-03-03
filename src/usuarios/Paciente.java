package usuarios;
import aplicacao.*;

import java.util.ArrayList;
import java.util.List;

public final class Paciente extends Pessoa{
    private String contatoEmergencia;
    private List<String> alergias = new ArrayList<>();
    private List<String> cuidados = new ArrayList<>();
    private List<String> convenio = new ArrayList<>();
    private String statusAtendimento;
    private int contador;

    Auxiliar auxiliar = new Auxiliar();

    public Paciente(int id,
                    String nomeCompleto,
                    String genero,
                    String dataNascimento,
                    String CPF,
                    String telefone,
                    String contatoEmergencia,
                    String statusAtendimento)
    {
        super(id, nomeCompleto, genero, dataNascimento, CPF, telefone);
        this.contatoEmergencia = contatoEmergencia;
        this.statusAtendimento = statusAtendimento;
        this.contador = 0;
    }

    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public List<String> getAlergias() {
        return alergias;
    }

    public List<String> getCuidados() {
        return cuidados;
    }

    public List<String> getConvenio() {
        return convenio;
    }

    public String getStatusAtendimento() {
        return statusAtendimento;
    }

    public int getContador() {
        return contador;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    public void setCuidados(List<String> cuidados) {
        this.cuidados = cuidados;
    }

    public void setConvenio(List<String> convenio) {
        this.convenio = convenio;
    }

    public String toString(){
        StringBuilder exibir = new StringBuilder();
        exibir.append("Dados{")
                .append("id: ").append(super.getId())
                .append(" Nome: ").append(super.getNomeCompleto())
                .append(" Gênero: ").append(super.getGenero())
                .append(" Data de Nascimento: ").append(super.getDataNascimento())
                .append(" CPF: ").append(super.getCPF())
                .append(" Telefone ").append(super.getTelefone())
                .append(" Contato para Emergência: ").append(getContatoEmergencia())
                .append(" Status de Atendimento: ").append(getStatusAtendimento())
                .append(" Contador: ").append(getContador())
                .append(" Status de Atendimento: ").append(getStatusAtendimento());

        if (this.alergias != null && !this.alergias.isEmpty()) {
            exibir.append(" Lista de alergias: (").append(getAlergias()).append(")\n");
        }
        if (this.cuidados != null && !this.cuidados.isEmpty()) {
            exibir.append(" Lista de cuidados: (").append(getCuidados()).append(")\n");
        }

        if (this.convenio != null && !this.convenio.isEmpty()) {
            exibir.append(" Dados do convênio: (").append(getConvenio()).append(")\n");
        }

        exibir.append(("}"));
        return exibir.toString();
    }

    private void incrementaContador(){
        this.contador+=1;

    }

    private void atualizaStatusAtendimento(){
        this.statusAtendimento =  auxiliar.selecionaStatusPaciente(true);
    }

}

