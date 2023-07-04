package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;


public class Program {

	public static void main(String[] args) {
		
		try {
        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            
            do {
            	System.out.println("####### Menu #######"
            						+ "\n1 - Cadastrar"
            						+ "\n2 - Listar"
            						+ "\n3 - Alterar"
            						+ "\n4 - Excluir"
            						+ "\n5 - Sair");
            	System.out.println("\n\tOpção:");
            	opcao = Integer.parseInt(console.nextLine());
            	
            	if (opcao == 1) {
            	
            		Aluno a = new Aluno();
            		AlunoJDBC acao = new AlunoJDBC();
            		
            		System.out.println("\n ### Cadastrar Aluno ### \n\r");
            		
            		System.out.print("Nome: ");
            		a.setNome(console.nextLine());
            		
            		System.out.print("Sexo: ");
            		a.setSexo(console.nextLine());
            	
            		System.out.print("Data de Nascimento (dd-mm-aaaa): ");
            		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            		a.setDt_nasc( LocalDate.parse(console.nextLine(), formato) );
            		
            		acao.salvar(a);
            		console.nextLine();
            		System.out.println("\n\n\n\n");
            	}
            	
            	if (opcao == 2) {
            	    System.out.println("\n ### Listar Alunos ### \n");
            	    AlunoJDBC acao = new AlunoJDBC();
            	    List<Aluno> alunos = acao.listar();

            	    for (Aluno aluno : alunos) {
            	        System.out.println("ID: " + aluno.getId());
            	        System.out.println("Nome: " + aluno.getNome());
            	        System.out.println("Sexo: " + aluno.getSexo());
            	        System.out.println("Data de Nascimento: " + aluno.getDt_nasc());
            	        System.out.println();
            	    }

            	    console.nextLine();
            	    System.out.println("\n\n\n\n");
            	}

            	if (opcao == 3) {
            	    System.out.println("\n ### Alterar Aluno ### \n");
            	    AlunoJDBC acao = new AlunoJDBC();

            	    System.out.print("Informe o ID do aluno que deseja alterar: ");
            	    int id = Integer.parseInt(console.nextLine());

            	    Aluno aluno = acao.buscarPorId(id);

            	    if (aluno != null) {
            	        System.out.print("Nome: ");
            	        aluno.setNome(console.nextLine());

            	        System.out.print("Sexo: ");
            	        aluno.setSexo(console.nextLine());

            	        System.out.print("Data de Nascimento (dd-mm-aaaa): ");
            	        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            	        aluno.setDt_nasc(LocalDate.parse(console.nextLine(), formato));

            	        acao.alterar(aluno);
            	        console.nextLine();
            	        System.out.println("\n\n\n\n");
            	    } else {
            	        System.out.println("Aluno não encontrado.");
            	        console.nextLine();
            	        System.out.println("\n\n\n\n");
            	    }
            	}

            	if (opcao == 4) {
            	    System.out.println("\n ### Excluir Aluno ### \n");
            	    AlunoJDBC acao = new AlunoJDBC();

            	    System.out.print("Informe o ID do aluno que deseja excluir: ");
            	    int id = Integer.parseInt(console.nextLine());

            	    Aluno aluno = acao.buscarPorId(id);

            	    if (aluno != null) {
            	        acao.excluir(id);
            	        console.nextLine();
            	        System.out.println("\n\n\n\n");
            	    } else {
            	        System.out.println("Aluno não encontrado.");
            	        console.nextLine();
            	        System.out.println("\n\n\n\n");
            	    }
            	}

            	
            } while(opcao != 5);
            
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
	} 
}
