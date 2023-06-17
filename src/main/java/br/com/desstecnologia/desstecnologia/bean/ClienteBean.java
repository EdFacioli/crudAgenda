package br.com.desstecnologia.desstecnologia.bean;

import br.com.desstecnologia.desstecnologia.Dao.ClienteDao;
import br.com.desstecnologia.desstecnologia.model.Clientes;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;

@Named("cliente")
@ViewScoped
public class ClienteBean implements Serializable {

    ClienteDao clienteDao = ClienteDao.getInstance();
    private HashMap<Integer, Clientes> clientesList;
    private Clientes cliente;
    private Integer id;

    public void salvar() {
        clienteDao.save(cliente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Salvo com sucesso!"));
        clientesList = clienteDao.findAll();
        limpar();
    }

    public void excluir(Integer id) {
        clienteDao.delete(id);
        clientesList = clienteDao.findAll();
    }

    public void atualizar(Integer id) {
        clienteDao.merge(id, cliente);
        clientesList = clienteDao.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Atualizado com sucesso!"));
    }

    public void selecionado(Integer id) {
        this.id = id;
        this.cliente = clienteDao.findById(id);
    }

    public void carregarDados() {
        clientesList = clienteDao.findAll();
    }

    public void limpar() {
        cliente = null;
    }

    public HashMap<Integer, Clientes> getClientesList() {
        if (clientesList == null) {
            clientesList = new HashMap<>();
        }
        return clientesList;
    }

    public void setClientesList(HashMap<Integer, Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public Clientes getCliente() {
        if (cliente == null) {
            cliente = new Clientes();
        }
        return cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
}
