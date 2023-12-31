package br.com.desstecnologia.desstecnologia.dao;

import br.com.desstecnologia.desstecnologia.model.Clientes;

import java.util.HashMap;

public class ClienteDao {

    private static ClienteDao instance;
    private HashMap<Integer, Clientes> clientesStore;

    private Integer key = 0;

    private ClienteDao() {
        clientesStore = new HashMap<>();
    }

    public Clientes save(Integer id, Clientes cliente) {
        if (id != null && id > 0) {
            clientesStore.replace(id, cliente);
        } else {
            key++;
            clientesStore.put(key, cliente);
        }
        return clientesStore.get(key);
    }

    public Clientes delete(Integer id) {
        return clientesStore.remove(id);
    }

    public Clientes findById(Integer id) {
        return clientesStore.get(id);
    }

    public HashMap<Integer, Clientes> findAll() {
        return new HashMap<>(clientesStore);
    }


    public static ClienteDao getInstance() {
        if(instance == null) {
            instance = new ClienteDao();
        }
        return instance;
    }
}
