package br.com.lp2.dao;

import br.com.lp2.dto.BichoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BichoDAO {

    private final Connection connection;

    public BichoDAO() {
        this.connection = ConexaoDAO.getInstance().getConnection();
    }

    public boolean inserirBicho(BichoDTO bichoDTO) {
        String sql = "INSERT INTO bicho (nome_cli, bairro_cli, cidade_cli, estado_cli, cpf_cli, telefone_cli, nome_bicho, tipo_aposta, numero_cli, valor_aposta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, bichoDTO.getNome_cli());
            ps.setString(2, bichoDTO.getBairro_cli());
            ps.setString(3, bichoDTO.getCidade_cli());
            ps.setString(4, bichoDTO.getEstado_cli());
            ps.setString(5, bichoDTO.getCpf_cli());
            ps.setString(6, bichoDTO.getTelefone_cli());
            ps.setString(7, bichoDTO.getNome_bicho());
            ps.setString(8, bichoDTO.getTipo_aposta());
            ps.setInt(9, bichoDTO.getNumero_cli());
            ps.setFloat(10, bichoDTO.getValor_aposta());
            ps.executeUpdate();
            connection.commit();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inserir bicho: " + e.getMessage());
            try {
                connection.rollback();
            } catch (Exception rollbackEx) {
            }
            return false;
        }
    }

    public ResultSet consultarBicho(BichoDTO bichoDTO, int opcao) {
        String sql = "";
        switch (opcao) {
            case 1:
                sql = "SELECT * FROM bicho WHERE nome_cli ILIKE ? ORDER BY nome_cli";
                break;
            case 2:
                sql = "SELECT * FROM bicho WHERE id_cli = ?";
                break;
            case 3:
                sql = "SELECT id_cli, nome_cli FROM bicho";
                break;
            default:
                throw new IllegalArgumentException("Opção inválida para consulta");
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if (opcao == 1) {
                ps.setString(1, bichoDTO.getNome_cli() + "%");
            } else if (opcao == 2) {
                ps.setInt(1, bichoDTO.getId_cli());
            }
            return ps.executeQuery();
        } catch (Exception e) {
            System.err.println("Erro ao consultar bicho: " + e.getMessage());
            return null;
        }
    }

    public boolean alterarBicho(BichoDTO bichoDTO) {
        System.out.println("Dados para alteração: " + bichoDTO);

        String sql = "UPDATE bicho SET nome_cli = ?, bairro_cli = ?, cidade_cli = ?, estado_cli = ?, cpf_cli = ?, telefone_cli = ?, nome_bicho = ?, tipo_aposta = ?, numero_cli = ?, valor_aposta = ? WHERE id_cli = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, bichoDTO.getNome_cli());
            ps.setString(2, bichoDTO.getBairro_cli());
            ps.setString(3, bichoDTO.getCidade_cli());
            ps.setString(4, bichoDTO.getEstado_cli());
            ps.setString(5, bichoDTO.getCpf_cli());
            ps.setString(6, bichoDTO.getTelefone_cli());
            ps.setString(7, bichoDTO.getNome_bicho());
            ps.setString(8, bichoDTO.getTipo_aposta());
            ps.setInt(9, bichoDTO.getNumero_cli());
            ps.setFloat(10, bichoDTO.getValor_aposta());
            ps.setInt(11, bichoDTO.getId_cli());

            int rowsAffected = ps.executeUpdate();
            connection.commit();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println("Erro ao alterar bicho: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirBicho(BichoDTO bichoDTO) {
        String sql = "DELETE FROM bicho WHERE id_cli = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, bichoDTO.getId_cli());
            ps.executeUpdate();
            connection.commit();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir bicho: " + e.getMessage());
            try {
                connection.rollback();
            } catch (Exception rollbackEx) {
            }
            return false;
        }
    }
}
