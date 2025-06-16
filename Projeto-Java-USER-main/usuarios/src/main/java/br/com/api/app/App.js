import React, { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
  const [usuarios, setUsuarios] = useState([]);
  const [form, setForm] = useState({ nome: '', email: '', telefone: '', id: null });
  const [mensagem, setMensagem] = useState('');

  const url = 'http://localhost:8080'; // Altere para o endereço da sua API

  useEffect(() => {
    listarUsuarios();
  }, []);

  const listarUsuarios = async () => {
    try {
      const response = await axios.get(`${url}/usuarios`);
      setUsuarios(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const enviarFormulario = async (e) => {
    e.preventDefault();

    const metodo = form.id ? 'put' : 'post';
    const endpoint = form.id ? `${url}/usuarios/${form.id}` : `${url}/usuarios`;

    try {
      const response = await axios[metodo](endpoint, form);
      setMensagem('Usuário salvo com sucesso!');
      setForm({ nome: '', email: '', telefone: '', id: null });
      listarUsuarios();
    } catch (error) {
      setMensagem(error.response?.data?.mensagem || 'Erro ao salvar usuário');
    }
  };

  const removerUsuario = async (id) => {
    if (!window.confirm('Deseja remover este usuário?')) return;
    try {
      await axios.delete(`${url}/usuarios/${id}`);
      listarUsuarios();
    } catch (error) {
      console.error(error);
    }
  };

  const editarUsuario = (user) => {
    setForm({ ...user, id: user.codigo });
  };

  return (
    <div className="container">
      <h1>Cadastro de Usuários</h1>

      <form onSubmit={enviarFormulario}>
        <input
          type="text"
          placeholder="Nome"
          value={form.nome}
          onChange={(e) => setForm({ ...form, nome: e.target.value })}
          required
        />
        <input
          type="email"
          placeholder="Email"
          value={form.email}
          onChange={(e) => setForm({ ...form, email: e.target.value })}
          required
        />
        <input
          type="text"
          placeholder="Telefone (XX) 9XXXX-XXXX"
          value={form.telefone}
          onChange={(e) => setForm({ ...form, telefone: e.target.value })}
          required
        />
        <button type="submit">{form.id ? 'Atualizar' : 'Cadastrar'}</button>
      </form>

      {mensagem && <p>{mensagem}</p>}

      <table>
        <thead>
          <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {usuarios.map((u) => (
            <tr key={u.codigo}>
              <td>{u.nome}</td>
              <td>{u.email}</td>
              <td>{u.telefone}</td>
              <td>
                <button onClick={() => editarUsuario(u)}>Editar</button>
                <button onClick={() => removerUsuario(u.codigo)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;