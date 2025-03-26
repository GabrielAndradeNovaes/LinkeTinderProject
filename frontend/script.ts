interface Candidato {
    nome: string;
    email: string;
    cpf: string;
    idade: number;
    estado: string;
    cep: string;
    competencias: string[];
    telefone: string;
    linkedin: string;
}

interface Empresa {
    nome: string;
    emailCorporativo: string;
    cnpj: string;
    pais: string;
    estado: string;
    cep: string;
    competencias: string[];
}

class Validator {
         static isValidName(name: string): boolean {
            return /^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$/.test(name);
         }
    
         static isValidEmail(email: string): boolean {
            return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
         }
    
         static isValidCPF(cpf: string): boolean {
            return /^\d{3}\.\d{3}\.\d{3}-\d{2}$|^\d{11}$/.test(cpf);
         }
    
         static isValidCNPJ(cnpj: string): boolean {
            return /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$|^\d{14}$/.test(cnpj);
         }
    
         static isValidPhone(phone: string): boolean {
            return /^\(?\d{2}\)?[\s-]?\d{4,5}-?\d{4}$/.test(phone);
         }
    
         static isValidLinkedIn(url: string): boolean {
            return /^(https?:\/\/)?(www\.)?linkedin\.com\/in\/[a-zA-Z0-9-_]+\/?$/.test(url);
         }
    
         static isValidCEP(cep: string): boolean {
            return /^\d{5}-?\d{3}$/.test(cep);
         }
    
         static isValidTags(tags: string[]): boolean {
            return tags.every(tag => /^[A-Za-zÀ-ÖØ-öø-ÿ0-9\s]+$/.test(tag));
         }
    }

let candidatos: Candidato[] = [];
let empresas: Empresa[] = [];

