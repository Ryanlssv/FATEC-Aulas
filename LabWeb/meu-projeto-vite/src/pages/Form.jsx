import React from 'react'
import { useState } from 'react'
import './Form.css'
const Form = () => {
      const [nome,setNome] = useState("")
      const [assunto,setAssunto] = useState("")
      const [mensagem,setMensagem] = useState("")
      
      const handleSubmit = (e)=>{
        e.preventDefault()
        console.log("Enviando Formulario")
        console.log(nome,assunto,mensagem)
        setNome("")
        setAssunto("")
        setMensagem("")

      }

  return (
    <main>
      <h2>Formulario</h2>
      <form onSubmit={handleSubmit}>
        <fieldset>
          <legend>Dados</legend>
          <label>
            <span>Nome</span>
            <input type="text" name='nome' id='nome' placeholder='Nome completo' onChange={(e)=> setNome(e.target.value)} value={nome} />
          </label>
          <label>
            <span>Assunto</span>
            <input type="text" name='assunto' id='assunto' placeholder='Assunto' onChange={(e)=> setAssunto(e.target.value)} value={assunto} />
          </label>
          <label>
            <span>Mensagem</span>
            <input type="text" name='mensagem' id='mensagem' placeholder='mensagem' onChange={(e)2=> setMensagem(e.target.value)} value={mensagem}/>
          </label>
        </fieldset>
        <button type='submit'>Enviar</button>
      </form>
    </main>
  )
}

export default Form