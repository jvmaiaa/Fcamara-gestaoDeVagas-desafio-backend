package com.desafio.backend.infra.exceptions;

public class ExceptionMessages {

    public static final String ENDERECO_VINCULADO_A_EMPRESA = "Este esdereço não pode ser excluído pois está vinculado com uma empresa.";
    public static final String ENDERECO_NAO_ENCONTRADO = "O endereço com id %d não foi encontrado.";
    public static final String ENDERECO_JA_CADASTRADO = "O endereço já está vinculado a uma empresa.";
    public static final String EMPRESA_NAO_ENCONTRADA = "A empresa com id %d não foi encontrada.";
    public static final String EMPRESA_OU_VEICULOS_JA_CADASTRADOS = "A empresa ou veículos informados, já estão cadastrados em outro relatóiro.";
    public static final String EMPRESA_VINCULADA_A_RELATORIO = "A empresa com id %d não pode ser excluída pois está vinculada a um relatório.";
    public static final String VEICULO_NAO_ENCONTRADO = "O veículo com id %d não foi encontrado.";
    public static final String VEICULO_VINCULADO_A_RELATORIO = "O veículo com id %d não pode ser excluído pois está vinculado a um relatório.";
    public static final String ENTIDADE_NAO_ENCONTRADA = "A entidade não foi encontrada, verifique o Id e tente novamente.";
    public static final String RELATORIO_COMPLETO = "O relatório com id %d já foi finalizado.";
    public static final String RELATORIO_NAO_ENCONTRADO = "O relatório com id %d não foi encontrado.";
    public static final String RELATORIO_SAIDA_ANTES_ENTRADA = "O relatório deve possui uma saída após uma entrada.";
    public static final String DATA_INVALIDA = "A data deve possuir um formato válido.";
    public static final String USUARIO_JA_EXISTE = "O login que você informou já existe.";
    public static final String ERRO_GERACAO_TOKEN = "O token JWT não pode ser gerado.";
    public static final String SEM_AUTORIZACAO = "Usuário sem autorização para executar essa ação.";
    public static final String DATA_COM_ORDEM_INVALIDA = "A data final deve ser após a data inicial.";
}
