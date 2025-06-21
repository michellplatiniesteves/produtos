package br.com.produtos.fila;

import br.com.produtos.entidade.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PedidoFila {
    private final Logger logger = LoggerFactory.getLogger(PedidoFila.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    private final RabbitTemplate rabbitTemplate;

    public PedidoFila(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public Produto AdicionarFila(Produto produto){
        rabbitTemplate.convertAndSend(exchangeName,"",produto);
        logger.info("Adicionando o pedido a fila");
        return produto;
    }
}
