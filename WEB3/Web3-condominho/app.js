// app.js – Sistema de Condomínio
// Funcionalidades: cadastro de condôminos, unidades, garagens, utensílios; sorteio de garagens; chat; documentos.

// ==================== CONFIGURAÇÃO SUPABASE ====================
const SUPABASE_URL ='https://xjpimzgemjzcqwxzvetm.supabase.co';
const SUPABASE_ANON_KEY ='eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhqcGltemdlbWp6Y3F3eHp2ZXRtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzgwMjM4OTcsImV4cCI6MjA5MzU5OTg5N30.wQUh_8glb-1YiP1sH_Dlm6cea8VhneL-0bbj0O7usc8';

let supabaseClient = null;

function initSupabase() {
  if (typeof supabase !== 'undefined' && supabase.createClient) {
    supabaseClient = supabase.createClient(SUPABASE_URL, SUPABASE_ANON_KEY);
    console.log('Supabase inicializado com sucesso.');
  } else {
    console.warn('Supabase CDN não carregado. Usando armazenamento local (localStorage).');
    supabaseClient = null;
  }
}

// ==================== ARMAZENAMENTO LOCAL (FALLBACK) ====================
const localDB = {
  condominos: JSON.parse(localStorage.getItem('condominos') || '[]'),
  unidades: JSON.parse(localStorage.getItem('unidades') || '[]'),
  garagens: JSON.parse(localStorage.getItem('garagens') || '[]'),
  utensilios: JSON.parse(localStorage.getItem('utensilios') || '[]'),
  chat_messages: JSON.parse(localStorage.getItem('chat_messages') || '[]'),
  _nextId(store) {
    return store.length > 0 ? Math.max(...store.map(item => item.id)) + 1 : 1;
  },
  insert(table, data) {
    const store = this[table];
    const newItem = { id: this._nextId(store), ...data, created_at: new Date().toISOString() };
    store.push(newItem);
    localStorage.setItem(table, JSON.stringify(store));
    return { data: [newItem], error: null };
  },
  select(table) {
    return this[table] || [];
  },
  update(table, id, updates) {
    const store = this[table];
    const index = store.findIndex(item => item.id === id);
    if (index !== -1) {
      store[index] = { ...store[index], ...updates };
      localStorage.setItem(table, JSON.stringify(store));
    }
    return { data: [store[index]], error: null };
  },
  delete(table, id) {
    let store = this[table];
    store = store.filter(item => item.id !== id);
    this[table] = store;
    localStorage.setItem(table, JSON.stringify(store));
    return { error: null };
  }
};

// Helper para obter cliente (Supabase ou local)
function getClient() {
  return supabaseClient || localDB;
}

// ==================== NAVEGAÇÃO ====================
function showSection(sectionId) {
  document.querySelectorAll('.section').forEach(section => {
    section.classList.remove('active');
    section.style.display = 'none'; // fallback
  });
  const target = document.getElementById(sectionId);
  if (target) {
    target.classList.add('active');
    target.style.display = 'block';
  }
  // Atualiza dashboard se necessário
  if (sectionId === 'dashboard') {
    atualizarDashboard();
  }
  // Carrega dados das tabelas quando seção é exibida
  if (sectionId === 'condominos') carregarCondominos();
  if (sectionId === 'unidades') carregarUnidades();
  if (sectionId === 'garagens') carregarGaragens();
  if (sectionId === 'cozinha') carregarUtensilios();
  if (sectionId === 'chat') carregarMensagensChat();
}

// ==================== DASHBOARD ====================
async function atualizarDashboard() {
  const client = getClient();
  let totalCondominos = 0, totalUnidades = 0, totalGaragens = 0, garagensDisponiveis = 0;

  if (supabaseClient) {
    const [{ count: cC }, { count: cU }, { count: cG }, { data: gD }] = await Promise.all([
      client.from('condominos').select('*', { count: 'exact', head: true }),
      client.from('unidades').select('*', { count: 'exact', head: true }),
      client.from('garagens').select('*', { count: 'exact', head: true }),
      client.from('garagens').select('id').eq('disponivel', true)
    ]);
    totalCondominos = cC || 0;
    totalUnidades = cU || 0;
    totalGaragens = cG || 0;
    garagensDisponiveis = gD?.length || 0;
  } else {
    totalCondominos = localDB.condominos.length;
    totalUnidades = localDB.unidades.length;
    totalGaragens = localDB.garagens.length;
    garagensDisponiveis = localDB.garagens.filter(g => g.disponivel).length;
  }

  document.getElementById('total-condominos').textContent = totalCondominos;
  document.getElementById('total-unidades').textContent = totalUnidades;
  document.getElementById('total-garagens').textContent = totalGaragens;
  document.getElementById('garagens-disponiveis').textContent = garagensDisponiveis;
}

