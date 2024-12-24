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
    mostrarCarregando(true);
    fetch(baseUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao carregar tarefas!');
            }
            return response.json();
        })
        .then(tarefas => {
            const tarefasList = document.getElementById('tarefasList');
            if (!tarefasList) return;

            tarefasList.innerHTML = '';

            tarefas.forEach(tarefa => {
                const tr = document.createElement('tr');
                tr.classList.add(`prioridade-${tarefa.prioridade}`);
                tr.innerHTML = `
                    <td>${tarefa.titulo}</td>
                    <td>${tarefa.descricao}</td>
                    <td>${tarefa.prazo}</td>
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
            alert('Erro: ' + error.message);
        })
        .finally(() => {
            mostrarCarregando(false);
        });
}

// Função para editar uma tarefa
function editarTarefa(id) {
    fetch(`${baseUrl}/${id}`)
        .then(response => {
            if (!response.ok) throw new Error('Erro ao buscar tarefa!');
            return response.json();
        })
        .then(tarefa => {
            document.getElementById('pageTitle').textContent = 'Editar Tarefa';
            document.getElementById('tarefaId').value = tarefa.id;
            document.getElementById('titulo').value = tarefa.titulo;
            document.getElementById('descricao').value = tarefa.descricao;
            document.getElementById('prazo').value = tarefa.prazo;
            document.getElementById('prioridade').value = tarefa.prioridade;
            document.getElementById('submitBtn').textContent = 'Atualizar Tarefa';
        })
        .catch(error => alert('Erro: ' + error.message));
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
        .catch(error => alert('Erro: ' + error.message));
}

// Função para salvar ou atualizar tarefa
document.getElementById('tarefaForm')?.addEventListener('submit', function (e) {
    e.preventDefault();

    const id = document.getElementById('tarefaId').value;
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
    .catch(error => alert('Erro: ' + error.message));
});

// Carregar as tarefas ao carregar a página
if (document.getElementById('tarefasList')) {
    carregarTarefas();
}
