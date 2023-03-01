import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Telas {



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

        imprimirMenu(menuInicial);

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

        imprimirMenu(cadastro);
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
        String contatoEmergiancia;
        boolean possuiAlergia  = true;
        boolean possuiCuidados = true;
        boolean possuiConvenio = true;
        int statusAtendimento = 0; //padrão para entrar no while
        Scanner entradaPaciente = new Scanner(System.in);
        String [] menuStatusAtendimento = {
                "============================================================",
                "Escolha uma das opções de status de atendimento do paciente",
                "1 - Aguardando Atendimento",
                "2 - Em Atendimento",
                "3 - Atendido",
                "4 - Não Atendido\n"
        };
        //cadastroPessoa("Pacientes");
        System.out.println("Contato Para Emergencia: ");
        contatoEmergiancia  = entradaPaciente.next();

        System.out.println("Possui alergias? [S/N]");
        while (possuiAlergia){
            String alergia = entradaPaciente.next().toUpperCase();
            if(alergia.equals("S")){
                List<String> alergias = new ArrayList<>(preencheAtributoMultivalorado("Alergia"));
                possuiAlergia = false;
            }else if(alergia.equals("N")){
                possuiAlergia = false;
            }else{
                System.out.println("Valor incorreto. Favor preencher S ou N");

            }
        }

        System.out.println("Possui cuidados especiais? [S/N]");
        while (possuiCuidados){
            String cuidados = entradaPaciente.next().toUpperCase();
            if(cuidados.equals("S")){
                List<String> cuidadosEspeciais = new ArrayList<>(preencheAtributoMultivalorado("cuidado especial"));
                possuiCuidados = false;
            }else if(cuidados.equals("N")){
                possuiCuidados = false;
            }else{
                System.out.println("Valor incorreto. Favor preencher S ou N");

            }
        }

        System.out.println("Possui convênio? [S/N]");
        while (possuiConvenio){
            String convenio = entradaPaciente.next().toUpperCase();
            if(convenio.equals("S")){
                List<String> convenios = new ArrayList<>(preencheConvenio());
                possuiConvenio = false;
            }else if(convenio.equals("N")){
                possuiConvenio = false;
            }else{
                System.out.println("Valor incorreto. Favor preencher S ou N");

            }
        }



        imprimirMenu(menuStatusAtendimento);
        statusAtendimento = entradaPaciente.nextInt();
        while (statusAtendimento <1 || statusAtendimento > 4){

            System.out.println("Opção inválida\n");
            imprimirMenu(menuStatusAtendimento);
            statusAtendimento = entradaPaciente.nextInt();

        }

        System.out.println("Paciente cadastrado com sucesso!");




    }
    private void cadastroEnfermeiro () {

        Scanner entradaEnfermeiro = new Scanner(System.in);
        //cadastroPessoa("Enfermeiros");
        String ensinoSuperior, registro;

        ensinoSuperior = instituicaoEnsinoSuperior("enfermeiro",entradaEnfermeiro);
        registro = registroProfissional("COFEN",entradaEnfermeiro);

        System.out.println("Enfermeiro cadastrado com sucesso!\n");

    }



    private void cadastroMedico () {
        //cadastroPessoa("Médicos");

        Scanner entradaMedico = new Scanner(System.in);
        String ensinoSuperior, registro;
        int especializacaoClinica = 0; //padrão para entrar no while
        boolean ativo = false;
        ensinoSuperior = instituicaoEnsinoSuperior("Medico",entradaMedico);
        registro = registroProfissional("CRM",entradaMedico);


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

        imprimirMenu(menuEspecializacaoClinica);
        especializacaoClinica = entradaMedico.nextInt();
        while (especializacaoClinica <1 || especializacaoClinica > 8){

            System.out.println("Opção inválida\n");
            imprimirMenu(menuEspecializacaoClinica);
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




    }

    private void atendimentoMedico () {

    }

    private void atualizacaoStatusPaciente () {

    }

    void relatorios () {

    }

    private void imprimirMenu(String[] menu){
        for (String linha: menu) {
            System.out.println(linha.toString());
        }
    }

    private List<String> preencheAtributoMultivalorado(String tipoAtributo){
        List<String> lista = new ArrayList<>();
        Scanner entradaLista = new Scanner(System.in);
        String item = "";

        while (!item.equals("FIM")){

            if (tipoAtributo.equals("Alergia")){
                System.out.println("Adicione o nome da "+tipoAtributo+ ": ");

            }else{
                System.out.println("Adicione o nome do "+ tipoAtributo+ ": ");

            }
            item = entradaLista.next().toUpperCase();
            if(!item.equals("FIM")){
                lista.add(item);
                System.out.println("Item adicionado com sucesso!\n ");
            }
            }
        System.out.println( tipoAtributo+ "adicionado(s)(as): "+ lista +"\n");
        return lista;
    }


    private List<String> preencheConvenio(){
        List<String> listaConvenio = new ArrayList<>();
        Scanner entradaConvenio = new Scanner(System.in);
        String item = "";

        System.out.println("Nome do Convênio: ");
        item = entradaConvenio.next(); listaConvenio.add(item.toUpperCase());

        System.out.println("Número do Convênio: ");
        item = entradaConvenio.next(); listaConvenio.add(item);

        System.out.println("Validade do Convênio: ");
        item = entradaConvenio.next(); listaConvenio.add(item);

        System.out.println(
                "O convênio "+listaConvenio.get(0)+
                " com o número "+listaConvenio.get(1) +
                " e validade em " + listaConvenio.get(2) +
                " foi adicionado com sucesso!\n");

        return listaConvenio;
    }

    private String instituicaoEnsinoSuperior(String profissao, Scanner entrada) {
        System.out.println("O "+ profissao+ " se formou em qual Instituição de Ensino? ");

        return  entrada.next();
    }

    private String registroProfissional(String registro, Scanner entrada){
        System.out.println("Digite o "+registro + "/UF do profissional: " );
        return  entrada.next();
    }

}
