package br.com.gerenciador.controller.commands.usuario;

import java.io.IOException;

import br.com.gerenciador.controller.commands.Command;
import br.com.gerenciador.util.ControllerUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NovoUsuarioFormCommand implements Command {

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(
        ControllerUtil.enderecoJSP("formNovoUsuario.jsp"));
        rd.forward(request, response);
    }
}