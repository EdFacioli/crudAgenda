package br.com.desstecnologia.desstecnologia.bean;

import br.com.desstecnologia.desstecnologia.dao.ClienteDao;
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
    private HashMap<Integer, Clientes> clientesStore;
    private Clientes cliente;
    private Integer id;

    public void salvar() {
        if (clienteDao.save(cliente) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Salvo com sucesso!"));
            clientesStore = clienteDao.findAll();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Não pode ser salvo!"));
        }
        limpar();
    }

    public void excluir(Integer id) {
        if (clienteDao.delete(id) != null) {
            clientesStore = clienteDao.findAll();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Não pode ser excluído!"));
        }
    }

    public void atualizar(Integer id) {
        if (clienteDao.update(id, cliente) != null) {
            clientesStore = clienteDao.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Atualizado com sucesso!"));
            limpar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Não pode ser atualizado!"));
        }
    }

    public void selecionado(Integer id) {
        this.id = id;
        this.cliente = clienteDao.findById(id);
        if (this.cliente == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Não existe o registro!"));
        }
    }

    public void carregarDados() {
        clientesStore = clienteDao.findAll();
    }

    public void limpar() {
        cliente = null;
    }

    public HashMap<Integer, Clientes> getClientesStore() {
        if (clientesStore == null) {
            clientesStore = new HashMap<>();
        }
        return clientesStore;
    }

    public void setClientesStore(HashMap<Integer, Clientes> clientesStore) {
        this.clientesStore = clientesStore;
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
