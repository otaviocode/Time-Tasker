package com.example.demo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Login")
public class Login implements Serializable {

	Usuario usuario = new Usuario(null, null, null);
	private static final long serialVersionUID = 1L;

	@Id
	@Column // qualquer coisa remove esse !
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	private String senha;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, senha, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(senha, other.senha)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Login [usuario=" + usuario + ", id=" + id + ", email=" + email + ", senha=" + senha + ", getUsuario()="
				+ getUsuario() + ", getId()=" + getId() + ", getEmail()=" + getEmail() + ", getSenha()=" + getSenha()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}



	public static Optional<Login> map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Object getTitulo() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Login> getDescricao() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getPrioridade() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getPrazo() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
