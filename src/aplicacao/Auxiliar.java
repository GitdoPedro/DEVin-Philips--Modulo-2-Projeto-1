package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Auxiliar {

    protected int identificador = 0;
    protected void imprimirMenu(String[] menu){
        for (String linha: menu) {
            System.out.println(linha.toString());
        }
    }

    protected List<String> preencheAtributoMultivalorado(String tipoAtributo){
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
                System.out.println("Item adicionado com sucesso!\n");
            }
        }
        System.out.println( tipoAtributo+ " adicionado(s)(as): "+ lista +"\n");
        return lista;
    }


    protected List<String> preencheConvenio(){
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

    protected String instituicaoEnsinoSuperior(String profissao, Scanner entrada) {
        System.out.println("O "+ profissao+ " se formou em qual Instituição de Ensino? ");

        return  entrada.next();
    }

    protected String registroProfissional(String registro, Scanner entrada){
        System.out.println("Digite o "+registro + "/UF do profissional: " );
        return  entrada.next();
    }

    protected void incrementaIdentificador(){
        this.identificador +=1;
    }

    public String selecionaStatusPaciente(boolean alteracao){
        String msgAlteracao = "Status de atendimento atualizado com sucesso!\n";
        Scanner entrada = new Scanner(System.in);
        String[] menuStatusAtendimento = {
                "============================================================",
                "Escolha uma das opções de status de atendimento do paciente",
                "1 - Aguardando Atendimento",
                "2 - Em Atendimento",
                "3 - Atendido",
                "4 - Não Atendido\n"
        };


        imprimirMenu(menuStatusAtendimento);
        int statusAtendimentoCod = entrada.nextInt();
        while (statusAtendimentoCod < 1 || statusAtendimentoCod > 4) {

            System.out.println("Opção inválida\n");
            imprimirMenu(menuStatusAtendimento);
            statusAtendimentoCod = entrada.nextInt();

        }
        if(alteracao){System.out.println(msgAlteracao);};
        switch (statusAtendimentoCod) {
            case 1:
                return "Aguardando Atendimento";
            case 2:
                return "Em Atendimento";
            case 3:
                return "Atendido";
            case 4:
                return "Não Atendido";
            default:
                return "opção inválida";

        }

    }

}
