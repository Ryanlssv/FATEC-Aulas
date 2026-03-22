const { createClient } = require('@supabase/supabase-js');

// Substitua pelas credenciais que aparecem no seu painel do Supabase (Project Settings > API)
const supabaseUrl = 'https://ewwvsjufhixlxoetfmwv.supabase.co';
const supabaseKey = 'sb_publishable_LjW3M12V_rsYGrYyrtY2zw_YzVOZSZT';
const supabase = createClient(supabaseUrl, supabaseKey);

async function executarOperacoes() {
  try {
    // --- 1. CREATE (Inserir) ---
    const { data: inserido, error: errInsert } = await supabase
      .from('produtos')
      .insert([{ nome: 'Camisa Azul', valor: 89.90 }])
      .select(); // .select() é necessário para retornar o item criado

    if (errInsert) throw errInsert;
    const id = inserido[0].id;
    console.log('1. Produto inserido:', inserido[0]);

    // --- 2. PUT (Atualizar) com Validação ---
    const novoValor = 75.00;
    if (novoValor < 0) {
      console.log('Erro: Preço negativo não permitido.');
    } else {
      const { data: atualizado, error: errUpdate } = await supabase
        .from('produtos')
        .update({ valor: novoValor })
        .eq('id', id) // Filtra pelo ID
        .select();
      
      if (errUpdate) throw errUpdate;
      console.log('2. Produto atualizado:', atualizado[0]);
    }

    // --- 3. READ (Consultar) ---
    const { data: lista, error: errRead } = await supabase
      .from('produtos')
      .select('*');

    if (errRead) throw errRead;
    console.log('3. Lista atual:', lista);

    // --- 4. DELETE (Remover) ---
    const { error: errDelete } = await supabase
      .from('produtos')
      .delete()
      .eq('id', id);

    if (errDelete) throw errDelete;
    console.log('4. Produto removido com sucesso.');

  } catch (error) {
    console.error('Erro na operação:', error.message);
  }
}

executarOperacoes();