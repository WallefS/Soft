package br.ueg.posse.soft.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ueg.posse.soft.model.Usuario;

public class UsuariosImpl implements UsuariosQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Optional<Usuario> nome(String nome) {
		return manager
				.createQuery("from Usuario where lower(nome) = lower(:nome)", Usuario.class)
				.setParameter("nome", nome).getResultList().stream().findFirst();
	}

	@Override
	public List<String> permissoes(Usuario usuario) {
		return manager.createQuery(
				"select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}

}