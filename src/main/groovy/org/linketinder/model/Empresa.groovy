package org.linketinder.model

class Empresa {
    int id
    String nome
    String email
    String cnpj
    String pais
    String cep
    List<Competencia> competencias = []

    Empresa(){

    }

    Empresa(int id, String nome) {
        this.id = id
        this.nome = nome
    }

    Empresa(int id, String nome, String email, String cnpj, int idade, String pais, String cep, List<Competencia> competencias) {
        this.id = id
        this.nome = nome
        this.email = email
        this.cnpj = cnpj
        this.idade = idade
        this.pais = pais
        this.cep = cep
        this.competencias = competencias
    }
}
