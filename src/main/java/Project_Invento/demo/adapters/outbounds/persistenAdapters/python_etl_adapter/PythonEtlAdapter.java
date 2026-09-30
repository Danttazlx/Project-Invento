package Project_Invento.demo.adapters.outbounds.persistenAdapters.python_etl_adapter;

import Project_Invento.demo.dto.DocumentInput;
import Project_Invento.demo.infrastructore.config.WebClientConfig;
import Project_Invento.demo.ports.out.etl.EtlProcessorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PythonEtlAdapter implements EtlProcessorPort {

    private final WebClientConfig clientConfig;

    @Override
    public void processEtl(DocumentInput file) {

    clientConfig.pythonWebClient();


    }
}
   