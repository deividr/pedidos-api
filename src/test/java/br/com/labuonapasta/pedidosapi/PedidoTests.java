package br.com.labuonapasta.pedidosapi;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.labuonapasta.pedidosapi.model.Cliente;
import br.com.labuonapasta.pedidosapi.model.ItemPedido;
import br.com.labuonapasta.pedidosapi.model.Pedido;
import br.com.labuonapasta.pedidosapi.model.Produto;
import br.com.labuonapasta.pedidosapi.model.Usuario;
import br.com.labuonapasta.pedidosapi.repository.ClienteRepository;
import br.com.labuonapasta.pedidosapi.repository.PedidoRepository;
import br.com.labuonapasta.pedidosapi.repository.ProdutoRepository;
import br.com.labuonapasta.pedidosapi.repository.UsuarioRepository;

@SpringBootTest
class PedidoTests {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRespository;

    @Test
    void contextLoads() {
        Cliente cliente = clienteRepository.findById(1000l).get();
        // BeanUtils.copyProperties(, cliente);

        Usuario usuario = usuarioRespository.findById(1000l).get();
        // BeanUtils.copyProperties(usuarioRespository.findById(1l).get(), usuario);

        Produto produto = produtoRepository.findById(9000l).get();
        // BeanUtils.copyProperties(produtoRepository.findById(1l).get(), produto);

        Produto produto2 = produtoRepository.findById(9001l).get();
        // BeanUtils.copyProperties(produtoRepository.findById(1l).get(), produto);

        Pedido pedidoNovo = new Pedido();

        pedidoNovo.setCliente(cliente);
        pedidoNovo.setUsuario(usuario);
        pedidoNovo.setNumeroPedido(503);
        pedidoNovo.setDataRetirada(Date.valueOf(LocalDate.now()));
        pedidoNovo.setRetirado(false);

        ItemPedido item1 = new ItemPedido();
        item1.setPedido(pedidoNovo);
        item1.setProduto(produto);
        item1.setQtde(new BigDecimal("2.000"));

        ItemPedido item2 = new ItemPedido();
        item2.setPedido(pedidoNovo);
        item2.setProduto(produto2);
        item2.setQtde(new BigDecimal("2.000"));

        pedidoNovo.getItens().add(item1);
        pedidoNovo.getItens().add(item2);

        System.out.println("Vai incluir o pedido!!!!");

        pedidoRepository.save(pedidoNovo);

        System.out.println("Pedido foi incluído, com código: " + pedidoNovo.getPedId());

        /*
         * pedidoNovo.getItens().add(item2);
         * 
         * pedidoRepository.save(pedidoNovo);
         */
    }

    @Test
    void atualizar() {
        Pedido pedido = pedidoRepository.findById(11l).get();

        Produto produto = produtoRepository.findById(9002l).get();
        Produto produto2 = produtoRepository.findById(9002l).get();

        ItemPedido item1 = new ItemPedido();
        item1.setPedido(pedido);
        item1.setProduto(produto);
        item1.setQtde(new BigDecimal("2.000"));

        ItemPedido item2 = new ItemPedido();
        item2.setPedido(pedido);
        item2.setProduto(produto2);
        item2.setQtde(new BigDecimal("2.000"));

        List<ItemPedido> itens = new ArrayList<ItemPedido>();
        itens.add(item1);
        itens.add(item2);

        pedido.setItens(itens);

        pedidoRepository.save(pedido);
    }

}
