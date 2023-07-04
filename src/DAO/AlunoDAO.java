package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO() {
        // Inicializa a conexão com o banco de dados
        try {
        	conexao = DriverManager.getConnection(p.getProperty("host"), 
					  								p.getProperty("user"), 
					  								p.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();

        try {
            String query = "SELECT * FROM alunos";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");

                Aluno aluno = new Aluno(id, nome, idade);
                alunos.add(aluno);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    public void apagar(int id) {
        try {
            String query = "DELETE FROM alunos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Aluno a) {
        try {
            String query = "UPDATE alunos SET nome = ?, idade = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, a.getNome());
            statement.setInt(2, a.getIdade());
            statement.setInt(3, a.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

