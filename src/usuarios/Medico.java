package usuarios;

public final class Medico extends Pessoa{
    private String instituicaoEnsino;
    private String CRM;
    private String especializacaoClinica;
    private boolean estadoSistema;
    private int contador;

    public Medico(int id,
                  String nomeCompleto, String genero,
                  String dataNascimento, String CPF, String telefone,
                  String instituicaoEnsino, String CRM,
                  String especializacaoClinica, boolean estadoSistema
                  )
    {
        super(id, nomeCompleto, genero, dataNascimento, CPF, telefone);
        this.instituicaoEnsino = instituicaoEnsino;
        this.CRM = CRM;
        this.especializacaoClinica = especializacaoClinica;
        this.estadoSistema = estadoSistema;

    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public String getCRM() {
        return CRM;
    }

    public String getEspecializacaoClinica() {
        return especializacaoClinica;
    }

    public String getEstadoSistema() {
        if (estadoSistema){
            return "Ativo";
        }return "Inativo";
    }

    public int getContador() {
        return contador;
    }

    public String toString(){
        StringBuilder exibir = new StringBuilder();
        exibir.append("Dados{")
                .append("id: ").append(super.getId())
                .append(" Nome: ").append(super.getNomeCompleto())
                .append(" Gênero: ").append(super.getGenero())
                .append(" Data de Nascimento: ").append(super.getDataNascimento())
                .append(" CPF: ").append(super.getCPF())
                .append(" Telefone: ").append(super.getTelefone())
                .append(" Instituição de Ensino: ").append(getInstituicaoEnsino())
                .append(" CRM/UF: ").append(getCRM())
                .append(" Especialização Clínica: ").append(getEspecializacaoClinica())
                .append(" Estado no Sistema: ").append(getEstadoSistema());

        exibir.append(("}"));
        return exibir.toString();

    }


}
