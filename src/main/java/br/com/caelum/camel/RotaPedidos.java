package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidos {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			@Override //Para defir as rotas é sempre necessário sobreescrever o método abstrado configure() da classe RouteBuilder
			public void configure( ) throws Exception {
				from("file:pedidos?delay=5s&noop=true").
				log("${id}").
				split(). // Dividir uma mensagem em várias
					xpath("pedido/itens/item").
				filter(). // Filtrar para só receber as mensagens que eu quero
					xpath("/item/formato[text()='EBOOK']").
				marshal().xmljson(). //Converte mensagem de xml para json
				log("${body}").
				setHeader(Exchange.FILE_NAME, simple("${file:name.noext}-${header.CamelSplitIndex}.json")). // Muda nome do arquivo salvo
				to("file:saida");
			}

		});
		
		//Inicia o Context do Camel e pare ele, é obrigatório isso ao usar Java puro
		//Ao usar Spring, ele cuida dessa parte para nós 
		context.start();
		Thread.sleep(2000);
		context.stop();
	}
}
