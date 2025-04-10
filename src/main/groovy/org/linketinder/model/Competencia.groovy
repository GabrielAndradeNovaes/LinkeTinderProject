package org.linketinder.model

class Competencia {
    String nome;

    Competencia() {}

    Competencia(String nome) {
        this.nome = nome
    }

    @Override
    String toString() {
        return "Nome: $nome"
    }
}