// ==================== CONDÔMINOS ====================
async function carregarCondominos() {
  const client = getClient();
  let data = [];
  if (supabaseClient) {
    const res = await client.from('condominos').select('*').order('nome');
    data = res.data || [];
  } else {
    data = localDB.condominos.sort((a, b) => a.nome.localeCompare(b.nome));
  }
  const tbody = document.getElementById('tabela-condominos');
  tbody.innerHTML = '';
  data.forEach(cond => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td class="px-4 py-2">${cond.nome}</td>
      <td class="px-4 py-2">${cond.cpf || ''}</td>
      <td class="px-4 py-2">${cond.unidade || ''}</td>
      <td class="px-4 py-2">${cond.email || ''}</td>
      <td class="px-4 py-2">${cond.telefone || ''}</td>
      <td class="px-4 py-2">
        <button onclick="editarCondomino(${cond.id})" class="text-blue-600 hover:underline mr-2">Editar</button>
        <button onclick="excluirCondomino(${cond.id})" class="text-red-600 hover:underline">Excluir</button>
      </td>
    `;
    tbody.appendChild(tr);
  });
}

function openModalCondomino(id = null) {
  const modal = document.getElementById('modal');
  const title = document.getElementById('modal-title');
  const content = document.getElementById('modal-content');
  title.textContent = id ? 'Editar Condômino' : 'Novo Condômino';
  const cond = id ? (supabaseClient ? null : localDB.condominos.find(c => c.id === id)) : null;
  content.innerHTML = `
    <form id="form-condomino" ${id ? `data-id="${id}"` : ''}>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Nome *</label>
        <input type="text" name="nome" value="${cond?.nome || ''}" required class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">CPF *</label>
        <input type="text" name="cpf" value="${cond?.cpf || ''}" required class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Unidade *</label>
        <input type="text" name="unidade" value="${cond?.unidade || ''}" required class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">E-mail</label>
        <input type="email" name="email" value="${cond?.email || ''}" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Telefone</label>
        <input type="text" name="telefone" value="${cond?.telefone || ''}" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Tipo</label>
        <select name="tipo" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option value="Morador" ${cond?.tipo === 'Morador' ? 'selected' : ''}>Morador</option>
          <option value="Síndico" ${cond?.tipo === 'Síndico' ? 'selected' : ''}>Síndico</option>
          <option value="Subsíndico" ${cond?.tipo === 'Subsíndico' ? 'selected' : ''}>Subsíndico</option>
        </select>
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Quantidade de Carros</label>
        <input type="number" name="qtd_carros" value="${cond?.qtd_carros || 0}" min="0" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="flex justify-end space-x-2">
        <button type="button" onclick="closeModal()" class="px-4 py-2 border rounded-lg hover:bg-gray-100">Cancelar</button>
        <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">Salvar</button>
      </div>
    </form>
  `;
  modal.classList.remove('hidden');
  modal.style.display = 'flex';

  document.getElementById('form-condomino').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData.entries());
    data.qtd_carros = parseInt(data.qtd_carros) || 0;
    const client = getClient();
    if (id) {
      if (supabaseClient) {
        await client.from('condominos').update(data).eq('id', id);
      } else {
        localDB.update('condominos', id, data);
      }
    } else {
      if (supabaseClient) {
        await client.from('condominos').insert([data]);
      } else {
        localDB.insert('condominos', data);
      }
    }
    closeModal();
    carregarCondominos();
    atualizarDashboard();
  });
}

function editarCondomino(id) {
  openModalCondomino(id);
}

async function excluirCondomino(id) {
  if (!confirm('Tem certeza que deseja excluir este condômino?')) return;
  const client = getClient();
  if (supabaseClient) {
    await client.from('condominos').delete().eq('id', id);
  } else {
    localDB.delete('condominos', id);
  }
  carregarCondominos();
  atualizarDashboard();
}

// ==================== UNIDADES ====================
async function carregarUnidades() {
  const client = getClient();
  let data = [];
  if (supabaseClient) {
    const res = await client.from('unidades').select('*, condominos(nome)').order('numero');
    data = res.data || [];
  } else {
    data = localDB.unidades.sort((a, b) => a.numero - b.numero);
  }
  const tbody = document.getElementById('tabela-unidades');
  tbody.innerHTML = '';
  data.forEach(un => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td class="px-4 py-2">${un.numero}</td>
      <td class="px-4 py-2">${un.bloco || ''}</td>
      <td class="px-4 py-2">${un.andar || ''}</td>
      <td class="px-4 py-2">${un.condominos?.nome || un.condomino_nome || ''}</td>
      <td class="px-4 py-2">
        <button onclick="editarUnidade(${un.id})" class="text-blue-600 hover:underline mr-2">Editar</button>
        <button onclick="excluirUnidade(${un.id})" class="text-red-600 hover:underline">Excluir</button>
      </td>
    `;
    tbody.appendChild(tr);
  });
}

