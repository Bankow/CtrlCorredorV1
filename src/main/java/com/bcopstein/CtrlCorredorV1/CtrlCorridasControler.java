package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import com.bcopstein.CtrlCorredorV1.Repositories.CorredorRepository;
import com.bcopstein.CtrlCorredorV1.Repositories.ICorredorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctrlCorridas")
public class CtrlCorridasControler {
    private JdbcTemplate jdbcTemplate;
    private EventoRepository repository;

    private ICorredorRepository corredorRepository;

    @Autowired
    public CtrlCorridasControler(JdbcTemplate jdbcTemplate, CorredorRepository corredorRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.corredorRepository = corredorRepository;

        this.jdbcTemplate.execute("DROP TABLE corredores IF EXISTS");
        this.jdbcTemplate.execute("CREATE TABLE corredores("
                + "cpf VARCHAR(255), nome VARCHAR(255), genero VARCHAR(255), diaDn int, mesDn int, anoDn int, PRIMARY KEY(cpf))");

        this.jdbcTemplate.batchUpdate(
                "INSERT INTO corredores(cpf,nome,genero,diaDn,mesDn,anoDn) VALUES ('10001287','Luiz','masculino',22,5,1987)");
        this.repository.createTable();
        this.repository.dropTable();

        this.corredorRepository.dropTable();
        this.corredorRepository.createTable();
        this.corredorRepository.insertData();


/*        this.jdbcTemplate.batchUpdate(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
                " ('1','Poa Day Run',22,5,2019,5,0,35,32)");*/
    }

    @RequestMapping(value = "/corredor", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Corredor> consultaCorredor() {
        return this.corredorRepository.getAll();
    }

    @RequestMapping(value = "/corredor", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
        return this.corredorRepository.createCorredor(corredor);
    }

    @GetMapping("/eventos")
    @CrossOrigin(origins = "*")
    public List<Evento> consultaEventos() {
        return this.repository.selectTable();
    }

    @PostMapping("/eventos") // adiciona evento no único corredor
    @CrossOrigin(origins = "*")
    public boolean informaEvento(@RequestBody final Evento evento) {
        return this.repository.updateTable(evento);
    }
}
