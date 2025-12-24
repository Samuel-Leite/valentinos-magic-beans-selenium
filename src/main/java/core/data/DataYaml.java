package core.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe utilitária para leitura de arquivos YAML.
 *
 * Responsável por carregar:
 * - Arquivos de configuração de ambiente (url-<env>.yml)
 * - Arquivos de massa de dados de teste (<env>/<fileName>.yml)
 *
 * Utiliza Jackson com YAMLFactory para mapear os dados em objetos Java.
 */
@Log4j2
public class DataYaml {

    /**
     * Retorna o arquivo YAML de configuração de ambiente.
     * O nome do arquivo é construído a partir da variável de sistema "env".
     * Exemplo: url-qa.yml, url-prod.yml
     *
     * @return Arquivo YAML de configuração
     */
    private static File getYamlConfFile(){
        String env = System.getProperty("env");
        log.info("Carregando a URL do ambiente de '{}'", env);
        return new File("./src/main/resources/conf/url-" + env + ".yml");
    }

    /**
     * Retorna o arquivo YAML de massa de dados de teste.
     * O caminho é construído a partir da variável de sistema "env" e do nome do arquivo.
     * Exemplo: ./src/test/resources/data/qa/login.yml
     *
     * @param fileName Nome do arquivo de massa (sem extensão)
     * @return Arquivo YAML de massa de dados
     */
    private static File getYamlDataFile(String fileName){
        String env = System.getProperty("env");
        log.info("Carregando a massa de teste do ambiente de '{}'", env);
        return new File("./src/test/resources/data/" + env + "/" + fileName + ".yml");
    }

    /**
     * Lê um arquivo YAML de massa de dados e retorna os valores como LinkedHashMap.
     *
     * @param fileName Nome do arquivo de massa (sem extensão)
     * @param titulo   Título dentro do YAML que agrupa os dados
     * @return HashMap com os valores do título especificado
     * @throws Exception Caso ocorra erro ao ler o arquivo
     */
    @SneakyThrows
    public static LinkedHashMap<String,String> getMapYamlValues(String fileName, String titulo){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            Map<String , Object> maps = mapper.readValue(getYamlDataFile(fileName), Map.class);
            log.info("Retornando objeto HashMap com massa de dados do arquivo '{}' com titulo '{}'", fileName, titulo);
            return (LinkedHashMap<String, String>) maps.get(titulo);
        } catch (IOException e) {
            log.error("Erro ao tentar ler o arquivo de massa {}.yml - {}", fileName, e.getMessage(), e);
            throw new Exception(e);
        }
    }

    /**
     * Lê o arquivo YAML de configuração e retorna a URL base do ambiente.
     *
     * @return String com a URL base
     * @throws Exception Caso ocorra erro ao ler o arquivo
     */
    @SneakyThrows
    public static String getUrlBase() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        Map<String, Object> maps = mapper.readValue(getYamlConfFile(), Map.class);
        return (String) maps.get("urlBase");
    }

    /**
     * Lê o arquivo YAML de configuração do Lighthouse e retorna os valores como Map.
     *
     * Estrutura esperada no YAML:
     * lighthouse:
     *   categories:
     *     performance: 0.9
     *     accessibility: 0.8
     *     best-practices: 0.85
     *     seo: 0.9
     *     pwa: 0.5
     *   audits:
     *     first-contentful-paint: 2000
     *     largest-contentful-paint: 2500
     *     cumulative-layout-shift: 0.1
     *
     * @return Mapa com os valores configurados
     * @throws Exception Caso ocorra erro ao ler o arquivo
     */
    @SneakyThrows
    public static Map<String, Object> getLighthouseConfig() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            File file = new File("./src/main/resources/conf/lighthouse.yml");
            Map<String, Object> maps = mapper.readValue(file, Map.class);
            log.info("Configuração Lighthouse carregada com sucesso");
            return maps;
        } catch (IOException e) {
            log.error("Erro ao tentar ler o arquivo lighthouse.yml - {}", e.getMessage(), e);
            throw new Exception(e);
        }
    }
}