document.addEventListener('DOMContentLoaded', () => {
    const conteudo = document.getElementById('conteudo');

    if (!conteudo) {
        console.error("Elemento 'conteudo' não encontrado.");
        return;
    }

    document.getElementById('cadastro-candidato')?.addEventListener('click', () => {
        conteudo.innerHTML = `
            <h2>Cadastro de Candidato</h2>
            <form id="form-candidato" class="cadastro-form">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" required>
                <label for="email">Email:</label>
                <input type="email" id="email" required>
                <label for="cpf">CPF:</label>
                <input type="text" id="cpf" required>
                <label for="idade">Idade:</label>
                <input type="number" id="idade" required>
                <label for="estado">Estado:</label>
                <input type="text" id="estado" required>
                <label for="cep">CEP:</label>
                <input type="text" id="cep" required>
                <label for="telefone">Telefone:</label>
                <input type="text" id="telefone" required>
                <label for="linkedin">Linkedin:</label>
                <input type="text" id="linkedin" required>
                <label for="competencias">Competências (separadas por vírgula):</label>
                <textarea id="competencias" required></textarea>
                <button type="submit">Cadastrar</button>
            </form>
        `;

        const formCandidato = document.getElementById('form-candidato');
        if (formCandidato) {
            formCandidato.addEventListener('submit', (event) => {
                event.preventDefault();
                const nome = (document.getElementById('nome') as HTMLInputElement).value;
                const email = (document.getElementById('email') as HTMLInputElement).value;
                const cpf = (document.getElementById('cpf') as HTMLInputElement).value;
                const idade = parseInt((document.getElementById('idade') as HTMLInputElement).value);
                const estado = (document.getElementById('estado') as HTMLInputElement).value;
                const cep = (document.getElementById('cep') as HTMLInputElement).value;
                const telefone = (document.getElementById('telefone') as HTMLInputElement).value;
                const linkedin = (document.getElementById('linkedin') as HTMLInputElement).value;
                const competencias = (document.getElementById('competencias') as HTMLTextAreaElement).value.split(',').map(s => s.trim());

                if (!Validator.isValidName(nome)) return alert('Nome inválido!');
                if (!Validator.isValidEmail(email)) return alert('E-mail inválido!');
                if (!Validator.isValidCPF(cpf)) return alert('CPF inválido!');
                if (!Validator.isValidCEP(cep)) return alert('CEP inválido!');
                if (!Validator.isValidPhone(telefone)) return alert ('Telefone invalido');
                if (!Validator.isValidLinkedIn(linkedin)) return alert ('Link invalido');
                if (!Validator.isValidTags(competencias)) return alert('Competências inválidas!');

                alert('Candidato cadastrado com sucesso!');

                candidatos.push({ nome, email, cpf, idade, estado, cep, telefone, linkedin, competencias });
                alert('Candidato cadastrado com sucesso!');
                conteudo.innerHTML = '';
            });
        } else {
            console.error("Formulário de candidato não encontrado.");
        }
    });

    document.getElementById('cadastro-empresa')?.addEventListener('click', () => {
        conteudo.innerHTML = `
            <h2>Cadastro de Empresa</h2>
            <form id="form-empresa" class="cadastro-form">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" required>
                <label for="emailCorporativo">Email Corporativo:</label>
                <input type="email" id="emailCorporativo" required>
                <label for="cnpj">CNPJ:</label>
                <input type="text" id="cnpj" required>
                <label for="pais">País:</label>
                <input type="text" id="pais" required>
                <label for="estado">Estado:</label>
                <input type="text" id="estado" required>
                <label for="cep">CEP:</label>
                <input type="text" id="cep" required>
                <label for="competencias">Competências (separadas por vírgula):</label>
                <textarea id="competencias" required></textarea>
                <button type="submit">Cadastrar</button>
            </form>
        `;

        const formEmpresa = document.getElementById('form-empresa');
        if (formEmpresa) {
            formEmpresa.addEventListener('submit', (event) => {
                event.preventDefault();
                const nome = (document.getElementById('nome') as HTMLInputElement).value;
                const emailCorporativo = (document.getElementById('emailCorporativo') as HTMLInputElement).value;
                const cnpj = (document.getElementById('cnpj') as HTMLInputElement).value;
                const pais = (document.getElementById('pais') as HTMLInputElement).value;
                const estado = (document.getElementById('estado') as HTMLInputElement).value;
                const cep = (document.getElementById('cep') as HTMLInputElement).value;
                const competencias = (document.getElementById('competencias') as HTMLTextAreaElement).value.split(',').map(s => s.trim());

                if (!Validator.isValidName(nome)) return alert('Nome inválido!');
                if (!Validator.isValidEmail(emailCorporativo)) return alert('E-mail inválido!');
                if (!Validator.isValidCNPJ(cnpj)) return alert('CNPJ inválido!');
                if (!Validator.isValidCEP(cep)) return alert('CEP inválido!');
                if (!Validator.isValidTags(competencias)) return alert('Competências inválidas!');

                alert('Empresa cadastrada com sucesso!');

                empresas.push({ nome, emailCorporativo, cnpj, pais, estado, cep, competencias });
                alert('Empresa cadastrada com sucesso!');
                conteudo.innerHTML = '';
            });
        } else {
            console.error("Formulário de empresa não encontrado.");
        }
    });

    document.getElementById('perfil-candidato')?.addEventListener('click', () => {
        if (conteudo) {
            conteudo.innerHTML = `
                <h2>Perfil de Candidatos</h2>
                <p>Informações completas serão exibidas após um match.</p>
                ${candidatos.map(candidato => `
                    <div class="perfil">
                        <p>Idade: ${candidato.idade}</p>
                        <p>Estado: ${candidato.estado}</p>
                        <p>Competências: ${candidato.competencias.join(', ')}</p>
                    </div>
                `).join('')}

                <h2>Gráfico de Candidatos por Skill</h2>
                <div class="grafico-barras" id="grafico-competencias">
                    ${gerarGraficoSkills()}
                </div>
            `;
        }
    });

    document.getElementById('perfil-empresa')?.addEventListener('click', () => {
        if (conteudo) {
            conteudo.innerHTML = `
                <h2>Perfil de Empresas</h2>
                <p>Informações completas serão exibidas após um match.</p>
                ${empresas.map(empresa => `
                    <div class="perfil">
                        <p>Estado: ${empresa.estado}</p>
                        <p>Competências: ${empresa.competencias.join(', ')}</p>
                    </div>
                `).join('')}
    
                <h2>Gráfico de Candidatos por Skill</h2>
                <div class="grafico-barras" id="grafico-competencias">
                    ${gerarGraficoSkills()}
                </div>
            `;
        }
    });

    function gerarGraficoSkills(): string {
        const skillsContagem: { [key: string]: number } = {};
        candidatos.forEach(candidato => {
            candidato.competencias.forEach(skill => {
                skillsContagem[skill] = (skillsContagem[skill] || 0) + 1;
            });
        });

        const maxContagem = Math.max(...Object.values(skillsContagem));
        const alturaMaxima = 200;

        return Object.entries(skillsContagem).map(([skill, contagem]) => {
            const alturaBarra = (contagem / maxContagem) * alturaMaxima;
            return `
                <div style="display: inline-block; margin: 0 10px; text-align: center; vertical-align: bottom;">
                    <div style="width: 20px; height: ${alturaBarra}px; background-color: #007bff; display: inline-block; vertical-align: bottom;"></div>
                    <div>${skill} (${contagem})</div>
                </div>
            `;
        }).join('');
    }
});