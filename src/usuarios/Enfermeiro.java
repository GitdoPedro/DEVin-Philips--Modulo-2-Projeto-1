package usuarios;

public final class Enfermeiro extends Pessoa{

    private String instituicaoEnsino;
    private String COFEN;

    public Enfermeiro(int id, String nomeCompleto, String genero, String dataNascimento, String CPF, String telefone, String instituicaoEnsino, String COFEN) {
        super(id, nomeCompleto, genero, dataNascimento, CPF, telefone);
        this.instituicaoEnsino = instituicaoEnsino;
        this.COFEN = COFEN;
    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public String getCOFEN() {
        return COFEN;
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
                .append(" COFEN/UF: ").append(getCOFEN());

        exibir.append(("}"));
        return exibir.toString();

    }
}
