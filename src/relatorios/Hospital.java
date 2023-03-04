package relatorios;

import aplicacao.Auxiliar;
import usuarios.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hospital {

    Auxiliar auxiliar  = new Auxiliar();
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

    private String[] getInfoRelatorioPaciente(Paciente paciente, String convenio) {
        String[] dados = { String.valueOf(paciente.getId()), paciente.getNomeCompleto(), convenio, String.valueOf(paciente.getContador())};
        return dados;
    }

    private String[] getInfoRelatorioMedico(Medico medico, String especializacao) {
        String[] dados = { String.valueOf(medico.getId()), medico.getNomeCompleto(),medico.getInstituicaoEnsino(),medico.getCRM(), especializacao};
        return dados;
    }

    public void imprimirPessoas(List<String[]> pessoas) {
        for (String[] dado : pessoas) {
            System.out.printf("Id: %s, Nome: %s, CPF: %s\n", dado[0], dado[1], dado[2]);
        }
    }

    public void imprimirRelatorioPaciente(List<String[]> pacientes) {
        for (String[] dado : pacientes) {
            System.out.printf("Id: %s, Nome: %s, Convênio: %s, Total de atendimentos: %s\n", dado[0], dado[1], dado[2],dado[3]);
        }
    }

    public void imprimirRelatorioMedico(List<String[]> medicos) {
        for (String[] dado : medicos) {
            System.out.printf("Id: %s, Nome: %s, Instituição de Ensino: %s, CRM: %s, Especialização: %s\n", dado[0], dado[1], dado[2],dado[3],dado[4]);
        }
    }
    public void listarPessoas(String profissao){
        List<String[]> pessoas = new ArrayList<String[]>();
        switch (profissao) {
            case "Paciente":
                for (Paciente paciente : this.pacientes) {
                    pessoas.add(getInfoPessoa(paciente));
                }
                break;
            case "Enfermeiro":
                for (Enfermeiro enfermeiro : this.enfermeiros) {
                    pessoas.add(getInfoPessoa(enfermeiro));
                }
                break;
            case "Médico":
                for (Medico medico : this.medicos) {
                    pessoas.add(getInfoPessoa(medico));
                }
                break;
            case "Todos":
                for (Paciente paciente : this.pacientes) {
                    pessoas.add(getInfoPessoa(paciente));
                }
                for (Enfermeiro enfermeiro : this.enfermeiros) {
                    pessoas.add(getInfoPessoa(enfermeiro));
                }
                for (Medico medico : this.medicos) {
                    pessoas.add(getInfoPessoa(medico));
                }
                break;
            default:
                System.out.println("Opção inválida");
        }
        imprimirPessoas(pessoas);


    }

    public void relatorioPaciente(int status){
        List<String[]> pacienteRelatorio = new ArrayList<String[]>();
        switch (status) {
            case 1:
                for (Paciente paciente : this.pacientes) {
                    if (paciente.getStatusAtendimento().equals("Aguardando Atendimento")){
                        if (paciente.getConvenio() != null && !paciente.getConvenio().isEmpty()){
                            pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, paciente.getConvenio().get(0)));
                        }else{
                            pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, "NÃO POSSUI"));
                        }
                    }
                }
                break;

            case 2:
                for (Paciente paciente : this.pacientes) {
                    if (paciente.getStatusAtendimento().equals("Em Atendimento")){
                        if (paciente.getConvenio() != null && !paciente.getConvenio().isEmpty()){
                            pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, paciente.getConvenio().get(0)));
                        }else{
                            pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, "NÃO POSSUI"));
                        }
                    }
                }
                break;

            case 3:
                for (Paciente paciente : this.pacientes) {
                    if (paciente.getStatusAtendimento().equals("Atendido")){
                        if (paciente.getConvenio() != null && !paciente.getConvenio().isEmpty()){
                            pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, paciente.getConvenio().get(0)));
                        }else{
                            pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, "NÃO POSSUI"));
                        }
                    }
                }
                break;

            case 4:
                for (Paciente paciente : this.pacientes) {
                    if (paciente.getStatusAtendimento().equals("Não Atendido")){
                        if (paciente.getConvenio() != null && !paciente.getConvenio().isEmpty()){
                            pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, paciente.getConvenio().get(0)));
                        }else{
                            pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, "NÃO POSSUI"));
                        }
                    }
                }
                break;

            case 5:
                for (Paciente paciente : this.pacientes) {
                    if (paciente.getConvenio() != null && !paciente.getConvenio().isEmpty()){
                        pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, paciente.getConvenio().get(0)));
                    }else{
                        pacienteRelatorio.add(getInfoRelatorioPaciente(paciente, "NÃO POSSUI"));
                    }
                }
                break;


            default:
                System.out.println("Opção inválida");
        }
        imprimirRelatorioPaciente(pacienteRelatorio);


    }

    public void relatorioMedico(int especializacao){
        List<String[]> relatorioMedico = new ArrayList<String[]>();
        String nomeEspecializacao;
        switch (especializacao) {
            case 1:
                for (Medico medico : this.medicos) {
                    nomeEspecializacao = "Clínico Geral";
                    if(medico.getEspecializacaoClinica().equals(nomeEspecializacao)){
                        relatorioMedico.add(getInfoRelatorioMedico(medico,nomeEspecializacao));
                    }
                }
                break;
            case 2:
                for (Medico medico : this.medicos) {
                    nomeEspecializacao = "Anestesista";
                    if(medico.getEspecializacaoClinica().equals(nomeEspecializacao)){
                        relatorioMedico.add(getInfoRelatorioMedico(medico,nomeEspecializacao));
                    }
                }
                break;
            case 3:
                for (Medico medico : this.medicos) {
                    nomeEspecializacao = "Dermatologia";
                    if(medico.getEspecializacaoClinica().equals(nomeEspecializacao)){
                        relatorioMedico.add(getInfoRelatorioMedico(medico,nomeEspecializacao));
                    }
                }
                break;
            case 4:
                for (Medico medico : this.medicos) {
                    nomeEspecializacao = "Ginecologia";
                    if(medico.getEspecializacaoClinica().equals(nomeEspecializacao)){
                        relatorioMedico.add(getInfoRelatorioMedico(medico,nomeEspecializacao));
                    }
                }
                break;
            case 5:
                for (Medico medico : this.medicos) {
                    nomeEspecializacao = "Neurologia";
                    if(medico.getEspecializacaoClinica().equals(nomeEspecializacao)){
                        relatorioMedico.add(getInfoRelatorioMedico(medico,nomeEspecializacao));
                    }
                }
                break;
            case 6:
                for (Medico medico : this.medicos) {
                    nomeEspecializacao = "Pediatria";
                    if(medico.getEspecializacaoClinica().equals(nomeEspecializacao)){
                        relatorioMedico.add(getInfoRelatorioMedico(medico,nomeEspecializacao));
                    }
                }
                break;
            case 7:
                for (Medico medico : this.medicos) {
                    nomeEspecializacao = "Psiquiatria";
                    if(medico.getEspecializacaoClinica().equals(nomeEspecializacao)){
                        relatorioMedico.add(getInfoRelatorioMedico(medico,nomeEspecializacao));
                    }
                }
                break;
            case 8:
                for (Medico medico : this.medicos) {
                    nomeEspecializacao = "Ortopedia";
                    if(medico.getEspecializacaoClinica().equals(nomeEspecializacao)){
                        relatorioMedico.add(getInfoRelatorioMedico(medico,nomeEspecializacao));
                    }
                }
                break;
            case 9:
                for (Medico medico : this.medicos) {
                    relatorioMedico.add(getInfoRelatorioMedico(medico,medico.getEspecializacaoClinica()));
                    }
                break;


            default:
                System.out.println("Opção inválida");
                break;
        }
        imprimirRelatorioMedico(relatorioMedico);


    }

    public Paciente buscarPaciente (int id){
        for(Paciente paciente : this.pacientes){
            if (paciente.getId() == id){
                return paciente;
            }
        }
        return null;
    }



}
