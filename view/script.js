const API_BASE = 'http://localhost:8080/reservas';
const HOSPEDE_ID = 1;

const formReserva = document.getElementById('formReserva');
const listaReservas = document.getElementById('listaReservas');
const mensagem = document.getElementById('mensagem');
const btnRecarregar = document.getElementById('btnRecarregar');
const btnCancelar = document.getElementById('btnCancelar');

const campos = {
    reservaId: document.getElementById('reservaId'),
    status: document.getElementById('status'),
    dataHora: document.getElementById('dataHora'),
    disponibilidadeId: document.getElementById('disponibilidadeId'),
    estudanteId: document.getElementById('estudanteId'),
    tutorId: document.getElementById('tutorId')
};

function mostrarMensagem(texto, tipo = 'success') {
    mensagem.innerHTML = `<div class="alert alert-${tipo} alert-dismissible fade show" role="alert">
        ${texto}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Fechar"></button>
    </div>`;
}

function limparMensagem() {
    mensagem.innerHTML = '';
}

function formatarDataHora(valor) {
    if (!valor) return '-';
    return new Date(valor).toLocaleString('pt-BR');
}

function preencherFormulario(reserva) {
    campos.reservaId.value = reserva.id ?? '';
    campos.status.value = reserva.status ?? '';
    campos.dataHora.value = reserva.dataHora ? reserva.dataHora.substring(0, 16) : '';
    campos.disponibilidadeId.value = reserva.disponibilidadeId ?? '';
    campos.estudanteId.value = reserva.estudanteId ?? HOSPEDE_ID;
    campos.tutorId.value = reserva.tutorId ?? '';
}

function limparFormulario() {
    formReserva.reset();
    campos.reservaId.value = '';
    campos.estudanteId.value = HOSPEDE_ID;
}

function renderizarReservas(reservas) {
    if (!reservas.length) {
        listaReservas.innerHTML = '<tr><td colspan="7" class="text-center text-muted py-4">Nenhuma reserva encontrada para este hóspede.</td></tr>';
        return;
    }

    listaReservas.innerHTML = reservas.map((reserva) => `
        <tr>
            <td>${reserva.id ?? '-'}</td>
            <td><span class="badge text-bg-primary">${reserva.status ?? '-'}</span></td>
            <td>${formatarDataHora(reserva.dataHora)}</td>
            <td>${reserva.disponibilidadeId ?? '-'}</td>
            <td>${reserva.estudanteId ?? '-'}</td>
            <td>${reserva.tutorId ?? '-'}</td>
            <td class="text-end">
                <button class="btn btn-sm btn-outline-secondary me-2" data-acao="editar" data-id="${reserva.id}">Editar</button>
                <button class="btn btn-sm btn-outline-danger" data-acao="excluir" data-id="${reserva.id}">Excluir</button>
            </td>
        </tr>
    `).join('');
}

async function carregarReservas() {
    try {
        limparMensagem();
        const resposta = await fetch(`${API_BASE}/hospede/${HOSPEDE_ID}`);

        if (!resposta.ok) {
            throw new Error('Falha ao carregar reservas.');
        }

        const reservas = await resposta.json();
        renderizarReservas(reservas);
    } catch (erro) {
        listaReservas.innerHTML = '<tr><td colspan="7" class="text-center text-danger py-4">Não foi possível carregar as reservas.</td></tr>';
        mostrarMensagem(erro.message, 'danger');
    }
}

async function salvarReserva(evento) {
    evento.preventDefault();
    limparMensagem();

    const payload = {
        status: campos.status.value.trim(),
        dataHora: campos.dataHora.value ? `${campos.dataHora.value}:00` : null,
        disponibilidadeId: Number(campos.disponibilidadeId.value),
        estudanteId: Number(campos.estudanteId.value),
        tutorId: Number(campos.tutorId.value)
    };

    const reservaId = campos.reservaId.value;
    const metodo = reservaId ? 'PUT' : 'POST';
    const url = reservaId ? `${API_BASE}/${reservaId}` : API_BASE;

    try {
        const resposta = await fetch(url, {
            method: metodo,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (!resposta.ok) {
            throw new Error('Não foi possível salvar a reserva.');
        }

        mostrarMensagem('Reserva salva com sucesso.');
        limparFormulario();
        await carregarReservas();
    } catch (erro) {
        mostrarMensagem(erro.message, 'danger');
    }
}

async function excluirReserva(id) {
    if (!confirm('Tem certeza que deseja excluir esta reserva?')) {
        return;
    }

    try {
        const resposta = await fetch(`${API_BASE}/${id}`, { method: 'DELETE' });

        if (!resposta.ok) {
            throw new Error('Não foi possível excluir a reserva.');
        }

        mostrarMensagem('Reserva excluída com sucesso.');
        await carregarReservas();
    } catch (erro) {
        mostrarMensagem(erro.message, 'danger');
    }
}

async function editarReserva(id) {
    try {
        const resposta = await fetch(`${API_BASE}/${id}`);

        if (!resposta.ok) {
            throw new Error('Não foi possível carregar a reserva.');
        }

        const reserva = await resposta.json();
        preencherFormulario(reserva);
        window.scrollTo({ top: 0, behavior: 'smooth' });
    } catch (erro) {
        mostrarMensagem(erro.message, 'danger');
    }
}

listaReservas.addEventListener('click', (evento) => {
    const botao = evento.target.closest('button[data-acao]');
    if (!botao) {
        return;
    }

    const { acao, id } = botao.dataset;

    if (acao === 'editar') {
        editarReserva(id);
    }

    if (acao === 'excluir') {
        excluirReserva(id);
    }
});

formReserva.addEventListener('submit', salvarReserva);
btnRecarregar.addEventListener('click', carregarReservas);
btnCancelar.addEventListener('click', limparFormulario);

carregarReservas();