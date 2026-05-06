// supabase-client.js – cria e exporta o cliente Supabase para uso no navegador
// Usa o script CDN carregado em index.html (https://cdn.jsdelivr.net/npm/@supabase/supabase-js@2)
// O objeto global `supabase` já está disponível após o carregamento do script.

/**
 * Obtém o cliente Supabase configurado.
 * @returns {any} Instância Supabase ou null se não carregada.
 */
export function getSupabase() {
  if (typeof supabase !== "undefined") {
    return supabase;
  }
  console.error("Supabase não carregado. Verifique a conexão com a CDN.");
  return null;
}
