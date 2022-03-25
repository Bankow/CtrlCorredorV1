package com.bcopstein.CtrlCorredorV1;

import org.springframework.jdbc.core.JdbcTemplate;

public class EventoRepository {
    private JdbcTemplate dataBase;
    Evento evento;

    public EventoRepository(JdbcTemplate dataBase) {
        this.dataBase = dataBase;
    }

    public void createTable() {
        this.dataBase.execute("CREATE TABLE eventos("
                + "id int, nome VARCHAR(255), dia int, mes int, ano int, distancia int, horas int, minutos int, segundos int,PRIMARY KEY(id))");
    }

    public void dropTable() {
        this.dataBase.execute("DROP TABLE eventos IF EXISTS");
    }

    public void updateTable(Evento evento) {
        this.dataBase.update(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES (?,?,?,?,?,?,?,?,?)",
                evento.getId(), evento.getNome(), evento.getDia(), evento.getMes(), evento.getAno(),
                evento.getDistancia(), evento.getHoras(), evento.getMinutos(), evento.getSegundos());
    }

    public void selectTable() {
        this.dataBase.query("SELECT * from eventos",
                (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                        rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                        rs.getInt("segundos")));
    }

}
