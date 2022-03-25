package com.bcopstein.CtrlCorredorV1.Repositories;

import java.util.List;

import com.bcopstein.CtrlCorredorV1.Corredor;

public interface ICorredorRepository {
    public boolean createCorredor(Corredor corredor);
    public List<Corredor> getAll();
    public void insertData();
    public void createTable();
    public void dropTable();
}