function openModalUnidade(id = null) {
  const modal = document.getElementById('modal');
  const title = document.getElementById('modal-title');
  const content = document.getElementById('modal-content');
  title.textContent = id ? 'Editar Unidade' : 'Nova Unidade';
  const un = id ? (supabaseClient ? null : localDB.unidades.find(u => u.id === id)) : null;
  content.innerHTML = `
    <form id="form-unidade" ${id ? `data-id="${id}"` : ''}>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Número *</label>
        <input type="text" name="numero" value="${un?.numero || ''}" required class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Bloco</label>
        <input type="text" name="bloco" value="${un?.bloco || ''}" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Andar</label>
        <input type="text" name="andar" value="${un?.andar || ''}" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Condômino Responsável</label>
        <select name="condomino_id" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option value="">Selecione...</option>
          ${localDB.condominos.map(c => `<option value="${c.id}" ${un?.condomino_id === c.id ? 'selected' : ''}>${c.nome}</option>`).join('')}
        </select>
      </div>
      <div class="flex justify-end space-x-2">
        <button type="button" onclick="closeModal()" class="px-4 py-2 border rounded-lg hover:bg-gray-100">Cancelar</button>
        <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">Salvar</button>
      </div>
    </form>
  `;
  modal.classList.remove('hidden');
  modal.style.display = 'flex';

  document.getElementById('form-unidade').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData.entries());
    const client = getClient();
    if (id) {
      if (supabaseClient) {
        await client.from('unidades').update(data).eq('id', id);
      } else {
        localDB.update('unidades', id, data);
      }
    } else {
      if (supabaseClient) {
        await client.from('unidades').insert([data]);
      } else {
        localDB.insert('unidades', data);
      }
    }
    closeModal();
    carregarUnidades();
    atualizarDashboard();
  });
}

function editarUnidade(id) {
  openModalUnidade(id);
}

async function excluirUnidade(id) {
  if (!confirm('Tem certeza que deseja excluir esta unidade?')) return;
  const client = getClient();
  if (supabaseClient) {
    await client.from('unidades').delete().eq('id', id);
  } else {
    localDB.delete('unidades', id);
  }
  carregarUnidades();
  atualizarDashboard();
}

