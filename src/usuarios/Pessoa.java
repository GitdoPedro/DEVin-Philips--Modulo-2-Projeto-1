package usuarios;

public abstract class  Pessoa {
    private String id;
    private String nomeCompleto;
    private String genero;
    private String dataNascimento;
    private String CPF;
    private String telefone;

    public Pessoa(String id, String nomeCompleto, String genero, String dataNascimento, String CPF, String telefone){
        this.id = id ; this.nomeCompleto  = nomeCompleto ; this.genero = genero;
        this.dataNascimento = dataNascimento ; this.CPF = CPF ; this.telefone = telefone;

    }


}
