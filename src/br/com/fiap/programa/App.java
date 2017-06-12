package br.com.fiap.programa;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia_Atividade3");
		EntityManager em = emf.createEntityManager();

		Pedido pedido = new Pedido();
		pedido.setData(new Date());
		pedido.setDescricao("Galaxy S8 Samsung");
		pedido.setValor(2000.00);

		Cliente cliente = new Cliente();
		cliente.setNome("João Andrade");
		cliente.setEmail("joao.andrade@pedro.com");
		Set<Pedido> pedidos = new HashSet<>();
		pedido.setCliente(cliente);
		pedidos.add(pedido);
		cliente.setPedidos(pedidos);

		GenericDao<Cliente> genericDaoCliente = new GenericDao<>(Cliente.class);
		GenericDao<Pedido> genericDaoPedido = new GenericDao<>(Pedido.class);

		genericDaoCliente.adicionar(cliente);
		genericDaoPedido.adicionar(pedido);

		cliente.setEmail("jonnhy.pedro@email.com");
		pedido.setValor(4000.00);

		genericDaoCliente.atualizar(cliente);
		genericDaoPedido.atualizar(pedido);

		List<Cliente> clientes = genericDaoCliente.listar();
		List<Pedido> pedidoList = genericDaoPedido.listar();

		genericDaoCliente.buscar(clientes.get(0).getIdcliente());
		genericDaoPedido.buscar(pedidoList.get(0).getIdPedido());

		genericDaoCliente.remover(clientes.get(0));
		genericDaoPedido.remover(pedidoList.get(0));

	}

}
