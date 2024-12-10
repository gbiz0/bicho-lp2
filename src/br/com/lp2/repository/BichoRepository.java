package br.com.lp2.repository;

import br.com.lp2.dao.BichoDAO;
import br.com.lp2.dto.BichoDTO;
import java.sql.ResultSet;

public class BichoRepository {

    private final BichoDAO bichoDAO;

    public BichoRepository() {
        this.bichoDAO = new BichoDAO();
    }

    public boolean save(BichoDTO bichoDTO) {
        return bichoDAO.inserirBicho(bichoDTO);
    }

    public boolean update(BichoDTO bichoDTO) {
        return bichoDAO.alterarBicho(bichoDTO);
    }

    public boolean delete(BichoDTO bichoDTO) {
        return bichoDAO.excluirBicho(bichoDTO);
    }

    public ResultSet findByName(String name) {
        BichoDTO bichoDTO = new BichoDTO();
        bichoDTO.setNome_cli(name);
        return bichoDAO.consultarBicho(bichoDTO, 1);
    }

    public ResultSet findById(int id) {
        BichoDTO bichoDTO = new BichoDTO();
        bichoDTO.setId_cli(id);
        return bichoDAO.consultarBicho(bichoDTO, 2);
    }

    public ResultSet findAll() {
        return bichoDAO.consultarBicho(new BichoDTO(), 3);
    }

}
