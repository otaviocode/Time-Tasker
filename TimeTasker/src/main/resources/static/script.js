// Base URL para a API
const baseUrl = 'http://localhost:8080/api/tarefas';

// Função para carregar tarefas na página inicial
function carregarTarefas() {
    fetch(baseUrl)
        .then(response => response.json())
        .then(tarefas => {
            const tarefasList = document.getElementById('tarefasList');
            tarefasList.innerHTML = '';

            tarefas.forEach(tarefa => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${tarefa.titulo}</td>
                    <td>${tarefa.descricao}</td>
                    <td>${tarefa.prazo}</td>
                    <td>${tarefa.prioridade}</td>
                    <td>
                        <button onclick="editarTarefa(${tarefa.id})">Editar</button>
                        <button onclick="deletarTarefa(${tarefa.id})">Excluir</button>
                    </td>
                `;
                tarefasList.appendChild(tr);
            });
        });
}

// Função para editar uma tarefa
function editarTarefa(id) {
    fetch(`${baseUrl}/${id}`)
        .then(response => response.json())
        .then(tarefa => {
            document.getElementById('pageTitle').textContent = 'Editar Tarefa';
            document.getElementById('tarefaId').value = tarefa.id;
            document.getElementById('titulo').value = tarefa.titulo;
            document.getElementById('descricao').value = tarefa.descricao;
            document.getElementById('prazo').value = tarefa.prazo;
            document.getElementById('prioridade').value = tarefa.prioridade;
            document.getElementById('submitBtn').textContent = 'Atualizar Tarefa';
        });
}

// Função para excluir uma tarefa
function deletarTarefa(id) {
    fetch(`${baseUrl}/${id}`, {
        method: 'DELETE'
    })
    .then(() => {
        alert('Tarefa excluída com sucesso!');
        carregarTarefas();
    });
}

// Função para salvar ou atualizar tarefa
document.getElementById('tarefaForm').addEventListener('submit', function (e) {
    e.preventDefault();
    
    const id = document.getElementById('tarefaId').value;
    const tarefa = {
        titulo: document.getElementById('titulo').value,
        descricao: document.getElementById('descricao').value,
        prazo: document.getElementById('prazo').value,
        prioridade: document.getElementById('prioridade').value
    };

    if (id) {
        // Atualizar tarefa
        fetch(`${baseUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tarefa)
        })
        .then(response => response.json())
        .then(() => {
            alert('Tarefa atualizada com sucesso!');
            window.location.href = 'index.html';
        });
    } else {
        // Criar nova tarefa
        fetch(baseUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tarefa)
        })
        .then(response => response.json())
        .then(() => {
            alert('Tarefa criada com sucesso!');
            window.location.href = 'index.html';
        });
    }
});

// Carregar as tarefas ao carregar a página
if (document.getElementById('tarefasList')) {
    carregarTarefas();
}
