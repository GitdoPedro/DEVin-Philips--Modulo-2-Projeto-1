package aplicacao;

import usuarios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Telas {

    Pessoa pessoa;
    Auxiliar auxiliar = new Auxiliar();


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

        String[] menuStatusAtendimento = {
                "============================================================",
                "Escolha uma das opções de status de atendimento do paciente",
                "1 - Aguardando Atendimento",
                "2 - Em Atendimento",
                "3 - Atendido",
                "4 - Não Atendido\n"
        };

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

        auxiliar.imprimirMenu(menuStatusAtendimento);
        statusAtendimentoCod = entradaPaciente.nextInt();
        while (statusAtendimentoCod < 1 || statusAtendimentoCod > 4) {

            System.out.println("Opção inválida\n");
            auxiliar.imprimirMenu(menuStatusAtendimento);
            statusAtendimentoCod = entradaPaciente.nextInt();

        }

        switch (statusAtendimentoCod) {
            case 1:
                statusAtendimento = "Aguardando Atendimento";
                break;
            case 2:
                statusAtendimento = "Em Atendimento";
                break;
            case 3:
                statusAtendimento = "Atendido";
                break;
            case 4:
                statusAtendimento = "Não Atendido";
                break;
            default:
                System.out.println("opção inválida");
        }

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
        System.out.println(((Paciente)this.pessoa).toString());
        this.inicio();


    }
    private void cadastroEnfermeiro () {

        Scanner entradaEnfermeiro = new Scanner(System.in);
        //cadastroPessoa("Enfermeiros");
        String ensinoSuperior, registro;

        ensinoSuperior = auxiliar.instituicaoEnsinoSuperior("enfermeiro",entradaEnfermeiro);
        registro = auxiliar.registroProfissional("COFEN",entradaEnfermeiro);

        System.out.println("Enfermeiro cadastrado com sucesso!\n");

        this.inicio();

    }


    private void cadastroMedico () {
        //cadastroPessoa("Médicos");

        Scanner entradaMedico = new Scanner(System.in);
        String ensinoSuperior, registro;
        int especializacaoClinica = 0; //padrão para entrar no while
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
        especializacaoClinica = entradaMedico.nextInt();
        while (especializacaoClinica <1 || especializacaoClinica > 8){

            System.out.println("Opção inválida\n");
            auxiliar.imprimirMenu(menuEspecializacaoClinica);
            especializacaoClinica = entradaMedico.nextInt();

        }

        System.out.println("O médico está ativo? [S/N]");

        while (!ativo){
            String convenio = entradaMedico.next().toUpperCase();
            if(convenio.equals("S")){
                ativo = true;
            }else if(convenio.equals("N")){
                ativo = false;
                break;
            }else{
                System.out.println("Valor incorreto. Favor preencher S ou N");

            }
        }

        System.out.println("Médico cadastrado com sucesso! \n");

        this.inicio();




    }

    private void atendimentoMedico () {

    }

    private void atualizacaoStatusPaciente () {

    }

    void relatorios () {

    }



}
