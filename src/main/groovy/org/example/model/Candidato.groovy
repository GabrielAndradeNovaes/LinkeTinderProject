package org.example.model

import org.example.model.Competencia

class Candidato {
    String nome
    String email
    String cpf
    int idade
    String estado
    String cep
    List<Competencia> competencias = []

    Candidato(){

    }

    Candidato(String nome, String email, String cpf, int idade, String estado, String cep, List<Competencia> competencias) {
        this.nome = nome
        this.email = email
        this.cpf = cpf
        this.idade = idade
        this.estado = estado
        this.cep = cep
        this.competencias = competencias
    }

    Candidato(Map dados, List<Competencia> competencias) {
        this.nome = dados.nome
        this.email = dados.email
        this.cpf = dados.cpf
        this.idade = dados.idade
        this.estado = dados.estado
        this.cep = dados.cep
        this.competencias = competencias
    }

    @Override
    String toString() {
        return "Nome: ${nome}, Email: ${email}"
    }
}