// ==================== GARAGENS ====================
async function carregarGaragens() {
  const client = getClient();
  let data = [];
  if (supabaseClient) {
    const res = await client.from('garagens').select('*, condominos(nome)').order('numero');
    data = res.data || [];
  } else {
    data = localDB.garagens.sort((a, b) => a.numero - b.numero);
  }
  const tbody = document.getElementById('tabela-garagens');
  tbody.innerHTML = '';
  data.forEach(g => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td class="px-4 py-2">${g.numero}</td>
      <td class="px-4 py-2">${g.bloco || ''}</td>
      <td class="px-4 py-2">${g.andar || ''}</td>
      <td class="px-4 py-2">${g.tipo || 'Comum'}</td>
      <td class="px-4 py-2">${g.disponivel ? 'Sim' : 'Não'}</td>
      <td class="px-4 py-2">${g.condominos?.nome || g.condomino_nome || ''}</td>
      <td class="px-4 py-2">
        <button onclick="editarGaragem(${g.id})" class="text-blue-600 hover:underline mr-2">Editar</button>
        <button onclick="excluirGaragem(${g.id})" class="text-red-600 hover:underline">Excluir</button>
      </td>
    `;
    tbody.appendChild(tr);
  });
}

function openModalGaragem(id = null) {
  const modal = document.getElementById('modal');
  const title = document.getElementById('modal-title');
  const content = document.getElementById('modal-content');
  title.textContent = id ? 'Editar Garagem' : 'Nova Garagem';
  const g = id ? (supabaseClient ? null : localDB.garagens.find(gar => gar.id === id)) : null;
  content.innerHTML = `
    <form id="form-garagem" ${id ? `data-id="${id}"` : ''}>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Número *</label>
        <input type="text" name="numero" value="${g?.numero || ''}" required class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Bloco</label>
        <input type="text" name="bloco" value="${g?.bloco || ''}" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Andar</label>
        <input type="text" name="andar" value="${g?.andar || ''}" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Tipo</label>
        <select name="tipo" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option value="Comum" ${g?.tipo === 'Comum' ? 'selected' : ''}>Comum (Sorteio)</option>
          <option value="Síndico" ${g?.tipo === 'Síndico' ? 'selected' : ''}>Síndico (Fixa)</option>
          <option value="Subsíndico" ${g?.tipo === 'Subsíndico' ? 'selected' : ''}>Subsíndico (Fixa)</option>
          <option value="Fixa" ${g?.tipo === 'Fixa' ? 'selected' : ''}>Fixa (Condômino específico)</option>
        </select>
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Disponível para sorteio</label>
        <select name="disponivel" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option value="true" ${g?.disponivel ? 'selected' : ''}>Sim</option>
          <option value="false" ${!g?.disponivel ? 'selected' : ''}>Não</option>
        </select>
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Condômino Responsável (se fixa)</label>
        <select name="condomino_id" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option value="">Selecione...</option>
          ${localDB.condominos.map(c => `<option value="${c.id}" ${g?.condomino_id === c.id ? 'selected' : ''}>${c.nome}</option>`).join('')}
        </select>
      </div>
      <div class="flex justify-end space-x-2">
        <button type="button" onclick="closeModal()" class="px-4 py-2 border rounded-lg hover:bg-gray-100">Cancelar</button>
        <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">Salvar</button>
      </div>
    </form>
  `;
  modal.classList.remove('hidden');
  modal.style.display = 'flex';

  document.getElementById('form-garagem').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData.entries());
    data.disponivel = data.disponivel === 'true';
    data.condomino_id = data.condomino_id ? parseInt(data.condomino_id) : null;
    const client = getClient();
    if (id) {
      if (supabaseClient) {
        await client.from('garagens').update(data).eq('id', id);
      } else {
        localDB.update('garagens', id, data);
      }
    } else {
      if (supabaseClient) {
        await client.from('garagens').insert([data]);
      } else {
        localDB.insert('garagens', data);
      }
    }
    closeModal();
    carregarGaragens();
    atualizarDashboard();
  });
}

function editarGaragem(id) {
  openModalGaragem(id);
}

async function excluirGaragem(id) {
  if (!confirm('Tem certeza que deseja excluir esta garagem?')) return;
  const client = getClient();
  if (supabaseClient) {
    await client.from('garagens').delete().eq('id', id);
  } else {
    localDB.delete('garagens', id);
  }
  carregarGaragens();
  atualizarDashboard();
}

// ==================== SORTEIO DE GARAGENS ====================
async function realizarSorteio() {
  const client = getClient();
  let condominos = [];
  let garagens = [];

  if (supabaseClient) {
    const { data: condData } = await client.from('condominos').select('id, nome, tipo, qtd_carros, condomino_fixo_id');
    condominos = condData || [];
    const { data: garData } = await client.from('garagens').select('id, numero, tipo, disponivel, condomino_id');
    garagens = garData || [];
  } else {
    condominos = localDB.condominos;
    garagens = localDB.garagens;
  }

  // Filtrar garagens disponíveis para sorteio (não fixas, não reservadas)
  const garagensSorteio = garagens.filter(g => g.disponivel && g.tipo === 'Comum');
  // Filtrar condôminos elegíveis para sorteio
  const condominosSorteio = condominos.filter(c => {
    // Excluir síndico e subsíndico (eles têm garagem fixa)
    if (c.tipo === 'Síndico' || c.tipo === 'Subsíndico') return false;
    // Se tem apenas um carro, já deve ter garagem fixa (não participa do sorteio)
    if (c.qtd_carros === 1) return false;
    return true;
  });

  if (garagensSorteio.length === 0 || condominosSorteio.length === 0) {
    alert('Não há garagens disponíveis para sorteio ou condôminos elegíveis.');
    return;
  }

  // Embaralhar garagens e condôminos
  const garagensEmbaralhadas = [...garagensSorteio].sort(() => Math.random() - 0.5);
  const condominosEmbaralhados = [...condominosSorteio].sort(() => Math.random() - 0.5);

  const resultados = [];
  const maxAllocations = Math.min(garagensEmbaralhadas.length, condominosEmbaralhados.length);

  for (let i = 0; i < maxAllocations; i++) {
    const garagem = garagensEmbaralhadas[i];
    const condomino = condominosEmbaralhados[i];
    // Atribuir garagem ao condômino
    if (supabaseClient) {
      await client.from('garagens').update({ condomino_id: condomino.id, disponivel: false }).eq('id', garagem.id);
    } else {
      localDB.update('garagens', garagem.id, { condomino_id: condomino.id, disponivel: false });
    }
    resultados.push({ garagem: garagem.numero, condomino: condomino.nome });
  }

  // Exibir resultados
  const resultadoDiv = document.getElementById('resultado-sorteio');
  const listaResultados = document.getElementById('lista-resultados');
  listaResultados.innerHTML = resultados.map(r => `
    <div class="p-3 bg-white border rounded shadow-sm">
      <span class="font-semibold">${r.condomino}</span> → Garagem <span class="font-semibold">${r.garagem}</span>
    </div>
  `).join('');
  resultadoDiv.classList.remove('hidden');
  resultadoDiv.style.display = 'block';

  // Atualizar tabelas
  carregarGaragens();
  atualizarDashboard();
  alert(`Sorteio realizado! ${resultados.length} garagens foram alocadas.`);
}

// ==================== CHAT ====================
async function carregarMensagensChat() {
  const client = getClient();
  let mensagens = [];
  if (supabaseClient) {
    const { data } = await client.from('chat_messages').select('*').order('created_at', { ascending: false }).limit(50);
    mensagens = data || [];
  } else {
    mensagens = localDB.chat_messages.slice(-50).reverse();
  }
  const chatMessages = document.getElementById('chat-messages');
  chatMessages.innerHTML = '';
  if (mensagens.length === 0) {
    chatMessages.innerHTML = '<div class="text-center text-gray-500">Nenhuma mensagem ainda. Seja o primeiro a conversar!</div>';
    return;
  }
  mensagens.forEach(msg => {
    const div = document.createElement('div');
    div.className = 'p-3 bg-gray-100 rounded-lg';
    div.innerHTML = `
      <div class="font-semibold text-blue-600">${msg.autor || 'Anônimo'}</div>
      <div class="text-gray-700">${msg.texto}</div>
      <div class="text-xs text-gray-500 mt-1">${new Date(msg.created_at).toLocaleString('pt-BR')}</div>
    `;
    chatMessages.appendChild(div);
  });
  chatMessages.scrollTop = chatMessages.scrollHeight;
}

async function enviarMensagem() {
  const input = document.getElementById('chat-input');
  const texto = input.value.trim();
  if (!texto) return;
  const autor = prompt('Digite seu nome (ou deixe em branco para Anônimo):') || 'Anônimo';
  const client = getClient();
  const novaMensagem = { autor, texto, created_at: new Date().toISOString() };
  if (supabaseClient) {
    await client.from('chat_messages').insert([novaMensagem]);
  } else {
    localDB.insert('chat_messages', novaMensagem);
  }
  input.value = '';
  carregarMensagensChat();
}

// ==================== COZINHA (UTENSÍLIOS) ====================
async function carregarUtensilios() {
  const client = getClient();
  let data = [];
  if (supabaseClient) {
    const { data: utData } = await client.from('utensilios').select('*').order('nome');
    data = utData || [];
  } else {
    data = localDB.utensilios.sort((a, b) => a.nome.localeCompare(b.nome));
  }
  const lista = document.getElementById('lista-utensilios');
  lista.innerHTML = '';
  data.forEach(ut => {
    const div = document.createElement('div');
    div.className = 'border border-gray-200 rounded-lg p-4 hover:shadow-md transition-shadow';
    div.innerHTML = `
      <div class="flex justify-between items-start">
        <div>
          <h4 class="font-semibold text-lg">${ut.nome}</h4>
          <p class="text-gray-600 text-sm">${ut.descricao || ''}</p>
          <div class="mt-2 text-sm text-gray-500">
            <span class="inline-block bg-blue-100 text-blue-800 px-2 py-1 rounded mr-2">${ut.categoria || 'Sem categoria'}</span>
            <span class="inline-block bg-green-100 text-green-800 px-2 py-1 rounded">${ut.quantidade || 1} un.</span>
          </div>
        </div>
        <div class="flex space-x-2">
          <button onclick="editarUtensilio(${ut.id})" class="text-blue-600 hover:underline text-sm">Editar</button>
          <button onclick="excluirUtensilio(${ut.id})" class="text-red-600 hover:underline text-sm">Excluir</button>
        </div>
      </div>
    `;
    lista.appendChild(div);
  });
}

function openModalUtensilio(id = null) {
  const modal = document.getElementById('modal');
  const title = document.getElementById('modal-title');
  const content = document.getElementById('modal-content');
  title.textContent = id ? 'Editar Utensílio' : 'Novo Utensílio';
  const ut = id ? (supabaseClient ? null : localDB.utensilios.find(u => u.id === id)) : null;
  content.innerHTML = `
    <form id="form-utensilio" ${id ? `data-id="${id}"` : ''}>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Nome *</label>
        <input type="text" name="nome" value="${ut?.nome || ''}" required class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Descrição</label>
        <textarea name="descricao" rows="3" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">${ut?.descricao || ''}</textarea>
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Categoria</label>
        <input type="text" name="categoria" value="${ut?.categoria || ''}" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="mb-4">
        <label class="block text-sm font-medium mb-1">Quantidade</label>
        <input type="number" name="quantidade" value="${ut?.quantidade || 1}" min="1" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
      </div>
      <div class="flex justify-end space-x-2">
        <button type="button" onclick="closeModal()" class="px-4 py-2 border rounded-lg hover:bg-gray-100">Cancelar</button>
        <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">Salvar</button>
      </div>
    </form>
  `;
  modal.classList.remove('hidden');
  modal.style.display = 'flex';

  document.getElementById('form-utensilio').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData.entries());
    data.quantidade = parseInt(data.quantidade) || 1;
    const client = getClient();
    if (id) {
      if (supabaseClient) {
        await client.from('utensilios').update(data).eq('id', id);
      } else {
        localDB.update('utensilios', id, data);
      }
    } else {
      if (supabaseClient) {
        await client.from('utensilios').insert([data]);
      } else {
        localDB.insert('utensilios', data);
      }
    }
    closeModal();
    carregarUtensilios();
  });
}

function editarUtensilio(id) {
  openModalUtensilio(id);
}

async function excluirUtensilio(id) {
  if (!confirm('Tem certeza que deseja excluir este utensílio?')) return;
  const client = getClient();
  if (supabaseClient) {
    await client.from('utensilios').delete().eq('id', id);
  } else {
    localDB.delete('utensilios', id);
  }
  carregarUtensilios();
}

// ==================== DOCUMENTOS ====================
function gerarDocumento(tipo) {
  const client = getClient();
  let conteudo = '';
  const dataAtual = new Date().toLocaleDateString('pt-BR');
  const horaAtual = new Date().toLocaleTimeString('pt-BR');

  if (tipo === 'reserva-salao') {
    conteudo = `
      <!DOCTYPE html>
      <html>
      <head>
        <title>Reserva de Salão de Festas</title>
        <style>
          body { font-family: Arial, sans-serif; margin: 40px; }
          h1 { color: #1e40af; }
          .header { text-align: center; margin-bottom: 30px; }
          .content { line-height: 1.6; }
          .footer { margin-top: 50px; font-size: 12px; color: #666; }
          @media print { body { margin: 20px; } }
        </style>
      </head>
      <body>
        <div class="header">
          <h1>Reserva de Salão de Festas</h1>
          <p>Sistema de Condomínio</p>
        </div>
        <div class="content">
          <p><strong>Data da reserva:</strong> ${dataAtual}</p>
          <p><strong>Horário:</strong> ${horaAtual}</p>
          <p><strong>Responsável:</strong> ___________________________</p>
          <p><strong>Unidade:</strong> ___________________________</p>
          <p><strong>Evento:</strong> ___________________________</p>
          <p><strong>Período:</strong> _____:_____ às _____:_____</p>
          <p><strong>Número de convidados:</strong> _________</p>
          <p><strong>Observações:</strong></p>
          <p>_________________________________________________________________</p>
          <p>_________________________________________________________________</p>
          <p>Declaro que estou ciente das regras do condomínio para uso do salão de festas e me responsabilizo por eventuais danos.</p>
          <p style="margin-top: 40px;">Assinatura: ___________________________</p>
        </div>
        <div class="footer">
          <p>Documento gerado em ${dataAtual} às ${horaAtual} pelo Sistema de Condomínio.</p>
        </div>
      </body>
      </html>
    `;
  } else if (tipo === 'relatorio-utensilios') {
    const utensilios = supabaseClient ? [] : localDB.utensilios;
    const listaUtensilios = utensilios.map(u => `
      <tr>
        <td style="padding: 8px; border: 1px solid #ccc;">${u.nome}</td>
        <td style="padding: 8px; border: 1px solid #ccc;">${u.categoria || '-'}</td>
        <td style="padding: 8px; border: 1px solid #ccc;">${u.quantidade || 1}</td>
        <td style="padding: 8px; border: 1px solid #ccc;">${u.descricao || '-'}</td>
      </tr>
    `).join('');

    conteudo = `
      <!DOCTYPE html>
      <html>
      <head>
        <title>Relatório de Utensílios da Cozinha</title>
        <style>
          body { font-family: Arial, sans-serif; margin: 40px; }
          h1 { color: #1e40af; }
          table { width: 100%; border-collapse: collapse; margin-top: 20px; }
          th { background-color: #f0f0f0; padding: 10px; border: 1px solid #ccc; text-align: left; }
        </style>
      </head>
      <body>
        <div class="header">
          <h1>Relatório de Utensílios da Cozinha</h1>
          <p>Sistema de Condomínio</p>
        </div>
        <p><strong>Data:</strong> ${dataAtual}</p>
        <p><strong>Total de itens:</strong> ${utensilios.length}</p>
        <table>
          <thead>
            <tr>
              <th>Nome</th>
              <th>Categoria</th>
              <th>Quantidade</th>
              <th>Descrição</th>
            </tr>
          </thead>
          <tbody>
            ${listaUtensilios}
          </tbody>
        </table>
        <div class="footer" style="margin-top: 50px; font-size: 12px; color: #666;">
          <p>Documento gerado em ${dataAtual} às ${horaAtual} pelo Sistema de Condomínio.</p>
        </div>
      </body>
      </html>
    `;
  }

  // Abrir nova janela para impressão
  const win = window.open('', '_blank');
  win.document.write(conteudo);
  win.document.close();
  win.print();
}

// ==================== MODAL GLOBAL ====================
function openModal(tipo, id = null) {
  switch (tipo) {
    case 'condomino':
      openModalCondomino(id);
      break;
    case 'unidade':
      openModalUnidade(id);
      break;
    case 'garagem':
      openModalGaragem(id);
      break;
    case 'utensilio':
      openModalUtensilio(id);
      break;
  }
}

function closeModal() {
  const modal = document.getElementById('modal');
  modal.classList.add('hidden');
  modal.style.display = 'none';
}

// ==================== INICIALIZAÇÃO ====================
document.addEventListener('DOMContentLoaded', () => {
  initSupabase();
  // Mostrar dashboard por padrão
  showSection('dashboard');
  // Atualizar dashboard
  atualizarDashboard();
  // Configurar botões de navegação (já têm onclick inline, mas garantir)
  document.querySelectorAll('nav .nav-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      // Remover classe active de todos
      document.querySelectorAll('nav .nav-btn').forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
    });
  });
});
