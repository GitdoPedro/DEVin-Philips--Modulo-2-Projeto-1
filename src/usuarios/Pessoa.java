package usuarios;

public abstract class  Pessoa {


    private int id;
    private String nomeCompleto;
    private String genero;
    private String dataNascimento;
    private String CPF;
    private String telefone;

    public Pessoa(int id, String nomeCompleto, String genero, String dataNascimento, String CPF, String telefone){
        this.id = id ; this.nomeCompleto  = nomeCompleto ; this.genero = genero;
        this.dataNascimento = dataNascimento ; this.CPF = CPF ; this.telefone = telefone;

    }


    public int getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getGenero() {
        return genero;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTelefone() {
        return telefone;
    }


}
