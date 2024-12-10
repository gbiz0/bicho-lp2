package br.com.lp2.ctr;

import br.com.lp2.dto.BichoDTO;
import br.com.lp2.repository.BichoRepository;
import java.sql.ResultSet;

public class BichoCTR {

    private final BichoRepository bichoRepository;

    public BichoCTR() {
        this.bichoRepository = new BichoRepository();
    }

    public String inserirBicho(BichoDTO bichoDTO) {
        if (bichoRepository.save(bichoDTO)) {
            return "Bicho cadastrado com sucesso!";
        } else {
            return "Erro ao cadastrar bicho.";
        }
    }

    public String alterarBicho(BichoDTO bichoDTO) {
        if (bichoRepository.update(bichoDTO)) {
            return "Bicho alterado com sucesso!";
        } else {
            return "Erro ao alterar bicho.";
        }
    }

    public String excluirBicho(BichoDTO bichoDTO) {
        if (bichoRepository.delete(bichoDTO)) {
            return "Bicho excluído com sucesso!";
        } else {
            return "Erro ao excluir bicho.";
        }
    }

    public ResultSet consultarPorNome(String nomeCli) {
        return bichoRepository.findByName(nomeCli);
    }

    public ResultSet consultarPorId(int id) {
        return bichoRepository.findById(id);
    }

    public ResultSet consultarTodos() {
        return bichoRepository.findAll();
    }

}
