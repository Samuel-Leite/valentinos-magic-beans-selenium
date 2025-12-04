package core.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
public class DataYaml {

    private static File getYamlDataFile(String fileName){
        String env = System.getProperty("env");
        log.info("Pegando arquivo {}.yml do ambiente {}", fileName, env);
        return new File("./src/test/resources/data/" + env + "/" + fileName + ".yml");
    }

    @SneakyThrows
    public static LinkedHashMap<String,String> getMapYamlValues(String fileName, String titulo){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            Map<String , Object> maps = mapper.readValue(getYamlDataFile(fileName), Map.class);
            log.info("Retornando objeto HashMap com massa de dados do arquivo {} com titulo {}", fileName, titulo);
            return (LinkedHashMap<String, String>) maps.get(titulo);
        } catch (IOException e) {
            log.error("Erro ao tentar ler o arquivo de massa {}.yml - {}", fileName, e.getMessage(), e);
            throw new Exception(e);
        }
    }
}