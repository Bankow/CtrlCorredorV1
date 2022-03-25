package com.bcopstein.CtrlCorredorV1.Repositories;

import java.util.List;

import com.bcopstein.CtrlCorredorV1.Corredor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CorredorRepository implements ICorredorRepository {
    private JdbcTemplate dabaBase;

    public CorredorRepository(JdbcTemplate dabaBase) {
        this.dabaBase = dabaBase;
    }

    public void dropTable() {
        this.dabaBase.execute("DROP TABLE corredores IF EXISTS");
    }

    public void createTable() {
        this.dabaBase.execute("CREATE TABLE corredores("
                + "cpf VARCHAR(255), nome VARCHAR(255), genero VARCHAR(255), diaDn int, mesDn int, anoDn int, PRIMARY KEY(cpf))");
    }

    public void insertData() {
        this.dabaBase.batchUpdate("INSERT INTO corredores(cpf,nome,genero,diaDn,mesDn,anoDn) VALUES ('10001287','Luiz','masculino',22,5,1987)");
    }

    public List<Corredor> getAll() {
        return this.dabaBase.query("SELECT * from corredores",
                (rs, rowNum) -> new Corredor(rs.getString("cpf"), rs.getString("nome"), rs.getInt("diaDn"),
                        rs.getInt("mesDn"), rs.getInt("anoDn"), rs.getString("genero")));
    }

    public boolean createCorredor(Corredor corredor) {
        // Limpa a base de dados
        this.dabaBase.batchUpdate("DELETE from Corredores");
        // Então cadastra o novo "corredor único"
        this.dabaBase.update("INSERT INTO corredores(cpf,nome,diaDn,mesDn,anoDn,genero) VALUES (?,?,?,?,?,?)",
                corredor.getCpf(), corredor.getNome(), corredor.getDiaDn(), corredor.getMesDn(), corredor.getAnoDn(),
                corredor.getGenero());

        return true;
    }
}
