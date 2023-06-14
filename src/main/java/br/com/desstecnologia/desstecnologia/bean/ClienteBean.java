package br.com.desstecnologia.desstecnologia.bean;

import br.com.desstecnologia.desstecnologia.model.Clientes;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;

@Named("cliente")
@SessionScoped
public class ClienteBean implements Serializable {

    private HashMap<Integer, Clientes> clientesList;
    private Clientes cliente;

    private Integer key = 1;

    private String msg;

    private Integer id;

    public void salvar() {
        clientesList.put(key, cliente);
        key++;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Salvo com sucesso!"));
        msg = "Salvo com sucesso!";
        limpar();
    }

    public void excluir(Integer id) {
        clientesList.remove(id);
    }

    public void atualizar(Integer id) {
        clientesList.put(id, cliente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Atualizado com sucesso!"));
    }

    public void selecionado(Integer id) {
        this.id = id;
        this.cliente = getClientesList().get(id);
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

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getId() {
        return id;
    }
}
