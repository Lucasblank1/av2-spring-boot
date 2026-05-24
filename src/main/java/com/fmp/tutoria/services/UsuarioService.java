package com.fmp.tutoria.services;

import com.fmp.tutoria.models.Usuario;
import com.fmp.tutoria.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> listar(){ return repo.findAll(); }
    public Usuario salvar(Usuario obj){ return repo.save(obj); }
    public Usuario buscar(String id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado com ID: " + id)); }

    public Usuario atualizar(String id, Usuario alteracoes) {
        Usuario existente = buscar(id);

        if (alteracoes.getNome() != null) {
            existente.setNome(alteracoes.getNome());
        }
        if (alteracoes.getEmail() != null) {
            existente.setEmail(alteracoes.getEmail());
        }
        if (alteracoes.getSenha() != null) {
            existente.setSenha(alteracoes.getSenha());
        }
        if (alteracoes.getDataCadastro() != null) {
            existente.setDataCadastro(alteracoes.getDataCadastro());
        }
        if (alteracoes.getTipoPerfil() != null) {
            existente.setTipoPerfil(alteracoes.getTipoPerfil());
        }
        if (alteracoes.getTelefone() != null) {
            existente.setTelefone(alteracoes.getTelefone());
        }

        return repo.save(existente);
    }

    public void deletar(String id){
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Usuario nao encontrado com ID: " + id);
        }
        repo.deleteById(id);
    }
}
