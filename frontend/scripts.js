let tasks = [];
let filteredTasks = [];
let editIndex = -1;


document.getElementById('task-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const taskData = {
        nome: document.getElementById('task-name').value,
        descricao: document.getElementById('task-desc').value,
        dataTermino: document.getElementById('task-date').value,
        prioridade: document.getElementById('task-priority').value,
        categoria: document.getElementById('task-category').value,
        status: document.getElementById('task-status').value
    };

    if (editIndex === -1) {
        tasks.push(taskData);  
    } else {
        tasks[editIndex] = taskData; 
        editIndex = -1;
    }

    applyFilters();  
    clearForm();
});


function formatDate(dateString) {
    const date = new Date(dateString);
    const day = ('0' + date.getDate()).slice(-2);
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}

function clearForm() {
    document.getElementById('task-form').reset();
}

function editTask(index) {
    const task = tasks[index];
    document.getElementById('task-name').value = task.nome;
    document.getElementById('task-desc').value = task.descricao;
    document.getElementById('task-date').value = task.dataTermino;
    document.getElementById('task-priority').value = task.prioridade;
    document.getElementById('task-category').value = task.categoria;
    document.getElementById('task-status').value = task.status;
    editIndex = index;
}


function deleteTask(index) {
    tasks.splice(index, 1);
    applyFilters();  
}


function applyFilters() {
    const category = document.getElementById('category-input').value.toLowerCase();
    const priority = document.getElementById('priority-select').value;

    filteredTasks = tasks.filter(task => {
        const matchesCategory = category ? task.categoria.toLowerCase().includes(category) : true;
        const matchesPriority = priority ? task.prioridade === priority : true;
        return matchesCategory && matchesPriority;
    });

    renderTasks(filteredTasks);
    updateTaskCounts();
}

function renderTasks(tasksToRender) {
    const tbody = document.getElementById('tasks-table-body');
    tbody.innerHTML = ''; 

    tasksToRender.forEach((task, index) => {
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>${task.nome}</td>
            <td>${task.descricao}</td>
            <td>${formatDate(task.dataTermino)}</td>
            <td>${task.prioridade}</td>
            <td>${task.categoria}</td>
            <td>${task.status}</td>
            <td>
                <button onclick="editTask(${index})">Editar</button>
                <button onclick="deleteTask(${index})">Excluir</button>
            </td>
        `;

        tbody.appendChild(row);
    });
}

function updateTaskCounts() {
    const todoCount = filteredTasks.filter(task => task.status === 'TODO').length;
    const doingCount = filteredTasks.filter(task => task.status === 'DOING').length;
    const doneCount = filteredTasks.filter(task => task.status === 'DONE').length;

    document.getElementById('todo-count').textContent = todoCount;
    document.getElementById('doing-count').textContent = doingCount;
    document.getElementById('done-count').textContent = doneCount;
}

document.getElementById('filter-all').addEventListener('click', () => {
    filteredTasks = tasks.slice(); 
    renderTasks(filteredTasks);
    updateTaskCounts();
});

document.getElementById('filter-todo').addEventListener('click', () => {
    filteredTasks = tasks.filter(task => task.status === 'TODO');
    renderTasks(filteredTasks);
    updateTaskCounts();
});

document.getElementById('filter-doing').addEventListener('click', () => {
    filteredTasks = tasks.filter(task => task.status === 'DOING');
    renderTasks(filteredTasks);
    updateTaskCounts();
});

document.getElementById('filter-done').addEventListener('click', () => {
    filteredTasks = tasks.filter(task => task.status === 'DONE');
    renderTasks(filteredTasks);
    updateTaskCounts();
});

document.getElementById('filter-category').addEventListener('click', applyFilters);

document.getElementById('clear-category-filter').addEventListener('click', () => {
    document.getElementById('category-input').value = '';
    applyFilters();
});

document.getElementById('filter-priority').addEventListener('click', applyFilters);

document.getElementById('clear-priority-filter').addEventListener('click', () => {
    document.getElementById('priority-select').value = '';
    applyFilters();
});
