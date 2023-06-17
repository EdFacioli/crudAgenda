package br.com.desstecnologia.desstecnologia.Dao;

import br.com.desstecnologia.desstecnologia.model.Clientes;

import java.util.HashMap;

public class ClienteDao {

    private static ClienteDao instance;
    private HashMap<Integer, Clientes> clientesList;

    private Integer key = 1;

    private ClienteDao() { }

    public void save(Clientes cliente) {
        clientesListInstance();
        clientesList.put(key, cliente);
        key++;
    }

    public void delete(Integer id) {
        clientesListInstance();
        clientesList.remove(id);
    }

    public void merge(Integer id, Clientes cliente) {
        clientesListInstance();
        clientesList.put(id, cliente);
    }

    public Clientes findById(Integer id) {
        clientesListInstance();
        return clientesList.get(id);
    }

    public HashMap<Integer, Clientes> findAll() {
        clientesListInstance();
        return clientesList;
    }


    public static ClienteDao getInstance() {
        if(instance == null) {
            instance = new ClienteDao();
        }
        return instance;
    }

    private void clientesListInstance() {
        if (clientesList == null) {
            clientesList = new HashMap<>();
        }
    }
}
