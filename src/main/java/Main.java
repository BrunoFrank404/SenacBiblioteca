import java.util.ArrayList;
import java.util.List;

import model.Livro;
import model.Usuario;

public class Main {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Livro> livros = new ArrayList<>();
    private static int proximoIdUsuario = 1;
    private static int proximoIdLivro = 1;

    public static void main(String[] args) {
        // cadastro de usuario
        cadastrarUsuario("Bruno Frank", "bruno@email.com", "senha123");
        cadastrarLivro("Dom Casmurro", "Machado de Assis");
        cadastrarLivro("O Cortiço", "Aluísio Azevedo");

        // Listar livros
        System.out.println("Livros disponíveis:");
        for (Livro livro : livros) {
            System.out.println(livro.getTitulo() + " - " + livro.getStatus());
        }
        // Testar empréstimo de livro
        System.out.println("\nTentando emprestar o livro com ID 1:");
        emprestarLivro(1);

        // Tentar emprestar o mesmo livro novamente (deve falhar)
        System.out.println("\nTentando emprestar o mesmo livro novamente:");
        emprestarLivro(1);

        // Devolver o livro
        System.out.println("\nDevolvendo o livro com ID 1:");
        devolverLivro(1);

        // Listar livros
        System.out.println("\nLivros após as operações:");
        for (Livro livro : livros) {
            System.out.println(livro.getTitulo() + " - " + livro.getStatus());
        }
    }

    // Método Cadastrar usuario
    public static void cadastrarUsuario(String nome, String email, String senha) {
        Usuario novoUsuario = new Usuario(proximoIdUsuario, nome, email, senha);
        usuarios.add(novoUsuario);
        proximoIdUsuario++;
        System.out.println("Usuário cadastrado: " + nome);
    }

    // Método para cadastrar livro
    public static void cadastrarLivro(String titulo, String autor) {
        Livro novoLivro = new Livro(proximoIdLivro, titulo, autor);
        livros.add(novoLivro);
        proximoIdLivro++;
        System.out.println("Livro cadastrado: " + titulo);
    }

    // Método para emprestar livro
    public static boolean emprestarLivro(int idLivro) {
        for (Livro livro : livros) {
            if (livro.getId() == idLivro) {
                if (livro.getStatus() == Livro.Status.DISPONIVEL) {
                    livro.setStatus(Livro.Status.EMPRESTADO);
                    System.out.println("Livro emprestado: " + livro.getTitulo());
                    return true;
                } else {
                    System.out.println("Livro não disponível para empréstimo: " + livro.getTitulo());
                    return false;
                }
            }
        }
        System.out.println("Livro não encontrado");
        return false;
    }

    // Método para devolver livro
    public static boolean devolverLivro(int idLivro) {
        for (Livro livro : livros) {
            if (livro.getId() == idLivro) {
                if (livro.getStatus() == Livro.Status.EMPRESTADO) {
                    livro.setStatus(Livro.Status.DISPONIVEL);
                    System.out.println("Livro devolvido: " + livro.getTitulo());
                    return true;
                } else {
                    System.out.println("Livro já está disponível: " + livro.getTitulo());
                    return false;
                }
            }
        }
        System.out.println("Livro não encontrado");
        return false;
    }


}