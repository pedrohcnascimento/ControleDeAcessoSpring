package com.senai.ControleDeAcessoSpring.infrastructure;

import com.senai.ControleDeAcessoSpring.interface_ui.controller.OcorrenciaController;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class mqttSubscriber {
    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENT_ID = "ServidorJava";
    private static final String TOPICO = "catraca/rfid";
    @Autowired
    private OcorrenciaController ocorrenciaController;

    @PostConstruct
    public void iniciarMqtt() {
        try {
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);
            client.connect();
            client.subscribe(TOPICO, (topic, msg) -> {
                String idAcesso = new String(msg.getPayload());
                ocorrenciaController.criarOcorrenciaAtraso(idAcesso);
            });
            System.out.println("Inscrito no tópico MQTT: " + TOPICO);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
