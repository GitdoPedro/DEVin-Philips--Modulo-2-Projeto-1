package relatorios;

import usuarios.*;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private List<Paciente>   pacientes   = new ArrayList<>();
    private List<Enfermeiro> enfermeiros = new ArrayList<>();
    private List<Medico>     medicos     = new ArrayList<>();


    public void addPacientes(Paciente paciente) {
        this.pacientes.add(paciente);
    }

    public void addEnfermeiro(Enfermeiro enfermeiro) {
        this.enfermeiros.add(enfermeiro);
    }

    public void addMedicos(Medico medico) {
        this.medicos.add(medico);
    }

    private String[] getInfoPessoa(Pessoa pessoa) {
        String[] dados = { String.valueOf(pessoa.getId()), pessoa.getNomeCompleto(), pessoa.getCPF()};
        return dados;
    }

    public void imprimirPessoas(List<String[]> pessoas) {
        for (String[] dado : pessoas) {
            System.out.printf("Id: %s, Nome: %s, CPF: %s\n", dado[0], dado[1], dado[2]);
        }
    }
    public void listarPessoas(String profissao){
        List<String[]> pessoas = new ArrayList<String[]>();
        switch (profissao) {
            case "Paciente":
                for (Paciente paciente : pacientes) {
                    pessoas.add(getInfoPessoa(paciente));
                }
                break;
            case "Enfermeiro":
                for (Enfermeiro enfermeiro : enfermeiros) {
                    pessoas.add(getInfoPessoa(enfermeiro));
                }
                break;
            case "Médico":
                for (Medico medico : medicos) {
                    pessoas.add(getInfoPessoa(medico));
                }
                break;
            case "Todos":
                for (Paciente paciente : pacientes) {
                    pessoas.add(getInfoPessoa(paciente));
                }
                for (Enfermeiro enfermeiro : enfermeiros) {
                    pessoas.add(getInfoPessoa(enfermeiro));
                }
                for (Medico medico : medicos) {
                    pessoas.add(getInfoPessoa(medico));
                }
                break;
            default:
                System.out.println("Opção inválida");
        }
        imprimirPessoas(pessoas);


    }

}
