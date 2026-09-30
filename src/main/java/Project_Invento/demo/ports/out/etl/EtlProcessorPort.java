package Project_Invento.demo.ports.out.etl;

import Project_Invento.demo.dto.DocumentInput;


public interface EtlProcessorPort {

    void processEtl(DocumentInput file);

}
