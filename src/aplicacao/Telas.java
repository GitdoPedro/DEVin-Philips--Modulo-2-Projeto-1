package aplicacao;

import usuarios.*;
import relatorios.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Telas {

    Pessoa pessoa;
    Auxiliar auxiliar = new Auxiliar();
    Hospital hospital = new Hospital();


    public static void main(String[] args) {
        Telas tela = new Telas();
        tela.inicio();
    }

    void inicio()  {
        int opcao;

        try {
              opcao = menuInicial();
              switch (opcao){
                    case 1:
                        cadastroPaciente();
                        break;
                    case 2:
                        cadastroEnfermeiro();
                        break;
                    case 3:
                        cadastroMedico();
                        break;
                    case 4:
                        atendimentoMedico();
                        break;
                    case 5:
                        atualizacaoStatusPaciente();
                        break;
                    case 6:
                        relatorios();
                        break;
                  default:
                      System.out.println("Opção inexistente");
                        break;
            }
        } catch (Exception e) {
            System.out.println("Opção invalida! \n");
            inicio();
        }
    }

    private int menuInicial() throws Exception{
        Scanner entradaOpcao = new Scanner(System.in);
        String[] menuInicial = {
                "Bem vindo ao sistema LABMedicine",
                "=======================================================",
                "Selecione uma das opções abaixo",
                "1. Cadastro de Paciente",
                "2. Cadastro de Enfermeiro",
                "3. Cadastro de Médico",
                "4. Realização de Atendimento Médico",
                "5. Atualização do Status de Atendimento do Paciente",
                "6. Relatórios",
                "Número selecionado: "
        };

        auxiliar.imprimirMenu(menuInicial);

        int opcaoEntrada = entradaOpcao.nextInt();
        if (opcaoEntrada < 1 || opcaoEntrada > 6){
            throw new Exception();
        }else{
            return opcaoEntrada;
        }
    }

    private String[] cadastroPessoa(String funcao){

        Scanner entradaDados = new Scanner(System.in)        ;

        String [] cadastro = {
                "Cadastro de "+ funcao,
                "=======================================================",
                "Digite as informações pedidas abaixo: "
        };

        auxiliar.imprimirMenu(cadastro);
        String[] pessoa = new String[5];
        System.out.print("Nome completo: ");
        pessoa[0] = entradaDados.nextLine();

        System.out.print("Gênero: ");
        pessoa[1] = entradaDados.next();

        System.out.print("Data de Nascimento: ");
        pessoa[2] = entradaDados.next();

        System.out.print("CPF: ");
        pessoa[3] = entradaDados.next();

        System.out.print("Telefone: ");
        pessoa[4] = entradaDados.next();

        return pessoa;
    }

    private void cadastroPaciente() {
        String[] pessoaPaciente = cadastroPessoa("Pacientes");
        String contatoEmergiancia;
        boolean possuiAlergia = true;
        boolean possuiCuidados = true;
        boolean possuiConvenio = true;
        int statusAtendimentoCod = 0; //padrão para entrar no while
        String statusAtendimento = null;
        List<String> alergias = null;
        List<String> cuidadosEspeciais = null;
        List<String> convenios = null;
        Scanner entradaPaciente = new Scanner(System.in);

        System.out.println("Contato Para Emergencia: ");
        contatoEmergiancia = entradaPaciente.next();

        System.out.println("Possui alergias? [S/N]");

        while (possuiAlergia) {
            String alergia = entradaPaciente.next().toUpperCase();
            if (alergia.equals("S")) {
                alergias = new ArrayList<>(auxiliar.preencheAtributoMultivalorado("Alergia"));
                possuiAlergia = false;
            } else if (alergia.equals("N")) {
                possuiAlergia = false;
            } else {
                System.out.println("Valor incorreto. Favor preencher S ou N");

            }
        }

        System.out.println("Possui cuidados especiais? [S/N]");
        String cuidados = null;

        while (possuiCuidados) {
            cuidados = entradaPaciente.next().toUpperCase();
            if (cuidados.equals("S")) {
                cuidadosEspeciais = new ArrayList<>(auxiliar.preencheAtributoMultivalorado("cuidado especial"));
                possuiCuidados = false;
            } else if (cuidados.equals("N")) {
                possuiCuidados = false;
            } else {
                System.out.println("Valor incorreto. Favor preencher S ou N");

            }
        }

        System.out.println("Possui convênio? [S/N]");

        while (possuiConvenio) {
            String convenio = entradaPaciente.next().toUpperCase();
            if (convenio.equals("S")) {
                convenios = new ArrayList<>(auxiliar.preencheConvenio());
                possuiConvenio = false;
            } else if (convenio.equals("N")) {
                possuiConvenio = false;
            } else {
                System.out.println("Valor incorreto. Favor preencher S ou N");

            }


        }
        statusAtendimento = auxiliar.selecionaStatusPaciente(false);

        this.pessoa = new Paciente(
                auxiliar.identificador,
                pessoaPaciente[0],
                pessoaPaciente[1],
                pessoaPaciente[2],
                pessoaPaciente[3],
                pessoaPaciente[4],
                contatoEmergiancia,
                statusAtendimento
        );

        auxiliar.incrementaIdentificador();

        if (alergias != null && !alergias.isEmpty()) {
            ((Paciente)this.pessoa).setAlergias(alergias);
        }
        if (cuidadosEspeciais != null && !cuidadosEspeciais.isEmpty()) {
            ((Paciente)this.pessoa).setCuidados(cuidadosEspeciais);
        }

        if (convenios != null && !convenios.isEmpty()) {
            ((Paciente)this.pessoa).setConvenio(convenios);
        }

        System.out.println("Paciente cadastrado com sucesso!\n");
        hospital.addPacientes(((Paciente)this.pessoa));
        System.out.println(((Paciente)this.pessoa).toString());
        this.inicio();


    }
    private void cadastroEnfermeiro () {

        Scanner entradaEnfermeiro = new Scanner(System.in);
        String[] pessoaEnfermeiro = cadastroPessoa("Enfermeiro");
        String ensinoSuperior, registro;

        ensinoSuperior = auxiliar.instituicaoEnsinoSuperior("enfermeiro",entradaEnfermeiro);
        registro = auxiliar.registroProfissional("COFEN",entradaEnfermeiro);


        this.pessoa = new Enfermeiro(
                auxiliar.identificador,
                pessoaEnfermeiro[0],
                pessoaEnfermeiro[1],
                pessoaEnfermeiro[2],
                pessoaEnfermeiro[3],
                pessoaEnfermeiro[4],
                ensinoSuperior,
                registro
        );

        auxiliar.incrementaIdentificador();

        System.out.println("Enfermeiro cadastrado com sucesso!\n");
        hospital.addEnfermeiro(((Enfermeiro)this.pessoa));
        System.out.println(((Enfermeiro)this.pessoa).toString());


        this.inicio();

    }


    private void cadastroMedico () {
        String[] pessoaMedico = cadastroPessoa("Médico");

        Scanner entradaMedico = new Scanner(System.in);
        String ensinoSuperior, registro;
        int especializacaoClinicaCod = 0; //padrão para entrar no while
        String especializacaoClinica = null;
        boolean ativo = false;
        ensinoSuperior = auxiliar.instituicaoEnsinoSuperior("Medico",entradaMedico);
        registro = auxiliar.registroProfissional("CRM",entradaMedico);


        String [] menuEspecializacaoClinica = {
                "============================================================",
                "Escolha uma das opções de especialização clínica do médico: ",
                "1 - Clínico Geral",
                "2 - Em Atendimento",
                "3 - Atendido",
                "4 - Anestesista",
                "5 - Dermatologia",
                "6 - Ginecologia",
                "7 - Neurologia",
                "8 - Pediatria",
        };

        auxiliar.imprimirMenu(menuEspecializacaoClinica);
        especializacaoClinicaCod = entradaMedico.nextInt();
        while (especializacaoClinicaCod <1 || especializacaoClinicaCod > 8){

            System.out.println("Opção inválida\n");
            auxiliar.imprimirMenu(menuEspecializacaoClinica);
            especializacaoClinicaCod = entradaMedico.nextInt();

        }

        System.out.println("O médico está ativo? [S/N]");

        while (!ativo){
            String estadoSistema = entradaMedico.next().toUpperCase();
            if(estadoSistema.equals("S")){
                ativo = true;
            }else if(estadoSistema.equals("N")){
                ativo = false;
                break;
            }else{
                System.out.println("Valor incorreto. Favor preencher S ou N");

            }
        }

        switch (especializacaoClinicaCod) {
            case 1:
                especializacaoClinica = "Clínico Geral";
                break;
            case 2:
                especializacaoClinica = "Anestesista";
                break;
            case 3:
                especializacaoClinica = "Dermatologia";
                break;
            case 4:
                especializacaoClinica = "Ginecologia";
                break;
            case 5:
                especializacaoClinica = "Neurologia";
                break;
            case 6:
                especializacaoClinica = "Pediatria";
                break;
            case 7:
                especializacaoClinica = "Psiquiatria";
                break;
            case 8:
                especializacaoClinica = "Ortopedia";
                break;
            default:
                System.out.println("opção inválida");
        }

        this.pessoa = new Medico(
                auxiliar.identificador,
                pessoaMedico[0],
                pessoaMedico[1],
                pessoaMedico[2],
                pessoaMedico[3],
                pessoaMedico[4],
                ensinoSuperior,
                registro,
                especializacaoClinica,
                ativo


        );

        auxiliar.incrementaIdentificador();

        System.out.println("Medico cadastrado com sucesso!\n");
        hospital.addMedicos(((Medico)this.pessoa));
        System.out.println(((Medico)this.pessoa).toString());


        this.inicio();


    }

    private void atendimentoMedico () {

    }

    private void atualizacaoStatusPaciente () {

    }

    void relatorios () {
        int entradaRelatoriosCod = 0;
        Scanner entradaRelatorios = new Scanner(System.in);
        String [] relatorios = {
                "============================================================",
                "Escolha uma opção para impressão na tela: ",
                "1 - Listagem de Pessoas",
                "2 - Relatório dos Pacientes",
                "3 - Relatório dos Médicos"
        };

        auxiliar.imprimirMenu(relatorios);
        entradaRelatoriosCod = entradaRelatorios.nextInt();
        while (entradaRelatoriosCod <1 || entradaRelatoriosCod > 3){

            System.out.println("Opção inválida\n");
            auxiliar.imprimirMenu(relatorios);
            entradaRelatoriosCod = entradaRelatorios.nextInt();

        }

        switch (entradaRelatoriosCod) {
            case 1:
                listagemPessoas();
                break;
            case 2:
                relatorioPacientes();
                break;
            case 3:
                relatorioMedicos();
                break;

            default:
                System.out.println("opção inválida");
        }



    }

    private void listagemPessoas() {
        int entradaListagemPessoasCod = 0;
        Scanner entradaListagemPessoas = new Scanner(System.in);
        String [] litagemPessoas = {
                "============================================================",
                "Escolha uma opção para impressão na tela: ",
                "1 - Pacientes",
                "2 - Enfermeiros",
                "3 - Médicos",
                "4 - Todos"
        };

        auxiliar.imprimirMenu(litagemPessoas);
        entradaListagemPessoasCod = entradaListagemPessoas.nextInt();
        while (entradaListagemPessoasCod <1 || entradaListagemPessoasCod > 4){

            System.out.println("Opção inválida\n");
            auxiliar.imprimirMenu(litagemPessoas);
            entradaListagemPessoasCod = entradaListagemPessoas.nextInt();

        }

        switch (entradaListagemPessoasCod) {
            case 1:
                hospital.listarPessoas("Paciente");
                break;
            case 2:
                hospital.listarPessoas("Enfermeiro");
                break;
            case 3:
                hospital.listarPessoas("Médico");
                break;
            case 4:
                hospital.listarPessoas("Todos");
                break;

            default:
                System.out.println("opção inválida");
        }

        this.inicio();
    }

    private void relatorioPacientes() {

        int relatorioPacienteCod = 0;
        Scanner entradaRelatorios = new Scanner(System.in);
        String [] relatorios = {
                "============================================================",
                "Escolha uma opção para impressão na tela: ",
                "1 - Listagem de Pessoas",
                "2 - Relatório dos Pacientes",
                "3 - Relatório dos Médicos"
        };

        auxiliar.imprimirMenu(relatorios);
        relatorioPacienteCod = entradaRelatorios.nextInt();
        while (relatorioPacienteCod <1 || relatorioPacienteCod > 3){

            System.out.println("Opção inválida\n");
            auxiliar.imprimirMenu(relatorios);
            relatorioPacienteCod = entradaRelatorios.nextInt();

        }

        switch (relatorioPacienteCod) {
            case 1:
                listagemPessoas();
                break;
            case 2:
                relatorioPacientes();
                break;
            case 3:
                relatorioMedicos();
                break;

            default:
                System.out.println("opção inválida");
        }
    }

    private void relatorioMedicos() {
    }


}
