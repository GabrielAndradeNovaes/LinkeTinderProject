package org.linketinder.model

class Competencia {
    int id
    String nome;

    Competencia() {}

    Competencia(String nome) {
        this.nome = nome
    }

    Competencia(int id, String nome) {
        this.id = id
        this.nome = nome
    }

    @Override
    String toString() {
        return "Nome: $nome"
    }
}
