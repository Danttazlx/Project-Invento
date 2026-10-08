package Project_Invento.demo.ports.out.etl;

import Project_Invento.demo.dto.DocumentInput;
import Project_Invento.demo.dto.EtlResponseDto;


public interface EtlProcessorPort {

    EtlResponseDto processEtl(DocumentInput file);

}
