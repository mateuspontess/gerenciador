import { API_CONFIG } from "../../util/api-config.js";
import { ElementFactory } from "../../modules/elementFactory/ElementFactory.js";

/**
 * Classe que cria um novo elemento HTML representando uma empresa.
 *
 * @param {Object} empresa - Objeto contendo informações sobre a empresa.
 */
export class SearchEmpresasElementFactory extends ElementFactory {
	constructor() {
		super(API_CONFIG.EMPRESA.PARAM_ACAO.SEARCH);
	}
	
	createElement(empresa) {
	    const novoLi = document.createElement('li');
	    novoLi.classList.add('lista');
	    novoLi.id = empresa.id;
	    
	    const paragrafoNome = document.createElement('p');
	    paragrafoNome.classList.add('lista-nome');
	    paragrafoNome.id = 'lista-nome';
	    paragrafoNome.textContent = empresa.nome;
	
	    const paragrafoData = document.createElement('p');
	    paragrafoData.classList.add('lista-data');
	    paragrafoData.id = 'lista-data';
	    paragrafoData.textContent = empresa.data;
	    
	    novoLi.appendChild(paragrafoNome);
	    novoLi.appendChild(paragrafoData);
	    
	    return novoLi;
    }
}