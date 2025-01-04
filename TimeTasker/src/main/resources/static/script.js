// Base URL para a API
const baseUrl = 'http://localhost:8083/api/tarefas';

// Mostrar indicador de carregamento
function mostrarCarregando(ativo) {
    const loading = document.getElementById('loading');
    if (loading) {
        loading.style.display = ativo ? 'block' : 'none';
    }
}

// Função para carregar tarefas na página inicial
function carregarTarefas() {
    const tarefasList = document.getElementById('tarefasList');
    if (!tarefasList) {
        console.warn('Elemento "tarefasList" não encontrado.');
        return;
    }

    mostrarCarregando(true);
    fetch(baseUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao carregar tarefas!');
            }
            return response.json();
        })
        .then(tarefas => {
            tarefasList.innerHTML = ''; // Limpa a tabela
            if (tarefas.length === 0) {
                tarefasList.innerHTML = '<tr><td colspan="5">No tasks found.</td></tr>';
                return;
            }
            tarefas.forEach(tarefa => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${tarefa.titulo}</td>
                    <td>${tarefa.descricao}</td>
                    <td>${formatarData(tarefa.prazo)}</td>
                    <td>${tarefa.prioridade}</td>
                    <td>
                        <button onclick="editarTarefa(${tarefa.id})"><i class="fas fa-edit"></i></button>
                        <button onclick="deletarTarefa(${tarefa.id})"><i class="fas fa-trash"></i></button>
                    </td>
                `;
                tarefasList.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar tarefas:', error);
            alert('Erro ao carregar tarefas!');
        })
        .finally(() => {
            mostrarCarregando(false);
        });
}

// Função para formatar a data no formato brasileiro (dd/mm/yyyy)
function formatarData(data) {
    const date = new Date(data);
    // Ajusta a data para evitar problemas com fuso horário
    date.setMinutes(date.getMinutes() - date.getTimezoneOffset());
    // Formata no padrão dd/mm/yyyy
    return date.toLocaleDateString('pt-BR');
}

// Função para editar uma tarefa
function editarTarefa(id) {
    window.location.href = `tarefas.html?id=${id}`;
}

// Função para excluir uma tarefa
function deletarTarefa(id) {
    if (!confirm('Tem certeza que deseja excluir esta tarefa?')) return;

    fetch(`${baseUrl}/${id}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao excluir tarefa!');
            alert('Tarefa excluída com sucesso!');
            carregarTarefas();
        })
        .catch(error => {
            console.error('Erro ao excluir tarefa:', error);
            alert('Erro ao excluir tarefa!');
        });
}

// Evento de envio do formulário
const tarefaForm = document.getElementById('tarefaForm');
if (tarefaForm) {
    tarefaForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const id = document.getElementById('tarefaId')?.value || null;
        const tarefa = {
            titulo: document.getElementById('titulo').value.trim(),
            descricao: document.getElementById('descricao').value.trim(),
            prazo: document.getElementById('prazo').value,
            prioridade: document.getElementById('prioridade').value
        };

        if (!tarefa.titulo || !tarefa.descricao || !tarefa.prazo) {
            alert('Por favor, preencha todos os campos obrigatórios!');
            return;
        }

        const metodo = id ? 'PUT' : 'POST';
        const url = id ? `${baseUrl}/${id}` : baseUrl;

        fetch(url, {
            method: metodo,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(tarefa)
        })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao salvar tarefa!');
            alert(id ? 'Tarefa atualizada com sucesso!' : 'Tarefa criada com sucesso!');
            window.location.href = 'index.html';
        })
        .catch(error => {
            console.error('Erro ao salvar tarefa:', error);
            alert('Erro ao salvar tarefa!');
        });
    });
}

// Função para preencher os dados da tarefa ao editar
function carregarTarefaParaEdicao() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    if (!id) {
        alert('ID de tarefa inválido!');
        return;
    }

    mostrarCarregando(true);
    fetch(`${baseUrl}/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao carregar a tarefa!');
            }
            return response.json();
        })
        .then(tarefa => {
            document.getElementById('tarefaId').value = tarefa.id;
            document.getElementById('titulo').value = tarefa.titulo;
            document.getElementById('descricao').value = tarefa.descricao;
            document.getElementById('prazo').value = formatarData(tarefa.prazo);
            document.getElementById('prioridade').value = tarefa.prioridade;
        })
        .catch(error => {
            console.error('Erro ao carregar a tarefa:', error);
            alert('Erro ao carregar a tarefa!');
        })
        .finally(() => {
            mostrarCarregando(false);
        });
}

// Carregar tarefas ao abrir a página inicial
if (document.getElementById('tarefasList')) {
    carregarTarefas();
}

// Carregar os dados para edição na página de edição
if (document.getElementById('tarefaForm') && window.location.pathname.includes('tarefas.html')) {
    carregarTarefaParaEdicao();
}
