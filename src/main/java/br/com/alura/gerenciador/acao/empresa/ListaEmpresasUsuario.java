package main.java.br.com.alura.gerenciador.acao.empresa;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import main.java.br.com.alura.gerenciador.acao.AcaoComEntityManager;
import main.java.br.com.alura.gerenciador.modelo.Empresa;
import main.java.br.com.alura.gerenciador.modelo.Usuario;
import main.java.br.com.alura.gerenciador.repository.EmpresaRepositoryMySQL;

@NoArgsConstructor
public class ListaEmpresasUsuario extends AcaoComEntityManager {

	private EmpresaRepositoryMySQL repository = new EmpresaRepositoryMySQL(this.em);

	public ListaEmpresasUsuario(EntityManager em) {
		super(em);
	}
	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("listando empresas do usuário");

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		
		List<Empresa> listaEmpresas = repository.findEmpresasOfUsuarioById(usuario.getId());
		request.setAttribute("empresas", listaEmpresas);
	
		return "forward:usuarioEmpresas.jsp";
	}
}