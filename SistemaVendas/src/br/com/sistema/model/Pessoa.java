/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.model;

/**
 *
 * @author Usuário
 */
public class Pessoa {
    private int id;
    private String nome;
    private String rg;
    private String cpf;
    private String email;
    private String telefone;
    private String celular;
    private String urlImagem;
    
    private Endereco endereco = new Endereco();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
    
    public String getCep() {
        return endereco.getCep();
    }

    public void setCep(String cep) {
        this.endereco.setCep(cep);
    }

    public String getLogradouro() {
        return endereco.getLogradouro();
    }

    public void setLogradouro(String logradouro) {
        this.endereco.setLogradouro(logradouro);
    }

    public int getNumero() {
        return endereco.getNumero();
    }

    public void setNumero(int numero) {
        this.endereco.setNumero(numero);
    }

    public String getComplemento() {
        return endereco.getComplemento();
    }

    public void setComplemento(String complemento) {
        this.endereco.setComplemento(complemento);
    }

    public String getBairro() {
        return endereco.getBairro();
    }

    public void setBairro(String bairro) {
        this.endereco.setBairro(bairro);
    }

    public String getCidade() {
        return endereco.getCidade();
    }

    public void setCidade(String cidade) {
        this.endereco.setCidade(cidade);
    }

    public String getUf() {
        return endereco.getUf();
    }

    public void setUf(String uf) {
        this.endereco.setUf(uf);
    }
}
