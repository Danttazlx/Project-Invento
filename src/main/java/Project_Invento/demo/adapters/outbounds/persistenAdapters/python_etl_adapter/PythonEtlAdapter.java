package Project_Invento.demo.adapters.outbounds.persistenAdapters.python_etl_adapter;

import Project_Invento.demo.dto.DocumentInput;
import Project_Invento.demo.dto.EtlResponseDto;
import Project_Invento.demo.infrastructore.config.WebClientConfig;
import Project_Invento.demo.ports.out.etl.EtlProcessorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;

@Component
@RequiredArgsConstructor
public class PythonEtlAdapter implements EtlProcessorPort {

    private final WebClientConfig clientConfig;

    @Override
    public EtlResponseDto processEtl(DocumentInput file) {

        ByteArrayResource byteResource = new ByteArrayResource(file.content()) {
            @Override
            public String getFilename() {
                return file.fileName();
            }
        };

        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        builder
                .part("file", byteResource)
                .contentType(MediaType.parseMediaType(file.contentType()));


        return clientConfig.pythonWebClient()    // a gente retorna o client
                .post()                                                           // metodo HTTP
                .uri("/etl/process")                                          // Uri rota que foi mapeada no endpoint do servidor
                .contentType(MediaType.MULTIPART_FORM_DATA)                       // contentType basicamente é o tipo da requisicao
                .accept(MediaType.APPLICATION_JSON)                               // accept é oque a gente espera na resposta do servidor
                .body(BodyInserters.fromMultipartData(builder.build()))           // body basicamente diz o que vai na requisicao
                .retrieve( )                                                      // Retrieve basicamnte define oque vem apos dele o response do servidor
                .bodyToMono(EtlResponseDto.class)                                 // Comverte oque vem no Body da Requisicao para 1 ou 0 (Mono)
                .block();                                                         // Bloqueia as Threds
    }
}
