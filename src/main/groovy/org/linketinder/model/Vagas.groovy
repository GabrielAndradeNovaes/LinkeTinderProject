package org.linketinder.model

class Vagas {
    String nome;
    String descricao;
    Empresa empresa;

    Vagas(String nome, String descricao, Empresa empresa) {
        this.nome = nome
        this.descricao = descricao
        this.empresa = empresa
    }
}
