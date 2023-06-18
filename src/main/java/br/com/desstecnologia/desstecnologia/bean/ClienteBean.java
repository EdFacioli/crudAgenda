package br.com.desstecnologia.desstecnologia.bean;

import br.com.desstecnologia.desstecnologia.dao.ClienteDao;
import br.com.desstecnologia.desstecnologia.model.Clientes;
import com.sun.java.swing.plaf.windows.resources.windows;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
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
    private Integer id = 0;

    private boolean editar;

    public void salvar(Integer id) {
        if (clienteDao.save(id, cliente) != null) {
            this.msgDialog(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
            clientesStore = clienteDao.findAll();
        } else {
            this.msgDialog(FacesMessage.SEVERITY_ERROR, "Não pode ser salvo!");
        }
        limpar();
    }

    public void excluir(Integer id) {
        if (clienteDao.delete(id) != null) {
            clientesStore = clienteDao.findAll();
        } else {
            this.msgDialog(FacesMessage.SEVERITY_ERROR, "Não pode ser excluído!");
        }
    }

    public void selecionado(Integer id) {
        if (id > 0) {
            this.id = id;
            this.cliente = clienteDao.findById(id);
            if (this.cliente == null) {
                this.msgDialog(FacesMessage.SEVERITY_ERROR, "Não existe o registro para o id informado!");
            }
            this.editar = true;
        } else {
            this.cliente = new Clientes();
            this.editar = false;
        }
    }

    private void msgDialog(Severity severity, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, "", msg));
    }

    public void carregarDados() {
        clientesStore = clienteDao.findAll();
    }

    public void limpar() {
        id = 0;
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

    public boolean isEditar() {
        return editar;
    }
}
