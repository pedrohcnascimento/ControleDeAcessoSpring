package com.senai.ControleDeAcessoSpring.infrastructure.mqtt;

import com.senai.ControleDeAcessoSpring.inteface.controller.OcorrenciaController;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber {
    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENTE_ID = "ServidorJava";
    private static final String TOPICO = "catraca/rfid";
    @Autowired
    private OcorrenciaController controller;

    @PostConstruct
    public void iniciarMqtt() {
        try {
            MqttClient cliente = new MqttClient(BROKER, CLIENTE_ID);
            cliente.connect();
            cliente.subscribe(TOPICO, (topic, msg) -> {
                String idAcesso = new String(msg.getPayload());
                controller.criarOcorrenciaAtraso(idAcesso);
            });
            System.out.println("Inscrito no topico MQTT:" + TOPICO);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
